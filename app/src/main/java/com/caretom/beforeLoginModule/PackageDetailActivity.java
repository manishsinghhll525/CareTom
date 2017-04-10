package com.caretom.beforeLoginModule;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.caretom.R;
import com.caretom.customViews.CustomTextView;
import com.caretom.models.NoteModel;
import com.caretom.models.PackageInclusionModel;
import com.caretom.models.PackageModel;

import java.util.ArrayList;

/**
 * Created by techelogy2 on 22/3/17.
 */

public class PackageDetailActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private LinearLayout ll_package_content, ll_note_content;
    private CustomTextView tv_note;

    private Context context;
    private PackageModel packageModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_detail_screen);
        initWidgets();
        setUpActionBar();
        initWidgetListener();
        setupContentInUi();

    }


    private void initWidgets() {
        context = PackageDetailActivity.this;
        packageModel = (PackageModel) getIntent().getSerializableExtra("detail");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        ll_package_content = (LinearLayout) findViewById(R.id.ll_package_content);
        ll_note_content = (LinearLayout) findViewById(R.id.ll_note_content);
        tv_note = (CustomTextView) findViewById(R.id.tv_note);
    }

    private void initWidgetListener() {


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
        tv_title.setText(packageModel.getPackageName());


        toolbar.addView(logo);

    }

    private void setupContentInUi() {

        ArrayList<PackageInclusionModel> inclusionList = packageModel.getPackageInclusionList();

        for (int i = 0; i < inclusionList.size(); i++) {


            LinearLayout linearLayout = new LinearLayout(context);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(15, 5, 10, 10);
            linearLayout.setLayoutParams(params);
            // linearLayout.setGravity(Gravity.CENTER_VERTICAL);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout.setPadding(0, 15, 0, 5);


            ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            imageView.setImageResource(R.drawable.verified_icon);
             imageView.setPadding(15, 3, 10, 3);
            linearLayout.addView(imageView);


            CustomTextView subFeatureText = new CustomTextView(context);
            subFeatureText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            String packageInfo="<b>"+inclusionList.get(i).getHeading() + " : "+"</b>" + inclusionList.get(i).getDescription();
            subFeatureText.setText(Html.fromHtml(packageInfo));

            //  subFeatureText.setPadding(20, 10, 20, 15); // in pixels (left, top, right, bottom)
            linearLayout.addView(subFeatureText);


            ll_package_content.addView(linearLayout);

        }


        ArrayList<NoteModel> noteList = packageModel.getNoteList();

        if (noteList.size() > 0) {
            tv_note.setVisibility(View.VISIBLE);
        } else {
            tv_note.setVisibility(View.GONE);
        }

        for (int i = 0; i < noteList.size(); i++) {


            LinearLayout linearLayout = new LinearLayout(context);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(15, 5, 10, 10);
            linearLayout.setLayoutParams(params);
            // linearLayout.setGravity(Gravity.CENTER_VERTICAL);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout.setPadding(0, 15, 0, 5);


            ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            imageView.setImageResource(R.drawable.verified_icon);
            imageView.setPadding(15, 3, 10, 3);
            linearLayout.addView(imageView);


            CustomTextView subFeatureText = new CustomTextView(context);
            subFeatureText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));

            String packageInfo="<b>"+inclusionList.get(i).getHeading() + " : "+"</b>" + inclusionList.get(i).getDescription();
            subFeatureText.setText(Html.fromHtml(packageInfo));

            //  subFeatureText.setPadding(20, 10, 20, 15); // in pixels (left, top, right, bottom)
            linearLayout.addView(subFeatureText);

            ll_note_content.addView(linearLayout);

        }

    }

}
