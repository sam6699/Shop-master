package uz.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import uz.dao.ProductDAO;
import uz.models.Window;
import uz.utils.FxmlUrl;

import java.net.URL;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

public class ExpenseController implements Initializable {
    @FXML
    private JFXButton one;
    @FXML
    private JFXButton two;
    @FXML
    private JFXButton three;
    @FXML
    private JFXButton four;
    @FXML
    private JFXButton five;
    @FXML
    private JFXButton six;
    @FXML
    private JFXButton seven;
    @FXML
    private JFXButton eight;
    @FXML
    private JFXButton nine;
    @FXML
    private JFXButton zero;
    @FXML
    private JFXButton zero2;
    @FXML
    private JFXButton back;
    @FXML
    private JFXButton accept;
    @FXML
    private JFXTextField expense;
    @FXML
    private JFXButton cancel;
    @FXML
    private JFXButton add;
    @FXML
    private JFXTextField reason;
    private HashMap<Long,String> object;
    @FXML
    private JFXComboBox<String> expenser;
    @FXML
    private Label label;
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
            label.setVisible(false);
            reason.setVisible(false);
            expenser.setVisible(false);
            add.setVisible(false);
            expense.setText("");
            registerButtons();
    }

    private void registerButtons() {
           one.setOnAction(event -> {
               expense.setText(expense.getText()+"1");
           });
           two.setOnAction(event -> {
               expense.setText(expense.getText()+"2");
           });
           three.setOnAction(event -> {
               expense.setText(expense.getText()+"3");
           });
           four.setOnAction(event -> {
               expense.setText(expense.getText()+"4");
           });
           five.setOnAction(event -> {
               expense.setText(expense.getText()+"5");
           });
           six.setOnAction(event -> {
               expense.setText(expense.getText()+"6");
           });
           seven.setOnAction(event -> {
               expense.setText(expense.getText()+"7");
           });
           eight.setOnAction(event -> {
               expense.setText(expense.getText()+"8");
           });
           nine.setOnAction(event -> {
                expense.setText(expense.getText()+"9");
           });
           back.setOnAction(event -> {
               if(expense.getText().length() >= 1)
                  expense.setText(expense.getText().substring(0,expense.getText().length() - 1));
           });
           zero.setOnAction(event -> {
               if(!expense.getText().isEmpty()){
                   expense.setText(expense.getText()+"0");
               }
           });
           zero2.setOnAction(event -> {
               if(!expense.getText().isEmpty()){
                   expense.setText(expense.getText()+"00");
               }
           });
           cancel.setOnAction(event -> {
                expense.setText("");
           });
           add.setOnAction(event ->
           {
               Window window = new Window("Кушиш", FxmlUrl.products.addType);
               window.setFullScreen(false);
               AddTypeController type = window.getController();
               type.startToSaveExpenser();
               window.show();
           });
           accept.setOnAction(event ->
           {
               if(!expense.getText().isEmpty())
               {
                   if(new ProductDAO().createDataExpense(Integer.parseInt(expense.getText()))){
                       Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                       stage.close();
                   }
                   else
                   {
                       System.out.println("error");
                       //hatolik bo`lgan vahti yoziladi
                   }
               }

           });
    }
}
