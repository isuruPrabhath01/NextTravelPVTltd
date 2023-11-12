

package com.ipsoft.travelpackageservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Package {
    @Id
    private String packageId;
    private String packageCategory;
    private double price;
    private int nightCount;
    private int dayCount;
    private int totalHeadCount;
    @OneToMany(mappedBy = "aPackage",cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    List<Booking> bookings = new ArrayList<>();

}
