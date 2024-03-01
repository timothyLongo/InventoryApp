package com.example.longo_cs_360_5_3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class update_del_item extends AppCompatActivity {

    EditText name, price, quantity;
    Button updateButton;
    Button backButton;
    Button delButton;
    String Sname, Sprice, Squantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_del_item);

        name = findViewById(R.id.itemNameText2);
        price = findViewById(R.id.itemPriceText2);
        quantity = findViewById(R.id.itemQuanText2);

        updateButton = findViewById(R.id.updateItemButton);
        backButton = findViewById(R.id.backButton);
        delButton = findViewById(R.id.delItemButton);

        getAndSetIntentData();

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db = new DBHelper(update_del_item.this);
                Sname = name.getText().toString().trim();
                Sprice = price.getText().toString().trim();
                Squantity = quantity.getText().toString().trim();
                db.updateData(Sname, Sprice, Squantity);

                // NOW IF THE UPDATED QUANTITY IS < 6 FOR EXAMPLE
                // HERE WE ARE DECLARING 5 AS LOW
                // WE SEND TEXT LETTING THE USER KNOW
                // THE ITEM IS LOW
                // AS WELL AS THE LITTLE BUBBLE TEXT
                int i = Integer.parseInt(quantity.getText().toString());
                if (i < 6) {
                    // SO ONLY IF THE USER PERMITS THE APP TO SEND A TEXT
                    // DOES THE APP SEND A TEXT
                    if (ContextCompat.checkSelfPermission(update_del_item.this,
                            Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                        // SEND A TEXT HERE
                        SmsManager smsManger = SmsManager.getDefault();
                        smsManger.sendTextMessage(GLOBAL.PHONE_NUM, null,
                                Sname + "is running low", null, null);
                    }

                    Toast.makeText(update_del_item.this,
                            "LOW", Toast.LENGTH_SHORT).show();
                }



                startActivity(new Intent(update_del_item.this, GridScreen.class));
            }
        });

        delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db = new DBHelper(update_del_item.this);
                db.delData(Sname);
                startActivity(new Intent(update_del_item.this, GridScreen.class));
            }
        });


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TAKE ME BACK
                startActivity(new Intent(update_del_item.this, GridScreen.class));
            }
        });

    }

    void getAndSetIntentData() {
        if (getIntent().hasExtra("name") && getIntent().hasExtra("price")
        && getIntent().hasExtra("quantity")) {
            // GETTING DATA FROM INTENT
            Sname = getIntent().getStringExtra("name");
            Sprice = getIntent().getStringExtra("price");
            Squantity = getIntent().getStringExtra("quantity");

            // SETTING INTENT DATA
            name.setText(Sname);
            price.setText(Sprice);
            quantity.setText(Squantity);
        } else {
            Toast.makeText(this, "EMPTY", Toast.LENGTH_SHORT).show();
        }
    }
}