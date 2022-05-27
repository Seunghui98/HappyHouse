package com.ssafy.happyhouse.model.service;

import java.util.List;
import com.ssafy.happyhouse.model.HeatDto;

public interface HeatService {
	List<HeatDto> getList() throws Exception;
	void register(String dongName) throws Exception;
}
