<%@ page import="uz.pdp.task2.repo.UserRepo" %>
<%@ page import="uz.pdp.task2.entity.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.EnumSet" %>
<%@ page import="java.util.UUID" %><%--
  Created by IntelliJ IDEA.
  User: arslonbekyuldoshev
  Date: 13/05/24
  Time: 00:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User page</title>
    <link rel="stylesheet" href="/static/bootstrap.min.css">
</head>
<body>
<%
    UserRepo userRepo = new UserRepo();
    String id = request.getParameter("id");
    if (id!=null){
        userRepo.deleteById(UUID.fromString(id));
    }
    List<User> users = userRepo.findAll();

%>
<a href="/" class="btn btn-dark text-white">Hope page</a>
<a href="/admin/addUser.jsp" class="btn btn-dark text-white">Add user</a>
<div class="row">
    <div class="col-12 m-3">
        <table class="table table-stripped">
            <thead>
            <tr>
                <th>Id</th>
                <th>User name</th>
                <th>Password</th>
                <th>Role</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <% for (User user : users) { %>
            <tr>
                <td><%=user.getId()%>
                </td>
                <td><%=user.getUsername()%>
                </td>
                <td><%=user.getPassword()%>
                </td>
                <td><%=user.getRole()%>
                </td>
                <td>
                    <a href="/admin/addUser.jsp?id=<%=user.getId()%>" class="btn btn-dark text-white">Edit</a>
                    <a href="/User.jsp?id=<%=user.getId()%>" class="btn btn-dark text-white">Delete</a>
                </td>
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>
