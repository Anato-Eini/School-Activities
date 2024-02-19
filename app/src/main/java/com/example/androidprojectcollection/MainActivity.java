package com.example.androidprojectcollection;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button layoutExerciseBtn;
    Button buttonExerciseBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutExerciseBtn = (Button) findViewById(R.id.layoutExercise);
        buttonExerciseBtn = (Button) findViewById(R.id.buttonExercise);
        layoutExerciseBtn.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, LayoutExercise.class)));
        buttonExerciseBtn.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, ButtonExercise.class)));

    }
}