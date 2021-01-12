package com.sun.service.impl;

import com.sun.dao.TrendingSongDao;
import com.sun.dao.UserDao;
import com.sun.model.LikingSong;
import com.sun.model.Song;
import com.sun.model.User;
import com.sun.service.TrendingSongService;
import com.sun.utils.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service("trendingSongService")
public class TrendingSongServiceImpl implements TrendingSongService {
    @Autowired
    private TrendingSongDao trendingSongDao;
    @Autowired
    private UserDao userDao;

    public List<Song> getSongWithFlag(HttpServletRequest request) {
        List<Song> trendingSongList=new ArrayList<Song>();
        List<LikingSong> songList=new ArrayList<LikingSong>();
        User user=userDao.selectByUser(Request.getUserFromHttpServletRequest(request));
        songList=trendingSongDao.getLikingSong(user);
        trendingSongList=trendingSongDao.getTrendingSong();
        //在热门推荐列表中给已经被该用户收藏的歌曲加上标记
        if(songList!=null && trendingSongList!=null) {
            for(LikingSong c:songList) {
                for(Song t:trendingSongList) {
                    if(c.getSongId()==t.getSongId()) {
                        t.setWhetherCollected(true);
                    }
                }
            }
        }
        return trendingSongList;
    }

}
