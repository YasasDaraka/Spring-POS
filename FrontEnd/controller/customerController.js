getAllCustomers();


$("#cusSave").click(function () {

    if (checkAll()) {
        var image = $("#capturedImage");
        var imageUrl = image.attr('src');
        if (!imageUrl) {
            //alert("Error");
            swal("Error", "Take Customer Photo.!", "error");
        } else {
            saveCustomer();
        }
    } else {
        alert("Error");
        swal("Error", "Error Customer Save.!", "error");
    }
});

$(document).ready(function () {
    setTime();
    setDate();
    $("#customerID").prop('disabled', true);
    $("#customerName").prop('disabled', true);
    $("#customerAddress").prop('disabled', true);

    $('#cusThead').css({
        'width': '600px',
        'display': 'flex'
    });
    $('#cusThead>th').css({
        'flex': '1',
        'max-width': 'calc(100%/3*1)'
    });
    $('#customerTable').css({
        'max-height': '370px',
        'overflow-y': 'auto',
        'display': 'table-caption'
    });
    $('#customerTable>tr').css({
        'width': '600px',
        'display': 'flex'
    });
    $('#customerTable>tr>td').css({
        'flex': '1',
        'max-width': 'calc(100%/3*1)'
    });
    var targetNode = document.getElementById('customer');
    var config = {attributes: true, attributeFilter: ['style']};
    var callback = function (mutationsList, observer) {
        for (var mutation of mutationsList) {
            if (mutation.attributeName === 'style') {
                var displayStyle = window.getComputedStyle(targetNode).getPropertyValue('display');
                if (displayStyle === 'none') {
                    stopWebcamStream();
                    $("#capturedImage").show();
                    $('#captureButton').css("background-color", "#007bff");
                    $('#captureButton').css("border-color", "#007bff");
                    $('#captureButton').text("Capture");
                    $("#capturedImage").attr('src', "assets/images/defaultCusPic.gif");
                }
            }
        }
    };
    var observer = new MutationObserver(callback);
    observer.observe(targetNode, config);
});

function generateCustomerId() {
    loadCusId().then(function (id) {
            $("#customerID").val(id);
    }).catch(function (error) {
        console.error("Error loading customer Id:", error);
    });
}
function loadCusId() {
    return new Promise(function (resolve, reject) {
        var ar;
        $.ajax({
            url: "http://localhost:8080/BackEnd/customer/getGenId",
            method: "GET",
            success: function (res) {
                console.log(res);
                ar = res;
                resolve(ar);
            },
            error: function (error) {
                reject(error);
            }
        });
    });
}

function loadCusAr() {
    return new Promise(function (resolve, reject) {
        var ar;
        $.ajax({
            url: "http://localhost:8080/BackEnd/customer/getAll",
            method: "GET",
            success: function (res) {
                console.log(res);
                ar = res;
                resolve(ar);
            },
            error: function (error) {
                reject(error);
            }
        });
    });
}

$('#cusAdd').click(function () {
    $("#customerID").prop('disabled', false);
    $("#customerName").prop('disabled', false);
    $("#customerAddress").prop('disabled', false);

    $(this).find("#customerID").focus();
    generateCustomerId();
    setClBtn();
});

function bindTrrEvents() {
    $('#customerTable>tr').click(function () {

        let id = $(this).children().eq(0).text();
        let name = $(this).children().eq(1).text();
        let address = $(this).children().eq(2).text();


        $("#customerID").val(id);
        $("#customerName").val(name);
        $("#customerAddress").val(address);

        $("#customerID").prop('disabled', false);
        $("#customerName").prop('disabled', false);
        $("#customerAddress").prop('disabled', false);
        $("#cusUpdate").prop('disabled', false);
        $("#cusDelete").prop('disabled', false);
        setBtn();
        searchCustomer(id).then(function (res){
            captureClear();
            $("#capturedImage").attr('src', res.proPic);
        });
    });
}

