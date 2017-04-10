package com.example.forever.pmhma;

/**
 * Created by Forever on 4/10/2017.
 */

public class MedicalHistory {
    private int mHistoryId;
    private String addDate;
    private String imageName;

    public MedicalHistory() {
    }

    public MedicalHistory(String addDate, String imageName) {
        this.addDate = addDate;
        this.imageName = imageName;
    }

    public MedicalHistory(int mHistoryId, String addDate, String imageName) {
        this.mHistoryId = mHistoryId;
        this.addDate = addDate;
        this.imageName = imageName;
    }

    public int getmHistoryId() {
        return mHistoryId;
    }

    public void setmHistoryId(int mHistoryId) {
        this.mHistoryId = mHistoryId;
    }

    public String getAddDate() {
        return addDate;
    }

    public void setAddDate(String addDate) {
        this.addDate = addDate;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
