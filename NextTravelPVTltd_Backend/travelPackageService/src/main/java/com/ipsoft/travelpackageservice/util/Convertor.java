

package com.ipsoft.travelpackageservice.util;

import com.ipsoft.travelpackageservice.dto.BookingDTO;
import com.ipsoft.travelpackageservice.dto.BookingGetDTO;
import com.ipsoft.travelpackageservice.dto.PackageDTO;
import com.ipsoft.travelpackageservice.dto.PaymentDTO;
import com.ipsoft.travelpackageservice.entity.Booking;
import com.ipsoft.travelpackageservice.entity.Package;
import com.ipsoft.travelpackageservice.entity.Payment;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Convertor {
    @Autowired
    ModelMapper modelMapper;


    public PackageDTO packageEntityToPackageDto(Package aPackage){
        return modelMapper.map(aPackage, PackageDTO.class);
    }

    public List<PackageDTO> packageEntityListToPackageDTOList(List<Package> packages){
        return modelMapper.map(packages,new TypeToken<List<PackageDTO>>(){}.getType());
    }

    public Booking bookingDtoToBookingEntity(BookingDTO bookingDTO, PackageDTO packageDTO){
        Package aPackage = packageDtoToPackageEntity(packageDTO);
        Booking booking = modelMapper.map(bookingDTO, Booking.class);
        booking.setAPackage(aPackage);
        return booking;
    }

    public BookingGetDTO bookingEntityToBookingGetDto(Booking booking){
        BookingGetDTO bookingGetDTO=modelMapper.map(booking,BookingGetDTO.class);
        bookingGetDTO.setPackageDTO(packageEntityToPackageDto(booking.getAPackage()));
        return bookingGetDTO;
    }

    public List<BookingGetDTO> bookingEntityListToBookingGetDTOList(List<Booking> bookings){
        return modelMapper.map(bookings,new TypeToken<List<BookingGetDTO>>(){}.getType());
    }

    public Package packageDtoToPackageEntity(PackageDTO packageDTO) {
        return modelMapper.map(packageDTO,Package.class);
    }

    public Payment paymentDtoToPaymentEntity(PaymentDTO paymentDTO) {
        return modelMapper.map(paymentDTO,Payment.class);
    }
    public PaymentDTO paymentEntityToPaymentDto(Payment payment) {
        return modelMapper.map(payment,PaymentDTO.class);
    }
    public List<PaymentDTO> paymentEntityListToPaymentDTOList(List<Payment> payments){
        return modelMapper.map(payments,new TypeToken<List<PaymentDTO>>(){}.getType());
    }
}
