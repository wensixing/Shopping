/**
 * Created by sixing.wen on 11/13/16.
 */
var cartMap = {};
var totalPrice = 0.0;
var totalNum = 0;

function addProductToCart(pid, pname, pprice) {

    var tag = 'cart-' + pid;
    var stock = $("#stock-" + pid).html();
    if (stock > 0) {
        // product available
        stock = stock - 1;
        $("#stock-" + pid).html(stock);

        if (stock <= 0) {
            $("#btn-" + pid).html("");
        }

        if (cartMap[pid] != undefined) {
            // already in cart
            cartMap[pid] = cartMap[pid] + 1;
            $("#" + tag).html(cartMap[pid]);
        }
        else {
            cartMap[pid] = 1;
            $('#cart tr:last').after(`<tr><td>${pid}</td><td>${pname}</td><td>${pprice}</td><td id="${tag}">1</td></tr>`);
        }

        totalPrice += parseFloat(pprice);
        totalNum += 1;
        $('#totalPrice').html(totalPrice);
        $('#totalNum').html(totalNum);
        $('#cartDiv').show();
    }
}

function purchase() {
    $.ajax({
        type:'POST',
        data: cartMap,
        dataType: 'json',
        url:'Checkout',
        success: function(result) {
            cartMap = {};
            totalPrice = 0.0;
            totalNum = 0;
            $('#cart').html("<tr><th>ID</th><th>Name</th><th>Price</th><th>Num</th></tr>");
            $('#cartDiv').hide();
            $('#notice').html("<div class='alert alert-success'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a><strong>Purchase Success!</strong> Thank you.</div>");
        },
        error:function(data,status,er) {
            $('#notice').html("<div class='alert alert-danger'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a><strong>Purchase Error!</strong> Please try it again.</div>");
        }
    });
}

function clearCart() {
    window.location.replace("Load");
}
