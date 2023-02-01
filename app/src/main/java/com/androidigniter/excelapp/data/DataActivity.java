package com.androidigniter.excelapp.data;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.androidigniter.excelapp.DashboardActivity;
import com.androidigniter.excelapp.MySingleton;
import com.androidigniter.excelapp.R;
import com.androidigniter.excelapp.components.MultiSpinner;
import com.androidigniter.excelapp.model.CompanyResponse;
import com.androidigniter.excelapp.model.CraneResponse;
import com.androidigniter.excelapp.model.ProjectResponse;
import com.androidigniter.excelapp.model.SubContractorResponse;
import com.androidigniter.excelapp.model.WTGResponse;
import com.androidigniter.excelapp.model.WTGTypeResponse;
import com.androidigniter.excelapp.viewmodel.ProjectViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.StringJoiner;
import java.util.UUID;


public class DataActivity extends AppCompatActivity {

    private ProjectViewModel projectViewModel;
    final Calendar myCalendar= Calendar.getInstance();
    EditText crane_arrival_date;
    private ProgressDialog pDialog;


    //Company
    final List<String> companyNames = new ArrayList<>();
    final List<Integer> companyIDs = new ArrayList<>();
    List<Integer> selectedCompany = new ArrayList<>();

    //WTGTYpe
    final List<String> wtgTypeNames = new ArrayList<>();
    final List<Integer> wtgTypeIDs = new ArrayList<>();
    List<Integer> selectedWTGType = new ArrayList<>();
    //SubContractor
    final List<String> subcontractorames = new ArrayList<>();
    final List<Integer> subcontractorIDs = new ArrayList<>();
    List<Integer> selectedSubContractor = new ArrayList<>();

    String wtg_single_type = "";
    Integer selected_n_wtg = 1;

