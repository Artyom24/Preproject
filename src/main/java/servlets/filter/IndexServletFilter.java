package servlets.filter;

import entities.User;
import service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/auth")
public class IndexServletFilter implements Filter {
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
        String login  = servletRequest.getParameter("login");
        String password = servletRequest.getParameter("pass");
        User user = UserService.getInstance().getUserByLogin(login);
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        if(user!=null && user.getPassword().equals(password)) {
            session.setAttribute("user", user);
            if(user.getRole().equals("user")) {
                response.sendRedirect("/user");
            }
            else if(user.getRole().equals("admin")) {

            }
        }
        else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);

        }
    }
}
