package com.example.forever.pmhma;

import android.app.DatePickerDialog;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Locale;

@RequiresApi(api = Build.VERSION_CODES.N)
public class AddPrescription extends AppCompatActivity {
    private EditText doctorNameET;
    private EditText doctorDeatilsET;

    private Button prestionDateBTN;
    private int year,month,day,hour,minute;
    private Calendar calendar;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


    //for get intent variable
    private String docName,docSpecialist,docApoint,doctorPhone,doctorEmail;
    private  int rowId ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_prescription);

        doctorNameET    = (EditText) findViewById(R.id.doctorNameET);
        doctorDeatilsET = (EditText) findViewById(R.id.doctorDeatilsET);

        prestionDateBTN = (Button) findViewById(R.id.prestionDate);
        calendar        = Calendar.getInstance(Locale.getDefault());
        year            = calendar.get(Calendar.YEAR);
        month           = calendar.get(Calendar.MONTH);
        day             = calendar.get(Calendar.DAY_OF_MONTH);


        // pass data for edit
        docName         = getIntent().getStringExtra("doctorName");
        docSpecialist   = getIntent().getStringExtra("docSpecialist");
        rowId           = getIntent().getIntExtra("id",0);

        //set for data update
        doctorNameET.setText(docName);
        doctorDeatilsET.setText(docSpecialist);

    }


    public void showDate(View view) {
        DatePickerDialog dpd    =   new DatePickerDialog(this,dateListner,year,month,day);
        dpd.show();
    }

    private DatePickerDialog.OnDateSetListener dateListner  =   new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            // Toast.makeText(MainActivity.this, "year:"+year, Toast.LENGTH_SHORT).show();
            prestionDateBTN.setText(dayOfMonth+"-"+month+"-"+year);
        }
    };

    public void pickCamera(View view) {
    }
}
