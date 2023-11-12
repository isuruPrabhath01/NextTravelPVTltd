

package com.ipsoft.travelpackageservice.api;

import com.ipsoft.travelpackageservice.dto.PaymentDTO;
import com.ipsoft.travelpackageservice.dto.ReportDTO;
import com.ipsoft.travelpackageservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentAPI {
    @Autowired
    PaymentService paymentService;
    @PostMapping(path = "/savePayment")
    public ResponseEntity<String> savePayment(
            @RequestParam("bookingId")String bookingId,
            @RequestParam("customerId")String customerId,
            @RequestParam("date") LocalDate date,
            @RequestParam("price")double price,
            @RequestParam("paymentSlip") MultipartFile paymentSlip
    ){
        try {
            paymentService.savePayment(
                    new PaymentDTO(
                            bookingId,
                            customerId,
                            date,
                            price,
                            Base64.getEncoder().encodeToString(paymentSlip.getBytes())
                    )
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(bookingId+" Payment accepted..!!",HttpStatus.OK);
    }
    //get daily income
    @GetMapping(path = "/dailyIncome")
    public ResponseEntity<ArrayList<ReportDTO>> dailyIncome(){
        return new ResponseEntity<>(paymentService.dailyIncome(), HttpStatus.OK);
    }
    //get monthly Income
    @GetMapping(path = "/monthlyIncome")
    public ResponseEntity<ArrayList<ReportDTO>> monthlyIncome(){
        return new ResponseEntity<>(paymentService.monthlyIncome(), HttpStatus.OK);
    }
    //get annually income
    @GetMapping(path = "/AnnuallyIncome")
    public ResponseEntity<ArrayList<ReportDTO>> AnnuallyIncome(){
        return new ResponseEntity<>(paymentService.AnnuallyIncome(), HttpStatus.OK);
    }
}
