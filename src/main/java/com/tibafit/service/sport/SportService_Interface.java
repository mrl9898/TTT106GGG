package com.tibafit.service.sport;

import java.util.List;

import com.tibafit.dto.sport.SportRequestDTO;
import com.tibafit.model.sport.SportDataStatus;
import com.tibafit.model.sport.SportVO;

public interface SportService_Interface {
	
//	public void insertSport(String sportName, String sportDescription, Double sportMets, Integer sportEstimatedCalories, String sportPic, Integer adminId);
	public void insertSport(SportRequestDTO dto);
	public void insertSportMultiple(List<SportRequestDTO> dtos);
	
//	public void updateSport(Integer sportId, String sportName, String sportDescription, Double sportMets, Integer sportEstimatedCalories, String sportPic, Integer sportDataStatus, Integer adminId);
	public void updateSport(SportRequestDTO dto);
	public void updateSportMultiple(List<SportRequestDTO> dtos);
	
	public Integer updateSportDataStatusByIds(Integer targetStatus, List<Integer> sportIds);
	
	
	public List<SportVO> getSportAll();
	public SportVO getSportByPrimaryKey(Integer sportIds);
	
	public List<SportVO> getSportByDataStatuses(List<Integer> sportDataStatuses);
	
//	public List<SportVO> getSportByNameFuzzy(String keyword);
	
	public List<SportDataStatus> getSportDataStatusOptions();
	
	public List<SportDataStatus> getSportDataStatusNeed(List<Integer> needStatusCodeNums);

}
