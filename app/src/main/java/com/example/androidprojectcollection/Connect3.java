package com.example.androidprojectcollection;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class Connect3 extends AppCompatActivity {
    AtomicBoolean isBlack = new AtomicBoolean(true),
            gameEnd = new AtomicBoolean(false);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect3);
        TextView displayText = findViewById(R.id.DisplayText);
        Button[][] slots = {
                {
                    findViewById(R.id.button18),
                    findViewById(R.id.button17),
                    findViewById(R.id.button2),
                    findViewById(R.id.button3),
                    findViewById(R.id.button4)
                },
                {
                    findViewById(R.id.button20),
                    findViewById(R.id.button19),
                    findViewById(R.id.button7),
                    findViewById(R.id.button6),
                    findViewById(R.id.button5)
                },
                {
                    findViewById(R.id.button25),
                    findViewById(R.id.button24),
                    findViewById(R.id.button23),
                    findViewById(R.id.button22),
                    findViewById(R.id.button21)
                },
                {
                    findViewById(R.id.button31),
                    findViewById(R.id.button30),
                    findViewById(R.id.button29),
                    findViewById(R.id.button28),
                    findViewById(R.id.button26),
                },
                {
                    findViewById(R.id.button36),
                    findViewById(R.id.button34),
                    findViewById(R.id.button33),
                    findViewById(R.id.button32),
                    findViewById(R.id.button27)
                }
        };
        int[] slotsLeft = {4, 4, 4, 4, 4};

        for(int i = 0; i < 5; i++){
            int dupI = i;
            slots[0][i].setOnClickListener(view -> {
                if(slotsLeft[dupI] > -1 && !gameEnd.get()){
                    fillSlot(dupI, slots, slotsLeft, displayText);
                }
            });
        }
    }
    public void fillSlot(int column, Button[][] slots, int[] slotsLeft, TextView displayText){
        slots[slotsLeft[column]][column].setBackgroundColor(isBlack.get() ? Color.BLACK : Color.RED);
        checker(slots, slotsLeft[column], column, displayText);
        slotsLeft[column]--;
        isBlack.set(!isBlack.get());
    }

    public void checker(Button[][] slots, int row, int column, TextView displayText){
        int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
        int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
        int result = 0;
        for(int i = 0; i < dx.length; i++){
            if(column + dx[i] < 5 && column + dx[i] >= 0 && row + dy[i] >= 0 && row + dy[i] < 5 &&
            ((ColorDrawable)slots[column][row].getBackground()).getColor() ==
                    ((ColorDrawable) slots[column + dx[i]][row + dy[i]].getBackground()).getColor())
                result += checkerHelper(slots, dx[i], dy[i], row, column, slots[row][column]);
        }
        if(result >= 3){
            gameEnd.set(true);
            displayText.setText((isBlack.get() ? "BLACK" : "RED") .concat(" wins"));
        }
    }

    public int checkerHelper(Button[][] slots, int directionRow, int directionColumn,
                             int row, int column, Button slotRef){
        row += directionRow;
        column += directionColumn;
        if(row < 0 || column < 0 || row >= 5 || column >= 5)
            return 0;
        else if(((ColorDrawable)slots[row][column].getBackground()).getColor() ==
                ((ColorDrawable) slotRef.getBackground()).getColor())
            return checkerHelper(slots, directionRow, directionColumn, row, column, slotRef) + 1;

        return 0;
    }
}