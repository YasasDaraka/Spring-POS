
getAllCustomers();


$("#cusSave").click(function () {

    if (checkAll()){
        saveCustomer();
    }else{
        alert("Error");
    }
});

$(document).ready(function(){
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
});
function generateCustomerId() {
    loadCusAr().then(function (customerDB) {
        if (customerDB.length === 0) {
            $("#customerID").val("C00-1");
        } else {
            console.log(customerDB[customerDB.length - 1].id);
            var id = customerDB[customerDB.length - 1].id.split("-")[1];
            var tempId = parseInt(id, 10);
            if (!isNaN(tempId)) {
                tempId = tempId + 1;
                $("#customerID").val("C00-" + tempId);
            } else {
                console.error("Error converting customer ID to a number");
            }
        }
    }).catch(function (error) {
        console.error("Error loading customer data:", error);
    });
}

function loadCusAr(){
    return new Promise(function (resolve, reject) {
        var ar;
        $.ajax({
            url: "http://localhost:8080/BackEnd/customer?info=getall",
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

$('#cusAdd').click(function(){
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

    });
}

$("#cusDelete").click(function () {
    let id = $("#customerID").val();

    validCustomer(id).then(function (isValid) {
        if (isValid == false) {
            alert("No such Customer..please check the ID");
            clearCustomerInputFields();
        } else {
            let consent = confirm("Do you want to delete.?");
            if (consent) {
                $.ajax({
                    url: "http://localhost:8080/BackEnd/customer?cusId="+id,
                    method: "DELETE",
                    success:function (res) {
                        console.log(res);
                        alert("Customer Delete Successfully");
                        clearCustomerInputFields();
                        getAllCustomers();
                    },
                    error:function (ob, textStatus, error) {
                        alert(textStatus+" : Error Customer Not Delete")
                    }
                });
            }
        }
    });

    $("#customerID").prop('disabled', true);
    $("#customerName").prop('disabled', true);
    $("#customerAddress").prop('disabled', true);

});

$("#cusUpdate").click(function () {
    let id = $("#customerID").val();
    validCustomer(id).then(function (isValid){
    if (isValid) {
        let consent = confirm("Do you really want to update this customer.?");
        if (consent) {
            var array = $("#CusForm").serializeArray();
            var data = {};
            array.forEach(function (field) {
                data[field.name] = field.value;
            });
            console.log(data)
            $.ajax({
                url: "http://localhost:8080/BackEnd/customer",
                method: "PUT",
                data:JSON.stringify(data),
                contentType:"application/json",
                success:function (res) {
                    console.log(res);
                    alert("Customer Update Successfully")
                    getAllCustomers();
                },
                error:function (ob, textStatus, error) {
                    alert(textStatus+" : Error Customer Not Update");
                }
            });
            $("#customerID").prop('disabled', true);
            $("#customerName").prop('disabled', true);
            $("#customerAddress").prop('disabled', true);
            clearCustomerInputFields();
        }
    } else {
        alert("No such Customer..please check the ID");
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
        var array = $("#CusForm").serializeArray();
        var data = {};
        array.forEach(function (field) {
            data[field.name] = field.value;
        });
        $.ajax({
            url:"http://localhost:8080/BackEnd/customer",
            method: "POST",
            data:JSON.stringify(data),
            contentType:"application/json",
            success:function (res,textStatus,jsXH) {
                console.log(res);
                alert("Customer Added Successfully");
                getAllCustomers();
            },
            error:function (ob, textStatus, error) {
                alert(textStatus+" : Error Customer Not Added")
            }
        });
    }else {
        alert("Customer already exits.!");
        clearCustomerInputFields();
    }
    });
}

function getAllCustomers() {

    $("#customerTable").empty();
    $.ajax({
        url:"http://localhost:8080/BackEnd/customer?info=getall",
        method: "GET",
        success:function (res) {
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
            url: "http://localhost:8080/BackEnd/customer?cusId=" + id + "&info=search",
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
function searchCustomer(id) {
    console.log(id);
    return new Promise(function (resolve, reject) {
    $.ajax({
        url:"http://localhost:8080/BackEnd/customer?cusId="+id+"&info=search",
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
$('#cusSearch').click(function(){
    let id = $("#customerID").val();
    searchCustomer(id).then(function (res){
        $("#customerName").val(res.name);
        $("#customerAddress").val(res.address);
    });
    setClBtn();
});
