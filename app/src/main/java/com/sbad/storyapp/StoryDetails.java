package com.sbad.storyapp;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class StoryDetails extends AppCompatActivity {
    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_details);

        Bundle data = getIntent().getExtras();
        TextView title, description, author;
        ImageView imgView,follow;
        dbHelper = new DbHelper(this);

        title = (TextView) findViewById(R.id.title_details);
        description = (TextView) findViewById(R.id.description_details);
        author = (TextView) findViewById(R.id.username_details);
        imgView = (ImageView) findViewById(R.id.story_details_img);
        follow = (ImageView) findViewById(R.id.follow_image_details);

        if(dbHelper.checkFollowing(data.getString("db")).equals("true")){
            follow.setImageResource(R.drawable.ic_followed);
        }

        title.setText(data.getString("title"));
        description.setText(data.getString("description"));
        author.setText(data.getString("author"));
        Glide.with(this)
                .load(data.getString("si"))
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(imgView);
    }
}

