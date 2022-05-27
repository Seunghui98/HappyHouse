package com.ssafy.happyhouse.model.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.HouseDealDto;
import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.mapper.HouseMapMapper;

@Service
public class HouseMapServiceImpl implements HouseMapService {
	
	@Autowired
	private HouseMapMapper houseMapMapper;

	@Override
	public List<SidoGugunCodeDto> getSido() throws Exception {
		return houseMapMapper.getSido();
	}

	@Override
	public List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception {
		return houseMapMapper.getGugunInSido(sido);
	}

	@Override
	public List<HouseInfoDto> getDongInGugun(String gugun) throws Exception {
		return houseMapMapper.getDongInGugun(gugun);
	}

	@Override
	public List<HouseInfoDto> getAptInDong(String dong) throws Exception {
		return houseMapMapper.getAptInDong(dong);
	}
	@Override
	public String[] geoCoding(String location) throws Exception {
		String[] data = new String[2];
		 try {
			 	 
	            String addr = "https://dapi.kakao.com/v2/local/search/address.json";
	 
	            String apiKey = "KakaoAK "+"2c2fb6fab068d5f583ca5702949921d2";
	 
	            location = URLEncoder.encode(location, "UTF-8");
	 
	            String query = "query=" + location;
	            
	            StringBuffer stringBuffer = new StringBuffer();
	            stringBuffer.append(addr);
	            stringBuffer.append("?");
	            stringBuffer.append(query);
	            
	            System.out.println("stringBuffer.toString() "+ stringBuffer.toString());
	            
	            URL url = new URL(stringBuffer.toString());
	            
	            URLConnection conn = url.openConnection();
	            
	            conn.setRequestProperty("Authorization", apiKey);
	            
	            BufferedReader rd = null;
	            
	            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
	            StringBuffer docJson = new StringBuffer();
	            
	            String line;
	            
	            while((line=rd.readLine())!=null){
	                docJson.append(line);
	            }
	            
	            if(0<docJson.toString().length()){
	                System.out.println("docJson    :"+docJson.toString());
	                
	            }
	            
	            rd.close();
	            
	            JSONObject jsonObject = new JSONObject(docJson.toString());
	            
	            JSONArray jsonArray= (JSONArray) jsonObject.get("documents");
	            
	            JSONObject tempObj = (JSONObject) jsonArray.get(0);
	                
	            System.out.println("latitude : " + tempObj.getDouble("y"));
	            System.out.println("longitude : " + tempObj.getDouble("x"));
	            data[0] = String.valueOf(tempObj.getDouble("y"));
	            data[1] = String.valueOf(tempObj.getDouble("x"));
	            return data;
	            
	        }catch(Exception e) {
	            e.printStackTrace();
	        }
		 return null;

	}

	@Override
	public double distance(double lat1, double lon1, double lat2, double lon2) {
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		
		dist = dist * 1.609344;
		
		return dist;
	}
	
	// decimal degrees -> radians
	private double deg2rad(double deg) {
		return (deg * Math.PI) / 180.0;
		
	}
	
	// radians -> decimal degrees
	private double rad2deg(double rad) {
		return (rad * 180) / Math.PI;
		
	}

	@Override
	public List<HouseInfoDto> getApts() throws Exception {
		// TODO Auto-generated method stub
		return houseMapMapper.getApts();
	}

	@Override
	public List<HouseInfoDto> getAptInDongName(String dongName) throws Exception {
		// TODO Auto-generated method stub
		return houseMapMapper.getAptInDongName(dongName);
	}

	@Override
	public List<HouseDealDto> getDeals(int aptCode) throws Exception {
		// TODO Auto-generated method stub
		return houseMapMapper.getDeals(aptCode);
	}

}
