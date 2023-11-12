

package com.ipsoft.userservice.entity;

import com.ipsoft.userservice.util.RoleType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    private String userId;
    private String name;
    private String nic;
    private int age;
    private String gender;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private RoleType roleType;
    private String contactNumber;
    private String address;
    @Column(columnDefinition = "LONGTEXT")
    private String  profilePic;
}
