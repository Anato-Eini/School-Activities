package com.example.client.Entities;

import java.sql.Timestamp;
import java.util.UUID;

public class EventParticipant implements Comparable<EventParticipant> {

    public enum Status{
        PENDING ((String) "Pending"),
        ACCEPTED ((String) "Accepted"),
        REJECTED ((String) "Rejected");
        public final String value;
        Status(String value) {
            this.value = value;
        }

        public String getValue(){
            return value;
        }
        public static EventParticipant.Status fromValue(String value) {
            for (EventParticipant.Status status: EventParticipant.Status.values()) {
                if (status.value.equalsIgnoreCase(value)) {
                    return status;
                }
            }
            throw new IllegalArgumentException("Unknown status: " + value);
        }
    }
    public UUID id;
    public UUID event_id;
    public UUID user_id;
    public Status status;
    public Timestamp createdAt;
    public Timestamp updatedAt;

    public EventParticipant(UUID id, UUID event_id, UUID user_id, Status status, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.event_id = event_id;
        this.user_id = user_id;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Override
    public int compareTo(EventParticipant o) {
        return o.createdAt.compareTo(this.createdAt);
    }
}