    final DatePickerDialog.OnDateSetListener arrivalDatePickerListener =new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH,month);
            myCalendar.set(Calendar.DAY_OF_MONTH,day);
            updateCraneArrivalDate();
        }
    };








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);



        //Click Crane_arrival_date input box.
        crane_arrival_date = findViewById(R.id.crane_input_arrival_date);
        crane_arrival_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(DataActivity.this, arrivalDatePickerListener ,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }

        });

        //Click Save Data button
        Button save_data_btn = findViewById(R.id.btn_data_save);
        save_data_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save_data();
            }
        });

        //Click Save Data button
        Button save_crane_btn = findViewById(R.id.btn_crane_save);
        save_crane_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save_crane();
            }
        });

        //Click Save Data button
        Button save_wtg_btn = findViewById(R.id.btn_wtg_save);
        save_wtg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save_wtg();
            }
        });
        Button data_list_btn = findViewById(R.id.btn_data_list);
        data_list_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DataActivity.this, DataListActivity.class);
                startActivity(i);
            }
        });
        Button crane_list_btn = findViewById(R.id.btn_crane_list);
        crane_list_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DataActivity.this, CraneListActivity.class);
                startActivity(i);
            }
        });
        Button wtg_list_btn = findViewById(R.id.btn_wtg_list);
        wtg_list_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DataActivity.this, WTGListActivity.class);
                startActivity(i);
            }
        });



        projectViewModel = ViewModelProviders.of(this).get(ProjectViewModel.class);
        //As for Company Selector
        final MultiSpinner multiSpinner = (MultiSpinner) findViewById(R.id.data_spinner_crane_company);
        for(CompanyResponse response:projectViewModel.getAllCompanies()) {
            companyNames.add(response.getName());
            companyIDs.add(response.getId());
        }
        multiSpinner.setItems(companyNames, "", new MultiSpinner.MultiSpinnerListener() {
            @Override
            public void onItemsSelected(boolean[] selected) {
                selectedCompany.clear();
                for(int i = 0; i < selected.length; i ++) {
                    if(selected[i] == true) {
                        selectedCompany.add(companyIDs.get(i));
                    }
                }
            }
        });

        //As for WTG Type Selector
        final MultiSpinner wtgTypeSpinner = (MultiSpinner) findViewById(R.id.data_spinner_wtg_type);

        for(WTGTypeResponse response:projectViewModel.getAllWTGType()) {
            wtgTypeNames.add(response.getName());
            wtgTypeIDs.add(response.getId());
        }
        wtgTypeSpinner.setItems(wtgTypeNames, "", new MultiSpinner.MultiSpinnerListener() {
            @Override
            public void onItemsSelected(boolean[] selected) {
                selectedWTGType.clear();
                for(int i = 0; i < selected.length; i ++) {
                    if(selected[i] == true) {

                        selectedWTGType.add(wtgTypeIDs.get(i));
                    }
                }
            }
        });

        //As for SubContractor Selector
        final MultiSpinner subContractorSpinner = (MultiSpinner) findViewById(R.id.data_spinner_assembly_subcontractor);

        for(SubContractorResponse response:projectViewModel.getAllsubContractor()) {
            subcontractorames.add(response.getName());
            subcontractorIDs.add(response.getId());
        }
        subContractorSpinner.setItems(subcontractorames, "", new MultiSpinner.MultiSpinnerListener() {
            @Override
            public void onItemsSelected(boolean[] selected) {
                selectedSubContractor.clear();
                for(int i = 0; i < selected.length; i ++) {
                    if(selected[i] == true) {
                        selectedSubContractor.add(subcontractorIDs.get(i));
                    }
                }
            }
        });

        Spinner wtgTypeSingleSpinner = findViewById(R.id.wtg_input_wtg_type);

        ArrayAdapter wtgTypearrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, wtgTypeNames);
        wtgTypearrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        wtgTypeSingleSpinner.setAdapter(wtgTypearrayAdapter);

        wtgTypeSingleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                wtg_single_type = wtgTypeNames.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        Spinner n_wtgs = findViewById(R.id.data_input_n_wtgs);
        List<Integer> wtgArray = new ArrayList<>();
        for(int i = 1; i <= 15; i ++) wtgArray.add(i);
        ArrayAdapter wtgArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, wtgArray);
        wtgArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        n_wtgs.setAdapter(wtgArrayAdapter);

        n_wtgs.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected_n_wtg = wtgArray.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
        //companyViewModel.getAllCompanies().observe(this, companyObserver);
    }


    private void updateCraneArrivalDate(){
        String myFormat="MM/dd/yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        crane_arrival_date.setText(dateFormat.format(myCalendar.getTime()));
    }

    private void displayLoader() {
        pDialog = new ProgressDialog(DataActivity.this);
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

        EditText project_code = findViewById(R.id.data_input_project_code);
        EditText windfarm_name = findViewById(R.id.data_input_windfarm_name);
        EditText country = findViewById(R.id.data_edit_country);
        EditText customer = findViewById(R.id.data_input_customer);
        EditText wtg_power = findViewById(R.id.data_input_wtg_power);

        EditText blade_length = findViewById(R.id.data_input_blade_length);
        EditText blade_manufacture = findViewById(R.id.data_input_blade_manufacture);
        EditText tower_height = findViewById(R.id.data_input_tower_height);
        EditText n_tower_sections = findViewById(R.id.data_input_tower_sections);
        EditText pre_assembly_sections = findViewById(R.id.data_input_preassembly_sections);
        EditText special_nacelle_beacon = findViewById(R.id.data_input_wtg_special_nacelle);
        EditText special_tower_beacon = findViewById(R.id.data_input_wtg_special_tower);

        ProjectResponse newResponse = new ProjectResponse();
        try {
            newResponse.setProject_code(project_code.getText().toString());
            newResponse.setWindfarm_name(windfarm_name.getText().toString());
            newResponse.setCountry(country.getText().toString());
            newResponse.setCustomer(customer.getText().toString());
            newResponse.setWtg_power(wtg_power.getText().toString());
            newResponse.setN_wtgs(selected_n_wtg);
            newResponse.setBlade_length(Double.valueOf(blade_length.getText().toString()));
            newResponse.setBlade_manufacture(blade_manufacture.getText().toString());
            newResponse.setTower_height(Double.valueOf(tower_height.getText().toString()));
            newResponse.setN_tower_section(Integer.valueOf(n_tower_sections.getText().toString()));
            newResponse.setPre_assembly_section(Integer.valueOf(pre_assembly_sections.getText().toString()));
            newResponse.setN_wtg_special_nacelle_beacon(Integer.valueOf(special_nacelle_beacon.getText().toString()));
            newResponse.setN_wtg_special_tower_beacon(Integer.valueOf(special_tower_beacon.getText().toString()));

            newResponse.setStringID(UUID.randomUUID().toString());
            String builder = "";
            for(Integer selected:selectedCompany) {
                builder += selected.toString() + ",";
            }

            newResponse.setCrane_company(builder);
            builder = "";
            for(Integer selected:selectedSubContractor) {
                builder += selected.toString() + ",";
            }

            newResponse.setAssembly_subcontractor(builder);
            builder = "";
            for(Integer selected:selectedWTGType) {
                builder += selected.toString() + ",";
            }
            newResponse.setWtg_type(builder);
            projectViewModel.insert(newResponse);
            Toast.makeText(getApplicationContext(), "Saved!!", Toast.LENGTH_SHORT).show();
        } catch(NumberFormatException exception) {
            Toast.makeText(getApplicationContext(), "Please confirm all the fields are entered!", Toast.LENGTH_SHORT).show();
        }
        pDialog.dismiss();
    }


    private void save_crane() {
        displayLoader();

        EditText resource = findViewById(R.id.crane_input_resource);
        EditText crane_type = findViewById(R.id.crane_input_crane_type);
        EditText mob_demob = findViewById(R.id.crane_edit_mob_demob);
        EditText arrival_date = findViewById(R.id.crane_input_arrival_date);
        EditText ready = findViewById(R.id.crane_input_ready);
        EditText demob = findViewById(R.id.crane_edit_demob);

        CraneResponse newResponse = new CraneResponse();
        try {
            if(isEmpty(resource) ||  isEmpty(crane_type) || isEmpty(mob_demob) ||  isEmpty(arrival_date) || isEmpty(ready) ||  isEmpty(demob) ) {
                Toast.makeText(getApplicationContext(), "Please confirm all the fields are entered!", Toast.LENGTH_SHORT).show();
                pDialog.dismiss();
                return;
            }
            newResponse.setResource(resource.getText().toString());
            newResponse.setCrane_type(crane_type.getText().toString());
            newResponse.setMob_demob(mob_demob.getText().toString());
            newResponse.setArrival_date(arrival_date.getText().toString());
            newResponse.setReady(ready.getText().toString());
            newResponse.setDemob(demob.getText().toString());
            newResponse.setStringID(UUID.randomUUID().toString());
            projectViewModel.insertCrane(newResponse);

            Toast.makeText(getApplicationContext(), "Saved!!", Toast.LENGTH_SHORT).show();
        } catch(NumberFormatException exception) {
            Toast.makeText(getApplicationContext(), "Please confirm all the fields are entered!", Toast.LENGTH_SHORT).show();
        }
        pDialog.dismiss();
    }

    private void save_wtg() {
        displayLoader();

        EditText wtg_name = findViewById(R.id.wtg_input_wtg_name);
        //EditText wtg_type = findViewById(R.id.wtg_input_wtg_type);
        EditText assembly_secuence = findViewById(R.id.wtg_edit_assembly_secuence);
        Switch wtg_switch_special_nacelle_beacon = findViewById(R.id.wtg_switch_special_nacelle_beacon);
        Switch wtg_switch_special_tower_beacon = findViewById(R.id.wtg_switch_special_tower_beacon);


        WTGResponse newResponse = new WTGResponse();
        try {

            if(isEmpty(wtg_name) ||  isEmpty(assembly_secuence) || wtg_single_type.trim().length() == 0) {
                Toast.makeText(getApplicationContext(), "Please confirm all the fields are entered!", Toast.LENGTH_SHORT).show();
                pDialog.dismiss();
                return;
            }
            newResponse.setWtg_name(wtg_name.getText().toString());
            newResponse.setWtg_type(wtg_single_type);
            newResponse.setAssembly_secuence(assembly_secuence.getText().toString());
            newResponse.setSpecial_nacelle_beacon(wtg_switch_special_nacelle_beacon.isChecked()?1:0);
            newResponse.setSpecial_tower_beacon(wtg_switch_special_tower_beacon.isChecked()?1:0);
            newResponse.setStringID(UUID.randomUUID().toString());
            projectViewModel.insertWTG(newResponse);

            Toast.makeText(getApplicationContext(), "Saved!!", Toast.LENGTH_SHORT).show();
        } catch(NumberFormatException exception) {
            Toast.makeText(getApplicationContext(), "Please confirm all the fields are entered!", Toast.LENGTH_SHORT).show();
        }
        pDialog.dismiss();
    }
}
