package com.ssafy.happyhouse.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NoticeServlet
 */
@WebServlet("/notice")
public class NoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}   
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String act = request.getParameter("act");
		String path = "";
		if("mvnotice".equals(act)) {
			response.sendRedirect("notice.jsp");
		} else if("write".equals(act)) {
			
		} else if("delete".equals(act)) {
			
		} else if("mvwrite".equals(act)) {
			
		} else if("mvupdate".equals(act)) {
			
		} else if("update".equals(act)) {
			
		} else if("view".equals(act)) {
			
		}
	}



}
