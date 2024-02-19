package com.example.androidprojectcollection;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ButtonExercise extends AppCompatActivity {
    Button anotherActivity, changeLayout, toastButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_exercise);
        anotherActivity = (Button) findViewById(R.id.newActivity);
        changeLayout = (Button) findViewById(R.id.changeLayout);
        toastButton = (Button) findViewById(R.id.toastButton);
        anotherActivity.setOnClickListener(view -> startActivity(new Intent(ButtonExercise.this, AnotherActivity.class)));
        changeLayout.setOnClickListener(view -> changeLayout.setAlpha(0));
        toastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(), "TEST", Toast.LENGTH_LONG).show();
            }
        });

    }
}