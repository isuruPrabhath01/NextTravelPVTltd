/**
 * Created By Isuru Prabhath
 * Date : 10/25/2023
 * Time : 3:33 AM
 * Project Name : GuideService
 */

package com.ipsoft.guideservice.util;

import com.ipsoft.guideservice.dto.GuideDTO;
import com.ipsoft.guideservice.entity.Guide;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Convertor {
    @Autowired
    ModelMapper modelMapper;

    public Guide guideDtoToGuideEntity(GuideDTO guideDTO) {
        return modelMapper.map(guideDTO, Guide.class);
    }
    public GuideDTO guideEntityToGuideDto(Guide byGuideId) {
        return modelMapper.map(byGuideId, GuideDTO.class);
    }
    public List<GuideDTO> guideEntityListToGuideDTOList(List<Guide> guides){
        return modelMapper.map(guides,new TypeToken<List<GuideDTO>>(){}.getType());
    }
}
