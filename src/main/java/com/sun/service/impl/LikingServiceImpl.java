package com.sun.service.impl;

import com.sun.dao.LikingDao;
import com.sun.dao.UserDao;
import com.sun.model.LikingSong;
import com.sun.model.User;
import com.sun.service.LikingService;
import com.sun.utils.Request;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class LikingServiceImpl implements LikingService {
    @Autowired
    private LikingDao collectionDao;
    @Autowired
    private UserDao userDao;

    public boolean collectionChange(HttpServletRequest request, int songId) {
        boolean isCurCollected=true;
        User user=userDao.selectByUser(Request.getUserFromHttpServletRequest(request));
        LikingSong collection=collectionDao.selectByCollection(new LikingSong(user.getUserId(),songId));
        if(collection==null) {
            //该歌曲没有被收藏
            isCurCollected=false;
            //添加收藏
            collectionDao.insert(new LikingSong(user.getUserId(),songId));
        }else {
            //已经被收藏，则取消收藏
            collectionDao.deleteById(collection.getLikeId());
        }
        //返回改变后的收藏状态
        return !isCurCollected;
    }

    public List<LikingSong> getAllRecords() {
        return collectionDao.selectAll();
    }
}
