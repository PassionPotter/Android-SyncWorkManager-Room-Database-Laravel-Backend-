package com.androidigniter.excelapp.data;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.androidigniter.excelapp.R;
import com.androidigniter.excelapp.model.DailyStatusResponse;
import com.androidigniter.excelapp.model.InefficienciesResponse;
import com.androidigniter.excelapp.model.OTS1Response;
import com.androidigniter.excelapp.model.ProjectResponse;
import com.androidigniter.excelapp.model.TaskResponse;
import com.androidigniter.excelapp.model.WTGResponse;
import com.androidigniter.excelapp.viewmodel.ProjectViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class InefficienciesActivity extends AppCompatActivity {


    private ProjectViewModel projectViewModel;
    ArrayList<String> projectNameList = new ArrayList<>();
    ArrayList<String> wtgNameList = new ArrayList<>();
    ArrayList<String> OTS1NameList = new ArrayList<>();

    EditText daily_date;
    EditText partial_time;

    private ProgressDialog pDialog;
    String project_id = "";
    String wtg_id = "";
    String responsible_id = "";
    String validated_id = "";
    String cause_id = "";
    String subCause_id = "";
    String ots1_id = "";

    final Calendar myCalendar= Calendar.getInstance();
    final DatePickerDialog.OnDateSetListener datePickerListener =new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH,month);
            myCalendar.set(Calendar.DAY_OF_MONTH,day);
            updateDate();
        }
    };

    final TimePickerDialog.OnTimeSetListener partialTimeListener =new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            myCalendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
            myCalendar.set(Calendar.MINUTE,minute);
            updatePartialTime();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inefficiencies);


        projectViewModel = ViewModelProviders.of(this).get(ProjectViewModel.class);

        partial_time = findViewById(R.id.inefficiencies_partial_time_input);
        partial_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(InefficienciesActivity.this, partialTimeListener ,myCalendar.get(Calendar.HOUR_OF_DAY),myCalendar.get(Calendar.MINUTE), true).show();
            }
        });


        List<ProjectResponse> projectList = projectViewModel.getAllProjects();
        List<WTGResponse> wtgList = projectViewModel.getAllWTGs();
        List<OTS1Response> ots1List = projectViewModel.getAllOts1();

        for(ProjectResponse project:projectList) {
            projectNameList.add(project.getProject_code());
        }
        for(WTGResponse wtg:wtgList) {
            wtgNameList.add(wtg.getWtg_name());
        }
        for(OTS1Response ots1:ots1List) {
            OTS1NameList.add(ots1.getCode());
        }
        Spinner project = (Spinner) findViewById(R.id.inefficiencies_project_code_input);
        Spinner wtg = (Spinner) findViewById(R.id.inefficiencies_wtg_input);
        Spinner responsible = (Spinner) findViewById(R.id.inefficiencies_responsible_input);
        Spinner validated = (Spinner) findViewById(R.id.inefficiencies_validated_input);
        Spinner cause = (Spinner) findViewById(R.id.inefficiencies_cause_input);
        Spinner subcause = (Spinner) findViewById(R.id.inefficiencies_subcause_input);
        Spinner ots1 = (Spinner) findViewById(R.id.inefficiencies_task_input);

        ArrayAdapter wtgarrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, wtgNameList);
        wtgarrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        wtg.setAdapter(wtgarrayAdapter);

        ArrayAdapter projectArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, projectNameList);
        projectArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        project.setAdapter(projectArrayAdapter);

        ArrayAdapter taskArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, OTS1NameList);
        taskArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ots1.setAdapter(taskArrayAdapter);


        ArrayAdapter<CharSequence> responsibleAdapter = ArrayAdapter.createFromResource(this,R.array.responsible_array, android.R.layout.simple_spinner_dropdown_item);
        responsibleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        responsible.setAdapter(responsibleAdapter);

        ArrayAdapter<CharSequence> validatedAdapter = ArrayAdapter.createFromResource(this,R.array.validated_array, android.R.layout.simple_spinner_dropdown_item);
        validatedAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        validated.setAdapter(validatedAdapter);

        ArrayAdapter<CharSequence> causeAdapter = ArrayAdapter.createFromResource(this,R.array.cause_array, android.R.layout.simple_spinner_dropdown_item);
        causeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cause.setAdapter(causeAdapter);



        //Set Spinner Listener.
        project.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                project_id = projectList.get(position).getStringID();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ots1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ots1_id = ots1List.get(position).getStringID();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        wtg.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                wtg_id = wtgList.get(position).getStringID();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        responsible.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                responsible_id = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        validated.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                validated_id = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        cause.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cause_id = (String) parent.getItemAtPosition(position);
                int resourceID = R.array.subcause_PARALIZATION;
                if(cause_id.matches("ADMINISTRATION"))
                    resourceID =  R.array.subcause_ADMINISTRATION;
                if(cause_id.matches("QUALITY"))
                    resourceID =  R.array.subcause_QUALITY;
                if(cause_id.matches("SENS"))
                    resourceID =  R.array.subcause_SENSE;
                ArrayAdapter<CharSequence> causeAdapter = ArrayAdapter.createFromResource(parent.getContext(),resourceID, android.R.layout.simple_spinner_dropdown_item);
                causeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                subcause.setAdapter(causeAdapter);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        subcause.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                subCause_id = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        daily_date = findViewById(R.id.inefficiencies_date_input);
        daily_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(InefficienciesActivity.this, datePickerListener ,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });



        Button saveBtn = findViewById(R.id.inefficiencies_save_btn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveData();
            }
        });

        Button task_list_btn = findViewById(R.id.inefficiencies_list_btn);
        task_list_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(InefficienciesActivity.this, InefficienciesTaskListActivity.class);
                startActivity(i);
            }
        });

    }


    private void displayLoader() {
        pDialog = new ProgressDialog(InefficienciesActivity.this);
        pDialog.setMessage("Saving Data.. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);

        pDialog.show();

    }

    private void SaveData() {
        displayLoader();
        EditText hours = findViewById(R.id.inefficiencies_hours_input);
        EditText justification = findViewById(R.id.inefficiencies_justification_input);
        EditText numberWorkers = findViewById(R.id.inefficiencies_n_workers_input);
        Switch shift_option = findViewById(R.id.inefficiencies_shift_option_input);
        try {
            InefficienciesResponse newResponse = new InefficienciesResponse();
            newResponse.setProject(project_id);
            newResponse.setWtg(wtg_id);
            newResponse.setHours(Integer.valueOf(hours.getText().toString()));
            newResponse.setN_workers(Integer.valueOf(numberWorkers.getText().toString()));
            newResponse.setShift_option(shift_option.isChecked()?1:0);
            newResponse.setJustification(justification.getText().toString());
            newResponse.setDate(daily_date.getText().toString());
            newResponse.setPartial_time(partial_time.getText().toString());
            newResponse.setValidated(validated_id);
            newResponse.setCause(cause_id);
            newResponse.setResponsible(responsible_id);
            newResponse.setSubcause(subCause_id);
            newResponse.setTaskID(ots1_id);
            projectViewModel.insertInEfficiencies(newResponse);
            Toast.makeText(getApplicationContext(), "Saved!!", Toast.LENGTH_SHORT).show();

        } catch(NumberFormatException exception) {
            Toast.makeText(getApplicationContext(), "Please confirm all the fields are entered!", Toast.LENGTH_SHORT).show();
        }
        pDialog.dismiss();

    }



    private void updateDate() {
        String myFormat="MM/dd/yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        daily_date.setText(dateFormat.format(myCalendar.getTime()));
    }
    private void updatePartialTime(){
        String myFormat="HH:mm";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        partial_time.setText(dateFormat.format(myCalendar.getTime()));
    }
}
