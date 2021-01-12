package com.sun.model;

public class DownloadSongRecord {
    private int downloadId;
    private int userId;
    private int songId;

    public DownloadSongRecord() {

    }
    public DownloadSongRecord(int userId,int songId) {
        this.userId=userId;
        this.songId=songId;
    }

    public int getDownloadId() {
        return downloadId;
    }
    public void setDownloadId(int downloadId) {
        this.downloadId = downloadId;
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
