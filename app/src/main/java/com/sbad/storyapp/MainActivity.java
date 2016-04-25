package com.sbad.storyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    RecyclerViewAdapter adapter;
    RecyclerView recyclerView;
    ArrayList<Story> storyArrayList;
    Map<String, HashMap<String, String>> userList;
    Map getUser(String id)  {
        return userList.get(id);
    }
    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.stories);
        storyArrayList = new ArrayList<>();
        dbHelper = new DbHelper(this);
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray array = obj.getJSONArray("data");
            userList = new HashMap<>();
            HashMap<String, String> userid;

            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                if(object.has("username"))    {
                    String username = object.getString("username");
                    String id = object.getString("id");
                    String is_following = ""+object.getBoolean("is_following");

                    dbHelper.insertData(id,is_following);

                    userid = new HashMap<String, String>();
                    userid.put("id", id);
                    userid.put("username", username);

                    userList.put(id, userid);
                }
            }

            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                if(object.has("type"))    {
                    String follow= dbHelper.checkFollowing(object.getString("db"));
                    String username = getUser(object.getString("db")).get("username").toString();
                    Story item = new Story();
                    item.setTitle(object.getString("title"));
                    item.setDescription(object.getString("description"));
                    item.setSi(object.getString("si"));
                    item.setDb(object.getString("db"));
                    item.setIs_following(follow);
                    item.setAuthor(username);
                    storyArrayList.add(item);
                }
                Log.d("json", object.toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewAdapter(MainActivity.this,storyArrayList);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {
                final ImageView image = (ImageView) view.findViewById(R.id.follow_image);
                View v = view.findViewById(R.id.ll_clickable);
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MainActivity.this, StoryDetails.class);
                        Story obj= storyArrayList.get(position);
                        intent.putExtra("title", obj.getTitle());
                        intent.putExtra("description", obj.getDescription());
                        intent.putExtra("si", obj.getSi());
                        intent.putExtra("author", obj.getAuthor());
                        intent.putExtra("db", obj.getDb());
                        startActivity(intent);
                    }
                });
                image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(dbHelper.checkFollowing(storyArrayList.get(position).getDb()).equals("false")) {
                            image.setImageResource(R.drawable.ic_followed);
                            dbHelper.UpdateData(storyArrayList.get(position).getDb(),"true");
                        }else{
                            image.setImageResource(R.drawable.ic_default);
                            dbHelper.UpdateData(storyArrayList.get(position).getDb(),"false");
                        }
                    }
                });

            }
        }));

    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = this.getAssets().open("data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
