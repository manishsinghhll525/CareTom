package com.caretom.beforeLoginModule;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.caretom.MyApplication;
import com.caretom.R;
import com.caretom.common.BlogsCategoryModel;
import com.caretom.common.BlogsModel;
import com.caretom.common.BlogsUserModel;
import com.caretom.common.CaretomUrl;
import com.caretom.common.FragmentVolleyResponse;
import com.caretom.common.NetworkUtils;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.share.ShareApi;
import com.facebook.share.Sharer;
import com.facebook.share.internal.ShareFeedContent;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.ShareMediaContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by techelogy2 on 15/4/17.
 */

public class BlogsFragment extends Fragment {

    /* filterByRating (1 = > ASC, 2 => DESC)
     filterByCategory (Category Id)
     filterByAuthor (Author Id)
     filterByMonth (Month Number)
     startIndex*/
    private static final int MODE_GET_BLOGS = 101;
    private final String KEY_FILTER_BY_RATING = "filterByRating";
    private final String KEY_FILTER_BY_CATEGORY = "filterByCategory";
    private final String KEY_FILTER_BY_AUTHOR = "filterByAuthor";
    private final String KEY_FILTER_BY_MONTH = "filterByMonth";
    private final String KEY_START_INDEX = "startIndex";
    private String startIndex = "0";

    private RecyclerView recyclerView;
    View view;
    private BlogsListingAdapter adapter;
    private Context context;
    private MyApplication myApplication;
    ArrayList<BlogsModel> blogsList = new ArrayList<>();


