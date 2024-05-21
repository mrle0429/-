package Controller;

import Model.Schedules;
import Service.AdministratorDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

@WebServlet("/UpdateSchedule")
public class UpdateScheduleServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String scheduleId = req.getParameter("scheduleId");
        String trainId = req.getParameter("trainId");
        String departureStation = req.getParameter("departureStation");
        String arrivalStation = req.getParameter("arrivalStation");

        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd HH:mm")
                .optionalStart()
                .appendPattern(":ss")
                .optionalEnd()
                .toFormatter();
        LocalDateTime departureTime = LocalDateTime.parse(req.getParameter("departureTime").replace('T', ' '),formatter);
        LocalDateTime arrivalTime = LocalDateTime.parse(req.getParameter("arrivalTime").replace('T', ' '), formatter);
        int availableSeats = Integer.parseInt(req.getParameter("availableSeats"));

        Schedules schedule = new Schedules(scheduleId, trainId, departureStation, arrivalStation, departureTime, arrivalTime, availableSeats);

        if (AdministratorDao.updateSchedule(schedule)){
            resp.sendRedirect(req.getContextPath() + "/Admin/ViewSchedules.jsp");
        }else{
            req.setAttribute("errorUpdate", "Fail to update");
            req.getRequestDispatcher("/Admin/UpdateSchedule.jsp").forward(req, resp);
        }
    }
}
