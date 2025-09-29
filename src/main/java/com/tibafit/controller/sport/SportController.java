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
//		SportVO sportVO = new SportVO();
//		model.addAttribute("empVO", empVO);
		
//		return "redirect:/adminlte/sport/simple.html";
		
//		Gson gson = new Gson();
//		return gson.toJson(sportVO);
		
//		System.out.println("get接收到前端資料: " + body);
		List<SportVO> rawList = sportSvc.getSportAll();			

		return SportConverter.toDtoList(rawList);
	}
	
	/*
	 * Test
	 */
	@PostMapping("/getMultipleBySportDataStatuses")
	@ResponseBody
	public List<SportResponseDTO> getMultipleBySportDataStatuses( @RequestBody Map<String, List<Integer>> map) {
//		SportVO sportVO = new SportVO();
//		model.addAttribute("empVO", empVO);
		
//		return "redirect:/adminlte/sport/simple.html";
		
//		Gson gson = new Gson();
//		return gson.toJson(sportVO);
		
		List<Integer> statuses = map.get("statuses");
		
		System.out.println("update接收到前端資料: " + statuses);
					
		List<SportVO> rawList = sportSvc.getSportByDataStatuses(statuses);		

		return SportConverter.toDtoList(rawList);
	}
	
	/*
	 * Test
	 */
	@PostMapping("/getSingle")
	@ResponseBody
	public SportResponseDTO getSingle( @RequestBody Map<String, Integer> map) {
//		SportVO sportVO = new SportVO();
//		model.addAttribute("empVO", empVO);
		
//		return "redirect:/adminlte/sport/simple.html";
		
//		Gson gson = new Gson();
//		return gson.toJson(sportVO);
		
//		System.out.println("get接收到前端資料: " + body);
		
		Integer sportId = map.get("sportId");
		
		SportVO rawSport = sportSvc.getSportByPrimaryKey(sportId);			

		return SportConverter.toDTO(rawSport);
	}
	
	/*
	 * Test
	 */
	@PostMapping("/insert")
	@ResponseBody
	public void insert( @RequestBody SportRequestDTO dto) {
//		SportVO sportVO = new SportVO();
//		model.addAttribute("empVO", empVO);
		
//		return "redirect:/adminlte/sport/simple.html";
		
//		Gson gson = new Gson();
//		return gson.toJson(sportVO);
		
		System.out.println("insert接收到前端資料: " + dto);
		sportSvc.insertSport(dto);			

//		return SportConverter.toDTOList(rawList);
	}
	/*
	 * Test
	 */
	@PostMapping("/insertMultiple")
	@ResponseBody
	public void sportInsertMultiple( @RequestBody Map<String, Object> map) {
//		SportVO sportVO = new SportVO();
//		model.addAttribute("empVO", empVO);
		
//		return "redirect:/adminlte/sport/simple.html";
		
//		Gson gson = new Gson();
//		return gson.toJson(sportVO);
		
		ObjectMapper objectMapper = new ObjectMapper();
		
//		List<Integer> statuses = map.get("insertList");
		List<SportRequestDTO> sports = objectMapper.convertValue(map.get("sports"), new TypeReference<List<SportRequestDTO>>() {});
		System.out.println("update接收到前端資料: " + sports);
		sportSvc.insertSportMultiple(sports);			

//		return SportConverter.toDTOList(rawList);
	}
	
	/*
	 * Test
	 */
	@PostMapping("/update")
	@ResponseBody
	public void sportUpdate( @RequestBody SportRequestDTO dto) {
//		SportVO sportVO = new SportVO();
//		model.addAttribute("empVO", empVO);
		
//		return "redirect:/adminlte/sport/simple.html";
		
//		Gson gson = new Gson();
//		return gson.toJson(sportVO);
		
		System.out.println("update接收到前端資料: " + dto);
		sportSvc.updateSport(dto);			

//		return SportConverter.toDTOList(rawList);
	}
	
	/*
	 * Test
	 */
	@PostMapping("/updateMultiple")
	@ResponseBody
	public void updateMultiple( @RequestBody Map<String, Object> map) {
//		SportVO sportVO = new SportVO();
//		model.addAttribute("empVO", empVO);
		
//		return "redirect:/adminlte/sport/simple.html";
		
//		Gson gson = new Gson();
//		return gson.toJson(sportVO);
		
		ObjectMapper objectMapper = new ObjectMapper();
		List<SportRequestDTO> sports = objectMapper.convertValue(map.get("sports"), new TypeReference<List<SportRequestDTO>>() {});
		
		System.out.println("update接收到前端資料: " + sports);
		sportSvc.updateSportMultiple(sports);			

//		return SportConverter.toDTOList(rawList);
	}
	
	
	/*
	 * Test
	 */
	@PostMapping("/updateMultipleSportDataStatus")
	@ResponseBody
	public void updateMultipleSportDataStatus( @RequestBody Map<String, Object> map) {
//		SportVO sportVO = new SportVO();
//		model.addAttribute("empVO", empVO);
		
//		return "redirect:/adminlte/sport/simple.html";
		
//		Gson gson = new Gson();
//		return gson.toJson(sportVO);
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		Integer status = (Integer) map.get("sportDataStatus");		
//		List<Integer> sportIds = (List<Integer>) map.get("sportIds");
		List<Integer> sportIds = objectMapper.convertValue(map.get("sportIds"), new TypeReference<List<Integer>>() {});
		
		System.out.println("update接收到前端資料: " + sportIds);
					
		sportSvc.updateSportDataStatusByIds(status, sportIds);		

//		return SportConverter.toDtoList(rawList);
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
