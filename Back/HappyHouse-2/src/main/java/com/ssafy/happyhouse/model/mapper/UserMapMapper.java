package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.UserDto;

@Mapper
public interface UserMapMapper {
	int idCheck(String id);
	void registerUser(UserDto user) throws SQLException;
	UserDto login(UserDto userDto) throws SQLException;
	UserDto getUser(String id) throws SQLException;
	void updateUser(UserDto user) throws SQLException;
	void deleteUser(String id) throws SQLException;
	void updatePassword(UserDto userDto) throws SQLException;
	public void saveRefreshToken(Map<String, String> map) throws SQLException;
	public void deleteRefreshToken(Map<String, String> map) throws SQLException;
	public String getRefreshToken(String userid) throws SQLException;
}
