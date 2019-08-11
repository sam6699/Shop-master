package uz.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.springframework.util.FileSystemUtils;
import uz.Main;
import uz.controllers.productContent.controllerEvents.AddProductEvent;
import uz.controllers.productContent.controllerEvents.AddTypeEvent;
import uz.entity.Product;
import uz.entity.ProductType;
import uz.models.ProductDtoList;
import uz.models.Window;
import uz.service.ProductService;
import uz.service.ProductTypeService;
import uz.utils.FilterForTextField;
import uz.utils.FxmlUrl;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Jack on 09.03.2019.
 */
public class AddProductController implements Initializable
{
    @FXML
    private AnchorPane anchor;

    @FXML
    private JFXTextField productName;

    @FXML
    private JFXTextField sold;

    @FXML
    private JFXComboBox<String>  dimensionCount;



    @FXML
    private JFXComboBox<ProductType> types;

    @FXML
    private Button addType;



    @FXML
    private JFXButton uploadImage;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnCancel;


    @FXML
    private Label infoAboutProduct;

    @FXML
    private ImageView img;

    @FXML
    private Label fill1;

    @FXML
    private Label fill2;

    @FXML
    private Label fill3;



    private Product product = new Product();
    private ProductDtoList editProduct;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        this.editProduct = new ProductDtoList();
        events();
        prepare();
        types.getItems().addAll(new ProductTypeService().getAllProduct());
        dimensionCount.getItems().addAll(new ProductService().getDimensions());


    }

    private void prepare() {
        infoAboutProduct.setVisible(false);
        fill1.setVisible(false);
        fill2.setVisible(false);
        fill3.setVisible(false);
        FilterForTextField.isIntegerValidation(sold);

    }

    private void events()
    {
            //Rasim yuklash
            uploadImage.setOnAction(event ->
            {
                //Here is give setting of FileChooser and copying it into directory
                //path of choosing File is in temp String path is selected image
                Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
                FileChooser.ExtensionFilter ex1 = new FileChooser.ExtensionFilter("JPG files(*.jpg)","*.jpg");
                FileChooser.ExtensionFilter ex2 = new FileChooser.ExtensionFilter("PNG files(*.png)","*.png");
                FileChooser fileChooser = new FileChooser();
                fileChooser.getExtensionFilters().addAll(ex1,ex2);
                File approve = fileChooser.showOpenDialog(stage);
                String g = approve.getAbsolutePath();
                File path = new File(approve.getAbsolutePath());
                File savePlace = new File("C:/images");
                if(!savePlace.exists()){
                    savePlace.mkdir();
                }
                String temp = savePlace.getAbsolutePath();
                temp +="\\" + path.getName();
                product.setPath(temp);
                savePlace = new File(temp);
               try
                {
                    FileSystemUtils.copyRecursively(path,savePlace);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                BufferedImage bf;
                try {
                    bf = ImageIO.read(approve);
                    Image image = SwingFXUtils.toFXImage(bf,null);
                    img.setImage(image);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            //Save button
            btnAdd.setOnAction(event ->
            {
                check();
                if(allFilled() && !this.editProduct.isChange())
                {
                    infoAboutProduct.setVisible(true);
                    ProductService productService = new ProductService();
                    productService.addProduct(product);
                    infoAboutProduct.setText("Товар Кўшилди: " + productName.getText());
                    clear();
                }
                else if(allFilled() && this.editProduct.isChange())
                {
                    product.setId(Math.toIntExact(this.editProduct.getProductId()));
                    infoAboutProduct.setVisible(true);
                    if(product.getPath() == null){
                        product.setPath(this.editProduct.getPathImage());
                    }
                    infoAboutProduct.setText("Товар Ўзгартирилди: " + productName.getText());
                    new ProductService().updateProduct(product);
                    clear();
                }
            });

            //Cancel button
            btnCancel.setOnAction(event -> {
                 close(event);
            });
            //Call window of Type
            addType.setOnAction(event ->
            {
                Window window = new Window("Тип кўшиш", FxmlUrl.products.addType);
                window.getStage().setOnCloseRequest(event1 -> {
                    AddTypeEvent addTypeEvent = new AddTypeEvent(AddTypeEvent.ANY);
                    Main.eventBus.fireEvent(addTypeEvent);
                });
                window.setFullScreen(false);
                window.show();
            });

            //After adding new type into comboboxes
            Main.eventBus.addEventHandler(AddTypeEvent.ANY,event -> {
                types.getItems().clear();
                types.getItems().addAll(new ProductTypeService().getAllProduct());
                types.getSelectionModel().selectLast();
            });

    }

    private void clear() {
        productName.setText("");
        productName.requestFocus();
        sold.setText("");
        img.setImage(null);
    }

    private boolean allFilled()
    {
        if(types.getSelectionModel().isEmpty())
            return false;
        product.setName(productName.getText());
        product.setMeasureLabel(dimensionCount.getSelectionModel().getSelectedItem());
        product.setPrice(Integer.parseInt(sold.getText()));
        product.setTypeId(types.getSelectionModel().getSelectedItem().getId());
        ProductType productType = new ProductType();
        productType.setId(product.getTypeId());
        product.setProductType(productType);
        System.out.println("selected item: "+dimensionCount.getSelectionModel().getSelectedItem());
        if(dimensionCount.getSelectionModel().getSelectedItem().equals("килограм")
                || dimensionCount.getSelectionModel().getSelectedItem().charAt(0) == 'к'
                || dimensionCount.getSelectionModel().getSelectedItem().charAt(0) == 'К')
        {
                product.setMeasureValue(new Double("0.1"));
            System.out.println("yes "+product.getMeasureValue());
        }
        if(dimensionCount.getSelectionModel().getSelectedItem().equals("дона")
                || dimensionCount.getSelectionModel().getSelectedItem().charAt(0) =='д'
                || dimensionCount.getSelectionModel().getSelectedItem().charAt(0) =='Д')
        {

            System.out.println("yes dona");
            product.setMeasureValue(1);
        }
        if(dimensionCount.getSelectionModel().getSelectedItem().equals("литир")){
            product.setMeasureValue(new Double("0.1"));
        }
        if(fill1.isVisible() || fill2.isVisible() || fill3.isVisible())
            return false;
        return true;
    }

    private void check() {
        fill1.setVisible(productName.getText().isEmpty());
        fill2.setVisible(sold.getText().isEmpty());
        fill3.setVisible(dimensionCount.getSelectionModel().isEmpty());
    }

    //Close window
    private void close(ActionEvent event) {
        Stage stage = (Stage)((Button)(event).getSource()).getScene().getWindow();
        stage.close();
        AddProductEvent addProductEvent = new AddProductEvent(AddProductEvent.ANY);
        Main.eventBus.fireEvent(addProductEvent);
    }


    public void setEditProduct(ProductDtoList editProduct) {
        this.editProduct = editProduct;
        productName.setText(this.editProduct.getProductName());
        sold.setText(this.editProduct.getSellPrice()+"");
        dimensionCount.setValue(editProduct.getDimension());
        img.setImage(new Image("file:///"+this.editProduct.getPathImage()));
        types.getItems().forEach(productType -> {
            if(productType.getId() == editProduct.getTypeId())
                types.getSelectionModel().select(productType);
        });

    }
}
