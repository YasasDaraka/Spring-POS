setTime();
setDate();
let home = $("#home");
let customer = $("#customer");
let item = $("#item");
let order = $("#order");
let btnCus = $("#btn-cust");
let btnItem = $("#btn-item");
let btnOrder = $("#btn-order");

customer.css('display','none');
item.css('display','none');
order.css('display','none');

function homeUp(){
    home.css('display','block');
    customer.css('display','none');
    item.css('display','none');
    order.css('display','none');
}
btnCus.click(function (){
    if (customer.css('display')==="none"){
        home.css('display','none');
        customer.css('display','block');
        item.css('display','none');
        order.css('display','none');
    }else{
        homeUp();
    }
});
btnItem.click(function (){
    if (item.css('display')==="none"){
        home.css('display','none');
        customer.css('display','none');
        item.css('display','block');
        order.css('display','none');
    }else{
        homeUp();
    }
});
btnOrder.click(function (){
    if (order.css('display')==="none"){
        home.css('display','none');
        customer.css('display','none');
        item.css('display','none');
        order.css('display','block');
    }else{
        homeUp();
    }
});

const btnHomeElements = $(".home-nav");

btnHomeElements.each(function() {
    $(this).click(function (){
        if (home.css('display')==="none"){
            home.css('display','block');
            customer.css('display','none');
            item.css('display','none');
            order.css('display','none');
        }else{
            home.css('display','none');
        }
    });
});

const btnCustomerElements = $(".cus-nav");

btnCustomerElements.each(function() {
    $(this).click(function() {
        if (customer.css('display')==="none"){
            home.css('display','none');
            customer.css('display','block');
            item.css('display','none');
            order.css('display','none');
        }else{
            customer.css('display','none');
        }
    });
});

const btnItemElements = $(".item-nav");

btnItemElements.each(function() {
    $(this).click(function() {
        if (item.css('display')==="none"){
            home.css('display','none');
            customer.css('display','none');
            item.css('display','block');
            order.css('display','none');
        }else{
            item.css('display','none');
        }
    });
});

const btnOrderElements = $(".order-nav");

btnOrderElements.each(function() {
    $(this).click(function() {
        if (order.css('display')==="none"){
            home.css('display','none');
            customer.css('display','none');
            item.css('display','none');
            order.css('display','block');
        }else{
            order.css('display','none');
        }
    });
});
function setDate() {
    let currentDate = new Date();
    let year = currentDate.getFullYear();
    let month = (currentDate.getMonth() + 1).toString().padStart(2, '0');
    let day = currentDate.getDate().toString().padStart(2, '0');
    let formattedDate = year + '-' + month + '-' + day;
    $('.currDate').text(`Date : ${formattedDate}`);
}

function setTime() {
    setInterval(function (){
        let currentTime = new Date();
        let hours = currentTime.getHours();
        let minutes = currentTime.getMinutes();
        let seconds = currentTime.getSeconds();

        if (hours < 10) {
            hours = "0" + hours;
        }
        if (minutes < 10) {
            minutes = "0" + minutes;
        }
        if (seconds < 10) {
            seconds = "0" + seconds;
        }

        const formattedTime = hours + ":" + minutes + ":" + seconds;
        $('.currTime').text(`Time : ${formattedTime}`);
    },1000);
}