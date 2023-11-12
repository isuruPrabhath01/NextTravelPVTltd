

package com.ipsoft.userservice.repo;

import com.ipsoft.userservice.entity.User;
import com.ipsoft.userservice.util.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserREPO extends JpaRepository<User,String> {
    @Query(value = "SELECT userId FROM User ORDER BY userId DESC LIMIT 1",nativeQuery = true)
    String getLastIndex();
    boolean existsByEmail(String email);
    void deleteByEmail(String userEmail);
    Optional<User> findByEmail(String email);
    int countByRoleType(RoleType roleType);
}
