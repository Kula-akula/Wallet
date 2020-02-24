package kg.wallet.dao;

import kg.wallet.model.User;
import kg.wallet.model.Wallet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WalletDao {
    static UserDao userDao=new UserDao();
    public List<Wallet> getWallets() {
        List<Wallet> wallets = new ArrayList<>();
        String SQL = "select * from wallets";
        try (Connection connection = DB.connect();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(SQL)) {
            while(rs.next()) {
                wallets.add(Wallet.builder()
                        .id(rs.getInt("ID"))
                        .name(rs.getString("NAME"))
                        .user(userDao.getUserById(rs.getInt("USER_ID")))
                        .isActive(rs.getBoolean("IS_ACTIVE"))
                        .createdDate(rs.getTimestamp("CREATED_DATE"))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wallets;
    }

    public Wallet getWalletById(int id) {

        String SQL = "select * from users where id = ?";
        try (Connection connection = DB.connect();
             PreparedStatement statement = connection.prepareStatement(SQL)){
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()){
                if (rs.next()) {
                    return Wallet.builder()
                            .id(rs.getInt("ID"))
                            .name(rs.getString("NAME"))
                            .user(userDao.getUserById(rs.getInt("USER_ID")))
                            .isActive(rs.getBoolean("IS_ACTIVE"))
                            .createdDate(rs.getTimestamp("CREATED_DATE"))
                            .build();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Wallet updateWallet(Wallet wallet) {
        String SQL = "update wallets set name = ?, user_id = ?, is_active=? where id = ?";
        try (Connection connection = DB.connect();
             PreparedStatement statement = connection.prepareStatement(SQL)){
            statement.setString(1, wallet.getName());
            statement.setInt(2, wallet.getUserId());
            statement.setBoolean(3,wallet.isActive());
            statement.setInt(4, wallet.getId());
            statement.executeUpdate();
            return wallet;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Wallet createWallet(Wallet wallet) {
        String SQL = "insert into wallets (name, user_id, is_active, created_date) values (?,?,?,now())";
        try (Connection connection = DB.connect();
             PreparedStatement statement = connection.prepareStatement(SQL)){
            statement.setString(1, wallet.getName());
            statement.setInt(2, wallet.getUserId());
            statement.setBoolean(3, wallet.isActive());
            statement.executeUpdate();
            return wallet;//TODO how to get inserted id
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean deleteById(int id) {
        String SQL = "delete from wallets where id=?";
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
