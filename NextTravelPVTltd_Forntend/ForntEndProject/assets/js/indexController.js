loadAllPackageName();

//-----------------------------------Load packageName------------------------------------
function loadAllPackageName() {
    $("#packageCategory").empty();
    packgeName=$("#packageCategory").val();
    $.ajax({
        url: "http://localhost:8989/nextTravelPVTLtdTravelPackage/api/v1/Package/getPackageNameList",
        method: "GET",
        contentType: "json",
        dataType: "json",
        success: function (res) {
            for (let i of res) {
                console.log(i);
                let package_name = i;
                console.log(package_name);
                $("#packageCategory").append(`<option>${package_name}</option>`)
            }
        }, error: function (error) {
            console.log(error);
        }
    })
}

/*-----------------------Select Package name----------------------------------*/
$("#packageCategory").on("click", function () {
    loadAllHotel();
    loadAllVehicle();

});
$("#searchVehicle").on("click", function () {
    filterVehicle();
});
$("#searchHotel").on("click", function () {
    filterHotels();
});
//----------------------------Load All Hotel according to package--------------------
function loadAllHotel() {
    $("#hotelSection").empty();
    hotelCategory=$("#packageCategory").val();
    $.ajax({
        url:"http://localhost:8989/nextTravelPVTLtdHotelService/api/v1/Hotel/getAllHotelsByPackageCategory?hotelCategory="+hotelCategory,
        method: "GET",
        dataType: "json",
        success: function (res) {
            for (let i of res) {
                $("#hotelSection").append(
                    `<div class="card" style="width: 500px;">
                         <div class="row no-gutters">
                              <div class="col-sm-6">
                                   <img class="card-img-top"
                                             src="${i.rear_View}"
                                             alt="Card image cap">
                              </div>
                                    <div class="col-sm-6">
                                        <div class="card-body">
                                            <h4 class="card-title" style="margin-top: 15px">${i.hotelName}</h4>
                                            <small>${i.hotelLocation}</small><br>
                                            <small>${i.hotelRate}</small>
                                        </div>
                                    </div>
                         </div>
                    </div>`);
            }
        }
    })
}


//----------------------------Filter Hotels--------------------
function filterHotels() {
    $("#hotelSection").empty();
    hotelCategory=$("#packageCategory").val();
    hotelRate=$("#hotelRate").val();
    locations=$("#locations").val();
    $.ajax({
        url:"http://localhost:8989/nextTravelPVTLtdHotelService/api/v1/Hotel/getAllHotelsByStarRateAndLocation?hotelCategory="+hotelCategory+"&hotelRate="+hotelRate+"&hotelLocation="+locations,
        method: "GET",
        dataType: "json",
        success: function (res) {
            for (let i of res) {
                $("#hotelSection").append(
                    `<div class="card" style="width: 500px;">
                         <div class="row no-gutters">
                              <div class="col-sm-6">
                                   <img class="card-img-top"
                                             src="${i.rear_View}"
                                             alt="Card image cap">
                              </div>
                                    <div class="col-sm-6">
                                        <div class="card-body">
                                            <h4 class="card-title" style="margin-top: 15px">${i.hotelName}</h4>
                                            <small>${i.hotelLocation}</small><br>
                                            <small>${i.hotelRate}</small>
                                        </div>
                                    </div>
                         </div>
                    </div>`);
            }
        }
    })
}

//-------------------------------------------Load All Vehicle according to package-----------------

