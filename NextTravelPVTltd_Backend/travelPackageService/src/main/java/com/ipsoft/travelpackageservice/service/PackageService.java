

package com.ipsoft.travelpackageservice.service;

import com.ipsoft.travelpackageservice.dto.PackageDTO;

import java.util.List;

public interface PackageService {
    void savePackage(PackageDTO packageDTO);
    void updatePackage(PackageDTO packageDTO);
    void deletePackage(String packageId);
    PackageDTO getPackageById(String packageId);
    List<PackageDTO> getAllPackages();
    PackageDTO getPackageByPackageCategory(String packageCategory);

    int getCountOfPackage();

    String getLastIndex();

    boolean existsById(String id);
}
