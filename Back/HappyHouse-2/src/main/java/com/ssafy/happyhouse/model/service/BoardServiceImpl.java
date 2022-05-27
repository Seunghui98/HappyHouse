package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.BoardDto;
import com.ssafy.happyhouse.model.mapper.BoardMapMapper;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardMapMapper boardmapper;
	
	@Override
	public BoardDto selectBoard(int no) throws SQLException {
		return boardmapper.selectBoard(no);
	}

	@Override
	public List<BoardDto> selectList() throws SQLException {
		return boardmapper.selectList();
	}

	@Override
	public int writeBoard(BoardDto board) throws SQLException {
		return boardmapper.writeBoard(board);
	}

	@Override
	public int modifyBoard(int no) throws SQLException {
		return boardmapper.modifyBoard(no);
	}

	@Override
	public int deleteBoard(int no) throws SQLException {
		return boardmapper.deleteBoard(no);
	}
	
	

}
