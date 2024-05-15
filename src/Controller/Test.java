package Controller;

import Model.User;
import Service.AdministratorDao;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<User> scheduledUserByPage = AdministratorDao.getScheduledUserByPage(10921, 1, 5);
        System.out.println(scheduledUserByPage.isEmpty());
    }
}
