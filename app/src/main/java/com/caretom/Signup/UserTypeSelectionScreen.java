package com.caretom.Signup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.caretom.R;
import com.caretom.customViews.CustomTextView;

/**
 * Created by techelogy2 on 23/4/17.
 */

public class UserTypeSelectionScreen extends AppCompatActivity {
    private Context context;
    private CustomTextView tv_login;
    private LinearLayout ll_doctor, ll_patient;
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_type_selection);
        initWidgets();
        initWidgetLisetener();
        setUpActionBar();
    }


    private void setUpActionBar() {
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        View logo = getLayoutInflater().inflate(R.layout.widget_header, null);
        LinearLayout ll_back = (LinearLayout) logo.findViewById(R.id.ll_back);
        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
        });
        Button btn_next = (Button) logo.findViewById(R.id.btn_next);
        btn_next.setVisibility(View.GONE);
        CustomTextView tv_title = (CustomTextView) logo.findViewById(R.id.tv_title);
        tv_title.setText("Signup");


        toolbar.addView(logo);

    }

    private void initWidgets() {
        context = UserTypeSelectionScreen.this;
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tv_login = (CustomTextView) findViewById(R.id.tv_login);
        ll_doctor = (LinearLayout) findViewById(R.id.ll_doctor);
        ll_patient = (LinearLayout) findViewById(R.id.ll_patient);

    }

    private void initWidgetLisetener() {
        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

        ll_doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, DoctorSignupActivity.class));

            }
        });

        ll_patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, CustomerSignupActivity.class));
            }
        });
    }
}
