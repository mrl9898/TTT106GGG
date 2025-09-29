package com.tibafit.controller.sporttype;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tibafit.dto.sporttype.SportTypeRequestDTO;
import com.tibafit.dto.sporttype.SportTypeResponseDTO;
import com.tibafit.dto.sporttype.SportTypeResponseExtraSportsDTO;
import com.tibafit.service.sporttype.SportTypeService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/sportType/api")
public class SportTypeController {

    @Autowired
    private SportTypeService sportTypeSvc;

    // 新增多筆 SportType
    @PostMapping("/insertMultiple")
    public String insertSportTypes(@RequestBody List<SportTypeRequestDTO> dtos) {
        sportTypeSvc.insertSportTypes(dtos);
        String result = "新增成功";
        return result;
    }

    // 更新多筆 SportType
    @PostMapping("/updateMultiple")
    public String updateSportTypes(@RequestBody List<SportTypeRequestDTO> dtos) {
        sportTypeSvc.updateSportTypes(dtos);
        String result = "更新成功";
        return result;
    }

    // 檢查名稱是否存在
    @PostMapping("/isExistSportTypeName")
    public Boolean isExistSportTypeName(@RequestBody String sportTypeName) {
        Boolean isExist = sportTypeSvc.isExistBySportTypeName(sportTypeName);
        return isExist;
    }

    // 查單筆
    @PostMapping("/getSingleBySportTypeId")
    public SportTypeResponseDTO getBySportTypeId(@RequestBody Integer sportTypeId) {
        SportTypeResponseDTO dto = sportTypeSvc.getBySportTypeId(sportTypeId);
        return dto;
    }

    // 查多筆
    @PostMapping("/getMultipleBySportTypeIds")
    public List<SportTypeResponseDTO> getBySportTypeIds(@RequestBody List<Integer> sportTypeIds) {
        List<SportTypeResponseDTO> dtos = sportTypeSvc.getBySportTypeIds(sportTypeIds);
        return dtos;
    }
    
    
    
 // 查單筆 (分類下運動 + 狀態)
    @PostMapping("/getSingleBySportTypeIdWithSportDataStatuses")
    public SportTypeResponseExtraSportsDTO getSingleBySportTypeIdWithSportDataStatuses(
            @RequestBody @Valid SportTypeIdAndSportDataStatusesRequest req) {
        return sportTypeSvc.getBySportTypeId_SportDataStatuses(
                req.getSportTypeId(), 
                req.getSportDataStatuses()
        );
    }

    // 查多筆 分類狀態 + 分類下運動狀態
    @PostMapping("/getMultipleBySportTypeDataStatusesWithSportDataStatuses")
    public List<SportTypeResponseExtraSportsDTO> getMultipleBySportTypeDataStatusesWithSportDataStatuses(
            @RequestBody @Valid SportTypeDataStatusesAndSportDataStatusesRequest req) {
        return sportTypeSvc.getBySportTypeDataStatuses_SportDataStatuses(
                req.getSportTypeDataStatuses(), 
                req.getSportDataStatuses()
        );
    }
    
    

    // 查單筆 (含狀態篩選)
    @PostMapping("/getSingleSportTypeIdAndDataStatuses")
    public SportTypeResponseDTO getBySportTypeIdAndDataStatuses(@RequestBody SportTypeIdAndStatusesRequest req) {
        SportTypeResponseDTO dto =
                sportTypeSvc.getBySportTypeIdAndSportTypeDataStatuses(req.getSportTypeId(), req.getStatuses());
        return dto;
    }

    // 查多筆 (含狀態篩選)
    @PostMapping("/getMultipleBySportTypeIdsAndDataStatuses")
    public List<SportTypeResponseDTO> getBySportTypeIdsAndDataStatuses(@RequestBody SportTypeIdsAndStatusesRequest req) {
        List<SportTypeResponseDTO> dtos =
                sportTypeSvc.getBySportTypeIdsAndSportTypeDataStatuses(req.getSportTypeIds(), req.getStatuses());
        return dtos;
    }

    // 查全部 (依狀態)
    @PostMapping("/getMultipleBySportTypeDataStatuses")
    public List<SportTypeResponseDTO> getBySportTypeDataStatuses(@RequestBody List<Integer> statuses) {
        List<SportTypeResponseDTO> dtos = sportTypeSvc.getBySportTypeDataStatuses(statuses);
        return dtos;
    }

