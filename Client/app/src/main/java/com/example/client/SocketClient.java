package com.example.client;


import androidx.datastore.preferences.core.Preferences;
import androidx.datastore.rxjava2.RxDataStore;

import com.example.client.Classes.Response;
import com.example.client.Entities.Event;
import com.example.client.Entities.User;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class SocketClient implements Runnable {
    private static Socket socket;
    Gson gson;
    ResponseListener responseListener;
    MetroEvents metroEvents;

    public SocketClient(MetroEvents metroEvents){
        this.metroEvents = metroEvents;
    }

    public ResponseListener getResponseListener() {
        return responseListener;
    }

    public void setResponseListener(ResponseListener responseListener) {
        this.responseListener = responseListener;
    }

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

            String messageFromServer;
            while (socket.isConnected()) {
                byte[] dataFromClient = readBytes();

                if (dataFromClient == null) continue;

                messageFromServer = new String(dataFromClient, StandardCharsets.UTF_8);
                gson = new Gson();
                Response response = gson.fromJson(messageFromServer, Response.class);
//                System.out.println(response.message);
//                System.out.println(response.data);
//                System.out.println(response.status);

                switch (response.operation) {
                    case "login" -> {
                        if(!response.status)break;
                        String id = (String) response.data.get("id");
                        String firstName = (String) response.data.get("firstname");
                        String lastName =  (String)response.data.get("lastname");
                        String username = (String) response.data.get("username");
                        User.Privilege privilege =  User.Privilege.fromValue((String) response.data.get("privilege"));
                        Timestamp createdAt = Timestamp.valueOf((String)response.data.get("createdAt"));
                        Timestamp updatedAt = response.data.get("updatedAt") != null ? Timestamp.valueOf((String)response.data.get("updatedAt")) : null;
                        User user = new User(id, firstName, lastName, username, privilege, createdAt, updatedAt);
                        metroEvents.saveUser(user);
                    }
                    case "createEvent" -> {
                        System.out.println(response.data);
                    }
                    case "getEvents" -> {
                        HashMap<UUID, Event> events = new HashMap<>();
                        for (Map.Entry<String, Object> entry : response.data.entrySet()) {
                            LinkedTreeMap<String, Object> eventData = (LinkedTreeMap<String, Object>) entry.getValue();

                            UUID event_id = UUID.fromString((String) eventData.get("id"));
                            UUID organizer_id = UUID.fromString((String) eventData.get("organizer_id"));
                            String title = (String) eventData.get("title");
                            String description = (String) eventData.get("description");
                            String venue = (String) eventData.get("venue");
                            String image = (String) eventData.get("image");
                            Timestamp datetime = Timestamp.valueOf((String)eventData.get("datetime"));
                            Boolean is_cancelled = !Objects.equals(eventData.get("is_cancelled"), "f");
                            String cancellation_reason = eventData.get("cancellation_reason") != null ? (String) eventData.get("cancellation_reason") : null;
                            Timestamp createdAt = Timestamp.valueOf((String)eventData.get("createdat"));
                            Timestamp updatedAt = eventData.get("updatedat") != null ? Timestamp.valueOf((String)eventData.get("updatedAt")) : null;
                            Event event = new Event(event_id, organizer_id, title, description, venue, image, datetime, is_cancelled, cancellation_reason, createdAt, updatedAt);
                            events.put(UUID.fromString((String) eventData.get("id")), event);
                        }
                        metroEvents.saveEvents(events);
                    }
                }
                responseListener.onSuccess(response);
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

            Map<String, Object> credentials = new HashMap<>();
            credentials.put("username", username);
            credentials.put("password", password);
            credentials.put("firstName", firstName);
            credentials.put("lastName", lastName);

            try {
                sendBytes(writeRequest("register", credentials));
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

    public static void createEventAsync(String title, String description, String venue, byte[] image, LocalDateTime dateTime) {
        new Thread(() -> {
            try {
                Map<String, Object> eventData = new HashMap<>();
                eventData.put("title", title);
                eventData.put("description", description);
                eventData.put("venue", venue);
                String s = Base64.getEncoder().encodeToString(image);
                eventData.put("image", s);
                eventData.put("mimeType", URLConnection.guessContentTypeFromStream(new ByteArrayInputStream(image)));
                eventData.put("datetime", dateTime.toString());
                sendBytes(writeRequest("createEvent", eventData));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    public static void getEvents(){
        new Thread(() -> {
            try {
                sendBytes(writeRequest("getEvents", null));
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

    public interface ResponseListener {
        void onSuccess(Response response);
    }
}