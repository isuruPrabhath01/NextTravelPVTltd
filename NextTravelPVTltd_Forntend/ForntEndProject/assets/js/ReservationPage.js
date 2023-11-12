loadAllPackageName()
loadBookingDetails();
getBookingByUserId();
autoGeneratebookingid();
//----------------current user id---------------------------
let userId;
$.ajax({
    url: bookingBaseUrl + "getBookingByUserId?userId="+userId,
    method: "GET",
    success: function (res) {
        userId = res.data.userId;
        $("#userId").val(res.data.userId);
    }
});
//------------------Auto Generate Booking id--------------------------------
function autoGeneratebookingid() {
    $.ajax({
        url: "http://localhost:8989/nextTravelPVTLtdTravelPackage/api/v1/booking/genarateId",
        method: "GET",
        contentType: "application/json",
        dataType: "json",
        success: function (res) {
            $("#bookingReservationId").val(res);
        }
    })
}
//----------------------get booking by user id-----------------------------------
function getBookingByUserId() {
    $("#reservationCustomerId").val(localStorage.getItem('username'));
}
//-----------------------------------Load packageName------------------------------------
function loadAllPackageName() {
    $("#package_Category").empty();
    packgeName=$("#package_Category").val();
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
                $("#package_Category").append(`<option>${package_name}</option>`)
            }
        }, error: function (error) {
            console.log(error);
        }
    })
}

//-------------------load Package Details----------------------------------------
$("#package_Category").click(function (){
    var package_Category=$("#package_Category").val();
    $.ajax({
        url: "http://localhost:8989/nextTravelPVTLtdTravelPackage/api/v1/Package/getPackageByPackageCategory?packageCategory="+package_Category,
        method:"GET",
        contentType:"application/json",
        dataType:"json",
        success:function (res){
            console.log(res);
            $("#package_value").val(res.value);
            $("#headCount").val(res.head_count);
            $("#nightCount").val(res.night_Count);
            $("#dayCount").val(res.dayCount);
        },
        error: function (error) {
            console.log(error);
        }
    });
    var vehicleCategory=$("#package_Category").val();
    $("#vehicleRegId").empty();
    $.ajax({
        url:"api/v1/vehicle/getAllVehicleByCategory?vehicleCategory="+vehicleCategory,
        method:"GET",
        contentType:"application/json",
        datatype:"json",
        success:function (res){
            console.log(res);
            for(let i of res){
                let vehicleRegId=i.vehicleRegId;
                $("#vehicleRegId").append(`<option>${vehicleRegId}</option>`);
            }
        },
        error: function (error) {
            console.log(error);
        }
    })

    var hotelCategory=$("#package_Category").val();
    $("#hotelID").empty();
    $.ajax({
        url:"api/vi/hotel/getAllHotelPackageCategory?hotelCategory="+hotelCategory,
        method:"GET",
        contentType:"application/json",
        dataType:"json",
        success:function (res){
            console.log(res);
            for (let i of res){
                let hotelId=i.hotelId;
                $("#hotelID").append(`<option>${hotelId}</option>`);
            }
        }
    })
});


//----------------------Save reservation-----------------------------

$("#saveBookingbtn").on("click", function () {
    let formData = new FormData($("#raservationDetails")[0]);
    formData.append("bookingId", $("#bookingReservationId").val());
    formData.append("packageId", $("#package_Category").val());
    formData.append("userId", $("#reservationCustomerId").val());
    formData.append("currentlyDate", $("#date").val());
    formData.append("startDate", $("#startDate").val());
    formData.append("endDate", $("#endDate").val());
    formData.append("nightCount", $("#endDate").val());
    formData.append("dayCount", $("#endDate").val());
    formData.append("adultsCount", $("#adults").val());
    formData.append("childrenCount", $("#children").val());


    $.ajax({
        url: "http://localhost:8989/nextTravelPVTLtdTravelPackage/api/v1/booking",
        method: "POST",
        data: formData,
        dataType: "json",
        contentType: false,
        processData: false,
        success: function (res) {
            console.log(res);
            loadBookingDetails();
            clearTextFields();
            autoGeneratebookingid();
            alert("Successfully...!");
        },
        error: function (error) {
            console.log(error);
            alert("Try Again...!");
        }
    });
});




