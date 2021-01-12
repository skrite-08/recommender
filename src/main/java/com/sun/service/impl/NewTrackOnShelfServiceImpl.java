package com.sun.service.impl;

import com.sun.dao.TrendingSongDao;
import com.sun.dao.UserDao;
import com.sun.model.LikingSong;
import com.sun.model.Song;
import com.sun.model.User;
import com.sun.service.NewTrackOnShelfService;
import com.sun.utils.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sun.dao.NewTrackOnShelfDao;


import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service("newTrackOnShelfService")
public class NewTrackOnShelfServiceImpl implements NewTrackOnShelfService {
	@Autowired
	private NewTrackOnShelfDao newTrackOnShelfDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private TrendingSongDao trendingSongDao;

	public List<Song> getNewTrackWithFlag(HttpServletRequest request) {
		List<Song> newTackOnShelList=new ArrayList<Song>();
		List<LikingSong> collectionList=new ArrayList<LikingSong>();
		User user=userDao.selectByUser(Request.getUserFromHttpServletRequest(request));
		collectionList=trendingSongDao.getLikingSong(user);
		newTackOnShelList=newTrackOnShelfDao.selectNewSong();
		//在新碟上架列表中给已经被该用户收藏的歌曲加上标记
		if(collectionList!=null && newTackOnShelList!=null) {
			for(LikingSong c:collectionList) {
				for(Song t:newTackOnShelList) {
					if(c.getSongId()==t.getSongId()) {
						t.setWhetherCollected(true);
					}
				}
			}
		}
		return newTackOnShelList;
	}

}
