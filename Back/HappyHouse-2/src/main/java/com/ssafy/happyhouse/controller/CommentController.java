package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.CommentDto;
import com.ssafy.happyhouse.model.LikeDto;
import com.ssafy.happyhouse.model.UserDto;
import com.ssafy.happyhouse.model.service.CommentService;

@Controller
@RequestMapping("/comment")
@CrossOrigin("*")
public class CommentController {
	
	private static final Logger logger = LoggerFactory.getLogger(CommentController.class);
	
	@Autowired
	
	private CommentService commentService;
	
	
	@ResponseBody
	@GetMapping("/{Board_no}")
	public ResponseEntity<?> commentList(@PathVariable int Board_no){
		logger.debug("commentList 호출");
		return new ResponseEntity<List<CommentDto>>(commentService.list(Board_no), HttpStatus.OK);
	}

	@ResponseBody
	@DeleteMapping("/{comment_no}")
	public ResponseEntity<Map<String, Object>> delete(@PathVariable("comment_no") int comment_no,
			HttpServletRequest request) throws Exception {
		logger.info("delete comment 요청");
		logger.info("매개변수 : {}", comment_no);
		LikeDto like = new LikeDto();
		HttpStatus status = null;
		Map<String, Object> resultMap = new HashMap<>();
		try {
			commentService.delete(comment_no);
			logger.debug("댓글 삭제 : {}", comment_no);
			resultMap.put("message", "success");
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			logger.error("댓글 삭제 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@PostMapping("/{Board_no}")
	public ResponseEntity<Map<String, Object>> regist(@PathVariable("Board_no") int Board_no,@RequestBody CommentDto commentDto) {
		logger.info("댓글작성 요청");
		commentDto.setBoard_no(Board_no);
		logger.info("매개변수 : {}", commentDto);

		HttpStatus status = null;

		Map<String, Object> resultMap = new HashMap<>();
		try {
			commentService.create(commentDto);
			logger.debug("댓글 등록 : {}", commentDto);
			resultMap.put("message", "success");
			status = HttpStatus.ACCEPTED;
			
		} catch (Exception e) {
			logger.error("댓글 등록 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@PutMapping
	public ResponseEntity<Map<String, Object>> update(@RequestBody CommentDto commentDto) throws Exception {
		logger.debug("댓글 수정요청 : {}", commentDto);
		HttpStatus status = null;

		Map<String, Object> resultMap = new HashMap<>();
		try {
			commentService.modify(commentDto);
			logger.debug("댓글 수정 성공 : {}", commentDto);
			resultMap.put("message", "success");
			status = HttpStatus.ACCEPTED;
			
		} catch (Exception e) {
			logger.error("수정 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	
	


}
