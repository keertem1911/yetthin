package com.yetthin.web.persistence;

import java.util.List;

import com.yetthin.web.domain.Concept;

public interface ConceptMapper {
    int deleteByPrimaryKey(String conceptId);

    int insert(Concept record);

    int insertSelective(Concept record);

    Concept selectByPrimaryKey(String conceptId);

    int updateByPrimaryKeySelective(Concept record);

    int updateByPrimaryKey(Concept record);

	List<Concept> selectByPrimaryKeyFromAll();
}