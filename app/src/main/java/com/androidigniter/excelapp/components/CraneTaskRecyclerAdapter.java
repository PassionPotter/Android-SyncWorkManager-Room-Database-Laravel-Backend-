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
import com.androidigniter.excelapp.model.CraneTaskResponse;
import com.androidigniter.excelapp.model.TaskResponse;
import com.androidigniter.excelapp.repository.CraneRepository;
import com.androidigniter.excelapp.repository.ProjectRepository;
import com.androidigniter.excelapp.repository.WTGRepository;

import java.util.ArrayList;
import java.util.List;

public class CraneTaskRecyclerAdapter extends RecyclerView.Adapter<CraneTaskRecyclerAdapter.ViewHolder> {

    private List<CraneTaskResponse> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    private ProjectRepository projectRepository;
    private WTGRepository wtgRepository;
    private CraneRepository craneRepository;
    // data is passed into the constructor
    public CraneTaskRecyclerAdapter(Context context, ArrayList<CraneTaskResponse> data, ProjectRepository projectRepo, WTGRepository wtgRepo, CraneRepository craneRepository) {

        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        projectRepository = projectRepo;
        wtgRepository = wtgRepo;
        this.craneRepository = craneRepository;
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
        CraneTaskResponse task = mData.get(position);
        holder.task_name.setText(projectRepository.getProjectByString(task.getProjectID()).getProject_code());
        holder.wtg_name.setText(wtgRepository.getWTGByString(task.getWTGID()).getWtg_name());
        holder.number_employee.setText(craneRepository.getCraneByString(task.getCraneID()).getCrane_type());
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
    CraneTaskResponse getItem(int id) {
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