package Controller;

import Util.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/UserLogin")
public class UserLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");
        try {
            Connection con = DBUtil.getConnection();
            String sql = "select * from USER where userId = ? and password = ? ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,userId);
            ps.setString(2,password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                String redirectURL = "/UserInterface?userId="+ URLEncoder.encode(userId,"UTF-8");
                resp.sendRedirect(req.getContextPath()+redirectURL);
                return;
            }
            req.setAttribute("errorMessage","Wrong Login Information !!!!");
            req.getRequestDispatcher("/userLogin.jsp").forward(req,resp);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
