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
        String cid = req.getParameter("cid");
        String cname = req.getParameter("cname");
        Integer cage = Integer.valueOf(req.getParameter("cage"));
        String csex = req.getParameter("csex");
        String password = req.getParameter("password");
        String cphone = req.getParameter("cphone");

        boolean checkInsert = UserDao.insertUer(cid,cname,cage,csex,cphone,password);

        if(!checkInsert){
            req.setAttribute("errorSignIn","不能插入");
            req.getRequestDispatcher("/userLogin.jsp").forward(req,resp);
        }
        resp.sendRedirect(req.getContextPath()+"/UserInterface");


    }
}
