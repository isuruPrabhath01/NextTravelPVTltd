

package com.ipsoft.userservice.api;

import com.ipsoft.userservice.dto.UserDTO;
import com.ipsoft.userservice.service.UserService;
import com.ipsoft.userservice.util.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/v1/user")
public class UserAPI {
    @Autowired
    UserService userService;
    @PostMapping("/saveUser")
    public ResponseEntity<String> saveUser(
            @RequestParam("userId") String userId,
            @RequestParam("name") String name,
            @RequestParam("nic") String nic,
            @RequestParam("age") int age,
            @RequestParam("gender") String gender,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("roleType") RoleType roleType,
            @RequestParam("contactNumber") String contactNumber,
            @RequestParam("address") String address,
            @RequestParam("profilePic") MultipartFile profilePic
    ){
        try {
            userService.userSave(
                    new UserDTO(
                    userId,
                    name,
                    nic,
                    age,
                    gender,
                    email,
                    password,
                    roleType,
                    contactNumber,
                    address,
                    Base64.getEncoder().encodeToString(profilePic.getBytes())
                    )
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(userId+" User Saved..!!", HttpStatus.OK);
    }
    @PutMapping("/updateUser")
    public ResponseEntity<String> updateUser(
            @RequestParam("userId") String userId,
            @RequestParam("name") String name,
            @RequestParam("nic") String nic,
            @RequestParam("age") int age,
            @RequestParam("gender") String gender,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("roleType") RoleType roleType,
            @RequestParam("contactNumber") String contactNumber,
            @RequestParam("address") String address,
            @RequestParam("profilePic") MultipartFile profilePic
    ){
        try {
            userService.updateUser(
                    new UserDTO(
                            userId,
                            name,
                            nic,
                            age,
                            gender,
                            email,
                            password,
                            roleType,
                            contactNumber,
                            address,
                            Base64.getEncoder().encodeToString(profilePic.getBytes())
                    )
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(userId+" User Updated..!!", HttpStatus.OK);
    }
    @GetMapping(params = "userEmail",path = "/getUser")
    public ResponseEntity<UserDTO> userFindByEmail(String userEmail){
        return new ResponseEntity<>(userService.userFindByEmail(userEmail),HttpStatus.OK);
    }
    @DeleteMapping(path = "/deleteUserByEmail",params = "userEmail")
    public ResponseEntity<String> deleteUser(String userEmail){
        userService.deleteUser(userEmail);
        return new ResponseEntity<>(userEmail+" User deleted..!!",HttpStatus.OK);
    }
    @GetMapping(path = "/getAllUsers")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
    }
    //get Count by users by Role type(number of admins,users and customers)
    @GetMapping(params = "roleType",path = "/countByRoleType")
    public ResponseEntity<Integer> countByRoleType(RoleType roleType){

        return new ResponseEntity<>(userService.countByRoleType(roleType),HttpStatus.OK);
    }
    //get last index of userId
    @GetMapping(path = "/getLastId")
    public ResponseEntity<String> getLastId(){
        return new ResponseEntity<>(userService.getLastIndex(),HttpStatus.OK);
    }
    //existsByEmail
    @GetMapping(params = "email" ,path = "/existsByEmail")
    public ResponseEntity<Boolean> existsByEmail(String email){
        return new ResponseEntity<>(userService.existByEmail(email),HttpStatus.OK);
    }
    @GetMapping(path = "/genarateId")
    public ResponseEntity<String> genarateId(){
        String id=getRandom();
        if (userService.existsById(id))
            genarateId();
        return new ResponseEntity<>(id,HttpStatus.OK);
    }
    public String getRandom(){
        Random rand = new Random();
        return Integer.toString(rand.nextInt(10000));
    }

}