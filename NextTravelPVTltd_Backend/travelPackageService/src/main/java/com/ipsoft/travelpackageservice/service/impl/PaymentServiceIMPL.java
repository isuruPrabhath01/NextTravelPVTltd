
package com.ipsoft.travelpackageservice.service.impl;

import com.ipsoft.travelpackageservice.dto.PaymentDTO;
import com.ipsoft.travelpackageservice.dto.ReportDTO;
import com.ipsoft.travelpackageservice.entity.Payment;
import com.ipsoft.travelpackageservice.repo.PaymentREPO;
import com.ipsoft.travelpackageservice.service.PaymentService;
import com.ipsoft.travelpackageservice.util.Convertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional
@Service
public class PaymentServiceIMPL implements PaymentService {
    @Autowired
    private PaymentREPO paymentREPO;
    @Autowired
    private Convertor convertor;

    @Override
    public void savePayment(PaymentDTO paymentDTO) {
        if (paymentREPO.existsById(paymentDTO.getBookingId()))
            throw new RuntimeException(paymentDTO.getBookingId()+" Payment is already exists..!!");
        paymentREPO.save(convertor.paymentDtoToPaymentEntity(paymentDTO));
    }

    @Override
    public void updatePayment(PaymentDTO paymentDTO) {
        if (!paymentREPO.existsById(paymentDTO.getBookingId()))
            throw new RuntimeException(paymentDTO.getBookingId()+" Cannot find Payment..!!");
        paymentREPO.save(convertor.paymentDtoToPaymentEntity(paymentDTO));
    }

    @Override
    public void deletePayment(String bookingId) {
        if (!paymentREPO.existsById(bookingId))
            throw new RuntimeException(bookingId+" Payment is not found..!!");
        paymentREPO.deleteById(bookingId);
    }

    @Override
    public PaymentDTO getPaymentById(String bookingId) {
        if (!paymentREPO.existsById(bookingId))
            throw new RuntimeException(bookingId+" Payment is not found..!!");
        return convertor.paymentEntityToPaymentDto(paymentREPO.findById(bookingId).get());
    }

    @Override
    public List<PaymentDTO> getAllPayments() {
        return convertor.paymentEntityListToPaymentDTOList(paymentREPO.findAll());
    }

    @Override
    public ArrayList<ReportDTO> dailyIncome() {
        return paymentREPO.dailyIncome();
    }

    @Override
    public ArrayList<ReportDTO> monthlyIncome() {
        return paymentREPO.monthlyIncome();
    }

    @Override
    public ArrayList<ReportDTO> AnnuallyIncome() {
        return paymentREPO.AnnuallyIncome();
    }

}
