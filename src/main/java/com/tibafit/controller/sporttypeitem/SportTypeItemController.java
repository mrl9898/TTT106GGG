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
    

    // 檢查是否存在
    @PostMapping("/isExitBySportTypeIdAndSportId")
    public Boolean isExitBySportTypeIdAndSportId(@RequestBody @Valid SportTypeIdAndSportIdRequest req) {
        Boolean result =
                sportTypeItemSvc.isExitBySportTypeIdAndSportId(req.getSportTypeId(), req.getSportId());
        return result;
    }
    
    
    // 刪除
    @PostMapping("/deleteBySportTypeItemIds")
    public void deleteBySportTypeItemIds(@RequestBody @Valid DeleteBySportTypeItemIdsRequest req) {
          sportTypeItemSvc.deleteBySportTypeItemIds(req.getSportTypeItemIds());
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
    
    public static class DeleteBySportTypeItemIdsRequest {
        private List<Integer> sportTypeItemIds;

        @NotEmpty(message = "運動分類明細IDs不可為空")
        public List<Integer> getSportTypeItemIds() {
            return sportTypeItemIds;
        }
        public void setSportTypeItemIds(List<Integer> sportTypeItemIds) {
            this.sportTypeItemIds = sportTypeItemIds;
        }
    }
}

