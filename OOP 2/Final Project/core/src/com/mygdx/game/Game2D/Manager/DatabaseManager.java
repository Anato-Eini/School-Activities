package com.mygdx.game.Game2D.Manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class DatabaseManager {
    public static DatabaseManager instance;
    private static final String CONNECTION_URI = "jdbc:postgresql://aws-0-ap-southeast-1.pooler.supabase.com:5432/postgres";
    /**
     * Preferably store in environment variables
     */
    private static final String USER = "postgres.sypotpzlpxujgwunkqzz";
    private static final String PASSWORD = "GkARwX7w2XtDOJfp";

    public static DatabaseManager getInstance(){
        return Objects.requireNonNullElseGet(instance, DatabaseManager::new);
    }

    public Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(CONNECTION_URI, USER, PASSWORD);
        Statement statement = connection.createStatement();
        initializeDatabase(statement);
        return connection;
    }

    public void initializeDatabase(Statement statement) throws SQLException {
        statement.execute("CREATE EXTENSION IF NOT EXISTS \"uuid-ossp\"");
        statement.execute("""
            CREATE TABLE IF NOT EXISTS users(
                id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                username VARCHAR NOT NULL UNIQUE,
                password VARCHAR NOT NULL,
                created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
                updated_at TIMESTAMPTZ
            )
        """);
        statement.execute("""
            CREATE TABLE IF NOT EXISTS profiles(
                id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                user_id UUID REFERENCES users(id) ON DELETE CASCADE,
                username VARCHAR NOT NULL,
                x FLOAT4 NOT NULL,
                y FLOAT4 NOT NULL,
                direction VARCHAR NOT NULL,
                map VARCHAR NOT NULL,
                created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
                updated_at TIMESTAMPTZ
            )
            """);
    }
}
