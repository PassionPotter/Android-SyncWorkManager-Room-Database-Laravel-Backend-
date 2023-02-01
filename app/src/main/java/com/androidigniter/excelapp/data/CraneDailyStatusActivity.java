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
import com.androidigniter.excelapp.components.MultiSpinner;
import com.androidigniter.excelapp.model.CompanyResponse;
import com.androidigniter.excelapp.model.CraneDailyStatusResponse;
import com.androidigniter.excelapp.model.CraneResponse;
import com.androidigniter.excelapp.model.CraneTaskResponse;
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


public class CraneDailyStatusActivity extends AppCompatActivity {


    private ProjectViewModel projectViewModel;
    ArrayList<String> projectNameList = new ArrayList<>();
    ArrayList<String> wtgNameList = new ArrayList<>();
    ArrayList<String> craneTypeList = new ArrayList<>();
    ArrayList<String> OTS1NameList = new ArrayList<>();

    EditText daily_date;
    EditText entry_time;
    EditText departure_time;

    private ProgressDialog pDialog;
    String project_id = "";
    String daily_status_project_id = "";
    String wtg_id = "";
    String crane_id = "";
    String ots1_id = "";

    List<String> craneNames = new ArrayList<>();
    List<String> craneIDs = new ArrayList<>();
    List<String> selectedCranes = new ArrayList<>();

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
        setContentView(R.layout.activity_crane_daily_status);


        projectViewModel = ViewModelProviders.of(this).get(ProjectViewModel.class);


        List<ProjectResponse> projectList = projectViewModel.getAllProjects();
        List<WTGResponse> wtgList = projectViewModel.getAllWTGs();
        List<CraneResponse> craneList = projectViewModel.getAllCranes();
        List<OTS1Response> ots1List = projectViewModel.getAllOts1();

        for(ProjectResponse project:projectList) {
            projectNameList.add(project.getProject_code());
        }
        for(WTGResponse wtg:wtgList) {
            wtgNameList.add(wtg.getWtg_name());
        }

        for(CraneResponse craneResponse:craneList) {
            craneTypeList.add(craneResponse.getCrane_type());
        }
        for(OTS1Response ots1:ots1List) {
            OTS1NameList.add(ots1.getCode());
        }

        Spinner project = (Spinner) findViewById(R.id.crane_new_task_project_code_input);
        Spinner wtg = (Spinner) findViewById(R.id.crane_new_task_wtg_input);
        Spinner craneSpinner = (Spinner) findViewById(R.id.crane_new_crane_type_input);
        Spinner ots1 = (Spinner) findViewById(R.id.crane_new_task_task_input);
        Spinner daily_project = (Spinner) findViewById(R.id.crane_daily_status_project_code_input);


        ArrayAdapter wtgarrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, wtgNameList);
        wtgarrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        wtg.setAdapter(wtgarrayAdapter);

        ArrayAdapter projectArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, projectNameList);
        projectArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        project.setAdapter(projectArrayAdapter);
        daily_project.setAdapter(projectArrayAdapter);


        ArrayAdapter craneArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, craneTypeList);
        craneArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        craneSpinner.setAdapter(craneArrayAdapter);


        ArrayAdapter taskArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, OTS1NameList);
        taskArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ots1.setAdapter(taskArrayAdapter);
        daily_project.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                daily_status_project_id = projectList.get(position).getStringID();
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

        final MultiSpinner multiSpinner = (MultiSpinner) findViewById(R.id.crane_daily_status_crane_input);
        for(CraneResponse crane:projectViewModel.getAllCranes()) {
            craneNames.add(crane.getResource());
            craneIDs.add(crane.getStringID());
        }
        multiSpinner.setItems(craneNames, "", new MultiSpinner.MultiSpinnerListener() {
            @Override
            public void onItemsSelected(boolean[] selected) {
                selectedCranes.clear();
                for(int i = 0; i < selected.length; i ++) {
                    if(selected[i] == true) {
                        selectedCranes.add(craneIDs.get(i));
                    }
                }
            }
        });

        //Set Spinner Listener.
        project.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                project_id = projectList.get(position).getStringID();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                project_id = "";
            }
        });
        wtg.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                wtg_id = wtgList.get(position).getStringID();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                wtg_id = "";
            }
        });

        craneSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                crane_id = craneList.get(position).getStringID();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                crane_id = "";
            }
        });


        daily_date = findViewById(R.id.crane_daily_status_date_input);
        daily_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(CraneDailyStatusActivity.this, datePickerListener ,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        entry_time = findViewById(R.id.crane_daily_status_entry_time_input);
        entry_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(CraneDailyStatusActivity.this, entryTimeListener ,myCalendar.get(Calendar.HOUR_OF_DAY),myCalendar.get(Calendar.MINUTE), true).show();
            }
        });
        departure_time = findViewById(R.id.crane_daily_status_departure_time_input);
        departure_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(CraneDailyStatusActivity.this, departureTimeListener ,myCalendar.get(Calendar.HOUR_OF_DAY),myCalendar.get(Calendar.MINUTE), true).show();
            }
        });



        Button saveBtn = findViewById(R.id.crane_task_save_btn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveData();
            }
        });

        Button dailyStatusSaveBtn = findViewById(R.id.crane_daily_status_save_btn);
        dailyStatusSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDailyStatusData();
            }
        });

        Button task_list_btn = findViewById(R.id.crane_task_list_btn);
        task_list_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CraneDailyStatusActivity.this, CraneTaskListActivity.class);
                startActivity(i);
            }
        });

    }


    private void displayLoader() {
        pDialog = new ProgressDialog(CraneDailyStatusActivity.this);
        pDialog.setMessage("Saving Data.. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);

        pDialog.show();

    }

    private void SaveData() {
        displayLoader();
        EditText status = findViewById(R.id.crane_new_task_status_input);
        Switch shift_option = (Switch) findViewById(R.id.crane_new_task_shift_option_input);
        EditText comments = findViewById(R.id.crane_new_task_comment_input);

        try {
            CraneTaskResponse newResponse = new CraneTaskResponse();
            newResponse.setProjectID(project_id);
            newResponse.setWTGID(wtg_id);
            newResponse.setStatus(Double.valueOf(status.getText().toString()));
            newResponse.setCraneID(crane_id);
            newResponse.setShift_option(shift_option.isChecked() ? 1: 0);
            newResponse.setComments(comments.getText().toString());
            newResponse.setTaskID(ots1_id);
            projectViewModel.insertCraneTask(newResponse);
            Toast.makeText(getApplicationContext(), "Saved!!", Toast.LENGTH_SHORT).show();

        } catch(NumberFormatException exception) {
            Toast.makeText(getApplicationContext(), "Please confirm all the fields are entered!", Toast.LENGTH_SHORT).show();
        }
        pDialog.dismiss();

    }

    private void saveDailyStatusData() {
        displayLoader();
        try {
            CraneDailyStatusResponse newResponse = new CraneDailyStatusResponse();
            String builder = "";
            for(String selected:selectedCranes) {
                builder += selected+ ",";
            }
            newResponse.setCranes(builder);
            newResponse.setDate(daily_date.getText().toString());
            newResponse.setEntry_time(entry_time.getText().toString());
            newResponse.setDeparture_time(departure_time.getText().toString());
            newResponse.setProjectID(daily_status_project_id);
            projectViewModel.insertCraneDailyStatus(newResponse);
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
