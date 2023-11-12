

package com.ipsoft.hotelservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Hotel {
    @Id
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
    @Column(columnDefinition = "LONGTEXT")
    private String hotelImage;
}
