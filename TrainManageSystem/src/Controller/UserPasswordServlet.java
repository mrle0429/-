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

@WebServlet("/UserPassword")
public class UserPasswordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");
        String oldPassword = req.getParameter("oldPassword");
        String newPassword = req.getParameter("newPassword");

        System.out.println(password+"  " +oldPassword+"    " +newPassword);
        if(password.equals(oldPassword)){
            User user = UserDao.selectUserById(userId);
            UserDao.updatePassword(user,newPassword);
            String redirectURL = "/UserInterface?userId="+ URLEncoder.encode(userId,"UTF-8");
            resp.sendRedirect(req.getContextPath()+redirectURL);
        }else{
            req.setAttribute("errorMsg","请输入正确的密码！");
            req.getRequestDispatcher("/userPassword.jsp").forward(req,resp);
        }


    }
}
