package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Vasya", "Ivanov", (byte) 30);
        System.out.println("User с именем – Vasya добавлен в базу данных");
        userService.saveUser("Masha", "Rublev", (byte) 20);
        System.out.println("User с именем – Masha добавлен в базу данных");
        userService.saveUser("Pasha", "Ideev", (byte) 10);
        System.out.println("User с именем – Pasha добавлен в базу данных");
        userService.saveUser("Dasha", "Koshkina", (byte) 5);
        System.out.println("User с именем – Dasha добавлен в базу данных");
        for (User user : userService.getAllUsers()) {
            System.out.println(user.toString());
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
