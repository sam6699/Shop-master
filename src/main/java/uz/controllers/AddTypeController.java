package uz.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import uz.Main;
import uz.controllers.productContent.controllerEvents.AddTypeEvent;
import uz.entity.ProductType;
import uz.service.ProductTypeService;


import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Jack on 09.03.2019.
 */
public class AddTypeController implements Initializable
{
    @FXML
     private AnchorPane pane;

    @FXML
    private JFXButton btn;

    @FXML
    private JFXButton cancel;

    @FXML
    private JFXTextField field;

    @FXML
    private Label info;

    @FXML
    private ImageView img;

    private int id = 0;
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        events();


    }

    private void events() {
            cancel.setOnAction(event -> {
                Stage stage = (Stage)((Button)(event).getSource()).getScene().getWindow();
                stage.close();
            });
            btn.setOnAction(event ->
            {
                if(!field.getText().isEmpty())
                {
                    if(this.id == 0)
                        saveIt();
                    else{
                        System.out.println("hello");
                        //this method
                    }
                    Stage stage = (Stage)((Button)(event).getSource()).getScene().getWindow();
                    stage.close();
                }

            });
            pane.setOnKeyPressed(event -> {
                if(event.getCode().equals(KeyCode.ENTER))
                {
                    if(!field.getText().isEmpty())
                    {
                        saveIt();
                        Stage stage = (Stage)((AnchorPane)(event).getSource()).getScene().getWindow();
                        stage.close();
                    }

                }
            });
    }

    private void saveIt()
    {
            //Save It
            new ProductTypeService().addProduct(new ProductType(field.getText()));
            AddTypeEvent addTypeEvent = new AddTypeEvent(AddTypeEvent.ANY);
            Main.eventBus.fireEvent(addTypeEvent);
    }
    public void startToSaveExpenser(){
        info.setText("Исми фамилиясини киритинг");
        //how to set image into img
        img.setImage(new Image(getClass().getResourceAsStream("/img/icons8.png")));
        this.id = 1;
    }
}
