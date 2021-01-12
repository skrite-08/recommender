package com.sun.controller;

import com.sun.model.ReviewSong;
import com.sun.model.Song;
import com.sun.model.User;
import com.sun.service.SearchService;
import com.sun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class SearchController {
    @Autowired
    private SearchService searchService;
    @Autowired
    private UserService userService;

    /**
     *
     * @param request
     * @param keyword
     * @param mode
     * mode =0 :音乐搜索 ;
     * mode =1 :用户搜索 ;
     * mode =2 :评论搜索 ;
     * mode =null :用户及以下身份搜索
     * @return
     */
    @RequestMapping(value = "searchFrameLoad.domain",method = { RequestMethod.GET })
    public ModelAndView searchFrameLoad(HttpServletRequest request, String keyword, String mode) {
        ModelAndView modelAndView=new ModelAndView();
        //管理员搜索
        if(mode!=null && userService.isHasPrivilege(request)) {
            int modeInt=Integer.parseInt(mode);
            if(modeInt==0) {
                //歌曲搜索
                modelAndView.setViewName("/WEB-INF/pages/songManageSearchFrame.jsp");
                List<Song> songManageSearchList=searchService.getSearchSong(keyword);
                modelAndView.addObject("songManageSearchList",songManageSearchList);
                if(songManageSearchList.size()==0) {
                    modelAndView.addObject("oneDayOneWord","下落不明");
                }else {
                    //modelAndView.addObject("oneDayOneWord",OneDayOneWord.getOneDayOneWord(Static.SEARCH_WORD_ARRAY));
                }

            }else if(modeInt==1) {
                //用户搜索
                modelAndView.setViewName("/WEB-INF/pages/userManageSearchFrame.jsp");
                List<User> userManageSearchList=searchService.getSearchUser(request,keyword);
                modelAndView.addObject("userManageSearchList",userManageSearchList);
                if(userManageSearchList.size()==0) {
                    modelAndView.addObject("oneDayOneWord","下落不明");
                }else {
                    //modelAndView.addObject("oneDayOneWord",OneDayOneWord.getOneDayOneWord(Static.SEARCH_WORD_ARRAY));
                }

            }else {
                //评论搜索
                modelAndView.setViewName("/WEB-INF/pages/reviewManageSearchFrame.jsp");
                List<ReviewSong> reviewSongManageSearchList=searchService.getSearchReviewSong(keyword);
                modelAndView.addObject("reviewSongManageSearchList",reviewSongManageSearchList);
                if(reviewSongManageSearchList.size()==0) {
                    modelAndView.addObject("oneDayOneWord","下落不明");
                }else {
                    //modelAndView.addObject("oneDayOneWord",OneDayOneWord.getOneDayOneWord(Static.SEARCH_WORD_ARRAY));
                }

            }
        }else {
            //普通用户及以下搜索
            modelAndView.setViewName("searchFrame");
            List<Song> searchSongList=searchService.getSearchSongWithFlag(request,keyword);
            modelAndView.addObject("searchSongList",searchSongList);
            if(searchSongList.size()==0) {
                modelAndView.addObject("oneDayOneWord","下落不明");
            }else {
                //modelAndView.addObject("oneDayOneWord",OneDayOneWord.getOneDayOneWord(Static.SEARCH_WORD_ARRAY));
            }

        }

        return modelAndView;
    }

}
