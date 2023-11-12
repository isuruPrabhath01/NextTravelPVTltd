
package com.ipsoft.travelpackageservice.api;

import com.ipsoft.travelpackageservice.dto.BookingDTO;
import com.ipsoft.travelpackageservice.dto.BookingGetDTO;
import com.ipsoft.travelpackageservice.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/v1/booking")
public class BookingAPI {
    @Autowired
    BookingService bookingService;

    @PostMapping
    public ResponseEntity<String> saveBooking(@RequestBody BookingDTO bookingDTO){
        System.out.println(bookingDTO.getBookingId());
        bookingService.saveBooking(bookingDTO);
        return new ResponseEntity<>(bookingDTO.getBookingId()+" Booking is saved..!!", HttpStatus.OK);
    }
    @GetMapping(params = "bookingId")
    public ResponseEntity<BookingGetDTO> getBookingById(String bookingId){
        return new ResponseEntity<>(bookingService.getBookingById(bookingId),HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<BookingGetDTO>> getAllBookings(){
        return new ResponseEntity<>(bookingService.getAllBooking(),HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<String> updateBooking(@RequestBody BookingDTO bookingDTO){
        if (bookingService.isValidToUpdate(bookingDTO.getBookingId())) {
            bookingService.updateBooking(bookingDTO);
            return new ResponseEntity<>(bookingDTO.getBookingId() + " Booking is Updated..!!", HttpStatus.OK);
        }else
            throw new RuntimeException(bookingDTO.getBookingId()+" Booking is not valid to update..!!");

    }
    @DeleteMapping(params = "bookingId")
    public ResponseEntity<String> deleteBooking(String bookingId){
        bookingService.deleteBooking(bookingId);
        return new ResponseEntity<>(bookingId+" Booking is Deleted..!!",HttpStatus.OK);
    }
    //get last index of Booking id
    @GetMapping(path = "/getLastId")
    public ResponseEntity<String> getLastId(){
        return new ResponseEntity<>(bookingService.getLastIndex(),HttpStatus.OK);
    }
    //get booking by user id
    @GetMapping(params = "userId",path = "/getBookingByUserId")
    public ResponseEntity<BookingGetDTO> getBookingByUserId(String userId){
        return new ResponseEntity<>(bookingService.getBookingByUserId(userId),HttpStatus.OK);
    }
    //get booking ids using user id
    @GetMapping(params = "userId",path = "/getBookingIdsByUserId")
    public ResponseEntity<List<String>> getBookingIdsByUserId(String userId){
        List<String> bookingIds = null;
        List<BookingGetDTO> bookingsByUserId = bookingService.getBookingsByUserId(userId);
        for(BookingGetDTO bookingGetDTO : bookingsByUserId){
            bookingIds.add(bookingGetDTO.getBookingId());
        }
        return new ResponseEntity<>(bookingIds,HttpStatus.OK);
    }
    //get count of Booking
    @GetMapping(path = "/getCountOfBooking")
    public ResponseEntity<Integer> getCountOfBooking(){
        return new ResponseEntity<>(bookingService.getCountOfBooking(),HttpStatus.OK);
    }
    @GetMapping(path = "/genarateId")
    public ResponseEntity<String> genarateId(){
        String id=getRandom();
        if (bookingService.existsById(id))
            genarateId();
        return new ResponseEntity<>(id,HttpStatus.OK);
    }
    public String getRandom(){
        Random rand = new Random();
        return Integer.toString(rand.nextInt(10000));
    }
}
