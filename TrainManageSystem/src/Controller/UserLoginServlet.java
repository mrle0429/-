package Controller;

import Util.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
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
        String cid = req.getParameter("userId");
        String password = req.getParameter("password");
        try {
            Connection con = DBUtil.getConnection();
            String sql = "select * from USER where userId = ? and password = ? ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,cid);
            ps.setString(2,password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                resp.sendRedirect(req.getContextPath()+"/UserInterface");
                return;
            }
            req.setAttribute("errorMessage","Wrong Information !!!!");
            req.getRequestDispatcher("/userLogin.jsp").forward(req,resp);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
