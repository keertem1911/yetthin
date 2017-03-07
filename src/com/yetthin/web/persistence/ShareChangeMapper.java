package com.yetthin.web.persistence;

import java.util.List;

import com.yetthin.web.domain.ShareChange;

public interface ShareChangeMapper {
    int deleteByPrimaryKey(String sharechangeId);

    int insert(List<ShareChange> record);

    int insertSelective(ShareChange shareChanges);

    ShareChange selectByPrimaryKey(String sharechangeId);

    int updateByPrimaryKeySelective(ShareChange record);

    int updateByPrimaryKey(ShareChange record);

	ShareChange selectLastChangeTime(String shareId);

	String getLastShareChangeByGroupId(String groupId);

	List<ShareChange> selectByGroupId(String groupId);
}