package com.androidigniter.excelapp.data;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidigniter.excelapp.R;
import com.androidigniter.excelapp.components.CraneTrazabilityRecyclerAdapter;
import com.androidigniter.excelapp.components.TrazabilityRecyclerAdapter;
import com.androidigniter.excelapp.model.CraneTrazabilityResponse;
import com.androidigniter.excelapp.model.TrazabilityResponse;
import com.androidigniter.excelapp.viewmodel.ProjectViewModel;

import java.util.ArrayList;

public class CraneTrazabilityListActivity extends AppCompatActivity implements CraneTrazabilityRecyclerAdapter.ItemClickListener {
    CraneTrazabilityRecyclerAdapter adapter;

    private ProjectViewModel projectViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crane_trazability_list);

        projectViewModel = ViewModelProviders.of(this).get(ProjectViewModel.class);
        // data to populate the RecyclerView with
        ArrayList<CraneTrazabilityResponse> tasksList = new ArrayList<>(projectViewModel.getAllCraneTrazability());

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.cranetrazabilityListRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CraneTrazabilityRecyclerAdapter(this, tasksList,  projectViewModel.getWtgRepository());
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        //Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }
}
