package com.example.client.Classes;

import com.example.client.Entities.Comment;

import java.util.HashMap;
import java.util.UUID;

public class CommentHandler {
    CreateCommentCallback createCommentCallback;
    GetCommentsCallback getCommentsCallback;

    public interface CreateCommentCallback{
        void onCommentCreated(boolean success, Comment comment);
    }

    public void setNotifyCreateComment(CreateCommentCallback callback) {
        this.createCommentCallback = callback;
    }

    public void notifyCommentCreated(boolean success, Comment comment) {
        if (createCommentCallback != null) {
            createCommentCallback.onCommentCreated(success, comment);
        }
    }



    public interface GetCommentsCallback{
        void onCommentsFetched(boolean success, HashMap<UUID, Comment> comments);
    }

    public void setNotifyFetchedComments(GetCommentsCallback callback) {
        this.getCommentsCallback = callback;
    }

    public void notifyCommentsFetched(boolean success, HashMap<UUID, Comment> comments){
        if(getCommentsCallback != null){
            getCommentsCallback.onCommentsFetched(success, comments);
        }
    }


}
