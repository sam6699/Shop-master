package uz.models;

import uz.entity.Product;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class OrderRowModel {
    private Product row;


    private BigDecimal one_quantity;

    private String name;

    private BigDecimal total_quantity;

    private BigDecimal total_price;

    private BigDecimal one_price;

    private String measureType;

    public OrderRowModel(Product row) {
        DecimalFormat decimalFormat = new DecimalFormat("#.0");

        this.row = row;

        this.total_quantity = new BigDecimal(row.getMeasureValue());
        //this.total_quantity=this.total_quantity.setScale(2,BigDecimal.ROUND_CEILING);

        this.name = row.getName();

        this.total_price = new BigDecimal(row.getPrice());


        this.one_quantity = new BigDecimal(1);
        //this.one_quantity=this.one_quantity.setScale(2,BigDecimal.ROUND_CEILING);


        this.one_price = new BigDecimal(row.getPrice());
        this.one_price = this.one_price.setScale(0, BigDecimal.ROUND_CEILING);

        this.measureType = row.getMeasureLabel();

    }

    public OrderRowModel() {

    }

    public void setQuantity(double quantity) {
        this.total_quantity = new BigDecimal(quantity);
        this.total_price = new BigDecimal((this.total_quantity.doubleValue() / row.getMeasureValue()) * row.getPrice());
    }

    public double getQuantity() {
        return this.total_quantity.doubleValue();
    }


    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.total_price.doubleValue();
    }

    public BigDecimal increment() {
        Locale.setDefault(Locale.US);
        this.total_quantity = this.total_quantity.add(one_quantity);
        BigDecimal temp = this.total_quantity.multiply(one_price);
        this.total_price = temp;
        DecimalFormat decimalFormat = new DecimalFormat("#.0");
        this.total_quantity = new BigDecimal(decimalFormat.format(total_quantity));
        return this.total_quantity;

    }

    public BigDecimal decrement() {
        Locale.setDefault(Locale.US);

        this.total_quantity = this.total_quantity.subtract(one_quantity);
        BigDecimal temp = this.total_quantity.multiply(one_price);
        this.total_price = temp;
        DecimalFormat decimalFormat = new DecimalFormat("#.0");
        this.total_quantity = new BigDecimal(decimalFormat.format(total_quantity));
        return this.total_quantity;

    }

    public Product getRow() {
        return row;
    }

    public void setRow(Product row) {
        this.row = row;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTotal_price(BigDecimal total_price) {
        this.total_price = total_price;
    }

    public String getMeasureType() {
        return measureType;
    }

    public void setMeasureType(String measureType) {
        this.measureType = measureType;
    }

    public BigDecimal getTotal_quantity() {
        return total_quantity;
    }

    public void setTotal_quantity(BigDecimal total_quantity) {
        this.total_quantity = total_quantity;
    }

    public BigDecimal getOne_quantity() {
        return one_quantity;
    }

    public void setOne_quantity(BigDecimal one_quantity) {
        this.one_quantity = one_quantity;
    }

    public BigDecimal getOne_price() {
        return one_price;
    }

    public void setOne_price(BigDecimal one_price) {
        this.one_price = one_price;
    }

    public BigDecimal getTotal_price() {
        NumberFormat numberFormat = new DecimalFormat("#");
        total_price = new BigDecimal(numberFormat.format(total_price));
        return total_price;
    }
}