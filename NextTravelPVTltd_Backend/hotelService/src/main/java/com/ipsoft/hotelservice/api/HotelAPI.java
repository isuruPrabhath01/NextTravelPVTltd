
package com.ipsoft.hotelservice.api;

import com.ipsoft.hotelservice.dto.HotelDTO;
import com.ipsoft.hotelservice.dto.HotelGetDto;
import com.ipsoft.hotelservice.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.Random;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/api/v1/Hotel")
public class HotelAPI {
    @Autowired
    HotelService hotelService;
    @PostMapping
    public ResponseEntity<String> saveHotel(
            @RequestParam("hotelId")String hotelId,
            @RequestParam("hotelName")String hotelName,
            @RequestParam("hotelRate")String hotelRate,
            @RequestParam("hotelCategory")String hotelCategory,
            @RequestParam("hotelLocation")String hotelLocation,
            @RequestParam("hotelCoordinates")String hotelCoordinates,
            @RequestParam("hotelEmail")String hotelEmail,
            @RequestParam("hotelNumber1")String hotelNumber1,
            @RequestParam("hotelNumber2")String hotelNumber2,
            @RequestParam("petsAllowed")String petsAllowed,
            @RequestParam("hotelFee")double hotelFee,
            @RequestParam("cancellationCriteria")String cancellationCriteria,
            @RequestParam("hotelImage") MultipartFile hotelImage
    ){
        try {
            hotelService.saveHotel(new HotelDTO(
                    hotelId,
                    hotelName,
                    hotelRate,
                    hotelCategory,
                    hotelLocation,
                    hotelCoordinates,
                    hotelEmail,
                    hotelNumber1,
                    hotelNumber2,
                    petsAllowed,
                    hotelFee,
                    cancellationCriteria,
                    Base64.getEncoder().encodeToString(hotelImage.getBytes())
            ));
        } catch (IOException e) {
            throw new RuntimeException("Image Cannot Find..!!");
        }
        return new ResponseEntity<>(hotelId+" Hotel is Saved..!!", HttpStatus.OK);
    }

    @GetMapping(params = "hotelId")
    public ResponseEntity<HotelGetDto> getHotelById(String hotelId){
        return new ResponseEntity<>(hotelService.getHotelById(hotelId),HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> updateHotel(
            @RequestParam("hotelId")String hotelId,
            @RequestParam("hotelName")String hotelName,
            @RequestParam("hotelRate")String hotelRate,
            @RequestParam("hotelCategory")String hotelCategory,
            @RequestParam("hotelLocation")String hotelLocation,
            @RequestParam("hotelCoordinates")String hotelCoordinates,
            @RequestParam("hotelEmail")String hotelEmail,
            @RequestParam("hotelNumber1")String hotelNumber1,
            @RequestParam("hotelNumber2")String hotelNumber2,
            @RequestParam("petsAllowed")String petsAllowed,
            @RequestParam("hotelFee")double hotelFee,
            @RequestParam("cancellationCriteria")String cancellationCriteria,
            @RequestParam("hotelImage") MultipartFile hotelImage
    ){
        try {
            hotelService.updateHotel(new HotelDTO(
                    hotelId,
                    hotelName,
                    hotelRate,
                    hotelCategory,
                    hotelLocation,
                    hotelCoordinates,
                    hotelEmail,
                    hotelNumber1,
                    hotelNumber2,
                    petsAllowed,
                    hotelFee,
                    cancellationCriteria,
                    Base64.getEncoder().encodeToString(hotelImage.getBytes())
            ));
        } catch (IOException e) {
            throw new RuntimeException("Image Cannot Find..!!");
        }
        return new ResponseEntity<>(hotelId+" Hotel is Updated..!!", HttpStatus.OK);
    }

    @DeleteMapping(params = "hotelId")
    public ResponseEntity<String> deleteHotelById(String hotelId){
        hotelService.deleteHotelById(hotelId);
        return new ResponseEntity<>(hotelId+" hotel has been deleted", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<HotelGetDto>> getAllHotels(){
        return new ResponseEntity<>(hotelService.getAllHotels(),HttpStatus.OK);
    }

//    @GetMapping
//    public ResponseEntity<String> testApi(){
//        return new ResponseEntity<>(" Api works..!!", HttpStatus.OK);
//    }
    //get all hotels by package category
    @GetMapping(params = "hotelCategory",path = "/getAllHotelsByPackageCategory")
    public ResponseEntity<List<HotelGetDto>> getAllHotelsByPackageCategory(String hotelCategory){
        return new ResponseEntity<>(hotelService.getAllHotelsByPackageCategory(hotelCategory),HttpStatus.OK);
    }
    //get all hotels by package category,hotelRate,hotelLocation
    @GetMapping(params = {"hotelCategory","hotelRate","hotelLocation"},path = "/getAllHotelsByStarRateAndLocation")
    public ResponseEntity<List<HotelGetDto>> getAllHotelsByStarRateAndLocation(String hotelCategory, String hotelRate, String hotelLocation){
        return new ResponseEntity<>(hotelService.getAllHotelsByHotelCategoryAndStarRateAndLocation(hotelCategory,hotelRate,hotelLocation),HttpStatus.OK);
    }
    //get all hotel ids
    @GetMapping(path = "/getAllHotelIds")
    public ResponseEntity<List<String>> getAllHotelIds(){
        List<String> hotelIds = null;
        List<HotelGetDto> allHotels = hotelService.getAllHotels();
        for(HotelGetDto hotelDTO : allHotels){
            hotelIds.add(hotelDTO.getHotelId());
        }
        return new ResponseEntity<>(hotelIds,HttpStatus.OK);
    }
    //get count of hotels
    @GetMapping(path = "/getCountOfHotels")
    public ResponseEntity<Integer> getCountOfHotels(){
        return new ResponseEntity<>(hotelService.getCountOfHotels(),HttpStatus.OK);
    }
    //get last index of HotelId
    @GetMapping(path = "/getLastId")
    public ResponseEntity<String> getLastId(){
        return new ResponseEntity<>(hotelService.getLastIndex(),HttpStatus.OK);
    }
    @GetMapping(path = "/genarateId")
    public ResponseEntity<String> genarateId(){
        String id=getRandom();
        if (hotelService.existsById(id))
            genarateId();
        return new ResponseEntity<>(id,HttpStatus.OK);
    }
    public String getRandom(){
        Random rand = new Random();
        return Integer.toString(rand.nextInt(10000));
    }
}