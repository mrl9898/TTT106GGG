package com.tibafit.dto.sporttypeitem;

import jakarta.validation.constraints.NotNull;

public class SportTypeItemRequestDTO {
    private Integer sportTypeItemId;
    private Integer sportTypeId;
    private Integer sportId;
    private Integer sportTypeItemDataStatus;

    public Integer getSportTypeItemId() {
        return sportTypeItemId;
    }
    public void setSportTypeItemId(Integer sportTypeItemId) {
        this.sportTypeItemId = sportTypeItemId;
    }

    
    public Integer getSportTypeId() {
        return sportTypeId;
    }
    public void setSportTypeId(Integer sportTypeId) {
        this.sportTypeId = sportTypeId;
    }

    
    @NotNull(message = "運動ID: 不可為空")
    public Integer getSportId() {
        return sportId;
    }
    public void setSportId(Integer sportId) {
        this.sportId = sportId;
    }
    
    
    public Integer getSportTypeItemDataStatus() {
		return sportTypeItemDataStatus;
	}
	public void setSportTypeItemDataStatus(Integer sportTypeItemDataStatus) {
		this.sportTypeItemDataStatus = sportTypeItemDataStatus;
	}
    
    
    @Override
    public String toString() {
        return "SportTypeItemRequestDTO {" +
               "sportTypeItemId=" + sportTypeItemId + 
               ", sportTypeId=" + sportTypeId + 
               ", sportId=" + sportId + 
               ", sportTypeItemDataStatus=" + sportTypeItemDataStatus + 
               "}";
    }
}
