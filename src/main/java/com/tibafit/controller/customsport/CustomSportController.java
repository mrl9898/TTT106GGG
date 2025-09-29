package com.tibafit.controller.customsport;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tibafit.dto.customsport.CustomSportRequestDTO;
import com.tibafit.dto.customsport.CustomSportResponseDTO;
import com.tibafit.model.customsport.CustomSportConverter;
import com.tibafit.model.customsport.CustomSportVO;
import com.tibafit.service.customsport.CustomSportService_interface;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customSport/api")
public class CustomSportController {

    @Autowired
    private CustomSportService_interface svc;
	
	
	/*
	 * 
	 */
	@PostMapping("/getMultipleBySportDataStatuses")
	@ResponseBody
	public List<CustomSportResponseDTO> getMultipleBySportDataStatuses(@Valid @RequestBody Map<String, List<Integer>> map) {	
		List<Integer> statuses = map.get("statuses");
		
		System.out.println("update接收到前端資料: " + statuses);
					
		List<CustomSportVO> rawList = svc.getSportByDataStatuses(statuses);		

		return CustomSportConverter.toDtoList(rawList);
	}
	

	/*
	 * 
	 */
	@PostMapping("/insertMultiple")
	@ResponseBody
	public void sportInsertMultiple(@Valid @RequestBody Map<String, Object> map) {
		ObjectMapper objectMapper = new ObjectMapper();
		List<CustomSportRequestDTO> sports = objectMapper.convertValue(map.get("sports"), new TypeReference<List<CustomSportRequestDTO>>() {});
		
		System.out.println("update接收到前端資料: " + sports);
		svc.insertSportMultiple(sports);			
	}
	
	
	/*
	 * 
	 */
	@PostMapping("/updateMultiple")
	@ResponseBody
	public void updateMultiple(@Valid @RequestBody Map<String, Object> map) {
		ObjectMapper objectMapper = new ObjectMapper();
		List<CustomSportRequestDTO> sports = objectMapper.convertValue(map.get("sports"), new TypeReference<List<CustomSportRequestDTO>>() {});
		
		System.out.println("update接收到前端資料: " + sports);
		svc.updateSportMultiple(sports);			
	}
	
	
	/*
	 * 
	 */
	@PostMapping("/updateMultipleSportDataStatus")
	@ResponseBody
	public void updateMultipleSportDataStatus(@Valid @RequestBody Map<String, Object> map) {
		ObjectMapper objectMapper = new ObjectMapper();
		
		Integer status = (Integer) map.get("sportDataStatus");		

		List<Integer> sportIds = objectMapper.convertValue(map.get("sportIds"), new TypeReference<List<Integer>>() {});
		
		System.out.println("update接收到前端資料: " + sportIds);
					
		svc.updateSportDataStatusByIds(status, sportIds);		

	}
}
