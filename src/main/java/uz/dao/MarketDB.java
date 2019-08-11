package uz.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MarketDB{
    private static MarketDB  instance;
    private static Connection con;
    public static MarketDB getInstance(){
        if(instance==null)
            instance=new MarketDB();
        return instance;
    }

    public Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/marketdb?useLegacyDatetimeCode=false&amp&serverTimezone=UTC&useUnicode=yes&characterEncoding=UTF-8";
        String login = "root";
        String password = "";
        try {
            con = DriverManager.getConnection(url,login,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;

    }
    public void close(){
        if (con!=null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }



}
