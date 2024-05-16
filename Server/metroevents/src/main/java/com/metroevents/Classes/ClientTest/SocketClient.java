package com.metroevents.Classes.ClientTest;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;

public class SocketClient implements Runnable {
    private static Socket socket;
    Gson gson;

    public void closeEverything(Socket socket) {
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            System.out.println("Connecting to server");
            socket = new Socket("192.168.254.104", 23696);

            // register("asd", "asd", "asd", "asd");
            loginAsync("zxcv", "zxcv");

            String messageFromServer;
            while (socket.isConnected()) {
                byte[] dataFromClient = readBytes();

                if (dataFromClient == null)
                    continue;

                messageFromServer = new String(dataFromClient, StandardCharsets.UTF_8);
                gson = new Gson();
                Response response = gson.fromJson(messageFromServer, Response.class);
                System.out.println(response.message);
                System.out.println(response.data);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
            closeEverything(socket);
        }
    }

    public static void sendBytes(byte[] myByteArray) throws IOException {
        sendBytes(myByteArray, 0, myByteArray.length);
    }

    public static void sendBytes(byte[] myByteArray, int start, int len) throws IOException {
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

    public static void registerAsync(String username, String password, String firstName, String lastName) {
        new Thread(() -> {
            Gson gson = new Gson();

            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("operation", "register");

            Map<String, String> innerDataMap = new HashMap<>();
            innerDataMap.put("username", username);
            innerDataMap.put("password", password);
            innerDataMap.put("firstName", firstName);
            innerDataMap.put("lastName", lastName);

            dataMap.put("data", innerDataMap);

            String jsonData = gson.toJson(dataMap);

            byte[] bytes = jsonData.getBytes();
            try {
                sendBytes(bytes);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    public static void loginAsync(String username, String password) {
        new Thread(() -> {
            try {
                Map<String, Object> credentials = new HashMap<>();
                credentials.put("username", username);
                credentials.put("password", password);

                sendBytes(writeRequest("login", credentials));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void createEventAsync(String title, String description, String venue, byte[] image,
            LocalDateTime dateTime) {
        new Thread(() -> {
            try {
                Map<String, Object> eventData = new HashMap<>();
                eventData.put("title", title);
                eventData.put("description", description);
                eventData.put("venue", venue);
                String s = Base64.getEncoder().encodeToString(image);
                eventData.put("image", s);
                eventData.put("mimeType", URLConnection.guessContentTypeFromStream(new ByteArrayInputStream(image)));
                eventData.put("datetime", dateTime);
                sendBytes(writeRequest("createEvent", eventData));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    public static byte[] writeRequest(String operation, Map<String, Object> data) {
        Gson gson = new Gson();
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("operation", operation);
        dataMap.put("data", data);
        String jsonData = gson.toJson(dataMap);
        return jsonData.getBytes();
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new SocketClient());
        thread.start();
    }
}