function loadAllVehicle() {
    console.log("Reach")
    $("#vehicleSection").empty();
    vehicleCategory=$("#packageCategory").val();
    $.ajax({
        url: "http://localhost:8989/nextTravelPVTLtdVehicleService/api/v1/Vehicle/getAllVehiclesByCategory?vehicleCategory="+vehicleCategory,
        method: "GET",
        dataType: "json",
        success: function (res) {
            for (let i of res) {
                console.log(i.vehicleFueltype)
                $("#vehicleSection").append(
                    `<div class="card" style="width: 500px;">
                            <div class="row no-gutters">
                                <div class="col-sm-6">
                                    <img class="card-img-top"
                                         src="data:image/jpeg;base64, ${i.rear_View}"
                                         alt="Card image cap">
                                </div>
                                <div class="col-sm-6">
                                    <div class="card-body">
                                        <h4 class="card-title" style="margin-top: 15px">${i.vehiclebrand}</h4>
                                        <small>${i.vehicleSeatCapacity}</small><br>
                                        <small>${i.vehicleFueltype}</small><br>
                                        <small>${i.transmission}</small><br>

                                    </div>
                                </div>
                            </div>
                        </div>`);
            }
        }
    })
}


//-------------------------------------------Filter Vehicle-----------------
    function filterVehicle() {
    console.log("Reach")
    $("#vehicleSection").empty();
    vehicleCategory=$("#packageCategory").val();
    filter_Passengers = $("#vehicleSeatCapacity").val();
    filter_Transmission = $("#transmission").val();
    filter_Fueltype = $("#FuelType").val();
    $.ajax({
        url: "http://localhost:8989/nextTravelPVTLtdVehicleService/api/v1/Vehicle/getAllVehiclesByVehicleTypeSeatCapacityTransmissionTypeFuelType?vehicleCategory="+vehicleCategory+"&vehicleSeatCapacity="+filter_Passengers+"&transmissionType="+filter_Transmission+"&vehicleFueltype="+filter_Fueltype,
        method: "GET",
        dataType: "json",
        success: function (res) {
            for (let i of res) {
                console.log(i.vehicleFueltype)
                $("#vehicleSection").append(
                    `<div class="card" style="width: 500px;">
                            <div class="row no-gutters">
                                <div class="col-sm-6">
                                    <img class="card-img-top"
                                         src="data:image/jpeg;base64, ${i.rear_View}"
                                         alt="Card image cap">
                                </div>
                                <div class="col-sm-6">
                                    <div class="card-body">
                                        <h4 class="card-title" style="margin-top: 15px">${i.vehiclebrand}</h4>
                                        <small>${i.vehicleSeatCapacity}</small><br>
                                        <small>${i.vehicleFueltype}</small><br>
                                        <small>${i.transmission}</small><br>

                                    </div>
                                </div>
                            </div>
                        </div>`);
            }
        }
    })
}



//-----------------------------------filter all hotel & vehicle according to select package name--------------------
/*function filterPackages() {
    $("#hotelSection").empty();
    $("#vehicleSection").empty();
    packgeName=$("#packageName").val();
    starRate=$("#hotelRate").val();
    location=$("#locations").val();
    passengers=$("#vehicleSeatCapacity").val();
    transmission=$("#transmission").val();
    fuelType=$("#FuelType").val();
    $.ajax({
        url: indexBaseUrl + "hotel/getAllHotelsByStarRateAndLocation?hotelCategory="+packgeName+ "&hotelRate"+starRate+"&hotelLocation"+location,
        method: "GET",
        data:"json",
        dataType: "json",
        success: function (res) {
            for (let i of res) {
                $("#hotelSection").append(
                    `<div class="card" style="width: 500px;">
                         <div class="row no-gutters">
                              <div class="col-sm-6">
                                   <img class="card-img-top"
                                             src="${i.image.hotelImage}"
                                             alt="Card image cap">
                              </div>
                                    <div class="col-sm-6">
                                        <div class="card-body">
                                            <h4 class="card-title" style="margin-top: 15px">
                                                ${i.hotelName}</h4>
                                            <small>${i.hotelLocation}</small><br>
                                            <small>${i.hotelRate}</small>
                                        </div>
                                    </div>
                         </div>
                    </div>`);
            }


        },
        error: function (error) {
            loadAllHotel();
            loadAllVehicle();
        }
    })


    $.ajax({
        url: "http://localhost:8090/vehicleServer/api/v1/Vehicle/getAllVehicleByVehicleTypeSeatCapacityTransmissionTypeFuelType?vehicleCategory="+packgeName+"&vehicleSeatCapacity"+passengers+"&transmissionType"+transmission+"&vehicleFuelType"+fuelType,
        method: "GET",
        data:"json",
        dataType: "json",
        success: function (res) {
            for (let i of res) {
                $("#vehicleSection").append(
                    `<div class="card" style="width: 500px;">
                            <div class="row no-gutters">
                                <div class="col-sm-6">
                                    <img class="card-img-top"
                                         src="${i.rear_View}"
                                         alt="Card image cap">
                                </div>
                                <div class="col-sm-6">
                                    <div class="card-body">
                                        <h4 class="card-title" style="margin-top: 15px">${i.vehiclebrand}</h4>
                                        <small>${i.vehicleSeatCapacity}</small><br>
                                        <small>${i.vehicleFueltype}</small><br>
                                        <small>${i.transmission}</small><br>

                                    </div>
                                </div>
                            </div>
                        </div>`);
            }


        },
        error: function (error) {
            loadAllHotel();
            loadAllVehicle();
        }
    })

}*/

