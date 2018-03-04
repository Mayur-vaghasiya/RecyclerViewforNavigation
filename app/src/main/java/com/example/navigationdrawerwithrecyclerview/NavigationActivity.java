package com.example.navigationdrawerwithrecyclerview;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.navigationdrawerwithrecyclerview.constants.Constants;
import com.example.navigationdrawerwithrecyclerview.fragment.AboutFragment;
import com.example.navigationdrawerwithrecyclerview.fragment.FeedsFragment;
import com.example.navigationdrawerwithrecyclerview.fragment.NavigationFragment;
import com.example.navigationdrawerwithrecyclerview.fragment.NewsFragment;
import com.example.navigationdrawerwithrecyclerview.fragment.PopularTagsFragment;
import com.example.navigationdrawerwithrecyclerview.fragment.SettingsFragment;
import com.example.navigationdrawerwithrecyclerview.fragment.WatchLiveFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.view.Gravity.LEFT;
import static android.view.Gravity.RIGHT;

public class NavigationActivity extends AppCompatActivity {

    DrawerLayout drawer;
    Toolbar toolbar;

    ImageView toolbar_ivNavigation;
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);


        toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setNavigationIcon(null);
        setSupportActionBar(toolbar);

        toolbar_ivNavigation=(ImageView)findViewById(R.id.toolbar_ivNavigation);

        toolbar_ivNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCloseDrawer();
            }
        });

        tvTitle = (TextView) findViewById(R.id.toolbar_tvTitle);

        drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, null, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        replaceFragment(0);
        replaceNavigationFragment();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(LEFT)) {
            drawer.closeDrawer(LEFT);
        }
        else {
            super.onBackPressed();
        }
    }

    private void openCloseDrawer() {
        if (drawer.isDrawerOpen(LEFT)) drawer.closeDrawer(LEFT);
        else drawer.openDrawer(LEFT);
    }

    public void replaceNavigationFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flContainerNavigationMenu, NavigationFragment.newInstance(), "Navigation").commit();
    }

    public void replaceFragment(int position) {
        Fragment fragment = null;
        String tag = null;

        switch (position) {
            case 0:
                fragment = NewsFragment.newInstance();
                tag = Constants.TAG_FRG_NEWS;
                break;
            case 1:
                fragment = FeedsFragment.newInstance();
                tag = Constants.TAG_FRG_FEEDS;
                break;
            case 2:
                fragment = WatchLiveFragment.newInstance();
                tag = Constants.TAG_FRG_WATCH_LIVE;
                break;
            case 3:
                fragment = PopularTagsFragment.newInstance();
                tag = Constants.TAG_FRG_POPULAR_TAGS;
                break;
            case 4:
                fragment = SettingsFragment.newInstance();
                tag = Constants.TAG_FRG_SETTINGS;
                break;
            case 5:
                fragment = AboutFragment.newInstance();
                tag = Constants.TAG_FRG_ABOUT;
                break;
        }
        replaceFragment(fragment,tag);
    }
    public void replaceFragment(Fragment fragment, String tag) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flContainerFragment, fragment,tag)
                .commit();

        setToolbarTitle(tag);
        closeNavigationDrawer();
    }

    public void setToolbarTitle(String title) {
        tvTitle.setText(title);
    }

    public void closeNavigationDrawer() {
        if (drawer.isDrawerOpen(LEFT)) drawer.closeDrawer(LEFT);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
