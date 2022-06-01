package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
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
        UserDao userDao = new UserDaoJDBCImpl();

        userDao.createUsersTable();

        userDao.saveUser("nameFirst","lastNameFirst",(byte)21);
        userDao.saveUser("nameSecond","lastNameSecond",(byte)22);
        userDao.saveUser("nameThird","lastNameThird",(byte)23);
        userDao.saveUser("nameFourth","lastNameFourth",(byte)24);

        userDao.removeUserById(1);
        userDao.getAllUsers();
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }
}

