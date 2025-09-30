package com.tibafit.service.sporttypeitem;

import java.util.List;
import java.util.Optional;

import com.tibafit.dto.sporttypeitem.SportTypeItemRequestDTO;
import com.tibafit.dto.sporttypeitem.SportTypeItemResponseDTO;
import com.tibafit.model.sporttypeitem.SportTypeItemVO;

public interface SportTypeItemService_interface {

    // 新增
	public void insertSportTypeItems(List<SportTypeItemRequestDTO> dtos);

    // 更新
	public void updateSportTypeItems(List<SportTypeItemRequestDTO> dtos);
    
    // 檢查是否存在 (sportTypeId + sportId)
    public Boolean isExitBySportTypeIdAndSportId(Integer sportTypeId, Integer sportId);

    // 查單筆
    public SportTypeItemResponseDTO getBySportTypeItemId(Integer sportTypeItemId);

    // 查多筆 (sportTypeId)
    public List<SportTypeItemResponseDTO> getBySportTypeId(Integer sportTypeId);

    // 查單筆 (sportTypeId + sportId)
    public SportTypeItemResponseDTO getBySportTypeIdAndSportId(Integer sportTypeId, Integer sportId);

    // 查單筆 (SportTypeItemId + 資料狀態)
    public SportTypeItemResponseDTO getBySportTypeItemIdAndSportTypeItemDataStatuses(Integer sportTypeItemId, List<Integer> statuses);

    // 查多筆 (sportTypeId + 資料狀態)
    public List<SportTypeItemResponseDTO> getBySportTypeIdAndSportTypeItemDataStatuses(Integer sportTypeId, List<Integer> statuses);

    // 查單筆 (sportTypeId + sportId + 資料狀態)
    public SportTypeItemResponseDTO getBySportTypeIdAndSportIdAndSportTypeItemDataStatuses(Integer sportTypeId, Integer sportId, List<Integer> statuses);

    // 批次更新狀態 (sportTypeItemIds)
    public Integer updateSportTypeItemDataStatusBySportTypeItemIds(Integer dataStatus, List<Integer> sportTypeItemIds);

    // 批次更新狀態 (sportTypeIds)
    public Integer updateSportTypeItemDataStatusBySportTypeIds(Integer dataStatus, List<Integer> sportTypeIds);

    // 批次更新狀態 (sportIds)
    public Integer updateSportTypeItemDataStatusBySportIds(Integer dataStatus, List<Integer> sportIds);
    
    public void deleteBySportTypeItemIds(List<Integer> sportTypeRecordIds);
}