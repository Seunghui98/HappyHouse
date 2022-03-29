package com.ssafy.happyhouse.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.util.DBUtil;
import com.ssafy.happyhouse.model.dto.housedeal;
import com.ssafy.happyhouse.model.dto.houseinfo;

public class houseDaoImpl implements houseDao {
	private final DBUtil util = DBUtil.getInstance();
	private static houseDaoImpl ins = new houseDaoImpl();
	private houseDaoImpl() {	}
	public static houseDaoImpl getIns() {
		return ins;
	}
	
	public List<houseinfo> select() throws SQLException {
		String sql = "select hi.aptCode, hi.aptName, dc.sidoName, dc.gugunName, dc.dongName, hi.buildYear, hi.jibun \n"
				+ "from houseinfo hi, dongcode dc \n"
				+ "where hi.dongCode = dc.dongCode \n"
				+ "order by 1";
		
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        
        List<houseinfo> result = new ArrayList<>();
        try {
            // DBUtil을 통해 Connection 객체를 가져온다.
            con = util.getConnection();
            // Connection을 통해 PreparedStatement를 가져온다.
            pstmt = con.prepareStatement(sql);
            // PreparedStatement를 실행한다. select 계열이므로 executeQuery를 사용한다. 결과로 ResultSet을 얻는다.
            rset = pstmt.executeQuery();
            // 여러 개의 자료가 반환될 수 있으므로 while 문장을 이용한다.
            while (rset.next()) {
                // ResultSet을 통해서 조회 결과를 넘겨받는다.
                // 조회 결과를 이용해 Book 객체를 생성하고 list에 담는다.
                houseinfo hi = new houseinfo(rset.getString("hi.aptcode"),
                        rset.getString("hi.aptname"),
                        rset.getString("dc.sidoname"),
                        rset.getString("dc.gugunname"),
                        rset.getString("dc.dongname"),
                        rset.getInt("hi.buildyear"),
                        rset.getString("hi.jibun"));
                result.add(hi);
            }
        }
        // 동작의 성공 여부와 상관 없이 사용한 리소스들을 반환한다. 이때 얻은 순의 역순으로 반환해주자.
        finally {
            util.close(rset, pstmt, con);
        }
        return result;
	}
	@Override
	public houseinfo getInfo(String aptCode) throws SQLException {
		String sql = "select hi.aptCode, hi.aptName, dc.sidoName, dc.gugunName, dc.dongName, hi.buildYear, hi.jibun \n"
				+ "from houseinfo hi, dongcode dc \n"
				+ "where hi.dongCode = dc.dongCode and hi.aptcode = ? \n"
				+ "order by 1";
		
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        
        houseinfo hinfo = null;
        
        try {
            // DBUtil을 통해 Connection 객체를 가져온다.
            con = util.getConnection();
            // Connection을 통해 PreparedStatement를 가져온다.
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, aptCode);
            // PreparedStatement를 실행한다. select 계열이므로 executeQuery를 사용한다. 결과로 ResultSet을 얻는다.
            rset = pstmt.executeQuery();
            if (rset.next()) {
                // ResultSet을 통해서 조회 결과를 넘겨받는다.
                // 조회 결과를 이용해 Book 객체를 생성하고 담는다.
                hinfo = new houseinfo(rset.getString("hi.aptcode"),
                        rset.getString("hi.aptname"),
                        rset.getString("dc.sidoname"),
                        rset.getString("dc.gugunname"),
                        rset.getString("dc.dongname"),
                        rset.getInt("hi.buildyear"),
                        rset.getString("hi.jibun"));
            }
        }finally {
        	util.close(rset, pstmt, con);
        }
		return hinfo;
	}
	@Override
	public List<housedeal> dealinfo(String aptCode) throws SQLException {
		String sql = "select aptCode, dealAmount, dealYear, dealMonth, dealDay, area, floor \n"
				+ "from housedeal \n"
				+ "where aptCode is not null and aptCode = \"" + aptCode +  "\" \n"
				+ "order by 1";
		
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        
        List<housedeal> result = new ArrayList<>();
        try {
            // DBUtil을 통해 Connection 객체를 가져온다.
            con = util.getConnection();
            // Connection을 통해 PreparedStatement를 가져온다.
            pstmt = con.prepareStatement(sql);
            // PreparedStatement를 실행한다. select 계열이므로 executeQuery를 사용한다. 결과로 ResultSet을 얻는다.
            rset = pstmt.executeQuery();
            // 여러 개의 자료가 반환될 수 있으므로 while 문장을 이용한다.
            while (rset.next()) {
                // ResultSet을 통해서 조회 결과를 넘겨받는다.
                // 조회 결과를 이용해 Book 객체를 생성하고 list에 담는다.
                housedeal hd = new housedeal(rset.getString("aptcode"),
                        rset.getString("dealamount"),
                        rset.getString("dealyear"),
                        rset.getString("dealmonth"),
                        rset.getString("dealday"),
                        rset.getString("area"),
                        rset.getString("floor"));
                result.add(hd);
            }
        }
        // 동작의 성공 여부와 상관 없이 사용한 리소스들을 반환한다. 이때 얻은 순의 역순으로 반환해주자.
        finally {
            util.close(rset, pstmt, con);
        }
        return result;
	}
	@Override
	public List<houseinfo> search(String aptName, String dongName) throws SQLException {
		System.out.println(aptName);
		System.out.println(dongName);
		String sql = "select hi.aptCode, hi.aptName, dc.sidoName, dc.gugunName, dc.dongName, hi.buildYear, hi.jibun \n"
				+ "from houseinfo hi, dongcode dc \n";
		String plus = "where hi.dongCode = dc.dongCode ";
		String end = "order by 1";
		if(aptName.equals("전체")) {	//아파트명 전체
			if(dongName.equals("전체")) { //동 전체
				plus += "\n";
				sql += plus + end;
			} else {	//아파트 전체, 동 별도
				plus += "and dc.dongName like \"%" + dongName + "%\" \n";
				sql += plus + end;
			}
		} else {	//아파트 별도
			if(dongName.equals("전체")) { //동 전체
				plus += "and hi.aptName like \"%" + aptName + "%\" \n";
				System.out.println(plus);
				sql += plus + end;
			} else {	//아파트 별도, 동 별도
				plus += "and dc.dongName like \"%" + dongName + "%\" and hi.aptName like \"%" + aptName + "%\" \n";
				sql += plus + end;
			}
		}
		
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        
        List<houseinfo> result = new ArrayList<>();
        try {
            // DBUtil을 통해 Connection 객체를 가져온다.
            con = util.getConnection();
            // Connection을 통해 PreparedStatement를 가져온다.
            pstmt = con.prepareStatement(sql);
            // PreparedStatement를 실행한다. select 계열이므로 executeQuery를 사용한다. 결과로 ResultSet을 얻는다.
            rset = pstmt.executeQuery();
            // 여러 개의 자료가 반환될 수 있으므로 while 문장을 이용한다.
            while (rset.next()) {
                // ResultSet을 통해서 조회 결과를 넘겨받는다.
                // 조회 결과를 이용해 Book 객체를 생성하고 list에 담는다.
                houseinfo hi = new houseinfo(rset.getString("hi.aptcode"),
                        rset.getString("hi.aptname"),
                        rset.getString("dc.sidoname"),
                        rset.getString("dc.gugunname"),
                        rset.getString("dc.dongname"),
                        rset.getInt("hi.buildyear"),
                        rset.getString("hi.jibun"));
                result.add(hi);
            }
        }
        // 동작의 성공 여부와 상관 없이 사용한 리소스들을 반환한다. 이때 얻은 순의 역순으로 반환해주자.
        finally {
            util.close(rset, pstmt, con);
        }
        return result;
	}
	
	
	
}
