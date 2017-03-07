package com.yetthin.web.persistence;

import java.util.List;
import java.util.Map;

import com.yetthin.web.domain.Share;

public interface ShareMapper {
    int deleteByPrimaryKey(String shareId);

    int insert(List<Share> record);

    int insertSelective(Share record);

    Share selectByPrimaryKey(String shareId);

    int updateByPrimaryKeySelective(Share record);

    int updateByPrimaryKey(Share record);

	List<Share> selectIndustry(String groupId);

	List<Share> selectByGroupIdList(String groupId);

	List<Share> selectSharesAll();

	int deleteByGroupId(String groupId);

	Long countSumStartFundByGroupId(String groupId);

	Map<String, Double> selectSumFundByGroupId(String groupId);

	void updateDeleteStatus(String groupId);

	List<Share> selectDeletedShare(String groupId);

	Share selectByGroupIdOne(String groupId);

	int updateShareStatusToAble(String shareId);

	List<Share> selectAllShareByGroupIdList(String groupId);

	 

	 
	 
	 

	 
}