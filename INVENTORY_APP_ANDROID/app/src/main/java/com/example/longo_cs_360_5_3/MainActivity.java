package com.example.longo_cs_360_5_3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.Manifest;

public class MainActivity extends AppCompatActivity {

    private static final int RC_NOTIFICATION = 99;
    DBHelper db;

    EditText username;
    EditText password;
    Button loginButton;
    Button signupButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        signupButton = findViewById(R.id.signupButton);

        db = new DBHelper(this);

        if ((Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)) {
            requestPermissions(new String[]{Manifest.permission.RECEIVE_SMS},
                    RC_NOTIFICATION);
        }


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = username.getText().toString();
                String pass = password.getText().toString();

                if (user.equals("") || pass.equals("")) {
                    Toast.makeText(MainActivity.this, "FAILED",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    Boolean checkUserPass = db.checkUserPass(user, pass);
                    if (checkUserPass == true) {
                        // TAKE ME TO THE SCROLLING GRID SCREEN
                        startActivity(new Intent(MainActivity.this,
                                GridScreen.class));
                    }
                    else {
                        Toast.makeText(MainActivity.this, "FAILED",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    // TAKE ME TO THE SIGNUP PAGE
                startActivity(new Intent(MainActivity.this, SignUpScreen.class));
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == RC_NOTIFICATION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "ALLOWED", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }
}