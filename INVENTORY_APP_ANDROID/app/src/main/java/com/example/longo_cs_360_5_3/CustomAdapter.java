package com.example.longo_cs_360_5_3;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{

    private Context context;
    Activity activity;
    private ArrayList name, price, quantity;


    CustomAdapter(Activity activity, Context context,
                  ArrayList name,
                  ArrayList price,
                  ArrayList quantity) {
        this.activity = activity;
        this.context = context;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,
                                 final int position) {

        holder.inventoryNameText.setText(String.valueOf(name.get(position)));
        holder.inventoryPriceText.setText(String.valueOf(price.get(position)));
        holder.inventoryQuanText.setText(String.valueOf(quantity.get(position)));
        holder.my_row.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // HERE WHEN WE CLICKED ON THE CUSTOM ADAPTER
                // WE PULL UP THE UPDATE/DEL ITEM SCREEN FROM HERE
                Intent intent = new Intent(context, update_del_item.class);
                intent.putExtra("name", String.valueOf(name.get(position)));
                intent.putExtra("price", String.valueOf(price.get(position)));
                intent.putExtra("quantity", String.valueOf(quantity.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView inventoryNameText, inventoryPriceText, inventoryQuanText;
        LinearLayout my_row;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            inventoryNameText = itemView.findViewById(R.id.inventoryNameText);
            inventoryPriceText = itemView.findViewById(R.id.inventoryPriceText);
            inventoryQuanText = itemView.findViewById(R.id.inventoryQuanText);
            my_row = itemView.findViewById(R.id.my_row);
        }
    }
}
