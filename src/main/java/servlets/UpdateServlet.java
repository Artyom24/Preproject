
package servlets;

import entities.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
   private User user;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        user = UserService.getInstance().getUserById(id);
        req.setAttribute("user", user);
        req.getRequestDispatcher("/WEB-INF/update.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String name = req.getParameter("name");
       String login = req.getParameter("login");
       String password = req.getParameter("pass");

       if(!user.getName().equals(name) || !user.getLogin().equals(login) || !user.getPassword().equals(password)) {
           User newUser = new User(name, login, password);
           if(UserService.getInstance().updateUser(user, newUser)==0) {
               req.setAttribute("user", user);
               resp.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
               req.getRequestDispatcher("/WEB-INF/update.jsp").forward(req, resp);
           }
       }
       resp.sendRedirect("/");
    }
}
