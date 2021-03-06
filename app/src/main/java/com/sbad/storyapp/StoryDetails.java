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
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_details);

        final Bundle data = getIntent().getExtras();
        TextView title, description, username,likes,about;
        final ImageView imgView,follow;
        dbHelper = new DbHelper(this);
        user = new User();

        user = dbHelper.readData(data.getString("db"));

        title = (TextView) findViewById(R.id.title_details);
        description = (TextView) findViewById(R.id.description_details);
        username = (TextView) findViewById(R.id.username_details);
        imgView = (ImageView) findViewById(R.id.story_details_img);
        follow = (ImageView) findViewById(R.id.follow_image_details);
        likes= (TextView) findViewById(R.id.likes);
        about = (TextView) findViewById(R.id.about);

        if(dbHelper.checkFollowing(data.getString("db")).equals("true")){
            follow.setImageResource(R.drawable.ic_followed);
        }

        follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dbHelper.checkFollowing(data.getString("db")).equals("false")) {
                    follow.setImageResource(R.drawable.ic_followed);
                    dbHelper.UpdateData(data.getString("db"),"true");
                }else{
                    follow.setImageResource(R.drawable.ic_default);
                    dbHelper.UpdateData(data.getString("db"),"false");
                }
            }
        });

        title.setText(data.getString("title"));
        description.setText(data.getString("description"));
        username.setText(data.getString("author"));
        likes.setText(Integer.toString(data.getInt("likes")));
        about.setText(user.getAbout());

        Glide.with(this)
                .load(data.getString("si"))
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(imgView);
    }
}

