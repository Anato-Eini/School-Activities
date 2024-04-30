import Classes.Request;
import com.google.gson.Gson;
import com.sun.tools.jconsole.JConsoleContext;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ClientHandler implements Runnable {
    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private volatile boolean isRunning = true;
    private final Socket socket;
    private final Connection connection;

    public ClientHandler(Socket socket, Connection connection) {
            this.socket = socket;
            this.connection = connection;
            clientHandlers.add(this);
    }

    public byte[] readBytes() throws IOException {
        InputStream in = socket.getInputStream();
        DataInputStream dis = new DataInputStream(in);

        int len = dis.readInt();
        byte[] data = new byte[len];
        if (len > 0) {
            dis.readFully(data);
        }
        return data;
    }

    public void run() {
         String messageFromClient;
         String operation = null;
         while (socket.isConnected() && isRunning) {
            try {
                byte[] dataFromClient = readBytes();

                if(dataFromClient == null) continue;

                messageFromClient = new String(dataFromClient, StandardCharsets.UTF_8);
                Gson gson = new Gson();
                Request request = gson.fromJson(messageFromClient, Request.class);
                HashMap<String, Object> requestData= request.data;
                operation = request.operation;

                switch (request.operation) {
                    case "register" -> {
                        System.out.println("Register");
                        String username = (String) requestData.get("username");
                        String password = (String) requestData.get("password");
                        String firstName = (String) requestData.get("firstName");
                        String lastName = (String) requestData.get("lastName");
                        String query = """
                                    INSERT INTO users (username, password, firstName, lastName) VALUES (?, ?, ?, ?)
                                """;
                        PreparedStatement statement = connection.prepareStatement(query);
                        statement.setString(1, username);
                        statement.setString(2, password);
                        statement.setString(3, firstName);
                        statement.setString(4, lastName);
                        int rows = statement.executeUpdate();
                        sendBytes(writeResponse("register", rows > 0, rows > 0 ? "Registered Successfully" : "Register failed", null));
                    }
                    case "login" -> {
                        System.out.println("Login");
                        String username = (String) requestData.get("username");
                        String password = (String) requestData.get("password");

                        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
                        PreparedStatement statement = connection.prepareStatement(query);
                        statement.setString(1, username);
                        statement.setString(2, password);

                        ResultSet resultSet = statement.executeQuery();

                        Map<String, String> loginData = new HashMap<>();
                        if(resultSet.next()){
                            loginData.put("id", resultSet.getString(("id")));
                            loginData.put("username", resultSet.getString(("username")));
                            loginData.put("firstname", resultSet.getString(("firstname")));
                            loginData.put("lastname", resultSet.getString(("lastname")));
                            loginData.put("privilege", resultSet.getString(("privilege")));
                            loginData.put("createdAt", resultSet.getString(("createdAt")));
                            loginData.put("updatedAt", resultSet.getString(("updatedAt")));
                            sendBytes(writeResponse("login", true, "Login Success", loginData));
                        }else{
                            sendBytes(writeResponse("login", false, "Incorrect username or password", null));
                        }
                    }

                    case "createEvent" -> {
                        System.out.println("Create Event");

                    }
                }
            } catch (Throwable e) {
                e.printStackTrace();
                if (e instanceof SQLException) {
                    try {
                        if (Objects.equals(((SQLException) e).getSQLState(), "23505")) {
                            sendBytes(writeResponse(operation, false, "Username already taken", null));
                        } else {
                            sendBytes(writeResponse(operation, false, e.getMessage(), null));
                        }
                    } catch (IOException ex) {
//                        System.err.println(ex.getMessage());
                    }
                } else {
                    removeClientHandler();
                }
            }
         }
    }

    public byte[] writeResponse(String operation, boolean status, String message, Map<String, String> data){
        Map<String, Object> response = new HashMap<>();
        response.put("operation", operation);
        response.put("status", status);
        response.put("message", message);

        response.put("data", data);

        Gson gson = new Gson();
        String jsonData = gson.toJson(response);
        return jsonData.getBytes();
    }

    public void sendBytes(byte[] myByteArray) throws IOException {
        sendBytes(myByteArray, 0, myByteArray.length);
    }

    public void sendBytes(byte[] myByteArray, int start, int len) throws IOException {
        if (len < 0)
            throw new IllegalArgumentException("Negative length not allowed");
        if (start < 0 || start >= myByteArray.length)
            throw new IndexOutOfBoundsException("Out of bounds: " + start);
        // Other checks if needed.

        // May be better to save the streams in the support class;
        // just like the socket variable.
        OutputStream out = socket.getOutputStream();
        DataOutputStream dos = new DataOutputStream(out);

        dos.writeInt(len);
        if (len > 0) {
            dos.write(myByteArray, start, len);
        }
    }

    public void removeClientHandler() {
        clientHandlers.remove(this);
        isRunning = false;
    }

    public void closeEverything(Socket socket) {
        try {
            removeClientHandler();
            if (socket != null) {
                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}