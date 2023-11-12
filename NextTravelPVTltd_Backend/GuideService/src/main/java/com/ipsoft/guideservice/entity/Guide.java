

package com.ipsoft.guideservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Guide {
    @Id
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
