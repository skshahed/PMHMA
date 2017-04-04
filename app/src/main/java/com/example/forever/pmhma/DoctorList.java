package com.example.forever.pmhma;

/**
 * Created by Forever on 4/3/2017.
 */

public class DoctorList {
    private int docId;
    private String docName;
    private String docDetails;
    private String docApnmnt;
    private String docPhone;
    private String docEmail;

    public DoctorList() {
    }

    public DoctorList(int docId, String docName, String docDetails, String docApnmnt, String docPhone, String docEmail) {
        this.docId = docId;
        this.docName = docName;
        this.docDetails = docDetails;
        this.docApnmnt = docApnmnt;
        this.docPhone = docPhone;
        this.docEmail = docEmail;
    }

    public int getDocId() {
        return docId;
    }

    public void setDocId(int docId) {
        this.docId = docId;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocDetails() {
        return docDetails;
    }

    public void setDocDetails(String docDetails) {
        this.docDetails = docDetails;
    }

    public String getDocApnmnt() {
        return docApnmnt;
    }

    public void setDocApnmnt(String docApnmnt) {
        this.docApnmnt = docApnmnt;
    }

    public String getDocPhone() {
        return docPhone;
    }

    public void setDocPhone(String docPhone) {
        this.docPhone = docPhone;
    }

    public String getDocEmail() {
        return docEmail;
    }

    public void setDocEmail(String docEmail) {
        this.docEmail = docEmail;
    }
}
