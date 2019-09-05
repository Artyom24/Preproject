package servlets.filter;

import entities.User;
import service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/user/*")
public class UserServletFilter implements Filter {
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
        String sign = request.getParameter("sign");
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        if(sign!=null && sign.equals("out") || user==null) {
            if(user!=null) {
                session.removeAttribute("user");
            }
            response.sendRedirect("/");
        }
        else {
            request.getRequestDispatcher("/WEB-INF/user.jsp").forward(request, response);
        }

    }

}
