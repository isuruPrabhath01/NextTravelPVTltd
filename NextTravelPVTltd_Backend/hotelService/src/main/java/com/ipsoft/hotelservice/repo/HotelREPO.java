

package com.ipsoft.hotelservice.repo;

import com.ipsoft.hotelservice.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HotelREPO extends JpaRepository<Hotel, String> {
    List<Hotel> findAllByHotelCategory(String packageCategory);
    List<Hotel> findAllByHotelCategoryAndHotelRateAndHotelLocation(String hotelCategory, String starRate,String hotelLocation);
    boolean existsByHotelCategory(String packageCategory);
    boolean existsByHotelRate(String starRate);
    boolean existsByHotelLocation(String hotelLocation);

    @Query(value = "SELECT COUNT(*) FROM Hotels",nativeQuery=true)
    int getCountOfHotels();

    @Query(value = "SELECT hotelId FROM Hotel ORDER BY hotelId DESC LIMIT 1",nativeQuery = true)
    String getLastIndex();
}
