package com.example.client;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.datastore.rxjava2.RxDataStore;
import androidx.fragment.app.FragmentManager;

import com.example.client.Fragments.Login;
import com.example.client.Fragments.Register;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;

public class MainActivity extends AppCompatActivity {
    Button btnSelectEventPhoto;
    Button btnCreateEvent;

    Button btnNavigateRegister;
    public static SocketClient socketClient = new SocketClient();
    public static RxDataStore dataStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MetroEvents metroEvents = (MetroEvents) getApplication();

        dataStore = metroEvents.getDataStore();

        Thread thread = new Thread(socketClient);
        thread.start();


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container_view, Login.class, null)
                    .commit();
        }

        socketClient.setResponseListener(new SocketClient.ResponseListener() {
            @Override
            public void onSuccess(String message) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

//        btnSelectEventPhoto = findViewById(R.id.btnSelectEventPhoto);
//        btnCreateEvent = findViewById(R.id.btnCreateEvent);

//        ActivityResultLauncher<PickVisualMediaRequest> pickMedia =
//                registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), uri -> {
//                    if (uri != null) {
//                        Log.d("PhotoPicker", "Selected URI: " + uri);
//                        btnCreateEvent.setOnClickListener(v -> {
//                            try {
//                                InputStream inputStream = getContentResolver().openInputStream(uri);
//                                byte[] fileBytes = readFileToBytes(inputStream);
//                                SocketClient.createEventAsync("test", "test", "test", fileBytes, LocalDateTime.now());
//                                inputStream.close();
//                            } catch (IOException e) {
//                                throw new RuntimeException(e);
//                            }
//                        });
//                    } else {
//                        Log.d("PhotoPicker", "No media selected");
//                    }
//                });
//
//        btnSelectEventPhoto.setOnClickListener(v -> {
//            pickMedia.launch(new PickVisualMediaRequest.Builder()
//                    .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE)
//                    .build());
//        });
//
//        btnRegister.setOnClickListener(v -> {
//            String username = String.valueOf(inputUsername.getText());
//            String password = String.valueOf(inputPassword.getText());
//            String firstName = String.valueOf(inputFirstName.getText());
//            String lastName = String.valueOf(inputLastName.getText());
//            SocketClient.registerAsync(username, password, firstName, lastName);
//        });
    }

//    private static byte[] readFileToBytes(InputStream inputStream) throws IOException {
//        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
//            byte[] buffer = new byte[4096];
//            int bytesRead;
//            while ((bytesRead = inputStream.read(buffer)) != -1) {
//                outputStream.write(buffer, 0, bytesRead);
//            }
//            return outputStream.toByteArray();
//        }
//    }
}
