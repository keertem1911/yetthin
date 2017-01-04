package com.yetthin.web.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import com.yetthin.web.domain.BarDataOfDay;

public interface BarDataOfDayMapper {
    int deleteByPrimaryKey(String id);

    int insert(BarDataOfDay record);

    int insertSelective(BarDataOfDay record);

    BarDataOfDay selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BarDataOfDay record);

    int updateByPrimaryKey(BarDataOfDay record);

	List<BarDataOfDay> selectBarDataByStockCodes(List<String> symbols);
	
	List<BarDataOfDay> selectByDates(@Param("begin")String begin,@Param("end")String end);

	List<String> selectLimitDay(int n);
	
	List<BarDataOfDay> selectBetweenDaY(Map<String, Object> map);
}