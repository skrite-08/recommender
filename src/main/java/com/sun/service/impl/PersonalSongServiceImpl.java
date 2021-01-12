package com.sun.service.impl;

import com.sun.dao.NewTrackOnShelfDao;
import com.sun.dao.PersonalSongDao;
import com.sun.dao.TrendingSongDao;
import com.sun.dao.UserDao;
import com.sun.model.LikingSong;
import com.sun.model.Song;
import com.sun.model.User;
import com.sun.service.PersonalSongService;
import com.sun.utils.Request;
import com.sun.utils.Static;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.function.BiConsumer;

@Controller
public class PersonalSongServiceImpl implements PersonalSongService {
    @Autowired
    private PersonalSongDao personalSongDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private TrendingSongDao trendingSongDao;
    @Autowired
    private NewTrackOnShelfDao newTrackOnShelfDao;

    public List<Song> getPersonalDailySongWithFlag(HttpServletRequest request) {
        List<Song> personalRecList = new ArrayList<Song>();
        List<LikingSong> collectionList = new ArrayList<>();
        User user = userDao.selectByUser(Request.getUserFromHttpServletRequest(request));
        collectionList = trendingSongDao.getLikingSong(user);
        /* =============================================================== */
        personalRecList=selectPersonalRec(user);
        /* =============================================================== */
        // 在个性化列表中给已经被该用户收藏的歌曲加上标记
        if (collectionList != null && personalRecList != null) {
            for (LikingSong c : collectionList) {
                for (Song t : personalRecList) {
                    if (c.getSongId() == t.getSongId()) {
                        t.setWhetherCollected(true);
                    }
                }
            }
        }
        return personalRecList;
    }

    /**
     * 每天早上6点更新个性化推荐列表，从更新后的表中读取记录
     * 这里采用两张表交替的方式来实现：
     * （1）	6点之后就从另外一张表中读取记录
     * （2）	重新开始计算新的个性化推荐列表存放于原来的表中的
     * @param user
     * @return
     */
    private List<Song> selectPersonalRec(User user) {
        if(user==null) return null;
        List<Song> personalRecList = new ArrayList<Song>();
        if(Static.isFromA) {
            personalRecList=personalSongDao.selectPersonalSongFromA(user);
        }else {
            personalRecList=personalSongDao.selectPersonalSongFromB(user);
        }
        return personalRecList;
    }

    public void initializePersonalSongList(HttpServletRequest request) {
        final User user = userDao.selectByUser(Request.getUserFromHttpServletRequest(request));
        List<Song> initialRecListA = new ArrayList<Song>();
        List<Song> initialRecListB = new ArrayList<Song>();
        //从新歌中随机获取10首，作为初始化列表
        initialRecListA=newTrackOnShelfDao.selectNewSong();
        for(int i=0;i<40;i++) {
            int len=initialRecListA.size();
            Random random=new Random();
            int index=random.nextInt(len);
            if(i<10) {
                initialRecListB.add(initialRecListA.get((index+1)%len));
            }
            initialRecListA.remove(index);
        }
        //批量插入
        if(Static.isFromA) {
            personalSongDao.insertListIntoRecA(initialRecListA,user.getUserId());
        }else {
            personalSongDao.insertListIntoRecB(initialRecListB,user.getUserId());
        }

    }

    public void updatePersonalSongIntoB(Map<Integer, Integer[]> user2song) {
        // TODO Auto-generated method stub
        user2song.forEach(new BiConsumer<Integer,Integer[]>(){

            public void accept(Integer userId, Integer[] recSongIds) {
                // TODO Auto-generated method stub
                personalSongDao.deleteBByUserId(userId);
                //批量插入
                personalSongDao.insertArrayIntoRecB(recSongIds,userId);

            }

        });

    }

    public void updatePersonalSongIntoA(Map<Integer, Integer[]> user2song) {
        // TODO Auto-generated method stub
        user2song.forEach(new BiConsumer<Integer,Integer[]>(){

            public void accept(Integer userId, Integer[] recSongIds) {
                // TODO Auto-generated method stub
                personalSongDao.deleteAByUserId(userId);
                //批量插入
                personalSongDao.insertArrayIntoRecA(recSongIds,userId);

            }

        });

    }

    public void addHybridRecIntoA(Map<Integer, Integer[]> user2song) {
        user2song.forEach(new BiConsumer<Integer,Integer[]>(){

            public void accept(Integer userId, Integer[] recSongIds) {
                //批量插入
                personalSongDao.insertArrayIntoRecA(recSongIds,userId);
            }

        });

    }

    public void addHybridRecIntoB(Map<Integer, Integer[]> user2song) {
        user2song.forEach(new BiConsumer<Integer,Integer[]>(){

            public void accept(Integer userId, Integer[] recSongIds) {
                //批量插入
                personalSongDao.insertArrayIntoRecB(recSongIds,userId);
            }

        });

    }
}
