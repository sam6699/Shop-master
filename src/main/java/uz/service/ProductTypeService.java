package uz.service;

import uz.dao.ProductTypeDAO;
import uz.entity.ProductType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductTypeService {
    public ProductTypeService() {
    }
    public ProductType getProduct(int id){
        ProductType product = null;
        ResultSet rs = null;
        try {
           rs = ProductTypeDAO.getInstance().getProductType(id).executeQuery();
            rs.next();
            if (rs.isFirst()){
                product = fillObject(rs);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if(rs != null) {
                try
                {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return product;
    }

    public int addProduct(ProductType product){
        int result = -1;
        try {
            PreparedStatement statement = ProductTypeDAO.getInstance().getInsert(product);
            statement.executeUpdate();
            ResultSet rs =  statement.getGeneratedKeys();
            rs.next();
            if (rs.isFirst())
                result = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int deleteProduct(ProductType product){
        int result = -1;
        try {
            result = ProductTypeDAO.getInstance().delete(product).executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }

    public ArrayList<ProductType> getAllProduct(){
        ArrayList<ProductType> list = new ArrayList<>();
        try {
            ResultSet rs = ProductTypeDAO.getInstance().getAll().executeQuery();
            while (rs.next()){
                list.add(fillObject(rs));


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private ProductType fillObject(ResultSet rs) {
        ProductType product = new ProductType();
        try {
            product.setId(rs.getInt(1));
            product.setName(rs.getString(2));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }




}
