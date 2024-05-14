package Controller;

import Model.User;
import Service.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URLEncoder;

@WebServlet("/UpdateUser")
public class UpdateUserServlet extends HttpServlet {
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

        User newUser = new User(userId,userName,userAge,userSex,phoneNumber,email,address,country,password);
        boolean isUpdate = UserDao.updateUserById(newUser);
        String redirectURL = "/UserInterface?userId="+ URLEncoder.encode(userId,"UTF-8");
        if(!isUpdate){
//            req.setAttribute("errorMsg","更新信息失败！");
            resp.sendRedirect(req.getContextPath()+redirectURL);
        }

        resp.sendRedirect(req.getContextPath()+redirectURL);
    }
}
