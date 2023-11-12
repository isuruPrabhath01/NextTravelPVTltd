

package com.ipsoft.hotelservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelDTO {
    private String hotelId;
    private String hotelName;
    private String hotelRate;
    private String hotelCategory;
    private String hotelLocation;
    private String hotelCoordinates;
    private String hotelEmail;
    private String hotelNumber1;
    private String hotelNumber2;
    private String petsAllowed;
    private double hotelFee;
    private String cancellationCriteria;
    private String hotelImage;
}
