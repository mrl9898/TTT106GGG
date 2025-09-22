package com.tibafit.model.workoutplanrecord;

public enum WorkoutPlanRecordCalorieCountMethoed {
    RAWCOUNT("估算"), KGCOUNT("體重計算"), FILLIN("自填");

    private final String displayName;

    private WorkoutPlanRecordCalorieCountMethoed(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() { 
    	return displayName; 
    }
}
