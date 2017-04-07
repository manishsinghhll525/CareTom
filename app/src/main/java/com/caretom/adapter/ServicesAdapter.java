package com.caretom.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
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

public class ServicesAdapter extends BaseAdapter {
    Context context;
    String servicesHedaingList[];
    int listSelectedPosition = -1;


    int[] backgroundImage = new int[]{R.drawable.your_dhr_bg, R.drawable.diet_chart_bg, R.drawable.emergency_bg, R.drawable.book_a_doc_bg, R.drawable.free_ccheckup_bg, R.drawable.health_tips_bg};
    int[] whiteIcon = {R.drawable.dhr, R.drawable.diet_chart, R.drawable.emergency, R.drawable.doc, R.drawable.services, R.drawable.health_tips};


    public ServicesAdapter(Context context) {
        this.context = context;
        servicesHedaingList = context.getResources().getStringArray(R.array.services_main_headings);
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




    /*    if (listSelectedPosition == position) {
            holder.tv_heading.setTextColor(context.getResources().getColor(R.color.whiteColor));
            holder.cardView.setBackgroundColor(context.getResources().getColor(R.color.menu_screen_item_hovered));
            holder.imageView.setImageResource(whiteIcon[position]);
        } else {
            holder.tv_heading.setTextColor(context.getResources().getColor(R.color.menu_screen_item_hovered));
            holder.cardView.setBackgroundColor(context.getResources().getColor(R.color.whiteColor));
            holder.imageView.setImageResource(coloredIcon[position]);
        }
*/


        holder.tv_heading.setTextColor(context.getResources().getColor(R.color.whiteColor));
        holder.cardView.setBackgroundColor(context.getResources().getColor(R.color.menu_screen_item_hovered));
        holder.imageView.setImageResource(whiteIcon[position]);

        holder.cardView.setBackgroundResource(backgroundImage[position]);

        holder.tv_heading.setText(servicesHedaingList[position]);
        return convertView;

    }

    private class Holder {
        CardView cardView;
        ImageView imageView;
        TextView tv_heading;
    }
}
