
getAllItem();


$("#itmSave").click(function () {

    if (checkAllItem()){
        saveItem();
    }else{
        alert("Error");
    }
});

$(document).ready(function(){
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
    loadItemAr().then(function (itemDB) {
        if (itemDB.length === 0) {
            $("#itmCode").val("I00-1");
        } else {
            console.log(itemDB[itemDB.length - 1].itmCode);
            var id = itemDB[itemDB.length - 1].itmCode.split("-")[1];
            var tempId = parseInt(id, 10);
            if (!isNaN(tempId)) {
                tempId = tempId + 1;
                $("#itmCode").val("I00-" + tempId);
            } else {
                console.error("Error converting Item Code to a number");
            }
        }
    }).catch(function (error) {
        console.error("Error loading Item data:", error);
    });
}
function loadItemAr(){
    return new Promise(function (resolve, reject) {
        var ar;
        $.ajax({
            url: "http://localhost:8080/BackEnd/item?info=getall",
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
$('#itmAdd').click(function(){
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
            alert("No such Item..please check the Code");
            clearItemInputFields();
        } else {
            let consent = confirm("Do you want to delete.?");
            if (consent) {
                $.ajax({
                    url: "http://localhost:8080/BackEnd/item?itmCode="+id,
                    method: "DELETE",
                    success:function (res) {
                        console.log(res);
                        alert("Item Delete Successfully");
                        clearItemInputFields();
                        getAllItem();
                    },
                    error:function (ob, textStatus, error) {
                        alert(textStatus+" : Error Item Not Delete")
                    }
                });
            }
        }
    });
    $("#itmCode").prop('disabled', true);
    $("#itmName").prop('disabled', true);
    $("#itmQTY").prop('disabled', true);
    $("#itmPrice").prop('disabled', true);

});

$("#itmUpdate").click(function () {

    let id = $("#itmCode").val();
    validItem(id).then(function (isValid){
        if (isValid) {
            let consent = confirm("Do you really want to update this Item.?");
            if (consent) {
                var array = $("#itmForm").serializeArray();
                var data = {};
                array.forEach(function (field) {
                    data[field.name] = field.value;
                });
                console.log(data)
                $.ajax({
                    url: "http://localhost:8080/BackEnd/item",
                    method: "PUT",
                    data:JSON.stringify(data),
                    contentType:"application/json",
                    success:function (res) {
                        console.log(res);
                        alert("Item Update Successfully")
                        getAllItem();
                    },
                    error:function (ob, textStatus, error) {
                        alert(textStatus+" : Error Item Not Update");
                    }
                });
                $("#itmCode").prop('disabled', true);
                $("#itmName").prop('disabled', true);
                $("#itmQTY").prop('disabled', true);
                $("#itmPrice").prop('disabled', true);
                clearItemInputFields();
            }
        } else {
            alert("No such Item..please check the Code");
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
            var array = $("#itmForm").serializeArray();
            var data = {};
            array.forEach(function (field) {
                data[field.name] = field.value;
            });
            $.ajax({
                url:"http://localhost:8080/BackEnd/item",
                method: "POST",
                data:JSON.stringify(data),
                contentType:"application/json",
                success:function (res,textStatus,jsXH) {
                    console.log(res);
                    alert("Item Added Successfully");
                    getAllItem();
                },
                error:function (ob, textStatus, error) {
                    alert(textStatus+" : Error Item Not Added")
                }
            });
        }else {
            alert("Item already exits.!");
            clearItemInputFields();
        }
    });
}

function getAllItem() {
    $("#itemTable").empty();
    $.ajax({
        url:"http://localhost:8080/BackEnd/item?info=getall",
        method: "GET",
        success:function (res) {
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
            url: "http://localhost:8080/BackEnd/item?itmCode=" + id + "&info=search",
            method: "GET",
            dataType: "json",
            success: function (res, textStatus, xhr) {
                console.log(res);
                if(xhr.status===200){
                    resolve(true);
                }else {
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
        url:"http://localhost:8080/BackEnd/item?itmCode="+id+"&info=search",
        method: "GET",
        dataType:"json",
        success:function (res) {
            console.log(res);
            resolve(res);
        },
        error:function (ob, textStatus, error) {
            resolve(error);
        }
    });
    });
}
$('#itmSearch').click(function(){
    let id = $("#itmCode").val();
    searchItem(id).then(function (res){
        $("#itmName").val(res. itmName);
        $("#itmPrice").val(res. itmPrice);
        $("#itmQTY").val(res. itmQTY);
    });
    setItemClBtn();
});
