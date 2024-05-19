package Controller;

import Service.SchedulesDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URLEncoder;

@WebServlet("/Refund")
public class RefundServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("userId");
        String ticketId = req.getParameter("ticketId");
        SchedulesDao.refund(ticketId);

        String redirectURL = "/TicketsInterface?userId="+ URLEncoder.encode(id,"UTF-8");
        resp.sendRedirect(req.getContextPath()+redirectURL);

    }
}
