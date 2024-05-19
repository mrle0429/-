package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/AdminScheduleManagement")
public class AdminScheduleManagementServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String option = req.getParameter("option");

        switch (option){
            case "viewSchedules":
                req.getRequestDispatcher("/Admin/ViewSchedules.jsp").forward(req, resp);
                break;
            case "viewUsers":
                req.getRequestDispatcher("/Admin/ViewUsers.jsp").forward(req, resp);
                break;
            default:
                resp.sendRedirect("AdminHome.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
