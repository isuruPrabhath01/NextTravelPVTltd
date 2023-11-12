

package com.ipsoft.guideservice.service.impl;

import com.ipsoft.guideservice.dto.GuideDTO;
import com.ipsoft.guideservice.repo.GuideREPO;
import com.ipsoft.guideservice.service.GuideService;
import com.ipsoft.guideservice.util.Convertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class GuideServiceIMPL implements GuideService {
    @Autowired
    GuideREPO guideREPO;
    @Autowired
    Convertor convertor;

    @Override
    public void saveGuide(GuideDTO guideDTO) {
        if (guideREPO.existsById(guideDTO.getGuideId()))
            throw new RuntimeException(guideDTO.getGuideId()+" GuideId is already exists..!!");
        guideREPO.save(convertor.guideDtoToGuideEntity(guideDTO));
    }

    @Override
    public void updateGuide(GuideDTO guideDTO) {
        if (!guideREPO.existsById(guideDTO.getGuideId()))
            throw new RuntimeException(guideDTO.getGuideId()+" GuideId is cannot find..!!");
        guideREPO.save(convertor.guideDtoToGuideEntity(guideDTO));
    }

    @Override
    public void deleteGuide(String guideId) {
        if (!guideREPO.existsById(guideId))
            throw new RuntimeException(guideId+" GuideId is cannot find..!!");
        guideREPO.deleteById(guideId);
    }

    @Override
    public GuideDTO getGuideById(String guideId) {
        if (!guideREPO.existsById(guideId))
            throw new RuntimeException(guideId+" GuideId is cannot find..!!");
        return convertor.guideEntityToGuideDto(guideREPO.findByGuideId(guideId));
    }

    @Override
    public List<GuideDTO> getAllGuides() {
        return convertor.guideEntityListToGuideDTOList(guideREPO.findAll());
    }

    @Override
    public boolean existById(String id) {
        return guideREPO.existsById(id);
    }

}
