package com.example.client.Entities;

import java.sql.Timestamp;
import java.util.UUID;

public class Comment {
//    CREATE TABLE IF NOT EXISTS event_comments(
//    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
//    event_id UUID REFERENCES events(id) ON DELETE CASCADE,
//    user_id UUID REFERENCES users(id) ON DELETE CASCADE,
//    comment VARCHAR,
//    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
//    updatedAt TIMESTAMP,

    public UUID id;
    public UUID event_id;
    public UUID user_id;
    public String comment;
    public Timestamp createdAt;
    public Timestamp updatedAt;

    public Comment(UUID id, UUID event_id, UUID user_id, String comment, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.event_id = event_id;
        this.user_id = user_id;
        this.comment = comment;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
