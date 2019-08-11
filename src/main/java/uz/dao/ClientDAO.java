package uz.dao;

import uz.entity.Client;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClientDAO {
    private static ClientDAO instance;
    public static ClientDAO getInstance(){
        if (instance == null)
            instance = new ClientDAO();
        return instance;
    }

    public PreparedStatement getInsert(Client client) {
        PreparedStatement stmt = null;
        String columnNames[] = new String[] { "id" };
        try {
            stmt = MarketDB.getInstance().getConnection().
                    prepareStatement("INSERT into client (companyName,director,phone,address) value (?,?,?,?)", columnNames);
            stmt.setString(1,client.getName());
            stmt.setString(2,client.getDirector());
            stmt.setString(3,client.getPhone());
            stmt.setString(4,client.getAddress());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stmt;

    }

    public PreparedStatement getPurchases(Client client){
        PreparedStatement stmt = null;
        try {
            stmt = MarketDB.getInstance().getConnection().
                    prepareStatement("SELECT * FROM sales where client_id=?");
            stmt.setInt(1,client.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stmt;

    }


    public PreparedStatement getClients() {
        PreparedStatement stmt = null;
        try {
            stmt = MarketDB.getInstance().getConnection().
                    prepareStatement("SELECT * FROM client");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stmt;
    }
    public PreparedStatement updateClients(Client client){
        PreparedStatement ptm = null;
        try {
            ptm = MarketDB.getInstance().getConnection().prepareStatement("UPDATE client set companyName=?,director = ?,phone = ?,address = ? where id="+client.getId());
            ptm.setString(1,client.getName());
            ptm.setString(2,client.getDirector());
            ptm.setString(3,client.getPhone());
            ptm.setString(4,client.getAddress());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ptm;
    }
}
