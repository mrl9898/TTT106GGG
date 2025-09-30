package com.tibafit.dto.sport;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class SportRequestDTO {
	private Integer sportId;
	private String sportName;
	private String sportDescription;
	private Double sportMets;
	private Integer sportEstimatedCalories;
	private String sportLevel;
	private String sportPic;
	private Integer sportDataStatus;
    private Integer adminId;
	
	
	public SportRequestDTO() {
		super();
	}


	public SportRequestDTO(Integer sportId, String sportName, String sportDescription, Double sportMets, Integer sportEstimatedCalories, String sportLevel, String sportPic, Integer sportDataStatus, Integer adminId) {
		super();
		this.sportId = sportId;
		this.sportName = sportName;
		this.sportDescription = sportDescription;
		this.sportMets = sportMets;
		this.sportEstimatedCalories = sportEstimatedCalories;
		this.sportLevel = sportLevel;
		this.sportPic = sportPic;
		this.sportDataStatus = sportDataStatus;
		this.adminId = adminId;
	}

	
	public Integer getSportId() {
		return sportId;
	}
	public void setSportId(Integer sportId) {
		this.sportId = sportId;
	}


	@NotBlank(message="名稱: 請勿空白")
	public String getSportName() {
		return sportName;
	}
	public void setSportName(String sportName) {
		this.sportName = sportName;
	}

	
	@NotBlank(message="描述: 請勿空白")
	public String getSportDescription() {
		return sportDescription;
	}
	public void setSportDescription(String sportDescription) {
		this.sportDescription = sportDescription;
	}

	
	@NotNull(message = "強度: 請勿空白")
//	@Positive(message="運動描述: 請輸入大於0的數字，最多4位整數，小數點後最多2位")
	@DecimalMin(value = "0.01", inclusive = true, message="強度: 請輸入大於0的數字，最多4位整數，小數點後最多2位")
	@Digits(integer = 4, fraction = 2, message = "強度: 請輸入大於0的數字，最多4位整數，小數點後最多2位")
	public Double getSportMets() {
		return sportMets;
	}
	public void setSportMets(Double sportMets) {
		this.sportMets = sportMets;
	}

	
	@NotNull(message = "卡路里: 請勿空白")
	@Positive(message="卡路里: 請輸入大於0的正整數數字，最多5位整數")
	@Digits(integer = 5, fraction = 0, message = "卡路里: 請輸入大於0的正整數數字，最多5位整數")
	public Integer getSportEstimatedCalories() {
		return sportEstimatedCalories;
	}
	public void setSportEstimatedCalories(Integer sportEstimatedCalories) {
		this.sportEstimatedCalories = sportEstimatedCalories;
	}
	
	
	public String getSportLevel() {
		return sportLevel;
	}
	public void setSportLevel(String sportLevel) {
		this.sportLevel = sportLevel;
	}
	
	
	public String getSportPic() {
		return sportPic;
	}

	public void setSportPic(String sportPic) {
		this.sportPic = sportPic;
	}

	
	public Integer getSportDataStatus() {
		return sportDataStatus;
	}
	public void setSportDataStatus(Integer sportDataStatus) {
		this.sportDataStatus = sportDataStatus;
	}

	
	@NotNull(message = "建立人員: 不可為空")
	public Integer getAdminId() {
		return adminId;
	}
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}


	@Override
	public String toString() {
	    return "SportVO {" +
	            "sportId=" + sportId +
	            ", sportName='" + sportName + '\'' +
	            ", sportDescription='" + sportDescription + '\'' +
	            ", sportMets=" + sportMets +
	            ", sportEstimatedCalories=" + sportEstimatedCalories +
	            ", sportLevel='" + sportLevel + '\'' +
	            ", sportPic='" + sportPic + '\'' +
	            ", sportDataStatus=" + sportDataStatus +
	            ", adminId=" + adminId +
	            '}';
	}
}
