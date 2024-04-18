import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {

    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();

    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String clientUsername;

    public ClientHandler(Socket socket) {
        try {
            this.socket = socket;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            byte[] dataFromClient = readBytes();
            String messageFromClient = new String(dataFromClient, "UTF-8");
            // System.out.println("Received message from client: " + messageFromClient);
            this.clientUsername = messageFromClient;
            System.out.println(clientUsername);
            // clientHandlers.add(this);
            // broadcastMessage("SERVER: " + clientUsername + " has entered the chat!");
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
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
        // String messageFromClient;
        // while (socket.isConnected()) {
        // try {
        // messageFromClient = bufferedReader.readLine();
        // broadcastMessage(messageFromClient);
        // } catch (IOException e) {
        // closeEverything(socket, bufferedReader, bufferedWriter);
        // break;
        // }
        // }
    }

    public void broadcastMessage(String message) {
        for (ClientHandler clientHandler : clientHandlers) {
            try {
                if (!clientHandler.clientUsername.equals(clientUsername)) {
                    clientHandler.bufferedWriter.write(message);

                    clientHandler.bufferedWriter.newLine();

                    clientHandler.bufferedWriter.flush();

                }
            } catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
                e.printStackTrace();
            }
        }
    }

    public void removeClientHandler() {
        clientHandlers.remove(this);
        broadcastMessage("SERVER " + clientUsername + " has left the chat!");
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