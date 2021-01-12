package com.sun.dao;

import com.sun.model.LikingSong;

import java.util.List;

public interface LikingDao {
    /**
     * 查询用户对某歌曲的收藏
     * @param collection
     * 记录对象
     * @return
     * 若没有收藏，则返回null
     */
    public LikingSong selectByCollection(LikingSong collection);

    /**
     * 删除收藏记录
     * @param collectionId
     * 记录Id
     */
    public void deleteById(int collectionId);

    /**
     * 添加新记录
     * @param collection
     */
    public void insert(LikingSong collection);

    /**
     * 查询所有用户的收藏记录
     * @return
     */
    public List<LikingSong> selectAll();
}
