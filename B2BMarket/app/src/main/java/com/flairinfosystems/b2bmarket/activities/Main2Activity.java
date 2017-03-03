package com.flairinfosystems.b2bmarket.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.flairinfosystems.b2bmarket.R;
import com.flairinfosystems.b2bmarket.fragments.HomeFragment;
import com.flairinfosystems.b2bmarket.fragments.PostBuyFragment;
import com.flairinfosystems.b2bmarket.fragments.SellFragment;
import com.flairinfosystems.b2bmarket.tasks.BitmapToArray;
import com.flairinfosystems.b2bmarket.tasks.DatabaseSql;
import com.flairinfosystems.b2bmarket.tasks.SaveSharedPreference;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    DatabaseSql helper;
    private int exit_counter=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);
        if (SaveSharedPreference.getPrefEmail(Main2Activity.this).length() == 0) {
            Intent reg = new Intent(Main2Activity.this, LoginActivity.class);
            startActivity(reg);
            finish();
        } else {


            setContentView(R.layout.activity_main2);
            helper = DatabaseSql.getInstance(this);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            HomeFragment homeFragment = new HomeFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_main2, homeFragment).commit();
            getSupportActionBar().setTitle(R.string.app_name);

            String emstr = SaveSharedPreference.getPrefEmail(Main2Activity.this);
            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);
            View header = navigationView.getHeaderView(0);

            TextView em = (TextView) header.findViewById(R.id.email_head);
            em.setText(emstr);

            ImageView profileImageview = (ImageView) header.findViewById(R.id.profileImageMain);
            byte[] profileimagebyte = helper.getProfileImageByte(emstr);
            profileImageview.setImageBitmap(BitmapToArray.getImage(profileimagebyte));

            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.addDrawerListener(toggle);
            toggle.syncState();

        }
    }
    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (exit_counter == 0) {
            exit_counter++;
            Toast.makeText(this, "Press again to exit.", Toast.LENGTH_SHORT).show();
        }
            super.onBackPressed();
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.Home) {
            HomeFragment homeFragment= new HomeFragment();
            FragmentManager manager= getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_main2, homeFragment).commit();
            getSupportActionBar().setTitle(R.string.app_name);
            // Handle the camera action
        } else if (id == R.id.Post) {
            PostBuyFragment postBuyFragment= new PostBuyFragment();
            FragmentManager manager= getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_main2, postBuyFragment).commit();
            getSupportActionBar().setTitle(R.string.PostBuyRequirement);

        } else if (id == R.id.Manage) {

        } else if (id == R.id.Fav) {

        } else if (id == R.id.Sell) {
            SellFragment sellFragment= new SellFragment();
            FragmentManager manager= getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_main2, sellFragment).commit();
            getSupportActionBar().setTitle(R.string.SellonB2B);
        } else if (id == R.id.Share) {

        }
        else if (id == R.id.Rate) {

        }else if (id == R.id.Settings) {

        }else if (id == R.id.Logout) {
            SaveSharedPreference.clearEmail(getApplicationContext());
            Intent reg = new Intent(getApplicationContext(), LoginActivity.class);
            reg.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            reg.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(reg);
            finish();

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