/*--------------------------------------------------*/

/*$("#searchHotel").on("click", function () {
    filterHotel();
});

function filterHotel() {
    $("#hotelSection").empty();
    hotelRate = $("#hotelRate").val();
    hotelLocation = $("#hotelLocation").val();
    $.ajax({
        url: "http://localhost:8081/hotelServer/api/v1/hotel/filterHotel?startRate=" + hotelRate +
            "&locations=" + hotelLocation,
        method: "GET",
        dataType: "json",
        success: function (res) {
            for (let i of res) {
                $("#hotelSection").append(
                    `<div class="card" style="width: 500px;">
                         <div class="row no-gutters">
                              <div class="col-sm-6">
                                   <img class="card-img-top"
                                             src="${i.hotelImage}"
                                             alt="Card image cap">
                              </div>
                                    <div class="col-sm-6">
                                        <div class="card-body">
                                            <h4 class="card-title" style="margin-top: 15px">${i.hotelName}</h4>
                                            <small>${i.hotelLocation}</small><br>
                                            <small>${i.hotelRate}</small>
                                        </div>
                                    </div>
                         </div>
                    </div>`);
            }
        },
        error: function (error) {
            loadAllHotel();
        }
    });
}

/!*------------------------------------------------------*!/

$("#searchVehicle").on("click", function () {
    filterVehicle();
});

function filterVehicle() {
    $("#vehicleSection").empty();
    filter_Passengers = $("#filterpassengers").val();
    filter_Transmission = $("#filterTransmission").val();
    filter_Fueltype = $("#filterFueltype").val();

    $.ajax({
        url2: indexBaseUrl + "vehicle/filterVehicle?passengers=" + filter_Passengers + "&transmission" + filter_Transmission + "&fuelType" + filter_Fueltype,
        method: "GET",
        dataType: "json",
        success: function (res) {
            for (let i of res) {
                let url2 = i.image.vehicle_Font_View;
                $("#vehicleSection").append(
                    `<div class="card" style="width: 500px;">
                            <div class="row no-gutters">
                                <div class="col-sm-6">
                                    <img class="card-img-top"
                                         src="${indexBaseUrl + url2}"
                                         alt="Card image cap">
                                </div>
                                <div class="col-sm-6">
                                    <div class="card-body">
                                        <h4 class="card-title" style="margin-top: 15px">${i.vehiclebrand}</h4>
                                        <small>${i.vehicleSeatCapacity}</small><br>
                                        <small>${i.vehicleFueltype}</small><br>
                                        <small>${i.transmission}</small><br>

                                    </div>
                                </div>
                            </div>
                        </div>`);
            }
        },
        error: function (error) {
            loadAllVehicle();
        }
    });
}*/
