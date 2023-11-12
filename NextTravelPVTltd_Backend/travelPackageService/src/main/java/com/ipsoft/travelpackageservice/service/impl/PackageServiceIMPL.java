

package com.ipsoft.travelpackageservice.service.impl;

import com.ipsoft.travelpackageservice.dto.PackageDTO;
import com.ipsoft.travelpackageservice.repo.PackageREPO;
import com.ipsoft.travelpackageservice.service.PackageService;
import com.ipsoft.travelpackageservice.util.Convertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class PackageServiceIMPL implements PackageService {
    @Autowired
    Convertor convertor;
    @Autowired
    PackageREPO packageREPO;

    @Override
    public void savePackage(PackageDTO packageDTO) {
        if (packageREPO.existsById(packageDTO.getPackageId()))
            throw new RuntimeException(packageDTO.getPackageId()+" Package is already exists..!!");
        packageREPO.save(convertor.packageDtoToPackageEntity(packageDTO));
    }

    @Override
    public void updatePackage(PackageDTO packageDTO) {
        if (!packageREPO.existsById(packageDTO.getPackageId()))
            throw new RuntimeException(packageDTO.getPackageId()+" Package is not found..!!");
        packageREPO.save(convertor.packageDtoToPackageEntity(packageDTO));
    }

    @Override
    public void deletePackage(String packageId) {
        if (!packageREPO.existsById(packageId))
            throw new RuntimeException(packageId+" Package is not found..!!");
        packageREPO.deleteById(packageId);
    }

    @Override
    public PackageDTO getPackageById(String packageId) {
        if (!packageREPO.existsById(packageId))
            throw new RuntimeException(packageId+" Package is not found..!!");
        return convertor.packageEntityToPackageDto(packageREPO.findById(packageId).get());
    }

    @Override
    public List<PackageDTO> getAllPackages() {
        return convertor.packageEntityListToPackageDTOList(packageREPO.findAll());
    }

    @Override
    public PackageDTO getPackageByPackageCategory(String packageCategory) {
        return convertor.packageEntityToPackageDto(packageREPO.findPackageByPackageCategory(packageCategory));
    }

    @Override
    public int getCountOfPackage() {
        return packageREPO.getCountOfPackage();
    }

    @Override
    public String getLastIndex() {
        return packageREPO.getLastIndex();
    }

    @Override
    public boolean existsById(String id) {
        return packageREPO.existsById(id);
    }
}
