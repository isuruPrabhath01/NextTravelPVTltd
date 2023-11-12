loadPackages();
autoGeneratePackageID();

//----------------save Package-------------------------
$("#saveBtnPackage").click(function () {
    let formData = new FormData($("#packageFrom")[0]);
    console.log(formData);
    $.ajax({
        url: "http://localhost:8989/nextTravelPVTLtdTravelPackage/api/v1/Package",
        method: "POST",
        headers:{
            "Authorization":localStorage.getItem("jwt")
        },
        data: formData,
        contentType: false,
        processData: false,
        success: function (res) {
            console.log("Success:", res);
            loadPackages();
            clearTextFields();
            alert("Successfully...!");
        },
        error: function (error) {
            loadPackages();
            console.log("Response Text:", error.responseText);
            alert("Try Again...!");
        }
    });
});

//----------------update Package-----------------------
$("#updateBtnPackage").click(function () {
    let formData = new FormData($("#packageFrom")[0]);
    $.ajax({
        url: "http://localhost:8989/nextTravelPVTLtdTravelPackage/api/v1/Package",
        method: "PUT",
        headers:{
            "Authorization":localStorage.getItem("jwt")
        },
        data: formData,
        contentType: false,
        processData: false,
        success: function (res) {
            alert("Successfully...!");
            loadPackages();
            clearTextFields();
        },
        error: function (error) {
            alert("Try Again...!");
            console.log(error);
        }
    });
});

//----------------delete Package-----------------------
$("#deleteBtnPackage").click(function () {
    let id = $("#packageId").val();
    $.ajax({
        url: "http://localhost:8989/nextTravelPVTLtdTravelPackage/api/v1/Package?packageId=" + id,
        method: "DELETE",
        headers:{
            "Authorization":localStorage.getItem("jwt")
        },
        dataType: "json",
        success: function (res) {
            console.log(res);
            alert("Successfully...!");
            loadPackages();
            clearTextFields();
        },
        error: function (error) {
            loadPackages();
            alert("Successfully...!");
            console.log(error);
        }
    });
});

//------------------Auto Generate package id--------------------------------
function autoGeneratePackageID() {
    $.ajax({
        url: "http://localhost:8989/nextTravelPVTLtdTravelPackage/api/v1/Package/genarateId",
        method: "GET",
        headers:{
            "Authorization":localStorage.getItem("jwt")
        },
        //contentType: "application/json",
        dataType: "json",
        success: function (res) {
            $("#packageId").val(res);
        },
        error: function (error) {
            console.log(error);
        }
    });
}



//---------------------------get all packages---------------------------------------
function loadPackages() {
    $("#packageTable").empty();
    $.ajax({
        url: "http://localhost:8989/nextTravelPVTLtdTravelPackage/api/v1/Package",
        method: "GET",
        headers:{
            "Authorization":localStorage.getItem("jwt")
        },
        dataType: "json",
        success: function (res) {
            console.log(res);
            for (let i of res) {
                let id = i.packageId;
                let category = i.packageCategory;
                let packageFee = i.price;
                let hotelNightCount = i.nightCount;
                let hotelDayCount = i.dayCount;
                let hotelHeadCount = i.totalHeadCount;

                let row="<tr><td>" + id + "</td>" +
                    "<td>" + category + "</td>" +
                    "<td>" + packageFee + "</td>" +
                    "<td>" + hotelNightCount + "</td>" +
                    "<td>" + hotelDayCount + "</td>" +
                    "<td>" + hotelHeadCount + "</td></tr>";
                $("#packageTable").append(row)
            }
            autoGeneratePackageID();
            checkValidity(packageValidation);
            loadTextFieldValues();
        },
        error: function (error) {
            console.log(error);
        }
    })
}

//----------------------------load Text Field Values-------------------------------------
function loadTextFieldValues(){
    $("#packageTable>tr").on("click",function (){
        let packageId =$(this).children().eq(0).text();
        let packageCategory = $(this).children().eq(1).text();
        let price = $(this).children().eq(2).text();
        let nightCount = $(this).children().eq(3).text();
        let dayCount = $(this).children().eq(4).text();
        let totalHeadCount = $(this).children().eq(5).text();

        $("#packageId").val(packageId);
        $("#packageCategory").val(packageCategory);
        $("#price").val(price);
        $("#nightCount").val(nightCount);
        $("#dayCount").val(dayCount);
        $("#totalHeadCount").val(totalHeadCount);

    });
}

//---------------------clear---------------------------
function clearTextFields() {
    $("#packageId").val("");
    $("#packageCategory").val("");
    $("#price").val("");
    $("#nightCount").val("");
    $("#dayCount").val("");
    $("#totalHeadCount").val("");
}


//------------------search Packages-----------------------------------


function searchPackageId() {
    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById("searchPackage");
    filter = input.value.toUpperCase();
    table = document.getElementById("packageTable");
    tr = table.getElementsByTagName("tr");
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[0];
        if (td) {
            txtValue = td.textContent || td.innerText;
            if (txtValue.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}

//--------------------------load package value--------------------------------------------------------------------
    function loadPackageValues() {
        var loadPackageValue = $("#packageCategory").val();
        $.ajax({
            url: packagedBaseUrl + "package/loadPackage/?packageValue" + loadPackageValue,
            method: "GET",
            contentType: "json",
            success: function (res) {
                $("#price").val(res.value);
            }, error: function (error) {
                console.log(error);
            }
        });
    }

