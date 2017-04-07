package com.caretom.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.caretom.R;

/**
 * Created by techelogy2 on 23/2/17.
 */

public class MenuScreenAdapter extends BaseAdapter {
    private Context context;
    String headingList[];
    int listSelectedPosition = -1;
    int[] colored_icons = {R.drawable.services_colored, R.drawable.services_colored, R.drawable.blogs_colored, R.drawable.login_colored, R.drawable.heartbeat_colored, R.drawable.phone_colored};
    int[] white_icons = {R.drawable.services, R.drawable.services, R.drawable.blogs, R.drawable.login, R.drawable.heartbeat, R.drawable.phone};

    public MenuScreenAdapter(Context context) {
        this.context = context;
        headingList = context.getResources().getStringArray(R.array.menu_headings);
    }

    public void setListSelectedPosition(int position) {
        listSelectedPosition = position;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return 6;
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
        Holder holder = null;
        if (convertView == null) {
            convertView = ((LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.row_menu_screen, parent, false);

            holder = new Holder();
            holder.cardView = (CardView) convertView.findViewById(R.id.cardView);
            holder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
            holder.tv_heading = (TextView) convertView.findViewById(R.id.tv_heading);
            convertView.setTag(holder);

        } else {
            holder = (Holder) convertView.getTag();

        }

        if (listSelectedPosition == position) {
            holder.tv_heading.setTextColor(context.getResources().getColor(R.color.whiteColor));
            holder.cardView.setBackgroundColor(context.getResources().getColor(R.color.menu_screen_item_hovered));
            holder.imageView.setImageResource(white_icons[position]);
        } else {
            holder.tv_heading.setTextColor(context.getResources().getColor(R.color.menu_screen_item_hovered));
            holder.cardView.setBackgroundColor(context.getResources().getColor(R.color.whiteColor));
            holder.imageView.setImageResource(colored_icons[position]);
        }

        holder.tv_heading.setText(headingList[position]);


        return convertView;
    }

    private class Holder {
        CardView cardView;
        ImageView imageView;
        TextView tv_heading;
    }
}
