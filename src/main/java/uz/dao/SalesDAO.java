package uz.dao;

import uz.entity.Sale;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class SalesDAO {
    private static SalesDAO instance;
    public static SalesDAO getInstance(){
        if (instance == null)
            instance = new SalesDAO();
        return instance;
    }

    public PreparedStatement getInsert(Sale sale){
        long client;
        int payed;
        if (sale.getClientId() == null){
            client=0;
            payed=1;
        }else {
            client=sale.getClientId().getId();
            payed=sale.isPayed();
        }

        PreparedStatement stmt = null;
        System.out.println(sale.getProductId().getId());
        try {
            stmt = MarketDB.getInstance().getConnection().
                    prepareStatement("INSERT INTO sales(quantity,amount,product_id,transaction_id,client_id,payed) values (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
                    stmt.setDouble(1,sale.getQuantity());
                    stmt.setInt(2,sale.getAmount());
                    stmt.setInt(3,sale.getProductId().getId());
                    stmt.setInt(4,sale.getTransactionId().getId());
                    stmt.setLong(5,client);
                    stmt.setInt(6,payed);
        } catch (SQLException e) {
            e.printStackTrace();
        }
            return stmt;


    }
    public PreparedStatement getByDates(LocalDate startDate){
        PreparedStatement stmt=null;
        try {
             stmt = MarketDB.getInstance().getConnection().
        prepareStatement("SELECT sales.`product_id`, SUM(quantity) AS obshiy_soni_tovarni ,  SUM(amount) AS total_sold_price,transaction.`sale_date` FROM sales INNER JOIN TRANSACTION \n" +
                "ON sales.`transaction_id` = transaction.id  WHERE transaction.`sale_date` = ? GROUP BY product_id;");
                    stmt.setString(1,startDate.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return stmt;
    }

    public PreparedStatement getByDateByProduct(LocalDate startDate, LocalDate endDate){
        PreparedStatement statement = null;
        try {
            statement = MarketDB.getInstance().getConnection().prepareStatement("SELECT SUM(quantity) AS soni , SUM(amount) AS summasi, product_id ,sale_date FROM sales INNER JOIN TRANSACTION  ON \n" +
                    "sales.`transaction_id` = transaction.`id`  WHERE transaction.`sale_date` BETWEEN ? AND ? GROUP BY product_id");
            statement.setString(1,startDate.toString());
            statement.setString(2,endDate.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  statement;
    }

    public PreparedStatement getHistoryByClient(long companyId,LocalDate start,LocalDate end){
        PreparedStatement statement = null;
        try {
            statement = MarketDB.getInstance().getConnection().prepareStatement("SELECT SUM(quantity) AS soni , SUM(amount) AS summasi, product_id,transaction.`sale_date`  FROM sales INNER JOIN TRANSACTION  ON \n" +
                    "sales.`transaction_id` = transaction.`id`  WHERE transaction.`sale_date` BETWEEN ? AND ? AND client_id = ? GROUP BY transaction.sale_date");
            statement.setString(1,start.toString());
            statement.setString(2,end.toString());
            statement.setLong(3,companyId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  statement;






    }
    public PreparedStatement getHistoryByClient1(LocalDate date,long clientId){
        PreparedStatement stmt=null;
        try {
            stmt = MarketDB.getInstance().getConnection().
                    prepareStatement("SELECT sales.`product_id`, SUM(quantity) AS obshiy_soni_tovarni ,  SUM(amount) AS total_sold_price,transaction.`sale_date` FROM sales INNER JOIN TRANSACTION \n" +
                            "ON sales.`transaction_id` = transaction.id  WHERE transaction.`sale_date` = ? and sales.`client_id`=? GROUP BY product_id;");
            stmt.setString(1,date.toString());
            stmt.setLong(2,clientId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return stmt;
    }







    }






