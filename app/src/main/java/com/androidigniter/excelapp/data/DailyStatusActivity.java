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

import com.androidigniter.excelapp.DashboardActivity;
import com.androidigniter.excelapp.R;
import com.androidigniter.excelapp.model.CraneRecolocationResponse;
import com.androidigniter.excelapp.model.CraneResponse;
import com.androidigniter.excelapp.model.DailyStatusResponse;
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


public class DailyStatusActivity extends AppCompatActivity {


    private ProjectViewModel projectViewModel;
    ArrayList<String> projectNameList = new ArrayList<>();
    ArrayList<String> wtgNameList = new ArrayList<>();
    ArrayList<String> OTS1NameList = new ArrayList<>();

    EditText daily_date;
    EditText entry_time;
    EditText departure_time;

    private ProgressDialog pDialog;
    String project_id = "";
    String daily_status_project_id = "";
    String wtg_id = "";
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
    final TimePickerDialog.OnTimeSetListener entryTimeListener =new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            myCalendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
            myCalendar.set(Calendar.MINUTE,minute);
            updateEntryTime();
        }
    };
    final TimePickerDialog.OnTimeSetListener departureTimeListener =new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            myCalendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
            myCalendar.set(Calendar.MINUTE,minute);
            updateDepartureTime();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_status);


        projectViewModel = ViewModelProviders.of(this).get(ProjectViewModel.class);


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
        Spinner project = (Spinner) findViewById(R.id.new_task_project_code_input);
        Spinner wtg = (Spinner) findViewById(R.id.new_task_wtg_input);
        Spinner daily_project = (Spinner) findViewById(R.id.daily_status_project_code_input);
        Spinner ots1 = (Spinner) findViewById(R.id.new_task_task_input);


        ArrayAdapter wtgarrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, wtgNameList);
        wtgarrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        wtg.setAdapter(wtgarrayAdapter);

        ArrayAdapter projectArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, projectNameList);
        projectArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        project.setAdapter(projectArrayAdapter);

        daily_project.setAdapter(projectArrayAdapter);

        ArrayAdapter taskArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, OTS1NameList);
        taskArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ots1.setAdapter(taskArrayAdapter);



        //Set Spinner Listener.
        daily_project.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                daily_status_project_id = projectList.get(position).getStringID();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        project.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                project_id = projectList.get(position).getStringID();
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
        ots1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ots1_id = ots1List.get(position).getStringID();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        daily_date = findViewById(R.id.daily_status_date_input);
        daily_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(DailyStatusActivity.this, datePickerListener ,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        entry_time = findViewById(R.id.daily_status_entry_time_input);
        entry_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(DailyStatusActivity.this, entryTimeListener ,myCalendar.get(Calendar.HOUR_OF_DAY),myCalendar.get(Calendar.MINUTE), true).show();
            }
        });
        departure_time = findViewById(R.id.daily_status_departure_time_input);
        departure_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(DailyStatusActivity.this, departureTimeListener ,myCalendar.get(Calendar.HOUR_OF_DAY),myCalendar.get(Calendar.MINUTE), true).show();
            }
        });



        Button saveBtn = findViewById(R.id.task_save_btn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveData();
            }
        });

        Button dailyStatusSaveBtn = findViewById(R.id.daily_status_save_btn);
        dailyStatusSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDailyStatusData();
            }
        });

        Button task_list_btn = findViewById(R.id.task_list_btn);
        task_list_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DailyStatusActivity.this, TaskListActivity.class);
                startActivity(i);
            }
        });

    }


    private void displayLoader() {
        pDialog = new ProgressDialog(DailyStatusActivity.this);
        pDialog.setMessage("Saving Data.. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);

        pDialog.show();

    }

    private void SaveData() {
        displayLoader();
        EditText status = findViewById(R.id.new_task_status_input);
        EditText numberWorkers = findViewById(R.id.new_task_number_workers_input);
        EditText serialNumber = findViewById(R.id.new_task_serial_N_input);
        Switch shift_option = (Switch) findViewById(R.id.new_task_shift_option_input);
        EditText comments = findViewById(R.id.new_task_comment_input);

        try {
            TaskResponse newResponse = new TaskResponse();
            newResponse.setProjectID(project_id);
            newResponse.setWTGID(wtg_id);
            newResponse.setStatus(Double.valueOf(status.getText().toString()));
            newResponse.setNumber_workers(Integer.valueOf(numberWorkers.getText().toString()));
            newResponse.setSerial_number(Integer.valueOf(serialNumber.getText().toString()));
            newResponse.setShift_option(shift_option.isChecked() ? 1: 0);
            newResponse.setComments(comments.getText().toString());
            newResponse.setTaskID(ots1_id);
            projectViewModel.insertTask(newResponse);
            Toast.makeText(getApplicationContext(), "Saved!!", Toast.LENGTH_SHORT).show();

        } catch(NumberFormatException exception) {
            Toast.makeText(getApplicationContext(), "Please confirm all the fields are entered!", Toast.LENGTH_SHORT).show();
        }
        pDialog.dismiss();

    }

    private void saveDailyStatusData() {
        displayLoader();
        EditText totalEmployee = findViewById(R.id.daily_status_total_employee_input);
        try {
            DailyStatusResponse newResponse = new DailyStatusResponse();
            newResponse.setProjectID(daily_status_project_id);
            newResponse.setTotal_employee(Integer.valueOf(totalEmployee.getText().toString()));
            newResponse.setDate(daily_date.getText().toString());
            newResponse.setEntry_time(entry_time.getText().toString());
            newResponse.setDeparture_time(departure_time.getText().toString());
            projectViewModel.insertDailyStatus(newResponse);
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
    private void updateEntryTime(){
        String myFormat="HH:mm";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        entry_time.setText(dateFormat.format(myCalendar.getTime()));
    }
    private void updateDepartureTime(){
        String myFormat="HH:mm";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        departure_time.setText(dateFormat.format(myCalendar.getTime()));
    }

}
