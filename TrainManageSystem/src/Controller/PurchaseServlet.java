package Controller;

import Model.Schedules;
import Model.Tickets;
import Service.SchedulesDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.Random;

@WebServlet("/Purchase")
public class PurchaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("userId");
        String schedulesId = req.getParameter("scheduleId");
        LocalDateTime localDateTime = LocalDateTime.now();

        req.setAttribute("userId",id);

        Random random = new Random();
        int seatNumber = random.nextInt(1000);

        String randomId = String.valueOf(random.nextInt(1000000));
        while (!SchedulesDao.checkSameTicketId(randomId)){
            randomId = String.valueOf(random.nextInt(1000000));
        }

        Tickets tickets = new Tickets(randomId,id,schedulesId,String.valueOf(seatNumber),localDateTime,"","paid");
        boolean isAdd = SchedulesDao.addTickets(tickets);
        if(isAdd){
            SchedulesDao.updateAvailableSeats(schedulesId);
            String redirectURL = "/TicketsInterface?userId="+ URLEncoder.encode(id,"UTF-8");
            resp.sendRedirect(req.getContextPath()+redirectURL);
//            req.getRequestDispatcher("/ticketsInterface.jsp").forward(req,resp);
        }else{
            req.setAttribute("errorMg","¹ºÆ±Ê§°Ü");
            req.getRequestDispatcher("/schedulesInterface.jsp").forward(req,resp);
        }


    }
}
