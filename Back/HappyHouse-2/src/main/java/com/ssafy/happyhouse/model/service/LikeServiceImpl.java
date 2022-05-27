package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.LikeDto;
import com.ssafy.happyhouse.model.mapper.LikeMapper;


@Service
public class LikeServiceImpl implements LikeService{
	
	
	@Autowired
	private LikeMapper likeMapper;
	
	
	@Override
	public List<LikeDto> listLike(String user_id) throws Exception {
		return likeMapper.listLike(user_id);
	}

	@Override
	public void register(LikeDto likeDto) throws Exception {
		likeMapper.register(likeDto);
		
	}

	@Override
	public void delete(int no) throws Exception {
		likeMapper.delete(no);
		
	}

	@Override
	public LikeDto getLike(LikeDto likeDto) throws Exception {
		// TODO Auto-generated method stub
		return likeMapper.getLike(likeDto);
	}

	@Override
	public List<HouseInfoDto> getApts(String user_id) throws Exception {
		return likeMapper.getApts(user_id);
	}

	@Override
	public void deleteByAptCode(LikeDto likeDto) throws Exception {
		likeMapper.deleteByAptCode(likeDto);
		
	}

}