$("#cusDelete").click(function () {
    let id = $("#customerID").val();

    validCustomer(id).then(function (isValid) {
        if (isValid == false) {
            //alert("No such Customer..please check the ID");
            swal("Error", "No such Customer..please check the ID", "error");
            clearCustomerInputFields();
        } else {
            /*let consent = confirm("Do you want to delete.?");
            if (consent) {}*/
            swal("Do you want to delete this customer.?", {
                buttons: {
                    cancel1: {
                        text: "Cancel",
                        className: "custom-cancel-btn",
                    },
                    ok: {
                        text: "OK",
                        value: "confirm",
                        className: "custom-ok-btn",
                    }
                },
            }).then((value) => {
                if (value === "confirm") {
                    $.ajax({
                        url: "http://localhost:8080/BackEnd/customer?cusId=" + id,
                        method: "DELETE",
                        success: function (res) {
                            console.log(res);
                            // alert("Customer Delete Successfully");
                            swal("Deleted", "Customer Delete Successfully", "success");
                            clearCustomerInputFields();
                            captureClear();
                            getAllCustomers();
                        },
                        error: function (ob, textStatus, error) {
                            //alert(textStatus + " : Error Customer Not Delete")
                            swal("Error", textStatus + "Error Customer Not Delete", "error");
                        }
                    });
                }
            });
        }
    });

    $("#customerID").prop('disabled', true);
    $("#customerName").prop('disabled', true);
    $("#customerAddress").prop('disabled', true);

});

$("#cusUpdate").click(function () {
    let id = $("#customerID").val();
    validCustomer(id).then(function (isValid) {
        if (isValid) {
            /*let consent = confirm("Do you really want to update this customer.?");
            if (consent) {}*/
            swal("Do you really want to update this customer.?", {
                buttons: {
                    cancel1: {
                        text: "Cancel",
                        className: "custom-cancel-btn",
                    },
                    ok: {
                        text: "OK",
                        value: "confirm",
                        className: "custom-ok-btn",
                    }
                },
            }).then((value) => {
                if (value === "confirm") {
                    var array = $("#CusForm").serializeArray();
                    var data = {};
                    array.forEach(function (field) {
                        data[field.name] = field.value;
                    });
                    let haveImg = $("#capturedImage").attr('src');
                    if (haveImg.startsWith("data:image/png;base64,")){
                        data["proPic"] = haveImg;
                    }
                    console.log(data)
                    $.ajax({
                        url: "http://localhost:8080/BackEnd/customer",
                        method: "PUT",
                        data: JSON.stringify(data),
                        contentType: "application/json",
                        success: function (res) {
                            console.log(res);
                            //alert("Customer Update Successfully")
                            swal("Updated", "Customer Update Successfully", "success");
                            captureClear();
                            getAllCustomers();
                        },
                        error: function (ob, textStatus, error) {
                            //alert(textStatus+" : Error Customer Not Update");
                            swal("Error", textStatus + "Error Customer Not Update", "error");
                        }
                    });
                    $("#customerID").prop('disabled', true);
                    $("#customerName").prop('disabled', true);
                    $("#customerAddress").prop('disabled', true);
                    clearCustomerInputFields();
                }
            });

        } else {
            swal("Error", "No such Customer..please check the ID", "error");
            /*alert("No such Customer..please check the ID");*/
        }
    });

});

$("#cusClear").click(function () {
    clearCustomerInputFields();
    $("#cusIDError,#cusNameError,#cusAddressError").text("");
});


function saveCustomer() {
    let id = $("#customerID").val();
    validCustomer(id).then(function (isValid) {
        console.log(isValid)
        if (!isValid) {
            console.log(isValid)
            /*var array = $("#CusForm").serializeArray();
            var data = {};
            array.forEach(function (field) {
                data[field.name] = field.value;
            });*/
            var formData = $("#CusForm").serializeArray();
            var image = $("#capturedImage");
            var imageUrl = image.attr('src');
            formData.push({name: 'proPic', value: imageUrl});
            $.ajax({
                url: "http://localhost:8080/BackEnd/customer",
                method: "POST",
                data: formData,
                /*contentType: "application/x-www-form-urlencoded",*/
                success: function (res, textStatus, jsXH) {
                    console.log(res);
                    // alert("Customer Added Successfully");
                    swal("Saved", "Customer Added Successfully", "success");
                    getAllCustomers();
                },
                error: function (ob, textStatus, error) {
                    //alert(textStatus + " : Error Customer Not Added")
                    swal("Error", textStatus + " : Error Customer Not Added", "error");
                }
            });
        } else {
            //alert("Customer already exits.!");
            swal("Error", "Customer already exits.!", "error");
            clearCustomerInputFields();
        }
    });
}

