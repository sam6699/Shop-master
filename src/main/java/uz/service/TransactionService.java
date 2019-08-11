package uz.service;

import uz.dao.TransactionDAO;
import uz.entity.Transaction;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionService {

    public Transaction getTransaction(int anInt) {
        Transaction transaction = null;
        try {
            ResultSet rs = TransactionDAO.getInstance().getTransaction(anInt).executeQuery();
            rs.next();
            if (rs.isFirst())
                transaction = fillObejct(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transaction;
    }

    public int addTransaction(Transaction transaction){
        int result = -1;
        try {
            PreparedStatement stmt =TransactionDAO.getInstance().getInsert(transaction);
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            if (rs.isFirst()){
                result = rs.getInt(1);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private Transaction fillObejct(ResultSet rs) {
        Transaction transaction = new Transaction();
        try {
            transaction.setId(rs.getInt(1));
            transaction.setCash(rs.getInt(2));
            transaction.setCard(rs.getInt(3));
            transaction.setDate(rs.getDate(4));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transaction;
    }
}
