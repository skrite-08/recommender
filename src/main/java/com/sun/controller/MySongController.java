package com.sun.controller;

import com.alibaba.fastjson.JSONObject;
import com.sun.model.Song;
import com.sun.service.MySongService;
import com.sun.utils.ReturnMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class MySongController {
    @Autowired
    private MySongService mySongService;

    @GetMapping(value = "myMusicFrameLoad.domain")
    public ModelAndView myMusicFrameLoad(HttpServletRequest request) {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/WEB-INF/pages/myMusicFrame.jsp");
        //modelAndView.addObject("oneDayOneWord",OneDayOneWord.getOneDayOneWord(Static.MY_MUSIC_WORD_ARRAY));

        return modelAndView;

    }

    //@GetMapping(value = "collectedFrameLoad.domain")
    @RequestMapping(value = "collectedFrameLoad.domain",method = { RequestMethod.GET })
    public ModelAndView collectedFrameLoad(HttpServletRequest request) {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/WEB-INF/pages/mymusicpage/collectedFrame.jsp");
        List<Song> myLikeSongList=mySongService.getMySongListWithFlag(request);

        //ReturnMsg.msg(HttpServletResponse.SC_OK, JSONObject.toJSON(myLikeSongList).toString());

        modelAndView.addObject("myLikeSongList",myLikeSongList);

        //modelAndView.addObject("oneDayOneWord",OneDayOneWord.getOneDayOneWord(Static.MY_MUSIC_WORD_ARRAY));

        return modelAndView;

    }


    //@GetMapping(value = "recentFrameLoad.domain")
    @RequestMapping(value = "recentFrameLoad.domain",method = { RequestMethod.GET })
    public ModelAndView recentFrameLoad(HttpServletRequest request) {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/WEB-INF/pages/mymusicpage/recentFrame.jsp");
        List<Song> myRecentPlayList=mySongService.getMyRecentPlayListWithFlag(request);

        //ReturnMsg.msg(HttpServletResponse.SC_OK, JSONObject.toJSON(myRecentPlayList).toString());

        modelAndView.addObject("myRecentPlayList",myRecentPlayList);
        //modelAndView.addObject("oneDayOneWord",OneDayOneWord.getOneDayOneWord(Static.MY_MUSIC_WORD_ARRAY));


        return modelAndView;

    }





    @GetMapping(value = "trendingFrameLoad.domain")
    //@RequestMapping(value = "trendingFrameLoad.domain",method = { RequestMethod.GET })
    public ModelAndView trendingFrameLoad(HttpServletRequest request) {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/WEB-INF/pages/mymusicpage/trendingFrame.jsp");
        //List<Song> personalRecSongList=personalRecService.getPersonalDailyRecWithCollectionFlag(request);
        List<Song> personalSongList=mySongService.getPersonalDailySongWithFlag(request);
        modelAndView.addObject("personalSongList",personalSongList);
        //ReturnMsg.msg(HttpServletResponse.SC_OK, JSONObject.toJSON(personalSongList).toString());
//        if(personalSongList==null) {
//            modelAndView.addObject("oneDayOneWord","登录即享——遇见不一样的自己");
//        }else {
//            modelAndView.addObject("oneDayOneWord","更懂你的心");
//        }
        return modelAndView;
    }
}
