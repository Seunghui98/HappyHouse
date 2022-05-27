package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.LikeDto;


@Mapper
public interface LikeMapper {
	void register(LikeDto likeDto) throws SQLException;
	void delete(int no) throws SQLException;
	void deleteByAptCode(LikeDto likeDto) throws SQLException;
	List<LikeDto> listLike(String user_id) throws Exception;
	LikeDto getLike(LikeDto likeDto) throws Exception;
	List<HouseInfoDto> getApts(String user_id) throws SQLException;
}
