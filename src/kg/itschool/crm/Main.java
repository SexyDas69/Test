package kg.itschool.crm;

import kg.itschool.crm.dao.DaoUtil.DaoContext;
import kg.itschool.crm.dao.Manager;
import kg.itschool.crm.dao.ManagerDao;
import kg.itschool.crm.dao.impl.ManagerDaoImpl;

import java.sql.Date;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        ManagerDao managerDao = (ManagerDao) DaoContext.autowired("ManagerDao", "singleton");
        managerDao.findByPhoneNumber("+996556100165");



    }
}
