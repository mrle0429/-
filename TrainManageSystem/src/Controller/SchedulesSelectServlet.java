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


        //将用户选择的车站，日期进行对比匹配
        if(departure == null || arrival == null){
            req.setAttribute("errorMsg","请输入正确的查询信息");
            req.getRequestDispatcher("/schedulesInterface.jsp").forward(req,resp);
        }else if (!departureDate.isEmpty()){
            //精确搜索：需要判断是否需要购票：
            //如果当前日期在查询后则不显示购票，前则显示购票

            LocalDate selectedDate = LocalDate.parse(departureDate);
            List<Schedules> schedulesList = SchedulesDao.selectSchedules(departure,arrival,selectedDate);
            if(schedulesList!=null){

                req.setAttribute("list",schedulesList);
                req.getRequestDispatcher("/viewSchedules.jsp").forward(req,resp);
            }else{
                req.setAttribute("None","没有对应的车次信息");
                req.getRequestDispatcher("/schedulesInterface.jsp").forward(req,resp);
            }
        }else{
            //无日期的查询 只是查询从一个城市到另一个城市的车次
            List<Schedules> schedulesList = SchedulesDao.selectSchedules(departure,arrival);
            if(schedulesList==null){
                req.setAttribute("None","没有查询到对应的车次信息");
                req.getRequestDispatcher("/schedulesInterface.jsp").forward(req,resp);
            }else{
                req.setAttribute("list",schedulesList);
                req.getRequestDispatcher("/viewSchedules.jsp").forward(req,resp);
            }

        }

    }
}
