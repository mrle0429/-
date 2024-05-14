package Controller;

import Model.User;
import Service.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/UserInterface")
public class UserInterfaceServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        User user = UserDao.selectUserById(userId);

        if(user==null){
            req.getRequestDispatcher("/index.jsp").forward(req,resp);
        }

        req.setAttribute("user",user);
        req.getRequestDispatcher("/userInterface.jsp").forward(req,resp);

    }
}
