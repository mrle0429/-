package Controller;

import Service.AdministratorDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/DeleteSchedule")
public class DeleteScheduleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int scheduleId = Integer.parseInt(req.getParameter("scheduleId"));

        AdministratorDao.deleteScheduleByID(scheduleId);

        resp.sendRedirect(req.getContextPath() + "/Admin/ViewSchedules.jsp");
    }
}
