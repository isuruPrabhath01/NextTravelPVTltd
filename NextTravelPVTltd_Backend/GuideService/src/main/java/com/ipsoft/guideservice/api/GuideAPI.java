

package com.ipsoft.guideservice.api;

import com.ipsoft.guideservice.dto.GuideDTO;
import com.ipsoft.guideservice.service.GuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/v1/guide")
public class GuideAPI {
    @Autowired
    GuideService guideService;

    @PostMapping("/saveGuide")
    public ResponseEntity<String> saveGuide(
            @RequestParam("guideId")String guideId,
            @RequestParam("guideName")String guideName,
            @RequestParam("address")String address,
            @RequestParam("gender")String gender,
            @RequestParam("number")String number,
            @RequestParam("experience")String experience,
            @RequestParam("manDayValue")double manDayValue,
            @RequestParam("guideImage")MultipartFile guideImage,
            @RequestParam("nicImage")MultipartFile nicImage,
            @RequestParam("guideIDImage")MultipartFile guideIDImage
    ){
        try {
            guideService.saveGuide(
                    new GuideDTO(
                            guideId,
                            guideName,
                            address,
                            gender,
                            number,
                            experience,
                            manDayValue,
                            Base64.getEncoder().encodeToString(guideImage.getBytes()),
                            Base64.getEncoder().encodeToString(nicImage.getBytes()),
                            Base64.getEncoder().encodeToString(guideIDImage.getBytes())
                    )
            );
            return new ResponseEntity<>(guideId+" Guide saved..!!", HttpStatus.OK);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }
    @PutMapping
    public ResponseEntity<String> updateGuide(
            @RequestParam("guideId")String guideId,
            @RequestParam("guideName")String guideName,
            @RequestParam("address")String address,
            @RequestParam("gender")String gender,
            @RequestParam("number")String number,
            @RequestParam("experience")String experience,
            @RequestParam("manDayValue")double manDayValue,
            @RequestParam("guideImage")MultipartFile guideImage,
            @RequestParam("nicImage")MultipartFile nicImage,
            @RequestParam("guideIDImage")MultipartFile guideIDImage
    ){
        try {
            guideService.updateGuide(
                    new GuideDTO(
                            guideId,
                            guideName,
                            address,
                            gender,
                            number,
                            experience,
                            manDayValue,
                            Base64.getEncoder().encodeToString(guideImage.getBytes()),
                            Base64.getEncoder().encodeToString(nicImage.getBytes()),
                            Base64.getEncoder().encodeToString(guideIDImage.getBytes())
                    )
            );
            return new ResponseEntity<>(guideId+" Guide updated..!!", HttpStatus.OK);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }
    @DeleteMapping(params = "guideId")
    public ResponseEntity<String> deleteGuide(String guideId){
        guideService.deleteGuide(guideId);
        return new ResponseEntity<>(guideId+" Guide Deleted..!!", HttpStatus.OK);
    }
    @GetMapping(params = "guideId")
    public ResponseEntity<GuideDTO> getGuideById(String guideId){
        return new ResponseEntity<>(guideService.getGuideById(guideId),HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<GuideDTO>> getAllGuides(){
        return new ResponseEntity<>(guideService.getAllGuides(),HttpStatus.OK);
    }
    //get guide by random
    @GetMapping(path = "/getGuideByRandom")
    public ResponseEntity<String> getGuideById(){
        String id=getRandom();
        if (guideService.existById(id))
            getGuideById();
        return new ResponseEntity<>(id,HttpStatus.OK);
    }
    public String getRandom(){
        Random rand = new Random();
        return Integer.toString(rand.nextInt(10000));
    }
}
