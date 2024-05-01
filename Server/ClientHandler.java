import Classes.Request;
import Classes.User;
import com.google.gson.Gson;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.*;

public class ClientHandler implements Runnable {
    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private volatile boolean isRunning = true;
    private final Socket socket;
    private final Connection connection;
    private User user;
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
//                System.out.println(messageFromClient);
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
                            String id = resultSet.getString("id");
                            username = resultSet.getString(("username"));
                            String firstName = resultSet.getString(("firstName"));
                            String lastName = resultSet.getString(("lastName"));
                            String privilege = resultSet.getString(("privilege"));
                            String createdAt = resultSet.getString(("createdAt"));
                            String updatedAt = resultSet.getString(("updatedAt"));

                            loginData.put("id", id);
                            loginData.put("username", username);
                            loginData.put("firstname", firstName);
                            loginData.put("lastname", lastName);
                            loginData.put("privilege", privilege);
                            loginData.put("createdAt", createdAt);
                            loginData.put("updatedAt", updatedAt);

                            user = new User();
                            sendBytes(writeResponse("login", true, "Login Success", loginData));
                        }else{
                            sendBytes(writeResponse("login", false, "Incorrect username or password", null));
                        }
                    }
                    case "createEvent" -> {
                        String supabaseUrl = "https://rafhblqrgvjzlxhigvlt.supabase.co/storage/v1/object/post-images/test1.jpg";
                        String apiKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InJhZmhibHFyZ3Zqemx4aGlndmx0Iiwicm9sZSI6InNlcnZpY2Vfcm9sZSIsImlhdCI6MTcwOTYxMzI5NiwiZXhwIjoyMDI1MTg5Mjk2fQ.hdnYG3UDwEolp6-mspMJHC3TevtNy09lnTE9dUmhjJw";

                        HttpURLConnection connection = (HttpURLConnection) new URL(supabaseUrl).openConnection();
                        connection.setRequestMethod("POST");
                        connection.setDoOutput(true);

                        connection.setRequestProperty("Authorization", "Bearer " + apiKey);
                        connection.setRequestProperty("Content-Type", (String) requestData.get("mimeType"));
                        byte[] imageData = Base64.getDecoder().decode(((String)requestData.get("image")).getBytes());

                        try (OutputStream outputStream = connection.getOutputStream()) {
                            outputStream.write(imageData);
                        }

                        int responseCode = connection.getResponseCode();
                        if (responseCode == HttpURLConnection.HTTP_OK) {
                            System.out.println("File uploaded successfully.");
                        } else {
                            System.err.println("Failed to upload file. Response code: " + responseCode);
                        }
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

    private static String guessMimeType(byte[] data) throws IOException {
        return URLConnection.guessContentTypeFromStream(new ByteArrayInputStream(data));
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