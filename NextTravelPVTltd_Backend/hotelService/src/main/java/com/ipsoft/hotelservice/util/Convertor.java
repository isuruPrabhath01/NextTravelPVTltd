/**
 * Created By Isuru Prabhath
 * Date : 10/14/2023
 * Time : 2:20 AM
 * Project Name : NextTravel_PVT_LtdBackEnd_HotelServer
 */

package com.ipsoft.hotelservice.util;

import com.ipsoft.hotelservice.dto.HotelDTO;
import com.ipsoft.hotelservice.dto.HotelGetDto;
import com.ipsoft.hotelservice.entity.Hotel;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.List;

@Component
public class Convertor {
    @Autowired
    ModelMapper modelMapper;
    public Hotel hotelDtoToHotelEntity(HotelDTO hotelDTO){
        return modelMapper.map(hotelDTO, Hotel.class);
    }
    public HotelGetDto hotelEntityToHotelDto(Hotel hotel){
        HotelGetDto map = modelMapper.map(hotel, HotelGetDto.class);
        map.setHotelImage(decodeImage(hotel.getHotelImage()));
        return map;
    }
    public List<HotelDTO> hotelEntityListToHotelDTOList(List<Hotel> hotels){
        return modelMapper.map(hotels,new TypeToken<List<HotelDTO>>(){}.getType());
    }
    private byte[] decodeImage(String imageString) {
        return Base64.getDecoder().decode(imageString);
    }
}
