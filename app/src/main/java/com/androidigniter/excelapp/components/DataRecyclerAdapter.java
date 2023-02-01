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
import com.androidigniter.excelapp.model.ProjectResponse;
import com.androidigniter.excelapp.model.TaskResponse;
import com.androidigniter.excelapp.repository.ProjectRepository;
import com.androidigniter.excelapp.repository.WTGRepository;

import java.util.ArrayList;
import java.util.List;

public class DataRecyclerAdapter extends RecyclerView.Adapter<DataRecyclerAdapter.ViewHolder> {

    private List<ProjectResponse> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    public DataRecyclerAdapter(Context context, ArrayList<ProjectResponse> data) {

        this.mInflater = LayoutInflater.from(context);

        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.activity_data_list_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ProjectResponse task = mData.get(position);
        holder.project_code.setText(String.valueOf(task.getProject_code()));
        holder.data_customer.setText(String.valueOf(task.getCustomer()));
        holder.data_country.setText(task.getCountry());
        holder.data_wtg_power.setText(task.getWtg_power());
        holder.data_windfarm.setText(task.getWindfarm_name());
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView project_code;
        TextView data_customer;
        TextView data_country;
        TextView data_wtg_power;
        TextView data_windfarm;
        ViewHolder(View itemView) {
            super(itemView);
            project_code = itemView.findViewById(R.id.data_code);
            data_customer = itemView.findViewById(R.id.data_customer);
            data_country = itemView.findViewById(R.id.data_country);
            data_wtg_power = itemView.findViewById(R.id.data_wtg_power);
            data_windfarm = itemView.findViewById(R.id.data_windfarm);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    ProjectResponse getItem(int id) {
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