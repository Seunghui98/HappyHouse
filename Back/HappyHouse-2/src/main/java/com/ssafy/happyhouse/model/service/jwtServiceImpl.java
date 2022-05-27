package com.ssafy.happyhouse.model.service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Service
public class jwtServiceImpl implements jwtService{
	
	
	public static final Logger logger =LoggerFactory.getLogger(jwtServiceImpl.class);
	
	private static final String SALT = "ssafySecret";
	private static final int EXPIRE_MINUTES = 1;
	
	
	@Override
	public <T> String createRefreshToken(String key, T data) {
		return create (key,data,"refresh-token", EXPIRE_MINUTES*5);
	}
	
	@Override
	public <T> String createAccessToken(String key, T data) {
		return create(key,data,"access-token", EXPIRE_MINUTES);
	}
	
	
	@Override
	public <T> String create(String key, T data, String subject, long expir) {
		String jwt = Jwts.builder()
				.setHeaderParam("typ", "JWT")
				.setHeaderParam("regDate", System.currentTimeMillis())
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * expir))
				.setSubject(subject)
				.claim(key, data)
				.signWith(SignatureAlgorithm.HS256, this.generateKey())
				.compact();
		logger.debug("토큰 발생! : {}", jwt);
		return jwt;
	}

	private byte[] generateKey() {
		byte[] key = null;
		try {
			//byte 코드로 인코딩 해주기
			//캐릭터셋 인자로 안주면 사용자 플랫폼의 기본 인코딩 설정대로 인코딩
			key = SALT.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			if (logger.isInfoEnabled()) {
				e.printStackTrace();
			} else {
				logger.error("Making JWT Key Error ::: {}", e.getMessage());
			}
		}

		return key;
	}

	//	전달 받은 토큰이 제대로 생성된것인지 확인 하고 문제가 있다면Exception을 발생.
	@Override
	public boolean checkToken(String jwt) {
		try {
			//Json Web Signature? 서버에서 인증을 근거로 인증정보를 서버의 private key로 서명 한것을 토큰화 한것
			//setSigningKey : JWS 서명 검증을 위한  secretkey 셋팅
			//parseClaimsJws : 파싱하여 원본 jws 만들기
			Jws<Claims> claims = Jwts.parser().setSigningKey(this.generateKey()).parseClaimsJws(jwt);
			//Claims 는 Map의 구현체 형태
			logger.info("claims: {}", claims);
			return true;
		} catch (Exception e) {
				logger.error(e.getMessage());
			return false;
		}
	}

	@Override
	public Map<String, Object> get(String key) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		String jwt = request.getHeader("access-token");
		Jws<Claims> claims = null;
		try {
			claims = Jwts.parser().setSigningKey(SALT.getBytes("UTF-8")).parseClaimsJws(jwt);
		} catch (Exception e) {
				logger.error(e.getMessage());
		}
		Map<String, Object> value = claims.getBody();
		logger.info("value : {}", value);
		return value;
	}

	@Override
	public String getUserId() {
		return (String) this.get("user").get("userid");
	}
}
