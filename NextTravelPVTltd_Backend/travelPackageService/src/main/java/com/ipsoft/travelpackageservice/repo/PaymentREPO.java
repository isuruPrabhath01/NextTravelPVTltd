

package com.ipsoft.travelpackageservice.repo;

import com.ipsoft.travelpackageservice.dto.ReportDTO;
import com.ipsoft.travelpackageservice.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.*;

public interface PaymentREPO extends JpaRepository<Payment,String> {
    @Query(value = "SELECT payment.date,count(payment.bookingId),sum(payment.price) FROM Payment GROUP BY date", nativeQuery = true)
    ArrayList dailyIncome();

    @Query(value = "SELECT (MONTHNAME(date )) ,count(payment.bookingId),sum(payment.price)FROM  Payment  GROUP BY extract(MONTH FROM(date))", nativeQuery = true)
    ArrayList monthlyIncome();

    @Query(value = "SELECT (YEAR(date )) ,count(payment.bookingId),sum(payment.price)FROM Payment GROUP BY extract(YEAR FROM(date))", nativeQuery = true)
    ArrayList AnnuallyIncome();

}
