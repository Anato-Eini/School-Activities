package com.example.client.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.client.Activities.OrganizerDashboard;
import com.example.client.Activities.UserDashboard;
import com.example.client.Classes.EventsAdapter;
import com.example.client.Entities.Event;
import com.example.client.Entities.User;
import com.example.client.MetroEvents;
import com.example.client.R;
import com.example.client.SocketClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Events#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Events extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    MetroEvents metroEvents;

    public Events() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Events.
     */
    // TODO: Rename and change types and number of parameters
    public static Events newInstance(String param1, String param2) {
        Events fragment = new Events();
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
        View view = inflater.inflate(R.layout.fragment_events, container, false);
        SocketClient.getEvents();

        RecyclerView events_rv_ = view.findViewById(R.id.events_rv_);

        metroEvents.getEvents(new MetroEvents.EventsFetchCallback() {
            @Override
            public void onEventsFetched(HashMap<UUID, Event> events) {
                List<Event> eventList = new ArrayList<>(events.values());
                EventsAdapter adapter = new EventsAdapter(eventList);
                events_rv_.setAdapter(adapter);
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        events_rv_.setLayoutManager(layoutManager);

        return view;
    }
};