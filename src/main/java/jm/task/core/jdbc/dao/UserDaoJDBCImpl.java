package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private final static Connection connection;
// Идея настоятельно  рекомендует делать так
    static {
        try {
            connection = Util.getConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static final String insertUser = "INSERT INTO usertabl (name, lastName, age) VALUES(?,?,?)";
    private static final String removeUser = "DELETE FROM usertabl WHERE id=?";
    private static final String getAll = "SELECT * FROM usertabl";
    private static final String cleanAll = "TRUNCATE usertabl";


    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS usertabl" +
                    "(id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(20), lastName VARCHAR(20), age INT)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Таблица создана");
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS usertabl");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Таблица удалена");
    }

    public void saveUser(String name, String lastName, byte age) {
//        connection.setAutoCommit(false);
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertUser)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
//            connection.rollback();
        }
        System.out.println("Юзер добавлен");
    }

    public void removeUserById(long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(removeUser)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Все юзеры удалены");
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

         try (ResultSet resultSet = connection.prepareStatement(getAll).executeQuery()) {

            while (resultSet.next()) {
                User user = new User(resultSet.getString("name"),
                        resultSet.getString("lastName"),
                        resultSet.getByte("age"));
                user.setId(resultSet.getLong("id"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Список юзеров получен");
         return users;
    }


    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(cleanAll);
        } catch (SQLException e) {
            e.printStackTrace();
        }System.out.println("Таблица очищена");

    }
}
