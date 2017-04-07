package com.caretom.beforeLoginModule;

import android.content.Context;
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
import com.caretom.common.NetworkUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by techelogy2 on 19/3/17.
 */

public class ContactUsFragment extends Fragment {
    private final int MODE_CONTACT_US = 101;
    View view;
    private Context context;
    private EditText et_name, et_address, et_email, et_mobile_no;
    private Button btn_send;


    private final String KEY_NAME = "name";
    private final String KEY_ADDRESS = "address";
    private final String KEY_EMAIL = "email";
    private final String KEY_MOBILE = "mobileNo";
    private final String KEY_COMMENT = "comment";







    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_contact_us, container, false);
        initWidgets();
        initWidgetListener();
        return view;
    }


    private void initWidgets() {
        context = getActivity();
        et_name = (EditText) view.findViewById(R.id.et_name);
        et_address = (EditText) view.findViewById(R.id.et_address);
        et_email = (EditText) view.findViewById(R.id.et_email);
        et_mobile_no = (EditText) view.findViewById(R.id.et_mobile_no);
        btn_send = (Button) view.findViewById(R.id.btn_send);

    }

    private void initWidgetListener() {
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isAllDetailVallid()) {
                    callContactUsApi();
                }

            }
        });
    }


    private boolean isAllDetailVallid() {
        if (et_name.getText().toString().isEmpty()) {
            showAlertDialog(context.getResources().getString(R.string.name_empty));
            return false;

        } else if (et_address.getText().toString().isEmpty()) {
            showAlertDialog(context.getResources().getString(R.string.address_empty));
            return false;

        } else if (et_email.getText().toString().isEmpty()) {
            showAlertDialog(context.getResources().getString(R.string.email_empty));
            return false;
        } else if (!isEmailValid(et_email.getText().toString())) {
            showAlertDialog(context.getResources().getString(R.string.email_incorrect_format));
            return false;
        } else if (et_mobile_no.getText().toString().isEmpty()) {
            showAlertDialog(context.getResources().getString(R.string.mobile_no_empty));
            return false;
        } else if (et_mobile_no.getText().toString().length() < 10) {
            showAlertDialog(context.getResources().getString(R.string.small_mobile_no));
            return false;
        }
        return true;
    }

    private void callContactUsApi() {
        if (NetworkUtils.isNetworkAvailable(context)) {

            FragmentVolleyResponse response = new FragmentVolleyResponse(context, true) {
                @Override
                public void onVolleyResponseGet(String response, int apiCall) throws JSONException {
                    JSONObject responseObj = new JSONObject(response);
                    if (responseObj.getBoolean("status")) {
                        et_address.setText("");
                        et_email.setText("");
                        et_mobile_no.setText("");
                        et_name.setText("");
                        Snackbar.make(et_name, "Contact Details Saved Successfully", Snackbar.LENGTH_LONG).show();

                    } else {
                        Snackbar.make(et_name, responseObj.getString("message"), Snackbar.LENGTH_LONG).show();

                    }
                }
            };

            HashMap<String, String> postRequest = new HashMap<>();
            postRequest.put(KEY_NAME, et_name.getText().toString());
            postRequest.put(KEY_ADDRESS, et_address.getText().toString());
            postRequest.put(KEY_COMMENT, "");
            postRequest.put(KEY_EMAIL, et_email.getText().toString());
            postRequest.put(KEY_MOBILE, et_mobile_no.getText().toString());

            response.getVolleyResponse(CaretomUrl.CONTACT_US_URL, postRequest, MODE_CONTACT_US, "Please wait...");

        } else {
            NetworkUtils.showInternetDialog(context);
        }

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

        }
        return isValid;
    }
}
