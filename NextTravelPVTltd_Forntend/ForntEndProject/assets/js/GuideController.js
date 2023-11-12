autoGenerateId();
loadAllGuide();

//Save Guider
$("#saveGuidebtn").click(function () {
    let formData = new FormData($("#guideFrom")[0]);
    formData.append("guideId", $("#guideid").val());
    formData.append("guideName", $("#guideName").val());
    formData.append("address", $("#guideAddress").val());
    formData.append("gender", $("#guide_Gender").val());
    formData.append("number", $("#guideNumber").val());
    formData.append("experience", $("#guideExperience").val());
    formData.append("manDayValue", $("#guideDayValue").val());
    formData.append("guideImage", $("#GuideImage")[0].files[0]);
    formData.append("nicImage", $("#NicImage")[0].files[0]);
    formData.append("guideIDImage", $("#GuideIDImage")[0].files[0]);

    $.ajax({
        url: "http://localhost:8989/nextTravelPVTLtdGuideService/api/v1/guide/saveGuide",
        method: "POST",
        date: formData,
        contentType: false,
        processData: false,
        success: function (res) {
            console.log("Success:", res);
            loadAllGuide();
            clearTextFields();
            alert("Successfully....!");
        },
        error:function (error){
            console.log("Response Text:", error);
            alert("Try again....!");
        }
    })
})


//update Guider
$("#updateGuidebtn").click(function () {
    let formData = new FormData($("#guideFrom")[0]);
    formData.append("guide_id", $("#guideid").val());
    formData.append("guide_Name", $("#guideName").val());
    formData.append("guide_Address", $("#guideAddress").val());
    formData.append("guide_Gender", $("#guide_Gender").val());
    formData.append("guide_Number", $("#guideNumber").val());
    formData.append("guide_Experience", $("#guideExperience").val());
    formData.append("guide_Day_Value", $("#guideDayValue").val());
    formData.append("Guide_Image", $("#GuideImage")[0].files[0]);
    formData.append("Nic_Image", $("#NicImage")[0].files[0]);
    formData.append("Guide_ID_Image", $("#GuideIDImage")[0].files[0]);

    $.ajax({
        url: "http://localhost:9696/guideServer/api/v1/guide/update",
        method: "PUT",
        data: formData,
        contentType: false,
        processData: false,
        success: function (res) {
            loadAllGuide();
            clearTextFields();
            alert("Successfully....!");
        },
        error:function (error){
            console.log(error);
            alert("Try again....!");
        }
    })
});


//delete Guider
$("#deleteGuidebtn").click(function () {
    let id = $("#guideid").val();
    $.ajax({
        url:"http://localhost:9696/guideServer/api/v1/guide?id" + id,
        method: "DELETE",
        dataType: "json",
        success: function (res) {
            loadAllGuide();
            console.log(resp);
            clearTextFields();
            alert("Successfully....!");

        },
        error:function (error){
            console.log(error);
            alert("Successfully....!");
        }
    })
});


//Search Guider
function searchGuideId() {
    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById("searchbtn");
    filter = input.value.toUpperCase();
    table = document.getElementById("giudeTable");
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

//Get All Guider details
function loadAllGuide() {
    $("#giudeTable").empty();
    $.ajax({
        url:  "http://localhost:9696/guideServer/api/v1/guide/loadAllguide",
        method: "GET",
        dataType: "json",
        success: function (res) {
            console.log(res);
            for (let i of res.data) {
                let guide_id = i.guide_id;
                let guide_Name = i.guide_Name;
                let guide_Address = i.guide_Address;
                let guide_Gender = i.guide_Gender;
                let guide_Number = i.guide_Number;
                let guide_Experience = i.guide_Experience;
                let guide_Day_Value = i.guide_Day_Value;

                let row="<tr><td>" + guide_id + "</td>" +
                    "<td>" + guide_Name + "</td>" +
                    "<td>" + guide_Address + "</td>" +
                    "<td>" + guide_Gender + "</td>" +
                    "<td>" + guide_Number + "</td>" +
                    "<td>" + guide_Experience + "</td>" +
                    "<td>" + guide_Day_Value + "</td></tr>";
                $("#giudeTable").append(row)
                console.log(row);
            }
            autoGenerateId();
            checkValidity(guideValidation);
            loadTextFieldValues();
            console.log(res.message);
        },
        error:function (error){
            console.log(error);
        }
    })
}


//Auto Generate id
function autoGenerateId() {
    $.ajax({
        url:"http://localhost:8989/nextTravelPVTLtdGuideService/api/v1/guide/getGuideByRandom",
        method:"GET",
        //contentType: "application/json",
        dataType: "json",
        success: function (resp) {
            $("#guideid").val(resp);
        },

        error:function (error){
        console.log(error);
    }
    })
}



//-------------------------load text field value---------------------------

function loadTextFieldValues(){
    $("#giudeTable>tr").on("click",function (){
        let guide_id = $(this).children().eq(0).text();
        let guide_Name = $(this).children().eq(1).text();
        let guide_Address = $(this).children().eq(2).text();
        let guide_Gender = $(this).children().eq(3).text();
        let guide_Number = $(this).children().eq(4).text();
        let guide_Experience = $(this).children().eq(5).text();
        let guide_Day_Value = $(this).children().eq(6).text();

        $("#guideid").val(guide_id);
        $("#guideName").val(guide_Name);
        $("#guideAddress").val(guide_Address);
        $("#guideGender").val(guide_Gender);
        $("#guideNumber").val(guide_Number);
        $("#guideExperience").val(guide_Experience);
        $("#guideDayValue").val(guide_Day_Value);
    });

}


