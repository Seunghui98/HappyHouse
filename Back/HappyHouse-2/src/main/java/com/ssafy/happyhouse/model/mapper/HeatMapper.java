package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.HeatDto;

@Mapper
public interface HeatMapper {
	List<HeatDto> getList() throws SQLException;
	void register(String dongName) throws SQLException;
	
}
