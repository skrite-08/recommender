package com.sun.dao;

import java.util.List;

import com.sun.model.*;

public interface ReviewDao {


	List<ReviewUser> selectAllReviewUser();

	/**
	 * 插入评论
	 * @param reviewUser
	 * @return
	 */
	int insertReviewUser(ReviewUser reviewUser);



	/**
	 * 获取当前用户的所有点赞记录
	 * @param userId
	 * @return
	 */
	List<Support> selectSuportByUserId(int userId);

	/**
	 * 获取目标用户自我分享下面的所有精彩评论，并带上点赞数目
	 * @param targetUserId
	 * @return
	 */
	List<ReviewUser> selectHotReviewUserWithSupportNumber(int targetUserId);

	/**
	 * 根据点赞查询点赞信息
	 * @param support
	 * @return
	 */
	Support selectBySupport(Support support);

	/**
	 * 插入点赞信息
	 * @param support
	 */
	void insertSupportRecord(Support support);

	/**
	 * 根据点赞Id删除点赞信息
	 * @param supportId
	 */
	void deleteSupportRecordById(int supportId);

    /**    /////////////////////           */


	List<ReviewSong> selectAllReviewSong();

	/**
	 * 插入评论
	 * @param reviewSong
	 * @return
	 */
	int insertReviewSong(ReviewSong reviewSong);


	/**
	 * 查询当前用户的点赞信息
	 * @param userId
	 * 当前用户Id
	 * @return
	 * 如果没有，则返回null
	 */
	List<Like> selectLikeByUserId(int userId);

	/**
	 * 查询当前歌曲的带点赞数目的精彩评论信息,这里定义前10条
	 * @param songId
	 * 当前歌曲Id
	 * @return
	 * 如果没有，返回null
	 */
	List<ReviewSong> selectHotReviewSongWithLikeNumber(int songId);

	/**
	 * 查询当前点在是否存在
	 * @param like
	 * 点赞对象like（主要是根据用户userId和评论reviewId）
	 * @return
	 * 如果没有，返回null
	 */
	Like selectByLike(Like like);

	/**
	 * 插入点赞记录
	 * @param like
	 */
	void insertLikeRecord(Like like);

	/**
	 * 删除点赞记录
	 * @param likeId
	 */
	void deleteLikeRecordById(int likeId);

	/**
	 * 查询当前歌曲的带点赞数目的最新评论信息
	 * @param songId
	 * 当前歌曲Id
	 * @return
	 * 如果没有，返回null
	 */
	List<ReviewSong> selectNewReviewSongWithLikeNumber(int songId);

	/**
	 * 批量删除，根据数组里面的Id删除对应的评论
	 * @param reviewIds
	 */
	void deleteByIds(int[] reviewIds);

}
