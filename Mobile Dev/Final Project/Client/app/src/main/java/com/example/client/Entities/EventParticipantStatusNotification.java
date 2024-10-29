package com.example.client.Entities;

import java.sql.Timestamp;
import java.util.UUID;

public class EventParticipantStatusNotification implements Comparable<EventParticipantStatusNotification> {
    public UUID id;
    public UUID event_id;
    public UUID user_id;
    public String notification;
    public Timestamp createdAt;
    public Timestamp updatedAt;

    public EventParticipantStatusNotification(UUID id, UUID event_id, UUID user_id, String notification, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.event_id = event_id;
        this.user_id = user_id;
        this.notification = notification;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Override
    public int compareTo(EventParticipantStatusNotification o) {
        return o.createdAt.compareTo(this.createdAt);
    }
}