    // 更新狀態
    @PostMapping("/updateSportTypeDataStatusBySportTypeIds")
    public Integer updateSportTypeDataStatusBySportTypeIds(@RequestBody UpdateStatusRequest req) {
        Integer affected =
                sportTypeSvc.updateSportTypeDataStatusBySportTypeIds(req.getDataStatus(), req.getSportTypeIds());
        return affected;
    }
    
    
	/*
	 * Test
	 */
	@PostMapping("/getByComplexCondition")
	@ResponseBody
	public List<SportTypeResponseDTO> getByComplexCondition(@RequestBody Map<String, Object> map) {
	    ObjectMapper objectMapper = new ObjectMapper();

	    // 字串型別參數
	    String sportTypeNameFuzzy = objectMapper.convertValue(map.get("sportTypeNameFuzzy"), String.class);
	    String createStartDate   = objectMapper.convertValue(map.get("createStartDate"), String.class);
	    String createEndDate     = objectMapper.convertValue(map.get("createEndDate"), String.class);
	    String updateStartDate   = objectMapper.convertValue(map.get("updateStartDate"), String.class);
	    String updateEndDate     = objectMapper.convertValue(map.get("updateEndDate"), String.class);

	    // List<Integer>
	    List<Integer> statuses = objectMapper.convertValue(map.get("statuses"), new TypeReference<List<Integer>>() {});


	    List<SportTypeResponseDTO> dtoList = sportTypeSvc.getByComplexCondition(
	    	sportTypeNameFuzzy, 
	        createStartDate, 
	        createEndDate, 
	        updateStartDate, 
	        updateEndDate, 
	        statuses
	    );

	     return dtoList;
	}

    // Request DTO 
    public static class SportTypeIdAndStatusesRequest {

        private Integer sportTypeId;
        private List<Integer> statuses;

        @NotNull(message = "運動分類: ID不可為空")
        public Integer getSportTypeId() {
            return sportTypeId;
        }

        public void setSportTypeId(Integer sportTypeId) {
            this.sportTypeId = sportTypeId;
        }

        @NotEmpty(message = "資料狀態: 不可為空")
        public List<Integer> getStatuses() {
            return statuses;
        }

        public void setStatuses(List<Integer> statuses) {
            this.statuses = statuses;
        }
    }

    public static class SportTypeIdsAndStatusesRequest {

        private List<Integer> sportTypeIds;
        private List<Integer> statuses;

        @NotEmpty(message = "運動分類: IDs不可為空")
        public List<Integer> getSportTypeIds() {
            return sportTypeIds;
        }

        public void setSportTypeIds(List<Integer> sportTypeIds) {
            this.sportTypeIds = sportTypeIds;
        }

        @NotEmpty(message = "資料狀態: 不可為空")
        public List<Integer> getStatuses() {
            return statuses;
        }

        public void setStatuses(List<Integer> statuses) {
            this.statuses = statuses;
        }
    }

    public static class UpdateStatusRequest {

        private Integer dataStatus;
        private List<Integer> sportTypeIds;

        @NotNull(message = "資料狀態: 不可為空")
        public Integer getDataStatus() {
            return dataStatus;
        }

        public void setDataStatus(Integer dataStatus) {
            this.dataStatus = dataStatus;
        }

        @NotEmpty(message = "運動分類: IDs不可為空")
        public List<Integer> getSportTypeIds() {
            return sportTypeIds;
        }

        public void setSportTypeIds(List<Integer> sportTypeIds) {
            this.sportTypeIds = sportTypeIds;
        }
    }
    
    public static class SportTypeIdAndSportDataStatusesRequest {

        private Integer sportTypeId;
        private List<Integer> sportDataStatuses;

        @NotNull(message = "運動分類 ID 不可為空")
        public Integer getSportTypeId() {
            return sportTypeId;
        }
        public void setSportTypeId(Integer sportTypeId) {
            this.sportTypeId = sportTypeId;
        }

        @NotEmpty(message = "運動資料狀態列表不可為空")
        public List<Integer> getSportDataStatuses() {
            return sportDataStatuses;
        }
        public void setSportDataStatuses(List<Integer> sportDataStatuses) {
            this.sportDataStatuses = sportDataStatuses;
        }
    }
    
    public static class SportTypeDataStatusesAndSportDataStatusesRequest {

        private List<Integer> sportTypeDataStatuses;
        private List<Integer> sportDataStatuses;

        @NotEmpty(message = "運動分類 IDs 不可為空")
        public List<Integer> getSportTypeDataStatuses() {
            return sportTypeDataStatuses;
        }
        public void setSportTypeIds(List<Integer> sportTypeDataStatuses) {
            this.sportTypeDataStatuses = sportTypeDataStatuses;
        }

        @NotEmpty(message = "運動資料狀態列表不可為空")
        public List<Integer> getSportDataStatuses() {
            return sportDataStatuses;
        }
        public void setSportDataStatuses(List<Integer> sportDataStatuses) {
            this.sportDataStatuses = sportDataStatuses;
        }
    }
}
