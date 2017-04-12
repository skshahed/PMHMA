package com.example.forever.pmhma;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Locale;

public class AddDoctorActivity extends AppCompatActivity {

    private EditText nameET;
    private EditText detailsET;
    //private EditText appoinmentET;
    private Button appoinmentET;
    private int year,month,day;
    private Calendar calendar;
//    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    private EditText phoneET;
    private EditText emailET;
    private Button btnAdd;

    private Doctor doctor;
    private DoctorDatabaseSource doctorDatabaseSource;

    //for get intent variable
    private String docName,docSpecialist,docApoint,doctorPhone,doctorEmail;
    private  int rowId ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_doctor);
        nameET        = (EditText) findViewById(R.id.doctorName);
        detailsET     = (EditText) findViewById(R.id.doctorDetails);
        appoinmentET  = (Button) findViewById(R.id.doctorAppoinment);
        phoneET       = (EditText) findViewById(R.id.doctorPhone);
        emailET       = (EditText) findViewById(R.id.doctorEmail);

        calendar      = Calendar.getInstance(Locale.getDefault());
        year          = calendar.get(Calendar.YEAR);
        month         = calendar.get(Calendar.MONTH);
        day           = calendar.get(Calendar.DAY_OF_MONTH);

        btnAdd        = (Button) findViewById(R.id.btnAdd);
        doctorDatabaseSource = new DoctorDatabaseSource(this);

        // pass data for edit
        docName         = getIntent().getStringExtra("doctorName");
        docSpecialist   = getIntent().getStringExtra("docSpecialist");
        docApoint       = getIntent().getStringExtra("doctorApoint");
        doctorPhone     = getIntent().getStringExtra("doctorPhone");
        doctorEmail     = getIntent().getStringExtra("doctorEmail");
        rowId           = getIntent().getIntExtra("id",0);

        //set for data update
        if(rowId > 0){
            nameET.setText(docName);
            detailsET.setText(docSpecialist);
            appoinmentET.setText(docApoint);
            phoneET.setText(doctorPhone);
            emailET.setText(doctorEmail);
            btnAdd.setText("Update");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                startActivity(new Intent(AddDoctorActivity.this,DoctorListActivity.class));
                break;
            case R.id.logout:
                Intent loginscreen=new Intent(this,LoginActivity.class);
                loginscreen.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(loginscreen);
                this.finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    public void addDoctor(View view) {
        String name         =   nameET.getText().toString();
        String details      =   detailsET.getText().toString();
        String appointment  =   appoinmentET.getText().toString();
        String phone        =   phoneET.getText().toString();
        String email        =   emailET.getText().toString();
        if(name.isEmpty()){
            nameET.setError("This field must not be Empty !");
        }
        if(details.isEmpty()){
            detailsET.setError("This field must not be Empty !");
        }

        if(appointment.isEmpty()){
            appoinmentET.setError("This field must not be Empty !");
        }

        if(phone.isEmpty()){
            phoneET.setError("This field must not be Empty !");
        }

        if(email.isEmpty()){
            emailET.setError("This field must not be Empty !");
        }else{
            // it condition for update
            if(rowId > 0){
                doctor =   new Doctor(name,details,appointment,phone,email);
                boolean status  = doctorDatabaseSource.editDoctor(doctor,rowId);
                if(status){
                    Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddDoctorActivity.this,DoctorListActivity.class));
                }else{
                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
                }

            }else{            //it condition for add
                doctor =   new Doctor(name,details,appointment,phone,email);
                boolean status  =   doctorDatabaseSource.addDoctorInfo(doctor);
                if(status){
                    Toast.makeText(this, "Successfull", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddDoctorActivity.this,DoctorListActivity.class));
                }else{
                    Toast.makeText(this, "Could not save", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public void showDate(View view) {
        DatePickerDialog dpd    =   new DatePickerDialog(this,dateListner,year,month,day);
        dpd.show();
    }

    private DatePickerDialog.OnDateSetListener dateListner  =   new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            // Toast.makeText(MainActivity.this, "year:"+year, Toast.LENGTH_SHORT).show();
            appoinmentET.setText(dayOfMonth+"-"+(month+1)+"-"+year);
        }
    };
}
