getAllItem();


$("#itmSave").click(function () {

    if (checkAllItem()) {
        saveItem();
    } else {
        //alert("Error");
        swal("Error", "Error Item Save.!", "error");
    }
});

$(document).ready(function () {
    setTime();
    setDate();
    $("#itmCode").prop('disabled', true);
    $("#itmName").prop('disabled', true);
    $("#itmPrice").prop('disabled', true);
    $("#itmQTY").prop('disabled', true);

    $('#itemThead').css({
        'width': '600px',
        'display': 'flex'
    });
    $('#itemThead>th').css({
        'flex': '1',
        'max-width': 'calc(100%/4*1)'
    });
    $('#itemTable').css({
        'max-height': '370px',
        'overflow-y': 'auto',
        'display': 'table-caption'
    });
    $('#itemTable>tr').css({
        'width': '600px',
        'display': 'flex'
    });
    $('#itemTable>tr>td').css({
        'flex': '1',
        'max-width': 'calc(100%/4*1)'
    });
});
function generateItemId() {
    loadItemId().then(function (id) {
        $("#itmCode").val(id);
    }).catch(function (error) {
        console.error("Error loading item code:", error);
    });
}
function loadItemId() {
    return new Promise(function (resolve, reject) {
        var ar;
        $.ajax({
            url: "http://localhost:8080/BackEnd/item/getGenId",
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

function loadItemAr() {
    return new Promise(function (resolve, reject) {
        var ar;
        $.ajax({
            url: "http://localhost:8080/BackEnd/item/getAll",
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

$('#itmAdd').click(function () {
    $("#itmCode").prop('disabled', false);
    $("#itmName").prop('disabled', false);
    $("#itmPrice").prop('disabled', false);
    $("#itmQTY").prop('disabled', false);

    $(this).find("#itmCode").focus();
    generateItemId();
    setItemClBtn();
});

function bindItemTrrEvents() {
    $('#itemTable>tr').click(function () {

        let id = $(this).children().eq(0).text();
        let name = $(this).children().eq(1).text();
        let unPrice = $(this).children().eq(2).text();
        let Qty = $(this).children().eq(3).text();


        $("#itmCode").val(id);
        $("#itmName").val(name);
        $("#itmQTY").val(Qty);
        $("#itmPrice").val(unPrice);

        $("#itmCode").prop('disabled', false);
        $("#itmName").prop('disabled', false);
        $("#itmQTY").prop('disabled', false);
        $("#itmPrice").prop('disabled', false);
        $("#itmUpdate").prop('disabled', false);
        $("#itmDelete").prop('disabled', false);
        setItemBtn();

    });
}

$("#itmDelete").click(function () {

    let id = $("#itmCode").val();

    validItem(id).then(function (isValid) {
        if (isValid == false) {
            //alert("No such Item..please check the Code");
            swal("Error", "No such Item..please check the Code", "error");
            clearItemInputFields();
        } else {
            /*let consent = confirm("Do you want to delete.?");
            if (consent) {*/
            swal("Do you want to delete this Item.?", {
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
                        url: "http://localhost:8080/BackEnd/item?itmCode=" + id,
                        method: "DELETE",
                        success: function (res) {
                            console.log(res);
                            //alert("Item Delete Successfully");
                            swal("Deleted", "Item Delete Successfully", "success");
                            clearItemInputFields();
                            getAllItem();
                        },
                        error: function (ob, textStatus, error) {
                            //alert(textStatus + " : Error Item Not Delete")
                            swal("Error", textStatus + "Error Item Not Delete", "error");
                        }
                    });
                }
            });
        }
    });
    $("#itmCode").prop('disabled', true);
    $("#itmName").prop('disabled', true);
    $("#itmQTY").prop('disabled', true);
    $("#itmPrice").prop('disabled', true);

});

$("#itmUpdate").click(function () {

    let id = $("#itmCode").val();
    validItem(id).then(function (isValid) {
        if (isValid) {
            /*let consent = confirm("Do you really want to update this Item.?");
            if (consent) {}*/
            swal("Do you really want to update this Item.?", {
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
                    var array = $("#itmForm").serializeArray();
                    var data = {};
                    array.forEach(function (field) {
                        data[field.name] = field.value;
                    });
                    console.log(data)
                    $.ajax({
                        url: "http://localhost:8080/BackEnd/item",
                        method: "PUT",
                        data: JSON.stringify(data),
                        contentType: "application/json",
                        success: function (res) {
                            console.log(res);
                            //alert("Item Update Successfully")
                            swal("Updated", "Item Update Successfully", "success");
                            getAllItem();
                        },
                        error: function (ob, textStatus, error) {
                            //alert(textStatus+" : Error Item Not Update");
                            swal("Error", textStatus + "Error Item Not Update", "error");
                        }
                    });
                    $("#itmCode").prop('disabled', true);
                    $("#itmName").prop('disabled', true);
                    $("#itmQTY").prop('disabled', true);
                    $("#itmPrice").prop('disabled', true);
                    clearItemInputFields();
                }
            });
        } else {
            //alert("No such Item..please check the Code");
            swal("Error", "No such Item..please check the Code", "error");
        }
    });
});

$("#itmClear").click(function () {
    clearItemInputFields();
});


function saveItem() {

    let id = $("#itmCode").val();
    validItem(id).then(function (isValid) {
        console.log(isValid)
        if (!isValid) {
            console.log(isValid)
            /*var array = $("#itmForm").serializeArray();
            var data = {};
            array.forEach(function (field) {
                data[field.name] = field.value;
            });*/
            var data = $("#itmForm").serialize();
            $.ajax({
                url: "http://localhost:8080/BackEnd/item",
                method: "POST",
                data: data,
                contentType: "application/x-www-form-urlencoded",
                success: function (res, textStatus, jsXH) {
                    console.log(res);
                    //alert("Item Added Successfully");
                    swal("Saved", "Item Added Successfully", "success");
                    getAllItem();
                },
                error: function (ob, textStatus, error) {
                    //alert(textStatus+" : Error Item Not Added")
                    swal("Error", textStatus + " : Error Item Not Added", "error");
                }
            });
        } else {
            // alert("Item already exits.!");
            swal("Error", "Item already exits.!", "error");
            clearItemInputFields();
        }
    });
}

function getAllItem() {
    $("#itemTable").empty();
    $.ajax({
        url: "http://localhost:8080/BackEnd/item/getAll",
        method: "GET",
        success: function (res) {
            console.log(res);
            for (var r of res) {
                let row = `<tr>
                     <td>${r.itmCode}</td>
                     <td>${r.itmName}</td>
                     <td>${r.itmPrice}</td>
                     <td>${r.itmQTY}</td>
                    </tr>`;

                $("#itemTable").append(row);
                $('#itemTable').css({
                    'max-height': '370px',
                    'overflow-y': 'auto',
                    'display': 'table-caption'
                });
                $('#itemTable>tr').css({
                    'width': '600px',
                    'display': 'flex'
                });
                $('#itemTable>tr>td').css({
                    'flex': '1',
                    'max-width': 'calc(100%/4*1)'
                });
                bindItemTrrEvents();
            }
        }
    });
}

function validItem(id) {
    return new Promise(function (resolve, reject) {
        $.ajax({
            url: "http://localhost:8080/BackEnd/item/search/" + id,
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

function searchItem(id) {
    console.log(id);
    return new Promise(function (resolve, reject) {
        $.ajax({
            url: "http://localhost:8080/BackEnd/item/search/" + id,
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

$('#itmSearch').click(function () {
    let id = $("#itmCode").val();
    searchItem(id).then(function (res) {
        $("#itmName").val(res.itmName);
        $("#itmPrice").val(res.itmPrice);
        $("#itmQTY").val(res.itmQTY);
    });
    setItemClBtn();
});
