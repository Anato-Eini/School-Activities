package com.example.androidprojectcollection;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.Arrays;

public class PassingIntentsExercise extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passing_intents_exercise);
        Button submitBtn = findViewById(R.id.submitBtn), clearBtn = findViewById(R.id.clearBtn);
        RadioGroup genders = findViewById(R.id.gendersRG);
        LinearLayout mainContainer = findViewById(R.id.mainContainer);
        ArrayList<EditText> fields = new ArrayList<>(Arrays.asList( new EditText[]{
                        findViewById(R.id.firstNameField),
                        findViewById(R.id.lastNameField),
                    }
                )
        );
        for(int i = 5; i <= 12; i++)
            fields.add((EditText) ((LinearLayout)mainContainer.getChildAt(i)).getChildAt(1));
        clearBtn.setOnClickListener(view -> {
            genders.clearCheck();
            for(EditText e: fields)
                e.setText("");
        });
        submitBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, PassingIntentsExercise2.class);
            int checkedId = genders.getCheckedRadioButtonId();
            intent.putExtra("Gender", checkedId == R.id.maleGender ? "Male" :
                            checkedId == R.id.femaleGender ? "Female" :
                            checkedId == R.id.othersGender ? "Others" : "N/A");
            ArrayList<String> values = new ArrayList<>();
            for(EditText e: fields)
                values.add(String.valueOf(e.getText()).isEmpty() ? "N/A" :
                        String.valueOf(e.getText()));
            System.out.println(values.toArray().getClass());
            intent.putExtra("Values", values);
            startActivity(intent);
        });
    }
}
