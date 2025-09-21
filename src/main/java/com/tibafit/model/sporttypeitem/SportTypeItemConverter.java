package com.tibafit.model.sporttypeitem;

import java.util.ArrayList;
import java.util.List;

import com.tibafit.dto.sport.SportResponseDTO;
import com.tibafit.dto.sporttypeitem.SportTypeItemRequestDTO;
import com.tibafit.dto.sporttypeitem.SportTypeItemResponseDTO;
import com.tibafit.model.sport.SportConverter;

public class SportTypeItemConverter {

    public static SportTypeItemVO toNewVO(SportTypeItemRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        
        SportTypeItemVO vo = new SportTypeItemVO();
        
        vo.setSportTypeId(dto.getSportTypeId());
        vo.setSportId(dto.getSportId());
        vo.setSportTypeItemDataStatus(SportTypeItemDataStatus.ONLINE.getCodeNum());
        
        return vo;
    }

    // TODO: 應該不會有update
    public static SportTypeItemVO toUpdateVO(SportTypeItemVO ori, SportTypeItemRequestDTO dto) {
        if (ori == null || dto == null) {
            return null;
        }
        
        SportTypeItemVO vo = new SportTypeItemVO();
        
        vo.setSportTypeItemId(ori.getSportTypeItemId());
        vo.setSportTypeId(ori.getSportTypeId());
        vo.setSportId(ori.getSportId());
        vo.setSportTypeItemDataStatus(ori.getSportTypeItemDataStatus());
        
        if (dto.getSportTypeItemId() != null) {
            vo.setSportTypeItemId(dto.getSportTypeItemId());
        }
        if (dto.getSportTypeId() != null) {
            vo.setSportTypeId(dto.getSportTypeId());
        }
        if (dto.getSportId() != null) {
            vo.setSportId(dto.getSportId());
        }
        if (dto.getSportTypeItemDataStatus() != null) {
            vo.setSportTypeItemDataStatus(dto.getSportTypeItemDataStatus());
        }
        
        return vo;
    }

    public static SportTypeItemResponseDTO toDTO(SportTypeItemVO vo) {
        if (vo == null) {
            return null;
        }
        
        SportTypeItemResponseDTO dto = new SportTypeItemResponseDTO();
        
        dto.setSportTypeItemId(vo.getSportTypeItemId());
        dto.setSportTypeId(vo.getSportTypeId());
        dto.setSportId(vo.getSportId());
        
        dto.setSportTypeItemDataStatus(vo.getSportTypeItemDataStatus());
        dto.setSportTypeItemDataStatusText(SportTypeItemDataStatus.getDisplayNameByCodeNum(vo.getSportTypeItemDataStatus()));
        
        dto.setCreateDatetime(vo.getCreateDatetime());
        dto.setUpdateDatetime(vo.getUpdateDatetime());
        
    	SportResponseDTO sportResponseDTO = SportConverter.toDTO(vo.getSportVO());
    	dto.setSportResponseDTO(sportResponseDTO);
    	
        return dto;
    }
    
    
    public static List<SportTypeItemVO> toNewVoList(List<SportTypeItemRequestDTO> dtos) {
        List<SportTypeItemVO> list = new ArrayList<>();
        
        if (dtos == null || dtos.isEmpty()) {
            return list;
        }
        
        for (SportTypeItemRequestDTO dto : dtos) {
            SportTypeItemVO vo = toNewVO(dto);
            if (vo != null) {
                list.add(vo);
            }
        }
        
        return list;
    }
    
    // TODO: 應該不會有update
    public static List<SportTypeItemVO> toUpdateVoList(List<SportTypeItemVO> oriVos, List<SportTypeItemRequestDTO> dtos) {
        List<SportTypeItemVO> list = new ArrayList<>();
        
        if (oriVos == null || oriVos.isEmpty() || dtos == null || dtos.isEmpty()) {
            return list;
        }
        
        // 兩個 List 對應順序應相同 (一對一)
        int size = Math.min(oriVos.size(), dtos.size());
        for (int i = 0; i < size; i++) {
            SportTypeItemVO vo = toUpdateVO(oriVos.get(i), dtos.get(i));
            if (vo != null) {
                list.add(vo);
            }
        }
        
        return list;
    }

    public static List<SportTypeItemResponseDTO> toDtoList(List<SportTypeItemVO> vos) {
        List<SportTypeItemResponseDTO> list = new ArrayList<>();
        if (vos == null || vos.isEmpty()) {
            return list;
        }
        for (SportTypeItemVO vo : vos) {
            list.add(toDTO(vo));
        }
        return list;
    }
}
