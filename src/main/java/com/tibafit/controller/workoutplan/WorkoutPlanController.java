package com.tibafit.controller.workoutplan;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tibafit.dto.workoutplan.WorkoutPlanRequestDTO;
import com.tibafit.dto.workoutplan.WorkoutPlanRequestDtoList;
import com.tibafit.dto.workoutplan.WorkoutPlanResponseDTO;
import com.tibafit.service.workoutplan.workoutPlanService_interface;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/workoutPlan/api")
public class WorkoutPlanController {

	@Autowired
	private workoutPlanService_interface workoutPlanSvc;
	
	
	@PostMapping("/insertMultiple")
	@ResponseBody
	public void insertMultiple(@Valid @RequestBody WorkoutPlanRequestDtoList list) {
		List<WorkoutPlanRequestDTO> dtoList = list.getList();
		workoutPlanSvc.insertWorkoutPlanMultiple(dtoList);
	}

	
	@PostMapping("/updateMultiple")
	@ResponseBody
	public void updateMultiple(@Valid @RequestBody WorkoutPlanRequestDtoList list) {
		List<WorkoutPlanRequestDTO> dtoList = list.getList();
		workoutPlanSvc.updateWorkoutPlanMultiple(dtoList);
	}

	
	/**
	 * 更新資料狀態
	 */
	@PostMapping("/updateMultipleWorkoutPlanDataStatus")
	@ResponseBody
	public Integer updateStatus(@RequestBody Map<String, Object> req) {
		Integer status = (Integer) req.get("dataStatus");
		@SuppressWarnings("unchecked")
		List<Integer> ids = (List<Integer>) req.get("workoutPlanIds");

		Integer affectNum = workoutPlanSvc.updateWorkoutPlanDataStatusByIds(status, ids);
		return affectNum;
	}
	

	/**
	 * 依日期區間查詢
	 */
	@PostMapping("/getMultipleByUserIdAndDateRange")
	@ResponseBody
	public List<WorkoutPlanResponseDTO> getMultipleByUserIdAndDateRange(@RequestBody Map<String, Object> req) {
		Integer userId = (Integer) req.get("userId");
		LocalDate startDate = LocalDate.parse(req.get("startDate").toString());
		LocalDate endDate = LocalDate.parse(req.get("endDate").toString());
		@SuppressWarnings("unchecked")
		List<Integer> statuses = (List<Integer>) req.get("dataStatuses");

		List<WorkoutPlanResponseDTO> dtos = workoutPlanSvc.getWorkoutPlanByDateRange(userId, startDate, endDate,
				statuses);
		return dtos;
	}

	
	/**
	 * 依日期查詢
	 */
	@PostMapping("/getMultipleByUserIdAndWorkoutPlanDate")
	@ResponseBody
	public List<WorkoutPlanResponseDTO> getMultipleByUserIdAndWorkoutPlanDate(@RequestBody Map<String, Object> req) {
		Integer userId = (Integer) req.get("userId");
		LocalDate workoutPlanDate = LocalDate.parse(req.get("workoutPlanDate").toString());
		@SuppressWarnings("unchecked")
		List<Integer> statuses = (List<Integer>) req.get("dataStatuses");

		List<WorkoutPlanResponseDTO> dtos = workoutPlanSvc.getWorkoutPlanByDate(userId, workoutPlanDate, statuses);
		return dtos;
	}
}
