package com.ssafy.happyhouse.model.service;

import com.ssafy.happyhouse.model.dto.User;

public interface UserService {
	int idCheck(String id);
	void registerUser(User user) throws Exception;
	User login(String id, String pass) throws Exception;
	User getUser(String id) throws Exception;
	void updateUser(User user) throws Exception;
	void deleteUser(String id) throws Exception;
}
