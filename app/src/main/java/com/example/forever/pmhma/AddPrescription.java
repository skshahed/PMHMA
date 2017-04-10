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
import android.widget.Toast;

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
    private File image;
    private String imageFileName;

    private EditText doctorNameET;
    private EditText doctorDeatilsET;

    private Button prestionDateBTN;
    private int year, month, day, hour, minute;
    private Calendar calendar;
//    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


    //for get intent variable
    private String docName, docSpecialist, docApoint, doctorPhone, doctorEmail;
    private int rowId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_prescription);
        imageView = (ImageButton) findViewById(R.id.presImageButton);

        doctorNameET = (EditText) findViewById(R.id.doctorNameET);
        doctorDeatilsET = (EditText) findViewById(R.id.doctorDeatilsET);

        prestionDateBTN = (Button) findViewById(R.id.prestionDate);
        calendar = Calendar.getInstance(Locale.getDefault());
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        // pass data for edit
        docName = getIntent().getStringExtra("doctorName");
        docSpecialist = getIntent().getStringExtra("docSpecialist");
        rowId = getIntent().getIntExtra("id", 0);

        //set for data update
        doctorNameET.setText(docName);
        doctorDeatilsET.setText(docSpecialist);

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
            prestionDateBTN.setText(dayOfMonth + "-" + month + "-" + year);
        }
    };


    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir =  getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
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
            Toast.makeText(this, imageFileName, Toast.LENGTH_SHORT).show();

        }
    }
}