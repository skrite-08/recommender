package com.sun.dao;

import com.sun.model.DownloadSongRecord;

import java.util.List;

public interface DownloadDao {
     void insert(DownloadSongRecord downloadSongRecord);

    /**
     * 获取所有用户的下载记录
     * @return
     */
    List<DownloadSongRecord> selectAll();
}
