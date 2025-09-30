package com.tibafit.service.workoutplan;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tibafit.dto.workoutplan.WorkoutPlanRequestDTO;
import com.tibafit.dto.workoutplan.WorkoutPlanResponseDTO;
import com.tibafit.model.workoutplan.WorkoutPlanConverter;
import com.tibafit.model.workoutplan.WorkoutPlanVO;
import com.tibafit.model.workoutplanrecord.WorkoutPlanRecordVO;
import com.tibafit.repository.workoutplan.WorkoutPlanRepository;
import com.tibafit.repository.workoutplanrecord.WorkoutPlanRecordRepository;

@Service
@Transactional
public class workoutPlanService implements workoutPlanService_interface {

	@Autowired
	private WorkoutPlanRepository planRepo;
	
	@Autowired
	private WorkoutPlanRecordRepository planRecordRepo;


	@Override
	public void insertWorkoutPlanMultiple(List<WorkoutPlanRequestDTO> dtos) {
		List<WorkoutPlanVO> vos = WorkoutPlanConverter.toNewVoList(dtos);
		planRepo.saveAll(vos);
	}


	@Override
	public void updateWorkoutPlanMultiple(List<WorkoutPlanRequestDTO> dtos) {
	    List<Integer> ids = new ArrayList<>();
	    for (WorkoutPlanRequestDTO dto : dtos) {
	        if (dto != null && dto.getWorkoutPlanId() != null) {
	            ids.add(dto.getWorkoutPlanId());
	        }
	    }

	    // PO
	    List<WorkoutPlanVO> oriVos = planRepo.findAllById(ids);
	    
		List<WorkoutPlanVO> vos = WorkoutPlanConverter.toUpdateVoList(oriVos, dtos);
		
		planRepo.saveAll(vos);
	}


	@Override
	public WorkoutPlanResponseDTO getWorkoutPlanByPrimaryKey(Integer id) {
		WorkoutPlanVO vo = planRepo.findByWorkoutPlanId(id).orElse(null);
		WorkoutPlanResponseDTO dto = WorkoutPlanConverter.toDTO(vo);
		return dto;
	}

	@Override
	public List<WorkoutPlanResponseDTO> getWorkoutPlanByDateRange(Integer userId, LocalDate startDate, LocalDate endDate, List<Integer> statuses) {
		List<WorkoutPlanVO> vos = planRepo.findByUserIdAndWorkoutPlanDateBetweenAndWorkoutPlanDataStatusIn(userId, startDate, endDate, statuses);
		List<WorkoutPlanResponseDTO> dtos = WorkoutPlanConverter.toDtoList(vos);
		return dtos;
	}
	
	@Override
	public List<WorkoutPlanResponseDTO> getWorkoutPlanByDate(Integer userId, LocalDate workoutPlanDate, List<Integer> statuses) {
		List<WorkoutPlanVO> vos = planRepo.findByUserIdAndWorkoutPlanDateAndWorkoutPlanDataStatusIn(userId, workoutPlanDate, statuses);
		List<WorkoutPlanResponseDTO> dtos = WorkoutPlanConverter.toDtoList(vos);
		return dtos;
	}

	@Override
	public Integer updateWorkoutPlanDataStatusByIds(Integer status, List<Integer> ids) {
		if (ids == null || ids.isEmpty()) {
			return 0;
		}
		Integer affectNumOfPlan = planRepo.updateWorkoutPlanDataStatusByIds(status, ids);
		
		// 一併更新該計畫的紀錄資料狀態
		List<Integer> recordIds = new ArrayList<>();
		if(affectNumOfPlan > 0) {
			// PO
			List<WorkoutPlanRecordVO> recordVOs = planRecordRepo.findByWorkoutPlanIdIn(ids);
			
			for (WorkoutPlanRecordVO recordVO : recordVOs) {
				if(recordVO != null && recordVO.getWorkoutPlanRecordId() != null) {
					recordIds.add(recordVO.getWorkoutPlanRecordId());
				}
			}
		}
		
		planRecordRepo.updateWorkoutPlanRecordDataStatusByRecordIds(status, recordIds);
		
		return affectNumOfPlan;
	}
}
