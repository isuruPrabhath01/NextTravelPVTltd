

package com.ipsoft.guideservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuideDTO {
    private String guideId;
    private String guideName;
    private String address;
    private String gender;
    private String number;
    private String experience;
    private double manDayValue;
    private String guideImage;
    private String nicImage;
    private String guideIDImage;
}
