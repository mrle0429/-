package Controller;

import Model.Schedule;
import Service.AdministratorDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/AddSchedule")
public class AddScheduleServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int scheduleId = Integer.parseInt(req.getParameter("scheduleId"));
        String trainId = req.getParameter("trainId");
        String departureStation = req.getParameter("departureStation");
        String arrivalStation = req.getParameter("arrivalStation");
        LocalDateTime departureTime = LocalDateTime.parse(req.getParameter("departureTime").replace('T', ' '), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime arrivalTime = LocalDateTime.parse(req.getParameter("arrivalTime").replace('T', ' '), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        int availableSeats = Integer.parseInt(req.getParameter("availableSeats"));

        Schedule schedule = new Schedule(scheduleId, trainId, departureStation, arrivalStation, departureTime, arrivalTime, availableSeats);

        AdministratorDao.insertSchedule(schedule);

        resp.sendRedirect(req.getContextPath() + "/Admin/ViewSchedules.jsp");
    }
}
