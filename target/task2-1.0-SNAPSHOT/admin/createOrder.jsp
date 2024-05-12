<%@ page import="uz.pdp.task2.repo.ProductRepo" %>
<%@ page import="uz.pdp.task2.entity.Product" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create order</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .form-container {
            background-color: white;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            width: 400px;
        }

        .form-group {
            margin-bottom: 15px;
        }

        .form-group label {
            font-weight: bold;
            display: block;
            margin-bottom: 5px;
        }

        .form-group select {
            width: 100%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }

        .form-group button {
            padding: 8px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 3px;
            cursor: pointer;
        }

        .form-group button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>

<%
    ProductRepo productRepo = new ProductRepo();
    String id = request.getParameter("id");
    String orderNumber = request.getParameter("orderNumber");
    String status = request.getParameter("status");
    List<Product> products = productRepo.findAll();
%>

<div class="form-container">
    <h2>Create order</h2>
    <form method="post" action="<%=id == null ? "/create/order" : "/update/order"%>">
        <input type="hidden" value="<%=id%>" name="id">
        <div class="form-group">
            <label for="orderNumber">Order Number:</label>
            <input type="text" id="orderNumber" name="orderNumber" value="<%=id == null ? "" : orderNumber%>"> required>
            <label for="status">Order Status:</label>
            <input type="text" id="Status" name="status" value="<%=id == null ? "" : status%>"> required>
        </div>
        <div class="form-group">
            <label for="products">Products:</label>
            <select id="products" name="products" multiple required>
                <%for (Product product : products) {%>
                    <option value="<%=product.getId()%>"><%=product.getName()%></option>
                <%}%>
            </select>
        </div>
        <div class="form-group">
            <button type="submit">Create Order</button>
        </div>
    </form>
</div>
</body>
</html>
