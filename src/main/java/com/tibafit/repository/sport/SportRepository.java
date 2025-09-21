package com.tibafit.repository.sport;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tibafit.model.sport.SportDataStatus;
import com.tibafit.model.sport.SportVO;

public interface SportRepository extends JpaRepository<SportVO, Integer> {
	static final int DATA_STATUS_DELETE = SportDataStatus.DELETE.getCodeNum();
	
//	List<SportVO> findAll(Sort sort);

//	Page<SportVO> findAll(Pageable pageable);

//	Optional<SportVO> findById(Integer sportId);
	
	
	@Query(value = "SELECT * FROM sport WHERE sport_data_status IN :statuses ORDER BY sport_id DESC", nativeQuery = true)
	List<SportVO> findBySportDataStatuses(@Param("statuses") List<Integer> statuses);
	
	
	@Query(
			value = "SELECT * FROM sport WHERE sport_data_status IN :statuses ORDER BY sport_id DESC", 
			countQuery="SELECT * FROM sport WHERE sport_data_status IN :statuses",
			nativeQuery = true
			)
	Page<SportVO> findBySportDataStatusesPage(@Param("statuses") List<Integer> statuses, Pageable pageable);
	
	
	// 新增/更新
	@Modifying
//	@Query(value="INSERT INTO sport ( sport_name, sport_description, sport_mets, sport_estimated_calories, sport_level, sport_pic, sport_data_status, admin_id ) VALUES  (?, ?, ?, ?, ?, ?, ?, ?)", nativeQuery=true )
	SportVO save(SportVO sportVO);
	

//	List<SportVO> saveAll(List<SportVO> sportVOs);
    
	
    @Modifying
    @Query(value = "UPDATE sport SET sport_data_status = :sport_data_status WHERE sport_id IN :ids", nativeQuery = true)
    Integer updateSportDataStatusByIds(@Param("sport_data_status") Integer status, @Param("ids") List<Integer> ids);
}
