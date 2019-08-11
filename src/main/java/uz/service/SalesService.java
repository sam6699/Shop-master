package uz.service;

import uz.dao.SalesDAO;
import uz.entity.Product;
import uz.entity.Sale;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class SalesService {
    public SalesService() {
    }

    public int addSale(Sale sale){
        int result = -1;

        try {
            PreparedStatement statement = SalesDAO.getInstance().getInsert(sale);
            statement.executeUpdate();
            ResultSet rs =  statement.getGeneratedKeys();
            if (rs.isFirst())
                result = rs.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
    public ArrayList<Sale> getSalesByDateWithGroupProducts(LocalDate start, LocalDate end){
        ArrayList<Sale> list = new ArrayList<>();
        try {
            ResultSet resultSet = SalesDAO.getInstance().getByDateByProduct(start,end).executeQuery();
            while (resultSet.next()){
                list.add(fillObject1(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<Sale> getSalesByDates(LocalDate start){
        ArrayList<Sale> list = new ArrayList<>();

        try {
            ResultSet rs = SalesDAO.getInstance().getByDates(start).executeQuery();

            while (rs.next()){

                list.add(fillObject(rs));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
            return list;
    }
    public ArrayList<Sale> getHistoryByClient(Long companyName,LocalDate start,LocalDate end){
        ArrayList<Sale> list = new ArrayList<>();
        try {
            ResultSet resultSet = SalesDAO.getInstance().getHistoryByClient(companyName,start,end).executeQuery();
            while (resultSet.next()){
                list.add(fillObject2(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;


    }
    public ArrayList<Sale> getOnDayHistory(Long companyId,LocalDate start){
        ArrayList<Sale> list = new ArrayList<>();

        try {
            ResultSet rs = SalesDAO.getInstance().getHistoryByClient1(start,companyId).executeQuery();

            while (rs.next()){

                list.add(fillObject(rs));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;



    }


    private Sale fillObject(ResultSet rs) {
        Sale sale = new Sale();
        ProductService productService = new ProductService();
        TransactionService transactionService = new uz.service.TransactionService();
        try {

            sale.setQuantity(rs.getDouble(2));
            sale.setAmount(rs.getInt(3));
            sale.setProductId(productService.getProduct(rs.getInt(1)));
            sale.setDate(rs.getString(4));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sale;
    }
    private Sale fillObject1(ResultSet resultSet){
        Sale sale = new Sale();
        ProductService productService = new ProductService();
        try {
            sale.setQuantity(resultSet.getDouble(1));
            sale.setAmount(resultSet.getInt(2));
            sale.setProductId(productService.getProduct(resultSet.getInt(3)));
            sale.setDate(resultSet.getString(4));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sale;
    }
    private Sale fillObject2(ResultSet resultSet){
        Sale sale = new Sale();
        ProductService productService = new ProductService();
        try {
            sale.setQuantity(resultSet.getDouble(1));
            sale.setAmount(resultSet.getInt(2));
            Product pr = new Product();
            pr.setName("");
            sale.setProductId(pr);
            sale.setDate(resultSet.getString(4));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sale;
    }




}
