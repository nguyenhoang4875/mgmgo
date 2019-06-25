function checkForm(form) {
    let nameEdit1 = document.getElementById("title");
    let desEdit1 = document.getElementById("description");
    if (nameEdit1.value.length == 0 || nameEdit1.value.toString().trim().length == 0 || nameEdit1.value.toString().trim().length > 100) {
        return false;
    }
    if (desEdit1.value.length == 0 || desEdit1.value.toString().trim().length == 0 || desEdit1.value.toString().trim().length > 100000) {
        return false;
    }
    return true;
}

$(document).ready(function () {
    let title = $("#title");
    title.on('input', function () {
        console.log(title.val());
        let nameEdit = title.val(),
            alertNameMes = document.getElementById("alertName");
        if (nameEdit.length == 0 || nameEdit.toString().trim().length == 0 || nameEdit.toString().trim().length > 100) {
            alertNameMes.innerHTML = "Name must not be empty, not whitespace-only, not longer than 100 characters";
        } else {
            alertNameMes.innerHTML = "";
        }
    })
})

$(document).ready(function () {
    let title = $("#description");
    title.on('input', function () {
        console.log(title.val());
        let desEdit = title.val(),
            alertDesMes = document.getElementById("alertDes");
        if (desEdit.length == 0 || desEdit.toString().trim().length == 0 || desEdit.toString().trim().length > 100000) {
            alertDesMes.innerHTML = "Description must not be empty, not whitespace only, not longer than 100.000 characters";
        } else {
            alertDesMes.innerHTML = "";
        }
    })
})