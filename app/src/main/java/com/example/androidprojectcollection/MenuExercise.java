package com.example.androidprojectcollection;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MenuExercise extends AppCompatActivity {
    Button button;
    ConstraintLayout container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_exercise);
        button = findViewById(R.id.buttonChanger);
        container = findViewById(R.id.container);
        button.setBackgroundColor(Color.YELLOW);
        View viewButton = button;
        viewButton.getLayoutParams().height = 500;
        viewButton.getLayoutParams().width = 500;
        viewButton.requestLayout();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.choice_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.changeButtonPosition:
                button.setX(50);
                break;
            case R.id.changeButtonColor:
                button.setBackgroundColor(Color.BLUE);
                break;
            case R.id.changeTextColor:
                button.setTextColor(Color.GREEN);
                break;
            case R.id.changeText:
                button.setText("Changed Text");
                break;
            case R.id.changeButtonHeight:
                View viewButton = button;
                viewButton.getLayoutParams().height = 1000;
                viewButton.requestLayout();
                break;
            case R.id.reset:
                View viewButton1 = button;
                int containerWidth = container.getWidth(), containerHeight = container.getHeight()
                        , buttonWidth = button.getWidth(), buttonHeight = button.getHeight();
                button.setX((containerWidth / 2) - (buttonWidth / 2));
                button.setY((containerHeight / 2) - (buttonHeight / 2));
                container.setBackgroundColor(Color.WHITE);
                button.setText("EXAMPLE TEXT");
                viewButton1.getLayoutParams().height = 500;
                button.setBackgroundColor(Color.YELLOW);
                button.setTextColor(Color.BLACK);
                viewButton1.requestLayout();
                break;
            case R.id.exitMenu:
                finish();
        }
        return true;
    }
}