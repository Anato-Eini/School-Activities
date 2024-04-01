package com.example.androidprojectcollection;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.atomic.AtomicBoolean;

public class Connect3 extends AppCompatActivity {
    AtomicBoolean isBlack = new AtomicBoolean(true),
            gameEnd = new AtomicBoolean(false);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect3);
        TextView displayText = findViewById(R.id.DisplayText);
        Button resetBtn = findViewById(R.id.resetBtn);
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
                    if(!gameEnd.get())
                        displayText.setText(isBlack.get() ? getString(R.string.black_turn) :
                                getString(R.string.red_turn));
                }
            });
        }

        resetBtn.setOnClickListener(view -> {
            for(int i = 0; i < 5; i++)
                slotsLeft[i] = 4;
            displayText.setText(getString(R.string.black_turn));
            for(Button[] B: slots){
                for(Button b: B)
                    b.setBackgroundColor(Color.WHITE);
            }
            gameEnd.set(false);
            isBlack.set(true);
        });

    }
    public void fillSlot(int column, Button[][] slots, int[] slotsLeft, TextView displayText){
        slots[slotsLeft[column]][column].setBackgroundColor(isBlack.get() ? Color.BLACK : Color.RED);
        checker(slots, slotsLeft[column], column, displayText);
        isBlack.set(!isBlack.get());
        slotsLeft[column]--;
    }

    public void checker(Button[][] slots, int row, int column, TextView displayText){
        int[] dx = {0, 1, 1, 1, 0, -1, -1, -1},
                dy = {-1, -1, 0, 1, 1, 1, 0, -1};

        for(int i = 0; i < 8; i++){
            if(column + dx[i] < 5 && column + dx[i] >= 0 && row + dy[i] >= 0 && row + dy[i] < 5 &&
            ((ColorDrawable)slots[row][column].getBackground()).getColor() ==
                    ((ColorDrawable) slots[row + dy[i]][column + dx[i]].getBackground()).getColor()) {
                int result = 1;
                result += checkerHelper(slots, dy[i], dx[i], row,
                        column, slots[row][column]);
                result += checkerHelper(slots, -dy[i], -dx[i], row, column, slots[row][column]);
                if(result >= 3){
                    gameEnd.set(true);
                    displayText.setText(isBlack.get() ? getString(R.string.black_win) :
                            getString(R.string.red_win));
                }
            }
        }
    }

    public int checkerHelper(Button[][] slots, int directionRow, int directionColumn,
                             int row, int column, Button slotRef){
        row += directionRow;
        column += directionColumn;
        if(row < 0 || column < 0 || row >= 5 || column >= 5 ||
                (((ColorDrawable)slots[row][column].getBackground()).getColor() !=
                ((ColorDrawable) slotRef.getBackground()).getColor()))
            return 0;
        else
            return checkerHelper(slots, directionRow, directionColumn, row, column, slotRef) + 1;
    }
}