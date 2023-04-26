package com.example.rucafe_android;


import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DonutAdapter extends RecyclerView.Adapter<DonutAdapter.ItemsHolder> {
    private final Context context;
    private final ArrayList<Donut> donutItems;
    private final RecyclerViewClickInterface recyclerViewClickInterface;

    /**
     * Constructor for DonutItemHolder
     *
     * @param context
     * @param donutItems
     */
    public DonutAdapter(Context context, ArrayList<Donut> donutItems, RecyclerViewClickInterface recyclerViewClickInterface) {
        this.context = context;
        this.donutItems = donutItems;
        this.recyclerViewClickInterface = recyclerViewClickInterface;
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
    public DonutAdapter.ItemsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.donut_item_view, parent, false);

        return new DonutAdapter.ItemsHolder(view);
    }

    /**
     * Assign data values for each row according to their "position" (index) when the item becomes
     * visible on the screen.
     *
     * @param holder   the instance of ItemsHolder
     * @param position the index of the item in the list of items
     */
    @Override
    public void onBindViewHolder(@NonNull DonutAdapter.ItemsHolder holder, int position) {
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
        private final TextView donut_flavor;
        private final TextView donut_price;
        private final TextView donut_qty;
        private final ImageView donut_image;

        public ItemsHolder(@NonNull View itemView) {
            super(itemView);
            donut_flavor = itemView.findViewById(R.id.donut_flavor);
            donut_price = itemView.findViewById(R.id.donut_price);
            donut_qty = itemView.findViewById(R.id.donut_qty);
            donut_image = itemView.findViewById(R.id.donut_image);
            Button btn_add = itemView.findViewById(R.id.btn_add);
            Button btn_remove = itemView.findViewById(R.id.btn_remove);

            btn_add.setOnClickListener(v -> recyclerViewClickInterface.onIncrementBTClick(getAdapterPosition()));

            btn_remove.setOnClickListener(v -> {
                if(Integer.parseInt(donut_qty.getText().toString()) == 0) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(itemView.getContext());
                    alert.setTitle("RU Cafe Error");
                    alert.setMessage("Donut Quantity Can Not Be Negative!");

                    AlertDialog dialog = alert.create();
                    dialog.show();
                } else {
                    recyclerViewClickInterface.onDecrementBTClick(getAdapterPosition());
                }
            });
        }
    }
}
