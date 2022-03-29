package com.ssafy.happyhouse.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssafy.happyhouse.model.dao.houseDao;
import com.ssafy.happyhouse.model.dao.houseDaoImpl;
import com.ssafy.happyhouse.model.dto.housedeal;
import com.ssafy.happyhouse.model.dto.houseinfo;

/**
 * Servlet implementation class houseServlet
 */
@WebServlet("/main")
public class houseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final houseDao hdao = houseDaoImpl.getIns();
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String act = request.getParameter("act");
		switch(act) {
			case "list" :
			try {
				show(request, response);
			} catch (ServletException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				break;
			case "result" :
			try {
				doResult(request, response);
			} catch (ServletException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				break;
			case "dealinfo" :
			try {
				dealinfo(request, response);
			} catch (ServletException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				break;
			case "search" :
			try {
				search(request, response);
			} catch (ServletException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				break;
			case "getHouse" :
				try {
					getInfo(request, response);
				} catch (ServletException | IOException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					break;
		}
		
	}
	
	private void getInfo(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException, SQLException {
		List<houseinfo> house = hdao.select();
		request.setAttribute("houses", house);
		RequestDispatcher disp = request.getRequestDispatcher("/index.jsp");
        disp.forward(request, response);
	}
	
	private void show(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException, SQLException {
//		String aptcode = request.getParameter("aptcode");
//		String aptname = request.getParameter("aptname");
//		String dongcode = request.getParameter("dongcode");
//		String dongname = request.getParameter("dobgname");
//		int buildyear = Integer.parseInt(request.getParameter("buildyear"));
//		String jibun = request.getParameter("jibun");
//		
//		houseinfo hi = new houseinfo(aptcode, aptname, dongcode, dongname, buildyear, jibun);
//		
		List<houseinfo> his = hdao.select();
		request.setAttribute("his", his);
		RequestDispatcher disp = request.getRequestDispatcher("/list.jsp");
        disp.forward(request, response);
	}
	
	private void doResult(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException, SQLException {
		String aptcode = request.getParameter("aptcode");
		
		houseinfo hinfo = hdao.getInfo(aptcode);
		request.setAttribute("houseinfo", hinfo);
		List<housedeal> dis = hdao.dealinfo(aptcode);
		request.setAttribute("dis", dis);
		request.getRequestDispatcher("/result.jsp").forward(request, response);
	}

	private void dealinfo(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException, SQLException {
		String aptcode = request.getParameter("aptcode");
		List<housedeal> dis = hdao.dealinfo(aptcode);
		request.setAttribute("dis", dis);
		RequestDispatcher disp = request.getRequestDispatcher("/result.jsp");
        disp.forward(request, response);
	}
	
	private void search(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException, SQLException {
		String aptname = request.getParameter("aptname");
		String dongname = request.getParameter("dongname");
		
		List<houseinfo> shis = hdao.search(aptname, dongname);
		request.setAttribute("shis", shis);
		RequestDispatcher disp = request.getRequestDispatcher("/srst.jsp");
        disp.forward(request, response);
	}


}
