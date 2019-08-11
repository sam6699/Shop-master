package uz.dao;

import uz.entity.Product;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class ProductDAO {
    private static ProductDAO instance;
    public static ProductDAO getInstance(){
        if (instance == null)
            instance = new ProductDAO();
        return instance;
    }

    public PreparedStatement insert(Product product){
        System.out.println(product);
        PreparedStatement stmt = null;
        try {
            String[] columnNames = new String[]{"id"};
             stmt = MarketDB.getInstance().getConnection().
                    prepareStatement("INSERT INTO product (name,price,measure_label,measure_value,quantity,path,product_type_id,deleted) values (?,?,?,?,?,?,?,?)",columnNames);
                    stmt.setString(1,product.getName());
                    stmt.setInt(2,product.getPrice());
                    stmt.setString(3,product.getMeasureLabel());
                    stmt.setDouble(4,product.getMeasureValue());
                    stmt.setDouble(5,product.getQuantity());
                    stmt.setString(6,product.getPath());
                    stmt.setLong(7,product.getTypeId());
                    stmt.setBoolean(8,product.isDeleted());
        } catch (SQLException e) {
            e.printStackTrace();
        }
            return stmt;

    }

    public PreparedStatement delete(Product product){
        PreparedStatement stmt = null;
        try {
            stmt = MarketDB.getInstance().getConnection().prepareStatement("UPDATE product SET  deleted = TRUE  where id=?",Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1,product.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stmt;

    }

    public PreparedStatement update(Product product){
        PreparedStatement stmt=null;
        System.out.println(product.getTypeId());
        try {
            stmt = MarketDB.getInstance().getConnection().
                    prepareStatement("UPDATE product set name = ?, price=?,measure_label=?,path=?,product_type_id = ?,quantity=?,measure_value = ? where id ="+product.getId(), Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1,product.getName());
            stmt.setInt(2,product.getPrice());
            stmt.setString(3,product.getMeasureLabel());
            stmt.setString(4,product.getPath());
            stmt.setLong(5,product.getProductType().getId());
            stmt.setDouble(6,product.getQuantity());
            stmt.setDouble(7,product.getMeasureValue());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return stmt;
    }
    public PreparedStatement deleteSameTypes(Long id){
        PreparedStatement statement = null;
        try {
            statement = MarketDB.getInstance().getConnection().prepareStatement("update product set deleted = true where product_type_id = ?");
            statement.setLong(1,id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }
    public PreparedStatement getAllByType(int id_type){
        PreparedStatement stmt = null;
        try {
            stmt = MarketDB.getInstance().getConnection().prepareStatement("SELECT * FROM product where product_type_id=? and product.deleted = false");
            stmt.setInt(1,id_type);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stmt;
    }
    public PreparedStatement getAllProduct(){
        PreparedStatement stmt = null;
        try {
            stmt = MarketDB.getInstance().getConnection().prepareStatement("SELECT * FROM product where deleted = false");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stmt;
    }


    public PreparedStatement selectProduct(int id) {
        PreparedStatement stmt = null;
        try {
            stmt = MarketDB.getInstance().getConnection().prepareStatement("SELECT * FROM product where id=?");
            stmt.setInt(1,id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stmt;
    }

    public PreparedStatement getAllDimension() throws SQLException {
        PreparedStatement ptm;
        ptm = MarketDB.getInstance().getConnection().prepareStatement("SELECT * FROM typedimension");
        return ptm;
    }
    public PreparedStatement getAllWarehouseProduct(){
        PreparedStatement ptm = null;
        try {
            ptm = MarketDB.getInstance().getConnection().prepareStatement("SELECT NAME,quantity,price,price*quantity AS totalsumma FROM product");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ptm;
    }

    public PreparedStatement addProductIntoShop(int productId, BigDecimal quantity) {
        PreparedStatement ptm = null;
        try {
            ptm = MarketDB.getInstance().getConnection().prepareStatement("INSERT INTO product_get_history(productId,quantity,date) VALUES(?,?,curdate())");
            ptm.setInt(1,productId);
            ptm.setBigDecimal(2,quantity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ptm;
    }

    public PreparedStatement getAllEnterProductsByDate(LocalDate dat1)
    {
        PreparedStatement ptm = null;
        try {
            ptm = MarketDB.getInstance().getConnection().prepareStatement("SELECT product.id,SUM(product_get_history.quantity),product_get_history.date AS summa FROM product_get_history \n" +
                    "INNER JOIN product ON product.id = product_get_history.`productId`  WHERE product_get_history.`date` =? GROUP BY product.id");
            ptm.setString(1, dat1.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ptm;
    }
    public boolean createDataExpense(int summa){
        try {
            PreparedStatement ptm = MarketDB.getInstance().getConnection().prepareStatement("insert into expense(`summa`,`get_of_time`) values(?,curdate())");
            ptm.setInt(1,summa);
            ptm.execute();
            ptm.close();
            return  true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  false;
    }
    public int expenseAllSuma(String date){
        int total = 0;
        try
        {
            PreparedStatement ptm = MarketDB.getInstance().getConnection().prepareStatement("SELECT SUM(summa) FROM expense WHERE get_of_time = ? GROUP BY get_of_time");
            ptm.setString(1,date);
            ResultSet resultSet = ptm.executeQuery();
            while (resultSet.next()){
             total =   resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }
}
