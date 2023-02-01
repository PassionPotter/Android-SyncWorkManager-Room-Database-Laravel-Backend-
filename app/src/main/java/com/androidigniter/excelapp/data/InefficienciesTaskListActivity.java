package com.androidigniter.excelapp.data;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidigniter.excelapp.R;
import com.androidigniter.excelapp.components.InefficienciesRecyclerAdapter;
import com.androidigniter.excelapp.components.TaskRecyclerAdapter;
import com.androidigniter.excelapp.model.InefficienciesResponse;
import com.androidigniter.excelapp.model.TaskResponse;
import com.androidigniter.excelapp.viewmodel.ProjectViewModel;

import java.util.ArrayList;

public class InefficienciesTaskListActivity extends AppCompatActivity implements InefficienciesRecyclerAdapter.ItemClickListener {
    InefficienciesRecyclerAdapter adapter;

    private ProjectViewModel projectViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inefficiencies_list);

        projectViewModel = ViewModelProviders.of(this).get(ProjectViewModel.class);
        // data to populate the RecyclerView with
        ArrayList<InefficienciesResponse> tasksList = new ArrayList<>(projectViewModel.getAllInefficiencies());

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.inefficienciesListRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new InefficienciesRecyclerAdapter(this, tasksList, projectViewModel.getProjectRepository(), projectViewModel.getWtgRepository());
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        //Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }
}
