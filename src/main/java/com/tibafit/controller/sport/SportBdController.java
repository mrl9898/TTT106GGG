package com.tibafit.controller.sport;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/dMain")
public class SportBdController {

	/*
	 * Test
	 */
	@GetMapping("/bd/sport")
	public String sportMain(ModelMap model) {
		return "redirect:/adminlte/sport/sport.html";
//		return "admin/sport/simple";
	}
	
	/*
	 * 
	 */
	@GetMapping("/bd/customSport")
	public String customSportMain(ModelMap model) {
		return "redirect:/adminlte/customSport/customSport.html";
	}
	
	/*
	 * 
	 */
	@GetMapping("/bd/sportType")
	public String sportTypeMain(ModelMap model) {
		return "redirect:/adminlte/sportType/sportType.html";
	}
	
	/*
	 * 
	 */
	@GetMapping("/bd/sportTypeItem")
	public String sportTypeItemMain(ModelMap model) {
		return "redirect:/adminlte/sportTypeItem/sportTypeItem.html";
	}
	
	/*
	 * 
	 */
	@GetMapping("/bd/workoutPlan")
	public String workoutPlanMain(ModelMap model) {
		return "redirect:/adminlte/workoutPlan/workoutPlan.html";
	}
	
	/*
	 * 
	 */
	@GetMapping("/bd/workoutPlanRecord")
	public String workoutPlanRecordMain(ModelMap model) {
		return "redirect:/adminlte/workoutPlanRecord/workoutPlanRecord.html";
	}
	
	/*
	 * 
	 */
	@GetMapping("/bd/testUploadFile")
	public String testUploadFileMain(ModelMap model) {
		return "redirect:/adminlte/sport/testUploadFile.html";
	}
}
