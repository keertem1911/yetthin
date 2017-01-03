package com.yetthin.web.service;

import com.yetthin.web.domain.StockOfGroup;
import com.yetthin.web.domain.StockOfGroupreq;

public interface GroupService {
	String getRecommend(String nameOrid,String path);
	String getAnalyse(String nameOrid);
	String getComponent(String nameOrid);
	/**
	 * 返回详情页面 
	 * @param nameOrid
	 * @param path
	 * @param userId
	 * @return
	 */
	String getDetail(String nameOrid,String path,String userId);
	String stockOfGroupSave(StockOfGroup stockOfGroup);
	String getStockofGroup(StockOfGroupreq req);
	String getStockTypeList(int stockType);
	String getSummarize(int pageNum, int pageSize);
	String getStockType();
	String saveRecommend(String groupNameOrId, 
			String upRecommendUserId, String context,String userId);
	int deleteMyGroups(String userId, String groupId);
	/**
	 * 点赞
	 */
	String saveMyAgree(String groupId,String userId);
	/**
	 * 获取组合的点赞数
	 * @param groupId
	 * @return
	 */
	String getMyGroupAgreeNumByGroupId(String groupId);
	/**
	 * 取消点赞
	 */
	String deleteMyAgreeByGroupIdAndUserId(String groupId,String userId);
	/**
	 * 收藏组合
	 */
	String saveMyCollection(String groupId,String userId);
	/**
	 * 取消收藏组合
	 */
	String cancelMyCollection(String userId,String groupId);
//	/**
//	 * 获取 收藏组合列表
//	 */
//	String getDetailByMyCollection(String groupId);
	/**
	 * 获取 用户收藏组合的列表
	 */
	String getSummarizeFromMyCollection(String userId,int pageNum, int pageSize);
}
