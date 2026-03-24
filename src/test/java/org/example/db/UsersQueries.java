package org.example.db;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.example.db.models.User;

import java.util.List;

import static org.example.db.DbUtils.getConnection;

public class UsersQueries {

    @SneakyThrows
    public static User getUserByName(String name) {
        String query = "SELECT * FROM users";
        List<User> users = new QueryRunner().query(getConnection(), query, new BeanListHandler<>(User.class));
        return users.stream().filter(user -> user.getName().equals(name)).findFirst().orElseThrow();
    }

    @SneakyThrows
    public static void setUserSuperAdminByName(String name) {
        String query =
                "UPDATE users " +
                        "SET is_superadmin = TRUE " +
                        "WHERE name = ?";

        new QueryRunner().execute(getConnection(), query, name);
    }
}
