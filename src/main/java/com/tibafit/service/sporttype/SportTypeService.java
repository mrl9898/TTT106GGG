package com.tibafit.service.sporttype;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tibafit.dto.sporttype.SportTypeRequestDTO;
import com.tibafit.dto.sporttype.SportTypeResponseDTO;
import com.tibafit.model.sporttype.SportTypeConverter;
import com.tibafit.model.sporttype.SportTypeVO;
import com.tibafit.repository.sporttype.SportTypeRepository;
import com.tibafit.repository.sporttypeitem.SportTypeItemRepository;

@Service
@Transactional
public class SportTypeService implements SportTypeService_interface {

    @Autowired
    private SportTypeRepository sportTypeRepo;
    
    @Autowired
    private SportTypeItemRepository sportTypeItemRepo;

//    @Override
//    public SportTypeVO insertSportType(SportTypeVO vo) {
//        SportTypeVO saved = sportTypeRepo.save(vo);
//        return saved;
//    }
    @Override
    public void insertSportTypes(List<SportTypeRequestDTO> dtos) {
    	List<SportTypeVO> vos = SportTypeConverter.toNewVoList(dtos);
        sportTypeRepo.saveAll(vos);
    }

//    @Override
//    public SportTypeVO updateSportType(SportTypeVO vo) {
//        SportTypeVO updated = sportTypeRepo.save(vo);
//        return updated;
//    }
    @Override
    public void updateSportTypes(List<SportTypeRequestDTO> dtos) {
    	List<Integer> typeIds = new ArrayList<>();
    	for(SportTypeRequestDTO dto : dtos) {
    		if(dto.getSportTypeId() != null){
    			typeIds.add(dto.getSportTypeId());
    		}
    	}
    	
    	// xxxxxx
    	System.out.println("SportTypeService dtos: " + dtos);
    	
    	// PO
    	List<SportTypeVO> oriVos = sportTypeRepo.findBySportTypeIdIn(typeIds);
    	
    	// xxxxxx
    	System.out.println("SportTypeService oriVos: " + oriVos);
    	
    	List<SportTypeVO> vos = SportTypeConverter.toUpdateVoList(oriVos, dtos);
    	
        sportTypeRepo.saveAll(vos);
    }
    
    
    @Override
    @Transactional(readOnly = true)
    public Boolean isExistBySportTypeName(String sportTypeName) {
        Boolean isExist = sportTypeRepo.existsBySportTypeName(sportTypeName);
        return isExist;
    }

    
    @Override
    @Transactional(readOnly = true)
    public SportTypeResponseDTO getBySportTypeId(Integer sportTypeId) {
        Optional<SportTypeVO> vo = sportTypeRepo.findBySportTypeId(sportTypeId);
        SportTypeResponseDTO dto = SportTypeConverter.toDTO(vo.orElse(null));
        return dto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<SportTypeResponseDTO> getBySportTypeIds(List<Integer> sportTypeIds) {
        List<SportTypeVO> vos = sportTypeRepo.findBySportTypeIdIn(sportTypeIds);
        List<SportTypeResponseDTO> dtos = SportTypeConverter.toDtoList(vos);
        return dtos;
    }

    
    @Override
    @Transactional(readOnly = true)
    public SportTypeResponseDTO getBySportTypeIdAndSportTypeDataStatuses(Integer sportTypeId, List<Integer> statuses) {
        Optional<SportTypeVO> vo = sportTypeRepo.findBySportTypeIdAndSportTypeDataStatusIn(sportTypeId, statuses);
        SportTypeResponseDTO dto = SportTypeConverter.toDTO(vo.orElse(null));
        return dto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<SportTypeResponseDTO> getBySportTypeIdsAndSportTypeDataStatuses(List<Integer> sportTypeIds, List<Integer> statuses) {
        List<SportTypeVO> vos = sportTypeRepo.findBySportTypeIdInAndSportTypeDataStatusIn(sportTypeIds, statuses);
        List<SportTypeResponseDTO> dtos = SportTypeConverter.toDtoList(vos);
        return dtos;
    }

    @Override
    @Transactional(readOnly = true)
    public List<SportTypeResponseDTO> getBySportTypeDataStatuses(List<Integer> statuses) {
        List<SportTypeVO> vos = sportTypeRepo.findBySportTypeDataStatusIn(statuses);
        List<SportTypeResponseDTO> dtos = SportTypeConverter.toDtoList(vos);
        return dtos;
    }

    
    @Override
    public Integer updateSportTypeDataStatusBySportTypeIds(Integer dataStatus, List<Integer> sportTypeIds) {
        Integer affectNumOfType = sportTypeRepo.updateSportTypeDataStatusBySportTypeIds(dataStatus, sportTypeIds);
        
        // 一併更新該分類詳細狀態
        if(affectNumOfType > 0) {
        	sportTypeItemRepo.updateSportTypeItemDataStatusBySportTypeIds(dataStatus, sportTypeIds);
        }
        
        return affectNumOfType;
    }
}
