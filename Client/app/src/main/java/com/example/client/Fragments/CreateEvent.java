package com.example.client.Fragments;


import static android.app.Activity.RESULT_OK;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.client.R;
import com.example.client.SocketClient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateEvent#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateEvent extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int PICK_IMAGE_REQUEST = 1;

    private ImageView event_image_img_;
    private Uri imageUri;

    private TextView event_datetime_txt_;
    private LocalDateTime eventDateTime;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CreateEvent() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateEvent.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateEvent newInstance(String param1, String param2) {
        CreateEvent fragment = new CreateEvent();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_event, container, false);

        EditText event_title_txt_ = view.findViewById(R.id.event_title_txt_);
        EditText event_description_txt_ = view.findViewById(R.id.event_description_txt_);
        EditText event_venue_txt_ = view.findViewById(R.id.event_venue_txt_);
        Button select_event_datetime_btn_ = view.findViewById(R.id.select_event_datetime_btn_);
        Button selectImageBtn = view.findViewById(R.id.select_event_image_btn_);
        Button create_event_btn = view.findViewById(R.id.create_event_btn_);
        event_image_img_ = view.findViewById(R.id.event_image_img_);
        event_datetime_txt_ = view.findViewById(R.id.event_datetime_txt_);

        select_event_datetime_btn_.setOnClickListener(v -> {
            openDateTimePicker();
        });

        selectImageBtn.setOnClickListener(v -> openImagePicker());

        create_event_btn.setOnClickListener(v -> {
            try {
                InputStream inputStream = requireContext().getContentResolver().openInputStream(getImageUri());
                assert inputStream != null;
                byte[] imageBytes = readFileToBytes(inputStream);

                SocketClient.createEventAsync(
                        event_title_txt_.getText().toString(),
                        event_description_txt_.getText().toString(),
                        event_venue_txt_.getText().toString(),
                        imageBytes,
                        eventDateTime
                );
            } catch (IOException e) {
                e.printStackTrace();
                // Handle the error accordingly
            }
        });

        return view;
    }

    private void openDateTimePicker() {
        final Calendar currentDate = Calendar.getInstance();
        final Calendar date = Calendar.getInstance();
        new DatePickerDialog(getContext(), (view, year, monthOfYear, dayOfMonth) -> {
            date.set(year, monthOfYear, dayOfMonth);
            new TimePickerDialog(getContext(), (view1, hourOfDay, minute) -> {
                date.set(Calendar.HOUR_OF_DAY, hourOfDay);
                date.set(Calendar.MINUTE, minute);
                eventDateTime = LocalDateTime.ofInstant(date.toInstant(), date.getTimeZone().toZoneId());
                event_datetime_txt_.setText(eventDateTime.toString());
            }, currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), false).show();
        }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE)).show();
    }

    private void openImagePicker() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == getActivity().RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(requireContext().getContentResolver(), imageUri);
                event_image_img_.setImageBitmap(bitmap);
                event_image_img_.setVisibility(View.VISIBLE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Uri getImageUri() {
        return imageUri;
    }

    private static byte[] readFileToBytes(InputStream inputStream) throws IOException {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            return outputStream.toByteArray();
        }
    }
}