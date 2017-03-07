package com.yetthin.web.persistence;

import java.util.List;
import java.util.Map;

import com.yetthin.web.domain.MyCollection;

public interface MyCollectionMapper {
    int deleteByPrimaryKey(Integer collectionId);

    int insert(MyCollection record);

    int insertSelective(MyCollection record);

    MyCollection selectByPrimaryKey(Integer collectionId);

    int updateByPrimaryKeySelective(MyCollection record);

    int updateByPrimaryKey(MyCollection record);
    /**
     * 获取用户的所有收藏
     * @param userId
     * @return
     */
    List<MyCollection> getMyCollectionByUserId(String userId);
    /**
     * 获取指定的收藏 
     * @param 
     * groupId
     * userId
     * @return
     */
    MyCollection selectByUserIdAndGroupId(Map<String, String> map);
    /**
     * 根据 用户的id获取收藏的 组合Id
     * @param userId
     * @return
     */
	List<String> selectByUserId(String userId);
	/**
	 * 
	 */

}