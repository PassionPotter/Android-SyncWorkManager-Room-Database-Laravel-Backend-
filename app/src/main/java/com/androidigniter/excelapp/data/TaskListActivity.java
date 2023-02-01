package com.androidigniter.excelapp.data;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidigniter.excelapp.R;
import com.androidigniter.excelapp.components.TaskRecyclerAdapter;
import com.androidigniter.excelapp.model.ProjectResponse;
import com.androidigniter.excelapp.model.TaskResponse;
import com.androidigniter.excelapp.viewmodel.ProjectViewModel;

import java.util.ArrayList;
import java.util.List;

public class TaskListActivity extends AppCompatActivity implements TaskRecyclerAdapter.ItemClickListener {
    TaskRecyclerAdapter adapter;

    private ProjectViewModel projectViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        projectViewModel = ViewModelProviders.of(this).get(ProjectViewModel.class);
        // data to populate the RecyclerView with
        ArrayList<TaskResponse> tasksList = new ArrayList<>(projectViewModel.getAllTasks());

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.taskListRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TaskRecyclerAdapter(this, tasksList, projectViewModel.getProjectRepository(), projectViewModel.getWtgRepository());
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        //Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }
}
