package com.example.forever.pmhma;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Forever on 4/1/2017.
 */

public class DoctorDatabaseSource {
    private DoctorDatabaseHelper doctorDatabaseHelper;
    private SQLiteDatabase sqLiteDatabase;
    private DoctorList doctorList;

    public DoctorDatabaseSource(Context context) {
        doctorDatabaseHelper = new DoctorDatabaseHelper(context);

    }

    public void open()
    {
        sqLiteDatabase = doctorDatabaseHelper.getWritableDatabase();
    }

    public void close(){
        sqLiteDatabase.close();

    }

    public boolean addDoctorInfo(DoctorList doctorList){
        this.open();
        ContentValues newDoctor = new ContentValues();
        newDoctor.put(DoctorDatabaseHelper.DOC_NAME,doctorList.getDocName());
        newDoctor.put(DoctorDatabaseHelper.DOC_DETAILS,doctorList.getDocDetails());
        newDoctor.put(DoctorDatabaseHelper.DOC_APNMT_DATE,doctorList.getDocApnmnt());
        newDoctor.put(DoctorDatabaseHelper.DOC_PHONE,doctorList.getDocPhone());
        newDoctor.put(DoctorDatabaseHelper.DOC_EMAIL,doctorList.getDocEmail());
        long id = sqLiteDatabase.insert(DoctorDatabaseHelper.DOCTOR_INFO_TABLE,null,newDoctor);

        this.close();
        if (id>0){
            return true;
        }
        else {
            return false;
        }

    }

    public ArrayList<DoctorList> getAllDoctor(){
        ArrayList<DoctorList> doctors = new ArrayList<>();
        this.open();
        Cursor cursor = sqLiteDatabase.query(DoctorDatabaseHelper.DOCTOR_INFO_TABLE,null,null,null,null,null,null);
        cursor.moveToFirst();
        if (cursor != null && cursor.getCount() > 0){
            for (int i=0;i < cursor.getCount();i++){
                int id = cursor.getInt(cursor.getColumnIndex(DoctorDatabaseHelper.DOC_ID));
                String docName = cursor.getString(cursor.getColumnIndex(DoctorDatabaseHelper.DOC_NAME));
                String docDetails = cursor.getString(cursor.getColumnIndex(DoctorDatabaseHelper.DOC_DETAILS));
                String docApnmnt=cursor.getString(cursor.getColumnIndex(DoctorDatabaseHelper.DOC_APNMT_DATE));
                String docPhone = cursor.getString(cursor.getColumnIndex(DoctorDatabaseHelper.DOC_PHONE));
                String docEmail = cursor.getString(cursor.getColumnIndex(DoctorDatabaseHelper.DOC_EMAIL));

                doctorList = new DoctorList(id,docName,docDetails,docApnmnt,docPhone,docEmail);
                doctors.add(doctorList);
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.close();
        return doctors;
    }
}

