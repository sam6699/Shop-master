package uz.entity;

public class Product {
    private int id;
    private String name;
    private int price;
    private String measureLabel;
    private double measureValue;
    private double quantity;
    private String path;
    private int typeId;
    private boolean deleted = false;

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    private ProductType productType;
    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }



    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Product() {
    }

    public Product(int id) {
        this.id = id;
    }

    public Product(int id, String name, int price, String measure, double measureValue) {

        this.id = id;
        this.name = name;
        this.price = price;
        this.measureLabel = measure;
        this.measureValue = measureValue;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getMeasureLabel() {
        return measureLabel;
    }

    public void setMeasureLabel(String measure) {
        this.measureLabel = measure;
    }

    public double getMeasureValue() {
        return measureValue;
    }

    public void setMeasureValue(double measureValue) {
        this.measureValue = measureValue;
    }

    public void subQuantity(double sub){
        this.quantity-=sub;
    }
    public void addQuantity(double sub){
        this.quantity += sub;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", measureLabel='" + measureLabel + '\'' +
                ", measureValue=" + measureValue +
                ", quantity=" + quantity +
                ", path='" + path + '\'' +
                ", typeId=" + typeId +
                ", productType=" + productType +
                '}';
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
