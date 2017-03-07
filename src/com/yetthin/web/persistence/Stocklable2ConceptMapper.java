package com.yetthin.web.persistence;

import java.util.List;

import com.yetthin.web.domain.StockLable;
import com.yetthin.web.domain.Stocklable2Concept;

public interface Stocklable2ConceptMapper {
    int deleteByPrimaryKey(String stocklable2conceptId);

    int insert(Stocklable2Concept record);

    int insertSelective(Stocklable2Concept record);

    Stocklable2Concept selectByPrimaryKey(String stocklable2conceptId);

    int updateByPrimaryKeySelective(Stocklable2Concept record);

    int updateByPrimaryKey(Stocklable2Concept record);

	List<StockLable> selectStockByConceptId(List<String> conceptIds);
}