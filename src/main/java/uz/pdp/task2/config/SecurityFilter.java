package uz.pdp.task2.config;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import uz.pdp.task2.entity.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogRecord;

@WebFilter("/*")
public class SecurityFilter implements Filter {
    List<String> closedPages = new ArrayList<>(List.of(
            "/admin/createOrder.jsp"
    ));
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        var req = (HttpServletRequest) servletRequest;
        var resp = (HttpServletResponse) servletResponse;

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        String url = req.getRequestURI();
        if (!closedPages.contains(url)){
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        if (user == null){
            resp.sendRedirect("/admin/login.jsp");
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }


}
