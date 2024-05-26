package com.example.client.Activities;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;

import com.example.client.Fragments.CreateEvent;
import com.example.client.Fragments.Events;
import com.example.client.Fragments.Notifications;
import com.example.client.Fragments.Profile;
import com.example.client.R;

public class UserDashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        setContentView(R.layout.activity_user_dashboard);

        //TODO KUNG GI BACK NI USER DLI DAPAT MU BALIK SA LOGIN MU QUIT DAPAT, THEN IF NAA SA PROFILE TAB THEN NI BACK MUBALIK SA HOME
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragment_container_view, Events.class, null)
                    .commit();
        }

        ImageButton navigate_home_btn_ = findViewById(R.id.navigate_home_btn_);
        navigate_home_btn_.setOnClickListener(v -> getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragment_container_view, Events.class, null)
                .commit());

        ImageButton navigate_notifications_btn_ = findViewById(R.id.navigate_notifications_btn_);
        navigate_notifications_btn_.setOnClickListener(v -> getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragment_container_view, Notifications.class, null)
                .commit());


        ImageButton navigate_profile_btn = findViewById(R.id.navigate_profile_btn_);
        navigate_profile_btn.setOnClickListener(v -> getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragment_container_view, Profile.class, null)
                .commit());

       // Button request_to_be_organizer = findViewById(R.id.requestOrganizer);
    }
}