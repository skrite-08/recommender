package com.sun.service;

import com.sun.model.ReviewSong;
import com.sun.model.ReviewUser;
import com.sun.model.Song;
import com.sun.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface SearchService {
    /**
     * 根据关键词，查询歌词信息，并加上是否收藏标记
     * @param request
     * HttpServletRequest
     * @param keyword
     * 搜索关键词
     * @return
     * 若没有结果，返回list.size()=0
     */
    List<Song> getSearchSongWithFlag(HttpServletRequest request, String keyword);

    /**
     * 根据关键词，查询评论信息
     * @param keyword
     * 搜索关键词
     * @return
     * 若没有结果，返回list.size()=0
     */
    List<ReviewUser> getSearchReviewUser(String keyword);

    /**
     * 根据关键词，查询评论信息
     * @param keyword
     * 搜索关键词
     * @return
     * 若没有结果，返回list.size()=0
     */
    List<ReviewSong> getSearchReviewSong(String keyword);

    /**
     * 根据关键词，查询用户信息,不包括管理员自己
     * @param request
     * HttpServletRequest
     * @param keyword
     * 搜索关键词
     * @return
     * 若没有结果，返回list.size()=0
     */
    List<User> getSearchUser(HttpServletRequest request, String keyword);

    /**
     * 根据关键词，查询歌曲信息
     * @param keyword
     * 搜索关键词
     * @return
     * 若没有结果，返回list.size()=0
     */
    List<Song> getSearchSong(String keyword);
}
