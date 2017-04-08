package com.example.forever.pmhma;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DoctorDetailsActivity extends AppCompatActivity {

    private TextView docNameTV, docSpecialistTV, docApointTV, docPhoneTV, docEmailTV;
    private Doctor doctor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

       // doctor = (Doctor) getIntent().getSerializableExtra("doctorObj");
        String docName = getIntent().getStringExtra("doctorName");
        String docSpecialist = getIntent().getStringExtra("doctorSpecial");
        String docApoint = getIntent().getStringExtra("doctorApoint");
        String docPhone = getIntent().getStringExtra("doctorPhone");
        String docEmail = getIntent().getStringExtra("doctorEmail");

        docNameTV = (TextView) findViewById(R.id.showName);
        docSpecialistTV = (TextView) findViewById(R.id.showSpecialist);
        docApointTV = (TextView) findViewById(R.id.showDate);
        docPhoneTV = (TextView) findViewById(R.id.showPhone);
        docEmailTV = (TextView) findViewById(R.id.showEmail);

        docNameTV.setText(docName);
        docSpecialistTV.setText(docSpecialist);
        docApointTV.setText(docApoint);
        //docSpecialistTV.setText(doctor.getDocDetails());
        //docApointTV.setText(doctor.getDocApnmnt());
        docPhoneTV.setText(docPhone);
        docEmailTV.setText(docEmail);


    }

    public void editDoctor(View view) {
    }

    public void deleteDoctor(View view) {

    }
}
