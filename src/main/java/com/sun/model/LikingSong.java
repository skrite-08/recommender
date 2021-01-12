package com.sun.model;

public class LikingSong {
    private int likeId;
    private int userId;
    private int songId;

    public LikingSong() {

    }

    public LikingSong(int userId,int songId) {
        this.userId=userId;
        this.songId=songId;
    }

    public int getLikeId() {
        return likeId;
    }

    public void setLikeId(int collectionId) {
        this.likeId = collectionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }
}
