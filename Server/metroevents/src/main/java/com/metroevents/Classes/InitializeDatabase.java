package com.metroevents.Classes;

import java.sql.SQLException;
import java.sql.Statement;

public class InitializeDatabase {

    private static void createUserPrivilegeType(Statement statement) throws SQLException {
        statement.execute("""
                DO $$
                    BEGIN
                        IF NOT EXISTS(SELECT 1 FROM pg_type WHERE typname = 'privilege') THEN
                            CREATE TYPE privilege AS ENUM('user', 'organizer', 'admin');
                        END IF;
                    END $$;
                """);
    }

    private static void createTableUsersQuery(Statement statement) throws SQLException {
        statement.execute(
                """
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
                        """);
    }

    private static void createEventsTableQuery(Statement statement) throws SQLException {
        statement.execute(
                """
                        CREATE TABLE IF NOT EXISTS events(
                            id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                            organizer_id UUID REFERENCES users(id) ON DELETE CASCADE,
                            title VARCHAR NOT NULL,
                            description VARCHAR NOT NULL,
                            venue VARCHAR NOT NULL,
                            image VARCHAR NOT NULL,
                            datetime TIMESTAMP NOT NULL,
                            is_cancelled BOOLEAN DEFAULT FALSE,
                            cancellation_reason VARCHAR,
                            createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                            updatedAt TIMESTAMP
                        )
                        """);
    }

    public static void initializeDatabase(Statement statement) throws SQLException {
        statement.execute("CREATE EXTENSION IF NOT EXISTS \"uuid-ossp\"");
        createUserPrivilegeType(statement);
        createTableUsersQuery(statement);
        createEventsTableQuery(statement);
    }
}