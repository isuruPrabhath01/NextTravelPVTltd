

package com.ipsoft.travelpackageservice.repo;

import com.ipsoft.travelpackageservice.entity.Package;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PackageREPO extends JpaRepository<Package,String>{
    Package findPackageByPackageCategory(String packageCategory);

    @Query(value = "SELECT COUNT(*) FROM Package",nativeQuery=true)
    int getCountOfPackage();

    @Query(value = "SELECT packageId FROM Package ORDER BY packageId DESC LIMIT 1",nativeQuery = true)
    String getLastIndex();

}
