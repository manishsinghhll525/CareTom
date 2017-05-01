package com.caretom.common;

import java.io.Serializable;

/**
 * Created by techelogy2 on 16/4/17.
 */

public class BlogsModel implements Serializable{
    private int id;
    private String title;
    private String description;
    private String user_id;
    private String user_name;
    private String category_id;
    private String post_datetime;
    private String blog_image;
    private String status;
    private String rating;
    private BlogsCategoryModel blogsCategoryModel;
    private BlogsUserModel blogsUserModel;

    public BlogsCategoryModel getBlogsCategoryModel() {
        return blogsCategoryModel;
    }

    public void setBlogsCategoryModel(BlogsCategoryModel blogsCategoryModel) {
        this.blogsCategoryModel = blogsCategoryModel;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getPost_datetime() {
        return post_datetime;
    }

    public void setPost_datetime(String post_datetime) {
        this.post_datetime = post_datetime;
    }

    public String getBlog_image() {
        return blog_image;
    }

    public void setBlog_image(String blog_image) {
        this.blog_image = blog_image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }


    public BlogsUserModel getBlogsUserModel() {
        return blogsUserModel;
    }

    public void setBlogsUserModel(BlogsUserModel blogsUserModel) {
        this.blogsUserModel = blogsUserModel;
    }

}
