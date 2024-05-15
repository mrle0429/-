package Controller;

import Model.Schedule;
import Service.AdministratorDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/AddSchedule")
public class AddScheduleServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int scheduleId = Integer.parseInt(req.getParameter("scheduleId"));
        String trainId = req.getParameter("trainId");
        String departureStation = req.getParameter("departureStation");
        String arrivalStation = req.getParameter("arrivalStation");
        String departureTime = req.getParameter("departureTime");
        String arrivalTime = req.getParameter("arrivalTime");
        int availableSeats = Integer.parseInt(req.getParameter("availableSeats"));

        Schedule schedule = new Schedule(scheduleId, trainId, departureStation, arrivalStation, departureTime, arrivalTime, availableSeats);

        AdministratorDao.insertSchedule(schedule);

        resp.sendRedirect(req.getContextPath() + "/Admin/ViewSchedules.jsp");
    }
}
