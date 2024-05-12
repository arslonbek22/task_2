<%@ page import="uz.pdp.task2.entity.User" %>
<%@ page import="jdk.dynalink.linker.LinkerServices" %>
<%@ page import="java.util.List" %>
<%@ page import="uz.pdp.task2.entity.Order" %>
<%@ page import="uz.pdp.task2.repo.OrderRepo" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Home page</title>
    <link rel="stylesheet" href="/static/bootstrap.min.css">
</head>
<body>

<%
    User user = (User)session.getAttribute("user");
    OrderRepo orderRepo = new OrderRepo();
    List<Order> orders = orderRepo.findAll();

%>

    <div class="bg bg-dark text-white m-1">
        <a href="/admin/login.jsp" class="btn btn-info float-right">Log in</a>
    </div>
    <div class="m-auto">
        <%if (user != null && user.getRole().equals("ADMIN")){%>
        <a href="/admin/createOrder.jsp" class="m-1 float-right btn btn-success">Create order</a>
        <%}%>
        <table class="table table-stripped">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Created Time</th>
                    <th>Order Number</th>
                    <th>Products</th>
                    <%if (user != null && user.getRole().equals("ADMIN")){%>
                        <th>Actions</th>
                    <%}%>

                </tr>
            </thead>

            <tbody>
            <% for (Order order : orders) { %>
            <%
                String orderProducts = order.getOrderProducts() != null
                        ? order.getOrderProducts().stream()
                        .map(op -> String.valueOf(op.getProduct().getName()))
                        .collect(Collectors.joining(", "))
                        : "N/A";
            %>
            <tr>
                <td><%= order.getId() %></td>
                <td><%= order.getOrderDate() %></td>
                <td><%= order.getOrderNumber() %></td>
                <td><%= orderProducts %></td>
                <% if (user != null && user.getRole().equals("ADMIN")) { %>
                    <td>
                        <a href="/admin/createOrder.jsp?id=<%=order.getId()%>&orderNumber=<%=order.getOrderNumber()%>" class="btn btn-info">Edit</a>
                        <a href="/delete?id=<%=order.getId()%>" class="btn btn-danger">Delete</a>
                    </td>
                <% } %>
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>
</body>
</html>