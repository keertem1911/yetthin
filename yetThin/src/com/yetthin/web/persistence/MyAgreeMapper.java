package com.yetthin.web.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yetthin.web.domain.MyAgree;

public interface MyAgreeMapper {
    int deleteByPrimaryKey(Integer agreeId);

    int insert(MyAgree record);

    int insertSelective(MyAgree record);

    MyAgree selectByPrimaryKey(Integer agreeId);

    int updateByPrimaryKeySelective(MyAgree record);

    int updateByPrimaryKey(MyAgree record);
    /**
     * 通过组合id获取 点赞集合
     */
    List<MyAgree> selectByGroupId(String groupId);
    /**
     * 通过组合及id查找点赞对象
     */
    MyAgree selectByGroupIdAndUserId(Map<String, String> map);
    /**
     * 统计点赞的组合数量
     */
    int selectCountByGroupId(String groupId);
    /**
     * 
     */
    List<MyAgree> selectByUserId(String userId);
}