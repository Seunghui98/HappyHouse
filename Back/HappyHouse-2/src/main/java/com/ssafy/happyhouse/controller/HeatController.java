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

import com.ssafy.happyhouse.model.HeatDto;
import com.ssafy.happyhouse.model.HouseDealDto;
import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.LikeDto;
import com.ssafy.happyhouse.model.UserDto;
import com.ssafy.happyhouse.model.service.HeatService;
import com.ssafy.happyhouse.model.service.LikeService;
import com.ssafy.happyhouse.model.service.UserService;
import com.ssafy.happyhouse.model.service.jwtServiceImpl;
import com.ssafy.happyhouse.model.service.jwtService;

@Controller
@RequestMapping("/heat")
@CrossOrigin("*")
public class HeatController {
	
	private static final Logger logger = LoggerFactory.getLogger(HeatController.class);
	
	@Autowired
	private HeatService heatService;
	
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<List<HeatDto>> getList(HttpServletRequest request) throws Exception {
		logger.info("getHeatList 요청");
		return new ResponseEntity<List<HeatDto>>(heatService.getList(), HttpStatus.OK);
	}
	
	@PostMapping("/{dongName}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> regist(@PathVariable("dongName") String dongName) {
		logger.info("heat 등록 요청");
		logger.info("매개변수 : {}", dongName);

		HttpStatus status = null;

		Map<String, Object> resultMap = new HashMap<>();
		try {
			heatService.register(dongName);
			resultMap.put("message", "success");
			status = HttpStatus.ACCEPTED;
			
		} catch (Exception e) {
			logger.error("heat 등록 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	


	
	

}
