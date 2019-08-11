package uz.controllers.productContent.contents;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import uz.Main;
import uz.controllers.Controller;
import uz.controllers.productContent.controllerEvents.AddProductEvent;
import uz.entity.Product;
import uz.models.ContentButton;
import uz.service.ProductService;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Jack on 17.03.2019.
 */
public class ConfirmDeletController implements Initializable {
    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private Label info;
    private int productId;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }



    public void setForDelet(String name,int id){
        productId = id;
        info.setText(name+" товарини ўчиришга тасдиклаш");
    }

    public void clicked(ActionEvent actionEvent) {
        String button = ((Button)actionEvent.getSource()).getId();
        if(button.equals("btnAdd")){
            //delete product
            Product product = new ProductService().getProduct(Math.toIntExact(productId));
            new ProductService().deleteProduct(new Product(Math.toIntExact(productId)));
            AddProductEvent addProductEvent = new AddProductEvent(AddProductEvent.ANY);
            Main.eventBus.fireEvent(addProductEvent);
        }
        Stage stage = (Stage)((Button)(actionEvent).getSource()).getScene().getWindow();
        stage.close();
    }
}
