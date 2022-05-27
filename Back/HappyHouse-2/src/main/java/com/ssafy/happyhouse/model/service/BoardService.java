package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.BoardDto;

public interface BoardService {
	
	BoardDto selectBoard(int no) throws SQLException;
	List<BoardDto> selectList() throws SQLException;
	int writeBoard(BoardDto board) throws SQLException;
	int modifyBoard(int no) throws SQLException;
	int deleteBoard(int no) throws SQLException;
}
