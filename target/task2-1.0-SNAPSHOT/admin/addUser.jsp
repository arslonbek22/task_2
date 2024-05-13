<%@ page import="uz.pdp.task2.entity.User" %>
<%@ page import="uz.pdp.task2.repo.UserRepo" %>
<%@ page import="java.util.UUID" %><%--
  Created by IntelliJ IDEA.
  User: arslonbekyuldoshev
  Date: 13/05/24
  Time: 00:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add user</title>
    <link rel="stylesheet" href="/static/bootstrap.min.css">
</head>
<body>
<%
    UserRepo userRepo = new UserRepo();
    String userId = request.getParameter("id");
    User user = null;
    if (userId!=null){
        user = userRepo.findById(UUID.fromString(userId));
    }


%>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <h2>Add User</h2>
            <form action="<%=user!=null?"/user/update":"/user/add"%>">
                <input type="hidden" name="userId" value="<%=user!=null? user.getId():""%>">

                    <label for="username">Username</label>
                    <input type="text" class="form-control" value="<%=user!=null? user.getUsername():""%>" name="username" id="username" placeholder="Enter username">

                    <label for="password">Password</label>
                    <input type="password" class="form-control" value="<%=user!=null? user.getPassword():""%>" name="password" id="password" placeholder="Enter password">

                    <label for="role">Role</label>
                    <select class="form-control" id="role" name="role">
                        <option disabled <%=user!=null?user.getRole():""%>></option>
                        <option value="ADMIN">Admin</option>
                        <option value="USER">User</option>
                    </select>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>
    </div>
</div>

</body>
</html>
