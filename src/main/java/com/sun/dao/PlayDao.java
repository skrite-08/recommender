package com.sun.dao;

import com.sun.model.PlaySongRecord;

import java.util.List;

public interface PlayDao {
    /***
     * 添加新的播放记录
     * @param playSongRecord
     */
    void insert(PlaySongRecord playSongRecord);

    /**
     * 查询所有用户的播放记录
     * @return
     */
    List<PlaySongRecord> selectAll();
}
