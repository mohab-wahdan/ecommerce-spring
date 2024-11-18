<%--
  Created by IntelliJ IDEA.
  User: mohab
  Date: 10/29/2024
  Time: 5:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Checkout</title>
  <script>
    function updateFormAction() {
      var amount = document.getElementById("amount").value;
      var form = document.getElementById("checkoutForm");
      form.action = "${pageContext.request.contextPath}/pay/" + amount;
    }
  </script>
</head>
<body>
<h1>Checkout Page</h1>

<form id="checkoutForm" method="get" onsubmit="updateFormAction()">
  <label for="amount">Enter Amount:</label>
  <input type="number" id="amount" name="amount" required>
  <input type="submit" value="Proceed to Checkout">
</form>

<div>
  <h2>Payment Information</h2>
  <p>Public Key: ${publicKey}</p>
  <p>Email: ${email}</p>
  <p>Product Name: ${productName}</p>
</div>
</body>
</html>
