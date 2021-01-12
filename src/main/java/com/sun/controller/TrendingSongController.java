package com.sun.controller;

import com.alibaba.fastjson.JSONObject;
import com.sun.model.Song;
import com.sun.service.TrendingSongService;
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
public class TrendingSongController {

    @Autowired
    private TrendingSongService trendingSongService;

    @RequestMapping(value = "trendingSongFrameLoad.domain",method = { RequestMethod.GET })
    public ModelAndView trendingRecFrameLoad(HttpServletRequest request) {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/WEB-INF/pages/trendingSongFrame.jsp");
        List<Song> trendingSongList=trendingSongService.getSongWithFlag(request);

        ReturnMsg.msg(HttpServletResponse.SC_OK, JSONObject.toJSON(trendingSongList).toString());

        modelAndView.addObject("trendingSongList",trendingSongList);
        modelAndView.addObject("test","Name");

        return modelAndView;

    }


}
