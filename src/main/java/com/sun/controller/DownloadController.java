package com.sun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.sun.service.DownloadService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

@Controller
public class DownloadController {
	@Autowired
	private DownloadService downloadService;

	@RequestMapping(value = "download.domain", method = { RequestMethod.GET})
	public void download(HttpServletRequest request,HttpServletResponse response,String songAddress,int songId) throws IOException {
		//对于登录用户，记录其下载记录
		downloadService.recordDownload(request,songId);
		//DownloadService.recordDownload(request, songId);

		response.setContentType("audio/mp3");
		response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(System.currentTimeMillis()+"如果不想返回名称的话.mp3", "utf8"));
		BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
		InputStream bis=null;
		if(songAddress.contains("http")) {
			//在另外服务器的文件
			URL url = new URL(songAddress);
            URLConnection uc = url.openConnection();
			bis=new BufferedInputStream(uc.getInputStream());
		}else {
			//在服务器内部的文件
			songAddress=request.getServletContext().getRealPath(songAddress);
			bis = new BufferedInputStream(new FileInputStream(new File(songAddress)));
		}
		int len = 0;
		while((len = bis.read()) != -1){
            out.write(len);
            out.flush();
        }
		out.close();
		bis.close();

	}


}
