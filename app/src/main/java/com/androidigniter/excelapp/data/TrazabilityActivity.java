package com.androidigniter.excelapp.data;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.androidigniter.excelapp.R;
import com.androidigniter.excelapp.model.ComponentResponse;
import com.androidigniter.excelapp.model.InefficienciesResponse;
import com.androidigniter.excelapp.model.ProjectResponse;
import com.androidigniter.excelapp.model.TrazabilityResponse;
import com.androidigniter.excelapp.model.WTGResponse;
import com.androidigniter.excelapp.viewmodel.ProjectViewModel;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class TrazabilityActivity extends AppCompatActivity {


    private ProjectViewModel projectViewModel;
    ArrayList<String> wtgNameList = new ArrayList<>();
    ArrayList<String> componentNameList = new ArrayList<>();
    ArrayList<String> projectNameList = new ArrayList<>();
    private ProgressDialog pDialog;
    String wtg_id = "";
    String componentName = "";
    ImageView photoView;
    String project_id = "";

    public static final int PICK_IMAGE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trazability);


        projectViewModel = ViewModelProviders.of(this).get(ProjectViewModel.class);

        List<ProjectResponse> projectList = projectViewModel.getAllProjects();
        List<ComponentResponse> componentList = projectViewModel.getAllComponents();


        photoView = findViewById(R.id.trazability_photo_view);

        List<WTGResponse> wtgList = projectViewModel.getAllWTGs();

        for(ProjectResponse project:projectList) {
            projectNameList.add(project.getProject_code());
        }

        for(WTGResponse wtg:wtgList) {
            wtgNameList.add(wtg.getWtg_name());
        }
        for(ComponentResponse component:componentList) {
            componentNameList.add(component.getName());
        }
        Spinner project = (Spinner) findViewById(R.id.trazability_status_project_code_input);
        Spinner wtg = (Spinner) findViewById(R.id.trazability_wtg_input);
        Spinner componentType = (Spinner) findViewById(R.id.trazability_componentType_input);

        ArrayAdapter wtgarrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, wtgNameList);
        wtgarrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        wtg.setAdapter(wtgarrayAdapter);


        ArrayAdapter componentArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, componentNameList);
        componentArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        componentType.setAdapter(componentArrayAdapter);

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

        wtg.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                wtg_id = wtgList.get(position).getStringID();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        componentType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                componentName = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        Button saveBtn = findViewById(R.id.trazability_save_btn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveData();
            }
        });

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);


        Button addImageBtn = findViewById(R.id.trazability_photo_btn);
        addImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);

            }
        });
        Button trazability_list_btn = findViewById(R.id.trazability_list_btn);
        trazability_list_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TrazabilityActivity.this, TrazabilityListActivity.class);
                startActivity(i);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                //Display an error
                return;
            }
            Uri selectedImage = data.getData();
            photoView.setImageURI(selectedImage);
            //TODO: action
        }
    }
    private void displayLoader() {
        pDialog = new ProgressDialog(TrazabilityActivity.this);
        pDialog.setMessage("Saving Data.. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);

        pDialog.show();

    }

    private void SaveData() {
        displayLoader();
        EditText serial_number = findViewById(R.id.trazability_serial_number_input);

        EditText comments = findViewById(R.id.trazability_comments_input);
        try {
            TrazabilityResponse newResponse = new TrazabilityResponse();
            newResponse.setWtg(wtg_id);
            newResponse.setComments(comments.getText().toString());
            newResponse.setSerial_number(Integer.valueOf(serial_number.getText().toString()));
            newResponse.setComponent_type(componentName);
            newResponse.setProjectID(project_id);
            BitmapDrawable drawable = (BitmapDrawable) photoView.getDrawable();
            Bitmap bitmap = drawable.getBitmap();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos); // bm is the bitmap object
            byte[] b = baos.toByteArray();
            String img_str = Base64.encodeToString(b, 0);
            newResponse.setPhoto(img_str);
            projectViewModel.insertTrazability(newResponse);
            Toast.makeText(getApplicationContext(), "Saved!!", Toast.LENGTH_SHORT).show();

        } catch(NumberFormatException exception) {
            Toast.makeText(getApplicationContext(), "Please confirm all the fields are entered!", Toast.LENGTH_SHORT).show();
        } catch(NullPointerException exception) {
            Toast.makeText(getApplicationContext(), "Please confirm all the fields are entered!", Toast.LENGTH_SHORT).show();
        }
        pDialog.dismiss();

    }

}
