$(document).ready(function () {
    init();
    $("input.form-control").on('input',handleTextInputChange);
    $("#file-input").change(handleFileChange);
    $("#picture-save-btn").click(handleSaveClick);
});

function handleTextInputChange(e) {
    var input = $(e.target);
    console.log(input);
    console.log(input.parent().find(".invalid-feedback"));
    if(input.val().length==0) input.addClass("is-invalid");
    else if(input.parent().find(".invalid-feedback").length==0) input.removeClass("is-invalid");
}

function handleFileChange() {
    showModal();
}

function handleSaveClick() {
    $("#crop-container").croppie("result", {type: 'blob'}).then(function (res) {
        postImage(res);
    })
}

function postImage(img) {
    var formData = new FormData();
    formData.append('photo', img);

    $.ajax({
        url: "/api/image/user",
        type: "POST",
        cache: false,
        contentType: false,
        processData: false,
        data: formData,
        beforeSend: function (xhr) {
            xhr.setRequestHeader($("meta[name=csrf-header]").attr("content"), $("meta[name=csrf-token]").attr("content"));
        }
    }).done(function (data) {
        if(data.message) setPictureMessage(data.message);
        else{
            hideModal();
            hidePictureMessage();
            $("#profile-picture").attr("src","/api/image/"+data.id);
            $("#user-thumbnail").attr("src","/api/image/"+data.id);
        }
    }).fail(function (data) {
        if(data.responseJSON.message) setPictureMessage(data.responseJSON.message);
        else setPictureMessage("Something went wrong! Please try again later.");
    });
}

function croppieBind(file) {
    $("#crop-container").croppie('bind', {
        url: file
    });
}

function init() {
    initCroppie();
    cfgModal();
}

function initCroppie() {
    $("#crop-container").croppie({
        viewport: {
            width: 150,
            height: 150,
            type: 'square'
        },
    });
}

function cfgModal() {
    $('#crop-modal').on('shown.bs.modal', function (e) {
        var reader = new FileReader();

        reader.onload = function (e) {
            croppieBind(e.target.result);
        }

        reader.readAsDataURL($("#file-input").prop("files")[0]);
    });
}

function getFilePath() {
    return $("#file-input").val();
}

function setPictureMessage(msg) {
    $("#picture-message").removeClass("d-none");
    $("#picture-message").html(msg);
}


function hidePictureMessage(){
    $("#picture-message").addClass("d-none");
}

function showModal() {
    $("#crop-modal").modal('show');
}

function hideModal() {
    $("#crop-modal").modal('hide');
}