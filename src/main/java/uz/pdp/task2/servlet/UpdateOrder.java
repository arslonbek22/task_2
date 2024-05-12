package uz.pdp.task2.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = "/update/order")
public class UpdateOrder extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String orderNumberStr = req.getParameter("orderNumber");
        String[] productIds = req.getParameterValues("products");

        int orderNumber = Integer.parseInt(orderNumberStr);

        OrderRepo orderRepo = new OrderRepo();
        Order order = orderRepo.findById(Integer.parseInt(id));
        order.setOrderNumber(orderNumber);
        orderRepo.update(order);

        OrderProductRepo orderProductRepo = new OrderProductRepo();
        orderProductRepo.deleteByOrderId(order.getId());

        List<OrderProduct> orderProducts = new ArrayList<>();
        for (String productId : productIds) {
            ProductRepo productRepo = new ProductRepo();
            Product product = productRepo.findById(Integer.parseInt(productId));
            OrderProduct orderProduct = OrderProduct.builder()
                    .product(product)
                    .order(order)
                    .build();
            orderProducts.add(orderProduct);
        }
        orderProductRepo.saveAll(orderProducts);

        resp.sendRedirect("/"); // Redirect to the home page or another appropriate URL
    }

}
