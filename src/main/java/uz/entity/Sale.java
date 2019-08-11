package uz.entity;

import uz.models.ClientDto;

public class Sale {
private int id;
private Product productId;
private int amount;
private double quantity;
private Transaction transactionId;
private String date;
private ClientDto clientId;
private int payed;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public Transaction getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Transaction transactionId) {
        this.transactionId = transactionId;
    }

    public Sale(int id, Product productId, int amount, double quantity, Transaction transactionId) {
        this.id = id;
        this.productId = productId;
        this.amount = amount;
        this.quantity = quantity;
        this.transactionId = transactionId;
    }

    public Sale() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ClientDto getClientId() {
        return clientId;
    }

    public void setClientId(ClientDto clientId) {
        this.clientId = clientId;
    }

    public int isPayed() {
        return payed;
    }

    public void setPayed(int payed) {
        this.payed = payed;
    }
}
