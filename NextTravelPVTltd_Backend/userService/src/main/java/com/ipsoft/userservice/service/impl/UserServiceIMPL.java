

package com.ipsoft.userservice.service.impl;

import com.ipsoft.userservice.dto.UserDTO;
import com.ipsoft.userservice.repo.UserREPO;
import com.ipsoft.userservice.service.UserService;
import com.ipsoft.userservice.util.Convertor;
import com.ipsoft.userservice.util.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class UserServiceIMPL implements UserService {
    @Autowired
    UserREPO userREPO;
    @Autowired
    Convertor convertor;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void userSave(UserDTO userDTO) {
        if (userREPO.existsById(userDTO.getUserId()))
            throw new RuntimeException(userDTO.getUserId()+" User already in the system..!! ");
        if (userREPO.existsByEmail(userDTO.getEmail()))
            throw new RuntimeException(userDTO.getEmail()+" User Not in the system..!! ");
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userREPO.save(convertor.userDtoToUserEntity(userDTO));
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        if (!userREPO.existsById(userDTO.getUserId()))
            throw new RuntimeException(userDTO.getUserId()+" User Not in the system..!! ");
        if (!userREPO.existsByEmail(userDTO.getEmail()))
            throw new RuntimeException(userDTO.getEmail()+" User Not in the system..!! ");
        userREPO.save(convertor.userDtoToUserEntity(userDTO));
    }

    @Override
    public UserDTO userFindByEmail(String email) {
        if (!userREPO.existsByEmail(email))
            throw new RuntimeException(email+" User Not in the system..!! ");
        return convertor.userEntityToUserDto(userREPO.findByEmail(email).get());
    }

    @Override
    public void deleteUser(String userEmail) {
        boolean b = userREPO.existsByEmail(userEmail);
        System.out.println(b);
        if (!userREPO.existsByEmail(userEmail)) {
            throw new RuntimeException(userEmail + " User Not in the system..!! ");
        }
        System.out.println(userEmail+"lastPoint");
        userREPO.deleteByEmail(userEmail);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return convertor.userEntityListToUserDTOList(userREPO.findAll());
    }

    @Override
    public int countByRoleType(RoleType roleType) {
        return userREPO.countByRoleType(roleType);
    }

    @Override
    public String getLastIndex() {
        return userREPO.getLastIndex();
    }

    @Override
    public boolean existByEmail(String email) {
        boolean b = userREPO.existsByEmail(email);
        System.out.println(b);
        return b;
    }

    @Override
    public boolean existsById(String id) {
        return userREPO.existsById(id);
    }
}
