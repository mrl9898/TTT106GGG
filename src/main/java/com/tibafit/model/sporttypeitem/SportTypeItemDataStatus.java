package com.tibafit.model.sporttypeitem;

public enum SportTypeItemDataStatus {
	ONLINE("上架", 1), OFFLINE("下架", 0), DELETE("刪除", 2);

	private final String displayName;
	private final Integer codeNum;


	public String getDisplayName() {
		return displayName;
	}
	public Integer getCodeNum() {
		return codeNum;
	}

	
	private SportTypeItemDataStatus(String displayName, Integer codeNum) {
		this.displayName = displayName;
		this.codeNum = codeNum;
	}
	
	public static String getDisplayNameByCodeNum(Integer codeNum) {
		try {
			for (SportTypeItemDataStatus status : SportTypeItemDataStatus.values()) {
				if (status.getCodeNum().equals(codeNum)) {
					return status.getDisplayName();
				}
			}
			throw new IllegalArgumentException("codeNum can't match : " + codeNum);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException("SportDataStatus getDisplayNameByCodeName Error: " + e.getMessage());
		}
	}
}
