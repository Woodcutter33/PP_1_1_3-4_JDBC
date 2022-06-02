package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {


    public static void main(String[] args) throws SQLException {
        try {
            Util.getConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("nameFirst","lastNameFirst",(byte)21);
        userService.saveUser("nameSecond","lastNameSecond",(byte)22);
        userService.saveUser("nameThird","lastNameThird",(byte)23);
        userService.saveUser("nameFourth","lastNameFourth",(byte)24);

        userService.removeUserById(1);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}

