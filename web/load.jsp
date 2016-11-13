<%--
  Created by IntelliJ IDEA.
  User: sixing.wen
  Date: 11/13/16
  Time: 14:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
         pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
    <title>Home</title>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap-theme.min.css">
    <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/purchase.js"></script>
</head>
<body>

<div class="page-header text-center">
    <h1>Shopping Cart</h1>
</div>
<div class="container">
    <div id="notice"></div>
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading">Products:</div>
        <!-- Table -->
        <table class="table">
            <tr>
                <th class="tid">ID</th><th class="tname">Name</th><th class="tprice">Price</th><th class="tstock">Stock</th><th>Description</th><th>Cart</th>
            </tr>
            <c:forEach items="${requestScope.products}" var="prod">
                <tr>
                    <td><c:out value="${prod.id}"></c:out></td>
                    <td><c:out value="${prod.name}"></c:out></td>
                    <td><c:out value="${prod.price}"></c:out></td>
                    <td id="<c:out value="stock-${prod.id}"></c:out>"><c:out value="${prod.stock}"></c:out></td>
                    <td><c:out value="${prod.getDiscrition()}"></c:out></td>
                    <td id="<c:out value="btn-${prod.id}"></c:out>">
                        <input type="button" class="btn btn-info" value="Add to cart" onclick="addProductToCart(
                                '<c:out value="${prod.id}"></c:out>',
                                '<c:out value="${prod.name}"></c:out>',
                                '<c:out value="${prod.price}"></c:out>')">
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<div class="container" style="display: none;" id="cartDiv">
    <div class="panel panel-default">
        <div class="panel-heading">Cart:</div>
        <table class="table" id="cart">
            <tr>
                <th>ID</thx>
                <th>Name</th>
                <th>Price</th>
                <th>Quantity</th>
            </tr>
        </table>
        <br>
    </div>
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading">Total</div>
        <!-- List group -->
        <ul class="list-group">
            <li class="list-group-item"><h2>Price: </h2> <h3 id="totalPrice">0</h3></li>
            <li class="list-group-item"><h2>Num: </h2> <h3 id="totalNum">0</h3></li>
        </ul>
    </div>
    <div class="text-center">
        <input type="button" class="btn btn-danger" value="Clear" onclick="clearCart()">
        <input type="button" class="btn btn-success" value="Checkout" onclick="purchase()">
    </div>
</div>
<br>


</body>
</html>
