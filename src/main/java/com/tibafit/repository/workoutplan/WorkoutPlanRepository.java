package com.tibafit.repository.workoutplan;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tibafit.model.workoutplan.WorkoutPlanVO;

@Repository
public interface WorkoutPlanRepository extends JpaRepository<WorkoutPlanVO, Integer> {
	
	@EntityGraph(attributePaths = {
	    "userVO",
	    "sportVO",
	    "customSportVO",
	    "workoutPlanRecordVOs"
	})
	public Optional<WorkoutPlanVO> findByWorkoutPlanId(Integer workoutPlanId);
	
	@EntityGraph(attributePaths = {
	    "userVO",
	    "sportVO",
	    "customSportVO",
	    "workoutPlanRecordVOs"
	})
	public List<WorkoutPlanVO> findByWorkoutPlanIdIn(List<Integer> workoutPlanIds);

	// 日期區間查詢 (區間最長一個月)
	@EntityGraph(attributePaths = {
	    "userVO",
	    "sportVO",
	    "customSportVO",
	    "workoutPlanRecordVOs"
	})
	public List<WorkoutPlanVO> findByUserIdAndWorkoutPlanDateBetweenAndWorkoutPlanDataStatusIn(Integer userId, LocalDate startDate, LocalDate endDate, List<Integer> statuses);
	

	// 單日期查詢
	@EntityGraph(attributePaths = {
	    "userVO",
	    "sportVO",
	    "customSportVO",
	    "workoutPlanRecordVOs"
	})
	public List<WorkoutPlanVO> findByUserIdAndWorkoutPlanDateAndWorkoutPlanDataStatusIn(Integer userId,
			LocalDate workoutPlanDate, List<Integer> statuses);
	
	// 單日期查詢
	@EntityGraph(attributePaths = {
	    "userVO",
	    "sportVO",
	    "customSportVO",
	    "workoutPlanRecordVOs"
	})
	public Page<WorkoutPlanVO> findByUserIdAndWorkoutPlanDateAndWorkoutPlanDataStatusIn(Integer userId,
			LocalDate workoutPlanDate, List<Integer> statuses, Pageable pageable);

	@Modifying
	@Query(value = "UPDATE workout_plan SET workout_plan_data_status = :status WHERE workout_plan_id IN :ids", nativeQuery = true)
	public Integer updateWorkoutPlanDataStatusByIds(@Param("status") Integer status, @Param("ids") List<Integer> ids);

	
//	@Query(value = "SELECT * FROM workout_plan WHERE user_id = :user_id AND workout_plan_date = :workout_plan_date AND workout_plan_data_status IN :statues", nativeQuery = true)
//	List<WorkoutPlanVO> findByUserIdWorkoutPlanDateWorkoutPlanDataStatusIn(@Param("user_id") Integer userId,
//			@Param("workout_plan_date") String workoutPlanDate, @Param("statues") List<Integer> statuses);
}
