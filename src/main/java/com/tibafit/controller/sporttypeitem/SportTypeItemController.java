package com.tibafit.controller.sporttypeitem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.tibafit.dto.sporttypeitem.SportTypeItemRequestDTO;
import com.tibafit.dto.sporttypeitem.SportTypeItemResponseDTO;
import com.tibafit.service.sporttypeitem.SportTypeItemService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/sportTypeItem/api")
public class SportTypeItemController {

    @Autowired
    private SportTypeItemService sportTypeItemSvc;

    // 新增多筆
    @PostMapping("/insertMultiple")
    public String insertSportTypeItems(@RequestBody @Valid List<SportTypeItemRequestDTO> dtos) {
        sportTypeItemSvc.insertSportTypeItems(dtos);
        String result = "新增成功";
        return result;
    }

    // 更新多筆
    @PostMapping("/updateMultiple")
    public String updateSportTypeItems(@RequestBody @Valid List<SportTypeItemRequestDTO> dtos) {
        sportTypeItemSvc.updateSportTypeItems(dtos);
        String result = "更新成功";
        return result;
    }

    // 檢查是否存在
    @PostMapping("/isExitBySportTypeIdAndSportId")
    public Boolean isExitBySportTypeIdAndSportId(@RequestBody @Valid SportTypeIdAndSportIdRequest req) {
        Boolean result =
                sportTypeItemSvc.isExitBySportTypeIdAndSportId(req.getSportTypeId(), req.getSportId());
        return result;
    }

    // 查單筆
    @PostMapping("/getSingleBySportTypeItemId")
    public SportTypeItemResponseDTO getBySportTypeItemId(@RequestBody @Valid SportTypeItemIdRequest req) {
        SportTypeItemResponseDTO dto = sportTypeItemSvc.getBySportTypeItemId(req.getSportTypeItemId());
        return dto;
    }

    // 查多筆 (sportTypeId)
    @PostMapping("/getMultipleBySportTypeId")
    public List<SportTypeItemResponseDTO> getBySportTypeId(@RequestBody @Valid SportTypeIdRequest req) {
        List<SportTypeItemResponseDTO> dtos = sportTypeItemSvc.getBySportTypeId(req.getSportTypeId());
        return dtos;
    }

    // 查單筆 (sportTypeId + sportId)
    @PostMapping("/getSingleByTypeIdAndSportId")
    public SportTypeItemResponseDTO getBySportTypeIdAndSportId(@RequestBody @Valid SportTypeIdAndSportIdRequest req) {
        SportTypeItemResponseDTO dto =
                sportTypeItemSvc.getBySportTypeIdAndSportId(req.getSportTypeId(), req.getSportId());
        return dto;
    }

    // 查單筆 (sportTypeItemId + 資料狀態)
    @PostMapping("/getSingleBySportTypeItemIdAndDataStatuses")
    public SportTypeItemResponseDTO getBySportTypeItemIdAndDataStatuses(@RequestBody @Valid SportTypeItemIdAndStatusesRequest req) {
        SportTypeItemResponseDTO dto =
                sportTypeItemSvc.getBySportTypeItemIdAndSportTypeItemDataStatuses(req.getSportTypeItemId(), req.getStatuses());
        return dto;
    }

    // 查多筆 (sportTypeId + 資料狀態)
    @PostMapping("/getMultipleBySportTypeIdAndDataStatuses")
    public List<SportTypeItemResponseDTO> getBySportTypeIdAndStatuses(@RequestBody @Valid SportTypeIdAndStatusesRequest req) {
        List<SportTypeItemResponseDTO> dtos =
                sportTypeItemSvc.getBySportTypeIdAndSportTypeItemDataStatuses(req.getSportTypeId(), req.getStatuses());
        return dtos;
    }

    // 查單筆 (sportTypeId + sportId + 資料狀態)
    @PostMapping("/getSingleBySportTypeIdAndSportIdAndDataStatuses")
    public SportTypeItemResponseDTO getBySportTypeIdAndSportIdAndDataStatuses(@RequestBody @Valid SportTypeIdSportIdAndStatusesRequest req) {
        SportTypeItemResponseDTO dto =
                sportTypeItemSvc.getBySportTypeIdAndSportIdAndSportTypeItemDataStatuses(req.getSportTypeId(), req.getSportId(), req.getStatuses());
        return dto;
    }

