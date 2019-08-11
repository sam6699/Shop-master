package uz.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import uz.models.OrderRowModel;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

public class KeyboardSimple implements Initializable {



    private OrderRowModel orm;

    @FXML
    private Button one;

    @FXML
    private Button two;

    @FXML
    private Button three;

    @FXML
    private Button four;

    @FXML
    private Button five;

    @FXML
    private Button six;

    @FXML
    private Button seven;

    @FXML
    private Button eight;

    @FXML
    private Button nine;

    @FXML
    private Button zero;

    @FXML
    private Button zero2;

    @FXML
    private Button zero3;

    @FXML
    private Button back;

    @FXML
    private Button purchase;

    @FXML
    private TextField cash;

    @FXML
    private Label summa;

    @FXML
    private Label toPurchase;

    private String value="";

    private int selectedInput=0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        initInputs();
        initDigits();
        initBack();
        initConfirm();


    }

    public void cl(){



    }

    private void initInputs(){
        cash.setOnMouseClicked(event -> {
            selectedInput=2;
            if (cash.getText().equals("0"))
                cash.setText("");

        });


        cash.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue){
                selectedInput=2;
                if (cash.getText().equals("0"))
                    cash.setText("");
            }else{
                if (cash.getText().equals(""))
                    cash.setText("0");
            }


        });
}
    public void calc(String chars){
        value+=chars;
        cash.setText(value);
        double f = Double.parseDouble(value);
        toPurchase.setText(f*this.orm.getOne_price().doubleValue()+"");


    }
    private void initDigits(){
        one.setOnMouseClicked(event -> {
              calc("1");
        });

        two.setOnMouseClicked(event -> {
              calc("2");
        });

        three.setOnMouseClicked(event -> {
              calc("3");

        });

        four.setOnMouseClicked(event -> {
            calc("4");
        });

        five.setOnMouseClicked(event -> {
                calc("5");
        });

        six.setOnMouseClicked(event -> {
                calc("6");
        });

        seven.setOnMouseClicked(event -> {
                calc("7");
        });

        eight.setOnMouseClicked(event -> {
                calc("8");
        });

        nine.setOnMouseClicked(event -> {
                calc("9");
        });

        zero.setOnMouseClicked(event -> {
                calc("0");
        });

        zero2.setOnMouseClicked(event -> {
                calc("00");
        });

        zero3.setOnMouseClicked(event -> {
                calc(".");
        });




    }




    private void initBack(){
        String temp="./resources/img/back.png";
        back.setId("back-btn");
        back.setOnMouseClicked(event -> {
                if (cash.getText().length()==1){
                    if (cash.getText().charAt(0)!='0'){
                        cash.setText("0");
                        value="";
                    }

                }else {
                    value=cash.getText(0,cash.getText().length()-1);
                    cash.setText(value);
              }

            toPurchase.setText(Integer.parseInt(cash.getText())*this.orm.getRow().getPrice()+"");



        });




    }

    private void initConfirm(){
        purchase.setOnMouseClicked(event -> {
            //Controller.instance.prepareToPurchase(Integer.parseInt(cash.getText()),Integer.parseInt(card.getText()));

            BigDecimal pr = new BigDecimal(toPurchase.getText());
            BigDecimal q = new BigDecimal(cash.getText());
            if (q.doubleValue()!=0.0){
                this.orm.setTotal_quantity(q);
                this.orm.setTotal_price(pr);

                if (Controller.map.containsKey(this.orm.getRow().getId())){
                    int temp = (int)Controller.map.get(this.orm.getRow().getId()).getPrice()*(int)Controller.map.get(this.orm.getRow().getId()).getQuantity();
                    Controller.instance.getUpdate();
                    System.out.println("exists"+temp);
                }

                Controller.map.put(orm.getRow().getId(),orm);
                Controller.list.clear();
                Controller.list.addAll(Controller.map.values());
                int r1 = (int)pr.doubleValue();
                int r2 = (int)q.doubleValue();
                Controller.instance.getUpdate();

            }



            Stage stage  = (Stage)((Button)event.getSource()).getScene().getWindow();
            stage.close();


        });






    }

    public OrderRowModel getOrm() {
        return orm;
    }

    public void setOrm(OrderRowModel orm) {
        this.orm = orm;
    }
}
