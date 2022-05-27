package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.LikeDto;



public interface LikeService {
	List<LikeDto> listLike(String user_id) throws Exception;
	void register(LikeDto likeDto) throws Exception;
	void delete(int no) throws Exception;
	void deleteByAptCode(LikeDto likeDto) throws Exception;
	LikeDto getLike(LikeDto likeDto) throws Exception;
	List<HouseInfoDto> getApts(String user_id) throws Exception;
}
