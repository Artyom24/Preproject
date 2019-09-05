package servlets.filter;

import entities.User;
import service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/admin/*")
public class AdminServletFilter implements Filter {
    private FilterConfig fConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        fConfig = filterConfig;
    }

    @Override
    public void destroy() {
        fConfig = null;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        if(user!=null) {
            if (user.getRole().equals("admin")) {
                String sign = request.getParameter("sign");
                if(sign!=null && sign.equals("out")) {
                    session.removeAttribute("user");
                    response.sendRedirect("/");
                }
                else {
                    filterChain.doFilter(servletRequest, servletResponse);
                }

            } else {
                response.sendRedirect("/user");
            }
        }
        else {
            response.sendRedirect("/");
        }
    }
}
