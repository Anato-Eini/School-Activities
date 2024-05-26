package com.example.client.Entities;

import java.sql.Timestamp;
import java.util.UUID;

public class Event implements Comparable<Event> {
    public UUID id;
    public UUID organizer_id;
    public String title;
    public String description;
    public String venue;
    //image link
    public String image;
    public Timestamp datetime;
    public Boolean is_cancelled;
    public String cancellation_reason;
    public Timestamp createdAt;
    public Timestamp updatedAt;

    public Event(UUID id, UUID organizer_id, String title, String description, String venue, String image, Timestamp datetime, Boolean is_cancelled, String cancellation_reason, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.organizer_id = organizer_id;
        this.title = title;
        this.description = description;
        this.venue = venue;
        this.image = image;
        this.datetime = datetime;
        this.is_cancelled = is_cancelled;
        this.cancellation_reason = cancellation_reason;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Override
    public int compareTo(Event o) {
        return o.createdAt.compareTo(this.createdAt);
    }
}
