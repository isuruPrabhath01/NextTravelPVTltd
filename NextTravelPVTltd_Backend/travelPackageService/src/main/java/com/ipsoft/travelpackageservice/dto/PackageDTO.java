

package com.ipsoft.travelpackageservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PackageDTO {
    private String packageId;
    private String packageCategory;
    private double price;
    private int nightCount;
    private int dayCount;
    private int totalHeadCount;
}
