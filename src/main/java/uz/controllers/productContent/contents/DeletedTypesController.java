package uz.controllers.productContent.contents;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import uz.Main;
import uz.controllers.productContent.controllerEvents.AddProductEvent;
import uz.entity.ProductType;
import uz.service.ProductService;
import uz.service.ProductTypeService;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Jack on 17.03.2019.
 */
public class DeletedTypesController implements Initializable
{
    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXComboBox<ProductType> typeList;

    @FXML private Label info;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        info.setVisible(false);
        events();
        typeList.getItems().addAll(new ProductTypeService().getAllProduct());
    }

    private void events()
    {
        btnAdd.setOnAction(event -> {
            if(!typeList.getSelectionModel().isEmpty()){
                new ProductTypeService().deleteProduct(new ProductType(typeList.getSelectionModel().getSelectedItem().getId(),typeList.getSelectionModel().getSelectedItem().getName()));
                new ProductService().deletedSameTypeProducts((long) typeList.getSelectionModel().getSelectedItem().getId());
                AddProductEvent addProductEvent = new AddProductEvent(AddProductEvent.ANY);
                Main.eventBus.fireEvent(addProductEvent);
                Stage stage = (Stage)((Button)(event).getSource()).getScene().getWindow();
                stage.close();
            }

        });
        btnCancel.setOnAction(event -> {
            Stage stage = (Stage)((Button)(event).getSource()).getScene().getWindow();
            stage.close();
        });
    }
}
    //        prepareToPurchase(Integer.parseInt(check.getText()),0);
    //list;
