package com.example.client.Fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.client.Activities.ManageEvent;
import com.example.client.Activities.UserDashboard;
import com.example.client.Classes.EventsAdapter;
import com.example.client.Entities.Event;
import com.example.client.MetroEvents;
import com.example.client.R;
import com.example.client.SocketClient;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ManageEvents#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ManageEvents extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    MetroEvents metroEvents;
    LinearLayout events_ll;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ManageEvents() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ManageEvents.
     */
    // TODO: Rename and change types and number of parameters
    public static ManageEvents newInstance(String param1, String param2) {
        ManageEvents fragment = new ManageEvents();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        metroEvents = (MetroEvents) requireActivity().getApplication();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manage_events, container, false);

        events_ll = view.findViewById(R.id.events_ll_);

        if(MetroEvents.users == null){
            SocketClient.getUsers((status, users) -> {
                MetroEvents.users = users;
            });
        }

        metroEvents.getEvents(new MetroEvents.EventsFetchCallback() {
            @Override
            public void onEventsFetched(HashMap<UUID, Event> events) {
                List<Event> eventList = new ArrayList<>(events.values());

                events_ll.removeAllViews();

                for (Event event : eventList) {
                    if(!event.organizer_id.equals(MetroEvents.user.id))continue;
                    View eventView = inflater.inflate(R.layout.event_item , events_ll, false);

                    TextView titleTextView = eventView.findViewById(R.id.title_txt_);
                    TextView descriptionTextView = eventView.findViewById(R.id.description_txt);
                    ImageView imageView = eventView.findViewById(R.id.image_img_);

                    titleTextView.setText(event.title);
                    descriptionTextView.setText(event.description);

                    Picasso.get()
                            .load(event.image)
                            .into(imageView);

                    eventView.setTag(event.id);
                    eventView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), ManageEvent.class);

                            intent.putExtra("id", event.id.toString());
                            intent.putExtra("title", event.title);
                            intent.putExtra("description", event.description);
                            intent.putExtra("image", event.image);

                            startActivity(intent);
                        }
                    });

                    events_ll.addView(eventView);
                }
            }
        });

        return view;
    }
}