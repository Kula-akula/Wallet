package kg.wallet.dao;

import kg.wallet.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        String SQL = "select * from users";
        try (Connection connection = DB.connect();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(SQL)) {
            while(rs.next()) {
                users.add(User.builder()
                        .id(rs.getInt("ID"))
                        .name(rs.getString("NAME"))
                        .password(rs.getString("PASSWORD"))
                        .createdDate(rs.getTimestamp("CREATED_DATE"))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public User getUserById(int id) {
        String SQL = "select * from users where id = ?";
        try (Connection connection = DB.connect();
             PreparedStatement statement = connection.prepareStatement(SQL)){
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()){
                if (rs.next()) {
                    return User.builder()
                            .id(rs.getInt("ID"))
                            .name(rs.getString("NAME"))
                            .password(rs.getString("PASSWORD"))
                            .createdDate(rs.getTimestamp("CREATED_DATE"))
                            .build();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User updateUser(User user) {
        String SQL = "update users set name = ?, password = ? where id = ?";
        try (Connection connection = DB.connect();
             PreparedStatement statement = connection.prepareStatement(SQL)){
            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());
            statement.setInt(3, user.getId());
            statement.executeUpdate();
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User createUser(User user) {
        String SQL = "insert into users (name, password,created_date) values (?,?,now())";
        try (Connection connection = DB.connect();
             PreparedStatement statement = connection.prepareStatement(SQL)){
            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());
            statement.executeUpdate();
            return user;//TODO how to get inserted id
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean deleteById(int id) {
        String SQL = "delete from users where id=?";
        try (Connection connection = DB.connect();
             PreparedStatement statement = connection.prepareStatement(SQL)){
            statement.setInt(1, id);
            int count = statement.executeUpdate();
            return count > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
