package com.tibafit.dto.sporttype;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SportTypeRequestDTO {
    private Integer sportTypeId;
    private String sportTypeName;
    private String sportTypePic;
    private Integer sportTypeDataStatus;
    
    
    public Integer getSportTypeId() {
        return sportTypeId;
    }
    public void setSportTypeId(Integer sportTypeId) {
        this.sportTypeId = sportTypeId;
    }
    

    @NotBlank(message = "運動分類名稱: 不可為空")
    @Size(max = 50, message = "運動分類名稱: 長度不可超過 50")
    public String getSportTypeName() {
        return sportTypeName;
    }
    public void setSportTypeName(String sportTypeName) {
        this.sportTypeName = sportTypeName;
    }
    

    @Size(max = 255, message = "運動分類圖片: 長度不可超過 255")
    public String getSportTypePic() {
        return sportTypePic;
    }
    public void setSportTypePic(String sportTypePic) {
        this.sportTypePic = sportTypePic;
    }
    
    
    @Min(value = 0, message = "運動分類資料狀態: 最小值為 0")
    @Max(value = 2, message = "運動分類資料狀態: 最大值為 2")
    @Digits(integer = 1, fraction = 0, message = "運動分類狀態只能是整數 (0, 1, 2)")
    public Integer getSportTypeDataStatus() {
		return sportTypeDataStatus;
	}
	public void setSportTypeDataStatus(Integer sportTypeDataStatus) {
		this.sportTypeDataStatus = sportTypeDataStatus;
	}
    
    
    @Override
    public String toString() {
        return "SportTypeRequestDTO {" +
               "sportTypeId=" + sportTypeId + 
               ", sportTypeName=" + sportTypeName + 
               ", sportTypePic=" + sportTypePic + 
               ", sportTypeDataStatus=" + sportTypeDataStatus + 
               "}";
    }
}
