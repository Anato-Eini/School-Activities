package com.example.midtermexam;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Batch1 extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        Button enter = findViewById(R.id.nextActivity);
        enter.setOnClickListener(view -> startActivity(new Intent(Batch1.this, MainActivity.class)));
    }
}
