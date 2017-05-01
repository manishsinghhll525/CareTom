package com.caretom.beforeLoginModule;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.caretom.R;
import com.caretom.common.CaretomUrl;
import com.caretom.common.FragmentVolleyResponse;
import com.caretom.common.KeyConstants;
import com.caretom.common.SaveData;
import com.caretom.customViews.CustomTextView;
import com.caretom.customer.CustomerDashboardScreen;
import com.caretom.doctor.DoctorDashBoardScreen;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by techelogy2 on 21/4/17.
 */

public class LoginFragment extends Fragment implements KeyConstants {


    private final int MODE_LOGIN = 101;
    private final String REQUEST_KEY_EMAIL = "email";
    private final String REQUEST_KEY_PASSWORD = "password";
    private final String REQUEST_KEY_DEVICE_TOKEN = "deviceToken";

    View view;
    private EditText et_email, et_password;
    private CustomTextView tv_forgot_password;
    private Button btn_login;

    private Context context;
    private SaveData saveData;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_login, container, false);
        initWidgets();
        initWidgetListener();
        return view;
    }


    private void initWidgets() {
        context = getActivity();
        saveData = new SaveData(context);
        et_email = (EditText) view.findViewById(R.id.et_email);
        et_password = (EditText) view.findViewById(R.id.et_password);
        tv_forgot_password = (CustomTextView) view.findViewById(R.id.tv_forgot_password);
        btn_login = (Button) view.findViewById(R.id.btn_login);

    }

    private void initWidgetListener() {
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAllDetailValid()) {
                    callLoginApi();
                }
            }
        });


        tv_forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    private boolean isAllDetailValid() {

        if (et_email.getText().toString().isEmpty()) {
            showAlertDialog("Please enter Email.");
            return false;

        } else if (et_password.getText().toString().isEmpty()) {
            showAlertDialog("Please enter Password.");
            return false;
        } else if (!isEmailValid(et_email.getText().toString())) {
            showAlertDialog("Please enter email in proper format.");
            return false;
        }
        return true;

    }


    private void showAlertDialog(String message) {
        android.support.v7.app.AlertDialog.Builder dialogBuilder = new android.support.v7.app.AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.alert_view, null);
        dialogBuilder.setView(view);
        final android.support.v7.app.AlertDialog alertDialog = dialogBuilder.create();
        TextView tv_alert_text = (TextView) view.findViewById(R.id.tv_alert_text);
        tv_alert_text.setText(message);
        Button btn_ok = (Button) view.findViewById(R.id.btn_ok);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }


    public boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        } else {
            isValid = false;
        }
        return isValid;
    }


    private void callLoginApi() {
        FragmentVolleyResponse volleyResponse = new FragmentVolleyResponse(context, true) {
            @Override
            public void onVolleyResponseGet(String response, int apiCall) throws JSONException {

                JSONObject object = new JSONObject(response);
                if (object.getBoolean("status") == true) {
                    Snackbar.make(view, "You have signed up successfully", Snackbar.LENGTH_SHORT).show();
                    JSONObject data = object.getJSONObject("data");

                    if (data.getString("user_type").equalsIgnoreCase("1")) {
                        // doctor

                        saveData.save(KEY_FNAME, data.getString("fname"));
                        saveData.save(KEY_LNAME, data.getString("lname"));
                        saveData.save(KEY_USERID, data.getString("id"));
                        saveData.save(KEY_EMAIL, data.getString("email"));
                        saveData.save(KEY_GENDER, data.getString("gender"));
                        saveData.save(KEY_USERTYPE, data.getString("user_type"));
                        saveData.save(KEY_DEGREE, data.getString("degree"));
                        saveData.save(KEY_SPECIALIZATION, data.getString("specilization"));
                        saveData.save(KEY_FEE_PER_PATIENT, data.getString("fee_per_client"));
                        saveData.save(IS_LOGGED_IN, true);

                        startActivity(new Intent(context, DoctorDashBoardScreen.class));
                    } else {
                        // user_type=2 customer

                        saveData.save(KEY_FNAME, data.getString("fname"));
                        saveData.save(KEY_LNAME, data.getString("lname"));
                        saveData.save(KEY_USERID, data.getString("id"));
                        saveData.save(KEY_EMAIL, data.getString("email"));
                        saveData.save(KEY_GENDER, data.getString("gender"));
                        saveData.save(KEY_USERTYPE, data.getString("user_type"));
                        saveData.save(IS_LOGGED_IN, true);

                        startActivity(new Intent(context, CustomerDashboardScreen.class));

                    }


                } else {
                    // some error occured while signup
                    Snackbar.make(view, object.getString("message"), Snackbar.LENGTH_SHORT).show();
                }


            }
        };
        HashMap<String, String> postRequest = new HashMap<>();
        postRequest.put(REQUEST_KEY_DEVICE_TOKEN, saveData.getString(DEVICE_TOKEN));
        postRequest.put(REQUEST_KEY_EMAIL, et_email.getText().toString());
        postRequest.put(REQUEST_KEY_PASSWORD, et_password.getText().toString());
        volleyResponse.getVolleyResponse(CaretomUrl.LOGIN_URL, postRequest, MODE_LOGIN, "Please wait login...");
    }
}
