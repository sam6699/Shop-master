package uz.service;

import uz.dao.ClientDAO;
import uz.entity.Client;
import uz.entity.Sale;
import uz.models.ClientDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClientService {

    public int addClient(Client client){
        int result = -1;

        try {
            PreparedStatement statement =ClientDAO.getInstance().getInsert(client);
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            if (rs.isFirst())
                result = rs.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;




    }

    public ArrayList<Sale> getPurchases(Client client){
        ArrayList<Sale> list = new ArrayList<>();
        try {
            ResultSet rs = ClientDAO.getInstance().getPurchases(client).executeQuery();
            while (rs.next()){
                list.add(fillSaleObject(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;

    }

    public ArrayList<Client> getClients(){
        ArrayList<Client> list = new ArrayList<>();
        try {
            ResultSet rs = ClientDAO.getInstance().getClients().executeQuery();
            while (rs.next()){
                list.add(fillClientObject(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public ArrayList<ClientDto> getClientsToTransfer(){
        ArrayList<ClientDto> list = new ArrayList<>();
        try {
            ResultSet resultSet = ClientDAO.getInstance().getClients().executeQuery();
            while (resultSet.next()){
                list.add(fillClient(resultSet,list.size() + 1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private ClientDto fillClient(ResultSet resultSet,int size) throws SQLException {
        ClientDto clientDto = new ClientDto();
        clientDto.setClientId(resultSet.getLong(1));
        clientDto.setNameCompany(resultSet.getString(2));
        clientDto.setDirector(resultSet.getString(3));
        clientDto.setPhone(resultSet.getString(4));
        clientDto.setAddress(resultSet.getString(5));
        clientDto.setId((long) size);
            return clientDto;
    }

    private Client fillClientObject(ResultSet rs) {
    Client client = new Client();
        try {
            client.setId(rs.getInt(1));
            client.setName(rs.getString(2));
        } catch (SQLException e) {
            e.printStackTrace();
        }
            return client;
    }


    private Sale fillSaleObject(ResultSet rs) {
        Sale sale = new Sale();
        ProductService productService = new ProductService();
        TransactionService transactionService = new uz.service.TransactionService();
        try {
            sale.setId(rs.getInt(1));
            sale.setQuantity(rs.getDouble(2));
            sale.setAmount(rs.getInt(3));
            sale.setProductId(productService.getProduct(rs.getInt(4)));
            sale.setTransactionId(transactionService.getTransaction(rs.getInt(5)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sale;
    }

    public int updateClient(Client client) {
        int result = -1;
        try {
            PreparedStatement statement = ClientDAO.getInstance().updateClients(client);
            result =  statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }
}
