package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.happyhouse.model.HouseDealDto;
import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.LikeDto;
import com.ssafy.happyhouse.model.UserDto;
import com.ssafy.happyhouse.model.service.LikeService;
import com.ssafy.happyhouse.model.service.UserService;
import com.ssafy.happyhouse.model.service.jwtServiceImpl;
import com.ssafy.happyhouse.model.service.jwtService;

@Controller
@RequestMapping("/like")
@CrossOrigin("*")
public class LikeController {
	
	private static final Logger logger = LoggerFactory.getLogger(LikeController.class);
	
	@Autowired
	private LikeService likeService;
	
	
	@GetMapping("/list/{user_id}")
	@ResponseBody
	public ResponseEntity<List<HouseInfoDto>> getList(@PathVariable("user_id") String user_id,
			HttpServletRequest request) throws Exception {
		logger.info("getList 요청");
		logger.info("매개변수 : {}", user_id);
		return new ResponseEntity<List<HouseInfoDto>>(likeService.getApts(user_id), HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping("/{apt_code}/{user_id}")
	public ResponseEntity<LikeDto> getLike(@PathVariable("apt_code") int apt_code, @PathVariable("user_id") String user_id,
			HttpServletRequest request) throws Exception {
		logger.info("getLike 요청");
		logger.info("매개변수 : {}", apt_code);
		LikeDto like = new LikeDto();
		like.setApt_code(apt_code);
		like.setUser_id(user_id);
		LikeDto likeDto = likeService.getLike(like);
		logger.info("조회 결과  : {}", likeDto);
		return new ResponseEntity<LikeDto>(likeDto, HttpStatus.OK);
	}
	


	
	@PostMapping
	public ResponseEntity<Map<String, Object>> regist(@RequestBody LikeDto likeDto) {
		logger.info("좋아요 요청");
		logger.info("매개변수 : {}", likeDto);

		HttpStatus status = null;

		Map<String, Object> resultMap = new HashMap<>();
		try {
			likeService.register(likeDto);
			logger.debug("좋아요 등록 : {}", likeDto);
			resultMap.put("message", "success");
			status = HttpStatus.ACCEPTED;
			
		} catch (Exception e) {
			logger.error("등록 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	

	@DeleteMapping
	@ResponseBody
	public ResponseEntity<Map<String, Object>> delete(@RequestParam(value = "no") int no) throws Exception {
		logger.debug("매개변수 map : {}", no);
		HttpStatus status = null;
		Map<String, Object> resultMap = new HashMap<>();
		try {
			likeService.delete(no);
			logger.debug("좋아요 취소 : {}", no);
			resultMap.put("message", "success");
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			logger.error("취소 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ResponseBody
	@DeleteMapping("/{apt_code}/{user_id}")
	public ResponseEntity<Map<String, Object>> deleteByAptCode(@PathVariable("apt_code") int apt_code, @PathVariable("user_id") String user_id,
			HttpServletRequest request) throws Exception {
		logger.info("deleteByAptCode 요청");
		logger.info("매개변수 : {}", apt_code, user_id);
		LikeDto like = new LikeDto();
		like.setApt_code(apt_code);
		like.setUser_id(user_id);
		HttpStatus status = null;
		Map<String, Object> resultMap = new HashMap<>();
		try {
			likeService.deleteByAptCode(like);
			logger.debug("좋아요 취소 : {}", like);
			resultMap.put("message", "success");
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			logger.error("취소 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	

}
