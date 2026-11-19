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
    <h1>Checkout</h1>

    <c:if test="${empty cart.items}">
        <p>Giỏ hàng của bạn đang trống.</p>
    </c:if>

    <c:if test="${not empty cart.items}">
        <table>
            <tr>
                <th>Quantity</th>
                <th>Description</th>
                <th class="right">Price</th>
                <th class="right">Amount</th>
            </tr>

            <c:forEach var="item" items="${cart.items}">
                <tr>
                    <td><c:out value='${item.quantity}'/></td>
                    <td><c:out value='${item.product.description}'/></td>
                    <td class="right">${item.product.priceCurrencyFormat}</td>
                    <td class="right">${item.totalCurrencyFormat}</td>
                </tr>
            </c:forEach>

            <tr>
                <td colspan="3" class="right"><b>Total:</b></td>
                <td class="right"><b>${cart.totalAmountCurrencyFormat}</b></td>
            </tr>
        </table>
    </c:if>

    <br>
    <form action="cart" method="post" style="margin-bottom: 0.5em;">
        <input type="hidden" name="action" value="shop">
        <input type="submit" value="Continue Shopping">
    </form>
    <form action="cart" method="post">
            <input type="hidden" name="action" value="cancel">
            <input type="submit" value="Menu">
        </form>
</body>
</html>