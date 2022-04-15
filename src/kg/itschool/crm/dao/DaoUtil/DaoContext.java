package kg.itschool.crm.dao.DaoUtil;

import kg.itschool.crm.dao.CrudDao;
import kg.itschool.crm.dao.ManagerDao;
import kg.itschool.crm.dao.impl.ManagerDaoImpl;

public class DaoContext {
    static {
        try {
            System.out.println("Loading driver...");
            Class.forName("org.postgresql.Driver");
            System.out.println("Driver loaded.");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver loading failed");
            e.printStackTrace();
        }
    }

    private static ManagerDao managerDao;


    public static CrudDao<?> autowired(String qualifier, String scope) {
        if (!scope.equals("singleton") && !scope.equals("prototype")) {
            throw new RuntimeException("Invalid scope of bean " + scope);
        }
        switch (qualifier) {
            case "ManagerDao":
                return getManagerDaoSQL(scope);
            default:
            throw new RuntimeException("Can not find bean for autowiring: " + qualifier);
        }
    }

    private static ManagerDao getManagerDaoSQL(String scope) {
        if (scope.equals("prototype")) {
            return new ManagerDaoImpl();
        }
        if (managerDao == null) {
            managerDao = new ManagerDaoImpl();
        }
        return managerDao;
    }
}


