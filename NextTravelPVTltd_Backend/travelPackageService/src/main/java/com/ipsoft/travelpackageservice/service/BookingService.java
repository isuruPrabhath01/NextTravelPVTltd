

package com.ipsoft.travelpackageservice.service;

import com.ipsoft.travelpackageservice.dto.BookingDTO;
import com.ipsoft.travelpackageservice.dto.BookingGetDTO;

import java.util.List;

public interface BookingService {
    void saveBooking(BookingDTO bookingDTO);
    BookingGetDTO getBookingById(String bookingId);
    List<BookingGetDTO> getAllBooking();
    void updateBooking(BookingDTO bookingDTO);
    void deleteBooking(String bookingId);

    String getLastIndex();
    BookingGetDTO getBookingByUserId(String userId);

    List<BookingGetDTO> getBookingsByUserId(String userId);
    int getCountOfBooking();
    boolean isValidToUpdate(String bookingId);
    boolean existsById(String id);
}
