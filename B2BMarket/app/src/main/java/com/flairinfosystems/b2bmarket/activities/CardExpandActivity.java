package com.flairinfosystems.b2bmarket.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flairinfosystems.b2bmarket.R;


public class CardExpandActivity extends AppCompatActivity  {
    private Toolbar toolbar;
    ImageView imageView;
    TextView textView;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_expand);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent=getIntent();
        String title = intent.getStringExtra("title");
        setTitle(title);
        String image = intent.getStringExtra("image");

        imageView=(ImageView)findViewById(R.id.card_expand_image);
        //create a new progress bar for each image to be loaded
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        Glide.with(this)
                .load(image)
                .error(R.drawable.progress_bar)
                .into(imageView);


       textView=(TextView)findViewById(R.id.card_expand_title);
       textView.setText(title);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



 }

