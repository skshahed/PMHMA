package com.example.forever.pmhma;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Forever on 4/1/2017.
 */

public class DoctorDatabaseSource {
    private DoctorDatabaseHelper doctorDatabaseHelper;
    private SQLiteDatabase sqLiteDatabase;
    private Doctor doctor;
    private MedicalHistory medicalHistory;

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

    public boolean addDoctorInfo(Doctor doctor){
        this.open();
        ContentValues values = new ContentValues();
        values.put(DoctorDatabaseHelper.DOC_NAME, doctor.getDocName());
        values.put(DoctorDatabaseHelper.DOC_DETAILS, doctor.getDocDetails());
        values.put(DoctorDatabaseHelper.DOC_APNMT_DATE, doctor.getDocApnmnt());
        values.put(DoctorDatabaseHelper.DOC_PHONE, doctor.getDocPhone());
        values.put(DoctorDatabaseHelper.DOC_EMAIL, doctor.getDocEmail());
        long id = sqLiteDatabase.insert(DoctorDatabaseHelper.DOCTOR_INFO_TABLE,null,values);

        this.close();
        if (id > 0){
            return true;
        }
        else {
            return false;
        }

    }

    public ArrayList<Doctor> getAllDoctor(){
        ArrayList<Doctor> doctors = new ArrayList<>();
        this.open();
        Cursor cursor = sqLiteDatabase.query(DoctorDatabaseHelper.DOCTOR_INFO_TABLE,null,null,null,null,null,null);
        cursor.moveToFirst();
        if (cursor != null && cursor.getCount() > 0){
            for (int i = 0;i < cursor.getCount();i++){
                int id = cursor.getInt(cursor.getColumnIndex(DoctorDatabaseHelper.DOC_ID));
                String docName = cursor.getString(cursor.getColumnIndex(DoctorDatabaseHelper.DOC_NAME));
                String docDetails = cursor.getString(cursor.getColumnIndex(DoctorDatabaseHelper.DOC_DETAILS));
                String docApnmnt=cursor.getString(cursor.getColumnIndex(DoctorDatabaseHelper.DOC_APNMT_DATE));
                String docPhone = cursor.getString(cursor.getColumnIndex(DoctorDatabaseHelper.DOC_PHONE));
                String docEmail = cursor.getString(cursor.getColumnIndex(DoctorDatabaseHelper.DOC_EMAIL));

                doctor = new Doctor(id,docName,docDetails,docApnmnt,docPhone,docEmail);
                doctors.add(doctor);
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.close();
        return doctors;
    }

    public boolean editDoctor(Doctor doctor,int rowId){
        this.open();
        ContentValues values = new ContentValues();
        values.put(DoctorDatabaseHelper.DOC_NAME, doctor.getDocName());
        values.put(DoctorDatabaseHelper.DOC_DETAILS, doctor.getDocDetails());
        values.put(DoctorDatabaseHelper.DOC_APNMT_DATE, doctor.getDocApnmnt());
        values.put(DoctorDatabaseHelper.DOC_PHONE, doctor.getDocPhone());
        values.put(DoctorDatabaseHelper.DOC_EMAIL, doctor.getDocEmail());
        int  updateId = sqLiteDatabase.update(DoctorDatabaseHelper.DOCTOR_INFO_TABLE,
                values,doctorDatabaseHelper.DOC_ID+" = ?",new String[]{Integer.toString(rowId)});
        if(updateId > 0){
            return  true;
        }else{
            return false;
        }
    }

    public boolean deleteDoctor(int rowId){
        this.open();
        int deleteId    =   sqLiteDatabase.delete(DoctorDatabaseHelper.DOCTOR_INFO_TABLE,
                DoctorDatabaseHelper.DOC_ID+"=?",new String[]{Integer.toString(rowId)});
        this.close();
        if(deleteId>0){
            return true;
        }else{
            return false;
        }
    }



    public ArrayList<MedicalHistory> getAllHistory(){
        ArrayList<MedicalHistory> medicalHistories = new ArrayList<>();
        this.open();
        Cursor cursor = sqLiteDatabase.query(DoctorDatabaseHelper.MEDICAL_HISTORY_TABLE,null,null,null,null,null,null);
        cursor.moveToFirst();
        if (cursor != null && cursor.getCount() > 0){
            for (int i = 0;i < cursor.getCount();i++){
                int id = cursor.getInt(cursor.getColumnIndex(DoctorDatabaseHelper.MH_ID));
                //String docName = cursor.getString(cursor.getColumnIndex(DoctorDatabaseHelper.DOC_NAME));
                //String docDetails = cursor.getString(cursor.getColumnIndex(DoctorDatabaseHelper.DOC_DETAILS));
                String prescribeDate = cursor.getString(cursor.getColumnIndex(DoctorDatabaseHelper.MH_DATE));
                String imagePath = cursor.getString(cursor.getColumnIndex(DoctorDatabaseHelper.MH_IMAGE_NAME));
               // String docEmail = cursor.getString(cursor.getColumnIndex(DoctorDatabaseHelper.DOC_EMAIL));

                medicalHistory = new MedicalHistory(id,prescribeDate,imagePath);
                medicalHistories.add(medicalHistory);
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.close();
        return medicalHistories;
    }


    public boolean addHistory(MedicalHistory medicalHistory){
        this.open();
        ContentValues values = new ContentValues();
        //values.put(DoctorDatabaseHelper.DOC_MH_ID, medicalHistory.getAddDate());


        values.put(DoctorDatabaseHelper.DOC_MH_ID, medicalHistory.getDoctorId());
        values.put(DoctorDatabaseHelper.MH_IMAGE_NAME, medicalHistory.getImageName());
        values.put(DoctorDatabaseHelper.MH_DATE, medicalHistory.getAddDate());
        long id = sqLiteDatabase.insert(DoctorDatabaseHelper.MEDICAL_HISTORY_TABLE,null,values);

        this.close();
        if (id > 0){
            return true;
        }
        else {
            return false;
        }

    }

}