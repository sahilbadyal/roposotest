package com.sbad.storyapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by sahil on 24/4/16.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private ArrayList<Story> stories = new ArrayList<>();
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.story_card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        //return stories.size();
        return 10;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,description,date;
        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            description= (TextView) itemView.findViewById(R.id.text_description);
            date=(TextView) itemView.findViewById(R.id.text_date);
        }
    }
    public void refresh(ArrayList<Story> list) {
        stories = list;
        notifyItemRangeChanged(0, list.size());
    }
}
