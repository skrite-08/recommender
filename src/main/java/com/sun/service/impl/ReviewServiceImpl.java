package com.sun.service.impl;

import com.sun.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sun.dao.ReviewDao;
import com.sun.dao.UserDao;
import com.sun.service.ReviewService;
import com.sun.utils.Request;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service("reviewServiceImpl")
public class ReviewServiceImpl implements ReviewService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private ReviewDao reviewDao;

	public List<ReviewUser> getAllReviewUserRecords(){
		return  reviewDao.selectAllReviewUser();
	}

	public boolean addReviewUser(HttpServletRequest request, int targetUserId, String content) {
		boolean isInsertSuccessful = false;
		com.sun.model.User user = userDao.selectByUser(com.sun.utils.Request.getUserFromHttpServletRequest(request));
		if (user == null) {
			return isInsertSuccessful;
		}
		com.sun.model.ReviewUser reviewUser = new com.sun.model.ReviewUser(user.getUserId(), targetUserId, content);
		int affectedRows = reviewDao.insertReviewUser(reviewUser);
		if (affectedRows > 0) {
			isInsertSuccessful = true;
		}
		return isInsertSuccessful;
	}

	public List<com.sun.model.ReviewUser> getHotReviewUserByTargetUserIdWithLikeFlag(HttpServletRequest request, int targetUserId) {
		com.sun.model.User user = userDao.selectByUser(com.sun.utils.Request.getUserFromHttpServletRequest(request));
		//获取用户的点赞列表
		List<com.sun.model.Support> suportList=null;
		if(user!=null) {
			suportList= reviewDao.selectSuportByUserId(user.getUserId());
		}
		//获取目标用户自我分享下面的精彩评论列表
		List<com.sun.model.ReviewUser> hotReviewList= reviewDao.selectHotReviewUserWithSupportNumber(targetUserId);

		//在结果列表中给已经被该用户点赞的评论加上标记
		if(hotReviewList!=null && suportList!=null) {
			for(com.sun.model.Support support:suportList) {
				for(ReviewUser review:hotReviewList) {
					if(support.getReviewId()==review.getReviewId()) {
						review.setWhetherLiked(true);
					}
				}
			}
		}
		return hotReviewList;
	}

	public boolean reviewSupportChange(HttpServletRequest request, int reviewId) {
		boolean isLiked=true;
		User user=userDao.selectByUser(Request.getUserFromHttpServletRequest(request));
		//获取当前评论的点赞状态
		com.sun.model.Support support=reviewDao.selectBySupport(new com.sun.model.Support(user.getUserId(),reviewId));
		if(support==null) {
			//该评论还没有被该用户点赞
			isLiked=false;
			//进行点赞
			reviewDao.insertSupportRecord(new Support(user.getUserId(),reviewId));
		}else {
			//已经点赞了，则取消点赞
			reviewDao.deleteSupportRecordById(support.getSupportId());
		}
		//返回该评论改变后的点赞状态
		return !isLiked;
	}

	public boolean tooQuickly(HttpServletRequest request,int minutes) {
		if(request.getSession().getAttribute("lastTime")==null) {
			System.out.println("null");
			request.getSession().setAttribute("lastTime", new SimpleDateFormat("mm").format(new Date()));
			return false;
		}
		String lastMinute=(String) request.getSession().getAttribute("lastTime");
		String curMinute=new SimpleDateFormat("mm").format(new Date());

		if( Math.abs(Integer.valueOf(curMinute)- Integer.valueOf(lastMinute)) <=minutes) {
			return true;
		}
		request.getSession().setAttribute("lastTime",curMinute);
		return false;
	}




	public boolean addReviewSong(HttpServletRequest request, int songId, String content) {
		boolean isInsertSuccessful = false;
		User user = userDao.selectByUser(Request.getUserFromHttpServletRequest(request));
		if (user == null) {
			return isInsertSuccessful;
		}
		ReviewSong reviewSong = new ReviewSong(user.getUserId(), songId, content);
		int affectedRows = reviewDao.insertReviewSong(reviewSong);
		if (affectedRows > 0) {
			isInsertSuccessful = true;
		}
		return isInsertSuccessful;
	}

	public List<ReviewSong> getHotReviewSongBySongIdWithLikeFlag(HttpServletRequest request, int songId) {
		User user = userDao.selectByUser(Request.getUserFromHttpServletRequest(request));
		//获取用户的点赞列表
		List<Like> likeList=null;
		if(user!=null) {
			likeList= reviewDao.selectLikeByUserId(user.getUserId());
		}
		//获取歌曲的精彩评论列表
		List<ReviewSong> hotReviewList= reviewDao.selectHotReviewSongWithLikeNumber(songId);

		//在结果列表中给已经被该用户点赞的评论加上标记
		if(hotReviewList!=null && likeList!=null) {
			for(Like like:likeList) {
				for(ReviewSong review:hotReviewList) {
					if(like.getReviewId()==review.getReviewId()) {
						review.setWhetherLiked(true);
					}
				}
			}
		}
		return hotReviewList;
	}

	public boolean reviewLikeChange(HttpServletRequest request, int reviewId) {
		boolean isLiked=true;
		User user=userDao.selectByUser(Request.getUserFromHttpServletRequest(request));
		//获取当前评论的点赞状态
		Like like=reviewDao.selectByLike(new Like(user.getUserId(),reviewId));
		if(like==null) {
			//该评论还没有被该用户点赞
			isLiked=false;
			//进行点赞
			reviewDao.insertLikeRecord(new Like(user.getUserId(),reviewId));
		}else {
			//已经点赞了，则取消点赞
			reviewDao.deleteLikeRecordById(like.getLikeId());
		}
		//返回该评论改变后的点赞状态
		return !isLiked;
	}

	public List<ReviewSong> getNewReviewSongBySongIdWithLikeFlag(HttpServletRequest request, int songId) {
		User user = userDao.selectByUser(Request.getUserFromHttpServletRequest(request));
		//获取用户的点赞列表
		List<Like> likeList=null;
		if(user!=null) {
			likeList= reviewDao.selectLikeByUserId(user.getUserId());
		}
		//获取歌曲的最新评论列表
		List<ReviewSong> newReviewList= reviewDao.selectNewReviewSongWithLikeNumber(songId);

		//在结果列表中给已经被该用户点赞的评论加上标记
		if(newReviewList!=null && likeList!=null) {
			for(Like like:likeList) {
				for(ReviewSong review:newReviewList) {
					if(like.getReviewId()==review.getReviewId()) {
						review.setWhetherLiked(true);
					}
				}
			}
		}
		return newReviewList;
	}

	public void batchDeleteById(int[] reviewIds) {
		if(reviewIds==null) {
			return;
		}
		reviewDao.deleteByIds(reviewIds);

	}



}
