package com.sun.service;

import com.sun.model.Song;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface PersonalSongService {
    /**
     * 获取当前用户每天的个性化推荐音乐列表，并带上是否已经收藏的标记.
     * 每天早上6点更新一次
     * @param request
     * HttpServletRequest
     * @return
     * 若没有获取到，返回null
     */
    List<Song> getPersonalDailySongWithFlag(HttpServletRequest request);

    /**
     * 初始化当前用户的个性化音乐推荐列表
     * @param request
     * HttpServletRequest
     */
    void initializePersonalSongList(HttpServletRequest request);

    /**
     * 更新个性化推荐列表B
     * @param user2song
     * userId to songId matrix
     */
    void updatePersonalSongIntoB(Map<Integer, Integer[]> user2song);

    /**
     * 更新个性化推荐列表A
     * @param user2song
     * userId to songId matrix
     */
    void updatePersonalSongIntoA(Map<Integer, Integer[]> user2song);

    /**
     * 向个性化推荐列表A添加混合推荐的结果
     * @param user2song
     * userId to songId matrix
     */
    void addHybridRecIntoA(Map<Integer, Integer[]> user2song);

    /**
     * 向个性化推荐列表B添加混合推荐的结果
     * @param user2song
     * userId to songId matrix
     */
    void addHybridRecIntoB(Map<Integer, Integer[]> user2song);

}
