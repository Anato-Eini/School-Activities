package com.example.client.Activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.client.Classes.CommentsAdapter;
import com.example.client.Entities.Comment;
import com.example.client.Entities.EventParticipant;
import com.example.client.Entities.Vote;
import com.example.client.MetroEvents;
import com.example.client.R;
import com.example.client.SocketClient;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class EventDetails extends AppCompatActivity {
    private TextView titleTextView;
    private TextView descriptionTextView;
    private ImageView imageView;
    private Button join_event_btn_;
    private Button comment_event_btn_;
    private EditText comment_event_et_;
    private RecyclerView comments_rv_;
    private ImageButton upvote_btn_;
    private ImageButton downvote_btn_;
    private TextView vote_count_txt_;
    private ArrayList<Comment> eventComments = new ArrayList<>();
    private ArrayList<Vote> eventVotes = new ArrayList<>();
    private Vote userVote;
    private UUID event_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_event_details);

        titleTextView = findViewById(R.id.title_detail);
        descriptionTextView = findViewById(R.id.description_detail);
        imageView = findViewById(R.id.image_detail);
        comment_event_btn_ = findViewById(R.id.comment_event_btn_);
        comment_event_et_ = findViewById(R.id.comment_event_et_);
        join_event_btn_ = findViewById(R.id.join_event_btn_);
        comments_rv_ = findViewById(R.id.comments_rv_);
        upvote_btn_ = findViewById(R.id.upvote_btn_);
        downvote_btn_ = findViewById(R.id.downvote_btn_);
        vote_count_txt_ = findViewById(R.id.vote_count_txt_);

        event_id = UUID.fromString(getIntent().getStringExtra("id"));
        String title = getIntent().getStringExtra("title");
        String description = getIntent().getStringExtra("description");
        String image = getIntent().getStringExtra("image");

        titleTextView.setText(title);
        descriptionTextView.setText(description);
        Picasso.get()
                .load(image)
                .into(imageView);

        SocketClient.getComments((success, comments) -> {
            if (success && comments != null) {
                runOnUiThread(() -> {
                    for (Map.Entry<UUID, Comment> entry : comments.entrySet()) {
                        Comment comment = entry.getValue();
                        if (comment.event_id.equals(event_id)) {
                            eventComments.add(comment);
                        }
                    }
                    Collections.sort(eventComments);
                    CommentsAdapter adapter = new CommentsAdapter(eventComments, EventDetails.this);
                    comments_rv_.setAdapter(adapter);
                });
            }
        });

        MetroEvents metroEvents = (MetroEvents) getApplication();

        metroEvents.getParticipatedEvents(new MetroEvents.ParticipatedEventsFetchCallback() {
            @Override
            public void onEventsFetched(HashMap<UUID, EventParticipant> events) {
                EventParticipant eventParticipant = events.get(event_id);
                if(eventParticipant != null){
                    if(eventParticipant.status == EventParticipant.Status.PENDING){
                        join_event_btn_.setText("Pending");
                    }
                    if(eventParticipant.status == EventParticipant.Status.ACCEPTED){
                        join_event_btn_.setText("Accepted");
                    }
                }else{
                    join_event_btn_.setOnClickListener(v -> {
                        SocketClient.joinEvent(event_id);
                        SocketClient.getJoinedEvents();
                        join_event_btn_.setText("Pending");
                    });
                }
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(EventDetails.this);
        comments_rv_.setLayoutManager(layoutManager);

        comment_event_btn_.setOnClickListener(v -> {
            String commentText = comment_event_et_.getText().toString();
            if (!commentText.isEmpty()) {
                SocketClient.createComment(commentText, event_id, (success, newComment) -> {
                    if (success && newComment != null) {
                        runOnUiThread(() -> {
                            eventComments.add(0, newComment);
                            comments_rv_.getAdapter().notifyItemInserted(eventComments.size() - 1);
                            comment_event_et_.setText("");
                        });
                    }
                });
            }
        });

        SocketClient.getVotes((success, votes) -> {
            if (success && votes != null) {
                runOnUiThread(() -> {
                    int upvote = 0;
                    int downvote = 0;
                    for (Map.Entry<UUID, Vote> entry : votes.entrySet()) {
                        Vote vote = entry.getValue();

                        if (vote.event_id.equals(event_id)) {
                            if (vote.vote == Vote.VoteType.UPVOTE) upvote++;
                            if (vote.vote == Vote.VoteType.DOWNVOTE) downvote++;

                            if (vote.user_id.equals(MetroEvents.user.id)) {
                                userVote = vote;
                            }
                            eventVotes.add(vote);
                        }
                    }

                    vote_count_txt_.setText(String.valueOf(upvote - downvote));
                    updateButtonStatesAndListeners();
                });
            }
        });

    }
    private void updateButtonStatesAndListeners() {
        if (userVote != null) {
            if (userVote.vote == Vote.VoteType.UPVOTE) {
                upvote_btn_.setImageResource(R.drawable.upvote_svgrepo_com_filled);
                downvote_btn_.setImageResource(R.drawable.downvote_svgrepo_com);

                upvote_btn_.setOnClickListener(v -> {
                    disableButtons();
                    SocketClient.deleteVote(event_id, (status, vote) -> {
                        runOnUiThread(() -> {
                            if (status) {
                                vote_count_txt_.setText(String.valueOf(Integer.parseInt(vote_count_txt_.getText().toString()) - 1));
                                userVote = null;
                                for (Vote eventVote : eventVotes) {
                                    if (eventVote.id.equals(vote.id)) {
                                        eventVotes.remove(eventVote);
                                        break;
                                    }
                                }
                            }
                            updateButtonStatesAndListeners();
                            enableButtons();
                        });
                    });
                });

                downvote_btn_.setOnClickListener(v -> {
                    disableButtons();
                    SocketClient.createVote(Vote.VoteType.DOWNVOTE, event_id, (status, vote) -> {
                        runOnUiThread(() -> {
                            if (status) {
                                vote_count_txt_.setText(String.valueOf(Integer.parseInt(vote_count_txt_.getText().toString()) - 2));
                                userVote = vote;
                            }
                            updateButtonStatesAndListeners();
                            enableButtons();
                        });
                    });
                });
            } else if (userVote.vote == Vote.VoteType.DOWNVOTE) {
                downvote_btn_.setImageResource(R.drawable.downvote_svgrepo_com_filled);
                upvote_btn_.setImageResource(R.drawable.upvote_svgrepo_com);

                downvote_btn_.setOnClickListener(v -> {
                    disableButtons();
                    SocketClient.deleteVote(event_id, (status, vote) -> {
                        runOnUiThread(() -> {
                            if (status) {
                                vote_count_txt_.setText(String.valueOf(Integer.parseInt(vote_count_txt_.getText().toString()) + 1));
                                userVote = null;
                                for (Vote eventVote : eventVotes) {
                                    if (eventVote.id.equals(vote.id)) {
                                        eventVotes.remove(eventVote);
                                        break;
                                    }
                                }
                            }
                            updateButtonStatesAndListeners();
                            enableButtons();
                        });
                    });
                });

                upvote_btn_.setOnClickListener(v -> {
                    disableButtons();
                    SocketClient.createVote(Vote.VoteType.UPVOTE, event_id, (status, vote) -> {
                        runOnUiThread(() -> {
                            if (status) {
                                vote_count_txt_.setText(String.valueOf(Integer.parseInt(vote_count_txt_.getText().toString()) + 2));
                                userVote = vote;
                            }
                            updateButtonStatesAndListeners();
                            enableButtons();
                        });
                    });
                });
            }
        } else {
            upvote_btn_.setImageResource(R.drawable.upvote_svgrepo_com);
            downvote_btn_.setImageResource(R.drawable.downvote_svgrepo_com);

            upvote_btn_.setOnClickListener(v -> {
                disableButtons();
                SocketClient.createVote(Vote.VoteType.UPVOTE, event_id, (status, vote) -> {
                    runOnUiThread(() -> {
                        if (status) {
                            vote_count_txt_.setText(String.valueOf(Integer.parseInt(vote_count_txt_.getText().toString()) + 1));
                            userVote = vote;
                        }
                        updateButtonStatesAndListeners();
                        enableButtons();
                    });
                });
            });

            downvote_btn_.setOnClickListener(v -> {
                disableButtons();
                SocketClient.createVote(Vote.VoteType.DOWNVOTE, event_id, (status, vote) -> {
                    runOnUiThread(() -> {
                        if (status) {
                            vote_count_txt_.setText(String.valueOf(Integer.parseInt(vote_count_txt_.getText().toString()) - 1));
                            userVote = vote;
                        }
                        updateButtonStatesAndListeners();
                        enableButtons();
                    });
                });
            });
        }
    }

    private void disableButtons() {
        upvote_btn_.setEnabled(false);
        downvote_btn_.setEnabled(false);
    }

    private void enableButtons() {
        upvote_btn_.setEnabled(true);
        downvote_btn_.setEnabled(true);
    }
}