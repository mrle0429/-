package Controller;

import Service.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/InsertUser")
public class InsertUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        String userName = req.getParameter("userName");
        Integer userAge = Integer.valueOf(req.getParameter("userAge"));
        String userSex = req.getParameter("userSex");
        String phoneNumber = req.getParameter("phoneNumber");
        String email = req.getParameter("email");
        String address = req.getParameter("address");
        String country = req.getParameter("country");
        String password = req.getParameter("password");


        boolean checkInsert = UserDao.insertUer(userId,userName,userAge,userSex,phoneNumber,email,address,country,password);

        if(!checkInsert){
            req.setAttribute("errorSignIn","无法注册新的用户");
            req.getRequestDispatcher("/userLogin.jsp").forward(req,resp);
        }
        resp.sendRedirect(req.getContextPath()+"/UserInterface");


    }
}
