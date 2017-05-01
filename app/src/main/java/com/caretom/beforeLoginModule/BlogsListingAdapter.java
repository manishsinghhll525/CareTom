package com.caretom.beforeLoginModule;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.caretom.R;
import com.caretom.common.BlogsModel;
import com.caretom.customViews.CustomTextView;

import java.util.ArrayList;

/**
 * Created by techelogy2 on 17/4/17.
 */

public abstract class BlogsListingAdapter extends RecyclerView.Adapter<BlogsListingAdapter.BlogsViewHolder> {


    public abstract void onShareClick(int position);


    ArrayList<BlogsModel> blogsList;
    Context context;

    public BlogsListingAdapter(ArrayList<BlogsModel> blogsList, Context context) {
        this.blogsList = blogsList;
        this.context = context;


    }

    @Override
    public BlogsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = ((LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.row_blogs_listing, parent, false);
        BlogsViewHolder viewHolder = new BlogsViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BlogsViewHolder holder, final int position) {
        BlogsModel model = blogsList.get(position);
        holder.tv_blog_title.setText(model.getTitle());
        Glide.with(context).load(model.getBlog_image()).into(holder.img_blog);

        holder.img_blog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        holder.tv_blog_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BlogDetailActivity.class);
                intent.putExtra("blogModel", blogsList.get(position));
                context.startActivity(intent);

            }
        });

        holder.img_share_fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onShareClick(position);
            }
        });

    }


    @Override
    public int getItemCount() {
        return blogsList.size();
    }

    public class BlogsViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        CustomTextView tv_blog_title, tv_readMore;
        ImageView img_blog, img_share_fb;

        public BlogsViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
            tv_blog_title = (CustomTextView) itemView.findViewById(R.id.tv_blog_title);
            tv_readMore = (CustomTextView) itemView.findViewById(R.id.tv_readMore);
            img_blog = (ImageView) itemView.findViewById(R.id.img_blog);
            img_share_fb = (ImageView) itemView.findViewById(R.id.img_share_fb);

        }
    }
}
