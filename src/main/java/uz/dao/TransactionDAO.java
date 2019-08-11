package uz.dao;

import java.sql.Statement;
import uz.entity.Transaction;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionDAO {
    private static TransactionDAO instance;
    public static TransactionDAO getInstance(){
        if (instance == null)
            instance = new TransactionDAO();
        return instance;
    }

    public PreparedStatement getInsert(Transaction transaction){
        PreparedStatement stmt = null;
        String columnNames[] = new String[] { "id" };
        try {
            stmt = MarketDB.getInstance().getConnection().
                    prepareStatement("INSERT INTO transaction (cash,card,sale_date) values (?,?,?)", columnNames);
            stmt.setInt(1,transaction.getCash());
            stmt.setInt(2,transaction.getCard());
            stmt.setDate(3,transaction.getDate());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stmt;


    }


    public PreparedStatement getTransaction(int anInt) {
        PreparedStatement stmt = null;
        try {
         stmt = MarketDB.getInstance().getConnection().prepareStatement("SELECT * from transaction where id=?");
         stmt.setInt(1,anInt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stmt;
    }
}
