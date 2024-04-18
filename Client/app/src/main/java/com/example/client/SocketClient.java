package com.example.client;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.Socket;
import java.util.Scanner;

public class SocketClient implements Runnable {
    private Socket socket;
    private String username = "Jake";
    private BufferedWriter bufferedWriter;
    private Scanner scanner = new Scanner(System.in);
    OutputStream outputStream;

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

            String data = "Jake";
            byte[] bytes = data.getBytes();
            sendBytes(bytes);
            socket.close();

//            while (socket.isConnected()){
//                String messageToSend = scanner.nextLine();
//                bufferedWriter.write(username + ": " + messageToSend);
//                bufferedWriter.newLine();
//                bufferedWriter.flush();
//            }
        } catch (IOException e) {
            System.out.println("ERROR");
            closeEverything(socket);
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
}
