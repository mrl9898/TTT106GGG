package com.tibafit.model.sport;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import com.tibafit.dto.sport.SportRequestDTO;
import com.tibafit.dto.sport.SportResponseDTO;

import com.tibafit.model.sport.SportDataStatus;
import com.tibafit.model.sport.SportDataStatus;

public class SportConverter {

    public static SportResponseDTO toDTO(SportVO vo) {
        if (vo == null) {
        	return null;
        }

        SportResponseDTO dto = new SportResponseDTO();
        
        dto.setSportId(vo.getSportId());
        dto.setSportName(vo.getSportName());
        dto.setSportDescription(vo.getSportDescription());
        dto.setSportMets(vo.getSportMets());
        dto.setSportEstimatedCalories(vo.getSportEstimatedCalories());
        dto.setSportLevel(vo.getSportLevel());
        dto.setSportLevelText(SportLevel.getDisplayNameByCodeName(vo.getSportLevel()));
        dto.setSportPic(vo.getSportPic());
        dto.setSportDataStatus(vo.getSportDataStatus());
        dto.setSportDataStatusText(SportDataStatus.getDisplayNameByCodeNum(vo.getSportDataStatus()));
        dto.setCreateDatetime(vo.getCreateDatetime());
        dto.setUpdateDatetime(vo.getUpdateDatetime());
        dto.setAdminId(vo.getAdminId());
        // TODO: 根據實際需求補值
        dto.setAdminIdText("待補");

        return dto;
    }

   
    public static List<SportResponseDTO> toDtoList(List<SportVO> voList) {
    	List<SportResponseDTO> dtoList = new ArrayList<>();
    	
        if (voList == null || voList.isEmpty()) {
        	return dtoList;
        }

        for (SportVO vo : voList) {
            dtoList.add(toDTO(vo));
        }
        
        return dtoList;
    }
    
    
    public static SportVO toVO(SportRequestDTO dto) {
        if (dto == null) {
        	return null;
        }

        SportVO vo = new SportVO();
        String tempDes = dto.getSportDescription() == null? "" : dto.getSportDescription();
        BigDecimal tempMets = BigDecimal.valueOf(dto.getSportMets()).setScale(2, RoundingMode.HALF_UP);
        
        vo.setSportId(dto.getSportId());
        vo.setSportName(dto.getSportName());
        vo.setSportDescription(tempDes);
        vo.setSportMets(tempMets);
        vo.setSportEstimatedCalories(dto.getSportEstimatedCalories());
        vo.setSportLevel(SportLevel.judgeSportLevel(tempMets));
        vo.setSportPic(dto.getSportPic());
        vo.setSportDataStatus(dto.getSportDataStatus());
        vo.setAdminId(dto.getAdminId());

        return vo;
    }

   
    public static List<SportVO> toVoList(List<SportRequestDTO> dtoList) {
    	List<SportVO> voList = new ArrayList<>();
    	
        if (dtoList == null || dtoList.isEmpty()) {
        	return voList;
        }

        for (SportRequestDTO dto : dtoList) {
        	voList.add(toVO(dto));
        }
        
        return voList;
    }
}
