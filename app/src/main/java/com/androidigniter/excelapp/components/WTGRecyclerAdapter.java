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
import com.androidigniter.excelapp.model.TaskResponse;
import com.androidigniter.excelapp.model.WTGResponse;
import com.androidigniter.excelapp.repository.ProjectRepository;
import com.androidigniter.excelapp.repository.WTGRepository;
import com.androidigniter.excelapp.repository.WTGTypeRepository;

import java.util.ArrayList;
import java.util.List;

public class WTGRecyclerAdapter extends RecyclerView.Adapter<WTGRecyclerAdapter.ViewHolder> {

    private List<WTGResponse> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    private WTGTypeRepository typeRepository;
    // data is passed into the constructor
    public WTGRecyclerAdapter(Context context, ArrayList<WTGResponse> data, WTGTypeRepository repo) {

        this.mInflater = LayoutInflater.from(context);

        this.mData = data;
        typeRepository = repo;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.activity_wtg_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        WTGResponse task = mData.get(position);
        holder.wtg_item_name.setText(task.getWtg_name());
        holder.wtg_item_type.setText(task.getWtg_type());
        holder.wtg_item_assembly_secuence.setText(task.getAssembly_secuence());
        holder.wtg_item_nacelle.setChecked(task.getSpecial_nacelle_beacon()==1?true:false);
        holder.wtg_item_tower.setChecked(task.getSpecial_tower_beacon()==1?true:false);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView wtg_item_name;
        TextView wtg_item_type;
        TextView wtg_item_assembly_secuence;
        CheckBox wtg_item_nacelle;
        CheckBox wtg_item_tower;
        ViewHolder(View itemView) {
            super(itemView);
            wtg_item_name = itemView.findViewById(R.id.wtg_item_name);
            wtg_item_type = itemView.findViewById(R.id.wtg_item_type);
            wtg_item_assembly_secuence = itemView.findViewById(R.id.wtg_item_assembly_secuence);
            wtg_item_nacelle = itemView.findViewById(R.id.wtg_item_nacelle);
            wtg_item_tower = itemView.findViewById(R.id.wtg_item_tower);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    WTGResponse getItem(int id) {
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