package com.example.client.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.client.Entities.Event;
import com.example.client.Entities.EventParticipant;
import com.example.client.MetroEvents;
import com.example.client.R;
import com.example.client.SocketClient;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.zip.Inflater;

public class ManageEvent extends AppCompatActivity {
    UUID event_id;
    TextView titleTextView;
    TextView descriptionTextView;
    ImageView imageView;
    LinearLayout event_participants_ll;
    ArrayList<EventParticipant> eventParticipants;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_manage_event);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        titleTextView = findViewById(R.id.title_detail);
        descriptionTextView = findViewById(R.id.description_detail);
        imageView = findViewById(R.id.image_detail);
        event_participants_ll = findViewById(R.id.event_participants_ll);

        event_id = UUID.fromString(getIntent().getStringExtra("id"));
        String title = getIntent().getStringExtra("title");
        String description = getIntent().getStringExtra("description");
        String image = getIntent().getStringExtra("image");

        SocketClient.getEventParticipants(event_id.toString(), (status, participants) -> {
            eventParticipants = new ArrayList<>(participants.values());
            runOnUiThread(() -> {
                populateEventParticipants(eventParticipants);
            });
        });

        titleTextView.setText(title);
        descriptionTextView.setText(description);
        Picasso.get()
                .load(image)
                .into(imageView);
    }

    public void populateEventParticipants(List<EventParticipant> eventParticipants){
        LayoutInflater inflater = LayoutInflater.from(this);
        Set<UUID> initializedSpinners = new HashSet<>();

        for (EventParticipant participant : eventParticipants) {
            View participantView = inflater.inflate(R.layout.event_participant, event_participants_ll, false);

            TextView usernameTextView = participantView.findViewById(R.id.username_txt_);
            Spinner statusSpinner = participantView.findViewById(R.id.status_spinner);

            usernameTextView.setText(participant.user_id.toString());

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getStatusValues());
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            statusSpinner.setAdapter(adapter);

            statusSpinner.setSelection(getStatusPosition(participant.status));
            statusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (initializedSpinners.contains(participant.id)) {
                        String selectedStatus = (String) parent.getItemAtPosition(position);
                        EventParticipant.Status newStatus = EventParticipant.Status.fromValue(selectedStatus);
                        participant.status = newStatus;
                        updateParticipantStatus(participant);
                    } else {
                        initializedSpinners.add(participant.id);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    // Do nothing
                }
            });

            event_participants_ll.addView(participantView);
        }
    }

    private void updateParticipantStatus(EventParticipant participant) {
        SocketClient.updateEventParticipantStatus(participant.id.toString(), participant.status.getValue(), (status) -> {
        });
    }

    private List<String> getStatusValues() {
        List<String> statusValues = new ArrayList<>();
        for (EventParticipant.Status status : EventParticipant.Status.values()) {
            statusValues.add(status.getValue());
        }
        return statusValues;
    }

    private int getStatusPosition(EventParticipant.Status status) {
        List<String> statusValues = getStatusValues();
        return statusValues.indexOf(status.getValue());
    }
}