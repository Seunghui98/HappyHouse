package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.HeatDto;
import com.ssafy.happyhouse.model.mapper.HeatMapper;

@Service
public class HeatServiceImpl implements HeatService{
	
	@Autowired
	private HeatMapper heatMapper;
	
	@Override
	public List<HeatDto> getList() throws Exception {
		return heatMapper.getList();
	}

	@Override
	public void register(String dongName) throws Exception {
		heatMapper.register(dongName);
	}

}
