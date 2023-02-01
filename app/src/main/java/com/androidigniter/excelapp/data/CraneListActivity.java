package com.androidigniter.excelapp.data;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidigniter.excelapp.R;
import com.androidigniter.excelapp.components.CraneRecyclerAdapter;
import com.androidigniter.excelapp.components.TaskRecyclerAdapter;
import com.androidigniter.excelapp.model.CraneResponse;
import com.androidigniter.excelapp.model.TaskResponse;
import com.androidigniter.excelapp.viewmodel.ProjectViewModel;

import java.util.ArrayList;

public class CraneListActivity extends AppCompatActivity implements CraneRecyclerAdapter.ItemClickListener {
    CraneRecyclerAdapter adapter;

    private ProjectViewModel projectViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crane_list);

        projectViewModel = ViewModelProviders.of(this).get(ProjectViewModel.class);
        // data to populate the RecyclerView with
        ArrayList<CraneResponse> craneList = new ArrayList<>(projectViewModel.getAllCranes());
        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.CraneListRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CraneRecyclerAdapter(this, craneList);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        //Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }
}
