package com.sbad.storyapp;

/**
 * Created by sbad on 24/4/16.
 */
public class Story {
    private String description;
    private String id;
    private String author;
    private String verb;
    private String db;
    private String url;
    private String type;
    private String title;
    private boolean like_flag;
    private int likes_count;
    private int comment_count;
    private String is_following;
    private String si;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getVerb() {
        return verb;
    }

    public void setVerb(String verb) {
        this.verb = verb;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isLike_flag() {
        return like_flag;
    }

    public void setLike_flag(boolean like_flag) {
        this.like_flag = like_flag;
    }

    public int getLikes_count() {
        return likes_count;
    }

    public void setLikes_count(int likes_count) {
        this.likes_count = likes_count;
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public String getSi() {
        return si;
    }

    public void setSi(String si) {
        this.si = si;
    }

    public String getIs_following() {
        return is_following;
    }

    public void setIs_following(String is_following) {
        this.is_following = is_following;
    }
}
