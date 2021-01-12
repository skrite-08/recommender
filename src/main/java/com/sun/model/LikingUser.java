package com.sun.model;

public class LikingUser {
    private int likeId;
    private int userId;
    private int likingUserId;

    public LikingUser() {}

    public LikingUser(int userId,int likingUserId) {
        this.userId=userId;
        this.likingUserId=likingUserId;
    }

    public int getLikeId() {
        return likeId;
    }

    public void setLikeId(int likeId) {
        this.likeId = likeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getLikingUserId() {
        return likingUserId;
    }

    public void setLikingUserId(int likingUserId) {
        this.likingUserId = likingUserId;
    }

}
