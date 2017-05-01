package com.caretom.doctor;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.caretom.R;
import com.caretom.Signup.DoctorSignupActivity;

/**
 * Created by techelogy2 on 22/4/17.
 */

public class DoctorDashBoardScreen extends AppCompatActivity {
    private Context context;
    private Button btn_my_patient_list, btn_my_upcoming_appointments, btn_upload_blog, btn_edit_profile, btn_my_wallet;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_dashboard);
        initWidgets();
        initWidgetListener();
    }


    private void initWidgets() {
        context = DoctorDashBoardScreen.this;
        btn_my_patient_list = (Button) findViewById(R.id.btn_my_patient_list);
        btn_my_upcoming_appointments = (Button) findViewById(R.id.btn_my_upcoming_appointments);
        btn_upload_blog = (Button) findViewById(R.id.btn_upload_blog);
        btn_edit_profile = (Button) findViewById(R.id.btn_edit_profile);
        btn_my_wallet = (Button) findViewById(R.id.btn_my_patient_list);

    }

    private void initWidgetListener() {
        btn_my_patient_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        btn_my_upcoming_appointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        btn_upload_blog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        btn_edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn_my_wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
