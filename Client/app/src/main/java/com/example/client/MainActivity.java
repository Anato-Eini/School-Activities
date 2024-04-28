package com.example.client;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.io.IOException;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    EditText inputUsername;
    EditText inputPassword;
    EditText inputFirstName;
    EditText inputLastName;
    Button btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputUsername= (EditText) findViewById(R.id.inputUsername);
        inputPassword= (EditText) findViewById(R.id.inputPassword);
        inputFirstName = (EditText) findViewById(R.id.inputFirstName);
        inputFirstName = (EditText) findViewById(R.id.inputLastName);
        btnRegister = (Button) findViewById(R.id.btnRegister);

        SocketClient socketClient = new SocketClient();
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

        Thread thread = new Thread(socketClient);
        thread.start();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = String.valueOf(inputUsername.getText());
                String password = String.valueOf(inputUsername.getText());
                String firstName = String.valueOf(inputUsername.getText());
                String lastName = String.valueOf(inputUsername.getText());
                SocketClient.registerAsync(username, password, firstName, lastName);
            }
        });
    }
}
