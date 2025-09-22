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
	 * 查詢單筆紀錄
	 */
	@PostMapping("/getSingle")
	@ResponseBody
	public WorkoutPlanRecordResponseDTO getSingle(@RequestBody Map<String, Integer> req) {
		Integer id = req.get("workoutPlanRecordId");
		WorkoutPlanRecordResponseDTO dto = workoutPlanRecordSvc.getRecordByPrimaryKey(id);
		return dto;
	}

	/**
	 * 依計畫ID查詢紀錄
	 */
	@PostMapping("/getMultipleByWorkoutPlanId")
	@ResponseBody
	public List<WorkoutPlanRecordResponseDTO> getMultipleByWorkoutPlanId(@RequestBody Map<String, Integer> req) {
		Integer planId = req.get("workoutPlanId");
		List<WorkoutPlanRecordResponseDTO> dtos = workoutPlanRecordSvc.getRecordsByWorkoutPlanId(planId);
		return dtos;
	}

	/**
	 * 依計畫ID + 多種狀態查詢紀錄
	 */
	@PostMapping("/getMultipleByWorkoutPlanIdAndWorkouPlanDataStatuses")
	@ResponseBody
	public List<WorkoutPlanRecordResponseDTO> getMultipleByWorkoutPlanIdAndWorkouPlanDataStatuses(@RequestBody Map<String, Object> req) {
		Integer planId = (Integer) req.get("workoutPlanId");
		@SuppressWarnings("unchecked")
		List<Integer> statuses = (List<Integer>) req.get("dataStatuses");
		List<WorkoutPlanRecordResponseDTO> dtos = workoutPlanRecordSvc.getRecordsByWorkoutPlanIdAndDataStatuses(planId,
				statuses);
		return dtos;
	}

	/**
	 * 分頁查詢 (依計畫ID + 狀態)
	 */
	@PostMapping("/getMultipleByWorkoutPlanIdAndWorkouPlanDataStatusesPage")
	@ResponseBody
	public Page<WorkoutPlanRecordResponseDTO> getMultipleByWorkoutPlanIdAndWorkouPlanDataStatusesPage(@RequestBody Map<String, Object> req,
			Pageable pageable) {
		Integer planId = (Integer) req.get("workoutPlanId");
		@SuppressWarnings("unchecked")
		List<Integer> statuses = (List<Integer>) req.get("dataStatuses");
		Page<WorkoutPlanRecordResponseDTO> page = workoutPlanRecordSvc
				.getRecordsByWorkoutPlanIdAndDataStatusesPage(planId, statuses, pageable);
		return page;
	}

	/**
	 * 多筆計畫IDs查詢
	 */
	@PostMapping("/getMultipleByWorkoutPlanIds")
	@ResponseBody
	public Map<Integer, List<WorkoutPlanRecordResponseDTO>> getMultipleByWorkoutPlanIds(@RequestBody Map<String, Object> req) {
		@SuppressWarnings("unchecked")
		List<Integer> planIds = (List<Integer>) req.get("workoutPlanIds");
		Map<Integer, List<WorkoutPlanRecordResponseDTO>> result = workoutPlanRecordSvc
				.getRecordsByWorkoutPlanIds(planIds);
		return result;
	}

	/**
	 * 多筆計畫IDs + 狀態查詢
	 */
	@PostMapping("/getMultipleByWorkoutPlanIdsAndDataStatuses")
	@ResponseBody
	public Map<Integer, List<WorkoutPlanRecordResponseDTO>> getMultipleByWorkoutPlanIdsAndDataStatuses(
			@RequestBody Map<String, Object> req) {
		@SuppressWarnings("unchecked")
		List<Integer> planIds = (List<Integer>) req.get("workoutPlanIds");
		@SuppressWarnings("unchecked")
		List<Integer> statuses = (List<Integer>) req.get("dataStatuses");
		Map<Integer, List<WorkoutPlanRecordResponseDTO>> result = workoutPlanRecordSvc
				.getRecordsByWorkoutPlanIdsAndDataStatuses(planIds, statuses);
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
