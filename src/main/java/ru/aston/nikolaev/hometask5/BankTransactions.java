package ru.aston.nikolaev.hometask5;

import ru.aston.nikolaev.hometask2.util.Config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BankTransactions {
    private static final String AMOUNT_UP = "UPDATE bank_account SET amount = amount + ? WHERE id = ?";
    private static final String AMOUNT_DOWN = "UPDATE bank_account SET amount = amount - ? WHERE id = ?";
    private static final String GET_ACCOUNT = "SELECT amount from bank_account WHERE id = ?";

    public int getAmount(int id) {
        int rsl = -100;
        try (Connection connection = Config.open()){
            PreparedStatement ps = connection.prepareStatement(GET_ACCOUNT);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                rsl = resultSet.getInt("amount");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rsl;
    }


    public boolean transfer(int fromId, int toId, int amount) {
        boolean rsl;

        try (Connection connection = Config.open()){
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            PreparedStatement ps1 = connection.prepareStatement(AMOUNT_DOWN);
            ps1.setInt(1, amount);
            ps1.setInt(2, fromId);
            ps1.execute();


            PreparedStatement ps2 = connection.prepareStatement(AMOUNT_UP);
            ps2.setInt(1, amount);
            ps2.setInt(2, toId);
            rsl = ps2.execute();

            connection.commit();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rsl;
    }

}
