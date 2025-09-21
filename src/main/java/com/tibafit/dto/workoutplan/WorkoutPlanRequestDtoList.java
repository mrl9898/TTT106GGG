package com.tibafit.dto.workoutplan;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class WorkoutPlanRequestDtoList {
	    private List<WorkoutPlanRequestDTO> list;
    
	    @NotNull
	    @Size(min = 1, message = "至少要有一筆計畫")
	    @Valid
		public List<WorkoutPlanRequestDTO> getList() {
			return list;
		}

		public void setList(List<WorkoutPlanRequestDTO> list) {
			this.list = list;
		}
}
