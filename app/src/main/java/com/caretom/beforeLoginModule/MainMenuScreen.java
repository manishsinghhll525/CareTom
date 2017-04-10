package com.caretom.beforeLoginModule;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.caretom.R;
import com.caretom.adapter.NavigationAdapter;

/**
 * Created by techelogy2 on 18/3/17.
 */

public class MainMenuScreen extends AppCompatActivity implements NavigationAdapter.NavigationItemClickListener, ServicesFragment.ServiceFragmentClickListener {
    private final String TAG_PACKAGES_FRAGMENT = "packages";
    private final String TAG_SERVICES_FRAGMENT = "services";
    private final String TAG_CONTACT_US_FRAGMENT = "ContactUs";
    private final String TAG_FRAGMENT_ABOUT_US = "AboutUs";
    private final int TAG_SERVICES = 0;
    private final int TAG_PACKAGES = 1;
    private final int TAG_BLOG = 2;
    private final int TAG_LOGIN = 3;
    private final int TAG_ABOUT_US = 4;
    private final int TAG_CONTACT = 5;


    private final String TAG_FREE_PACKAGES_FRAGMENT = "freePackages";
    private final int YOUR_DHR = 0;
    private final int BOOK_A_DOC = 1;
    private final int EMERGENCY = 2;
    private final int DIET_CHART = 3;
    private final int FREE_PACKAGES = 4;
    private final int HEALTH_TIPS = 5;


    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private FrameLayout frame_container;
    private RecyclerView recyclerView;
    private NavigationAdapter adapter;
    TextView tv_title;

    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_screen);
        initWidgets();
        initWidgetListener();
        setUpActionBar();
        setUpNavigationListener();
        loadDefaultServicesFragment();
    }


    private void initWidgets() {
        context = MainMenuScreen.this;
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        frame_container = (FrameLayout) findViewById(R.id.frame_container);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        adapter = new NavigationAdapter(context);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }

    private void initWidgetListener() {


    }


    private void setUpActionBar() {
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        View logo = getLayoutInflater().inflate(R.layout.toolbar_row2, null);
       /* LinearLayout ll_back = (LinearLayout) logo.findViewById(R.id.ll_back);
        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
        });*/

        tv_title = (TextView) logo.findViewById(R.id.tv_title);
        tv_title.setText("Menu");


        toolbar.addView(logo);

    }


    private void setUpNavigationListener() {
       /* navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawers();
                switch (item.getItemId()) {
                    case R.id.home:
                        return true;

                    case R.id.dashboard:
                        System.out.println("dashboard has been clicked = ");
                        loadPTDashboard();
                        return true;

                    case R.id.editProfile:
                        loadEditProfile();
                        return true;

                    case R.id.viewProfile:
                        loadPtViewProfile();
                        return true;

                    case R.id.mySettings:
                        loadMYSettings();
                        return true;

                    case R.id.reviews:
                        loadReviews();
                        return true;

                    case R.id.faq:
                        loadFAQ();
                        return true;

                    case R.id.logout:
                        return true;


                }

                return true;
                // above method returns true to display the item as selected item
            }
        });
*/
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank

                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessay or else your hamburger icon wont show up


        actionBarDrawerToggle.setDrawerIndicatorEnabled(false);
        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.menu, getTheme());
        actionBarDrawerToggle.setHomeAsUpIndicator(drawable);
        actionBarDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });


        actionBarDrawerToggle.syncState();


    }

    @Override
    public void onNavigationItemClick(int tag) {
        switch (tag) {
            case TAG_SERVICES:
                drawerLayout.closeDrawers();
                loadDefaultServicesFragment();

                break;

            case TAG_PACKAGES:
                drawerLayout.closeDrawers();
                loadPackagesFragment();
                break;

            case TAG_BLOG:
                drawerLayout.closeDrawers();

                break;

            case TAG_LOGIN:
                drawerLayout.closeDrawers();


                break;

            case TAG_ABOUT_US:
                drawerLayout.closeDrawers();
                loadAboutUsFragment();
                break;

            case TAG_CONTACT:
                drawerLayout.closeDrawers();
                loadContactUsFragment();
                break;


        }
    }


    private void loadAboutUsFragment() {
        tv_title.setText("ABOUT CARETOM");
        AboutUsFragment aboutUsFragment = new AboutUsFragment();


        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, aboutUsFragment, TAG_FRAGMENT_ABOUT_US);
        transaction.commit();
    }

    private void loadContactUsFragment() {
        tv_title.setText("CONTACT CARETOM");
        ContactUsFragment contactUsFragment = new ContactUsFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, contactUsFragment, TAG_CONTACT_US_FRAGMENT);
        transaction.commit();
    }

    private void loadDefaultServicesFragment() {
        tv_title.setText("SERVICES");
        ServicesFragment servicesFragment = new ServicesFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, servicesFragment, TAG_SERVICES_FRAGMENT);
        transaction.commit();

    }


    private void loadPackagesFragment() {
        tv_title.setText("PACKAGES");
        FreePackagesFragment packagesFragment = new FreePackagesFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, packagesFragment, TAG_PACKAGES_FRAGMENT);
        transaction.commit();
    }

    @Override
    public void onServiceItemClicked(int position) {

        switch (position) {
            case YOUR_DHR:
                break;

            case BOOK_A_DOC:
                break;
            case EMERGENCY:
                break;
            case DIET_CHART:
              //  loadContactUsFragment();
                break;
            case FREE_PACKAGES:
                loadPackagesFragment();

                break;

            case HEALTH_TIPS:
                break;
        }


    }

   /* private void loadFreePackagesFragment() {

        FreePackagesFragment freePackages = new FreePackagesFragment();

        FragmentManager manager = getSupportFragmentManager();

        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.frame_container, freePackages, TAG_FREE_PACKAGES_FRAGMENT);
        transaction.commit();
    }*/
}
