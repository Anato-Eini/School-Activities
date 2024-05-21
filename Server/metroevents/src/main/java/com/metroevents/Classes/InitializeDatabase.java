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

    private static void createParticipantStatus(Statement statement) throws SQLException {
        statement.execute("""
                DO $$
                    BEGIN
                        IF NOT EXISTS(SELECT 1 FROM pg_type WHERE typname = 'participant_status') THEN
                            CREATE TYPE participant_status AS ENUM('pending', 'accepted', 'rejected');
                        END IF;
                    END $$;
                """);
    }

    private static void createEventParticipants(Statement statement) throws SQLException {
        statement.execute(
                """
                                CREATE TABLE IF NOT EXISTS event_participants(
                                id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                                event_id UUID REFERENCES events(id) ON DELETE CASCADE,
                                user_id UUID REFERENCES users(id) ON DELETE CASCADE,
                                status participant_status DEFAULT 'pending',
                                createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                updatedAt TIMESTAMP,
                                CONSTRAINT unique_event_user_combination UNIQUE (event_id, user_id)
                                )
                        """);
    }

    private static void createEventComments(Statement statement) throws SQLException {
        statement.execute(
                """
                                CREATE TABLE IF NOT EXISTS event_comments(
                                id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                                event_id UUID REFERENCES events(id) ON DELETE CASCADE,
                                user_id UUID REFERENCES users(id) ON DELETE CASCADE,
                                comment VARCHAR,
                                createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                updatedAt TIMESTAMP
                                )
                        """);
    }

    private static void createVoteType(Statement statement) throws SQLException {
        statement.execute(
                """
                        DO $$
                             BEGIN
                                IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'vote') THEN
                                    CREATE TYPE vote AS ENUM ('upvote', 'downvote');
                                END IF;
                            END $$;
                            """);
    }

    private static void createEventVotes(Statement statement) throws SQLException {
        statement.execute(
                """
                                CREATE TABLE IF NOT EXISTS event_votes(
                                    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                                    event_id UUID NOT NULL,
                                    user_id UUID NOT NULL,
                                    vote vote NOT NULL,
                                    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                    updatedAt TIMESTAMP,
                                    CONSTRAINT unique_user_vote UNIQUE (event_id, user_id),
                                    FOREIGN KEY (event_id) REFERENCES events(id) ON DELETE CASCADE,
                                    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
                                )
                        """);
    }

    public static void initializeDatabase(Statement statement) throws SQLException {
        statement.execute("CREATE EXTENSION IF NOT EXISTS \"uuid-ossp\"");
        createUserPrivilegeType(statement);
        createTableUsersQuery(statement);
        createEventsTableQuery(statement);
        createParticipantStatus(statement);
        createEventParticipants(statement);
        createEventComments(statement);
        createVoteType(statement);
        createEventVotes(statement);
    }
}