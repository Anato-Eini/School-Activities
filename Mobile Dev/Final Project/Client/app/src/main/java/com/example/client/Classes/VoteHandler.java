package com.example.client.Classes;


import com.example.client.Entities.Vote;

import java.util.HashMap;
import java.util.UUID;

public class VoteHandler {

    GetVotesCallback getVotesCallback;
    CreateVoteCallback createVoteCallback;
    DeleteVoteCallback deleteVoteCallback;

    public interface CreateVoteCallback{
        void onVoteCreated(boolean success, Vote vote);
    }

    public void setNotifyCreateVote(CreateVoteCallback callback) {
        this.createVoteCallback = callback;
    }

    public void notifyVoteCreated(boolean success, Vote vote){
        if(createVoteCallback != null){
            createVoteCallback.onVoteCreated(success, vote);
        }
    }

    public interface GetVotesCallback{
        void onCommentsFetched(boolean success, HashMap<UUID, Vote> comments);
    }

    public void setNotifyFetchedVotes(GetVotesCallback callback) {
        this.getVotesCallback = callback;
    }

    public void notifyVotesFetched(boolean success, HashMap<UUID, Vote> votes){
        if(getVotesCallback != null){
            getVotesCallback .onCommentsFetched(success, votes);
        }
    }


    public interface DeleteVoteCallback{
        void onVoteDeleted(boolean success, Vote vote);
    }

    public void setNotifyDeleteVote(DeleteVoteCallback callback) {
        this.deleteVoteCallback = callback;
    }

    public void notifyVoteDeleted(boolean success, Vote vote){
        if(deleteVoteCallback != null){
            deleteVoteCallback.onVoteDeleted(success, vote);
        }
    }
}
