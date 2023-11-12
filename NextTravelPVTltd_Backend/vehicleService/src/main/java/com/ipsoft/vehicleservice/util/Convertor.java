/**
 * Created By Isuru Prabhath
 * Date : 10/18/2023
 * Time : 3:32 PM
 * Project Name : vehicleService
 */

package com.ipsoft.vehicleservice.util;

import com.ipsoft.vehicleservice.dto.VehicleDTO;
import com.ipsoft.vehicleservice.dto.VehicleGetDTO;
import com.ipsoft.vehicleservice.entity.Vehicle;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.List;

@Component
public class Convertor {
    @Autowired
    ModelMapper modelMapper;

    public Vehicle vehicleDtoToVehicleEntity(VehicleDTO vehicleDTO){
        return modelMapper.map(vehicleDTO, Vehicle.class);
    }

    public VehicleDTO vehicleEntityToVehicleGetDto( Vehicle vehicle){
        return modelMapper.map(vehicle, VehicleDTO.class);
    }

    public List<VehicleDTO> vehicleEntityListToVehicleDTOList(List<Vehicle> vehicles){
        return modelMapper.map(vehicles,new TypeToken<List<VehicleDTO>>(){}.getType());
    }
    public VehicleGetDTO getVehicleDto(Vehicle vehicle){
        VehicleGetDTO vehicleGetDTO = modelMapper.map(vehicle, VehicleGetDTO.class);
        vehicleGetDTO.setDriverLicense(decodeImage(vehicle.getDriverLicense()));
        vehicleGetDTO.setRearView(decodeImage(vehicle.getRearView()));
        return vehicleGetDTO;
    }

    private byte[] decodeImage(String imageString) {
        return Base64.getDecoder().decode(imageString);
    }
}
