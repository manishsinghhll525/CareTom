package com.caretom.beforeLoginModule;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.caretom.R;
import com.caretom.common.BlogsModel;
import com.caretom.common.SaveData;
import com.caretom.customViews.CustomTextView;

/**
 * Created by techelogy2 on 26/4/17.
 */

public class BlogDetailActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ImageView img_blog;
    private CustomTextView tv_blog_title, tv_blog_description;
    private SaveData saveData;
    private Context context;

    private BlogsModel blogsModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_detail);
        initWidgets();
        initWidgetListener();
        setUpActionBar();
    }


    private void initWidgets() {
        context = BlogDetailActivity.this;
        saveData = new SaveData(context);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        img_blog = (ImageView) findViewById(R.id.img_blog);
        tv_blog_title = (CustomTextView) findViewById(R.id.tv_blog_title);
        tv_blog_description = (CustomTextView) findViewById(R.id.tv_blog_description);
        blogsModel = (BlogsModel) getIntent().getSerializableExtra("blogModel");
    }

    private void initWidgetListener() {
    }

    private void setUpActionBar() {
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
        CustomTextView tv_title = (CustomTextView) logo.findViewById(R.id.tv_title);
        tv_title.setText("Blog Detail");


        toolbar.addView(logo);

    }
}
