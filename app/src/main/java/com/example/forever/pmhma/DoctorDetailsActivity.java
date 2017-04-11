package com.example.forever.pmhma;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DoctorDetailsActivity extends AppCompatActivity {

    private TextView docNameTV, docSpecialistTV, docApointTV, docPhoneTV, docEmailTV;
    private Doctor doctor;
    private MedicalHistory medicalHistory;

    //for get intent variable
    private String docName,docSpecialist,docApoint,doctorPhone,doctorEmail;

    private int rowId;
    private DoctorDatabaseSource  doctorDatabaseSource;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);
        doctorDatabaseSource = new DoctorDatabaseSource(this);

       // doctor = (Doctor) getIntent().getSerializableExtra("doctorObj");
        docName      = getIntent().getStringExtra("doctorName");
        docSpecialist= getIntent().getStringExtra("docSpecialist");
        docApoint    = getIntent().getStringExtra("doctorApoint");
        doctorPhone  = getIntent().getStringExtra("doctorPhone");
        doctorEmail  = getIntent().getStringExtra("doctorEmail");
        rowId        = getIntent().getIntExtra("id",0);

        docNameTV       = (TextView) findViewById(R.id.showName);
        docSpecialistTV = (TextView) findViewById(R.id.showSpecialist);
        docApointTV     = (TextView) findViewById(R.id.showDate);
        docPhoneTV      = (TextView) findViewById(R.id.showPhone);
        docEmailTV      = (TextView) findViewById(R.id.showEmail);

        docNameTV.setText(docName);
        docSpecialistTV.setText(docSpecialist);
        docApointTV.setText(docApoint);
        docPhoneTV.setText(doctorPhone);
        docEmailTV.setText(doctorEmail);


    }

   /* public void editDoctor(View view) {

        startActivity(new Intent(DoctorDetailsActivity.this,
                MainActivity.class)
                .putExtra("id",rowId)
                .putExtra("doctorName",docName)
                .putExtra("docSpecialist",docSpecialist)
                .putExtra("doctorApoint",docApoint)
                .putExtra("doctorPhone",doctorPhone)
                .putExtra("doctorEmail",doctorEmail)
        );
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.doctor_menu, menu);
        return true;
    }

    /*public void deleteDoctor(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Delete Doctor");
        alert.setMessage("Are you sure to delete this item ?");
        alert.setPositiveButton("Sure", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                boolean status = doctorDatabaseSource.deleteDoctor(rowId);
                if(status){
                    Toast.makeText(DoctorDetailsActivity.this, "Doctor Deleted", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(DoctorDetailsActivity.this,DoctorListActivity.class));
                }else{
                    Toast.makeText(DoctorDetailsActivity.this, "Couldn't Delete", Toast.LENGTH_SHORT).show();
                }
            }
        });
        alert.setNegativeButton("Cancel",null);
        alert.show();
    }*/

    public void addPrescription(View view) {
        startActivity(new Intent(DoctorDetailsActivity.this,AddPrescription.class)

                .putExtra("id",rowId)
                .putExtra("doctorName",docName)
                .putExtra("docSpecialist",docSpecialist));
    }

    public void viewPrescription(View view) {
       // medicalHistory.setDoctorId(rowId);
        //doctorDatabaseSource.getDoctorPrescription(rowId);
        startActivity(new Intent(DoctorDetailsActivity.this,MedicalListActivity.class));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                startActivity(new Intent(DoctorDetailsActivity.this,DoctorListActivity.class));
                break;
            case R.id.menu_update:
                startActivity(new Intent(DoctorDetailsActivity.this,
                        AddDoctorActivity.class)
                        .putExtra("id",rowId)
                        .putExtra("doctorName",docName)
                        .putExtra("docSpecialist",docSpecialist)
                        .putExtra("doctorApoint",docApoint)
                        .putExtra("doctorPhone",doctorPhone)
                        .putExtra("doctorEmail",doctorEmail)
                );
                break;
            case R.id.menu_delete:
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setTitle("Delete Doctor");
                alert.setMessage("Are you sure to delete this item ?");
                alert.setPositiveButton("Sure", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        boolean status = doctorDatabaseSource.deleteDoctor(rowId);
                        if(status){
                            Toast.makeText(DoctorDetailsActivity.this, "Doctor Deleted", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(DoctorDetailsActivity.this,DoctorListActivity.class));
                        }else{
                            Toast.makeText(DoctorDetailsActivity.this, "Couldn't Delete", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                alert.setNegativeButton("Cancel",null);
                alert.show();
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
