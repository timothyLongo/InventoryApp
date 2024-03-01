package com.example.longo_cs_360_5_3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Locale;

public class GridScreen extends AppCompatActivity {

    DBHelper db;
    ArrayList<String> name, price, quantity;
    CustomAdapter customAdapter;

    RecyclerView rec;
    FloatingActionButton addButton;

    Button logOutButton;
    Button editButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_screen);


        rec = findViewById(R.id.theInventoryRecView);

        addButton = findViewById(R.id.addButton);

        logOutButton = findViewById(R.id.logOutButton);

        editButton = findViewById(R.id.editPhoneButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TAKE ME TO THE SIGNUP PAGE
                startActivity(new Intent(GridScreen.this, addScreen.class));
            }
        });

        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TAKE ME TO THE HOME PAGE
                startActivity(new Intent(GridScreen.this, MainActivity.class));
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TAKE ME TO THE PHONE PAGE
                startActivity(new Intent(GridScreen.this, EnterPhoneNum.class));
            }
        });


        db = new DBHelper(GridScreen.this);
        name = new ArrayList<>();
        price = new ArrayList<>();
        quantity = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(GridScreen.this, this,
                name, price, quantity);
        rec.setAdapter(customAdapter);
        rec.setLayoutManager(new LinearLayoutManager(GridScreen.this));

    }

    void storeDataInArrays() {
        Cursor cursor = db.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "EMPTY", Toast.LENGTH_SHORT).show();
        }
        else {
            while (cursor.moveToNext()) {
                name.add(cursor.getString(0));
                price.add(cursor.getString(1));
                quantity.add(cursor.getString(2));
            }
        }
    }
}