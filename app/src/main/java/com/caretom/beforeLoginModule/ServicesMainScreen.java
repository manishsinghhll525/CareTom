package com.caretom.beforeLoginModule;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.caretom.R;
import com.caretom.adapter.ServicesAdapter;

/**
 * Created by techelogy2 on 23/2/17.
 */

public class ServicesMainScreen extends AppCompatActivity {
    private Context context;
    private GridView gridView;

    private ServicesAdapter adapter;
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_main_screen);
        initWidgets();
        initWidgetListener();
        setupAdapter();
        setUpActionBar();
    }


    private void initWidgets() {
        context = ServicesMainScreen.this;
        gridView = (GridView) findViewById(R.id.gridView);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

    }

    private void initWidgetListener() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setListSelectedPosition(position);
              //  processListItemsClick(position);
            }
        });
    }

    private void setupAdapter() {
        adapter = new ServicesAdapter(context);
        gridView.setAdapter(adapter);
    }


    private void setUpActionBar() {
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        View logo = getLayoutInflater().inflate(R.layout.toolbar_row, null);
        LinearLayout ll_back = (LinearLayout) logo.findViewById(R.id.ll_back);
        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
        });

        TextView tv_title = (TextView) logo.findViewById(R.id.tv_title);
        tv_title.setText("Services");


        toolbar.addView(logo);

    }


}
