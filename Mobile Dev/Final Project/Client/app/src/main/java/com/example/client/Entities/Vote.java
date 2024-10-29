package com.example.client.Entities;

import java.sql.Timestamp;
import java.util.UUID;

public class Vote {


    public enum VoteType{
        UPVOTE ((String) "Upvote"),
        DOWNVOTE ((String) "DownVote");
        public final String value;
        VoteType(String value) {
            this.value = value;
        }

        public String getValue(){
            return value;
        }
        public static VoteType fromValue(String value) {
            for (VoteType type: VoteType.values()) {
                if (type.value.equalsIgnoreCase(value)) {
                    return type;
                }
            }
            throw new IllegalArgumentException("Unknown status: " + value);
        }
    }

    public UUID id;
    public UUID event_id;
    public UUID user_id;
    public VoteType vote;
    public Timestamp createdAt;
    public Timestamp updatedAt;

    public Vote(UUID id, UUID event_id, UUID user_id, VoteType vote, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.event_id = event_id;
        this.user_id = user_id;
        this.vote = vote;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
