package com.caretom.common;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.caretom.R;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by techelogy2 on 19/3/17.
 */

public abstract class FragmentVolleyResponse {


    private Context context;
    private ProgressDialog progressDialog;
    private boolean isProgressVisible;


    public abstract void onVolleyResponseGet(String response, int apiCall) throws JSONException;


    public FragmentVolleyResponse(Context context, boolean isProgressVisible) {

        this.context = context;
        this.isProgressVisible = isProgressVisible;


    }

    private void showProgressDialog() {

        if (isProgressVisible) {

            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage("Wait...");
            progressDialog.show();

        }
    }

    private void showProgressDialog(String message) {

        if (isProgressVisible) {

            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage(message);
            progressDialog.show();

        }
    }

    private void dismissProgressDialog() {

        if (isProgressVisible) {

            progressDialog.dismiss();

        }
    }

    public String getVolleyResponse(String URL, final HashMap<String, String> postParams, final int apiCall) {

        showProgressDialog();
        // printMap(postParams);

        RequestQueue queue = Volley.newRequestQueue(context);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        dismissProgressDialog();
                        Log.v("RESPONSE", response);
                        try {
                            onVolleyResponseGet(response, apiCall);
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                dismissProgressDialog();
                System.out.println("error.printStackTrace(); = " + error.toString());
                showAlertDialog("server time out error occured");
//                Toast.makeText(context, "Volley Error: " + error.getMessage(), Toast.LENGTH_LONG).show();

            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub

                return postParams;
            }

        };
        // Add the request to the RequestQueue.
        int socketTimeout = 10000;//15 seconds - change to what you want

        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        queue.add(stringRequest);


        return null;
    }


    public String getVolleyResponse(String URL, final HashMap<String, String> postParams, final int apiCall, String proressDialogtext) {

        showProgressDialog(proressDialogtext);
        // printMap(postParams);

        RequestQueue queue = Volley.newRequestQueue(context);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        dismissProgressDialog();
                        Log.v("RESPONSE", response);
                        try {
                            onVolleyResponseGet(response, apiCall);
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                dismissProgressDialog();
                System.out.println("error.printStackTrace(); = " + error.toString());
                showAlertDialog("server time out error occured");
//                Toast.makeText(context, "Volley Error: " + error.getMessage(), Toast.LENGTH_LONG).show();

            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub

                return postParams;
            }

        };
        // Add the request to the RequestQueue.
        int socketTimeout = 20000;//15 seconds - change to what you want

        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        queue.add(stringRequest);


        return null;
    }


    //
    public String volleyResponseByGET(String URL, final int apiCall, String progressDialogtext) {

        showProgressDialog(progressDialogtext);
        // printMap(postParams);

        RequestQueue queue = Volley.newRequestQueue(context);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        dismissProgressDialog();
                        Log.v("RESPONSE", response);
                        try {
                            onVolleyResponseGet(response, apiCall);
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                dismissProgressDialog();
                System.out.println("error.printStackTrace(); = " + error.toString());
//                Toast.makeText(context, "Volley Error: " + error.getMessage(), Toast.LENGTH_LONG).show();
                showAlertDialog("server time out error occured");
            }
        }) {


        };
        // Add the request to the RequestQueue.
        int socketTimeout = 10000;//15 seconds - change to what you want

        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        queue.add(stringRequest);


        return null;
    }

//    public static void printMap(Map mp) {
//        Iterator it = mp.entrySet().iterator();
//        while (it.hasNext()) {
//            Map.Entry pair = (Map.Entry)it.next();
//            System.out.println(pair.getKey() + " = " + pair.getValue());
//            it.remove(); // avoids a ConcurrentModificationException
//        }
//    }


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

}





