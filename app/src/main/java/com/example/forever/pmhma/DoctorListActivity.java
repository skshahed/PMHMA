package com.example.forever.pmhma;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;

public class DoctorListActivity extends AppCompatActivity {

    private ListView mListView;
    private DoctorAdapter doctorAdapter;
    private ArrayList<Doctor> doctors;
    private DoctorDatabaseSource doctorDatabaseSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list);

        mListView = (ListView) findViewById(R.id.doctorList);
        doctorDatabaseSource = new DoctorDatabaseSource(this);
        doctors = doctorDatabaseSource.getAllDoctor();

        doctorAdapter = new DoctorAdapter(this, doctors);
        mListView.setAdapter(doctorAdapter);
    }
}