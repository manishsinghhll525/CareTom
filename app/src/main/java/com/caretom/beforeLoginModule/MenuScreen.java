package com.caretom.beforeLoginModule;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.caretom.R;
import com.caretom.adapter.MenuScreenAdapter;

/**
 * Created by techelogy2 on 22/2/17.
 */

public class MenuScreen extends AppCompatActivity {
    private Context context;

    private GridView gridView;
    private Toolbar toolbar;
    private MenuScreenAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_screen);
        initWidgets();
        initWidgetListener();
        setUpAdapter();
        setUpActionBar();
    }


    private void initWidgets() {
        context = MenuScreen.this;
        gridView = (GridView) findViewById(R.id.gridView);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    private void initWidgetListener() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setListSelectedPosition(position);
                processListItemsClick(position);
            }
        });
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
        tv_title.setText("Menu");


        toolbar.addView(logo);

    }

   /* private void setUpActionBar() {
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
        TextView tv_title = (TextView) logo.findViewById(R.id.tv_title);
        tv_title.setText("Features");


        toolbar.addView(logo);

    }*/

    private void setUpAdapter() {
        adapter = new MenuScreenAdapter(context);
        gridView.setAdapter(adapter);
    }

    private void processListItemsClick(int position) {
        switch (position) {
            case 0:
                Intent serviceIntent = new Intent(context, ServicesMainScreen.class);
                startActivity(serviceIntent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);

                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
        }
    }
}
