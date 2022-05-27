package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.HouseDealDto;
import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;

public interface HouseMapService {

	List<SidoGugunCodeDto> getSido() throws Exception;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;
	List<HouseInfoDto> getDongInGugun(String gugun) throws Exception;
	List<HouseInfoDto> getAptInDong(String dong) throws Exception;
	List<HouseInfoDto> getAptInDongName(String dongName) throws Exception;
	List<HouseInfoDto> getApts() throws Exception;
	List<HouseDealDto> getDeals(int aptCode) throws Exception;
	String[] geoCoding(String location) throws Exception;
	double distance(double lat1, double lon1, double lat2, double lon2) throws Exception;

}
