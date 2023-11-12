/**
 * Created By Isuru Prabhath
 * Date : 10/18/2023
 * Time : 3:31 PM
 * Project Name : vehicleService
 */

package com.ipsoft.vehicleservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Vehicle {
    @Id
    private String vehicleId;
    private String vehicleRegId;
    private String vehiclebrand;
    private String vehicleCategory;
    private String vehicleFueltype;
    private String hybridStatus;
    private int    vehicleFuelUsage;
    private int    vehicleSeatCapacity;
    private String vehicleType;
    private String transmissionType;
    private String vehicleDriverName;
    private String vehicleDriveNumber;
    @Column(columnDefinition = "LONGTEXT")
    private String driverLicense;
    @Column(columnDefinition = "LONGTEXT")
    private String rearView;

}
