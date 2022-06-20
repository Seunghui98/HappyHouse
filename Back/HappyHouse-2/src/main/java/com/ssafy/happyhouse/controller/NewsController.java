package com.ssafy.happyhouse.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.net.HttpURLConnection;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;



import org.json.JSONArray;
import org.json.JSONObject;

import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.NewsDto;


@Controller
@RequestMapping("/news")
@CrossOrigin("*")
public class NewsController {
	private static final Logger logger = LoggerFactory.getLogger(NewsController.class);
	
	
	
	
	@GetMapping("/{keyword}")
	@ResponseBody
	public ResponseEntity<List<NewsDto>> getNews(@PathVariable("keyword") String keyword,
			HttpServletRequest request) throws Exception {
		logger.info("getNews 요청");
		logger.info("매개변수 : {}", keyword);
		// String clientId = "";
		// String clientSecret = "";
		List<NewsDto> response = new ArrayList<>();
		try {
			String text = URLEncoder.encode(keyword, "UTF-8");
			String apiURL = "https://openapi.naver.com/v1/search/news.json?query=" + text
					+"&display=20&start=1&sort=date";
			Map<String, String> requestHeaders = new HashMap<>();
	        requestHeaders.put("X-Naver-Client-Id", clientId);
	        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
	        String responseBody = get(apiURL,requestHeaders);
	        
	        responseBody.trim();
	        JSONObject jsnobject = new JSONObject(responseBody);
	        
	        JSONArray jsonArray = jsnobject.getJSONArray("items");
	        for (int i = 0; i < jsonArray.length(); i++) {
	            JSONObject jsonobj = jsonArray.getJSONObject(i);
	            response.add(new NewsDto(jsonobj.get("title").toString(), jsonobj.get("link").toString(), jsonobj.get("description").toString()));
	        }
	        return new ResponseEntity<List<NewsDto>>(response, HttpStatus.OK);
	        
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		return new ResponseEntity<List<NewsDto>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	private static String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }


            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 에러 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }


    private static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }


    private static String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);


        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();


            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }


            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }
	
	
}
