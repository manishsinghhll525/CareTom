package com.caretom.common;

import java.io.Serializable;

/**
 * Created by techelogy2 on 16/4/17.
 */

public class BlogsCategoryModel implements Serializable {
    private int id;
    private String category;
    private String status;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
