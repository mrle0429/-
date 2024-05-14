package Controller;

import Model.User;
import Service.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/UserInformation")
public class UserInformationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("userId");
        User user = UserDao.selectUserById(id);

//        String userId = user.getUserId();
//        String userName = user.getUserName();
//        Integer userAge = user.getUserAge();
//        String userSex = user.getUserSex();
//        String phoneNumber = user.getPhoneNumber();
//        String email = user.getEmail();
//        String address = user.getAddress();
//        String country = user.getCountry();
//        String password = user.getPassword();

        req.setAttribute("user",user);
        req.getRequestDispatcher("/userInformation.jsp").forward(req,resp);


    }
}
