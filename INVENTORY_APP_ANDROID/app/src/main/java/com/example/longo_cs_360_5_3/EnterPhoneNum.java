package com.example.longo_cs_360_5_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EnterPhoneNum extends AppCompatActivity {


    Button subButton;
    Button backButton;
    EditText phone;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_phone_num);

        subButton = findViewById(R.id.phoneSubmitButton);
        backButton = findViewById(R.id.phoneBackButton);
        phone = findViewById(R.id.phoneText);

        subButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // ASSIGN GLOBAL PHONE VAR
                // TO THIS PHONE TEXT HERE

                // IDEALLY WE WOULD ADD THIS TO THE USER
                // DATABASE TABLE

                // BUT THIS WORKS JUST FINE FOR OUR
                // PURPOSES & INTENTIONS OF
                // GETTING FAMILIAR WITH ASKING PERMISSION
                // & SENDING TEXTS
                GLOBAL.PHONE_NUM = phone.getText().toString().trim();


                // TAKE ME TO THE GRID SCREEN
                startActivity(new Intent(EnterPhoneNum.this,
                        GridScreen.class));
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TAKE ME TO THE GRID SCREEN
                startActivity(new Intent(EnterPhoneNum.this,
                        GridScreen.class));
            }
        });

    }
}