

package com.ipsoft.travelpackageservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingGetDTO {
    private String bookingId;
    private String guideId;
    private String userId;
    private LocalDate currentlyDate;
    private LocalDate startDate;
    private LocalDate endDate;
    private int nightCount;
    private int dayCount;
    private int adultsCount;
    private int childrenCount;
    private PackageDTO packageDTO;
}
