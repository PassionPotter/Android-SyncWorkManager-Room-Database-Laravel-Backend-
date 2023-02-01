package com.androidigniter.excelapp.data;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidigniter.excelapp.R;
import com.androidigniter.excelapp.components.TaskRecyclerAdapter;
import com.androidigniter.excelapp.components.WTGRecyclerAdapter;
import com.androidigniter.excelapp.model.TaskResponse;
import com.androidigniter.excelapp.model.WTGResponse;
import com.androidigniter.excelapp.viewmodel.ProjectViewModel;

import java.util.ArrayList;

public class WTGListActivity extends AppCompatActivity implements WTGRecyclerAdapter.ItemClickListener {
    WTGRecyclerAdapter adapter;

    private ProjectViewModel projectViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wtg_list);

        projectViewModel = ViewModelProviders.of(this).get(ProjectViewModel.class);
        // data to populate the RecyclerView with
        ArrayList<WTGResponse> tasksList = new ArrayList<>(projectViewModel.getAllWTGs());

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.WTGListRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new WTGRecyclerAdapter(this, tasksList, projectViewModel.getWtgTypeRepository());
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        //Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }
}
