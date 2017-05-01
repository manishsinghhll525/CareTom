package com.caretom;

import android.app.Application;


/**
 * Created by techelogy2 on 15/4/17.
 */

public class MyApplication extends Application {


    private String filterByRating = "";
    private String filterByCategory = "";
    private String filterByMonth = "";
    private String filterByAuthor = "";
    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static MyApplication getIstance() {
        return mInstance;
    }

    public String getFilterByRating() {
        return filterByRating;
    }

    public void setFilterByRating(String filterByRating) {
        this.filterByRating = filterByRating;
    }

    public String getFilterByCategory() {
        return filterByCategory;
    }

    public void setFilterByCategory(String filterByCategory) {
        this.filterByCategory = filterByCategory;
    }

    public String getFilterByMonth() {
        return filterByMonth;
    }

    public void setFilterByMonth(String filterByMonth) {
        this.filterByMonth = filterByMonth;
    }

    public String getFilterByAuthor() {
        return filterByAuthor;
    }

    public void setFilterByAuthor(String filterByAuthor) {
        this.filterByAuthor = filterByAuthor;
    }


}
