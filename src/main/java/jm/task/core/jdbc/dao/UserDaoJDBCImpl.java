package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }
    Util util = new Util();

    public void createUsersTable() {
        Connection connection = util.connect();
        Statement statement = null;
        String sql = "create table if not exists UsersData (id mediumint primary key not null AUTO_INCREMENT, name CHAR(30) not null, lastName char(30) not null, age int not null);";

        try {
            statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
        }
    }

    public void dropUsersTable() {
        Connection connection = util.connect();
        Statement statement = null;
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        String sql = "drop table if exists UsersData;";

        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        Connection connection = util.connect();
        Statement statement = null;
        String sql = "insert into UsersData (name, lastName, age) values ('" + name + "', '" + lastName + "', " + age + ");";

        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void removeUserById(long id) {
        Connection connection = util.connect();
        Statement statement = null;
        String sql = "delete  from UsersData where id=" + id + ";";

        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<User> getAllUsers() {
        Connection connection = util.connect();
        PreparedStatement preparedStatement = null;
        List<User> allUsers = new LinkedList<>();
        String sql = "select * from UsersData";
        try {
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long i = resultSet.getLong(1);
                User user = new User(resultSet.getString(2), resultSet.getString(3), resultSet.getByte(4));
                user.setId(i);
                allUsers.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return allUsers;
    }

    public void cleanUsersTable() {
        Connection connection = util.connect();
        Statement statement = null;
        String sql = "truncate table UsersData;";
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}