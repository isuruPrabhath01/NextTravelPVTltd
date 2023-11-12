

package com.ipsoft.travelpackageservice.api;

import com.ipsoft.travelpackageservice.dto.PackageDTO;
import com.ipsoft.travelpackageservice.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/v1/Package")
public class PackageAPI {
    @Autowired
    PackageService packageService;

    @PostMapping
    public ResponseEntity<String> savePackage(@ModelAttribute PackageDTO packageDTO){
        System.out.println();
        packageService.savePackage(packageDTO);
        return new ResponseEntity<>(packageDTO.getPackageId()+" Package is saved..!!", HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> updatePackage(@ModelAttribute PackageDTO packageDTO){
        packageService.updatePackage(packageDTO);
        return new ResponseEntity<>(packageDTO.getPackageId()+" Package is update..!!", HttpStatus.OK);
    }

    @DeleteMapping(params = "packageId")
    public ResponseEntity<String> deletePackage(String packageId){
        System.out.println(packageId);
        packageService.deletePackage(packageId);
        return new ResponseEntity<>(packageId+" Package is deleted..!!", HttpStatus.OK);
    }

    @GetMapping(params = "packageId")
    public ResponseEntity<PackageDTO> getPackageById(String packageId){
        return new ResponseEntity<>(packageService.getPackageById(packageId),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PackageDTO>> getAllPackages(){
        return new ResponseEntity<>(packageService.getAllPackages(),HttpStatus.OK);
    }
    //get all package names
    @GetMapping(path = "/getPackageNameList")
    public ResponseEntity<List<String>> getPackageNameList(){
        ArrayList<String> packageNameList = new ArrayList<>();
        List<PackageDTO> allPackages = packageService.getAllPackages();

        for(PackageDTO packageDTO : allPackages){
            System.out.println(packageDTO.getPackageId());
            packageNameList.add(packageDTO.getPackageCategory());
        }
        return new ResponseEntity<>(packageNameList,HttpStatus.OK);
    }
    //get package by package category
    @GetMapping(params = "packageCategory",path = "getPackageByPackageCategory")
    public ResponseEntity<PackageDTO> getPackageByPackageCategory(String packageCategory){
        return new ResponseEntity<>(packageService.getPackageByPackageCategory(packageCategory),HttpStatus.OK);
    }
    //get count of packages
    @GetMapping(path = "/getCountOfPackage")
    public ResponseEntity<Integer>  getCountOfPackage(){
        return new ResponseEntity<>(packageService.getCountOfPackage(),HttpStatus.OK);
    }
    //get last index of Package id
    @GetMapping(path = "/getLastId")
    public ResponseEntity<String> getLastId(){
        return new ResponseEntity<>(packageService.getLastIndex(),HttpStatus.OK);
    }

    @GetMapping(path = "/genarateId")
    public ResponseEntity<String> genarateId(){
        String id=getRandom();
        if (packageService.existsById(id))
            genarateId();
        return new ResponseEntity<>(id,HttpStatus.OK);
    }
    public String getRandom(){
        Random rand = new Random();
        return Integer.toString(rand.nextInt(10000));
    }
}
