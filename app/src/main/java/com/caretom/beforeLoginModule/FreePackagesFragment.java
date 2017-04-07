package com.caretom.beforeLoginModule;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.caretom.R;
import com.caretom.adapter.PackageListingAdapter;
import com.caretom.common.CaretomUrl;
import com.caretom.common.FragmentVolleyResponse;
import com.caretom.common.NetworkUtils;
import com.caretom.models.NoteModel;
import com.caretom.models.PackageInclusionModel;
import com.caretom.models.PackageModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by techelogy2 on 21/3/17.
 */

public class FreePackagesFragment extends Fragment {
    private final int MODE_GET_PACKAGES = 101;
    View view;

    private ListView listview;
    private Context context;
    private ArrayList<PackageModel> packageList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_free_packages, container, false);
        initWidgets();
        initWidgetListener();
        callGetPackagesApi();

        return view;
    }


    private void initWidgets() {
        context = getActivity();
        listview = (ListView) view.findViewById(R.id.listview);
    }

    private void initWidgetListener() {
    }

    private void callGetPackagesApi() {
        if (NetworkUtils.isNetworkAvailable(context)) {

            FragmentVolleyResponse volleyResponse = new FragmentVolleyResponse(context, true) {
                @Override
                public void onVolleyResponseGet(String response, int apiCall) throws JSONException {
                    parseJsonResponse(response);

                }
            };


            volleyResponse.volleyResponseByGET(CaretomUrl.GET_PACKAGES_URL, MODE_GET_PACKAGES, "Please wait retrieving packages...");

        } else {
            NetworkUtils.showInternetDialog(context);
        }
    }


    private void parseJsonResponse(String response) {
        try {
            JSONObject object = new JSONObject(response);
            if (object.getBoolean("status") == true) {

                JSONArray data = object.getJSONArray("data");
                for (int i = 0; i < data.length(); i++) {
                    JSONObject modelObj = data.getJSONObject(i);
                    PackageModel model = new PackageModel();
                    model.setId(modelObj.getInt("id"));
                    model.setPackageName(modelObj.getString("package_name"));
                    model.setPrice(modelObj.getString("price"));
                    model.setStatus(modelObj.getString("status"));
                    model.setFreeStatus(modelObj.getString("free_status"));
                    JSONArray inclusions = modelObj.getJSONArray("inclusion");
                    ArrayList<PackageInclusionModel> packageInclusinList = new ArrayList<>();
                    for (int j = 0; j < inclusions.length(); j++) {
                        JSONObject inclusionObject = inclusions.getJSONObject(j);
                        PackageInclusionModel packageInclusionModel = new PackageInclusionModel();
                        packageInclusionModel.setId(inclusionObject.getInt("id"));
                        packageInclusionModel.setPackageId(inclusionObject.getString("package_id"));
                        packageInclusionModel.setHeading(inclusionObject.getString("heading"));
                        packageInclusionModel.setDescription(inclusionObject.getString("description"));
                        packageInclusinList.add(packageInclusionModel);
                    }
                    model.setPackageInclusionList(packageInclusinList);

                    JSONArray note = modelObj.getJSONArray("note");
                    ArrayList<NoteModel> noteList = new ArrayList<>();

                    for (int j = 0; j < note.length(); j++) {
                        JSONObject noteObj = note.getJSONObject(j);
                        NoteModel noteModel = new NoteModel();
                        noteModel.setId(noteObj.getInt("id"));
                        noteModel.setPackageId(noteObj.getString("package_id"));
                        noteModel.setDescription(noteObj.getString("note_description"));
                        noteList.add(noteModel);
                    }
                    model.setNoteList(noteList);

                    packageList.add(model);


                }

                setAdapter();
            } else {
                Snackbar.make(listview, object.getString("message"), Snackbar.LENGTH_LONG).show();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    private void setAdapter() {
        PackageListingAdapter adapter = new PackageListingAdapter(context, packageList);
        listview.setAdapter(adapter);
    }

}
