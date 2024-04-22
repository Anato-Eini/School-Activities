package com.example.androidprojectcollection;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button layoutExerciseBtn = findViewById(R.id.layoutExercise),
        buttonExerciseBtn = findViewById(R.id.buttonExercise),
        calculator = findViewById(R.id.calculator),
        connect3 = findViewById(R.id.connect3),
        passingIntents = findViewById(R.id.passingIntents),
        menuExercise = findViewById(R.id.menus);
        layoutExerciseBtn.setOnClickListener(
                view -> startActivity(
                        new Intent(this, LayoutExercise.class)));
        buttonExerciseBtn.setOnClickListener(
                view -> startActivity(
                        new Intent(this, ButtonExercise.class)));
        calculator.setOnClickListener(
                view -> startActivity(
                        new Intent(this, Calculator.class)));
        connect3.setOnClickListener(
                view -> startActivity(
                        new Intent(this, Connect3.class)));
        passingIntents.setOnClickListener(
                view -> startActivity(
                        new Intent(this, PassingIntentsExercise.class)));
        menuExercise.setOnClickListener(
                view -> startActivity(
                        new Intent(this, MenuExercise.class)));
    }
}