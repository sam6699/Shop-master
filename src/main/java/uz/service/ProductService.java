package uz.service;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import uz.dao.MarketDB;
import uz.dao.ProductDAO;
import uz.entity.Product;
import uz.models.ContentButton;
import uz.models.ProductDtoList;
import uz.models.ReportRowModel;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProductService {


    public ProductService() {
    }
    public Product getProduct(int id){
        Product product = null;
        try {
            ResultSet rs = ProductDAO.getInstance().selectProduct(id).executeQuery();
            rs.next();
            if (rs.isFirst()){
                product = fillObjectSimple(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    public boolean addProductIntoShop(int productId, BigDecimal quantity, LocalDate date){
        boolean result = false;
        try {
            result = ProductDAO.getInstance().addProductIntoShop(productId,quantity).execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public ArrayList<ReportRowModel> getByDateEnterProducts(LocalDate dat1,LocalDate dat2)
    {
        ArrayList<ReportRowModel> list = new ArrayList<>();
        for(LocalDate i = dat1; i.isBefore(dat2.plusDays(1)); i = i.plusDays(1))
        {

        PreparedStatement ptm = ProductDAO.getInstance().getAllEnterProductsByDate(i);
        try {
            ResultSet resultSet = ptm.executeQuery();
            while (resultSet.next())
            {
                ReportRowModel reportRowModel = new ReportRowModel();
                reportRowModel.setProductName(this.getProduct(resultSet.getInt(1)).getName());
                reportRowModel.setQuantity(resultSet.getDouble(2)+"");
                reportRowModel.setSoldDate(resultSet.getDate(3)+"");
                System.out.println(reportRowModel);
                list.add(reportRowModel);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        }
        return list;
    }

    public int addProduct(Product product){
        int result = -1;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = ProductDAO.getInstance().insert(product);
            statement.executeUpdate();
            rs =  statement.getGeneratedKeys();
            rs.next();
            if (rs.isFirst())
                result = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int deleteProduct(Product product){
        int result = -1;
        try {
            result = ProductDAO.getInstance().delete(product).executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }

    public int updateProduct(Product product){
        int result =-1;
        try {
            result = ProductDAO.getInstance().update(product).executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int deletedSameTypeProducts(Long typeId){
        int result = -1;
        try {
            result = ProductDAO.getInstance().deleteSameTypes(typeId).executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  result;
    }

    public ArrayList<ContentButton> getAllProduct(int type_id){
        ArrayList<ContentButton> list = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement =ProductDAO.getInstance().getAllByType(type_id);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                list.add(fillObject(resultSet));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                MarketDB.getInstance().close();
                if (statement!=null)
                    statement.close();
                if (resultSet!=null)
                    resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public List<ProductDtoList> getAllProductss() {
        List<ProductDtoList> lists = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int n=1;
        try {
            statement = ProductDAO.getInstance().getAllProduct();
            resultSet =  statement.executeQuery();
            while (resultSet.next()){
                lists.add(needField(resultSet,n));
                n++;
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }finally {
            try {
                MarketDB.getInstance().close();
                if (statement!=null)
                    statement.close();
                if (resultSet!=null)
                    resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return lists;
    }

    public List<String> getDimensions() {
        List<String> list = new ArrayList<>();
        try {
            ResultSet resultSet = ProductDAO.getInstance().getAllDimension().executeQuery();
            while (resultSet.next()){
                list.add(resultSet.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<ReportRowModel> getAllProductsFromWareHouse(){
        List<ReportRowModel> list = new ArrayList<>();
        try {
            ResultSet resultSet = ProductDAO.getInstance().getAllWarehouseProduct().executeQuery();
            while (resultSet.next())
            {
                list.add(getProducts(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private ReportRowModel getProducts(ResultSet resultSet) throws SQLException {
        ReportRowModel reportRowModel = new ReportRowModel();
        reportRowModel.setProductName(resultSet.getString(1));
        reportRowModel.setQuantity(String.valueOf(resultSet.getDouble(2)));
        reportRowModel.setProductPrice(String.valueOf(resultSet.getInt(3)));
        reportRowModel.setAmount(String.valueOf(resultSet.getInt(4)));
        return reportRowModel;
    }




    private ContentButton fillObject(ResultSet rs) {
        ContentButton cb = null;
        Product product = new Product();
        try {
            product.setId(rs.getInt(1));
            product.setName(rs.getString(2));
            product.setPrice(rs.getInt(3));
            product.setMeasureLabel(rs.getString(4));
            product.setMeasureValue(rs.getDouble(5));
            product.setQuantity(rs.getDouble(6));
            product.setPath(rs.getString(7));
            product.setProductType(new ProductTypeService().getProduct(rs.getInt(8)));

            cb = new ContentButton(product);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cb;
    }


    private ProductDtoList needField(ResultSet resultSet,int n) throws SQLException {
        ProductDtoList productDtoList = new ProductDtoList();
        productDtoList.setId(n);
        productDtoList.setProductName(resultSet.getString(2));
        productDtoList.setSellPrice(resultSet.getInt(3));
        productDtoList.setDimension(resultSet.getString(4));
        productDtoList.setMeasuer_label(resultSet.getString(5));
        productDtoList.setQuantity(resultSet.getDouble(6));
        productDtoList.setPathImage(resultSet.getString(7));
        Image image = new Image("file:///"+productDtoList.getPathImage(),60,60,false,false);
        productDtoList.setImage(new ImageView(image));

        productDtoList.setProductId(resultSet.getInt(1));
        productDtoList.setTypeId(resultSet.getInt(8));
        productDtoList.setProductType(new ProductTypeService().getProduct(resultSet.getInt(8)));
        return  productDtoList;
    }

    private Product fillObjectSimple(ResultSet rs) {
        Product product = new Product();
        try {
            product.setId(rs.getInt(1));
            product.setName(rs.getString(2));
            product.setPrice(rs.getInt(3));
            product.setMeasureLabel(rs.getString(4));
            product.setMeasureValue(rs.getDouble(5));
            product.setQuantity(rs.getDouble(6));
            product.setPath(rs.getString(7));
            product.setProductType(new ProductTypeService().getProduct(rs.getInt(8)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }



}
