package uz.pdp.task2.auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.pdp.task2.entity.User;
import uz.pdp.task2.repo.UserRepo;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(value = "/auth/login")
public class Login extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("username");
        String password = req.getParameter("password");
        User user = findByUserNameAndPassword(userName, password);
        if (user != null && user.getPassword().equals(password) && user.getUsername().equals(userName) && user.getRole().equals("ADMIN")) {
            req.getSession().setAttribute("user", user);
            resp.sendRedirect("/");
        } else if (user != null && user.getPassword().equals(password) && user.getUsername().equals(userName) && user.getRole().equals("USER")) {
            req.getSession().setAttribute("user", user);
            resp.sendRedirect("/");
        }else {
            resp.sendRedirect("/admin/login.jsp");
        }
    }

    private User findByUserNameAndPassword(String login, String password) {
        List<User> users = UserRepo.findAllUsers();
        Optional<User> optional = users.stream().
                filter(item -> item.getUsername().equals(login) && item.getPassword().equals(password))
                .findFirst();
        return optional.orElse(null);

    }
}
