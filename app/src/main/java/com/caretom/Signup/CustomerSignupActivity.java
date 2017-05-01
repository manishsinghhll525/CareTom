package com.caretom.Signup;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.caretom.R;
import com.caretom.common.CaretomUrl;
import com.caretom.common.FragmentVolleyResponse;
import com.caretom.common.KeyConstants;
import com.caretom.common.NetworkUtils;
import com.caretom.common.SaveData;
import com.caretom.customViews.CustomTextView;
import com.caretom.customer.CustomerDashboardScreen;
import com.caretom.doctor.DoctorDashBoardScreen;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.ContentValues.TAG;

/**
 * Created by techelogy2 on 22/4/17.
 */

public class CustomerSignupActivity extends AppCompatActivity implements KeyConstants, GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks {

    private static final int REQUEST_CODE_ACCESS_LOCATION = 105;
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 106;
    private static final int MODE_CUSTOMER_SIGNUP = 104;

    private GoogleApiClient mGoogleApiClient;
    private EditText et_first_name, et_lastname, et_email, et_password, et_confirm_password, et_location, et_disease;
    private CustomTextView male_text, female_text;
    private Button btn_signup;
    private Context context;
    private FrameLayout frame_female, frame_male;


    private final String REQUEST_KEY_FNAME = "fname";
    private final String REQUEST_KEY_LNAME = "lname";
    private final String REQUEST_KEY_EMAIL = "email";
    private final String REQUEST_KEY_PASSWORD = "password";
    private final String REQUEST_KEY_USER_TYPE = "userType";//  (1=>Doctor, 2=>Customer)
    private final String REQUEST_KEY_LAT = "lat";
    private final String REQUEST_KEY_LNG = "lng";
    private final String REQUEST_KEY_GENDER = "gender";//(1=>M, 2=>F)
    private final String REQUEST_KEY_CONFIRM_PASSWORD = "confirmPassword";
    private final String REQUEST_KEY_LOCATION = "location";
    private final String REQUEST_KEY_DEGREE = "degree";
    private final String REQUEST_KEY_SPECIALIZATION = "specilization";
    private final String REQUEST_KEY_FEE = "feePerClient";
    private final String REQUEST_KEY_DISEASE = "disease";
    private final String REQUEST_KEY_PACKAGEID = "packageId";
    private final String REQUEST_KEY_DEVICE_TOKEN = "deviceToken";

