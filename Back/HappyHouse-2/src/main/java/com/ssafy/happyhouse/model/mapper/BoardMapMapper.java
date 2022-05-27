package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;


import com.ssafy.happyhouse.model.BoardDto;

@Mapper
public interface BoardMapMapper {
	BoardDto selectBoard(int no) throws SQLException;
	List<BoardDto> selectList() throws SQLException;
	int writeBoard(BoardDto board) throws SQLException;
	int modifyBoard(int no) throws SQLException;
	int deleteBoard(int no) throws SQLException;
	
}
