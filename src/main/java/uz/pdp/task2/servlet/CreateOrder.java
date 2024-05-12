package uz.pdp.task2.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.pdp.task2.entity.Order;
import uz.pdp.task2.entity.OrderProduct;
import uz.pdp.task2.entity.Product;
import uz.pdp.task2.repo.OrderProductRepo;
import uz.pdp.task2.repo.OrderRepo;
import uz.pdp.task2.repo.ProductRepo;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet(value = "/create/order")
public class CreateOrder extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderNumberStr = req.getParameter("orderNumber");
        String orderStatus = req.getParameter("status");
        String[] productIds = req.getParameterValues("products");

        int orderNumber = Integer.parseInt(orderNumberStr);

        Order order = Order.builder()
                .status(orderStatus)
                .orderNumber(orderNumber)
                .orderDate(LocalDate.now().atStartOfDay())
                .build();


        OrderRepo orderRepo = new OrderRepo();
        ProductRepo productRepo = new ProductRepo();
        OrderProductRepo orderProductRepo = new OrderProductRepo();
        orderRepo.save(order);
        if (productIds != null) {
            for (String productId : productIds) {
                Product product = productRepo.findById(Integer.parseInt(productId));
                OrderProduct orderProduct = OrderProduct.builder()
                        .product(product)
                        .order(order)
                        .build();
                orderProductRepo.save(orderProduct);
            }
        }

        resp.sendRedirect("/");
    }
}