    // 更新狀態 (sportTypeItemIds)
    @PostMapping("/updateDataStatusBySportTypeItemIds")
    public Integer updateSportTypeItemDataStatusBySportTypeItemIds(@RequestBody @Valid UpdateStatusByItemIdsRequest req) {
        Integer affected =
                sportTypeItemSvc.updateSportTypeItemDataStatusBySportTypeItemIds(req.getDataStatus(), req.getSportTypeItemIds());
        return affected;
    }

    // ==== Request DTOs ====
    public static class SportTypeIdAndSportIdRequest {
        private Integer sportTypeId;
        private Integer sportId;

        @NotNull(message = "運動分類: ID不可為空")
        public Integer getSportTypeId() {
            return sportTypeId;
        }
        public void setSportTypeId(Integer sportTypeId) {
            this.sportTypeId = sportTypeId;
        }

        @NotNull(message = "運動ID不可為空")
        public Integer getSportId() {
            return sportId;
        }
        public void setSportId(Integer sportId) {
            this.sportId = sportId;
        }
    }

    public static class SportTypeItemIdRequest {
        private Integer sportTypeItemId;

        @NotNull(message = "運動分類明細ID不可為空")
        public Integer getSportTypeItemId() {
            return sportTypeItemId;
        }
        public void setSportTypeItemId(Integer sportTypeItemId) {
            this.sportTypeItemId = sportTypeItemId;
        }
    }

    public static class SportTypeIdRequest {
        private Integer sportTypeId;

        @NotNull(message = "運動分類ID不可為空")
        public Integer getSportTypeId() {
            return sportTypeId;
        }
        public void setSportTypeId(Integer sportTypeId) {
            this.sportTypeId = sportTypeId;
        }
    }

    public static class SportTypeItemIdAndStatusesRequest {
        private Integer sportTypeItemId;
        private List<Integer> statuses;

        @NotNull(message = "運動分類明細ID不可為空")
        public Integer getSportTypeItemId() {
            return sportTypeItemId;
        }
        public void setSportTypeItemId(Integer sportTypeItemId) {
            this.sportTypeItemId = sportTypeItemId;
        }

        @NotEmpty(message = "狀態列表不可為空")
        public List<Integer> getStatuses() {
            return statuses;
        }
        public void setStatuses(List<Integer> statuses) {
            this.statuses = statuses;
        }
    }

    public static class SportTypeIdAndStatusesRequest {
        private Integer sportTypeId;
        private List<Integer> statuses;

        @NotNull(message = "運動分類ID不可為空")
        public Integer getSportTypeId() {
            return sportTypeId;
        }
        public void setSportTypeId(Integer sportTypeId) {
            this.sportTypeId = sportTypeId;
        }

        @NotEmpty(message = "狀態列表不可為空")
        public List<Integer> getStatuses() {
            return statuses;
        }
        public void setStatuses(List<Integer> statuses) {
            this.statuses = statuses;
        }
    }

    public static class SportTypeIdSportIdAndStatusesRequest {
        private Integer sportTypeId;
        private Integer sportId;
        private List<Integer> statuses;

        @NotNull(message = "運動分類ID不可為空")
        public Integer getSportTypeId() {
            return sportTypeId;
        }
        public void setSportTypeId(Integer sportTypeId) {
            this.sportTypeId = sportTypeId;
        }

        @NotNull(message = "運動ID不可為空")
        public Integer getSportId() {
            return sportId;
        }
        public void setSportId(Integer sportId) {
            this.sportId = sportId;
        }

        @NotEmpty(message = "狀態列表不可為空")
        public List<Integer> getStatuses() {
            return statuses;
        }
        public void setStatuses(List<Integer> statuses) {
            this.statuses = statuses;
        }
    }

    public static class UpdateStatusByItemIdsRequest {
        private Integer dataStatus;
        private List<Integer> sportTypeItemIds;

        @NotNull(message = "資料狀態不可為空")
        public Integer getDataStatus() {
            return dataStatus;
        }
        public void setDataStatus(Integer dataStatus) {
            this.dataStatus = dataStatus;
        }

        @NotEmpty(message = "運動分類明細IDs不可為空")
        public List<Integer> getSportTypeItemIds() {
            return sportTypeItemIds;
        }
        public void setSportTypeItemIds(List<Integer> sportTypeItemIds) {
            this.sportTypeItemIds = sportTypeItemIds;
        }
    }
}

