package com.caretom.beforeLoginModule;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.caretom.R;
import com.caretom.common.CaretomUrl;
import com.caretom.common.FragmentVolleyResponse;
import com.caretom.common.NetworkUtils;
import com.caretom.customViews.CustomTextView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by techelogy2 on 19/3/17.
 */

public class AboutUsFragment extends Fragment {
    private final int MODE_GET_ABOUT_US = 101;
    View view;
    private Context context;
    private CustomTextView tv_title, tv_about_us_content;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_about_us, container, false);
        initWidgets();

        callAboutUsApi();
        return view;
    }


    private void initWidgets() {
        context = getActivity();
        tv_about_us_content = (CustomTextView) view.findViewById(R.id.tv_about_us_content);
        tv_title = (CustomTextView) view.findViewById(R.id.tv_title);
    }

    private void callAboutUsApi() {


        if (NetworkUtils.isNetworkAvailable(context)) {


            FragmentVolleyResponse volleyresponse = new FragmentVolleyResponse(context, true) {
                @Override
                public void onVolleyResponseGet(String response, int apiCall) throws JSONException {
                    JSONObject responseObj = new JSONObject(response);
                    if (responseObj.getBoolean("status")) {

                        // successful response has found

                        JSONObject data = responseObj.getJSONObject("data");
                        String content = data.getString("content");
                        String headingText = data.getString("title");

                        showContentInUi(content, headingText);

                    } else {
                        // error has occured
                    }


                }
            };
            volleyresponse.volleyResponseByGET(CaretomUrl.ABOUT_US_URL, MODE_GET_ABOUT_US, "Please wait...");


        } else {
            NetworkUtils.showInternetDialog(context);
        }

    }

    private void showContentInUi(String content, String headingText) {
        tv_about_us_content.setText(Html.fromHtml(content));
        tv_title.setText(headingText);
    }

}



