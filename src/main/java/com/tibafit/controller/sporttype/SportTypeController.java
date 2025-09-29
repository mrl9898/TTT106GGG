package com.tibafit.controller.sporttype;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.tibafit.dto.sport.ApiResponseDTO;
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

    // 批次新增
    @PostMapping("/insertMultiple")
    public ApiResponseDTO<Void> insertSportTypes(@Valid @RequestBody InsertMultipleRequest req) {
        sportTypeSvc.insertSportTypes(req.getDtos());
        Void result = null;
        return ApiResponseDTO.success(result);
    }

    // 批次更新
    @PostMapping("/updateMultiple")
    public ApiResponseDTO<Void> updateSportTypes(@Valid @RequestBody UpdateMultipleRequest req) {
        sportTypeSvc.updateSportTypes(req.getDtos());
        Void result = null;
        return ApiResponseDTO.success(result);
    }

    // 檢查名稱是否存在
    @PostMapping("/isExistSportTypeName")
    public ApiResponseDTO<Boolean> isExistSportTypeName(@Valid @RequestBody SportTypeNameRequest req) {
        Boolean result = sportTypeSvc.isExistBySportTypeName(req.getSportTypeName());
        return ApiResponseDTO.success(result);
    }

    // 查多筆 分類狀態 + 分類下運動狀態
    @PostMapping("/getMultipleBySportTypeDataStatusesWithSportDataStatuses")
    public ApiResponseDTO<List<SportTypeResponseExtraSportsDTO>> getMultipleBySportTypeDataStatusesWithSportDataStatuses(
            @Valid @RequestBody SportTypeDataStatusesAndSportDataStatusesRequest req) {
        List<SportTypeResponseExtraSportsDTO> result =
                sportTypeSvc.getBySportTypeDataStatuses_SportDataStatuses(
                        req.getSportTypeDataStatuses(),
                        req.getSportDataStatuses()
                );
        return ApiResponseDTO.success(result);
    }

    // 批次更新狀態
    @PostMapping("/updateSportTypeDataStatusBySportTypeIds")
    public ApiResponseDTO<Integer> updateSportTypeDataStatusBySportTypeIds(
            @Valid @RequestBody UpdateStatusRequest req) {
        Integer result =
                sportTypeSvc.updateSportTypeDataStatusBySportTypeIds(req.getDataStatus(), req.getSportTypeIds());
        return ApiResponseDTO.success(result);
    }

    // 複合條件查詢
    @PostMapping("/getByComplexCondition")
    public ApiResponseDTO<List<SportTypeResponseDTO>> getByComplexCondition(
            @Valid @RequestBody ComplexConditionRequest req) {
        List<SportTypeResponseDTO> result = sportTypeSvc.getByComplexCondition(
                req.getSportTypeNameFuzzy(),
                req.getCreateStartDate(),
                req.getCreateEndDate(),
                req.getUpdateStartDate(),
                req.getUpdateEndDate(),
                req.getStatuses()
        );
        return ApiResponseDTO.success(result);
    }

    
    
    // ------ DTOs ------

    public static class InsertMultipleRequest {
        private List<SportTypeRequestDTO> dtos;

        @NotEmpty(message = "dtos 不可為空")
        @Valid
        public List<SportTypeRequestDTO> getDtos() {
            return dtos;
        }
        public void setDtos(List<SportTypeRequestDTO> dtos) {
            this.dtos = dtos;
        }
    }

    public static class UpdateMultipleRequest {
        private List<SportTypeRequestDTO> dtos;

        @NotEmpty(message = "dtos 不可為空")
        @Valid
        public List<SportTypeRequestDTO> getDtos() {
            return dtos;
        }
        public void setDtos(List<SportTypeRequestDTO> dtos) {
            this.dtos = dtos;
        }
    }

    public static class SportTypeNameRequest {
        private String sportTypeName;

        @NotNull(message = "運動分類名稱不可為空")
        public String getSportTypeName() {
            return sportTypeName;
        }
        public void setSportTypeName(String sportTypeName) {
            this.sportTypeName = sportTypeName;
        }
    }

    public static class UpdateStatusRequest {
        private Integer dataStatus;
        private List<Integer> sportTypeIds;

        @NotNull(message = "資料狀態不可為空")
        public Integer getDataStatus() {
            return dataStatus;
        }
        public void setDataStatus(Integer dataStatus) {
            this.dataStatus = dataStatus;
        }

        
        @NotEmpty(message = "運動分類 IDs 不可為空")
        public List<Integer> getSportTypeIds() {
            return sportTypeIds;
        }
        public void setSportTypeIds(List<Integer> sportTypeIds) {
            this.sportTypeIds = sportTypeIds;
        }
    }

    public static class SportTypeDataStatusesAndSportDataStatusesRequest {
        private List<Integer> sportTypeDataStatuses;
        private List<Integer> sportDataStatuses;

        @NotEmpty(message = "運動分類狀態列表不可為空")
        public List<Integer> getSportTypeDataStatuses() {
            return sportTypeDataStatuses;
        }
        public void setSportTypeDataStatuses(List<Integer> sportTypeDataStatuses) {
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

    public static class ComplexConditionRequest {
        private String sportTypeNameFuzzy;
        private String createStartDate;
        private String createEndDate;
        private String updateStartDate;
        private String updateEndDate;
        private List<Integer> statuses;

        public String getSportTypeNameFuzzy() {
            return sportTypeNameFuzzy;
        }
        public void setSportTypeNameFuzzy(String sportTypeNameFuzzy) {
            this.sportTypeNameFuzzy = sportTypeNameFuzzy;
        }

        
        public String getCreateStartDate() {
            return createStartDate;
        }
        public void setCreateStartDate(String createStartDate) {
            this.createStartDate = createStartDate;
        }

        
        public String getCreateEndDate() {
            return createEndDate;
        }
        public void setCreateEndDate(String createEndDate) {
            this.createEndDate = createEndDate;
        }

        
        public String getUpdateStartDate() {
            return updateStartDate;
        }
        public void setUpdateStartDate(String updateStartDate) {
            this.updateStartDate = updateStartDate;
        }

        
        public String getUpdateEndDate() {
            return updateEndDate;
        }
        public void setUpdateEndDate(String updateEndDate) {
            this.updateEndDate = updateEndDate;
        }

        
        @NotEmpty(message = "狀態列表不可為空")
        public List<Integer> getStatuses() {
            return statuses;
        }
        public void setStatuses(List<Integer> statuses) {
            this.statuses = statuses;
        }
    }
}
