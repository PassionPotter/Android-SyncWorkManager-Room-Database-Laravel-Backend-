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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.androidigniter.excelapp.R;
import com.androidigniter.excelapp.model.PersonelResponse;
import com.androidigniter.excelapp.model.ProjectResponse;
import com.androidigniter.excelapp.model.ResourceActivationResponse;
import com.androidigniter.excelapp.viewmodel.ProjectViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class ResourceActivationActivity extends AppCompatActivity {

    EditText arrival_date;
    EditText ready_to_work;

    final Calendar myCalendar= Calendar.getInstance();
    private ProgressDialog pDialog;
    private ProjectViewModel projectViewModel;

    ArrayList<String> projectNameList = new ArrayList<>();
    String project_id = "";

    final DatePickerDialog.OnDateSetListener datePickerListener =new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH,month);
            myCalendar.set(Calendar.DAY_OF_MONTH,day);
            updateArrivalDate();
        }
    };

    final DatePickerDialog.OnDateSetListener readyToWorkDatePickerListener =new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH,month);
            myCalendar.set(Calendar.DAY_OF_MONTH,day);
            updateReadyToWork();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource_activation);

        arrival_date = findViewById(R.id.resource_activation_arrival_date_input);
        arrival_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(ResourceActivationActivity.this, datePickerListener ,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }

        });

        ready_to_work = findViewById(R.id.resource_activation_ready_to_work_input);
        ready_to_work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(ResourceActivationActivity.this, readyToWorkDatePickerListener ,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }

        });

        projectViewModel = ViewModelProviders.of(this).get(ProjectViewModel.class);


        Button resourceActivationBtn = findViewById(R.id.btn_resource_activation_save);
        resourceActivationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Save_ResourceActivation();
            }
        });

        List<ProjectResponse> projectList = projectViewModel.getAllProjects();
        for(ProjectResponse project:projectList) {
            projectNameList.add(project.getProject_code());
        }

        Spinner project = findViewById(R.id.resource_activation_project_code_input);

        ArrayAdapter projectArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, projectNameList);
        projectArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        project.setAdapter(projectArrayAdapter);


        project.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                project_id = projectList.get(position).getStringID();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });



    }

    private void displayLoader() {
        pDialog = new ProgressDialog(ResourceActivationActivity.this);
        pDialog.setMessage("Saving Data.. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);

        pDialog.show();

    }

    private void updateArrivalDate(){
        String myFormat="MM/dd/yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        arrival_date.setText(dateFormat.format(myCalendar.getTime()));
    }

    private void updateReadyToWork(){
        String myFormat="MM/dd/yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        ready_to_work.setText(dateFormat.format(myCalendar.getTime()));
    }

    private void Save_ResourceActivation() {
        displayLoader();
        EditText description = findViewById(R.id.resource_activation_description_input);
        EditText model_name = findViewById(R.id.resource_activation_modelname_input);
        EditText company = findViewById(R.id.resource_activation_company_input);
        EditText comments = findViewById(R.id.resource_activation_comment_input);
        try {
            ResourceActivationResponse newResponse = new ResourceActivationResponse();
            newResponse.setDescription(description.getText().toString());
            newResponse.setModel_name(model_name.getText().toString());
            newResponse.setCompany(company.getText().toString());
            newResponse.setArrival_date(arrival_date.getText().toString());
            newResponse.setReady_to_work(ready_to_work.getText().toString());
            newResponse.setComments(comments.getText().toString());
            newResponse.setProjectID(project_id);
            projectViewModel.insertResourceActivation(newResponse);
            Toast.makeText(getApplicationContext(), "Saved!!", Toast.LENGTH_SHORT).show();

        } catch(NumberFormatException exception) {
            Toast.makeText(getApplicationContext(), "Please confirm all the fields are entered!", Toast.LENGTH_SHORT).show();
        }
        pDialog.dismiss();
    }

}
