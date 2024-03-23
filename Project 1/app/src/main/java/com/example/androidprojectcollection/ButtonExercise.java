package com.example.androidprojectcollection;

import static android.graphics.Color.*;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class ButtonExercise extends AppCompatActivity {
    Button anotherActivity, changeLayout, toastButton, changeBgAct, changeBtnClr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_exercise);
        anotherActivity = (Button) findViewById(R.id.newActivity);
        changeLayout = (Button) findViewById(R.id.changeLayout);
        toastButton = (Button) findViewById(R.id.toastButton);
        changeBgAct = (Button) findViewById(R.id.changeBgBtn);
        changeBtnClr = (Button) findViewById(R.id.changeButtonClr);
        anotherActivity.setOnClickListener(view -> startActivity(new Intent(ButtonExercise.this, AnotherActivity.class)));
        changeLayout.setOnClickListener(view -> changeLayout.setAlpha(0));
        toastButton.setOnClickListener(view -> Toast.makeText(getBaseContext(), "TEST", Toast.LENGTH_LONG).show());
        changeBgAct.setOnClickListener(view -> {
            ConstraintLayout layout = findViewById(R.id.background);
            layout.setBackgroundColor(MAGENTA);
        });
        changeBtnClr.setOnClickListener(view -> changeBtnClr.setBackgroundResource(R.color.lightGreen));
    }
}