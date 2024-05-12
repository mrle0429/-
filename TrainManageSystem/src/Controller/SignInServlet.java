package Controller;

import Service.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Random;

@WebServlet("/SignIn")
public class SignInServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Random random = new Random();
        int randomNumber = random.nextInt(900000) + 100000;
        while(!UserDao.checkSameID(randomNumber)){
            randomNumber = random.nextInt(900000) + 100000;
        }
        int userId = randomNumber;
        req.setAttribute("userId",userId);
        req.getRequestDispatcher("/signin.jsp").forward(req,resp);
    }
}
