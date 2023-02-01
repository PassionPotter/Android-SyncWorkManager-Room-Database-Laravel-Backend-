package com.androidigniter.excelapp.components;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.androidigniter.excelapp.R;
import com.androidigniter.excelapp.model.CraneTrazabilityResponse;
import com.androidigniter.excelapp.model.TrazabilityResponse;
import com.androidigniter.excelapp.repository.WTGRepository;

import java.util.ArrayList;
import java.util.List;

public class CraneTrazabilityRecyclerAdapter extends RecyclerView.Adapter<CraneTrazabilityRecyclerAdapter.ViewHolder> {

    private List<CraneTrazabilityResponse> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    private WTGRepository wtgRepository;
    // data is passed into the constructor
    public CraneTrazabilityRecyclerAdapter(Context context, ArrayList<CraneTrazabilityResponse> data, WTGRepository wtgRepo) {

        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        wtgRepository = wtgRepo;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.activity_trazability_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CraneTrazabilityResponse task = mData.get(position);
        holder.wtg_name.setText(wtgRepository.getWTGByString(task.getWtg()).getWtg_name());
        holder.component_name.setText(task.getComponent_type());
        holder.serial_number.setText(String.valueOf(task.getSerial_number()));
        holder.comments.setText(task.getComments());
        byte[] decodedString = Base64.decode(task.getPhoto(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        holder.photo.setImageBitmap(decodedByte);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView wtg_name;
        TextView component_name;
        TextView serial_number;
        ImageView photo;
        TextView comments;
        ViewHolder(View itemView) {
            super(itemView);
            wtg_name = itemView.findViewById(R.id.trazability_item_wtg_name);
            component_name = itemView.findViewById(R.id.trazability_item_component_name);

            serial_number = itemView.findViewById(R.id.trazability_item_serial_number);
            photo = itemView.findViewById(R.id.trazability_item_photo);
            comments = itemView.findViewById(R.id.trazability_item_comment);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    CraneTrazabilityResponse getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}