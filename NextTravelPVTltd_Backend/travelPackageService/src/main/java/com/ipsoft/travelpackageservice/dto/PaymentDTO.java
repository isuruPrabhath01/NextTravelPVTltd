

package com.ipsoft.travelpackageservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
    private String bookingId;
    private String customerId;
    private LocalDate date;
    private double price;
    private String paymentSlip;


}
