/**
 * Created By Isuru Prabhath
 * Date : 10/18/2023
 * Time : 3:30 PM
 * Project Name : vehicleService
 */

package com.ipsoft.vehicleservice.api;
import com.ipsoft.vehicleservice.dto.VehicleDTO;
import com.ipsoft.vehicleservice.dto.VehicleGetDTO;
import com.ipsoft.vehicleservice.service.VehicleService;
import jdk.swing.interop.SwingInterOpUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/api/v1/Vehicle")
@RequiredArgsConstructor
public class VehicleAPI {
    @Autowired
    VehicleService vehicleService;


    @PostMapping
    public ResponseEntity<String> saveVehicle(
            @RequestParam("vehicleId") String vehicleId,
            @RequestParam("vehicleRegId") String vehicleRegId,
            @RequestParam("vehiclebrand") String vehiclebrand,
            @RequestParam("vehicleCategory") String vehicleCategory,
            @RequestParam("vehicleFueltype") String vehicleFueltype,
            @RequestParam("hybridStatus") String hybridStatus,
            @RequestParam("vehicleFuelUsage") int vehicleFuelUsage,
            @RequestParam("vehicleSeatCapacity") int vehicleSeatCapacity,
            @RequestParam("vehicleType") String vehicleType,
            @RequestParam("transmissionType") String transmissionType,
            @RequestParam("vehicleDriverName") String vehicleDriverName,
            @RequestParam("vehicleDriveNumber") String vehicleDriveNumber,
            @RequestParam("driverLicense") MultipartFile driverLicense,
            @RequestParam("rearView") MultipartFile rearView
    ){
        System.out.println(vehicleId);
        try {
            vehicleService.saveVehicle(new VehicleDTO(
                    vehicleId,
                    vehicleRegId,
                    vehiclebrand,
                    vehicleCategory,
                    vehicleFueltype,
                    hybridStatus,
                    vehicleFuelUsage,
                    vehicleSeatCapacity,
                    vehicleType,
                    transmissionType,
                    vehicleDriverName,
                    vehicleDriveNumber,
                    Base64.getEncoder().encodeToString(driverLicense.getBytes()),
                    Base64.getEncoder().encodeToString(rearView.getBytes())
            ));
        } catch (IOException e) {
            throw new RuntimeException("Image Not Found..!!");
        }
        return new ResponseEntity<>(vehicleRegId+" Vehicle is save successfully..!!", HttpStatus.OK);
    }

    @GetMapping(params = "registrationNumber")
    public ResponseEntity<VehicleGetDTO> getVehicle(String registrationNumber){
        return new ResponseEntity<>(vehicleService.getVehicleById(registrationNumber),HttpStatus.OK);
    }

    @PutMapping(path = "updateVehicle")
    public ResponseEntity<String> updateVehicle(
            @RequestParam("vehicleId") String vehicleId,
            @RequestParam("vehicleRegId") String vehicleRegId,
            @RequestParam("vehiclebrand") String vehiclebrand,
            @RequestParam("vehicleCategory") String vehicleCategory,
            @RequestParam("vehicleFueltype") String vehicleFueltype,
            @RequestParam("hybridStatus") String hybridStatus,
            @RequestParam("vehicleFuelUsage") int vehicleFuelUsage,
            @RequestParam("vehicleSeatCapacity") int vehicleSeatCapacity,
            @RequestParam("vehicleType") String vehicleType,
            @RequestParam("transmissionType") String transmissionType,
            @RequestParam("vehicleDriverName") String vehicleDriverName,
            @RequestParam("vehicleDriveNumber") String vehicleDriveNumber,
            @RequestParam("driverLicense") MultipartFile driverLicense,
            @RequestParam("rearView") MultipartFile rearView)
    {
        try {
            vehicleService.updateVehicle(new VehicleDTO(
                    vehicleId,
                    vehicleRegId,
                    vehiclebrand,
                    vehicleCategory,
                    vehicleFueltype,
                    hybridStatus,
                    vehicleFuelUsage,
                    vehicleSeatCapacity,
                    vehicleType,
                    transmissionType,
                    vehicleDriverName,
                    vehicleDriveNumber,
                    Base64.getEncoder().encodeToString(driverLicense.getBytes()),
                    Base64.getEncoder().encodeToString(rearView.getBytes())
            ));
        } catch (IOException e) {
            throw new RuntimeException("Image not Found..!!");
        }
        return new ResponseEntity<>(vehicleRegId+" Vehicle has been Updated Successfully",HttpStatus.OK);
    }

    @DeleteMapping(params = "vehicleId")
    public ResponseEntity<String> deleteVehicle(String vehicleId){
        System.out.println(vehicleId);
        vehicleService.deleteVehicle(vehicleId);
        return new ResponseEntity<>(vehicleId+" Vehicle Has been Deleted Successfully ",HttpStatus.OK);
    }

    @GetMapping(path="getAllVehicles")
    public ResponseEntity<List<VehicleGetDTO>> getAllVehicle(){
        return new ResponseEntity<>(vehicleService.getAllVehicles(),HttpStatus.OK);
    }
    //filter Vehicle by vehicleCategory
    @GetMapping(params = "vehicleCategory",path = "/getAllVehiclesByCategory")
    public ResponseEntity<List<VehicleGetDTO>> getAllVehiclesByCategory(@RequestParam("vehicleCategory") String vehicleCategory){
        System.out.println("Reach");
        return new ResponseEntity<>(vehicleService.getAllVehiclesByCategory(vehicleCategory),HttpStatus.OK);
    }

    //filter Vehicle by vehicleCategory,vehicleSeatCapacity,transmissionType and vehicleFueltype
    @GetMapping(params = {"vehicleCategory","vehicleSeatCapacity","transmissionType","vehicleFueltype"},path = "/getAllVehiclesByVehicleTypeSeatCapacityTransmissionTypeFuelType")
    public ResponseEntity<List<VehicleGetDTO>> getAllVehiclesByVehicleTypeSeatCapacityTransmissionTypeFuelType(
            String vehicleCategory,
            int vehicleSeatCapacity,
            String transmissionType,
            String vehicleFueltype
    ){
        return new ResponseEntity<>(vehicleService.getAllVehiclesByCategorySeatCapacityTransmissionTypeFuelType(vehicleCategory,vehicleSeatCapacity,transmissionType,vehicleFueltype),HttpStatus.OK);
    }

    //get all vehicle register numbers
    @GetMapping(path = "/getVehicleRegistrationNumbers")
    public ResponseEntity<List<String>> getVehicleRegistrationNumbers(){
        List<String> registrationNumbers = null;
        List<VehicleGetDTO> allVehicles = vehicleService.getAllVehicles();
        for(VehicleGetDTO vehicleDTO : allVehicles){
            registrationNumbers.add(vehicleDTO.getVehicleRegId());
        }
        return new ResponseEntity<>(registrationNumbers,HttpStatus.OK);
    }

    //get count of vehicles
    @GetMapping(path = "/getCountOfVehicles")
    public ResponseEntity<Integer> getCountOfVehicles(){
        return new ResponseEntity<>(vehicleService.getCountOfVehicles(),HttpStatus.OK);
    }

    //get last index in Vehicle id
    @GetMapping(path = "/getLastId")
    public ResponseEntity<String> getLastId(){
        String lastIndex = vehicleService.getLastIndex();
        if (lastIndex==null){
            lastIndex="NTV-000";
        }
        System.out.println(lastIndex);
        return new ResponseEntity<>(lastIndex,HttpStatus.OK);
    }
}

