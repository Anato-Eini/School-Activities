package com.metroevents;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import com.google.gson.Gson;
import com.metroevents.Classes.PasswordHashing;
import com.metroevents.Classes.Request;
import com.metroevents.Classes.User;

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

                if (dataFromClient == null)
                    continue;

                messageFromClient = new String(dataFromClient, StandardCharsets.UTF_8);
                Gson gson = new Gson();
                Request request = gson.fromJson(messageFromClient, Request.class);
                HashMap<String, Object> requestData = request.data;
                operation = request.operation;

                switch (request.operation) {
                    case "register" -> {
                        System.out.println("Register");
                        String username = (String) requestData.get("username");
                        String password = (String) requestData.get("password");
                        password = PasswordHashing.hashPassword(password);
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
                        sendBytes(writeResponse("register", rows > 0,
                                rows > 0 ? "Registered Successfully" : "Register failed", null));
                    }
                    case "login" -> {
                        System.out.println("Login");
                        String username = (String) requestData.get("username");
                        String password = (String) requestData.get("password");
                        password = PasswordHashing.hashPassword(password);
                        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
                        PreparedStatement statement = connection.prepareStatement(query);
                        statement.setString(1, username);
                        statement.setString(2, password);

                        ResultSet resultSet = statement.executeQuery();

                        Map<String, String> loginData = new HashMap<>();
                        if (resultSet.next()) {
                            String id = resultSet.getString("id");
                            username = resultSet.getString(("username"));
                            String firstName = resultSet.getString(("firstName"));
                            String lastName = resultSet.getString(("lastName"));
                            String privilege = resultSet.getString(("privilege"));
                            Timestamp createdAt = resultSet.getTimestamp("createdAt");
                            Timestamp updatedAt = resultSet.getTimestamp("updatedAt");

                            loginData.put("id", id);
                            loginData.put("username", username);
                            loginData.put("firstname", firstName);
                            loginData.put("lastname", lastName);
                            loginData.put("privilege", privilege);
                            loginData.put("createdAt", createdAt.toString());
                            loginData.put("updatedAt", updatedAt != null ? updatedAt.toString() : null);

                            user = new User(id, firstName, lastName, username, createdAt, updatedAt);

                            System.out.println("Login success");
                            sendBytes(writeResponse("login", true, "Login Success", loginData));
                        } else {
                            System.out.println("Login failed");
                            sendBytes(writeResponse("login", false, "Incorrect username or password", null));
                        }
                    }
                    case "createEvent" -> {
                        UUID imageId = UUID.randomUUID();
                        String mimeType = (String) requestData.get("mimeType");
                        byte[] image = Base64.getDecoder().decode(((String) requestData.get("image")).getBytes());
                        String imageURL = "https://zpssgimvgftmwinnflay.supabase.co/storage/v1/object/event_images/"
                                + imageId.toString() + "." + mimeType.split("/")[1];
                        String apiKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Inpwc3NnaW12Z2Z0bXdpbm5mbGF5Iiwicm9sZSI6InNlcnZpY2Vfcm9sZSIsImlhdCI6MTcxNDU0MDc4NCwiZXhwIjoyMDMwMTE2Nzg0fQ.OtW0FtVOjSUStdRv0WB4JOyjZpdlznPPw30ov5e-GGc";

                        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(imageURL).openConnection();

                        httpURLConnection.setRequestMethod("POST");
                        httpURLConnection.setDoOutput(true);
                        httpURLConnection.setRequestProperty("Authorization", "Bearer " + apiKey);
                        httpURLConnection.setRequestProperty("Content-Type", mimeType);
                        try (OutputStream outputStream = httpURLConnection.getOutputStream()) {
                            outputStream.write(image);
                        }

                        StringBuilder sb = new StringBuilder();
                        int responseCode = httpURLConnection.getResponseCode();
                        if (responseCode == HttpURLConnection.HTTP_OK) {
                            System.out.println("File uploaded successfully.");
                            sb.append(imageURL.substring(0, 59));
                            sb.append("public/");
                            sb.append(imageURL.substring(59, imageURL.length()));
                            System.out.println(sb.toString());
                        } else {
                            System.err.println("Failed to upload file. Response code: " + responseCode);
                            System.out.println(httpURLConnection.getResponseMessage());
                        }

                        String title = (String) requestData.get("title");
                        String description = (String) requestData.get("description");
                        String venue = (String) requestData.get("venue");
                        Timestamp datetime = (Timestamp) requestData.get("datetime");

                        String query = """
                                INSERT INTO users (organizer_id, title, description, venue, image, datetime) VALUES (?, ?, ?, ?, ?, ?) RETURNING *;
                                """;
                        PreparedStatement statement = connection.prepareStatement(query);
                        statement.setString(1, user.id);
                        statement.setString(2, title);
                        statement.setString(3, description);
                        statement.setString(4, venue);
                        statement.setString(5, sb.toString());
                        statement.setTimestamp(6, datetime);

                        ResultSet resultSet = statement.executeQuery();
                        while (resultSet.next()) {
                            System.out.println(resultSet.getString(0));
                        }

                        // sendBytes(writeResponse("register", rows > 0,
                        // rows > 0 ? "Registered Successfully" : "Register failed", null));
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
                        // System.err.println(ex.getMessage());
                    }
                } else {
                    removeClientHandler();
                }
            }
        }
    }

    public byte[] writeResponse(String operation, boolean status, String message, Map<String, String> data) {
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