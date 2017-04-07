package com.caretom.beforeLoginModule;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.caretom.R;
import com.caretom.adapter.ServicesAdapter;

/**
 * Created by techelogy2 on 19/3/17.
 */

public class ServicesFragment extends Fragment {
    public interface ServiceFragmentClickListener {
        public abstract void onServiceItemClicked(int position);
    }




    View view;
    private GridView gridView;
    private ServicesAdapter adapter;
    private Context context;
    ServiceFragmentClickListener mCallback;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallback = (ServiceFragmentClickListener) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.activity_services_main_screen, container, false);
        initWidgets();
        initWidgetListener();
        setupAdapter();
        return view;
    }


    private void initWidgets() {
        gridView = (GridView) view.findViewById(R.id.gridView);
        context = getActivity();
    }

    private void initWidgetListener() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("inside on click = " + parent);


                mCallback.onServiceItemClicked(position);

            }
        });
    }

    private void setupAdapter() {
        adapter = new ServicesAdapter(context);
        gridView.setAdapter(adapter);
    }


}
