package com.sun.dao;

import com.sun.model.Song;
import com.sun.model.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface MySongDao {
    /**
     * 查询当前用户收藏的歌曲信息列表
     * @param user
     * @return
     */
    @Select("select * from likingsong,song where userId=#{userId} and likingsong.songId=song.songId")
    public List<Song> selectLikingSong(User user);

    /**
     * 查询当前用户最近的播放列表
     * @param user
     * @return
     */
    @Select("select * from playsong,song where playsong.userId=#{userId} and playsong.songId=song.songId")
    public List<Song> selectMyRecentSong(User user);


    /**
     * 从表A中获取当前用户的个性化推荐列表
     * @param user
     * @return
     * 初始化为随机歌曲列表
     */
    List<Song> selectPersonalSongFromA(User user);

    /**
     * 从表B中获取当前用户的个性化推荐列表
     * @param user
     * @return
     * 初始化为随机歌曲列表
     */
    List<Song> selectPersonalSongFromB(User user);



}
