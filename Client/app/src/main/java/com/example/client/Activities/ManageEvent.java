package com.example.client.Activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.client.R;
import com.squareup.picasso.Picasso;

import java.util.UUID;

public class ManageEvent extends AppCompatActivity {
    UUID event_id;
    TextView titleTextView;
    TextView descriptionTextView;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_manage_event);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        titleTextView = findViewById(R.id.title_detail);
        descriptionTextView = findViewById(R.id.description_detail);
        imageView = findViewById(R.id.image_detail);

        event_id = UUID.fromString(getIntent().getStringExtra("id"));
        String title = getIntent().getStringExtra("title");
        String description = getIntent().getStringExtra("description");
        String image = getIntent().getStringExtra("image");

        titleTextView.setText(title);
        descriptionTextView.setText(description);
        Picasso.get()
                .load(image)
                .into(imageView);
    }
}