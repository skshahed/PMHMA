package com.example.forever.pmhma;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

public class ShowPrescriptionActivity extends AppCompatActivity {
    private ImageView showImageIV;
    private int rowId;
    private DoctorDatabaseSource doctorDatabaseSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_prescription);

        doctorDatabaseSource = new DoctorDatabaseSource(this);
        rowId        = getIntent().getIntExtra("MH_id",0);

        Bitmap bmp = getIntent().getParcelableExtra("prescriptionImage");
        showImageIV = (ImageView) findViewById(R.id.showPrescription);
        showImageIV.setImageBitmap(bmp);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.prescription_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                startActivity(new Intent(ShowPrescriptionActivity.this,DoctorListActivity.class));
                break;
            case R.id.prescription_delete:
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setTitle("Delete Prescription");
                alert.setMessage("Are you sure to delete this item ?");
                alert.setPositiveButton("Sure", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        boolean status = doctorDatabaseSource.deletePrescription(rowId);
                        if(status){
                            Toast.makeText(ShowPrescriptionActivity.this, "Prescription Deleted", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ShowPrescriptionActivity.this,MedicalListActivity.class));
                        }else{
                            Toast.makeText(ShowPrescriptionActivity.this, "Couldn't Delete", Toast.LENGTH_SHORT).show();
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
