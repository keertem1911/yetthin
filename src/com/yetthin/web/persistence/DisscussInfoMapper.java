package com.yetthin.web.persistence;

import java.util.List;

import com.yetthin.web.domain.DisscussInfo;
import com.yetthin.web.domain.GroupRecommend;

public interface DisscussInfoMapper {
    int deleteByPrimaryKey(String discussinfoId);

    int insert(DisscussInfo record);

    int insertSelective(DisscussInfo record);

    DisscussInfo selectByPrimaryKey(String discussinfoId);

    int updateByPrimaryKeySelective(DisscussInfo record);

    int updateByPrimaryKeyWithBLOBs(DisscussInfo record);

    int updateByPrimaryKey(DisscussInfo record);

	List<GroupRecommend> selectByGroupId(String groupId);

	List<GroupRecommend> selectByDisscussionFromDisscuss(List<String> disscussIds);

	List<DisscussInfo> selectByGroupIdFromList(List<String> commendList);

	int deleteByGroupId(String groupId);

	String selectByGroupIdFindDissinfo(String groupId);
}