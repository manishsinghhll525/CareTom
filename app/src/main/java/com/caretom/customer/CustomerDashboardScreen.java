package com.caretom.customer;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.caretom.R;

/**
 * Created by techelogy2 on 23/4/17.
 */

public class CustomerDashboardScreen extends AppCompatActivity {
    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_dashboard);
        initWidgets();
        initWidgetListener();
    }


    private void initWidgets() {
        context = CustomerDashboardScreen.this;
    }

    private void initWidgetListener() {
    }
}
