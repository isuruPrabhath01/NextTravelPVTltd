

package com.ipsoft.hotelservice.service.impl;

import com.ipsoft.hotelservice.dto.HotelDTO;
import com.ipsoft.hotelservice.dto.HotelGetDto;
import com.ipsoft.hotelservice.repo.HotelREPO;
import com.ipsoft.hotelservice.service.HotelService;
import com.ipsoft.hotelservice.util.Convertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class HotelServiceIMPL implements HotelService {
    @Autowired
    HotelREPO hotelREPO;
    @Autowired
    Convertor convertor;
    @Override
    public void saveHotel(HotelDTO hotelDTO) {
        if (hotelREPO.existsById(hotelDTO.getHotelId()))
            throw new RuntimeException(hotelDTO.getHotelId()+" Hotel Id already exists..!!");
        hotelREPO.save(convertor.hotelDtoToHotelEntity(hotelDTO));
    }

    @Override
    public HotelGetDto getHotelById(String hotelId) {
        if (!hotelREPO.existsById(hotelId))
            throw new RuntimeException(hotelId+" Hotel is not exists..!!");
        return convertor.hotelEntityToHotelDto(hotelREPO.findById(hotelId).get());
    }

    @Override
    public void updateHotel(HotelDTO hotelDTO) {
        if (!hotelREPO.existsById(hotelDTO.getHotelId()))
            throw new RuntimeException(hotelDTO.getHotelId()+" Hotel is not exists..!!");
        hotelREPO.save(convertor.hotelDtoToHotelEntity(hotelDTO));
    }

    @Override
    public void deleteHotelById(String hotelId) {
        if (!hotelREPO.existsById(hotelId))
            throw new RuntimeException(hotelId+" Hotel is not exists..!!");
        hotelREPO.deleteById(hotelId);
    }

    @Override
    public List<HotelGetDto> getAllHotels() {
        return hotelREPO.findAll().stream().map(hotel -> convertor.hotelEntityToHotelDto(hotel)).collect(Collectors.toList());
    }

    @Override
    public List<HotelGetDto> getAllHotelsByPackageCategory(String packageCategory) {
        if(!hotelREPO.existsByHotelCategory(packageCategory))
            throw new RuntimeException(packageCategory+" Package Category not exists");
        return hotelREPO.findAllByHotelCategory(packageCategory).stream().map(hotel -> convertor.hotelEntityToHotelDto(hotel)).collect(Collectors.toList());
    }

    @Override
    public List<HotelGetDto> getAllHotelsByHotelCategoryAndStarRateAndLocation(String hotelCategory, String starRate, String location) {
        if(!hotelREPO.existsByHotelRate(starRate))
            throw new RuntimeException(starRate+" Star Rate Category not exists");
        if(!hotelREPO.existsByHotelLocation(location))
            throw new RuntimeException(location+" location not exists");
        return hotelREPO.findAllByHotelCategoryAndHotelRateAndHotelLocation(hotelCategory,starRate,location).stream().map(hotel -> convertor.hotelEntityToHotelDto(hotel)).collect(Collectors.toList());
    }

    @Override
    public String getLastIndex() {
        return hotelREPO.getLastIndex();
    }

    @Override
    public int getCountOfHotels() {
        return hotelREPO.getCountOfHotels();
    }

    @Override
    public boolean existsById(String id) {
        return hotelREPO.existsById(id);
    }
}
