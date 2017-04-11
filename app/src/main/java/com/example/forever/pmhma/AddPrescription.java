package com.example.forever.pmhma;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.SimpleDateFormat;
//import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.forever.pmhma.DoctorDatabaseSource;
import com.example.forever.pmhma.MedicalHistory;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Locale;
import java.util.Calendar;

import static android.R.attr.data;
import static android.os.Environment.getExternalStoragePublicDirectory;

@RequiresApi(api = Build.VERSION_CODES.N)
public class AddPrescription extends AppCompatActivity {
    String mCurrentPhotoPath;
    static final int REQUEST_TAKE_PHOTO = 1;
    private ImageButton imageView;
    private TextView showImgePathTV;
    private File image;
    private String imageFileName;

    private TextView doctorNameET;
    private TextView doctorDeatilsET;
   // private EditText doctorIDET;


    private Button prestionDateBTN;

    private int year, month, day, hour, minute;
    private Calendar calendar;

    private Button addHistoryBtn;

    private MedicalHistory medicalHistory;
    private DoctorDatabaseSource doctorDatabaseSource;
//    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


    //for get intent variable
    private String docName, docSpecialist, docApoint, doctorPhone, doctorEmail;
    private int rowId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_prescription);
        imageView = (ImageButton) findViewById(R.id.presImageButton);
        doctorDatabaseSource = new DoctorDatabaseSource(this);
        showImgePathTV = (TextView) findViewById(R.id.showImgePath);

        doctorNameET = (TextView) findViewById(R.id.doctorNameET);
        doctorDeatilsET = (TextView) findViewById(R.id.doctorDeatilsET);
       // doctorIDET = (EditText) findViewById(R.id.docId);

        prestionDateBTN = (Button) findViewById(R.id.prestionDate);
        calendar = Calendar.getInstance(Locale.getDefault());
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        addHistoryBtn = (Button) findViewById(R.id.addMedicalBtn);

        // pass data for edit
        docName = getIntent().getStringExtra("doctorName");
        docSpecialist = getIntent().getStringExtra("docSpecialist");
        rowId = getIntent().getIntExtra("id", 0);

        //set for data update
        doctorNameET.setText(docName);
        doctorDeatilsET.setText(docSpecialist);
        //doctorIDET.setText(""+rowId);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();

            }
        });

    }


    public void showDate(View view) {
        DatePickerDialog dpd = new DatePickerDialog(this, dateListner, year, month, day);
        dpd.show();
    }

    private DatePickerDialog.OnDateSetListener dateListner = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            // Toast.makeText(MainActivity.this, "year:"+year, Toast.LENGTH_SHORT).show();
            prestionDateBTN.setText(dayOfMonth + "-" + (month+1) + "-" + year);
        }
    };


    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir =   getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        showImgePathTV.setText(mCurrentPhotoPath);
        return image;
    }



    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = Uri.fromFile(image);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            /*Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");*/
            Bitmap mBitmap = BitmapFactory.decodeFile(mCurrentPhotoPath);
            imageView.setImageBitmap(mBitmap);
            // showImgePath.setText(imageFileName);
            Toast.makeText(this, imageFileName, Toast.LENGTH_SHORT).show();

        }
    }

    public void addMedicalHistory(View view) {
        String imagePath      =   showImgePathTV.getText().toString();
        String addDate  =   prestionDateBTN.getText().toString();
       // String addId  =   doctorIDET.getText().toString();

        if(imagePath.isEmpty()){
            showImgePathTV.setError("Please use your Camera !");
        }
        if(addDate.isEmpty()){
            prestionDateBTN.setError("Set Date !");
        }

        else{
            // it condition for update
            /*if(rowId > 0){
                doctor =   new Doctor(name,details,appointment,phone,email);
                boolean status  = doctorDatabaseSource.editDoctor(doctor,rowId);
                if(status){
                    Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this,DoctorListActivity.class));
                }else{
                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }else{   */         //it condition for add
            //MedicalHistory  medicalHistory =   new MedicalHistory(addDate,imagePath);
            MedicalHistory medicalHistory = new MedicalHistory(addDate,imagePath,rowId);
            boolean status  =   doctorDatabaseSource.addHistory(medicalHistory);
            if(status){
                Toast.makeText(this, "Successfull", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AddPrescription.this,MedicalListActivity.class));
            }else{
                Toast.makeText(this, "Could not save", Toast.LENGTH_SHORT).show();
            }
            // }
        }
    }
}