    CallbackManager callbackManager;
    private int sharedPosition = -1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_blogs, container, false);
        initWidgets();
        initWidgetListener();
        callBlogsApi();
        registerForFacebookLogin();
        return view;
    }


    private void initWidgets() {
        callbackManager = CallbackManager.Factory.create();
        context = getActivity();
        myApplication = MyApplication.getIstance();
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));


    }


    private void registerForFacebookLogin() {
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        System.out.println("facebook onSuccess Has been called = " + loginResult);
                        showFacebookShareDialog();
                    }

                    @Override
                    public void onCancel() {
                        // App code
                        System.out.println("onCancel has been called = ");
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code

                        System.out.println("facebook error has been called = " + exception);
                    }
                });
    }

    private void initWidgetListener() {


    }

    private void callBlogsApi() {
        if (NetworkUtils.isNetworkAvailable(context)) {
            FragmentVolleyResponse volleyResponse = new FragmentVolleyResponse(context, true) {
                @Override
                public void onVolleyResponseGet(String response, int apiCall) throws JSONException {

                    JSONObject object = new JSONObject(response);
                    if (object.getBoolean("status") == true) {
                        JSONObject data = object.getJSONObject("data");
                        JSONArray blogs = data.getJSONArray("blogs");
                        for (int i = 0; i < blogs.length(); i++) {
                            JSONObject blogsObject = blogs.getJSONObject(i);
                            BlogsModel model = new BlogsModel();
                            model.setId(blogsObject.getInt("id"));
                            model.setTitle(blogsObject.getString("tittle"));
                            model.setDescription(blogsObject.getString("description"));
                            model.setUser_id(blogsObject.getString("user_id"));
                            model.setUser_name(blogsObject.getString("user_name"));
                            model.setCategory_id(blogsObject.getString("category_id"));
                            model.setPost_datetime(blogsObject.getString("post_datetime"));
                            model.setBlog_image(blogsObject.getString("blog_image"));
                            model.setStatus(blogsObject.getString("status"));
                            model.setRating(blogsObject.getInt("rating") + "");
                            BlogsUserModel blogsUserModel = new BlogsUserModel();
                            JSONObject userObject = blogsObject.getJSONObject("user");
                            blogsUserModel.setEmail(userObject.getString("email"));
                            blogsUserModel.setId(userObject.getInt("id"));
                            blogsUserModel.setFname(userObject.getString("fname"));
                            blogsUserModel.setLname(userObject.getString("lname"));
                            model.setBlogsUserModel(blogsUserModel);
                            JSONObject categoryObject = blogsObject.getJSONObject("category");
                            BlogsCategoryModel blogsCategoryModel = new BlogsCategoryModel();
                            blogsCategoryModel.setId(categoryObject.getInt("id"));
                            blogsCategoryModel.setCategory(categoryObject.getString("category"));
                            blogsCategoryModel.setStatus(categoryObject.getString("status"));
                            model.setBlogsCategoryModel(blogsCategoryModel);
                            blogsList.add(model);


                        }

                        setAdapter();


                    } else {
                        // no blogs found or some error occured
                    }


                }
            };
            HashMap<String, String> postRequest = new HashMap<>();
            postRequest.put(KEY_FILTER_BY_AUTHOR, myApplication.getFilterByAuthor());
            postRequest.put(KEY_FILTER_BY_RATING, myApplication.getFilterByRating());
            postRequest.put(KEY_FILTER_BY_CATEGORY, myApplication.getFilterByCategory());
            postRequest.put(KEY_FILTER_BY_MONTH, myApplication.getFilterByMonth());
            postRequest.put(KEY_START_INDEX, startIndex);
            volleyResponse.getVolleyResponse(CaretomUrl.GET_BLOGS, postRequest, MODE_GET_BLOGS, "Please wait receiving blogs...");


        } else {
            NetworkUtils.showInternetDialog(context);
        }
    }


    private void setAdapter() {
        adapter = new BlogsListingAdapter(blogsList, context) {
            @Override
            public void onShareClick(int position) {
                sharedPosition = position;
                System.out.println("on share click has been called = " + position);
                goToFacebookLogin();
            }
        };
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        System.out.println("on activity result of fragment has been called = " + requestCode);
    }


    private void goToFacebookLogin() {
        LoginManager.getInstance().logInWithPublishPermissions(getActivity(), Arrays.asList("publish_actions"));
    }

    private void showFacebookShareDialog() {
        final Bitmap[] bitmap = {null};
        android.support.v7.app.AlertDialog.Builder dialogBuilder = new android.support.v7.app.AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.share_on_facebook_dialog, null);
        dialogBuilder.setView(view);
        final android.support.v7.app.AlertDialog alertDialog = dialogBuilder.create();
        final ImageView img_share = (ImageView) view.findViewById(R.id.img_share);
        TextView tv_cancel = (TextView) view.findViewById(R.id.tv_cancel);
        final EditText et_category = (EditText) view.findViewById(R.id.et_category);
        TextView tv_post = (TextView) view.findViewById(R.id.tv_post);

        SimpleTarget target = new SimpleTarget<Bitmap>() {


            @Override
            public void onResourceReady(Bitmap bitmap2, GlideAnimation glideAnimation) {
                bitmap[0] = bitmap2;
                img_share.setImageBitmap(bitmap2);
            }
        };
        Glide.with(context).load(blogsList.get(sharedPosition).getBlog_image()).asBitmap().into(target);


        et_category.setText(blogsList.get(sharedPosition).getTitle());
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        tv_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bitmap image = bitmap[0];
                SharePhoto photo = new SharePhoto.Builder()
                        .setBitmap(image)
                        .setCaption(et_category.getText().toString())

                        .build();

                SharePhotoContent content = new SharePhotoContent.Builder()
                        .addPhoto(photo)
                        .build();

                //  new ShareApi(content).setMessage(et_category.getText().toString());
                ShareApi.share(content, new FacebookCallback<Sharer.Result>() {
                    @Override
                    public void onSuccess(Sharer.Result result) {
                        System.out.println("successfully shared on facebook = " + result);
                        Snackbar.make(recyclerView, "Successfully shared on Facebook", Snackbar.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onCancel() {
                        System.out.println("sharing  cancelled= ");
                        Snackbar.make(recyclerView, "Sharing has been cancelled", Snackbar.LENGTH_SHORT).show();


                    }

                    @Override
                    public void onError(FacebookException error) {
                        System.out.println("some error has occured while sharing on facebook");
                        Snackbar.make(recyclerView, "Some Error occurred while sharing on Facebook", Snackbar.LENGTH_SHORT).show();
                        error.printStackTrace();

                    }
                });

                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }
}
