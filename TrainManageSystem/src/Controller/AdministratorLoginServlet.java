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

@WebServlet("/AdministratorLogin")
public class AdministratorLoginServlet extends HttpServlet {
    private Connection con;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        try {
            Connection con = DBUtil.getConnection();
            String sql = "select * from administrator where username = ? and password = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                resp.sendRedirect(req.getContextPath()+"/AdministratorInterface");
            }
            req.setAttribute("errorMessage","Wrong Information !!");
            req.getRequestDispatcher("/adLogin.jsp").forward(req,resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(con);
        }
    }
}
