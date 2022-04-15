package kg.itschool.crm.dao;

import kg.itschool.crm.dao.DaoUtil.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;

public interface CrudDao<Model> {
    Optional<Model> findByPhoneNumber(String phoneNumber);


    default Connection getConnection() throws SQLException {
        final String URL = "jdbc:postgresql://localhost:5432/postgres";
        final String USERNAME = "postgres";
        final String PASSWORD = "1122";

        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    default void close(AutoCloseable closeable) {
        try {
           // Log.info(this.getClass().getSimpleName(), closeable.getClass().getSimpleName(), "Closing connection");
            closeable.close();
        } catch (Exception e) {
          //  Log.error(this.getClass().getSimpleName(), e.getStackTrace()[0].getClass().getSimpleName(), e.getMessage());
            e.printStackTrace();
        }
    }
}
