package uz.models;

import uz.entity.Sale;

public class ReportRowModel {
    private String soldDate;
    private String productName;
    private String quantity;
    private String productPrice;
    private String amount;
    private Sale sale;
    private int debt;
    public ReportRowModel(Sale sale) {
        this.sale=sale;
        this.soldDate = sale.getDate();
        this.productName = sale.getProductId().getName();
        this.quantity = sale.getQuantity()+"";
        this.productPrice = sale.getProductId().getPrice()+"";
        this.amount = sale.getAmount()+"";
        this.debt=sale.getAmount()-sale.isPayed();

    }

    public ReportRowModel(String soldDate, String productName, String quantity, String productPrice, String amount) {
        this.soldDate = soldDate;
        this.productName = productName;
        this.quantity = quantity;
        this.productPrice = productPrice;
        this.amount = amount;
    }

    public ReportRowModel() {
    }

    public String getSoldDate() {
        return soldDate;
    }

    public void setSoldDate(String soldDate) {
        this.soldDate = soldDate;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public int getDebt() {
        return debt;
    }

    public void setDebt(int debt) {
        this.debt = debt;
    }

    @Override
    public String toString() {
        return "ReportRowModel{" +
                "soldDate='" + soldDate + '\'' +
                ", productName='" + productName + '\'' +
                ", quantity='" + quantity + '\'' +
                ", productPrice='" + productPrice + '\'' +
                ", amount='" + amount + '\'' +
                ", sale=" + sale +
                '}';
    }
}
