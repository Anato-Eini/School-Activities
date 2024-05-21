package com.example.client.Activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.client.Fragments.Login;
import com.example.client.R;

public class Auth extends AppCompatActivity {
    Button btnSelectEventPhoto;
    Button btnCreateEvent;

    Button btnNavigateRegister;

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container_view, Login.class, null)
                    .commit();
        }
    }
}
