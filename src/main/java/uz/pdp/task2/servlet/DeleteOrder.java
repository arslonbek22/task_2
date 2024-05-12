package uz.pdp.task2.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.pdp.task2.repo.OrderRepo;

import java.io.IOException;

@WebServlet(value = "/delete")
public class DeleteOrder extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer orderId = Integer.parseInt(req.getParameter("id"));
        OrderRepo orderRepo = new OrderRepo();
        orderRepo.deleteById(orderId);
        resp.sendRedirect("/");
    }
}
