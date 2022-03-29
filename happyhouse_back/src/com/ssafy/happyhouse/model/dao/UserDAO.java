package com.ssafy.happyhouse.model.dao;

import java.sql.SQLException;

import com.ssafy.happyhouse.model.dto.User;


public interface UserDAO {
	int idCheck(String id);
	void registerUser(User user) throws SQLException;
	User login(String id, String pass) throws SQLException;
	User getUser(String id) throws SQLException;
	void updateUser(User user) throws SQLException;
	void deleteUser(String id) throws SQLException;
}
