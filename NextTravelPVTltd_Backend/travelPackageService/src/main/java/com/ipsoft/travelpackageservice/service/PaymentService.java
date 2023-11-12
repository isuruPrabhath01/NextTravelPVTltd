

package com.ipsoft.travelpackageservice.service;

import com.ipsoft.travelpackageservice.dto.PaymentDTO;
import com.ipsoft.travelpackageservice.dto.ReportDTO;

import java.util.ArrayList;
import java.util.List;

public interface PaymentService {
    void savePayment(PaymentDTO paymentDTO);
    void updatePayment(PaymentDTO paymentDTO);
    void deletePayment(String bookingId);
    PaymentDTO getPaymentById(String bookingId);
    List<PaymentDTO> getAllPayments();
    ArrayList<ReportDTO> dailyIncome();
    ArrayList<ReportDTO> monthlyIncome();
    ArrayList<ReportDTO> AnnuallyIncome();

}
