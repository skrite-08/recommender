package com.sun.method;

import com.sun.model.LikingSong;
import com.sun.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.web.context.WebApplicationContext;

import com.sun.model.DownloadSongRecord;
import com.sun.model.PlaySongRecord;
import com.sun.model.Song;
import com.sun.service.*;
import com.sun.utils.Static;

import javax.servlet.ServletContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * It’s not easy when  you want to make something change, but if it’s easy that everyone can make it.
 */
public class DailyAction implements ApplicationListener<ContextRefreshedEvent>{
	@Autowired
	private PersonalSongService personalSongService;
	@Autowired
	private DownloadService downloadService;
	@Autowired
	private PlayService recordPlayService;
	@Autowired
	private LikingService likingService;
	@Autowired
	private UserService userService;
	@Autowired
	private SongService songService;
	/**
	 * 是否第一次初始化
	 */
	private static volatile boolean isFirtTimeInit=true;

	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		System.out.println("###-----Spring 容器加载完毕_-_-----###");
		init(arg0);
	}

	private void init(ContextRefreshedEvent arg0) {
		if(isFirtTimeInit) {
			System.out.println("###-----开始Listener_-_-----###");
			ApplicationContext applicationContext = arg0.getApplicationContext();  
			WebApplicationContext webApplicationContext = (WebApplicationContext)applicationContext;
			final ServletContext servletContext = webApplicationContext.getServletContext();  
			
			Listener listener=new Listener(new TimerTask() {

				@Override
				public void run() {
					System.out.println("------------开始执行任务-------------");
					
					try {
						//等待10s再开始执行任务
						TimeUnit.SECONDS.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					userService.getAllUserRecords();
					//更换推荐列表
					Static.isFromA=!Static.isFromA;
					//用户-歌曲 推荐列表
					Map<Integer,Integer[]> user2songRecMatrix=new HashMap<Integer, Integer[]>();
					//获取用户的下载记录
					List<DownloadSongRecord> downloadList=downloadService.getAllRecords();
					//获取用户的播放记录
					List<PlaySongRecord> playList=recordPlayService.getAllRecords();
					//获取用户的收藏记录
					List<LikingSong> collectionList=likingService.getAllRecords();
					//获取用户
					List<Integer> userIdList=userService.getAllUserIdRecords();
					//获取歌曲
					List<Integer> songIdList=songService.getAllSongIdRecords();
					//用户-歌曲 “评分”矩阵
					Map<Integer, float[]> user2songRatingMatrix=DataTranslate.getFrequencyMatrix(userIdList,songIdList,
							downloadList,playList,collectionList);
					//用户相似性计算，获取用户的k个近邻用户
					Map<Integer,Integer[]> userKNNMatrix=UserKNN.getKNN(userIdList,user2songRatingMatrix,Static.K);
					//基于用户相似性的协同过滤
					user2songRecMatrix=CollaborativeFiltering.userKNNBasedCF(userIdList,userKNNMatrix,
							user2songRatingMatrix,songIdList,Static.N);
					System.out.println("------------执行任务完成-------------");
					if(Static.isFromA) {
						//向B中更新写数据
						personalSongService.updatePersonalSongIntoB(user2songRecMatrix);
					}else {
						//向A中更新写数据
						personalSongService.updatePersonalSongIntoA(user2songRecMatrix);
					}
					//是否开启混合
//					if(Static.IS_HYBRID) {
//						//获取歌曲信息
//						List<Song> songList=songService.getAllSongRecordsWithLyric();
//						if(songList!=null && songList.size()>1) {
//							Map<Integer,Integer[]> user2songRecMatrixHybrid=Hybrid.open(songList,user2songRecMatrix,collectionList,playList, servletContext);
//							System.out.println("----混合 done----");
//							if(Static.isFromA) {
//								//向B中添加数据
//								personalSongService.addHybridRecIntoB(user2songRecMatrixHybrid);
//							}else {
//								//向A中添加数据
//								personalSongService.addHybridRecIntoA(user2songRecMatrixHybrid);
//							}
//						}
//					}
				}
				
			});
			//开始执行监听
			listener.listen(Static.START_HOUR, Static.START_MINUTE, 
					Static.START_SECOND, Static.PERIOD_DAY, Static.IS_START_TOMORROW);
		}
		isFirtTimeInit=false;
	}

}