    private String gender = "";
    private String lat = "";
    private String lng = "";
    private SaveData saveData;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_signup);
        initWidgets();
        initWidgetListener();
        if (isGooglePlayServicesAvailable()) {
            initGoogleApiClient();
        }
    }


    private void initWidgets() {
        context = CustomerSignupActivity.this;
        saveData = new SaveData(context);
        male_text = (CustomTextView) findViewById(R.id.male_text);
        female_text = (CustomTextView) findViewById(R.id.female_text);
        et_first_name = (EditText) findViewById(R.id.et_first_name);
        et_lastname = (EditText) findViewById(R.id.et_lastname);
        et_email = (EditText) findViewById(R.id.et_email);
        et_password = (EditText) findViewById(R.id.et_password);
        et_confirm_password = (EditText) findViewById(R.id.et_confirm_password);
        et_location = (EditText) findViewById(R.id.et_location);
        et_disease = (EditText) findViewById(R.id.et_disease);
        btn_signup = (Button) findViewById(R.id.btn_signup);
        frame_female = (FrameLayout) findViewById(R.id.frame_female);
        frame_male = (FrameLayout) findViewById(R.id.frame_male);

    }

    private void initWidgetListener() {
        frame_female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender = "2";
                frame_female.setBackgroundColor(getResources().getColor(R.color.main_heading_text_color));
                frame_male.setBackgroundColor(getResources().getColor(R.color.gender_frame_background));

            }
        });


        frame_male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender = "1";
                frame_female.setBackgroundColor(getResources().getColor(R.color.gender_frame_background));
                frame_male.setBackgroundColor(getResources().getColor(R.color.main_heading_text_color));

            }
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAllDetailValid()) {
                    callSignupApi();
                }
            }
        });
    }

    private boolean isAllDetailValid() {
        if (et_first_name.getText().toString().isEmpty()) {
            showAlertDialog("Please enter your First Name");
            return false;

        } else if (et_lastname.getText().toString().isEmpty()) {
            showAlertDialog("Please enter your Last Name");
            return false;

        } else if (et_email.getText().toString().isEmpty()) {
            showAlertDialog("Please enter your email");
            return false;

        } else if (et_password.getText().toString().isEmpty()) {
            showAlertDialog("Please enter your Password");
            return false;

        } else if (et_confirm_password.getText().toString().isEmpty()) {
            showAlertDialog("Please enter your Confirm Password");
            return false;

        } else if (et_location.getText().toString().isEmpty()) {
            showAlertDialog("Please enter your City");
            return false;


        } else if (et_disease.getText().toString().isEmpty()) {
            showAlertDialog("Please enter your disease description");
            return false;

        } else if (!isEmailValid(et_email.getText().toString())) {
            showAlertDialog("Please enter email in proper format");
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


    private void callSignupApi() {
        if (NetworkUtils.isNetworkAvailable(context)) {
            FragmentVolleyResponse volleyResponse = new FragmentVolleyResponse(context, true) {
                @Override
                public void onVolleyResponseGet(String response, int apiCall) throws JSONException {
                    JSONObject object = new JSONObject(response);
                    if (object.getBoolean("status") == true) {
                        Snackbar.make(et_confirm_password, "You have signed up successfully", Snackbar.LENGTH_SHORT).show();
                        JSONObject data = object.getJSONObject("data");
                        saveData.save(KEY_FNAME, data.getString("fname"));
                        saveData.save(KEY_LNAME, data.getString("lname"));
                        saveData.save(KEY_USERID, data.getString("id"));
                        saveData.save(KEY_EMAIL, data.getString("email"));
                        saveData.save(KEY_GENDER, data.getString("gender"));
                        saveData.save(KEY_USERTYPE, data.getString("user_type"));
                        saveData.save(IS_LOGGED_IN, true);

                        startActivity(new Intent(context, CustomerDashboardScreen.class));
                        finish();


                    } else {
                        // some error occured while signup
                        Snackbar.make(et_confirm_password, object.getString("message"), Snackbar.LENGTH_SHORT).show();
                    }
                }
            };
            HashMap<String, String> postRequest = new HashMap<>();
            postRequest.put(REQUEST_KEY_FNAME, et_first_name.getText().toString());
            postRequest.put(REQUEST_KEY_LNAME, et_lastname.getText().toString());
            postRequest.put(REQUEST_KEY_EMAIL, et_email.getText().toString());
            postRequest.put(REQUEST_KEY_PASSWORD, et_password.getText().toString());
            postRequest.put(REQUEST_KEY_USER_TYPE, "2");// 1 doctor, 2 customer
            postRequest.put(REQUEST_KEY_LAT, lat);
            postRequest.put(REQUEST_KEY_LNG, lng);
            postRequest.put(REQUEST_KEY_GENDER, gender);
            postRequest.put(REQUEST_KEY_CONFIRM_PASSWORD, et_confirm_password.getText().toString());
            postRequest.put(REQUEST_KEY_LOCATION, et_location.getText().toString());
            postRequest.put(REQUEST_KEY_DEGREE, "");
            postRequest.put(REQUEST_KEY_SPECIALIZATION, "");
            postRequest.put(REQUEST_KEY_FEE, "");
            postRequest.put(REQUEST_KEY_DISEASE, et_disease.getText().toString());
            postRequest.put(REQUEST_KEY_PACKAGEID, "");
            postRequest.put(REQUEST_KEY_DEVICE_TOKEN, saveData.getString(DEVICE_TOKEN));
            volleyResponse.getVolleyResponse(CaretomUrl.SIGNUP_URL, postRequest, MODE_CUSTOMER_SIGNUP, "Please wait...");

        } else {
            NetworkUtils.showInternetDialog(context);
        }
    }

    private void processLocationPermission() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION)) {
                // show explanation why this permission is needed


                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_CODE_ACCESS_LOCATION);

            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_CODE_ACCESS_LOCATION);
            }


        } else {
            System.out.println("permission is already granted= ");


            Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                    mGoogleApiClient);
            System.out.println("mLastLocation = " + mLastLocation);


            if (mLastLocation != null) {
                System.out.println("mLastLocation.getLatitude() = " + mLastLocation.getLatitude());
                System.out.println("mLastLocation.getLongitude() = " + mLastLocation.getLongitude());
                lat = String.valueOf(mLastLocation.getLatitude());
                lng = String.valueOf(mLastLocation.getLongitude());

            } else {
                LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);
                boolean enabled = service
                        .isProviderEnabled(LocationManager.GPS_PROVIDER);
                if (!enabled) {
                    startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                }

                // createLocationRequest();
            }
        }
    }


    private void initGoogleApiClient() {
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }


    }


    @Override
    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    private boolean isGooglePlayServicesAvailable() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(context);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(CustomerSignupActivity.this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
                        .show();
            } else {
                Log.i(TAG, "This device is not supported.");

            }
            return false;
        }
        return true;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        processLocationPermission();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


        switch (requestCode) {
            case REQUEST_CODE_ACCESS_LOCATION:

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission granted successfully

                    System.out.println("permission granted successfully = ");


                    processLocationPermission();


                } else {
                    //

                    System.out.println("permission could not be granted= ");
                }
                break;

        }
    }

}
