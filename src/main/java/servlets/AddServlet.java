package servlets;



import entities.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add")
public class AddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("pass");
        User user = new User(name, login, password);
        if( UserService.getInstance().createUser(user)==1) {
           resp.setStatus(HttpServletResponse.SC_CREATED);
        }
        else {
           resp.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
        }
        resp.sendRedirect("/add");
    }
}
