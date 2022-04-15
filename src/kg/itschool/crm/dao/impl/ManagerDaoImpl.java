package kg.itschool.crm.dao.impl;

import kg.itschool.crm.dao.DaoUtil.Log;
import kg.itschool.crm.dao.Manager;
import kg.itschool.crm.dao.ManagerDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ManagerDaoImpl implements ManagerDao {


    public Optional<Manager> findByPhoneNumber(String phoneNumber) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Manager manager = null;

        try {
            Log.info(this.getClass().getSimpleName() + " findByPhoneNumber(" + phoneNumber + ")", Connection.class.getSimpleName(), "Establishing connection");
            connection = getConnection();

            String readQuery = "SELECT * FROM tb_managers WHERE phone_number = ?";

            preparedStatement = connection.prepareStatement(readQuery);
            preparedStatement.setString(1, phoneNumber);

            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            manager = new Manager();
            manager.setId(resultSet.getLong("id"));
            manager.setFirstName(resultSet.getString("first_name"));
            manager.setLastName(resultSet.getString("last_name"));
            manager.setEmail(resultSet.getString("email"));
            manager.setPhoneNumber(resultSet.getString("phone_number"));
            manager.setDob(resultSet.getDate("dob").toLocalDate());
            manager.setDateCreated(resultSet.getTimestamp("date_created").toLocalDateTime());
            System.out.println(manager);
        } catch (Exception e) {
            Log.error(this.getClass().getSimpleName(), e.getStackTrace()[0].getClass().getSimpleName(), e.getMessage());
            e.printStackTrace();
        } finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
        return Optional.of(manager);

    }


}

