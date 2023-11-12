function saveUpdateAlert(value1, value2) {
    Swal.fire({
        position:'bottom-end',
        icon: 'success',
        title: value1 + ' has been ' + value2,
        showConfirmButton: false,
        timer: 2500
    });
}
function unSuccessUpdateAlert(value1, value2) {
    Swal.fire({
        position: 'bottom-end',
        icon: 'error',
        title: value1 + " "+value2,
        showConfirmButton: false,
        timer: 1500
    })
}
