

package com.ipsoft.apigateway.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Predicate;

@Component
public class RouteValidator {
    public static final List<String> WHITE_LIST=List.of(
            "/nextTravelPVTLtdGuideService/api/v1/guide/getGuideByRandom",
            "/nextTravelPVTLtdGuideService/api/v1/guide/saveGuide",
            "/nextTravelPVTLtdTravelPackage/api/v1/Package/genarateId",
            "/nextTravelPVTLtdUserService/api/v1/user/deleteUserByEmail",
            "/nextTravelPVTLtdUserService/api/v1/user/getAllUsers",
            "/nextTravelPVTLtdTravelPackage/api/v1/Package",
            "/nextTravelPVTLtdTravelPackage/api/v1/booking/genarateId",
            "/nextTravelPVTLtdTravelPackage/api/v1/booking",
            "/nextTravelPVTLtdTravelPackage/api/v1/Package/getPackageByPackageCategory",
            "/nextTravelPVTLtdUserService/api/v1/user/updateUser",
            "/authenticationServer/auth/token",
            "/nextTravelPVTLtdUserService/api/v1/user/genarateId",
            "/nextTravelPVTLtdUserService/api/v1/user/saveUser",
            "/nextTravelPVTLtdHotelService/api/v1/Hotel/getAllHotelsByPackageCategory",
            "/nextTravelPVTLtdHotelService/api/v1/Hotel/getAllHotelsByStarRateAndLocation",
            "/nextTravelPVTLtdTravelPackage/api/v1/Package/getPackageNameList",
            "/nextTravelPVTLtdVehicleService/api/v1/Vehicle/getAllVehiclesByCategory",
            "/nextTravelPVTLtdVehicleService/api/v1/Vehicle/getAllVehiclesByVehicleTypeSeatCapacityTransmissionTypeFuelType",
            "/nextTravelPVTLtdVehicleService/api/v1/Vehicle/getVehicleRegistrationNumbers",
            "/nextTravelPVTLtdUserService/api/v1/user/getLastId",
            "/eureka"
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> WHITE_LIST
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));
}
