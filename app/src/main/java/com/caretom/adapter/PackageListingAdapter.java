package com.caretom.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import com.caretom.R;
import com.caretom.beforeLoginModule.PackageDetailActivity;
import com.caretom.customViews.CustomTextView;
import com.caretom.models.PackageModel;

import java.util.ArrayList;

/**
 * Created by techelogy2 on 21/3/17.
 */

public class PackageListingAdapter extends BaseAdapter {
    Context context;
    ArrayList<PackageModel> packageList;

    public PackageListingAdapter(Context context, ArrayList<PackageModel> packageList) {
        this.context = context;
        this.packageList = packageList;
    }

    @Override
    public int getCount() {
        return packageList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = ((LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.row_package_listing, parent, false);
            holder.ll_package = (LinearLayout) convertView.findViewById(R.id.ll_package);
            holder.tv_package_name = (CustomTextView) convertView.findViewById(R.id.tv_package_name);
            holder.tv_package_price = (CustomTextView) convertView.findViewById(R.id.tv_package_price);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final PackageModel model = packageList.get(position);
        holder.tv_package_name.setText(model.getPackageName().toUpperCase());
        if (model.getFreeStatus().equalsIgnoreCase("1")) {

            // this represents browse free
            holder.tv_package_price.setText("");
        } else {
            holder.tv_package_price.setText("RS." + model.getPrice() + "/MONTH");
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, PackageDetailActivity.class);
                intent.putExtra("detail", model);
                context.startActivity(intent);

            }
        });
        return convertView;
    }

    private class ViewHolder {
        LinearLayout ll_package;
        CustomTextView tv_package_name;
        CustomTextView tv_package_price;
    }
}
