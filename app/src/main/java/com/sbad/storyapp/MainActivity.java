package com.sbad.storyapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.stories);
        storyArrayList = new ArrayList<>();
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray m_jArry = obj.getJSONArray("data");
            userList = new HashMap<>();
            HashMap<String, String> userid;

            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                if(jo_inside.has("username"))    {
                    String username = jo_inside.getString("username");
                    String id = jo_inside.getString("id");

                    userid = new HashMap<String, String>();
                    userid.put("id", id);
                    userid.put("username", username);

                    userList.put(id, userid);
                }
            }

            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                if(jo_inside.has("type"))    {
                    String username = getUser(jo_inside.getString("db")).get("username").toString();
                    Story item = new Story();
                    item.setTitle(jo_inside.getString("title"));
                    item.setDescription(jo_inside.getString("description"));
                    item.setSi(jo_inside.getString("si"));
                    item.setAuthor(username);
                    storyArrayList.add(item);
                }
                Log.d("json", jo_inside.toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewAdapter(MainActivity.this,storyArrayList);
        recyclerView.setAdapter(adapter);
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
