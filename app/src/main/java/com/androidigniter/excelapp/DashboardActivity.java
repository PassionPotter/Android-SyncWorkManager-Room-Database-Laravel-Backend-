package com.androidigniter.excelapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.androidigniter.excelapp.data.CraneDailyStatusActivity;
import com.androidigniter.excelapp.data.CraneInefficienciesActivity;
import com.androidigniter.excelapp.data.CraneRecolocationActivity;
import com.androidigniter.excelapp.data.CraneTrazabilityActivity;
import com.androidigniter.excelapp.data.DailyStatusActivity;
import com.androidigniter.excelapp.data.DataActivity;
import com.androidigniter.excelapp.data.InefficienciesActivity;
import com.androidigniter.excelapp.data.OTs1Activity;
import com.androidigniter.excelapp.data.PersonalActivity;
import com.androidigniter.excelapp.data.ResourceActivationActivity;
import com.androidigniter.excelapp.data.TrazabilityActivity;
import com.androidigniter.excelapp.viewmodel.ProjectViewModel;
import com.androidigniter.excelapp.workmanager.SyncWorkManager;

public class DashboardActivity extends AppCompatActivity {
    private SessionHandler session;

    private ProjectViewModel projectViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dashboard);
        session = new SessionHandler(getApplicationContext());
        User user = session.getUserDetails();

        projectViewModel = ViewModelProviders.of(this).get(ProjectViewModel.class);

        projectViewModel.startSync(this);


        Button data_btn = findViewById(R.id.data_btn);
        data_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user.role.matches("Super Admin")) {
                    Intent i = new Intent(DashboardActivity.this, DataActivity.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(getApplicationContext(), "You're not authorized!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button personal_btn = findViewById(R.id.manpower_personnel_btn);
        personal_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashboardActivity.this, PersonalActivity.class);
                startActivity(i);

            }
        });

        Button crane_recolocation_btn = findViewById(R.id.crane_relocations_btn);
        crane_recolocation_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashboardActivity.this, CraneRecolocationActivity.class);
                startActivity(i);

            }
        });

        Button resource_activation_btn = findViewById(R.id.manpower_resource_activation_btn);
        resource_activation_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashboardActivity.this, ResourceActivationActivity.class);
                startActivity(i);
            }
        });

        Button daily_status_btn = findViewById(R.id.manpower_daily_status_btn);
        daily_status_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashboardActivity.this, DailyStatusActivity.class);
                startActivity(i);
            }
        });

        Button crane_daily_status_btn = findViewById(R.id.crane_daily_status_btn);
        crane_daily_status_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashboardActivity.this, CraneDailyStatusActivity.class);
                startActivity(i);
            }
        });

        Button manpower_inefficiencies_btn = findViewById(R.id.manpower_inefficiencies_btn);
        manpower_inefficiencies_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashboardActivity.this, InefficienciesActivity.class);
                startActivity(i);
            }
        });

        Button crane_inefficiencies_btn = findViewById(R.id.crane_inefficiencies_btn);
        crane_inefficiencies_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashboardActivity.this, CraneInefficienciesActivity.class);
                startActivity(i);
            }
        });
        Button trazability_btn = findViewById(R.id.manpower_trazability_btn);
        trazability_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashboardActivity.this, TrazabilityActivity.class);
                startActivity(i);
            }
        });

        Button crane_trazability_btn = findViewById(R.id.crane_trazability_btn);
        crane_trazability_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashboardActivity.this, CraneTrazabilityActivity.class);
                startActivity(i);
            }
        });

        Button ots1_btn = findViewById(R.id.ots1_btn);
        ots1_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashboardActivity.this, OTs1Activity.class);
                startActivity(i);
            }
        });

    }
}
