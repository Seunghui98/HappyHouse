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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.happyhouse.model.NoticeDto;
import com.ssafy.happyhouse.model.service.NoticeService;

@Controller
@RequestMapping("/notice")
@CrossOrigin("*")
public class NoticeController {
	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	@Autowired
	private NoticeService noticeService;
	
	@GetMapping
	public ResponseEntity<?> getList() throws SQLException{
		List<NoticeDto> list = noticeService.selectList();
		System.out.print(list);
		if (list != null) {
			return new ResponseEntity<List<NoticeDto>>(list, HttpStatus.OK);
		} else
			return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/{no}")
	public ResponseEntity<?> selectNotice(@PathVariable int no) throws SQLException{
		NoticeDto notice = noticeService.selectNotice(no);
		if(notice != null) {
			return new ResponseEntity<NoticeDto>(notice,HttpStatus.OK);
		}else
			return new ResponseEntity<String>("fail",HttpStatus.NO_CONTENT);
	}
	
	@ResponseBody
	@PostMapping("/notice")
	public ResponseEntity<?> writeNotice(@RequestBody NoticeDto notice) throws SQLException{
		logger.debug("writeNotice - 호출");
		int res = noticeService.writeNotice(notice);
		if(res > 0) {
			return new ResponseEntity<String>("success",HttpStatus.OK);
		}else
			return new ResponseEntity<String>("fail",HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/notice")
	public ResponseEntity<?> modifyNotice(@RequestBody NoticeDto notice) throws SQLException{
		int res = noticeService.modifyNotice(notice);
		if(res > 0) {
			return new ResponseEntity<String>("success",HttpStatus.OK);
		}else
			return new ResponseEntity<String>("fail",HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/notice/{no}")
	public ResponseEntity<?> deleteNotice(@PathVariable int no) throws SQLException{
		int res = noticeService.deleteNotice(no);
		if(res > 0) {
			return new ResponseEntity<String>("success",HttpStatus.OK);
		}else
			return new ResponseEntity<String>("fail",HttpStatus.NO_CONTENT);
	}
}
