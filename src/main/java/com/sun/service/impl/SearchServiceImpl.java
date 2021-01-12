package com.sun.service.impl;

import com.sun.dao.SearchDao;
import com.sun.dao.TrendingSongDao;
import com.sun.dao.UserDao;
import com.sun.model.*;
import com.sun.service.SearchService;
import com.sun.utils.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service("searchService")
public class SearchServiceImpl implements SearchService {
    @Autowired
    private SearchDao searchDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private TrendingSongDao trendingSongDao;


    public List<Song> getSearchSongWithFlag(HttpServletRequest request, String keyword) {
        List<Song> searchSongList=new ArrayList<Song>();
        List<LikingSong> collectionList=new ArrayList<LikingSong>();
        User user=userDao.selectByUser(Request.getUserFromHttpServletRequest(request));
        collectionList=trendingSongDao.getLikingSong(user);
        searchSongList=searchDao.selectSongLikeKeyword(keyword);

        //在搜索结果列表中给已经被该用户收藏的歌曲加上标记
        if(collectionList!=null && searchSongList!=null) {
            for(LikingSong c:collectionList) {
                for(Song t:searchSongList) {
                    if(c.getSongId()==t.getSongId()) {
                        t.setWhetherCollected(true);
                    }
                }
            }
        }
        return searchSongList;
    }


    public List<ReviewUser> getSearchReviewUser(String keyword) {
        List<ReviewUser> searchReviewUserList=new ArrayList<ReviewUser>();
        searchReviewUserList=searchDao.selectReviewUserLikeKeyword(keyword);
        return searchReviewUserList;
    }


    public List<ReviewSong> getSearchReviewSong(String keyword) {
        List<ReviewSong> searchReviewSongList=new ArrayList<ReviewSong>();
        searchReviewSongList=searchDao.selectReviewSongLikeKeyword(keyword);
        return searchReviewSongList;
    }


    public List<User> getSearchUser(HttpServletRequest request,String keyword) {
        User user=userDao.selectByUser(Request.getUserFromHttpServletRequest(request));
        List<User> searchUserList=new ArrayList<User>();
        searchUserList=searchDao.selectUserLikeKeyword(keyword,user.getUserId());
        return searchUserList;
    }


    public List<Song> getSearchSong(String keyword) {
        List<Song> searchSongList=new ArrayList<Song>();
        searchSongList=searchDao.selectSongLikeKeyword(keyword);
        return searchSongList;
    }

}
