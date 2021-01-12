package com.sun.dao;

import com.sun.model.LikingSong;
import com.sun.model.Song;
import com.sun.model.User;

import java.util.List;

public interface TrendingSongDao {
    /**
     * 获取热门音乐推荐列表
     * @return
     * 若曲库中没有任何歌曲，则返回null
     */
    List<Song> getTrendingSong();

    /**
     * 获取当前用户的收藏列表
     * @param user
     * 当前用户
     * @return
     * 若该用户没有任何收藏，则返回null
     */
    List<LikingSong> getLikingSong(User user);
}
