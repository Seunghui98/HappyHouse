package com.ssafy.happyhouse.controller;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.happyhouse.model.BoardDto;
import com.ssafy.happyhouse.model.service.BoardService;

@Controller
@RequestMapping("/board")
@CrossOrigin("*")
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	@Autowired
	private BoardService boardService;
	
	@GetMapping
	public ResponseEntity<?> getList() throws SQLException{
		List<BoardDto> list = boardService.selectList();
		System.out.println("list :" + list);
		if (list != null) {
			return new ResponseEntity<List<BoardDto>>(list, HttpStatus.OK);
		} else
			return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/{no}")
	public ResponseEntity<?> selectBoard(@PathVariable int no) throws SQLException{
		BoardDto board = boardService.selectBoard(no);
		if(board != null) {
			return new ResponseEntity<BoardDto>(board,HttpStatus.OK);
		}else
			return new ResponseEntity<String>("fail",HttpStatus.NO_CONTENT);
	}
	
	@PostMapping("/board")
	public ResponseEntity<?> writeBoard(@RequestBody BoardDto board) throws SQLException{
		logger.debug("writeBoard - 호출");
		int res = boardService.writeBoard(board);
		if(res > 0) {
			return new ResponseEntity<String>("success",HttpStatus.OK);
		}else
			return new ResponseEntity<String>("fail",HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/board/{no}")
	public ResponseEntity<?> modifyNotice(@PathVariable int no) throws SQLException{
		int res = boardService.modifyBoard(no);
		if(res > 0) {
			return new ResponseEntity<String>("success",HttpStatus.OK);
		}else
			return new ResponseEntity<String>("fail",HttpStatus.NO_CONTENT);
	}
}

