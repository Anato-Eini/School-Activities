package com.metroevents;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.metroevents.Classes.InitializeDatabase;

public class Server {
    private final ServerSocket serverSocket;
    private final Connection connection;
    public static String CONNECTION_URI = "jdbc:postgresql://localhost:5432/CSIT284";

    // private static final String CONNECTION_URI =
    // "jdbc:postgresql://aws-0-ap-southeast-1.pooler.supabase.com:5432/postgres";
    // private static final String USER = "postgres.zpssgimvgftmwinnflay";
    // private static final String PASSWORD = "D8RBWtx1nY5yP0FY";

    private static final String USER = "postgres";
    private static final String PASSWORD = "root";

    public Server(ServerSocket serverSocket) throws SQLException {
        this.serverSocket = serverSocket;
        connection = DriverManager.getConnection(CONNECTION_URI, USER, PASSWORD);
        Statement statement = connection.createStatement();
        InitializeDatabase.initializeDatabase(statement);
        System.out.println("Connected to database");
    }

    public void startServer() {
        try {
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                System.out.println("A new client has connected");
                ClientHandler clientHandler = new ClientHandler(socket, this.connection);
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (IOException e) {

        }
    }

    public void closeServerSocket() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, SQLException {
        System.out.println("Starting server");
        ServerSocket serverSocket = new ServerSocket(23696);
        // ServerSocket serverSocket = new ServerSocket(5050);
        Server server = new Server(serverSocket);
        server.startServer();
    }
}