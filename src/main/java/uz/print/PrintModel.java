package uz.print;

import java.math.BigDecimal;

public class PrintModel {
    private int id;
    private String name;
    private String quantity;
    private String measureType;
    private int summa;
    private BigDecimal jami;
    private String dimension;
    private BigDecimal price;
    private boolean tip1 = false;
    private String soldDate;
    private String companyName;
    private String directorName;
    private String phoneNumber;
    private String sell;
    private String getSumm;
    private String totalPrice;
    private String qqs;

    public PrintModel() {
    }
    public PrintModel(int id, String name,String quantity ,String soldDate){
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.soldDate = soldDate;
    }

    public PrintModel(int id, String name, String quantity, String measureType, int summa) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.measureType = measureType;
        this.summa = summa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getMeasureType() {
        return measureType;
    }

    public void setMeasureType(String measureType) {
        this.measureType = measureType;
    }

    public int getSumma() {
        return summa;
    }

    public void setSumma(int summa) {
        this.summa = summa;
    }

    public BigDecimal getJami() {
        return jami;
    }

    public void setJami(BigDecimal jami) {
        this.jami = jami;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isTip1() {
        return tip1;
    }

    public void setTip1(boolean tip1) {
        this.tip1 = tip1;
    }

    public String getSoldDate() {
        return soldDate;
    }

    public void setSoldDate(String soldDate) {
        this.soldDate = soldDate;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSell() {
        return sell;
    }

    public void setSell(String sell) {
        this.sell = sell;
    }

    public String getGetSumm() {
        return getSumm;
    }

    public void setGetSumm(String getSumm) {
        this.getSumm = getSumm;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getQqs() {
        return qqs;
    }

    public void setQqs(String qqs) {
        this.qqs = qqs;
    }
}
