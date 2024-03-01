package com.example.longo_cs_360_5_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpScreen extends AppCompatActivity {


    Button signupSubmitButton;
    EditText signupUserText, signupPasswordText;
    DBHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);

        signupSubmitButton = findViewById(R.id.signupSubmitButton);
        signupUserText = findViewById(R.id.signupUserText);
        signupPasswordText = findViewById(R.id.signupPasswordText);
        db = new DBHelper(SignUpScreen.this);

        signupSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //           TO DO
                //           REGISTER THE USER IN A DATABASE HERE
                String user = signupUserText.getText().toString();
                String pass = signupPasswordText.getText().toString();

                if (user.equals("") || pass.equals("")) {
                    Toast.makeText(SignUpScreen.this, "FAILED",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    Boolean checkUser = db.checkUser(user);
                    if (checkUser == false) { // if the username does NOT already exist
                        // in the registered users table then proceed
                        Boolean insert = db.insertUser(user, pass);
                        if (insert == true) {
                            Toast.makeText(SignUpScreen.this, "YOU'RE SIGNED UP =D"
                                    , Toast.LENGTH_SHORT).show();
                        }
                    }
                }


                // TAKE ME TO THE LOGIN PAGE
                startActivity(new Intent(SignUpScreen.this, MainActivity.class));
            }
        });
    }
}