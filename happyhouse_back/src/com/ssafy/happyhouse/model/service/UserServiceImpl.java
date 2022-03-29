package com.ssafy.happyhouse.model.service;

import com.ssafy.happyhouse.model.dao.UserDAO;
import com.ssafy.happyhouse.model.dao.UserDAOImpl;
import com.ssafy.happyhouse.model.dto.User;

public class UserServiceImpl implements UserService{
	private UserDAO userDao = UserDAOImpl.getMemberDao();
	private static UserServiceImpl userService = new UserServiceImpl();
	
	private UserServiceImpl() {

	}
	
	public static UserService getUserService() {
		return userService;
	}
	
	@Override
	public int idCheck(String id) {
		return userDao.idCheck(id);
	}

	@Override
	public void registerUser(User user) throws Exception {
		userDao.registerUser(user);
		
	}

	@Override
	public User login(String id, String pass) throws Exception {
		return userDao.login(id, pass);
	}

	@Override
	public User getUser(String id) throws Exception {
		// TODO Auto-generated method stub
		return userDao.getUser(id);
	}

	@Override
	public void updateUser(User user) throws Exception {
		userDao.updateUser(user);
		
	}

	@Override
	public void deleteUser(String id) throws Exception {
		userDao.deleteUser(id);
		
	}
	

}
