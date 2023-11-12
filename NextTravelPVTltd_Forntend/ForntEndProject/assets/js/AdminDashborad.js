
//------------------------------Number of Vehicle-------------------------------------
$("#VehiclesCount").val("00");
$.ajax({
    url: "http://localhost:8989/nextTravelPVTLtdVehicleService/api/v1/Vehicle/getCountOfVehicles",
    method: "GET",
    headers:{
        "Authorization":localStorage.getItem("jwt")
    },
    contentType: "application/json",
    dataType: "json",
    success: function (resp) {
        let num = resp.count;
        console.log(num);
        $("#VehiclesCount").text(num);
    },
    error: function (error) {
        console.log(error);
    }
});


//---------------------------------------Number of Today Booking----------------------------------
$("#tBookingCount").val("0");
$.ajax({
    url: "http://localhost:8989/nextTravelPVTLtdTravelPackage/api/v1/booking/getCountOfBooking",
    method: "GET",
    headers:{
        "Authorization":localStorage.getItem("jwt")
    },
    contentType: "application/json",
    dataType: "json",
    success: function (resp) {
        let num = resp.count;
        $("#tBookingCount").text(num);

    },
    error:function (error){
        console.log(error);
    }
});


//------------------------Number of Hotel--------------------------------
$("#HotelsCount").val("0");
$.ajax({
    url:"http://localhost:8989/nextTravelPVTLtdHotelService/api/v1/Hotel/getCountOfHotels",
    method:"GET",
    headers:{
        "Authorization":localStorage.getItem("jwt")
    },
    contentType:"application/json",
    dataType:"json",
    success:function (res){
        let num=resp.count;
        $("#HotelsCount").text(num);
    },
    error:function (error){
        console.log(error)
    }
})

//--------------------------Number of Package-----------------------
$("#PackagesCount").val("0");
$.ajax({
    url:"http://localhost:8989/nextTravelPVTLtdTravelPackage/api/v1/Package/getCountOfPackage",
    method:"GET",
    headers:{
        "Authorization":localStorage.getItem("jwt")
    },
    contentType:"application/json",
    dataType:"json",
    success:function (res){
        let num=resp.count;
        $("#PackagesCount").text(num);
    },
    error:function (error){
        console.log(error)
    }
})


//---------------------------------------Number of Customer--------------------------------------
$("#customerCount").val("0");
$.ajax({
    url:"http://localhost:8989/nextTravelPVTLtdUserService/api/v1/user/countByRoleType?roleType=CUSTOMER",
    method:"GET",
    headers:{
        "Authorization":localStorage.getItem("jwt")
    },
    contentType:"application/json",
    dataType:"json",
    success:function (res){
        let num=resp.count;
        $("#customerCount").text(num);
    },
    error:function (error){
        console.log(error)
    }
})

//------------------------------Number of User---------------------------------------
$("#UsersCount").val("0");
$.ajax({
    url:"http://localhost:8989/nextTravelPVTLtdUserService/api/v1/user/countByRoleType?roleType=USER",
    method:"GET",
    headers:{
        "Authorization":localStorage.getItem("jwt")
    },
    contentType:"application/json",
    dataType:"json",
    success:function (res){
        let num=resp.count;
        $("#UsersCount").text(num);
    },
    error:function (error){
        console.log(error)
    }
})
