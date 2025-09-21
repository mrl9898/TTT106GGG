package com.tibafit.model.sport;

import java.math.BigDecimal;

public enum SportLevel {
	JUNIOR("junior", "初階", 0), SENIOR("senior", "中階", 1), ADVANCED("advanced", "高階", 2);

	private final String codeName;
	private final String displayName;
	private final int codeNum;

	public String getCodeName() {
		return codeName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public Integer getCodeNum() {
		return codeNum;
	}

	private SportLevel(String codeName, String displayName, int codeNum) {
		this.codeName = codeName;
		this.displayName = displayName;
		this.codeNum = codeNum;
	}
	
	public static String getDisplayNameByCodeNum(Integer codeNum) {
		try {
			for (SportLevel level : SportLevel.values()) {
				if (level.getCodeNum().equals(codeNum)) {
					return level.getDisplayName();
				}
			}
			throw new IllegalArgumentException("codeNum can't match : " + codeNum);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException("SportDataStatus getDisplayNameByCodeName Error: " + e.getMessage());
		}
	}

	public static String getDisplayNameByCodeName(String codeName) {
		try {
			for (SportLevel level : SportLevel.values()) {
				if (level.getCodeName().equals(codeName)) {
					return level.getDisplayName();
				}
			}
			throw new IllegalArgumentException("codeName can't match : " + codeName);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException("SportLevel getDisplayNameByCodeName Error: " + e.getMessage());
		}
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