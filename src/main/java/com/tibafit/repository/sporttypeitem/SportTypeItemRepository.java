package com.tibafit.repository.sporttypeitem;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tibafit.model.sporttypeitem.SportTypeItemVO;

@Repository
public interface SportTypeItemRepository extends JpaRepository<SportTypeItemVO, Integer> {
    
	Boolean existsBySportTypeIdAndSportId(Integer sportTypeId, Integer sportId);
    
    
    @EntityGraph(attributePaths = { 
    	"sportTypeVO",
       	"sportVO"
    })
    Optional<SportTypeItemVO> findBySportTypeItemId(Integer sportTypeItemId);
    
    
    @EntityGraph(attributePaths = { 
    	"sportTypeVO",
       	"sportVO"
    })
    List<SportTypeItemVO> findBySportTypeItemIdIn(List<Integer> sportTypeItemIds);

    
    @EntityGraph(attributePaths = { 
    	"sportTypeVO",
       	"sportVO"
    })
    List<SportTypeItemVO> findBySportTypeId(Integer sportTypeId);
    
    
    @EntityGraph(attributePaths = { 
    	"sportTypeVO",
       	"sportVO"
    })
    List<SportTypeItemVO> findBySportTypeIdIn(List<Integer> sportTypeIds);

    
    @EntityGraph(attributePaths = { 
    	"sportTypeVO",
       	"sportVO"
    })
    Optional<SportTypeItemVO> findBySportTypeIdAndSportId(Integer sportTypeId, Integer sportId);
    
    
    @EntityGraph(attributePaths = { 
    	"sportTypeVO",
       	"sportVO"
    })
    Optional<SportTypeItemVO> findBySportTypeItemIdAndSportTypeItemDataStatusIn(Integer sportTypeItemId, List<Integer> SportTypeDataItemStatuses);

    
    @EntityGraph(attributePaths = { 
    	"sportTypeVO",
       	"sportVO"
    })
    List<SportTypeItemVO> findBySportTypeIdAndSportTypeItemDataStatusIn(Integer sportTypeId, List<Integer> SportTypeDataItemStatuses);

    
    @EntityGraph(attributePaths = { 
    	"sportTypeVO",
       	"sportVO"
    })
    Optional<SportTypeItemVO> findBySportTypeIdAndSportIdAndSportTypeItemDataStatusIn(Integer sportTypeId, Integer sportId, List<Integer> SportTypeDataItemStatuses);
    
    
    // TODO: update沒用到，後來決定使用deleteBySportTypeItemIdIn
	@Modifying
	@Query(value = "UPDATE sport_type_item SET sport_type_item_data_status = :dataStatus WHERE sport_type_item_id IN :sportTypeItemIds", nativeQuery = true)
	public Integer updateSportTypeItemDataStatusBySportTypeItemIds(@Param("dataStatus") Integer dataStatus, @Param("sportTypeItemIds") List<Integer> sportTypeItemIds);
	
	@Modifying
	@Query(value = "UPDATE sport_type_item SET sport_type_item_data_status = :dataStatus WHERE sport_type_id IN :sportTypeIds", nativeQuery = true)
	public Integer updateSportTypeItemDataStatusBySportTypeIds(@Param("dataStatus") Integer dataStatus, @Param("sportTypeIds") List<Integer> sportTypeIds);
	
	@Modifying
	@Query(value = "UPDATE sport_type_item SET sport_type_item_data_status = :dataStatus WHERE sport_id IN :sportIds", nativeQuery = true)
	public Integer updateSportTypeItemDataStatusBySportIds(@Param("dataStatus") Integer dataStatus, @Param("sportIds") List<Integer> sportIds);


	public void deleteBySportTypeItemIdIn(List<Integer> sportTypeRecordIds);
}
