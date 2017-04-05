package com.example.forever.pmhma;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText nameET;
    private EditText detailsET;
    private EditText appoinmentET;
    private EditText phoneET;
    private EditText emailET;

    private Doctor doctor;
    private DoctorDatabaseSource doctorDatabaseSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameET        = (EditText) findViewById(R.id.doctorName);
        detailsET     = (EditText) findViewById(R.id.doctorDetails);
        appoinmentET  = (EditText) findViewById(R.id.doctorAppoinment);
        phoneET       = (EditText) findViewById(R.id.doctorPhone);
        emailET       = (EditText) findViewById(R.id.doctorEmail);
        doctorDatabaseSource = new DoctorDatabaseSource(this);
    }

    public void addDoctor(View view) {
        String name         =   nameET.getText().toString();
        String details      =   detailsET.getText().toString();
        String appointment  =   appoinmentET.getText().toString();
        String phone        =   phoneET.getText().toString();
        String email        =   emailET.getText().toString();
        if(name.isEmpty()){
            nameET.setError("This field must not be required !");
        }
        if(details.isEmpty()){
            detailsET.setError("This field must not be required !");
        }

        if(appointment.isEmpty()){
            appoinmentET.setError("This field must not be required !");
        }

        if(phone.isEmpty()){
            phoneET.setError("This field must not be required !");
        }

        if(email.isEmpty()){
            emailET.setError("This field must not be required !");
        }else{
            doctor =   new Doctor(name,details,appointment,phone,email);
            boolean status  =   doctorDatabaseSource.addDoctorInfo(doctor);
            if(status){
                Toast.makeText(this, "Successfull", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this,DoctorListActivity.class));
            }else{
                Toast.makeText(this, "Could not save", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void addRegistration(View view) {
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
}
