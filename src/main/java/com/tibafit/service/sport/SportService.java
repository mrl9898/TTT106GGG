package com.tibafit.service.sport;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tibafit.dto.sport.SportRequestDTO;
import com.tibafit.model.sport.SportConverter;
import com.tibafit.model.sport.SportDataStatus;
import com.tibafit.model.sport.SportLevel;
import com.tibafit.model.sport.SportVO;
import com.tibafit.repository.sport.SportRepository;
import com.tibafit.repository.sporttypeitem.SportTypeItemRepository;

@Service("sportService")
public class SportService implements SportService_Interface {

	@Autowired
	private SportRepository sportRepo;
	
    @Autowired
    private SportTypeItemRepository sportTypeItemRepo;

	@Override
	@Transactional(readOnly = true)
	public List<SportVO> getSportAll() {
		return sportRepo.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public SportVO getSportByPrimaryKey(Integer sportId) {
		Optional<SportVO> opt = sportRepo.findById(sportId); 
		return opt.orElse(null);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<SportVO> getSportByDataStatuses(List<Integer> sportDataStatuses) {
		return sportRepo.findBySportDataStatuses(sportDataStatuses);
	}
	
//	@Override
//	public List<SportVO> getSportByNameFuzzy(String keyword) {
//		return sportRepo.getSportByNameFuzzy(keyword);
//	}

	@Override
	public List<SportDataStatus> getSportDataStatusOptions() {
		List<SportDataStatus> tempList = new ArrayList<>();
		
		for (SportDataStatus level : SportDataStatus.values()) {

			if(level.getCodeNum() == 0 || level.getCodeNum() == 1) {
				tempList.add(level);
			}
		}
		return tempList;
	}

	@Override
	public List<SportDataStatus> getSportDataStatusNeed(List<Integer> needStatusCodeNums) {
		List<SportDataStatus> tempList = new ArrayList<>();
		
		for (SportDataStatus level : SportDataStatus.values()) {
			int index = needStatusCodeNums.indexOf(level.getCodeNum());
			if(index != -1) {
				tempList.add(level);
			}
		}
		return tempList;
	}
	
	@Override
	@Transactional
	public void insertSport(SportRequestDTO dto) {		
			SportVO vo = SportConverter.toVO(dto);			
			sportRepo.save(vo);
	}
	
	@Override
	@Transactional
	public void insertSportMultiple(List<SportRequestDTO> dtos) {		
			List<SportVO> vos = SportConverter.toVoList(dtos);			
			sportRepo.saveAll(vos);
	}

	@Override
	@Transactional
	public void updateSport(SportRequestDTO dto) {		
		SportVO vo = SportConverter.toVO(dto);		
		sportRepo.save(vo);
	}
	
	@Override
	@Transactional
	public void updateSportMultiple(List<SportRequestDTO> dtos) {		
			List<SportVO> vos = SportConverter.toVoList(dtos);			
			sportRepo.saveAll(vos);
	}

	@Override
	@Transactional
	public Integer updateSportDataStatusByIds(Integer targetStatus, List<Integer> sportIds) {
		if (sportIds == null || sportIds.isEmpty()) {
			return 0;
		}
		
		Integer affectNumOfSport = sportRepo.updateSportDataStatusByIds(targetStatus, sportIds);
		
        // 一併更新該分類詳細狀態
        if(affectNumOfSport > 0) {
        	sportTypeItemRepo.updateSportTypeItemDataStatusBySportIds(targetStatus, sportIds);
        }
		return affectNumOfSport;
	}
}
