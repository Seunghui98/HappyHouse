package com.ssafy.happyhouse.controller;

import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.happyhouse.model.UserDto;
import com.ssafy.happyhouse.model.service.UserService;
import com.ssafy.happyhouse.model.service.jwtServiceImpl;
import com.ssafy.happyhouse.model.service.jwtService;

@Controller
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private jwtService jwtService;
	
	@GetMapping("/list")
	public String list() {
		return "list";
	}
	@GetMapping("/regist")
	public String register() {
		return "signup";
	}
	
	@PostMapping("/regist")
	public ModelAndView register(UserDto userDto) {
		ModelAndView mav = new ModelAndView();
		try {
			userService.registerUser(userDto);
			mav.addObject("msg", "회원가입을 성공했습니다.");
			mav.setViewName("login");
			return mav;
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("msg", "회원가입 중 에러가 발생했습니다.");
			mav.setViewName("error/error");
			return mav;
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/info/{userid}")
	public ResponseEntity<Map<String, Object>> getInfo(@PathVariable("userid") String userid,
			HttpServletRequest request) {
//		logger.debug("userid : {} ", userid);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		if (jwtService.checkToken(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				UserDto userDto = userService.getUser(userid);
				resultMap.put("userInfo", userDto);
				resultMap.put("message", "success");
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", "fail");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody UserDto userDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		logger.info("로그인 요청");
		logger.info("변수 : {}", userDto.getId());
		
		try {
			UserDto loginUser = userService.login(userDto);
			if (loginUser != null) {
				String accessToken = jwtService.createAccessToken("userid", loginUser.getId());// key, data
				String refreshToken = jwtService.createRefreshToken("userid", loginUser.getId());
				userService.saveRefreshToken(userDto.getId(), refreshToken);
				logger.debug("access토큰정보 : {}", accessToken);
				logger.debug("refresh 토큰정보 : {}", refreshToken);
				resultMap.put("access-token", accessToken);
				resultMap.put("refresh-token", refreshToken);
				resultMap.put("message", "success");
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", "fail");
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@PostMapping
	public ResponseEntity<Map<String, Object>> regist(@RequestBody UserDto userDto) {
		logger.info("회원가입 요청");
		logger.info("매개변수 : {}", userDto);

		HttpStatus status = null;

		Map<String, Object> resultMap = new HashMap<>();
		try {
			userService.registerUser(userDto);
			logger.debug("userInfo 등록 : {}", userDto);
			resultMap.put("message", "success");
			status = HttpStatus.ACCEPTED;
			
		} catch (Exception e) {
			logger.error("등록 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@PutMapping
	public ResponseEntity<Map<String, Object>> updateUserInfo(@RequestBody UserDto userDto) throws Exception {
		logger.debug("map : {}", userDto.getId());
		HttpStatus status = null;

		Map<String, Object> resultMap = new HashMap<>();
		try {
			userService.updateUser(userDto);
			logger.debug("userInfo 수정 : {}", userDto);
			resultMap.put("message", "success");
			status = HttpStatus.ACCEPTED;
			
		} catch (Exception e) {
			logger.error("수정 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@PutMapping("/password")
	public ResponseEntity<Map<String, Object>> updatePassword(@RequestBody UserDto userDto) throws Exception {
		logger.debug("map : {}", userDto.getId());
		HttpStatus status = null;

		Map<String, Object> resultMap = new HashMap<>();
		try {
			userService.updatePassword(userDto);
			logger.debug("password 수정 : {}", userDto);
			resultMap.put("message", "success");
			status = HttpStatus.ACCEPTED;
			
		} catch (Exception e) {
			logger.error("수정 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@GetMapping("/update")
	public String update() {
		return "update";
	}
	
	@GetMapping("/mypage")
	public String searchUser() {
		return "mypage";
	}
	
	@DeleteMapping
	public ResponseEntity<Map<String, Object>> updateUserInfo(@RequestParam(value = "id") String id) throws Exception {
		logger.debug("매개변수 map : {}", id);
		HttpStatus status = null;
		Map<String, Object> resultMap = new HashMap<>();
		try {
			userService.deleteUser(id);
			logger.debug("userInfo 탈퇴 : {}", id);
			resultMap.put("message", "success");
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	
	

}
