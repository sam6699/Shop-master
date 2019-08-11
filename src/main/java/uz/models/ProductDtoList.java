package uz.models;

import javafx.scene.image.ImageView;
import uz.entity.ProductType;

/**
 * Created by Jack on 16.03.2019.
 */
public class ProductDtoList {
    private int id;
    private String productName;
    private int typeId;
    private String dimension;
    private String measuer_label;
    private String measure_value;
    private int sellPrice;
    private String pathImage;
    private ImageView image;
    private int productId;
    private double quantity;
    private boolean change = false;
    private ProductType productType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(int sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getPathImage() {
        return pathImage;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getMeasuer_label() {
        return measuer_label;
    }

    public void setMeasuer_label(String measuer_label) {
        this.measuer_label = measuer_label;
    }

    public String getMeasure_value() {
        return measure_value;
    }

    public void setMeasure_value(String measure_value) {
        this.measure_value = measure_value;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public boolean isChange() {
        return change;
    }

    public void setChange(boolean change) {
        this.change = change;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    @Override
    public String toString() {
        return "ProductDtoList{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", typeId=" + typeId +
                ", dimension='" + dimension + '\'' +
                ", measuer_label='" + measuer_label + '\'' +
                ", measure_value='" + measure_value + '\'' +
                ", sellPrice=" + sellPrice +
                ", pathImage='" + pathImage + '\'' +
                ", image=" + image +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", change=" + change +
                ", productType=" + productType +
                '}';
    }
}
