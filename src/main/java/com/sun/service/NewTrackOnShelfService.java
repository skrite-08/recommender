package com.sun.service;

import com.sun.model.Song;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface NewTrackOnShelfService {

	/**
	 * 获取当前的新碟歌曲,并加上是否以及被收藏标记
	 * @param request
	 * @return
	 */
	List<Song> getNewTrackWithFlag(HttpServletRequest request);

}
