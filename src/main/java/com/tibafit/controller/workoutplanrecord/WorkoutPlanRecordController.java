package com.tibafit.controller.workoutplanrecord;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tibafit.dto.workoutplanrecord.WorkoutPlanRecordRequestDTO;
import com.tibafit.dto.workoutplanrecord.WorkoutPlanRecordResponseDTO;
import com.tibafit.service.workoutplanrecord.workoutPlanRecordService_interface;

@RestController
@RequestMapping("/workoutPlanRecord/api")
public class WorkoutPlanRecordController {

	@Autowired
	private workoutPlanRecordService_interface workoutPlanRecordSvc;
	

	/**
	 * 批次新增紀錄
	 */
	@PostMapping("/insertMultiple")
	@ResponseBody
	public List<WorkoutPlanRecordResponseDTO> insertMultiple(@RequestBody List<WorkoutPlanRecordRequestDTO> dtos) {
		List<WorkoutPlanRecordResponseDTO> result = workoutPlanRecordSvc.insertWorkoutPlanRecordMultiple(dtos);
		return result;
	}

	
	/**
	 * 批次更新紀錄狀態
	 */
	@PostMapping("/updateWorkoutPlanRecordDataStatusByRecordIds")
	@ResponseBody
	public Integer updateWorkoutPlanRecordDataStatusByRecordIds(
			@RequestBody Map<String, Object> req) {
		@SuppressWarnings("unchecked")
		Integer dataStatus = (Integer) req.get("dataStatus");
		@SuppressWarnings("unchecked")
		List<Integer> workoutPlanRecordIds = (List<Integer>) req.get("workoutPlanRecordIds");
		Integer affectNum = workoutPlanRecordSvc
				.updateWorkoutPlanRecordDataStatusByRecordIds(dataStatus, workoutPlanRecordIds);
		return affectNum;
	}
}
