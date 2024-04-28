import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ClientHandler implements Runnable {
    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private Socket socket;
    private Connection connection;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String clientUsername;

    public ClientHandler(Socket socket, Connection connection) {
        try {
            this.socket = socket;
            this.connection = connection;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             clientHandlers.add(this);
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
            e.printStackTrace();
        }
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
         while (socket.isConnected()) {
            try {
                byte[] dataFromClient = readBytes();

                if(dataFromClient == null) continue;

                messageFromClient = new String(dataFromClient, StandardCharsets.UTF_8);
                Gson gson = new Gson();
                Request request = gson.fromJson(messageFromClient, Request.class);
                HashMap<String, Object> data = request.data;
                switch (request.operation){
                    case "register" -> {
                        String username = (String)data.get("username");
                        String password = (String)data.get("password");
                        String firstName = (String) data.get("firstName");
                        String lastName = (String)data.get("lastName");
                        String query = """
                            INSERT INTO users (username, password, firstName, lastName) VALUES (?, ?, ?, ?)
                        """;
                        PreparedStatement statement = connection.prepareStatement(query);
                        statement.setString(1, username);
                        statement.setString(2, password);
                        statement.setString(3, firstName);
                        statement.setString(4, lastName);
                        int rows = statement.executeUpdate();

                        Map<String, Object> response = new HashMap<>();
                        Map<String, Object> responseData = new HashMap<>();
                        response.put("operation", "register");

                        if(rows > 0){
                            response.put("message", "Registered Successfully");
                        }
                        response.put("data", responseData);
                        gson = new Gson();
                        String jsonData = gson.toJson(response);
                        byte[] bytes = jsonData.getBytes();
                        sendBytes(bytes);
                    }
                }
            } catch (IOException | SQLException e) {
                System.err.println(e.getMessage());
                closeEverything(socket, bufferedReader, bufferedWriter);
               break;
            }
         }
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

//    public void broadcastMessage(String message) {
//        for (ClientHandler clientHandler : clientHandlers) {
//            try {
//                if (!clientHandler.clientUsername.equals(clientUsername)) {
//                    clientHandler.bufferedWriter.write(message);
//                    clientHandler.bufferedWriter.newLine();
//                    clientHandler.bufferedWriter.flush();
//                }
//            } catch (IOException e) {
//                closeEverything(socket, bufferedReader, bufferedWriter);
//                e.printStackTrace();
//            }
//        }
//    }

    public void removeClientHandler() {
        clientHandlers.remove(this);
//        broadcastMessage("SERVER " + clientUsername + " has left the chat!");
    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        try {
            removeClientHandler();
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}