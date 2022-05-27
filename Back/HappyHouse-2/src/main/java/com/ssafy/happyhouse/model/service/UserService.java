package com.ssafy.happyhouse.model.service;

import java.util.Map;

import com.ssafy.happyhouse.model.UserDto;


public interface UserService {
	int idCheck(String id);
	void registerUser(UserDto user) throws Exception;
	UserDto login(UserDto userDto) throws Exception;
	UserDto getUser(String id) throws Exception;
	void updateUser(UserDto user) throws Exception;
	void updatePassword(UserDto user) throws Exception;
	void deleteUser(String id) throws Exception;
	public void saveRefreshToken(String userid, String refreshToken) throws Exception;
	public String getRefreshToken(String userid) throws Exception;
	public void deleRefreshToken(String userid) throws Exception;
}
