package com.ssafy.happyhouse.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ssafy.happyhouse.model.HouseDealDto;
import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.service.HouseMapService;
import com.ssafy.happyhouse.model.service.UserService;

@Controller
@RequestMapping("/map")
@CrossOrigin("*")
public class HouseMapController {
	
	private final Logger logger = LoggerFactory.getLogger(HouseMapController.class);

	@Autowired
	private HouseMapService houseMapService;
	private UserService userService;
	
	@ResponseBody
	@GetMapping("/sido")
	public ResponseEntity<List<SidoGugunCodeDto>> sido() throws Exception {
		logger.debug("sido : {}", houseMapService.getSido());
		System.out.println("안녕하세요");
		return new ResponseEntity<List<SidoGugunCodeDto>>(houseMapService.getSido(), HttpStatus.OK);
	}
	@ResponseBody
	@GetMapping("/gugun")
	public ResponseEntity<List<SidoGugunCodeDto>> gugun(@RequestParam("sido") String sido) throws Exception {
		return new ResponseEntity<List<SidoGugunCodeDto>>(houseMapService.getGugunInSido(sido), HttpStatus.OK);
	}
	@ResponseBody
	@GetMapping("/dong")
	public ResponseEntity<List<HouseInfoDto>> dong(@RequestParam("gugun") String gugun) throws Exception {
		return new ResponseEntity<List<HouseInfoDto>>(houseMapService.getDongInGugun(gugun), HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping("/deal")
	public ResponseEntity<List<HouseDealDto>> dong(@RequestParam("aptCode") int aptCode) throws Exception {
		return new ResponseEntity<List<HouseDealDto>>(houseMapService.getDeals(aptCode), HttpStatus.OK);
	}
	
	
//  건설연도순으로 정렬
//	@ResponseBody
//	@GetMapping("/apt")
//	public ResponseEntity<List<HouseInfoDto>> apt(@RequestParam("dong") String dong) throws Exception {
//		List<HouseInfoDto> list = new ArrayList<>();
//		list = houseMapService.getAptInDong(dong);
//		Collections.sort(list,new Comparator<HouseInfoDto>() {
//
//			public int compare(HouseInfoDto o1, HouseInfoDto o2) {
//				return o1.getBuildYear() - o2.getBuildYear();
//			}
//			
//		});
//		for(int i=0;i<list.size();i++) {
//			System.out.println(list.get(i).getBuildYear());
//		}
//		return new ResponseEntity<List<HouseInfoDto>>(list, HttpStatus.OK);
//	}
	
	// 현재 위치 기준으로 거리순 정렬
		@ResponseBody
		@GetMapping("/apt/dongName")
		public ResponseEntity<List<HouseInfoDto>> aptbydongName(@RequestParam("dongName") String dongName) throws Exception{
			return new ResponseEntity<List<HouseInfoDto>>(houseMapService.getAptInDongName(dongName), HttpStatus.OK);
		}
	
	
	// 현재 위치 기준으로 거리순 정렬
	@ResponseBody
	@GetMapping("/apt")
	public ResponseEntity<List<HouseInfoDto>> apt(@RequestParam("dong") String dong) throws Exception{
		List<HouseInfoDto> list = new ArrayList<>();
		double lat = 37.50315783678538;
		double lon = 127.02908787529876;
		list = houseMapService.getAptInDong(dong);
		Collections.sort(list,new Comparator<HouseInfoDto>() {
			public int compare(HouseInfoDto o1, HouseInfoDto o2) {
				double distA = 0;
				try {
					distA = houseMapService.distance(lat,lon,Double.parseDouble(o1.getLat()),Double.parseDouble(o1.getLng()));
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				double distB = 0;
				try {
					distB = houseMapService.distance(lat,lon,Double.parseDouble(o2.getLat()),Double.parseDouble(o2.getLng()));
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return (distA - distB > 0) ? 1 : -1;
			}
		});
		
		return new ResponseEntity<List<HouseInfoDto>>(list, HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping("/apts")
	public ResponseEntity<List<HouseInfoDto>> apts() throws Exception{
		return new ResponseEntity<List<HouseInfoDto>>(houseMapService.getApts(), HttpStatus.OK);
	}
	
}