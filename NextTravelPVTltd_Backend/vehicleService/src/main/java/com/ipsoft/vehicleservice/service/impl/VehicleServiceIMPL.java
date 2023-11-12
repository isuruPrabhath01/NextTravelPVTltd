/**
 * Created By Isuru Prabhath
 * Date : 10/18/2023
 * Time : 3:31 PM
 * Project Name : vehicleService
 */

package com.ipsoft.vehicleservice.service.impl;

import com.ipsoft.vehicleservice.dto.VehicleGetDTO;
import com.ipsoft.vehicleservice.entity.Vehicle;
import com.ipsoft.vehicleservice.service.VehicleService;
import com.ipsoft.vehicleservice.dto.VehicleDTO;
import com.ipsoft.vehicleservice.repo.VehicleREPO;
import com.ipsoft.vehicleservice.util.Convertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class VehicleServiceIMPL implements VehicleService {
    @Autowired
    VehicleREPO vehicleREPO;
    @Autowired
    Convertor convertor;
    @Override
    public void saveVehicle(VehicleDTO vehicleDTO) {
        if (vehicleREPO.existsByVehicleRegId(vehicleDTO.getVehicleRegId()))
            throw new RuntimeException(vehicleDTO.getVehicleRegId()+" Vehicle is already in the System..!!");
        vehicleREPO.save(convertor.vehicleDtoToVehicleEntity(vehicleDTO));
    }

    @Override
    public VehicleGetDTO getVehicleById(String registrationNumber) {
        if (!vehicleREPO.existsByVehicleRegId(registrationNumber))
            throw new RuntimeException(registrationNumber+" Car cannot find in this server..!! ");
        return convertor.getVehicleDto(vehicleREPO.findById(registrationNumber).get());
    }

    @Override
    public void updateVehicle(VehicleDTO vehicleDTO) {
        System.out.println(vehicleDTO.getVehicleId());
        if (!vehicleREPO.existsByVehicleRegId(vehicleDTO.getVehicleRegId()))
            throw new RuntimeException(vehicleDTO.getVehicleRegId()+" Car cannot find in this server..!!");
        vehicleREPO.save(convertor.vehicleDtoToVehicleEntity(vehicleDTO));
    }

    @Override
    public void deleteVehicle(String vehicleId) {
        if (!vehicleREPO.existsById(vehicleId))
            throw new RuntimeException(vehicleId+" Car cannot find in this server..!! ");
        vehicleREPO.deleteById(vehicleId);
    }

    @Override
    public List<VehicleGetDTO> getAllVehicles() {
        return vehicleREPO.findAll().stream().map(vehicle -> convertor.getVehicleDto(vehicle)).collect(Collectors.toList());
    }

    @Override
    public List<VehicleGetDTO> getAllVehiclesByCategory(String category) {
        if (!vehicleREPO.existsByVehicleCategory(category))
            throw new RuntimeException(category+" type vehicles cannot find in this server..!! ");
        return vehicleREPO.findAllByVehicleCategory(category).stream().map(vehicle -> convertor.getVehicleDto(vehicle)).collect(Collectors.toList());
    }

    @Override
    public List<VehicleGetDTO> getAllVehiclesByCategorySeatCapacityTransmissionTypeFuelType(
            String category,
            int seatCapacity,
            String transmissionType,
            String fuelType) {
        List<Vehicle> vehicles = vehicleREPO.findAllByVehicleCategoryAndVehicleSeatCapacityAndTransmissionTypeAndVehicleFueltype(category, seatCapacity, transmissionType, fuelType);
        return vehicles.stream().map(vehicle -> convertor.getVehicleDto(vehicle)).collect(Collectors.toList());
    }

    @Override
    public int getCountOfVehicles() {
        return vehicleREPO.getCountOfVehicles();
    }

    @Override
    public String getLastIndex() {
        return vehicleREPO.getLastIndex();
    }
}
