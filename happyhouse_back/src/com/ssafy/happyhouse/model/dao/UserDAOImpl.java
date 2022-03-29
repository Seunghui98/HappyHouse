package com.ssafy.happyhouse.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ssafy.happyhouse.model.dto.User;
import com.ssafy.util.DBUtil;

public class UserDAOImpl implements UserDAO{
	private DBUtil dbUtil = DBUtil.getInstance();
	
	private static UserDAOImpl userDao = new UserDAOImpl();
	
	private UserDAOImpl() {}

	public static UserDAO getMemberDao() {
		return userDao;
	}
	
	@Override
	public int idCheck(String id) {
		int cnt = 1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder loginMember = new StringBuilder();
			loginMember.append("select count(userid) \n");
			loginMember.append("from ssafy_member \n");
			loginMember.append("where userid = ?");
			pstmt = conn.prepareStatement(loginMember.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			rs.next();
			cnt = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			cnt = 1;
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return cnt;
	}

	@Override
	public void registerUser(User user) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder registerMember = new StringBuilder();
			registerMember.append("insert into user (id, password, name, address, phoneNumber) \n");
			registerMember.append("values (?, ?, ?, ?, ?)");
			pstmt = conn.prepareStatement(registerMember.toString());
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getName());
			pstmt.setString(4, user.getAddress());
			pstmt.setNString(5,  user.getPhoneNumber());
			pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}
		
	}

	@Override
	public User login(String id, String pass) throws SQLException {
		User user = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dbUtil.getConnection();
			StringBuilder loginMember = new StringBuilder();
			loginMember.append("select * \n");
			loginMember.append("from user \n");
			loginMember.append("where id = ? and password = ? \n");
			pstmt = conn.prepareStatement(loginMember.toString());
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setId(rs.getString("id"));
				user.setName(rs.getString("name"));
				user.setId(id);
				user.setPassword(pass);
				user.setPhoneNumber(rs.getString("phoneNumber"));
				user.setAddress(rs.getString("address"));
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return user;
	}

	@Override
	public User getUser(String id) throws SQLException {
		User user = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dbUtil.getConnection();
			StringBuilder loginMember = new StringBuilder();
			loginMember.append("select * \n");
			loginMember.append("from user \n");
			loginMember.append("where id = ?");
			pstmt = conn.prepareStatement(loginMember.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setName(rs.getString("name"));
				user.setId(id);
				user.setPassword(rs.getString("password"));
				user.setPhoneNumber(rs.getString("phoneNumber"));
				user.setAddress(rs.getString("address"));
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return user;
	}

	@Override
	public void updateUser(User user) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder registerMember = new StringBuilder();
			registerMember.append("update user set password=?, name=?, address=?, phoneNumber=? \n");
			registerMember.append("where id=?");
			pstmt = conn.prepareStatement(registerMember.toString());
	
			pstmt.setString(1, user.getPassword());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getAddress());
			pstmt.setString(4, user.getPhoneNumber());
			pstmt.setNString(5,  user.getId());
			pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}
		
	}

	@Override
	public void deleteUser(String id) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder registerMember = new StringBuilder();
			registerMember.append("delete from user where id = ?");
			pstmt = conn.prepareStatement(registerMember.toString());
			pstmt.setString(1, id);
			pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}
		
	}

}
