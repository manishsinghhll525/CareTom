package com.caretom.models;

import java.io.Serializable;

/**
 * Created by techelogy2 on 21/3/17.
 */

public class NoteModel implements Serializable{

    private int id;
    private String packageId;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
