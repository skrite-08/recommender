package com.sun.service.impl;

import com.sun.dao.DownloadDao;
import com.sun.dao.UserDao;
import com.sun.model.DownloadSongRecord;
import com.sun.model.User;
import com.sun.service.DownloadService;
import com.sun.utils.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service("DownloadService")
public class DownloadServiceImpl implements DownloadService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private DownloadDao downloadDao;

    public void recordDownload(HttpServletRequest request, int songId) {
        User user= Request.getUserFromHttpServletRequest(request);
        if(user==null) {
            return;
        }
        //记录当前用户的下载记录
        user=userDao.selectByUser(user);
        downloadDao.insert(new DownloadSongRecord(user.getUserId(),songId));
    }

    public List<DownloadSongRecord> getAllRecords() {
        return downloadDao.selectAll();
    }

}
