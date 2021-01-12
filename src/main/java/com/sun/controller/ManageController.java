package com.sun.controller;

import com.alibaba.fastjson.JSONObject;
import com.sun.dao.ReviewDao;
import com.sun.dao.SongDao;
import com.sun.dao.UserDao;
import com.sun.model.ReviewSong;
import com.sun.model.Song;
import com.sun.model.User;
import com.sun.service.ReviewService;
import com.sun.service.SongService;
import com.sun.service.UserService;
import com.sun.utils.ReturnMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class ManageController {

    @Autowired
    private UserService userService;
    @Autowired
    private SongService songService;
    @Autowired
    private ReviewService reviewService;

    @RequestMapping("userManageFrameLoad.domain")
    public ModelAndView userManageFrameLoad(HttpServletRequest request){

        ModelAndView modelAndView=new ModelAndView();

        modelAndView.setViewName("/WEB-INF/pages/manage/userManageFrame.jsp");
        List<User> userManageList=userService.getAllUserRecords();

        ReturnMsg.msg(HttpServletResponse.SC_OK, JSONObject.toJSON(userManageList).toString());

        modelAndView.addObject("userManageList",userManageList);

        return modelAndView;
    }

    @RequestMapping("songManageFrameLoad.domain")
    public ModelAndView songManageFrameLoad(HttpServletRequest request){

        ModelAndView modelAndView=new ModelAndView();

        modelAndView.setViewName("/WEB-INF/pages/manage/songManageFrame.jsp");
        List<Song> songManageList=songService.getAllSongRecords();

        ReturnMsg.msg(HttpServletResponse.SC_OK, JSONObject.toJSON(songManageList).toString());

        modelAndView.addObject("songManageList",songManageList);

        return modelAndView;
    }

    @RequestMapping("reviewSongmanageFrameLoad.domain")
    public ModelAndView reviewSongManageFrameLoad(HttpServletRequest request){

        ModelAndView modelAndView=new ModelAndView();

        return modelAndView;
    }


    @RequestMapping(value = "deleteUser.domain", method = { RequestMethod.POST })
    public void deleteUser(HttpServletRequest request, int userIds[]) {
        if(userService.isHasPrivilege(request)) {
            userService.batchDeleteById(userIds);
        }

    }


}