function getAllCustomers() {

    $("#customerTable").empty();
    $.ajax({
        url: "http://localhost:8080/BackEnd/customer/getAll",
        method: "GET",
        success: function (res) {
            console.log(res);
            for (var r of res) {
                let row = `<tr>
                     <td>${r.id}</td>
                     <td>${r.name}</td>
                     <td>${r.address}</td>
                    </tr>`;
                $("#customerTable").append(row);
                $('#customerTable').css({
                    'max-height': '370px',
                    'overflow-y': 'auto',
                    'display': 'table-caption'
                });
                $('#customerTable>tr').css({
                    'width': '600px',
                    'display': 'flex'
                });
                $('#customerTable>tr>td').css({
                    'flex': '1',
                    'max-width': 'calc(100%/3*1)'
                });
                bindTrrEvents();
            }
        }
    });
}

function validCustomer(id) {
    return new Promise(function (resolve, reject) {
        $.ajax({
            url: "http://localhost:8080/BackEnd/customer/search/" + id,
            method: "GET",
            dataType: "json",
            success: function (res, textStatus, xhr) {
                console.log(res);
                if (xhr.status === 200) {
                    resolve(true);
                } else {
                    resolve(false);
                }
            },
            error: function (ob, textStatus, error) {
                resolve(false);
            }
        });
    });
}

function searchCustomer(id) {
    console.log(id);
    return new Promise(function (resolve, reject) {
        $.ajax({
            url: "http://localhost:8080/BackEnd/customer/search/" + id,
            method: "GET",
            dataType: "json",
            success: function (res) {
                console.log(res);
                resolve(res);
            },
            error: function (ob, textStatus, error) {
                resolve(error);
            }
        });
    });
}

$('#cusSearch').click(function () {
    let id = $("#customerID").val();
    searchCustomer(id).then(function (res) {
        $("#customerName").val(res.name);
        $("#customerAddress").val(res.address);
        captureClear();
        $("#capturedImage").attr('src', res.proPic);
    });
    setClBtn();
});
let videoStream;
$('#captureButton').click(function () {
    let text = $(this).text();
    var video = $('#video')[0];
    var canvas = $('#canvas')[0];
    var capturedImage = $('#capturedImage');

    var constraints = {
        video: true
    };

    if (text === "Capture") {
        $("#cusClear").prop("disabled", false);
        $(this).text("Take Picture");
        $(this).css("background-color", "#dc3545");
        $(this).css("border-color", "#dc3545");
        $(video).show();
        capturedImage.hide();

        navigator.mediaDevices.getUserMedia(constraints)
            .then((stream) => {
                videoStream = stream;
                video.srcObject = stream;
            })
            .catch((error) => {
                console.error('Error accessing webcam:', error);
            });
    } else if (text === "Take Picture") {
        $("#cusClear").prop("disabled", false);
        const context = canvas.getContext('2d');
        context.drawImage(video, 0, 0, canvas.width, canvas.height);

        const imageDataUrl = canvas.toDataURL('image/png');
        capturedImage.attr('src', imageDataUrl);
        capturedImage.show();
        $(this).css("background-color", "#007bff");
        $(this).css("border-color", "#007bff");
        $(this).text("Capture");
        stopWebcamStream();
        $(video).hide();
    }
});

function stopWebcamStream() {
    if (videoStream) {
        const tracks = videoStream.getTracks();
        tracks.forEach(track => track.stop());
        videoStream = null;
    }
}