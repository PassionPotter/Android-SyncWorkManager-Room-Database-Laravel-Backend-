package com.androidigniter.excelapp.data;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.androidigniter.excelapp.R;
import com.androidigniter.excelapp.components.MultiSpinner;
import com.androidigniter.excelapp.model.CompanyResponse;
import com.androidigniter.excelapp.model.CraneResponse;
import com.androidigniter.excelapp.model.OTS1Response;
import com.androidigniter.excelapp.model.PhaseResponse;
import com.androidigniter.excelapp.model.ProjectResponse;
import com.androidigniter.excelapp.model.SubContractorResponse;
import com.androidigniter.excelapp.model.WTGResponse;
import com.androidigniter.excelapp.model.WTGTypeResponse;
import com.androidigniter.excelapp.viewmodel.ProjectViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.UUID;


public class OTs1Activity extends AppCompatActivity {

    private ProjectViewModel projectViewModel;
    private ProgressDialog pDialog;


    //Phase
    final List<String> phaseNames = new ArrayList<>();
    final List<Integer> phaseIDs = new ArrayList<>();
    Integer selectedPhase = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ots1_data);


        //Click Save Data button
        Button save_data_btn = findViewById(R.id.ots1_save_btn);
        save_data_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save_data();
            }
        });




        projectViewModel = ViewModelProviders.of(this).get(ProjectViewModel.class);
        //As for Company Selector
        Spinner phase =  findViewById(R.id.ots1_data_phase_input);
        for(PhaseResponse response:projectViewModel.getAllPhase()) {
            phaseNames.add(response.getName());
            phaseIDs.add(response.getId());
        }
        ArrayAdapter phaseArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, phaseNames);
        phaseArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        phase.setAdapter(phaseArrayAdapter);

        phase.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedPhase = phaseIDs.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
        //companyViewModel.getAllCompanies().observe(this, companyObserver);
    }



    private void displayLoader() {
        pDialog = new ProgressDialog(OTs1Activity.this);
        pDialog.setMessage("Saving Data.. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);

        pDialog.show();

    }

    private  boolean isEmpty(EditText edText) {
        return edText.getText().toString().trim().length() == 0;
    }
    private void save_data() {
        displayLoader();

        EditText ot_code = findViewById(R.id.ots1_data_ot_code_input);
        EditText description = findViewById(R.id.ots1_data_ot_desc_input);
        EditText main_lead = findViewById(R.id.ots1_data_main_crane_lead_time_input);
        EditText tn_lead = findViewById(R.id.ots1_data_tn_crane_lead_time_input);
        EditText auxiliary_lead = findViewById(R.id.ots1_data_auxiliary_crane_lead_time_input);

        EditText n_tech = findViewById(R.id.ots1_data_n_technician_input);

        EditText roadmap_lead = findViewById(R.id.ots1_data_roadmap_lead_time_input);


        OTS1Response newResponse = new OTS1Response();
        try {
            newResponse.setCode(ot_code.getText().toString());
            newResponse.setDescription(description.getText().toString());
            newResponse.setMain_lead_time(Double.valueOf(main_lead.getText().toString()));
            newResponse.setTn_lead_time(Double.valueOf(tn_lead.getText().toString()));
            newResponse.setAuxiliary_lead_time(Double.valueOf(auxiliary_lead.getText().toString()));
            newResponse.setN_technician(Integer.valueOf(n_tech.getText().toString()));
            newResponse.setRoadmap_lead_time(Double.valueOf(roadmap_lead.getText().toString()));
            newResponse.setStringID(UUID.randomUUID().toString());
            newResponse.setPhaseID(selectedPhase);

            projectViewModel.insertOts1(newResponse);
            Toast.makeText(getApplicationContext(), "Saved!!", Toast.LENGTH_SHORT).show();
        } catch(NumberFormatException exception) {
            Toast.makeText(getApplicationContext(), "Please confirm all the fields are entered!", Toast.LENGTH_SHORT).show();
        }
        pDialog.dismiss();
    }


}
