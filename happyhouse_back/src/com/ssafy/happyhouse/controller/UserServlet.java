package com.ssafy.happyhouse.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssafy.happyhouse.model.dto.User;
import com.ssafy.happyhouse.model.service.UserService;
import com.ssafy.happyhouse.model.service.UserServiceImpl;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/user")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserService userService = UserServiceImpl.getUserService();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String act = request.getParameter("act");
		String path = "";
		System.out.println(act);
		if("login".equals(act)) {
			path = loginUser(request, response);
			request.getRequestDispatcher(path).forward(request, response);
		} else if("logout".equals(act)) {
			path = logout(request, response);
			response.sendRedirect(path);
		} else if("signup".equals(act)) {
			path = signUp(request, response);
			request.getRequestDispatcher(path).forward(request, response);
		} else if("update".equals(act)) {
			path = update(request, response);
			request.getRequestDispatcher(path).forward(request, response);
		} else if("mvlogin".equals(act)) {
			response.sendRedirect("login.jsp");
		} else if("mvsignup".equals(act)) {
			response.sendRedirect("signup.jsp");
		} else if("mvupdate".equals(act)) {
			path = mvUpdate(request, response);
			request.getRequestDispatcher(path).forward(request, response);
		} else if("delete".equals(act)) {
			path = delete(request, response);
			response.sendRedirect(path);
		} else if("mypage".equals(act)) {
			path = getUser(request, response);
			request.getRequestDispatcher(path).forward(request, response);
		}
	}
	
	private String delete(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		try {
			userService.deleteUser(id);
			HttpSession session = request.getSession();
			session.invalidate();
			return "index.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "회원 탈퇴 중 에러가 발생했습니다.");
			return "error/error.jsp";
		}
	}
	
	private String update(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phoneNumber = request.getParameter("phoneNumber");
		User user = new User(id, password, name, address, phoneNumber);
		try {
			userService.updateUser(user);
			request.setAttribute("userInfo", user);
			return "/mypage.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "사용자 정보 수정중 에러가 발생했습니다.");
			return "error/error.jsp";
		}
	}
	
	private String mvUpdate(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("userInfo");
		if(user != null) {
			return "update.jsp";
		} else {
			return "/user?act=mvlogin";
		}
		
	}
	
	private String getUser(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("userInfo");
		if(user != null) {
			try {
				request.setAttribute("userInfo", user);
				return "/mypage.jsp";
			} catch(Exception e) {
				e.printStackTrace();
				request.setAttribute("msg", "사용자 정보를 얻기 중에 에러가 발생했습니다.");
				return "/error/error.jsp";
			}
		} else {
			return "/user?act=mvlogin";
		}
	}
	
	private String signUp(HttpServletRequest request, HttpServletResponse response) {
		User user = new User();
		user.setId(request.getParameter("id"));
		user.setPassword(request.getParameter("password"));
		user.setName(request.getParameter("name"));
		user.setAddress(request.getParameter("address"));
		user.setPhoneNumber(request.getParameter("phoneNumber"));
		
		try {
			userService.registerUser(user);
			return "/login.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "회원 가입 중 문제가 발생했습니다.");
			return "error/error.jsp";
		}
	}
	
	private String logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "index.jsp";
	}

	private String loginUser(HttpServletRequest request, HttpServletResponse response) {
		User user = null;
		String id = request.getParameter("id");
		String pass = request.getParameter("password");
		System.out.println(id +" "+pass);
		
		try {
			user = userService.login(id, pass);
			if(user != null) { // 로그인 성공
//				session setting
				HttpSession session = request.getSession();
				session.setAttribute("userInfo", user);
				
				return "/index.jsp";
			} else { // 로그인 실패
				request.setAttribute("msg", "아이디 또는 비밀번호 확인 후 다시 로그인하세요.");
				return "/login.jsp";
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "로그인 처리중 문제 발생!!");
			return "error/error.jsp";
		}
	}

}
