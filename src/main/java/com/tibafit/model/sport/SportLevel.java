package com.tibafit.model.sport;

import java.math.BigDecimal;

public enum SportLevel {
	JUNIOR("junior", "初階"), SENIOR("senior", "中階"), ADVANCED("advanced", "高階");

	private final String codeName;
	private final String displayName;

	public String getCodeName() {
		return codeName;
	}
	public String getDisplayName() {
		return displayName;
	}

	private SportLevel(String codeName, String displayName) {
		this.codeName = codeName;
		this.displayName = displayName;
	}


	public static String getDisplayNameByCodeName(String codeName) {
    	String defultStr = String.valueOf(codeName);
    	
		for (SportLevel level : SportLevel.values()) {
			if (level.getCodeName().equals(codeName)) {
				return level.getDisplayName();
			}
		}
		
		return defultStr;
	}

	public static String judgeSportLevel(BigDecimal sportMets) {
		BigDecimal ZERO = new BigDecimal("0");
		BigDecimal LOWER = new BigDecimal("3");
		BigDecimal UPPER = new BigDecimal("6");
		try {
			if (sportMets.compareTo(ZERO) > 0 && sportMets.compareTo(LOWER) < 0) {
				return JUNIOR.getCodeName();
			} else if (sportMets.compareTo(LOWER) >= 0 && sportMets.compareTo(UPPER) < 0) {
				return SENIOR.getCodeName();
			} else if (sportMets.compareTo(UPPER) >= 0) {
				return ADVANCED.getCodeName();
			} else {
				throw new IllegalArgumentException(
						"sportMets can't be judge : " + sportMets);
			}
		} catch (IllegalArgumentException e) {
			throw new RuntimeException("SportLevel judgeSportLevel Error: " + e.getMessage());
		}
	}
}