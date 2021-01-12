package com.sun.service;

import com.sun.model.Review;
import com.sun.model.ReviewSong;
import com.sun.model.ReviewUser;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ReviewService {


	List<ReviewUser> getAllReviewUserRecords();

	/**
	 * 添加评论并返回是否成功
	 * @param request
	 * HttpServletRequest
	 * @param targetUserId
	 * 被评论的用户Id
	 * @param review
	 * 评论信息
	 * @return
	 * 如果添加成功，返回true
	 */
	boolean addReviewUser(HttpServletRequest request, int targetUserId, String review);

	/**
	 * 获取当前目标用户自我分享下面的精彩评论，并加上是否被当前用户点赞的标记
	 * @param request
	 * HttpServletRequest
	 * @param targetUserId
	 * 目标用户Id
	 * @return
	 * 评论信息列表
	 */
	List<ReviewUser> getHotReviewUserByTargetUserIdWithLikeFlag(HttpServletRequest request, int targetUserId);

	/**
	 * 改变当前用户对某个评论的点赞状态
	 * @param request
	 * HttpServletRequest对象
	 * @param reviewId
	 * 需要改变点赞状态的评论Id
	 * @return
	 * 若改变后状态为已经点赞，则返回true
	 */
	boolean reviewSupportChange(HttpServletRequest request, int reviewId);

	/**
	 * 简单限制minutes内只能评价一次
	 * @param request
	 * @param minutes
	 * 限制的分钟数
	 * @return
	 */
	boolean tooQuickly(HttpServletRequest request, int minutes);










	/**
	 * 添加评论并返回是否成功
	 * @param request
	 * HttpServletRequest
	 * @param songId
	 * 歌曲Id
	 * @param review
	 * 评论信息
	 * @return
	 * 如果添加成功，返回true
	 */
	boolean addReviewSong(HttpServletRequest request, int songId, String review);

	/**
	 * 获取当前歌曲的精彩评论，并加上是否被当前用户点赞的标记
	 * @param request
	 * HttpServletRequest
	 * @param songId
	 * 歌曲Id
	 * @return
	 * 评论信息列表
	 */
	List<ReviewSong> getHotReviewSongBySongIdWithLikeFlag(HttpServletRequest request, int songId);

	/**
	 * 改变当前用户对某个评论的点赞状态
	 * @param request
	 * HttpServletRequest对象
	 * @param reviewId
	 * 需要改变点赞状态的评论Id
	 * @return
	 * 若改变后状态为已经点赞，则返回true
	 */
	boolean reviewLikeChange(HttpServletRequest request, int reviewId);

	/**
	 * 获取当前歌曲的最新评论，并加上是否被当前用户点赞的标记
	 * @param request
	 * HttpServletRequest
	 * @param songId
	 * 歌曲Id
	 * @return
	 * 评论信息列表
	 */
	List<ReviewSong> getNewReviewSongBySongIdWithLikeFlag(HttpServletRequest request, int songId);

	/**
	 * 批量删除，根据数组里面的Id删除对应的评论
	 * @param reviewIds
	 */
	void batchDeleteById(int[] reviewIds);




}
