package com.example.client;

import android.os.AsyncTask;
import android.os.Handler;
import android.text.Editable;

import com.example.client.Classes.Response;
import com.google.gson.Gson;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class SocketClient implements Runnable {
    private static Socket socket;
    Gson gson;
    ResponseListener responseListener;

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
            socket = new Socket("links-warrant.gl.at.ply.gg", 23696);

            String messageFromServer;
            while (socket.isConnected()){
                byte[] dataFromClient = readBytes();

                if(dataFromClient == null) continue;

                messageFromServer = new String(dataFromClient, StandardCharsets.UTF_8);
                Gson gson = new Gson();
                Response response = gson.fromJson(messageFromServer, Response.class);

                switch (response.operation){
                    case "register":
                        responseListener.onSuccess(response.message);
                        break;
                }
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
        try {
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
            sendBytes(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void registerAsync(String username, String password, String firstName, String lastName) {
        new AsyncTask<String, Void, Void>() {
            @Override
            protected Void doInBackground(String... params) {
                String username = params[0];
                String password = params[1];
                String firstName = params[2];
                String lastName = params[3];

                register(username, password, firstName, lastName);

                return null;
            }
        }.execute(username, password, firstName, lastName);
    }

    public interface ResponseListener{
        void onSuccess(String message);
    }
}