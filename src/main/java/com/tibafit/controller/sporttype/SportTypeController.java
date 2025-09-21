package com.tibafit.controller.sporttype;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.tibafit.dto.sporttype.SportTypeRequestDTO;
import com.tibafit.dto.sporttype.SportTypeResponseDTO;
import com.tibafit.service.sporttype.SportTypeService;

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
}
