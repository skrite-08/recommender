package com.sun.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.service.PlayService;
import com.sun.service.SongService;
import com.sun.service.UserService;
import com.sun.utils.ReturnMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

import com.sun.model.User;

@Controller
public class SongController {
    @Autowired
    private UserService userService;
    @Autowired
    private SongService songService;
    @Autowired
    private PlayService recordPlayService;


    @GetMapping(value = "recordPlay.domain")
    public void recordPlay(HttpServletRequest request,int songId) {
        recordPlayService.recordPlay(request,songId);

    }

    @RequestMapping(value = "deleteSong.domain", method = { RequestMethod.POST })
    public void deleteSong(HttpServletRequest request, int songIds[]) {
        if(userService.isHasPrivilege(request)) {
            songService.batchDeleteById(request,songIds);
        }

    }

    @PostMapping(value = "addSong.domain")
    @ResponseBody
    public String addSong(HttpServletRequest request, MultipartFile song, MultipartFile lyric) {
        if(userService.isHasPrivilege(request) && songService.checkFormat(song,lyric)) {
            boolean isSuccessful=songService.addSong(request,song,lyric);
            if(isSuccessful) {
                return ReturnMsg.msg(HttpServletResponse.SC_OK, "上传成功");
            }else {
                return ReturnMsg.msg(HttpServletResponse.SC_BAD_REQUEST, "上传失败");
            }
        }

        return ReturnMsg.msg(HttpServletResponse.SC_BAD_REQUEST, "格式错误");
    }

}

