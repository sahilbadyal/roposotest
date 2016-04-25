package com.sbad.storyapp;

/**
 * Created by sbad on 25/04/16.
 */
public class User {
    private  String UserId,is_following;

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
}
