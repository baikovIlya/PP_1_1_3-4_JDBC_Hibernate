package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.*;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        User user1 = new User("Ilya", "Baykov", (byte)31);
        User user2 = new User("Alena", "Manzhina", (byte)31);
        User user3 = new User("Ivan", "Ivanov", (byte)40);
        User user4 = new User("Vasiliy", "Petrov", (byte)50);
        List<User> inputUsers = Arrays.asList(user1, user2, user3, user4);
        for (User user : inputUsers) {
            userService.saveUser(user.getName(), user.getLastName(), user.getAge());
            System.out.printf("User с именем — %s добавлен в базу данных\n", user.getName());
        }
        List<User> usersFromDB = userService.getAllUsers();
        for (User user : usersFromDB) {
            System.out.println(user);
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
