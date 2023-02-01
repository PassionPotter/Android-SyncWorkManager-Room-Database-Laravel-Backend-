package com.androidigniter.excelapp.data;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.androidigniter.excelapp.R;
import com.androidigniter.excelapp.model.CraneRecolocationResponse;
import com.androidigniter.excelapp.model.CraneResponse;
import com.androidigniter.excelapp.model.PersonelResponse;
import com.androidigniter.excelapp.model.ProjectResponse;
import com.androidigniter.excelapp.model.WTGResponse;
import com.androidigniter.excelapp.viewmodel.ProjectViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class CraneRecolocationActivity extends AppCompatActivity {


    EditText start_date;
    EditText start_hour;
    EditText end_date;
    EditText end_hour;
    final Calendar myCalendar= Calendar.getInstance();
    private ProjectViewModel projectViewModel;
    ArrayList<String> craneNameList = new ArrayList<>();
    ArrayList<String> wtgNameList = new ArrayList<>();

    ArrayList<String> projectNameList = new ArrayList<>();
    String project_id = "";

    private ProgressDialog pDialog;
    String movement_from_id;
    String movement_to_id;
    String crane_type_id;
    String walk_value;

    final DatePickerDialog.OnDateSetListener startDatePicker =new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH,month);
            myCalendar.set(Calendar.DAY_OF_MONTH,day);
            updateStartDate();
        }
    };
    final DatePickerDialog.OnDateSetListener endDatePicker =new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH,month);
            myCalendar.set(Calendar.DAY_OF_MONTH,day);
            updateEndDate();
        }
    };
    final TimePickerDialog.OnTimeSetListener startHourPicker =new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            myCalendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
            myCalendar.set(Calendar.MINUTE,minute);
            updateStartHour();
        }
    };
    final TimePickerDialog.OnTimeSetListener endHourPicker =new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            myCalendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
            myCalendar.set(Calendar.MINUTE,minute);
            updateEndHour();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crane_recolocation);

        start_date = findViewById(R.id.crane_recolocation_start_date_input);
        start_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(CraneRecolocationActivity.this, startDatePicker ,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        end_date = findViewById(R.id.crane_recolocation_end_date_input);
        end_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(CraneRecolocationActivity.this, endDatePicker ,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        start_hour = findViewById(R.id.crane_recolocation_start_hour_input);
        start_hour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(CraneRecolocationActivity.this, startHourPicker ,myCalendar.get(Calendar.HOUR_OF_DAY),myCalendar.get(Calendar.MINUTE), true).show();
            }
        });
        end_hour = findViewById(R.id.crane_recolocation_end_hour_input);
        end_hour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(CraneRecolocationActivity.this, endHourPicker ,myCalendar.get(Calendar.HOUR_OF_DAY),myCalendar.get(Calendar.MINUTE), true).show();
            }
        });
        projectViewModel = ViewModelProviders.of(this).get(ProjectViewModel.class);


        List<CraneResponse> craneList = projectViewModel.getAllCranes();
        List<WTGResponse> wtgList = projectViewModel.getAllWTGs();
        for(CraneResponse crane:craneList) {
            craneNameList.add(crane.getCrane_type());
        }
        for(WTGResponse wtg:wtgList) {
            wtgNameList.add(wtg.getWtg_name());
        }
        Spinner movement_from = (Spinner) findViewById(R.id.crane_recolocation_movement_from);
        Spinner movement_to = (Spinner) findViewById(R.id.crane_recolocation_movement_to);
        Spinner crane_type = (Spinner) findViewById(R.id.crane_recolocation_crane_type_input);
        Spinner walk_option = (Spinner) findViewById(R.id.crane_recolocation_walk_input);

        ArrayAdapter wtgarrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, wtgNameList);
        wtgarrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        movement_from.setAdapter(wtgarrayAdapter);
        movement_to.setAdapter(wtgarrayAdapter);

        ArrayAdapter cranearrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, craneNameList);
        cranearrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        crane_type.setAdapter(cranearrayAdapter);

        List<ProjectResponse> projectList = projectViewModel.getAllProjects();
        for(ProjectResponse project:projectList) {
            projectNameList.add(project.getProject_code());
        }

        Spinner project = findViewById(R.id.crane_recolocation_project_code_input);

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

        ArrayList<String> walkOption = new ArrayList<>();
        walkOption.add("Walk");
        walkOption.add("Half Disassembly");
        walkOption.add("Full Disassembly");

        ArrayAdapter walk_Adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, walkOption);
        walk_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        walk_option.setAdapter(walk_Adapter);

        //Set Spinner Listener.
        movement_from.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                movement_from_id = wtgList.get(position).getStringID();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                movement_from_id = "";
            }
        });
        movement_to.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                movement_to_id = wtgList.get(position).getStringID();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                movement_to_id = "";
            }
        });
        crane_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                crane_type_id = craneList.get(position).getStringID();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                crane_type_id = "";
            }
        });
        walk_option.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                walk_value = walkOption.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                walk_value = "";
            }
        });

        Button saveBtn = findViewById(R.id.btn_crane_recolocation);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveData();
            }
        });


    }

    private void updateStartDate(){
        String myFormat="MM/dd/yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        start_date.setText(dateFormat.format(myCalendar.getTime()));
    }
    private void updateEndDate(){
        String myFormat="MM/dd/yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        end_date.setText(dateFormat.format(myCalendar.getTime()));
    }
    private void updateStartHour(){
        String myFormat="HH:mm";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        start_hour.setText(dateFormat.format(myCalendar.getTime()));
    }
    private void updateEndHour(){
        String myFormat="HH:mm";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        end_hour.setText(dateFormat.format(myCalendar.getTime()));
    }
    private void displayLoader() {
        pDialog = new ProgressDialog(CraneRecolocationActivity.this);
        pDialog.setMessage("Saving Data.. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);

        pDialog.show();

    }

    private void SaveData() {
        displayLoader();
        EditText distance = findViewById(R.id.crane_recolocation_distance_input);
        EditText total_hours = findViewById(R.id.crane_recolocation_total_hours_input);
        EditText hours_contract = findViewById(R.id.crane_recolocation_hours_contract_input);
        EditText hours_excess = findViewById(R.id.crane_recolocation_hours_excess_input);
        EditText hours_stand_by = findViewById(R.id.crane_recolocation_hours_standbyinput);
        EditText hours_not_stand_by = findViewById(R.id.crane_recolocation_hours_not_standby_input);
        EditText comments = findViewById(R.id.crane_recolocation_comments_input);

        try {
            CraneRecolocationResponse newResponse = new CraneRecolocationResponse();
            newResponse.setMovement_from(movement_from_id);
            newResponse.setMovement_to(movement_to_id);
            newResponse.setCrane_type(crane_type_id);
            newResponse.setDistance(Double.valueOf(distance.getText().toString()));
            newResponse.setAssemble_option(walk_value);
            newResponse.setStart_date(start_date.getText().toString());
            newResponse.setStart_hour(start_hour.getText().toString());
            newResponse.setEnd_date(end_date.getText().toString());
            newResponse.setEnd_hour(end_hour.getText().toString());
            newResponse.setTotal_hours(Integer.valueOf(total_hours.getText().toString()));
            newResponse.setHours_contract(Integer.valueOf(hours_contract.getText().toString()));
            newResponse.setHours_excess(Integer.valueOf(hours_excess.getText().toString()));
            newResponse.setHours_standby(Integer.valueOf(hours_stand_by.getText().toString()));
            newResponse.setHours_not_standby(Integer.valueOf(hours_not_stand_by.getText().toString()));
            newResponse.setComments(comments.getText().toString());
            newResponse.setProjectID(project_id);
            projectViewModel.insertCraneRecolocation(newResponse);
            Toast.makeText(getApplicationContext(), "Saved!!", Toast.LENGTH_SHORT).show();

        } catch(NumberFormatException exception) {
            Toast.makeText(getApplicationContext(), "Please confirm all the fields are entered!", Toast.LENGTH_SHORT).show();
        }
        pDialog.dismiss();

    }

}
