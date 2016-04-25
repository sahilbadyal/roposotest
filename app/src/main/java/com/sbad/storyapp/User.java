package com.sbad.storyapp;

/**
 * Created by sbad on 25/04/16.
 */
public class User {
    private  String UserId,is_following,about,username;

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getIs_following() {
        return is_following;
    }

    public void setIs_following(String is_following) {
        this.is_following = is_following;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
