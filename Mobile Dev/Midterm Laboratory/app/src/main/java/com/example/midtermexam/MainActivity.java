package com.example.midtermexam;

import static android.graphics.Color.*;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class MainActivity extends AppCompatActivity {
    AtomicBoolean isPlayer1Turn = new AtomicBoolean(true);
    ArrayList<AtomicBoolean> isClicked = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(
                getApplicationContext(),
                "James Kenneth S. Acabal - TicTacToe", Toast.LENGTH_LONG).show();
        Button[] buttons = {
                findViewById(R.id.button1),
                findViewById(R.id.button2),
                findViewById(R.id.button3),
                findViewById(R.id.button4),
                findViewById(R.id.button5),
                findViewById(R.id.button6),
                findViewById(R.id.button7),
                findViewById(R.id.button8),
                findViewById(R.id.button9)
        };
        LinearLayout background = findViewById(R.id.background);
        TextView display = findViewById(R.id.display);
        for(int i = 0; i < 9; i++)
            isClicked.add(new AtomicBoolean(false));
        for(int i = 0; i < 9; i++){
            int index = i;
            buttons[i].setOnClickListener(view -> {
                if(!isClicked.get(index).get()){
                    isClicked.get(index).set(true);
                    buttons[index].setText(isPlayer1Turn.get() ? "O" : "X");
                    background.setBackgroundColor(isPlayer1Turn.get() ? RED: BLUE);
                    isPlayer1Turn.set(!isPlayer1Turn.get());
                    display.setText(isPlayer1Turn.get() ? "Player O's Turn" : "Player X's Turn");
                }
            });
        }
    }
}