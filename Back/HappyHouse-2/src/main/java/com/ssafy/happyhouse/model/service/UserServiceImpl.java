package com.ssafy.happyhouse.model.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.UserDto;
import com.ssafy.happyhouse.model.mapper.UserMapMapper;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapMapper userMapMapper;
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int idCheck(String id) {
		return userMapMapper.idCheck(id);
	}

	@Override
	public void registerUser(UserDto user) throws Exception {
		userMapMapper.registerUser(user);
		
	}

	@Override
	public UserDto login(UserDto userDto) throws Exception {
		return userMapMapper.login(userDto);
	}

	@Override
	public UserDto getUser(String id) throws Exception {
		// TODO Auto-generated method stub
		return userMapMapper.getUser(id);
	}

	@Override
	public void updateUser(UserDto user) throws Exception {
		userMapMapper.updateUser(user);
		
	}

	@Override
	public void deleteUser(String id) throws Exception {
		userMapMapper.deleteUser(id);
		
	}
	@Override
	public void saveRefreshToken(String userid, String refreshToken) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("token", refreshToken);
		sqlSession.getMapper(UserMapMapper.class).saveRefreshToken(map);
		
	}

	@Override
	public String getRefreshToken(String userid) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(UserMapMapper.class).getRefreshToken(userid);
	}

	@Override
	public void deleRefreshToken(String userid) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("token", null);
		sqlSession.getMapper(UserMapMapper.class).deleteRefreshToken(map);
		
	}

	@Override
	public void updatePassword(UserDto user) throws Exception {
		userMapMapper.updatePassword(user);
		
	}
	

}
