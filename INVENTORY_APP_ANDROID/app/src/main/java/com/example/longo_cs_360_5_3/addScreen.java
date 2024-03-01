package com.example.longo_cs_360_5_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addScreen extends AppCompatActivity {


    DBHelper db;

    EditText name, price, quan;
    Button addButton;




    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_screen);

        name = findViewById(R.id.itemNameText);
        price = findViewById(R.id.itemPriceText);
        quan = findViewById(R.id.itemQuanText);

        addButton = findViewById(R.id.addItemButton);

        backButton = findViewById(R.id.backButton);

        db = new DBHelper(this);


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String priceTXT = price.getText().toString();
                String quanTXT = quan.getText().toString();

                Boolean checkAddData = db.insertData(nameTXT, priceTXT, quanTXT);
                if (checkAddData == true) {
                    Toast.makeText(addScreen.this, "Item Added :D",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(addScreen.this, "FAILED TO ADD",
                            Toast.LENGTH_SHORT).show();
                }
                startActivity(new Intent(addScreen.this, GridScreen.class));
            }
        });


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TAKE ME TO THE SIGNUP PAGE
                startActivity(new Intent(addScreen.this, GridScreen.class));
            }
        });
    }
}