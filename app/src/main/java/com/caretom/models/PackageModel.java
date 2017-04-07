package com.caretom.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by techelogy2 on 21/3/17.
 */

public class PackageModel implements Serializable {
    private int id;
    private String packageName;
    private String price;
    private String status;
    private String freeStatus;// 1 free , 0 paid
    private ArrayList<PackageInclusionModel> packageInclusionList = new ArrayList<>();
    private ArrayList<NoteModel> noteList = new ArrayList<>();


    public String getFreeStatus() {
        return freeStatus;
    }

    public void setFreeStatus(String freeStatus) {
        this.freeStatus = freeStatus;
    }


    public ArrayList<NoteModel> getNoteList() {
        return noteList;
    }

    public void setNoteList(ArrayList<NoteModel> noteList) {
        this.noteList = noteList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<PackageInclusionModel> getPackageInclusionList() {
        return packageInclusionList;
    }

    public void setPackageInclusionList(ArrayList<PackageInclusionModel> packageInclusionList) {
        this.packageInclusionList = packageInclusionList;
    }
}
