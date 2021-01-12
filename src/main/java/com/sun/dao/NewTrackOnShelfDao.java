package com.sun.dao;

import com.sun.model.Song;

import java.util.List;

public interface NewTrackOnShelfDao {

	/**
	 * 查询当前新歌曲
	 * @return
	 * 若没有新歌曲，则返回null
	 */
	List<Song> selectNewSong();

}
