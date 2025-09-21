package com.tibafit.model.workoutplanrecord;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.tibafit.dto.workoutplanrecord.WorkoutPlanRecordRequestDTO;
import com.tibafit.dto.workoutplanrecord.WorkoutPlanRecordResponseDTO;
import com.tibafit.model.customsport.CustomSportVO;
import com.tibafit.model.sport.SportVO;
import com.tibafit.model.workoutplan.WorkoutPlanSportFrom;
import com.tibafit.model.workoutplan.WorkoutPlanVO;

public class WorkoutPlanRecordConverter {

    // RequestDTO 轉 VO
    public static WorkoutPlanRecordVO toInsertVO(WorkoutPlanRecordRequestDTO dto) {
        if (dto == null) {
        	return null;
        }

        WorkoutPlanRecordVO vo = new WorkoutPlanRecordVO();
        
		String tempSportFrom = dto.getSportFrom();
		Integer tempSportId = dto.getSportId();
		Integer tempCustomSportId = dto.getCustomSportId();
        
		boolean checkResult = false;
		if((WorkoutPlanSportFrom.SYSTEM.getCodeName()).equals(tempSportFrom) && tempSportId != null) {
			tempCustomSportId = null;
			checkResult = true;
		}
		if ((WorkoutPlanSportFrom.CUSTOM.getCodeName()).equals(tempSportFrom) && tempCustomSportId != null) {
			tempSportId = null;
			checkResult = true;
		}
		
		// TODO: 拋回FE
		if(!checkResult) {
			return null;
		}
		
		LocalDateTime tempActualStartTime = LocalDateTime.parse(dto.getActualStartTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		LocalDateTime tempActualEndTime = LocalDateTime.parse(dto.getActualEndTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		
        vo.setWorkoutPlanRecordId(dto.getWorkoutPlanRecordId());
        
        vo.setWorkoutPlanId(dto.getWorkoutPlanId());
        
        vo.setSportFrom(tempSportFrom);
        vo.setSportId(tempSportId);
        vo.setCustomSportId(tempCustomSportId);
        
        vo.setActualStartTime(tempActualStartTime);
        vo.setActualEndTime(tempActualEndTime);

        vo.setWorkoutPlanRecordDataStatus(WorkoutPlanRecordDataStatus.EXIST.getCodeNum());
        
        return vo;
    }

    // VO 轉 ResponseDTO
    public static WorkoutPlanRecordResponseDTO toDTO(WorkoutPlanRecordVO vo) {
        if (vo == null) {
        	return null;
        }

        WorkoutPlanRecordResponseDTO dto = new WorkoutPlanRecordResponseDTO();
        
        dto.setWorkoutPlanRecordId(vo.getWorkoutPlanRecordId());
        dto.setWorkoutPlanId(vo.getWorkoutPlanId());
        dto.setSportFrom(vo.getSportFrom());
        dto.setSportId(vo.getSportId());
        dto.setCustomSportId(vo.getCustomSportId());
        dto.setActualCalories(vo.getActualCalories());
        dto.setActualStartTime(vo.getActualStartTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        dto.setActualEndTime(vo.getActualEndTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        dto.setActualDuration(vo.getActualDuration());
        dto.setActualRecordDatetime(vo.getActualRecordDatetime() == null? "" : vo.getActualRecordDatetime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        dto.setWorkoutPlanRecordDataStatus(vo.getWorkoutPlanRecordDataStatus());
        dto.setCreateDatetime(vo.getCreateDatetime() == null? "" : vo.getCreateDatetime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        dto.setUpdateDatetime(vo.getUpdateDatetime() == null? "" : vo.getUpdateDatetime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        
        return dto;
    }

    // List<RequestDTO> 轉 List<VO>
    public static List<WorkoutPlanRecordVO> toInsertVoList(List<WorkoutPlanRecordRequestDTO> dtoList) {
    	List<WorkoutPlanRecordVO> voList = new ArrayList<>();
        
    	if (dtoList == null || dtoList.isEmpty()) {
        	return voList;
        }

        for (WorkoutPlanRecordRequestDTO dto : dtoList) {
            if (dto != null) {
                WorkoutPlanRecordVO vo = WorkoutPlanRecordConverter.toInsertVO(dto);
                voList.add(vo);
            }
        }

        return voList;
    }

    // List<VO> 轉 List<ResponseDTO>
    public static List<WorkoutPlanRecordResponseDTO> toDtoList(List<WorkoutPlanRecordVO> voList) {
    	List<WorkoutPlanRecordResponseDTO> dtoList = new ArrayList<>();
    	
    	if (voList == null || voList.isEmpty()) {
        	return dtoList;
    	}
        for (WorkoutPlanRecordVO vo : voList) {
            if (vo!= null) {
            	WorkoutPlanRecordResponseDTO dto = WorkoutPlanRecordConverter.toDTO(vo);
                dtoList.add(dto);
            }
        }

        return dtoList;
    }
}
