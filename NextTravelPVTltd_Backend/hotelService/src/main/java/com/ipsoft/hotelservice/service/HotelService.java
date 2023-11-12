
package com.ipsoft.hotelservice.service;

import com.ipsoft.hotelservice.dto.HotelDTO;
import com.ipsoft.hotelservice.dto.HotelGetDto;

import java.util.List;

public interface HotelService {
    void saveHotel(HotelDTO hotelDTO);
    HotelGetDto getHotelById(String hotelId);
    void updateHotel(HotelDTO hotelDTO);
    void deleteHotelById(String hotelId);

    List<HotelGetDto> getAllHotels();
    List<HotelGetDto> getAllHotelsByPackageCategory(String packageCategory);
    List<HotelGetDto> getAllHotelsByHotelCategoryAndStarRateAndLocation(String hotelCategory, String starRate,String location);
    String getLastIndex();
    int getCountOfHotels();

    boolean existsById(String id);

}
