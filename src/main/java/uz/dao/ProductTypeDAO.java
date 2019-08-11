package uz.dao;

import uz.entity.ProductType;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductTypeDAO {
    private static ProductTypeDAO instance;
    public static ProductTypeDAO getInstance(){
        if (instance == null)
            instance = new ProductTypeDAO();
        return instance;
    }

    public PreparedStatement getInsert(ProductType productType){
        PreparedStatement stmt = null;
        String columnNames[] = new String[] { "id" };
        try {
            stmt = MarketDB.getInstance().getConnection().
                    prepareStatement("INSERT INTO product_Type (name,deleted) value (?,?)", columnNames);
            stmt.setBoolean(2,false);
            stmt.setString(1,productType.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stmt;


    }


    public PreparedStatement getProductType(int anInt) {
        PreparedStatement stmt = null;
        try {
            stmt = MarketDB.getInstance().getConnection().prepareStatement("SELECT * from product_type where id=? and deleted = false");
            stmt.setInt(1,anInt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stmt;
    }
    public PreparedStatement delete(ProductType productType){
        PreparedStatement stmt = null;
        try {
            stmt = MarketDB.getInstance().getConnection().prepareStatement("UPDATE product_type set deleted = true where id=?", Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1,productType.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stmt;

    }

    public PreparedStatement getAll(){
        PreparedStatement stmt = null;
        try {
            stmt = MarketDB.getInstance().getConnection().prepareStatement("SELECT * FROM product_type where deleted = false");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stmt;
    }
    
}
