package com.example.client.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.client.Activities.ManageEvent;
import com.example.client.Entities.Event;
import com.example.client.Entities.EventParticipantStatusNotification;
import com.example.client.MetroEvents;
import com.example.client.R;
import com.example.client.SocketClient;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Notifications#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Notifications extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ArrayList<EventParticipantStatusNotification> eventParticipantStatusNotifications;
    LinearLayout notifications_ll_;

    public Notifications() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Notifications.
     */
    // TODO: Rename and change types and number of parameters
    public static Notifications newInstance(String param1, String param2) {
        Notifications fragment = new Notifications();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);
        notifications_ll_ = view.findViewById(R.id.notifications_ll_);

        notifications_ll_.removeAllViews();
        SocketClient.getUserEventParticipantStatusNotifications((status, notifications) -> {
            assert getActivity() != null;
            getActivity().runOnUiThread(() -> {
                eventParticipantStatusNotifications = new ArrayList<>(notifications.values());

                for (EventParticipantStatusNotification notification: eventParticipantStatusNotifications) {
                    View eventView = inflater.inflate(R.layout.notification_item, notifications_ll_, false);
                    TextView notification_txt_ = eventView.findViewById(R.id.notification_txt_);
                    notification_txt_.setText(notification.notification);
                    notifications_ll_.addView(eventView);
                }
            });
        });
        return view;
    }
}