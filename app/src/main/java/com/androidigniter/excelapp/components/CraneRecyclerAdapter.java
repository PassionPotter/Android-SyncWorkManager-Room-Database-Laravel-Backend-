package com.androidigniter.excelapp.components;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.androidigniter.excelapp.R;
import com.androidigniter.excelapp.model.CraneResponse;
import com.androidigniter.excelapp.model.TaskResponse;
import com.androidigniter.excelapp.repository.ProjectRepository;
import com.androidigniter.excelapp.repository.WTGRepository;

import java.util.ArrayList;
import java.util.List;

public class CraneRecyclerAdapter extends RecyclerView.Adapter<CraneRecyclerAdapter.ViewHolder> {

    private List<CraneResponse> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    public CraneRecyclerAdapter(Context context, ArrayList<CraneResponse> data) {

        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.activity_crane_list_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CraneResponse task = mData.get(position);
        holder.crane_item_resource.setText(task.getResource());
        holder.crane_item_type.setText(task.getCrane_type());
        holder.crane_item_mob_demob.setText(task.getMob_demob());
        holder.crane_item_ready.setText(task.getReady());
        holder.crane_item_arrival_date.setText(task.getArrival_date());
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView crane_item_resource;
        TextView crane_item_type;
        TextView crane_item_mob_demob;
        TextView crane_item_ready;
        TextView crane_item_arrival_date;
        ViewHolder(View itemView) {
            super(itemView);
            crane_item_resource = itemView.findViewById(R.id.crane_item_resource);
            crane_item_type = itemView.findViewById(R.id.crane_item_type);
            crane_item_mob_demob = itemView.findViewById(R.id.crane_item_mob_demob);
            crane_item_ready = itemView.findViewById(R.id.crane_item_ready);
            crane_item_arrival_date = itemView.findViewById(R.id.crane_item_arrival_date);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    CraneResponse getItem(int id) {
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