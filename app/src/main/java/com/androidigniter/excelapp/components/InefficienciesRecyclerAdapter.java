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
import com.androidigniter.excelapp.model.InefficienciesResponse;
import com.androidigniter.excelapp.model.TaskResponse;
import com.androidigniter.excelapp.repository.ProjectRepository;
import com.androidigniter.excelapp.repository.WTGRepository;

import java.util.ArrayList;
import java.util.List;

public class InefficienciesRecyclerAdapter extends RecyclerView.Adapter<InefficienciesRecyclerAdapter.ViewHolder> {

    private List<InefficienciesResponse> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    private ProjectRepository projectRepository;
    private WTGRepository wtgRepository;
    // data is passed into the constructor
    public InefficienciesRecyclerAdapter(Context context, ArrayList<InefficienciesResponse> data, ProjectRepository projectRepo, WTGRepository wtgRepo) {

        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        projectRepository = projectRepo;
        wtgRepository = wtgRepo;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.activity_inefficiencies_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        InefficienciesResponse task = mData.get(position);
        holder.task_name.setText(projectRepository.getProjectByString(task.getProject()).getProject_code());
        holder.wtg_name.setText(wtgRepository.getWTGByString(task.getWtg()).getWtg_name());
        holder.hours.setText(String.valueOf(task.getHours()));
        holder.cause.setText(String.valueOf(task.getCause()));
        holder.subcause.setText(task.getSubcause());
        holder.justification.setText(task.getJustification());
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView task_name;
        TextView wtg_name;
        TextView hours;
        TextView cause;
        TextView subcause;
        TextView justification;
        Button openBtn;
        ViewHolder(View itemView) {
            super(itemView);
            task_name = itemView.findViewById(R.id.inefficiencies_item_task_name);
            wtg_name = itemView.findViewById(R.id.inefficiencies_item_wtg_name);
            hours = itemView.findViewById(R.id.inefficiencies_item_hour);
            cause = itemView.findViewById(R.id.inefficiencies_item_cause);
            subcause = itemView.findViewById(R.id.inefficiencies_item_subcause);
            justification = itemView.findViewById(R.id.inefficiencies_item_justification);
            openBtn = itemView.findViewById(R.id.btn_inefficiencies_open);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    InefficienciesResponse getItem(int id) {
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