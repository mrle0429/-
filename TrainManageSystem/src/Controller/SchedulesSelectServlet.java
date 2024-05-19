package Controller;

import Model.Schedules;
import Service.SchedulesDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/SchedulesSelect")
public class SchedulesSelectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("userId");
        String departure = req.getParameter("departure");
        String  arrival =  req.getParameter("arrival");
        String departureDate = req.getParameter("departure-date");
        req.setAttribute("userId",id);
        LocalDateTime localDate = LocalDateTime.now();
        req.setAttribute("currentDate",localDate);


        //���û�ѡ��ĳ�վ�����ڽ��жԱ�ƥ��
        if(departure == null || arrival == null){
            req.setAttribute("errorMsg","��������ȷ�Ĳ�ѯ��Ϣ");
            req.getRequestDispatcher("/schedulesInterface.jsp").forward(req,resp);
        }else if (!departureDate.isEmpty()){
            //��ȷ��������Ҫ�ж��Ƿ���Ҫ��Ʊ��
            //�����ǰ�����ڲ�ѯ������ʾ��Ʊ��ǰ����ʾ��Ʊ

            LocalDate selectedDate = LocalDate.parse(departureDate);
            List<Schedules> schedulesList = SchedulesDao.selectSchedules(departure,arrival,selectedDate);
            if(schedulesList!=null){

                req.setAttribute("list",schedulesList);
                req.getRequestDispatcher("/viewSchedules.jsp").forward(req,resp);
            }else{
                req.setAttribute("None","û�ж�Ӧ�ĳ�����Ϣ");
                req.getRequestDispatcher("/schedulesInterface.jsp").forward(req,resp);
            }
        }else{
            //�����ڵĲ�ѯ ֻ�ǲ�ѯ��һ�����е���һ�����еĳ���
            List<Schedules> schedulesList = SchedulesDao.selectSchedules(departure,arrival);
            if(schedulesList==null){
                req.setAttribute("None","û�в�ѯ����Ӧ�ĳ�����Ϣ");
                req.getRequestDispatcher("/schedulesInterface.jsp").forward(req,resp);
            }else{
                req.setAttribute("list",schedulesList);
                req.getRequestDispatcher("/viewSchedules.jsp").forward(req,resp);
            }

        }

    }
}
