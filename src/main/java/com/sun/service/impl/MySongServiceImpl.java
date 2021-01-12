package com.sun.service.impl;

import com.sun.dao.*;
import com.sun.model.LikingSong;
import com.sun.model.Song;
import com.sun.model.User;
import com.sun.service.MySongService;
import com.sun.utils.Request;
import com.sun.utils.Static;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.BiConsumer;

@Service("mySongService")
public class MySongServiceImpl implements MySongService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private MySongDao mySongDao;


    @Autowired
    private PersonalSongDao personalSongDao;

    @Autowired
    private TrendingSongDao trendingSongDao;
    @Autowired
    private NewTrackOnShelfDao newTrackOnShelfDao;


    public List<Song> getMySongListWithFlag(HttpServletRequest request) {
        List<Song> myLikeSongList = new ArrayList<Song>();
        User user = userDao.selectByUser(Request.getUserFromHttpServletRequest(request));
        myLikeSongList = mySongDao.selectLikingSong(user);
        //为该用户收藏的歌曲加上标记
        if(myLikeSongList!=null) {
            for(Song t:myLikeSongList) {
                t.setWhetherCollected(true);
            }
        }
        return myLikeSongList;
    }

    public List<Song> getMyRecentPlayListWithFlag(HttpServletRequest request) {
        List<Song> myRecentPalyList = new ArrayList<Song>();
        List<LikingSong> myLikeSongList=new ArrayList<LikingSong>();
        User user = userDao.selectByUser(Request.getUserFromHttpServletRequest(request));
        myRecentPalyList = mySongDao.selectMyRecentSong(user);
        myLikeSongList=trendingSongDao.getLikingSong(user);
        //在新碟上架列表中给已经被该用户收藏的歌曲加上标记
        if(myLikeSongList!=null && myRecentPalyList!=null) {
            for(LikingSong c:myLikeSongList) {
                for(Song t:myRecentPalyList) {
                    if(c.getSongId()==t.getSongId()) {
                        t.setWhetherCollected(true);
                    }
                }
            }
        }
        return myRecentPalyList;
    }



    public List<Song> getPersonalDailySongWithFlag(HttpServletRequest request) {
        List<Song> personalSongList = new ArrayList<Song>();
        List<LikingSong> myLikeSongList = new ArrayList<>();
        User user = userDao.selectByUser(Request.getUserFromHttpServletRequest(request));
        myLikeSongList = trendingSongDao.getLikingSong(user);
        /* =============================================================== */
        personalSongList=selectPersonalRec(user);
        /* =============================================================== */
        // 在个性化列表中给已经被该用户收藏的歌曲加上标记
        if (myLikeSongList != null && personalSongList != null) {
            for (LikingSong c : myLikeSongList) {
                for (Song t : personalSongList) {
                    if (c.getSongId() == t.getSongId()) {
                        t.setWhetherCollected(true);
                    }
                }
            }
        }
        return personalSongList;
    }

    /**
     * 每天早上6点更新个性化推荐列表，从更新后的表中读取记录
     * 这里采用两张表交替的方式来实现：
     * （1）	6点之后就从另外一张表中读取记录
     * （2）	重新开始计算新的个性化推荐列表存放于原来的表中的
     * @param user
     * @return
     */
    private List<Song> selectPersonalRec(User user) {
        if(user==null) return null;
        List<Song> personalRecList = new ArrayList<Song>();
        if(Static.isFromA) {
            personalRecList=personalSongDao.selectPersonalSongFromA(user);
        }else {
            personalRecList=personalSongDao.selectPersonalSongFromB(user);
        }
        return personalRecList;
    }








}
