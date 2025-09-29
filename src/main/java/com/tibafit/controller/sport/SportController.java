package com.tibafit.controller.sport;

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
import com.tibafit.dto.sport.SportRequestDTO;
import com.tibafit.dto.sport.SportResponseDTO;
import com.tibafit.model.sport.SportConverter;
import com.tibafit.model.sport.SportVO;
import com.tibafit.service.sport.SportService_Interface;

@RestController
@RequestMapping("/sport/api")
public class SportController {
	
	@Autowired
	SportService_Interface sportSvc;
	
	
    // 檢查名稱是否存在
	// used
    @PostMapping("/isExistSportName")
    public Boolean isExistSportName(@RequestBody String sportName) {
        Boolean isExist = sportSvc.isExistBySportName(sportName);
        return isExist;
    }
	
    
	/*
	 * Test
	 */
	@PostMapping("/getMultipleAll")
	@ResponseBody
	public List<SportResponseDTO> getMultipleAll() {
		List<SportVO> rawList = sportSvc.getSportAll();			

		return SportConverter.toDtoList(rawList);
	}
	
	
	/*
	 * Test
	 */
	@PostMapping("/insertMultiple")
	@ResponseBody
	public void sportInsertMultiple( @RequestBody Map<String, Object> map) {	
		ObjectMapper objectMapper = new ObjectMapper();
		
		List<SportRequestDTO> sports = objectMapper.convertValue(map.get("sports"), new TypeReference<List<SportRequestDTO>>() {});
		System.out.println("update接收到前端資料: " + sports);
		sportSvc.insertSportMultiple(sports);			
	}
	
	
	/*
	 * Test
	 */
	@PostMapping("/update")
	@ResponseBody
	public void sportUpdate( @RequestBody SportRequestDTO dto) {
		System.out.println("update接收到前端資料: " + dto);
		sportSvc.updateSport(dto);			
	}
	
	
	/*
	 * Test
	 */
	@PostMapping("/updateMultipleSportDataStatus")
	@ResponseBody
	public void updateMultipleSportDataStatus( @RequestBody Map<String, Object> map) {
		ObjectMapper objectMapper = new ObjectMapper();
		
		Integer status = (Integer) map.get("sportDataStatus");		

		List<Integer> sportIds = objectMapper.convertValue(map.get("sportIds"), new TypeReference<List<Integer>>() {});
		
		System.out.println("update接收到前端資料: " + sportIds);
					
		sportSvc.updateSportDataStatusByIds(status, sportIds);		
	}
	
	
	/*
	 * Test
	 */
	@PostMapping("/getByComplexCondition")
	@ResponseBody
	public List<SportResponseDTO> getByComplexCondition(@RequestBody Map<String, Object> map) {
	    ObjectMapper objectMapper = new ObjectMapper();

	    // 字串型別參數
	    String sportNameDescFuzzy = objectMapper.convertValue(map.get("sportNameDescFuzzy"), String.class);
	    String sportLevel        = objectMapper.convertValue(map.get("sportLevel"), String.class);
	    String createStartDate   = objectMapper.convertValue(map.get("createStartDate"), String.class);
	    String createEndDate     = objectMapper.convertValue(map.get("createEndDate"), String.class);
	    String updateStartDate   = objectMapper.convertValue(map.get("updateStartDate"), String.class);
	    String updateEndDate     = objectMapper.convertValue(map.get("updateEndDate"), String.class);

	    // List<Integer>
	    List<Integer> statuses = objectMapper.convertValue(map.get("statuses"), new TypeReference<List<Integer>>() {});


	    List<SportVO> voList = sportSvc.getByComplexCondition(
	        sportNameDescFuzzy, 
	        sportLevel, 
	        createStartDate, 
	        createEndDate, 
	        updateStartDate, 
	        updateEndDate, 
	        statuses
	    );

	     return SportConverter.toDtoList(voList);
	}
}
