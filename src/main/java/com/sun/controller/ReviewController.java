package com.sun.controller;

import com.sun.model.*;
import com.sun.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.sun.service.ReviewService;
import com.sun.service.UserService;
import com.sun.utils.ReturnMsg;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class ReviewController {
	@Autowired
	private ReviewService reviewService;
	@Autowired
	private UserService userService;
	@Autowired
	private SongService songService;

	@RequestMapping(value = "reviewUserFrameLoad.domain", method = { RequestMethod.GET })
	public ModelAndView reviewUserFrameLoad(HttpServletRequest request, int userId) {
		//获取当前选中的用户
		User user = userService.getUserById(request, userId);
		//获取选中用户自我介绍下面的精彩评论
		List<ReviewUser> hotReviewList = reviewService.getHotReviewUserByTargetUserIdWithLikeFlag(request,userId);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/WEB-INF/pages/reviewUserFrame.jsp");
		
		modelAndView.addObject("user", user);
		modelAndView.addObject("curUserPhotoAddress", userService.getCurUserPhotoAddress(request));
		
		modelAndView.addObject("hotReviewList", hotReviewList);

		return modelAndView;
	}
	
	@PostMapping(value = "reviewUser.domain")
	@ResponseBody
	public String reviewUser(HttpServletRequest request,int userId,String review) {
		//防止过度评论，这里简单的限制一下1分钟只能评论一次
		if(reviewService.tooQuickly(request,1)) {
			return ReturnMsg.msg(HttpServletResponse.SC_BAD_REQUEST, "Wait a moment");
		}
		boolean isAdded=reviewService.addReviewUser(request,userId,review);
		if(isAdded) {
			return ReturnMsg.msg(HttpServletResponse.SC_OK,"Review Successful");
		}
		return ReturnMsg.msg(HttpServletResponse.SC_BAD_REQUEST, "Somethins is wrong");
	}
	
	@RequestMapping(value = "newReviewUserFrameLoad.domain", method = { RequestMethod.GET })
	public ModelAndView newReviewUserFrameLoad(HttpServletRequest request, int userId) {
		//获取选中目标用户的自我分享下面的最新评论(目前评论数据很少，先不做分页)
		List<ReviewUser> newReviewList = reviewService.getHotReviewUserByTargetUserIdWithLikeFlag(request,userId);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/WEB-INF/pages/newReviewUserFrame.jsp");
		modelAndView.addObject("newReviewList", newReviewList);

		return modelAndView;
	}
	
	@GetMapping(value = "reviewSupport.domain",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String reviewSupport(HttpServletRequest request,int reviewId) {
		boolean isLiked=reviewService.reviewSupportChange(request,reviewId);
		return ReturnMsg.msg(HttpServletResponse.SC_OK, isLiked+"");
	}




	@GetMapping(value = "reviewSongFrameLoad.domain")
	public ModelAndView reviewSongFrameLoad(HttpServletRequest request, int songId) {
		//获取当前选中的歌曲
		Song song = songService.getSongByIdWithLikingFlag(request,songId);
		//获取选中歌曲的精彩评论
		List<ReviewSong> hotReviewList = reviewService.getHotReviewSongBySongIdWithLikeFlag(request,songId);
		//获取选中歌曲的最新评论(目前评论数据很少，先不做分页)
		List<ReviewSong> newReviewList = reviewService.getNewReviewSongBySongIdWithLikeFlag(request,songId);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/WEB-INF/pages/reviewSongFrame.jsp");

		modelAndView.addObject("song", song);
		modelAndView.addObject("hotReviewList", hotReviewList);
		modelAndView.addObject("newReviewList", newReviewList);

		return modelAndView;
	}

	@PostMapping(value = "reviewSong.domain")
	@ResponseBody
	public String reviewSong(HttpServletRequest request,int songId,String review) {
		boolean isAdded=reviewService.addReviewSong(request,songId,review);
		if(isAdded) {
			return ReturnMsg.msg(HttpServletResponse.SC_OK,"评论成功");
		}
		return ReturnMsg.msg(HttpServletResponse.SC_BAD_REQUEST, "评论出错");
	}

	@GetMapping(value = "reviewLike.domain",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String reviewLike(HttpServletRequest request,int reviewId) {
		boolean isLiked=reviewService.reviewLikeChange(request,reviewId);
		return ReturnMsg.msg(HttpServletResponse.SC_OK, isLiked+"");
	}

	@RequestMapping(value = "newReviewSongFrameLoad.domain", method = { RequestMethod.GET })
	public ModelAndView newReviewSongFrameLoad(HttpServletRequest request, int songId) {
		//获取选中歌曲的最新评论(目前评论数据很少，先不做分页)
		List<ReviewSong> newReviewList = reviewService.getNewReviewSongBySongIdWithLikeFlag(request,songId);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/WEB-INF/pages/newReviewSongFrame.jsp");
		modelAndView.addObject("newReviewList", newReviewList);

		return modelAndView;
	}

	@RequestMapping(value = "deleteReviewSong.domain", method = { RequestMethod.POST })
	public void deleteReviewSong(HttpServletRequest request, int reviewIds[]) {
		if(userService.isHasPrivilege(request)) {
			reviewService.batchDeleteById(reviewIds);
		}

	}



}
