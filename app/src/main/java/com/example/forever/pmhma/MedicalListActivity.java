package com.example.forever.pmhma;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MedicalListActivity extends AppCompatActivity {

    private ListView mListView;
    private MedicalHistoryAdapter medicalHistoryAdapter;
    private ArrayList<MedicalHistory> medicalHistories;
    private DoctorDatabaseSource doctorDatabaseSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medical_history_layout);

        mListView = (ListView) findViewById(R.id.medicalList);
        doctorDatabaseSource = new DoctorDatabaseSource(this);
        medicalHistories = doctorDatabaseSource.getAllMedical();

        medicalHistoryAdapter = new MedicalHistoryAdapter(this, medicalHistories);
        mListView.setAdapter(medicalHistoryAdapter);

    }
}