package com.caretom.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.caretom.R;

/**
 * Created by techelogy2 on 18/3/17.
 */

public class NavigationAdapter extends RecyclerView.Adapter<NavigationAdapter.MyViewHolder> {
    public interface NavigationItemClickListener {
        public abstract void onNavigationItemClick(int tag);
    }

    NavigationItemClickListener mCallback;
    Context context;
    String[] navigationItems;

    public NavigationAdapter(Context context) {
        this.context = context;
        navigationItems = context.getResources().getStringArray(R.array.navigation_options);
        mCallback = (NavigationItemClickListener) context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_content_name;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_content_name = (TextView) itemView.findViewById(R.id.tv_content_name);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = ((LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.row_navigation_items, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.tv_content_name.setText(navigationItems[position]);
        holder.tv_content_name.setTag(position);
        holder.tv_content_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onNavigationItemClick((Integer) holder.tv_content_name
                        .getTag());
            }
        });

    }


    @Override
    public int getItemCount() {
        return navigationItems.length;
    }
}
