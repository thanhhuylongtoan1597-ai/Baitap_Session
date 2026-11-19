<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>Murach's Java Servlet and JSP</title>
    <link rel="stylesheet" href="styles/main.css" type="text/css"/>
</head>
<body>
    <h1>Your cart</h1>
    <table>
        <tr>
            <th>Quantity</th>
            <th>Description</th>
            <th class="right">Price</th>
            <th class="right">Amount</th>
            <th>&nbsp;</th>
        </tr>

        <c:forEach var="item" items="${cart.items}">
            <tr>
                <td>
                    <form action="cart" method="post">
                        <input type="hidden" name="productCode" value="<c:out value='${item.product.code}'/>">
                        <input type="text" name="quantity" value="<c:out value='${item.quantity}'/>" id="quantity">
                        <input type="submit" value="Update">
                    </form>
                </td>
                <td><c:out value='${item.product.description}'/></td>
                <td class="right">${item.product.priceCurrencyFormat}</td>
                <td class="right">${item.totalCurrencyFormat}</td>
                <td>
                    <form action="cart" method="post">
                        <input type="hidden" name="productCode" value="<c:out value='${item.product.code}'/>">
                        <input type="hidden" name="quantity" value="0">
                        <input type="submit" value="Remove Item">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

    <h2>To change the quantity, enter the new quantity and click on Update button</h2>

    <form action="cart" method="post" style="margin-bottom: 0.5em;">
        <input type="hidden" name="action" value="shop">
        <input type="submit" value="Continue Shopping">
    </form>

    <form action="cart" method="post">
        <input type="hidden" name="action" value="checkout">
        <input type="submit" value="Checkout">
    </form>
</body>
</html>