package com.example.forever.pmhma;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MedicalListActivity extends AppCompatActivity {

    private ListView mListView;
    private MedicalHistoryAdapter medicalHistoryAdapter;
    private ArrayList<MedicalHistory> medicalHistories;
    private DoctorDatabaseSource doctorDatabaseSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_list);

        mListView = (ListView) findViewById(R.id.medicalList);
        doctorDatabaseSource = new DoctorDatabaseSource(this);
        medicalHistories = doctorDatabaseSource.getAllHistory();

        medicalHistoryAdapter = new MedicalHistoryAdapter(this, medicalHistories);
        mListView.setAdapter(medicalHistoryAdapter);

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
                startActivity(new Intent(MedicalListActivity.this,DoctorListActivity.class));
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
}
