package com.example.androidprojectcollection;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import java.util.concurrent.atomic.AtomicBoolean;

public class Connect3 extends AppCompatActivity {
    AtomicBoolean isBlack = new AtomicBoolean(true);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect3);
        int[] slotsLeft = {4, 4, 4, 4, 4};
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
        for(int i = 0; i < 5; i++){
            slots[0][i].setOnClickListener(view -> {
                for(int j = 0; j < 5; j++){
                    slots[0][0].getBackground();
                    ColorDrawable c;
                }
            });
        }
    }
    public void fillSlot(int column){

    }
}