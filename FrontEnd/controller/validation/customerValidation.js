const CUS_ID_REGEX = /^C00-(0*[1-9]\d{0,2})$/;
const CUS_NAME_REGEX = /^[A-Za-z ]{5,}$/;
const CUS_ADDRESS_REGEX = /^[A-Za-z0-9 ]{5,}$/;

let c_vArray = new Array();
c_vArray.push({field: $("#customerID"), regEx: CUS_ID_REGEX ,error: $("#cusIDError")});
c_vArray.push({field: $("#customerName"), regEx: CUS_NAME_REGEX ,error: $("#cusNameError")});
c_vArray.push({field: $("#customerAddress"), regEx: CUS_ADDRESS_REGEX ,error: $("#cusAddressError")});

function clearCustomerInputFields() {
    $("#customerID,#customerName,#customerAddress").val("");
    $("#customerID,#customerName,#customerAddress").css("border", "1px solid #ced4da");
    $("#customerID").focus();
    setBtn();
}

setBtn();
function setClBtn(){
    var any = false;
    $("#customerID, #customerName, #customerAddress").each(function () {
        if ($(this).val().trim() !== "") {
            any= true;
            return false;
        }
    });
    if (any) {
        $("#cusClear").prop("disabled", false);
    } else {
        $("#cusClear").prop("disabled", true);
    }
}
setClBtn();
function events(e) {
    setClBtn();
    let indexNo = c_vArray.indexOf(c_vArray.find((c) => c.field.attr("id") == e.target.id));

    if (e.key == "Tab") {
        e.preventDefault();
    }

    checkValidations(c_vArray[indexNo]);

    setBtn();

    if (e.key == "Enter") {

        if (e.target.id != c_vArray[c_vArray.length - 1].field.attr("id")) {
            if (checkValidations(c_vArray[indexNo])) {
                c_vArray[indexNo + 1].field.focus();
            }
        } else {
            if (checkValidations(c_vArray[indexNo])) {
                saveCustomer();
            }
        }
    }
}

$("#customerName,#customerAddress").on("keydown keyup", function (e) {
    events(e);
});

$("#customerID").on("keydown keyup", function (e) {
    events(e);
    searchCustomer($("#customerID").val()).then(function (res){
        $("#customerName").val(res.name);
        $("#customerAddress").val(res.address);
    });
});

function checkValidations(object) {
    if (object.regEx.test(object.field.val())) {
        setBorder(true, object)
        return true;
    }
    setBorder(false, object)
    return false;
}

function setBorder(bol, ob) {
    if (!bol) {
        if (ob.field.val().length >= 1) {
            ob.field.css("border", "2px solid red");
            let check = ob.field.attr('id');
            switch (check) {
                case "customerID" : ob.error.text("cus-Id is a required field: C00-"); break
                case "customerName" : ob.error.text("cus-Name is a required field: Minimum 5,Max 20,Spaces Allowed"); break
                case "customerAddress" : ob.error.text("cus-Address is a required field: Minimum 5"); break
            }
        } else {
            ob.field.css("border", "1px solid #ced4da");
            ob.error.text("");
        }
    } else {
        if (ob.field.val().length >= 1) {
            ob.field.css("border", "2px solid green");
            ob.error.text("");
        } else {
            ob.field.css("border", "1px solid #ced4da");
            ob.error.text("");
        }
    }

}

function checkAll() {
    for (let i = 0; i < c_vArray.length; i++) {
        if (!checkValidations(c_vArray[i])) return false;
    }
    return true;
}

function setBtn() {
    setClBtn();
    $("#cusSave").prop("disabled", true);
    $("#cusDelete").prop("disabled", true);
    $("#cusUpdate").prop("disabled", true);
    $("#cusSearch").prop("disabled", true);
    let id = $("#customerID").val();
    if ($("#customerID").val() != "" && CUS_ID_REGEX.test($("#customerID").val())){
        $("#cusSearch").prop("disabled", false);
    }else {
        $("#cusSearch").prop("disabled", true);
    }
    validCustomer(id)
        .then(function (isValid) {
            if (isValid) {
                $("#cusDelete").prop("disabled", false);
                if (checkAll()) {
                    $("#cusUpdate").prop("disabled", false);
                    $("#cusDelete").prop("disabled", false);
                } else {
                    $("#cusUpdate").prop("disabled", true);
                }
            }else {
                $("#cusDelete").prop("disabled", true);
                $("#cusUpdate").prop("disabled", true);
                if (checkAll()) {
                    $("#cusSave").prop("disabled", false);
                } else {
                    $("#cusSave").prop("disabled", true);
                }
            }
        })
        .catch(function () {
            $("#cusDelete").prop("disabled", true);
            $("#cusUpdate").prop("disabled", true);
            if (checkAll()) {
                $("#cusSave").prop("disabled", false);
            } else {
                $("#cusSave").prop("disabled", true);
            }
        });
}

$("#cusClear").click(function () {
    clearCustomerInputFields();
    $("#cusIDError,#cusNameError,#cusAddressError,#cusSalaryError").text("");
});