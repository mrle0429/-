package Controller;

import Model.Order;
import Model.User;
import Service.SchedulesDao;
import Service.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/TicketsInterface")
public class TicketsInterfaceServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("userId");
        User user = UserDao.selectUserById(id);
        LocalDateTime localDateTime = LocalDateTime.now();

        List<Order> orderList = SchedulesDao.selectTicketsByUserId(id);

        req.setAttribute("userId",id);
        req.setAttribute("localDateTime",localDateTime);

        req.setAttribute("list",orderList);
        req.setAttribute("user",user);

        req.getRequestDispatcher("/ticketsInterface.jsp").forward(req,resp);



    }
}
