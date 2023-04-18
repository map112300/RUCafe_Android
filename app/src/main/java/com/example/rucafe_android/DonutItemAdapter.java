package com.example.rucafe_android;


import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DonutItemAdapter extends RecyclerView.Adapter<DonutItemAdapter.ItemsHolder> {

    private Context context;
    private ArrayList<DonutItem> donutItems;

    /**
     * Constructor for DonutItemHolder
     *
     * @param context
     * @param donutItems
     */
    public DonutItemAdapter(Context context, ArrayList<DonutItem> donutItems) {
        this.context = context;
        this.donutItems = donutItems;
    }


    /**
     * This method will inflate the row layout for the items in the RecyclerView
     *
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public DonutItemAdapter.ItemsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflator = LayoutInflater.from(context);
        View view = inflator.inflate(R.layout.donut_item_view, parent, false);

        return new DonutItemAdapter.ItemsHolder(view);
    }


    /**
     * Assign data values for each row according to their "position" (index) when the item becomes
     * visible on the screen.
     *
     * @param holder   the instance of ItemsHolder
     * @param position the index of the item in the list of items
     */
    @Override
    public void onBindViewHolder(@NonNull DonutItemAdapter.ItemsHolder holder, int position) {
        holder.donut_flavor.setText(donutItems.get(position).getFlavor());
        holder.donut_price.setText(String.valueOf(donutItems.get(position).getDonutPrice()));
        holder.donut_image.setImageResource(donutItems.get(position).getImage());
        holder.donut_qty.setText(String.valueOf(donutItems.get(position).getQuantity()));

    }

    /**
     * Get the number of items in the ArrayList.
     *
     * @return the number of items in the list.
     */
    @Override
    public int getItemCount() {
        return donutItems.size();
    }


    /**
     * Get the views from the row layout file, similar to the onCreate() method.
     */
    public class ItemsHolder extends RecyclerView.ViewHolder {

        private TextView donut_flavor, donut_price, donut_qty;
        private ImageView donut_image;
        private Button btn_add, btn_remove;
        private ConstraintLayout parentLayout; //row layout

        private int quantity = 0;

        public ItemsHolder(@NonNull View itemView) {
            super(itemView);
            donut_flavor = itemView.findViewById(R.id.donut_flavor);
            donut_price = itemView.findViewById(R.id.donut_price);
            donut_qty = itemView.findViewById(R.id.donut_qty);
            donut_image = itemView.findViewById(R.id.donut_image);
            btn_add = itemView.findViewById(R.id.btn_add);
            btn_remove = itemView.findViewById(R.id.btn_remove);
            parentLayout = itemView.findViewById(R.id.rowLayout);

            btn_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    quantity++;
                    donut_qty.setText(String.valueOf(quantity));
                }
            });
            btn_remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(quantity == 0) {
                        AlertDialog.Builder alert = new AlertDialog.Builder(itemView.getContext());
                        alert.setTitle("RU Cafe Error");
                        alert.setMessage("Donut Quantity Can Not Be Negative!");

                        AlertDialog dialog = alert.create();
                        dialog.show();
                    } else {
                        quantity--;
                        donut_qty.setText(String.valueOf(quantity));
                    }

                }
            });
        }
    }
}


        ////TODO IMPLEMENTATION IMPLEMENTATION
        ////TODO REUSE THIS CODE FOR ALERTS
        ///**
        // * Set the onClickListener for the button on each row.
        // * Clicking on the button will create an AlertDialog with the options of YES/NO.
        // *
        // * @param itemView
        // */
        //            btn_add.setOnClickListener(new View.OnClickListener() {
        //@Override
        //public void onClick(View view) {
        //        AlertDialog.Builder alert = new AlertDialog.Builder(itemView.getContext());
        //        alert.setTitle("Add to order");
        //        alert.setMessage(donut_flavor.getText().toString());
        //        //handle the "YES" click
        //        alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
        //public void onClick(DialogInterface dialog, int which) {
        //        Toast.makeText(itemView.getContext(),
        //        donut_flavor.getText().toString() + " added.", Toast.LENGTH_LONG).show();
        //        }
        //        //handle the "NO" click
        //        }).setNegativeButton("no", new DialogInterface.OnClickListener() {
        //public void onClick(DialogInterface dialog, int which) {
        //        Toast.makeText(itemView.getContext(),
        //        donut_flavor.getText().toString() + " not added.", Toast.LENGTH_LONG).show();
        //        }
        //        });
        //        AlertDialog dialog = alert.create();
        //        dialog.show();
        //        }
        //        });
