/**
 * Created By Isuru Prabhath
 * Date : 10/18/2023
 * Time : 3:31 PM
 * Project Name : vehicleService
 */

package com.ipsoft.vehicleservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleGetDTO {
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
    private byte[] driverLicense;
    private byte[] rearView;

}
