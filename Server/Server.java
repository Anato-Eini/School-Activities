import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
public class Server {
    private ServerSocket serverSocket;
    Connection connection;
    public static String URL = "jdbc:postgresql://localhost:5432/CSIT284";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "root";
    private String createUserPrivilegeType = """
            DO $$
                BEGIN
                    IF NOT EXISTS(SELECT 1 FROM pg_type WHERE typname = 'privilege') THEN
                        CREATE TYPE privilege AS ENUM('user', 'organizer', 'admin');
                    END IF;
                END $$;
            """;
    private String createUsersTableQuery = """
            CREATE TABLE IF NOT EXISTS users(
                id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                firstname VARCHAR NOT NULL,
                lastname VARCHAR NOT NULL,
                username VARCHAR NOT NULL UNIQUE,
                password VARCHAR NOT NULL,
                privilege privilege NOT NULL DEFAULT 'user',
                createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                updatedAt TIMESTAMP
            )
            """;

    public Server(ServerSocket serverSocket) throws SQLException {
        this.serverSocket = serverSocket;
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        Statement statement = connection.createStatement();
        statement.execute("CREATE EXTENSION IF NOT EXISTS \"uuid-ossp\"");
        statement.execute(createUserPrivilegeType);
        statement.execute(createUsersTableQuery);
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
        Server server = new Server(serverSocket);
        server.startServer();
    }
}