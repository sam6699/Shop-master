package uz.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * Created by Jack on 18.03.2019.
 */
public class TypeReportController implements Initializable {

    @FXML private JFXCheckBox groupProduct;
    @FXML private JFXCheckBox soldProducts;
    @FXML private JFXButton btn;
    @FXML private AnchorPane anchor;
    private ReportController params;
    @FXML private JFXCheckBox enterProducts;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
            events();
    }

    private void events() {
        btn.setOnAction(event -> {
            if(groupProduct.isSelected()){
                params.change = 1;
                params.drawTableWithGroupProduct();
            }
            else if(soldProducts.isSelected()){
                params.change = 2;
                params.drawTableWithGroupProduct();
            }

            else
            {
                params.change = 3;
                params.drawTableWithGroupProduct();
            }
            Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            stage.close();
        });
        soldProducts.setOnAction(event -> {
            soldProducts.setSelected(true);
            groupProduct.setSelected(false);
            enterProducts.setSelected(false);
        });
        groupProduct.setOnAction(event -> {
            groupProduct.setSelected(true);
            soldProducts.setSelected(false);
            enterProducts.setSelected(false);
        });
        enterProducts.setOnAction(event -> {
            enterProducts.setSelected(true);
            soldProducts.setSelected(false);
            groupProduct.setSelected(false);
        });
    }

    private void generateAnimation(){
        Random random = new Random();
        int sizeOfSqaure = random.nextInt(50) + 1;
        int speedOfSqaure = random.nextInt(10) + 5;
        int startXPoint = random.nextInt(420);
        int startYPoint = random.nextInt(350);
        int direction = random.nextInt(5) + 1;
        KeyValue X = null;
        KeyValue Y = null;
        Rectangle r1 = null;
        switch (direction){
            case 1 :
                r1 = new Rectangle(0,startYPoint,sizeOfSqaure,sizeOfSqaure);
                X = new KeyValue(r1.xProperty(), 350 -  sizeOfSqaure);
                break;
            case 2 :
                r1 = new Rectangle(startXPoint,0,sizeOfSqaure,sizeOfSqaure);
                Y = new KeyValue(r1.yProperty(), 420 - sizeOfSqaure);
                break;
            case 3 :
                r1 = new Rectangle(startXPoint,0,sizeOfSqaure,sizeOfSqaure);
                X = new KeyValue(r1.xProperty(), 350 -  sizeOfSqaure);
                Y = new KeyValue(r1.yProperty(), 420 - sizeOfSqaure);
                break;
            case 4 :
                r1 = new Rectangle(startXPoint,420-sizeOfSqaure ,sizeOfSqaure,sizeOfSqaure);
                Y = new KeyValue(r1.xProperty(), 0);
                break;
            case 5 :
                r1 = new Rectangle(420-sizeOfSqaure,startYPoint,sizeOfSqaure,sizeOfSqaure);
                X = new KeyValue(r1.xProperty(), 0);
                break;
            case 6 :
                r1 = new Rectangle(startXPoint,0,sizeOfSqaure,sizeOfSqaure);
                X = new KeyValue(r1.xProperty(), 350 -  sizeOfSqaure);
                Y = new KeyValue(r1.yProperty(), 420 - sizeOfSqaure);
                break;
        }
        r1.setFill(Color.web("#F89406"));
        r1.setOpacity(0.1);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(speedOfSqaure * 1000) , X, Y);
        Timeline timeline = new Timeline();
        timeline.setCycleCount(-1);
        timeline.setAutoReverse(true);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
        anchor.getChildren().add(r1);

    }

    public void setParams(ReportController params) {
        this.params = params;
    }
}
