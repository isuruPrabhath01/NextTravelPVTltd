autoGenerateid();
loadAllRegisterUser();

//Save Customer form RegisterPage.html
$("#register_button").click(function (){
    let formData=new FormData($("#registerForm")[0]);
    formData.append("userId", $("#userId").val());
    formData.append("name", $("#reg_Name").val());
    formData.append("nic", $("#reg_NIC").val());
    formData.append("age", $("#reg_Age").val());
    formData.append("gender", $("#reg_Gender").val());
    formData.append("email", $("#reg_Email").val());
    formData.append("password", $("#reg_Password").val());
    formData.append("roleType", $("#reg_RoleType").val());
    formData.append("contactNumber", $("#rag_contactNumber").val());
    formData.append("address", $("#rag_address").val());
    formData.append("profilePic", $("#newProfilePhoto")[0].files[0]);

    console.log(formData.get("profilePic"));

    $.ajax({
        url:"http://localhost:8989/nextTravelPVTLtdUserService/api/v1/user/saveUser",
        method:"POST",
        data:formData,
        contentType:false,
        processData: false,
        success:function (res){
            alert("Successfully....!");
            clearTextFields();
            loadAllRegisterUser();
        },error: function (error) {
            console.log(error);
            alert("error");
        }
    })
})


//update Customer form ManageCustomer.html
$("#btnUpdateRegUser").click(function (){
    let formData=new FormData($("#registerForm")[0]);
    formData.append("userId", $("#userId").val());
    formData.append("name", $("#reg_Name").val());
    formData.append("nic", $("#reg_NIC").val());
    formData.append("age", $("#reg_Age").val());
    formData.append("gender", $("#reg_Gender").val());
    formData.append("email", $("#reg_Email").val());
    formData.append("password", $("#reg_Password").val());
    formData.append("roleType", $("#reg_RoleType").val());
    formData.append("contactNumber", $("#rag_contactNumber").val());
    formData.append("address", $("#rag_address").val());
    formData.append("profilePic", $("#newProfilePhoto")[0].files[0]);

    $.ajax({
        url:"http://localhost:8989/nextTravelPVTLtdUserService/api/v1/user/updateUser",
        method:"PUT",
        headers:{
            "Authorization":localStorage.getItem("jwt")
        },
        data:formData,
        contentType:false,
        processData: false,
        success:function (res){
            loadAllRegisterUser();
            alert("Successfully....!");
            clearTextFields();

        },
        error: function (error) {
            alert("error!");
            console.log(error);
        }
    })

});

//Delete Customer form ManageCustomer.html
$("#btnDeleteRegUser").click(function (){
    let userEmail=$("#reg_Email").val();
    console.log(userEmail);
    $.ajax({
        url:"http://localhost:8989/nextTravelPVTLtdUserService/api/v1/user/deleteUserByEmail?userEmail="+userEmail,
        method:"DELETE",
        headers:{
            "Authorization":localStorage.getItem("jwt")
        },
        dataType:"json",
        success:function (res){
            console.log(res);
            loadAllRegisterUser();
            alert("Successfully....!");
            clearTextFields();

        },
        error: function (error) {
            console.log(error);
            alert("Successfully....!");
            loadAllRegisterUser();
        }
    })

});

// Get All customer Details  form ManageCustomer.html
function loadAllRegisterUser(){
    $("#registerUserTable").empty();
    $.ajax({
        url:"http://localhost:8989/nextTravelPVTLtdUserService/api/v1/user/getAllUsers",
        method: "GET",
        headers:{
            "Authorization":localStorage.getItem("jwt")
        },
        dataType:"json",
        success:function (res){
            console.log(res);

            for (let i of res){
                let userId =i.userId;
                let name = i.name;
                let nic = i.nic;
                let age = i.age;
                let gender = i.gender;
                let email =i.email;
                let roleType = i.roleType;
                let contactNumber = i.contactNumber;
                let address =i.address;


                let row ="<tr><td>" + userId+"</td>" +
                    "<td>" + name + "</td>" +
                    "<td>" + nic+"</td>"+
                    "<td>" + age+"</td>"+
                    "<td>" + gender+"</td>"+
                    "<td>" + email+"</td>"+
                    "<td>" + roleType+"</td>"+
                    "<td>" + contactNumber+"</td>"+
                    "<td>" + address+"</td></tr>";
                $("#registerUserTable").append(row)
            }
            autoGenerateid();
            loadTextFieldValues();
        },
        error: function (error) {
            console.log(error);
        }
    })
}

//Auto Generate Customer id
function autoGenerateid() {
    $.ajax({
        url: "http://localhost:8989/nextTravelPVTLtdUserService/api/v1/user/genarateId",
        method: "GET",
        contentType: "application/json",
        dataType: "json",
        success: function (res) {
            $("#userId").val(res);
        },
        error: function (xhr, textStatus, errorThrown) {
            console.log("Error while generating user ID: " + textStatus);
            console.log("Error details: " + errorThrown);
        }
    });
}

function clearTextFields() {
    $("#userId").val("");
    $("#name").val("");
    $("#nic").val("");
    $("#age").val("");
    $("#gender").val("");
    $("#email").val("");
    $("#password").val("");
    $("#roleType").val("");
    $("#contactNumber").val("");
    $("#address").val("");
}

//----------------------------load Text Field Values-------------------------------------

function loadTextFieldValues() {
    $("#registerUserTable>tr").on("click", function () {
        let userId = $(this).children().eq(0).text();
        let name = $(this).children().eq(1).text();
        let nic = $(this).children().eq(2).text();
        let age = $(this).children().eq(3).text();
        let gender = $(this).children().eq(4).text();
        let email = $(this).children().eq(5).text();
        let roleType = $(this).children().eq(6).text();
        let contactNumber = $(this).children().eq(7).text();
        let address = $(this).children().eq(8).text();

        $("#userId").val(userId);
        $("#reg_Name").val(name);
        $("#reg_NIC").val(nic);
        $("#reg_Age").val(age);
        $("#reg_Gender").val(gender);
        $("#reg_Email").val(email);
        $("#reg_RoleType").val(roleType);
        $("#rag_contactNumber").val(contactNumber);
        $("#rag_address").val(address);
    });
}

