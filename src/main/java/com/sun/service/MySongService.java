package com.sun.service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

import com.sun.model.Song;

public interface MySongService {
    /**
     * 获取当前用户的收藏列表，带收藏标记
     * @param request
     * HttpServletRequest
     * @return
     * 如果没有过收藏，则返回null
     */
    List<Song> getMySongListWithFlag(HttpServletRequest request);

    /**
     * 获取当前用户的最近的播放里表，带收藏标记
     * @param request
     * @return
     */
    List<Song> getMyRecentPlayListWithFlag(HttpServletRequest request);


    /**
     * 获取当前用户每天的个性化推荐音乐列表，并带上是否已经收藏的标记.
     * 每天早上6点更新一次
     * @param request
     * HttpServletRequest
     * @return
     * 若没有获取到，返回null
     */
    List<Song> getPersonalDailySongWithFlag(HttpServletRequest request);




}
