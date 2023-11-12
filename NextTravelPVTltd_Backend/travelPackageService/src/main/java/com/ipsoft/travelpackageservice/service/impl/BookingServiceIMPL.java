

package com.ipsoft.travelpackageservice.service.impl;

import com.ipsoft.travelpackageservice.dto.BookingDTO;
import com.ipsoft.travelpackageservice.dto.BookingGetDTO;
import com.ipsoft.travelpackageservice.dto.PackageDTO;
import com.ipsoft.travelpackageservice.entity.Booking;
import com.ipsoft.travelpackageservice.repo.BookingREPO;
import com.ipsoft.travelpackageservice.service.BookingService;
import com.ipsoft.travelpackageservice.service.PackageService;
import com.ipsoft.travelpackageservice.util.Convertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class BookingServiceIMPL implements BookingService {
    @Autowired
    BookingREPO bookingREPO;
    @Autowired
    PackageService packageService;
    @Autowired
    Convertor convertor;
    @Override
    public void saveBooking(BookingDTO bookingDTO) {
        if (bookingREPO.existsById(bookingDTO.getBookingId()))
            throw new RuntimeException(bookingDTO.getBookingId()+" Booking is already exists..!!");
        PackageDTO packageDTO = packageService.getPackageById(bookingDTO.getPackageId());
        bookingREPO.save(convertor.bookingDtoToBookingEntity(bookingDTO,packageDTO));
    }

    @Override
    public BookingGetDTO getBookingById(String bookingId) {
        if (!bookingREPO.existsById(bookingId))
            throw new RuntimeException(bookingId+" Booking cannot find in database..!!");
        return convertor.bookingEntityToBookingGetDto(bookingREPO.findById(bookingId).get());
    }

    @Override
    public List<BookingGetDTO> getAllBooking() {
        return convertor.bookingEntityListToBookingGetDTOList(bookingREPO.findAll());
    }

    @Override
    public void updateBooking(BookingDTO bookingDTO) {
        if (!bookingREPO.existsById(bookingDTO.getBookingId()))
            throw new RuntimeException(bookingDTO.getBookingId()+" Booking Cannot Find..!!");
        PackageDTO packageDTO = packageService.getPackageById(bookingDTO.getPackageId());
        bookingREPO.save(convertor.bookingDtoToBookingEntity(bookingDTO,packageDTO));
    }

    @Override
    public void deleteBooking(String bookingId) {
        if (!bookingREPO.existsById(bookingId))
            throw new RuntimeException(bookingId+" Booking cannot find in database..!!");
        bookingREPO.deleteById(bookingId);
    }

    @Override
    public String getLastIndex() {
        return bookingREPO.getLastIndex();
    }

    @Override
    public BookingGetDTO getBookingByUserId(String userId) {
        return convertor.bookingEntityToBookingGetDto(bookingREPO.findBookingByUserId(userId));
    }

    @Override
    public List<BookingGetDTO> getBookingsByUserId(String userId) {
        return convertor.bookingEntityListToBookingGetDTOList(bookingREPO.findBookingsByUserId(userId));
    }

    @Override
    public int getCountOfBooking() {
        return bookingREPO.getCountOfBookings();
    }

    @Override
    public boolean isValidToUpdate(String bookingId) {
        return getBookingById(bookingId).getCurrentlyDate().plusDays(3).isBefore(LocalDate.now());
    }

    @Override
    public boolean existsById(String id) {
        return bookingREPO.existsById(id);
    }
}
