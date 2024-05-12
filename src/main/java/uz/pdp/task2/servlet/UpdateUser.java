package uz.pdp.task2.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.pdp.task2.entity.User;
import uz.pdp.task2.repo.UserRepo;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/user/update")
public class UpdateUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID userId = UUID.fromString(req.getParameter("userId"));
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String role = req.getParameter("role");
        UserRepo userRepo = new UserRepo();
        User user =  userRepo.findById(userId);
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);
        userRepo.update(user);
        resp.sendRedirect("/");
    }
}
