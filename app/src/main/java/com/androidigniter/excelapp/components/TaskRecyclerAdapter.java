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
import com.androidigniter.excelapp.repository.ProjectRepository;
import com.androidigniter.excelapp.repository.WTGRepository;

import java.util.ArrayList;
import java.util.List;

public class TaskRecyclerAdapter extends RecyclerView.Adapter<TaskRecyclerAdapter.ViewHolder> {

    private List<TaskResponse> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    private ProjectRepository projectRepository;
    private WTGRepository wtgRepository;
    // data is passed into the constructor
    public TaskRecyclerAdapter(Context context, ArrayList<TaskResponse> data,ProjectRepository projectRepo, WTGRepository wtgRepo) {

        this.mInflater = LayoutInflater.from(context);

        this.mData = data;
        projectRepository = projectRepo;
        wtgRepository = wtgRepo;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.activity_task_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TaskResponse task = mData.get(position);
        holder.task_name.setText(projectRepository.getProjectByString(task.getProjectID()).getProject_code());
        holder.wtg_name.setText(wtgRepository.getWTGByString(task.getWTGID()).getWtg_name());
        holder.number_employee.setText(String.valueOf(task.getNumber_workers()));
        holder.task_status.setText(String.valueOf(task.getStatus()));
        holder.task_comment.setText(task.getComments());
        holder.shift_otption.setChecked(task.getShift_option()==1?true:false);
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
        TextView number_employee;
        TextView task_status;
        TextView task_comment;
        CheckBox shift_otption;
        Button openBtn;
        ViewHolder(View itemView) {
            super(itemView);
            task_name = itemView.findViewById(R.id.task_name);
            wtg_name = itemView.findViewById(R.id.wtg_name);
            number_employee = itemView.findViewById(R.id.task_number_workers);
            task_status = itemView.findViewById(R.id.task_status);
            task_comment = itemView.findViewById(R.id.task_comment);
            shift_otption = itemView.findViewById(R.id.task_shift_option);
            openBtn = itemView.findViewById(R.id.btn_task_open);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    TaskResponse getItem(int id) {
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