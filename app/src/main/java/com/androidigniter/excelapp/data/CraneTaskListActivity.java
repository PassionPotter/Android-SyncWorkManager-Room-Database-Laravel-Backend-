package com.androidigniter.excelapp.data;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidigniter.excelapp.R;
import com.androidigniter.excelapp.components.CraneTaskRecyclerAdapter;
import com.androidigniter.excelapp.components.TaskRecyclerAdapter;
import com.androidigniter.excelapp.model.CraneTaskResponse;
import com.androidigniter.excelapp.model.TaskResponse;
import com.androidigniter.excelapp.viewmodel.ProjectViewModel;

import java.util.ArrayList;

public class CraneTaskListActivity extends AppCompatActivity implements CraneTaskRecyclerAdapter.ItemClickListener {
    CraneTaskRecyclerAdapter adapter;

    private ProjectViewModel projectViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crane_task_list);

        projectViewModel = ViewModelProviders.of(this).get(ProjectViewModel.class);
        // data to populate the RecyclerView with
        ArrayList<CraneTaskResponse> tasksList = new ArrayList<>(projectViewModel.getAllCraneTasks());

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.craneTaskListRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CraneTaskRecyclerAdapter(this, tasksList, projectViewModel.getProjectRepository(), projectViewModel.getWtgRepository(), projectViewModel.getCraneRepository());
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        //Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }
}
