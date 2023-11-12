

$("#loginButton").on('click', function () {
    login();
});

/*--------Login Function-----------*/
function login() {
    let loginRoleType = $("#roleType").val();
    let loginusername = $("#username").val();
    let loginPassword = $("#password").val();
    localStorage.setItem('username',loginusername);
    $.ajax({
        url: "http://localhost:8989/authenticationServer/auth/token?username=" + loginusername + "&password=" + loginPassword,
        method: "POST",
        success: function (res) {
            console.log(res)
            localStorage.setItem('jwt', "Bearer " + res)
            root();
        }, error: function (error) {
            console.log(error);
        }
    })

function root(){

        if (loginRoleType === "ADMIN") {
            console.log(loginRoleType);
            window.location.href = "../../adminSite/AdminDashboard.html";
        } else if (loginRoleType === "CUSTOMER") {
            window.location.href = "../../registerUserSite/RegisterUser.html";
        } else if (loginRoleType === "USER") {
            window.location.href = "../../userSite/UserDashboard.html";
        }
    }



}
