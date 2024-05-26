package com.example.client;


import com.example.client.Classes.AuthHandler;
import com.example.client.Classes.CommentHandler;
import com.example.client.Classes.Response;
import com.example.client.Classes.VoteHandler;
import com.example.client.Entities.Comment;
import com.example.client.Entities.Event;
import com.example.client.Entities.EventParticipant;
import com.example.client.Entities.User;
import com.example.client.Entities.Vote;
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
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class SocketClient implements Runnable {
    private static Socket socket;
    Gson gson;
    ResponseListener responseListener;
    static MetroEvents metroEvents;

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
//            socket = new Socket("192.168.254.104", 23696);
           // socket = new Socket("pc-knives.gl.at.ply.gg", 42783);
            socket = new Socket("192.168.1.10", 42783);


            String messageFromServer;
            while (socket.isConnected()) {
                byte[] dataFromClient = readBytes();

                if (dataFromClient == null) continue;

                messageFromServer = new String(dataFromClient, StandardCharsets.UTF_8);
                gson = new Gson();
                Response response = gson.fromJson(messageFromServer, Response.class);
//                System.out.println(response.message);
//                System.out.println(response.operation);
//                System.out.println(response.data);
//                System.out.println(response.status);

                switch (response.operation) {
                    case "login" -> {
                        if(response.status){
                            String id = (String) response.data.get("id");
                            String firstName = (String) response.data.get("firstname");
                            String lastName =  (String)response.data.get("lastname");
                            String username = (String) response.data.get("username");
                            User.Privilege privilege =  User.Privilege.fromValue((String) response.data.get("privilege"));
                            Timestamp createdAt = Timestamp.valueOf((String)response.data.get("createdAt"));
                            Timestamp updatedAt = response.data.get("updatedAt") != null ? Timestamp.valueOf((String)response.data.get("updatedAt")) : null;
                            User user = new User(UUID.fromString(id), firstName, lastName, username, privilege, createdAt, updatedAt);
                            metroEvents.authHandler.notifyOnLogin(true, user);
                        }else{
                            metroEvents.authHandler.notifyOnLogin(false, null);
                        }
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
                    case "getJoinedEvents" -> {
                        HashMap<UUID, EventParticipant> participatedEvents = new HashMap<>();

                        for (Map.Entry<String, Object> entry : response.data.entrySet()) {
                            LinkedTreeMap<String, Object> eventData = (LinkedTreeMap<String, Object>) entry.getValue();
                            UUID id = UUID.fromString((String) eventData.get("id"));
                            UUID event_id = UUID.fromString((String) eventData.get("event_id"));
                            UUID user_id = UUID.fromString((String) eventData.get("user_id"));
                            EventParticipant.Status status = EventParticipant.Status.fromValue((String) eventData.get("status"));
                            Timestamp createdAt = Timestamp.valueOf((String)eventData.get("createdat"));
                            Timestamp updatedAt = eventData.get("updatedat") != null ? Timestamp.valueOf((String)eventData.get("updatedAt")) : null;
                            EventParticipant eventParticipant = new EventParticipant(id, event_id, user_id, status, createdAt, updatedAt);
                            participatedEvents.put(event_id, eventParticipant);
                        }
                        metroEvents.saveParticipatedEvents(participatedEvents);
                    }
                    case "getComments" -> {
                        if(response.status){
                            HashMap<UUID, Comment> comments = new HashMap<>();
                            for (Map.Entry<String, Object> entry : response.data.entrySet()) {
                                LinkedTreeMap<String, Object> commentData = (LinkedTreeMap<String, Object>) entry.getValue();
                                UUID id = UUID.fromString((String) commentData.get("id"));
                                UUID event_id = UUID.fromString((String) commentData.get("event_id"));
                                UUID user_id = UUID.fromString((String) commentData.get("user_id"));
                                String comment = (String) commentData.get("comment");
                                Timestamp createdAt = Timestamp.valueOf((String)commentData.get("createdat"));
                                Timestamp updatedAt = commentData.get("updatedat") != null ? Timestamp.valueOf((String)commentData.get("updatedAt")) : null;
                                Comment eventComment = new Comment(id, event_id, user_id, comment, createdAt, updatedAt);
                                comments.put(id, eventComment);
                            }
                            metroEvents.commentHandler.notifyCommentsFetched(true, comments);
                        }else{
                            metroEvents.commentHandler.notifyCommentsFetched(false, null);
                        }
                    }

                    case "createComment" -> {
                        if (response.status) {
                            for (Map.Entry<String, Object> entry : response.data.entrySet()) {
                                LinkedTreeMap<String, Object> commentData = (LinkedTreeMap<String, Object>) entry.getValue();
                                UUID id = UUID.fromString((String) commentData.get("id"));
                                UUID event_id = UUID.fromString((String) commentData.get("event_id"));
                                UUID user_id = UUID.fromString((String) commentData.get("user_id"));
                                String commentText = (String) commentData.get("comment");
                                Timestamp createdAt = Timestamp.valueOf((String) commentData.get("createdat"));
                                Timestamp updatedAt = response.data.get("updatedat") != null ? Timestamp.valueOf((String) commentData.get("updatedat")) : null;
                                Comment newComment = new Comment(id, event_id, user_id, commentText, createdAt, updatedAt);
                                metroEvents.commentHandler.notifyCommentCreated(true, newComment);
                            }
                        } else {
                            metroEvents.commentHandler.notifyCommentCreated(false, null);
                        }
                    }

                    case "getVotes" -> {
                        if(response.status){
                            HashMap<UUID, Vote>  votes = new HashMap<>();
                            for (Map.Entry<String, Object> entry : response.data.entrySet()) {
                                LinkedTreeMap<String, Object> voteData = (LinkedTreeMap<String, Object>) entry.getValue();
                                UUID id = UUID.fromString((String) voteData.get("id"));
                                UUID event_id = UUID.fromString((String) voteData.get("event_id"));
                                UUID user_id = UUID.fromString((String) voteData.get("user_id"));
                                Vote.VoteType voteType = Vote.VoteType.fromValue((String) voteData.get("vote"));
                                Timestamp createdAt = Timestamp.valueOf((String) voteData.get("createdat"));
                                Timestamp updatedAt = voteData.get("updatedat") != null ? Timestamp.valueOf((String) voteData.get("updatedAt")) : null;
                                Vote eventVote = new Vote(id, event_id, user_id, voteType, createdAt, updatedAt);
                                votes.put(id, eventVote);
                            }
                            metroEvents.voteHandler.notifyVotesFetched(true, votes);
                        }else{
                            metroEvents.voteHandler.notifyVotesFetched(false, null);
                        }
                    }

                    case "createVote" -> {
                        if (response.status) {
                            for (Map.Entry<String, Object> entry : response.data.entrySet()) {
                                LinkedTreeMap<String, Object> voteData = (LinkedTreeMap<String, Object>) entry.getValue();
                                UUID id = UUID.fromString((String) voteData.get("id"));
                                UUID event_id = UUID.fromString((String) voteData.get("event_id"));
                                UUID user_id = UUID.fromString((String) voteData.get("user_id"));
                                Vote.VoteType voteType = Vote.VoteType.fromValue((String) voteData.get("vote"));
                                Timestamp createdAt = Timestamp.valueOf((String) voteData.get("createdat"));
                                Timestamp updatedAt = voteData.get("updatedat") != null ? Timestamp.valueOf((String) voteData.get("updatedAt")) : null;
                                Vote eventVote = new Vote(id, event_id, user_id, voteType, createdAt, updatedAt);
                                metroEvents.voteHandler.notifyVoteCreated(true, eventVote);
                            }
                        } else {
                            metroEvents.voteHandler.notifyVoteCreated(false, null);
                        }
                    }

                    case "deleteVote" -> {
                        if (response.status) {
                            for (Map.Entry<String, Object> entry : response.data.entrySet()) {
                                LinkedTreeMap<String, Object> voteData = (LinkedTreeMap<String, Object>) entry.getValue();
                                UUID id = UUID.fromString((String) voteData.get("id"));
                                UUID event_id = UUID.fromString((String) voteData.get("event_id"));
                                UUID user_id = UUID.fromString((String) voteData.get("user_id"));
                                Vote.VoteType voteType = Vote.VoteType.fromValue((String) voteData.get("vote"));
                                Timestamp createdAt = Timestamp.valueOf((String) voteData.get("createdat"));
                                Timestamp updatedAt = voteData.get("updatedat") != null ? Timestamp.valueOf((String) voteData.get("updatedAt")) : null;
                                Vote eventVote = new Vote(id, event_id, user_id, voteType, createdAt, updatedAt);
                                metroEvents.voteHandler.notifyVoteDeleted(true, eventVote);
                            }
                        } else {
                            metroEvents.voteHandler.notifyVoteDeleted(false, null);
                        }
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

    public static void register(String username, String password, String firstName, String lastName) {
        new Thread(() -> {

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


    public static void login(String username, String password, AuthHandler.LoginCallback callback) {
        new Thread(() -> {
            try {
                metroEvents.authHandler.setNotifyLogin(callback);
                Map<String, Object> credentials = new HashMap<>();
                credentials.put("username", username);
                credentials.put("password", password);

                sendBytes(writeRequest("login", credentials));
            } catch (IOException e) {
                callback.onLogin(false, null);
                e.printStackTrace();
            }
        }).start();
    }

    public static void createEvent(String title, String description, String venue, byte[] image, LocalDateTime dateTime) {
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

    public static void joinEvent(UUID event_id){
        new Thread(() -> {
            try {
                Map<String, Object> data = new HashMap<>();

                data.put("event_id", event_id);

                sendBytes(writeRequest("joinEvent", data));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    public static void getJoinedEvents(){
        new Thread(() -> {
            try {
                sendBytes(writeRequest("getJoinedEvents", null));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }


    public static void getComments(CommentHandler.GetCommentsCallback callback){
        new Thread(() -> {
            metroEvents.commentHandler.setNotifyFetchedComments(callback);
            try {
                sendBytes(writeRequest("getComments", null));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    public static void createComment(String comment, UUID event_id, CommentHandler.CreateCommentCallback callback) {
        new Thread(() -> {
            metroEvents.commentHandler.setNotifyCreateComment(callback);
            try {
                Map<String, Object> data = new HashMap<>();
                data.put("comment", comment);
                data.put("event_id", event_id.toString());

                sendBytes(writeRequest("createComment", data));
            } catch (IOException e) {
                callback.onCommentCreated(false, null);
                throw new RuntimeException(e);
            }
        }).start();
    }

    public static void getVotes(VoteHandler.GetVotesCallback callback){
        new Thread(() -> {
            metroEvents.voteHandler.setNotifyFetchedVotes(callback);
            try {
                sendBytes(writeRequest("getVotes", null));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }


    public static void createVote(Vote.VoteType voteType, UUID event_id, VoteHandler.CreateVoteCallback callback) {
        new Thread(() -> {
            metroEvents.voteHandler.setNotifyCreateVote(callback);
            try {
                Map<String, Object> data = new HashMap<>();
                data.put("voteType", voteType);
                data.put("event_id", event_id);

                sendBytes(writeRequest("createVote", data));
            } catch (IOException e) {
                callback.onVoteCreated(false, null);
                throw new RuntimeException(e);
            }
        }).start();
    }


    public static void deleteVote(UUID event_id, VoteHandler.DeleteVoteCallback callback) {
        new Thread(() -> {
            metroEvents.voteHandler.setNotifyDeleteVote(callback);
            try {
                Map<String, Object> data = new HashMap<>();
                data.put("event_id", event_id);

                sendBytes(writeRequest("deleteVote", data));
            } catch (IOException e) {
                callback.onVoteDeleted(false, null);
                throw new RuntimeException(e);
            }
        }).start();
    }


    public static void restoreSession(User user){

        new Thread(() -> {
            try {
                Map<String, Object> userInfo = new HashMap<>();

                userInfo.put("id", user.id);
                userInfo.put("firstName", user.firstName);
                userInfo.put("lastName", user.lastName);
                userInfo.put("userName", user.username);
                userInfo.put("createdAt", String.valueOf(user.createdAt));
                userInfo.put("updatedAt", String.valueOf(user.updatedAt));

                sendBytes(writeRequest("restoreSession", userInfo));
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