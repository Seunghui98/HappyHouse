package com.ssafy.happyhouse.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.dto.housedeal;
import com.ssafy.happyhouse.model.dto.houseinfo;

public interface houseDao {
	List<houseinfo> select() throws SQLException;
	houseinfo getInfo(String aptCode) throws SQLException;
	List<housedeal> dealinfo(String aptCode) throws SQLException;
	List<houseinfo> search(String aptName, String dongName) throws SQLException;
}
