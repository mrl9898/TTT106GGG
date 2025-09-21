package com.tibafit.model.customsport;

import java.util.ArrayList;
import java.util.List;

import com.tibafit.dto.customsport.CustomSportRequestDTO;
import com.tibafit.dto.customsport.CustomSportResponseDTO;

public class CustomSportConverter {

	public static CustomSportVO toVO(CustomSportRequestDTO dto) {
		if (dto == null) {
			return null;
		}

		CustomSportVO vo = new CustomSportVO();

		vo.setCustomSportId(dto.getCustomSportId());
		vo.setSportName(dto.getSportName());
		vo.setSportDescription(dto.getSportDescription());
		vo.setSportEstimatedCalories(dto.getSportEstimatedCalories());
		vo.setSportPic(dto.getSportPic());
		vo.setSportDataStatus(dto.getSportDataStatus());
		vo.setUserId(dto.getUserId());

		return vo;
	}

	public static List<CustomSportVO> toVoList(List<CustomSportRequestDTO> dtoList) {
		List<CustomSportVO> voList = new ArrayList<>();

		if (dtoList == null || dtoList.isEmpty()) {
			return voList;
		}

		for (CustomSportRequestDTO dto : dtoList) {
			voList.add(toVO(dto));
		}

		return voList;
	}

	public static CustomSportResponseDTO toDTO(CustomSportVO vo) {
		if (vo == null) {
			return null;
		}

		CustomSportResponseDTO dto = new CustomSportResponseDTO();

		dto.setCustomSportId(vo.getCustomSportId());
		dto.setSportName(vo.getSportName());
		dto.setSportDescription(vo.getSportDescription());
		dto.setSportEstimatedCalories(vo.getSportEstimatedCalories());
		dto.setSportPic(vo.getSportPic());
		dto.setSportDataStatus(vo.getSportDataStatus());
		dto.setSportDataStatusText(CustomSportDataStatus.getDisplayNameByCodeNum(vo.getSportDataStatus()));
		dto.setCreateDatetime(vo.getCreateDatetime());
		dto.setUpdateDatetime(vo.getUpdateDatetime());
		dto.setUserId(vo.getUserId());
		dto.setUserIdText("待補");

		return dto;
	}

	public static List<CustomSportResponseDTO> toDtoList(List<CustomSportVO> voList) {
		List<CustomSportResponseDTO> dtoList = new ArrayList<>();

		if (voList == null || voList.isEmpty()) {
			return dtoList;
		}

		for (CustomSportVO vo : voList) {
			dtoList.add(toDTO(vo));
		}

		return dtoList;
	}
}
