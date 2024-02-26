package com.example.androidprojectcollection;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Calculator extends AppCompatActivity {
    int i;
    ArrayList<Button> numbers = new ArrayList<>(), operators = new ArrayList<>();
    Button clear;
    TextView display;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        clear = (Button) findViewById(R.id.remove);
        display = (TextView) findViewById(R.id.display);
        numbers.add((Button) findViewById(R.id.num1));
        numbers.add((Button) findViewById(R.id.num2));
        numbers.add((Button) findViewById(R.id.num3));
        numbers.add((Button) findViewById(R.id.num4));
        numbers.add((Button) findViewById(R.id.num5));
        numbers.add((Button) findViewById(R.id.num6));
        numbers.add((Button) findViewById(R.id.num7));
        numbers.add((Button) findViewById(R.id.num8));
        numbers.add((Button) findViewById(R.id.num9));
        numbers.add((Button) findViewById(R.id.num0));
        numbers.get(0).setOnClickListener(view -> display.append("1"));
        numbers.get(1).setOnClickListener(view -> display.append("2"));
        numbers.get(2).setOnClickListener(view -> display.append("3"));
        numbers.get(3).setOnClickListener(view -> display.append("4"));
        numbers.get(4).setOnClickListener(view -> display.append("5"));
        numbers.get(5).setOnClickListener(view -> display.append("6"));
        numbers.get(6).setOnClickListener(view -> display.append("7"));
        numbers.get(7).setOnClickListener(view -> display.append("8"));
        numbers.get(8).setOnClickListener(view -> display.append("9"));
        numbers.get(9).setOnClickListener(view -> display.append("0"));
        operators.add((Button) findViewById(R.id.add));
        operators.add((Button) findViewById(R.id.subtract));
        operators.add((Button) findViewById(R.id.multiply));
        operators.add((Button) findViewById(R.id.divide));
        operators.get(0).setOnClickListener(view -> display.append(" + "));
        operators.get(1).setOnClickListener(view -> display.append(" - "));
        operators.get(2).setOnClickListener(view -> display.append(" * "));
        operators.get(3).setOnClickListener(view -> display.append(" / "));
        clear.setOnClickListener(view -> {
            String contentText = display.getText().toString();
            String[] contents = contentText.split(" ");
            if(contents.length > 0){
                contents[contents.length - 1] = contents[contents.length - 1].substring(
                        0, contents[contents.length - 1].length() - 1
                );
                StringBuilder sb = new StringBuilder();
                for (String s: contents) {
                    if(s.equals("+") || s.equals("-") || s.equals("/") || s.equals("*")){
                        sb.append(" " + s + " ");
                    }
                }
            }
        });
    }
}