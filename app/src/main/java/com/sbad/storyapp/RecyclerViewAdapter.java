package com.sbad.storyapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by sbad on 24/4/16.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Context context;
    private DbHelper dbHelper;
    private ArrayList<Story> stories = new ArrayList<>();

    public RecyclerViewAdapter(Context context, ArrayList<Story> stories) {
        dbHelper= new DbHelper(context);
        this.context = context;
        this.stories = stories;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.story_card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if(!stories.get(position).getTitle().isEmpty()&&stories.get(position).getTitle().length()!=0){
            holder.title.setText(stories.get(position).getTitle());
        }
        if(!stories.get(position).getDescription().isEmpty()&&stories.get(position).getDescription().length()!=0){
            String desc = "";
            if(stories.get(position).getDescription().length()>=40) {
                desc = stories.get(position).getDescription().substring(0, 40);
            }else{
                desc =  stories.get(position).getDescription();
            }
            holder.description.setText(desc+"...");
        }
        if(!stories.get(position).getAuthor().isEmpty()&&stories.get(position).getAuthor().length()!=0){
            holder.author.setText(stories.get(position).getAuthor());
        }
        if(!stories.get(position).getSi().isEmpty()&&stories.get(position).getSi().length()!=0){
            Glide.with(context).load(stories.get(position).getSi()).diskCacheStrategy(DiskCacheStrategy.SOURCE).placeholder(R.drawable.ic_person).error(R.mipmap.ic_launcher).into(holder.si);
        }
        if(dbHelper.checkFollowing(stories.get(position).getDb()).equals("true")){
            holder.img.setImageResource(R.drawable.ic_followed);
        }else{
            holder.img.setImageResource(R.drawable.ic_default);
        }
    }

    @Override
    public int getItemCount() {
        //return stories.size();
        return stories.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,description,author;
        ImageView si;
        ImageView img;
        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            description= (TextView) itemView.findViewById(R.id.description);
            author=(TextView) itemView.findViewById(R.id.username);
            si = (ImageView) itemView.findViewById(R.id.story_img);
            img = (ImageView) itemView.findViewById(R.id.follow_image);

        }
    }
    public void refresh(ArrayList<Story> list) {
        stories = list;
        notifyItemRangeChanged(0, list.size());
    }
}