$("#updateBookingbtn").on("click", function () {
    let formData = new FormData($("#raservationDetails")[0]);
    formData.append("bookingId", $("#bookingReservationId").val());
    formData.append("packageId", $("#package_Category").val());
    formData.append("userId", $("#reservationCustomerId").val());
    formData.append("currentlyDate", $("#date").val());
    formData.append("startDate", $("#startDate").val());
    formData.append("endDate", $("#endDate").val());
    formData.append("nightCount", $("#endDate").val());
    formData.append("dayCount", $("#endDate").val());
    formData.append("adultsCount", $("#adults").val());
    formData.append("childrenCount", $("#children").val());


    $.ajax({
        url: "http://localhost:8989/nextTravelPVTLtdTravelPackage/api/v1/booking",
        method: "PUT",
        data: formData,
        dataType: "json",
        contentType: false,
        processData: false,
        success: function (res) {
            console.log(res);
            loadBookingDetails();
            clearTextFields();
            autoGeneratebookingid();
            alert("Successfully...!");
        },
        error: function (error) {
            console.log(error);
            alert("Try Again...!");
        }
    });
});
//-------------------load all details of Reservation-------------------------------
function loadBookingDetails(){
    $("#customerAccountBookingTable").empty();
    $.ajax({
        url:"",
        method:"GET",
        dataType:"json",
        success:function (res){
            console.log(res);
            for (let i of res.data) {
                let bookingReservation_Id = i.bookingId;
                let package_Category = i.package_Category;
                let hotelName = i.filter_hotel_name;
                let vehicleName = i.vehiclebrand;
                let guideName = i.guideName;
                let starDate = i.start_Date;
                let endDate = i.end_Date;
                let fullPayment = i.total;

                let row="<tr><td>" + bookingReservation_Id + "</td>" +
                    "<td>" + package_Category + "</td>" +
                    "<td>" + hotelName + "</td>" +
                    "<td>" + vehicleName + "</td>" +
                    "<td>" + guideName + "</td>" +
                    "<td>" + starDate + "</td>" +
                    "<td>" + endDate + "</td>" +
                    "<td>" + fullPayment + "</td>" +
                    "</tr>";
                $("#customerAccountBookingTable").append(row)
            }
            autoGeneratebookingid();
        },
        error:function (error){
            console.log(error);
        }
    })
}

function loadTextFieldValues(){
    $("#customerAccountBookingTable>tr").on("click",function (){
        let hotel_Id = $(this).children().eq(0).text();
        let hotel_Name =  $(this).children().eq(1).text();
        let hotel_Rate =  $(this).children().eq(2).text();
        let hotel_Category =  $(this).children().eq(3).text();
        let hotel_Location =  $(this).children().eq(4).text();
        let hotel_Coordinates =  $(this).children().eq(5).text();
        let hotel_Email =  $(this).children().eq(6).text();
        let hotel_Number1 =  $(this).children().eq(7).text();
        let hotel_Number2 =  $(this).children().eq(8).text();
        let pets_Allowed =  $(this).children().eq(9).text();
        let hotel_Fee =  $(this).children().eq(10).text();
        let cancellation_Criteria = $(this).children().eq(11).text();

        $("#hotel_Id").val(hotel_Id);
        $("#hotel_Name").val(hotel_Name);
        $("#hotel_Rate").val(hotel_Rate);
        $("#hotel_Category").val(hotel_Category);
        $("#hotel_Location").val(hotel_Location);
        $("#hotel_Coordinates").val(hotel_Coordinates);
        $("#hotel_Email").val(hotel_Email);
        $("#hotel_Number1").val(hotel_Number1);
        $("#hotel_Number2").val(hotel_Number2);
        $("#pets_Allowed").val(pets_Allowed);
        $("#hotel_Fee").val(hotel_Fee);
        $("#cancellation_Criteria").val(cancellation_Criteria);
    })
}