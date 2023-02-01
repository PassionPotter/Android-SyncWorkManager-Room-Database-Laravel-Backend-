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

import com.androidigniter.excelapp.R;
import com.androidigniter.excelapp.model.PersonelResponse;
import com.androidigniter.excelapp.model.ProjectResponse;
import com.androidigniter.excelapp.viewmodel.ProjectViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class PersonalActivity extends AppCompatActivity {

    EditText personal_date;
    ArrayList<String> projectNameList = new ArrayList<>();

    final Calendar myCalendar= Calendar.getInstance();
    final DatePickerDialog.OnDateSetListener datePickerListener =new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH,month);
            myCalendar.set(Calendar.DAY_OF_MONTH,day);
            updatePersonalDate();
        }
    };
    private ProgressDialog pDialog;

    private ProjectViewModel projectViewModel;
    String project_id = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);

        projectViewModel = ViewModelProviders.of(this).get(ProjectViewModel.class);
        List<ProjectResponse> projectList = projectViewModel.getAllProjects();
        for(ProjectResponse project:projectList) {
            projectNameList.add(project.getProject_code());
        }

        Spinner project = findViewById(R.id.personel_status_project_code_input);

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


        personal_date = findViewById(R.id.personal_date_input);
        personal_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(PersonalActivity.this, datePickerListener ,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }

        });
        projectViewModel = ViewModelProviders.of(this).get(ProjectViewModel.class);


        Button personelSaveBtn = findViewById(R.id.btn_personal_save);
        personelSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Save_Personel_Data();
            }
        });



    }

    private void updatePersonalDate(){
        String myFormat="MM/dd/yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        personal_date.setText(dateFormat.format(myCalendar.getTime()));
    }
    private void displayLoader() {
        pDialog = new ProgressDialog(PersonalActivity.this);
        pDialog.setMessage("Saving Data.. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);

        pDialog.show();

    }

    private void Save_Personel_Data() {
        displayLoader();
        EditText week = findViewById(R.id.personal_week_input);
        EditText n_workers_day = findViewById(R.id.peronsal_n_workers_day_input);
        EditText n_workers_night = findViewById(R.id.personal_n_workers_night_input);

        try {
            PersonelResponse newResponse = new PersonelResponse();
            newResponse.setDate(personal_date.getText().toString());
            newResponse.setWeek(Integer.valueOf(week.getText().toString()));
            newResponse.setN_workers_day(Integer.valueOf(n_workers_day.getText().toString()));
            newResponse.setN_workers_night(Integer.valueOf(n_workers_night.getText().toString()));
            newResponse.setProjectID(project_id);
            projectViewModel.insertPersonel(newResponse);
            Toast.makeText(getApplicationContext(), "Saved!!", Toast.LENGTH_SHORT).show();

        } catch(NumberFormatException exception) {
            Toast.makeText(getApplicationContext(), "Please confirm all the fields are entered!", Toast.LENGTH_SHORT).show();
        }
        pDialog.dismiss();

    }

}
