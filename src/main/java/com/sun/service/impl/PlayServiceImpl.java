package com.sun.service.impl;

import com.sun.dao.PlayDao;
import com.sun.dao.UserDao;
import com.sun.model.PlaySongRecord;
import com.sun.model.User;
import com.sun.service.PlayService;
import com.sun.utils.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@Service("PlayService")
public class PlayServiceImpl implements PlayService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private PlayDao playDao;


    public void recordPlay(HttpServletRequest request, int songId) {
        User user=userDao.selectByUser(Request.getUserFromHttpServletRequest(request));
        playDao.insert(new PlaySongRecord(user.getUserId(),songId));

    }


    public List<PlaySongRecord> getAllRecords() {
        return playDao.selectAll();
    }
}
