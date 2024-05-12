package uz.pdp.task2.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.pdp.task2.entity.User;
import uz.pdp.task2.repo.UserRepo;

import java.io.IOException;

@WebServlet("/user/add")
public class AddUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String role = req.getParameter("role");
        UserRepo userRepo = new UserRepo();
        User user = User.builder()
                .username(username)
                .password(password)
                .role(role).build();
        userRepo.save(user);
        resp.sendRedirect("/User.jsp");
    }
}
