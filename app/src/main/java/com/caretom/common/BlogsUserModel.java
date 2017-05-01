package com.caretom.common;

import java.io.Serializable;

/**
 * Created by techelogy2 on 16/4/17.
 */

public class BlogsUserModel implements Serializable{
    private int id;
    private String fname;
    private String lname;
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
