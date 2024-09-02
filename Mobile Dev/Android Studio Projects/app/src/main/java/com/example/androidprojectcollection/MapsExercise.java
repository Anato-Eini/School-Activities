package com.example.androidprojectcollection;

import android.content.Intent;
import android.net.Uri;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

public class MapsExercise extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_exercise);
        ImageButton mapButton1 = findViewById(R.id.mapButton1),
        mapButton2 = findViewById(R.id.mapButton2),
        mapButton3 = findViewById(R.id.mapButton3),
        mapButton4 = findViewById(R.id.mapButton4),
        mapButton5 = findViewById(R.id.mapButton5);
        ConstraintLayout bgMap = findViewById(R.id.backgroundMap);
        mapButton1.setOnClickListener(view -> {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(
                    "geo:48.85848667798409, 2.2944398211854713")));
            bgMap.setBackgroundResource(R.drawable.background_map1);
        });
        mapButton2.setOnClickListener(view -> {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(
                    "geo:9.82979513224445, 124.13986092926085")
            ));
            bgMap.setBackgroundResource(R.drawable.background_map2);
        });
        mapButton3.setOnClickListener(view -> {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(
                    "geo:11.968456876941374, 121.91965778552121"
            )));
            bgMap.setBackgroundResource(R.drawable.background_map3);
        });
        mapButton4.setOnClickListener(view -> {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(
                    "geo:9.463450773798725, 123.3798775794776"
            )));
            bgMap.setBackgroundResource(R.drawable.background_map4);
        });
        mapButton5.setOnClickListener(view -> {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(
                    "geo:27.17315785886068, 78.0419680965947"
            )));
            bgMap.setBackgroundResource(R.drawable.background_map5);
        });
    }
}