package uz.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import uz.print.Print;
import uz.print.PrintModel;

import java.math.BigDecimal;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Keyboard implements Initializable {

    @FXML
    private Button exitBtn;
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
    private Button one1;

    @FXML
    private Button two1;

    @FXML
    private Button three1;

    @FXML
    private Button four1;

    @FXML
    private Button five1;

    @FXML
    private Button six1;

    @FXML
    private Button seven1;

    @FXML
    private Button eight1;

    @FXML
    private Button nine1;

    @FXML
    private Button zero1;

    @FXML
    private Button zero2;

    @FXML
    private Button zero3;

    @FXML
    private Button zero4;

    @FXML
    private Button zero5;

    @FXML
    private Button back;

    @FXML
    private Button back1;

    @FXML
    private Button purchase;

    @FXML
    private TextField cash;

    @FXML
    private TextField card;

    @FXML
    private Label pays;

    @FXML
    private Label pays1;

    @FXML
    private Label pays2;

    @FXML
    private Label toPurchase;

    private String cashValue;

    private String cardValue;

    private int selectedInput=0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        toPurchase.setText(Controller.instance.getSumma()+"");
        initInputs();
        initDigits();
        initDigits1();
        initBack();
        initConfirm();
        initExit();


    }

    private void initInputs(){
        card.setOnMouseClicked(event -> {
            selectedInput=2;
            if (card.getText().equals("0"))
                card.setText("");

        });
        card.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue){
                selectedInput=2;
                if (card.getText().equals("0"))
                    card.setText("");
            }else{
                if (card.getText().equals(""))
                    card.setText("0");
            }


        });
        cash.setOnMouseClicked(event -> {
            selectedInput=1;
            if (cash.getText().equals("0"))
                cash.setText("");

        });
        cash.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue){
                selectedInput=1;
                if (cash.getText().equals("0"))
                    cash.setText("");
            }else{
                if (cash.getText().equals(""))
                    cash.setText("0");
            }


        });

    }
    private void calculate(int number){
        if (cash.getText().equals("0"))
            cash.setText("");


        if (selectedInput==1){
            cash.setText(cash.getText()+number);
        }else if (selectedInput==2){
            selectedInput=1;
            cash.setText(number+"");
        }else if (selectedInput==0){
            selectedInput=1;
            cash.setText(number+"");
        }

        pays.setText(cash.getText());

        int p = Integer.parseInt(pays.getText());
        int p2 = Integer.parseInt(toPurchase.getText());

        int p1=p2-p;
        pays1.setText(p1+"");
        card.setText(pays1.getText());
    }

    private void calculate2(int number){
        if (card.getText().equals("0"))
            card.setText("");

        if (selectedInput==2){
            card.setText(card.getText()+number);
        }else if (selectedInput==1){
            selectedInput=2;
            card.setText(number+"");
        }else if (selectedInput==0){
            selectedInput=2;
            card.setText(number+"");
        }
          pays1.setText(card.getText());

        int p1 = Integer.parseInt(pays1.getText());
        int p2 = Integer.parseInt(toPurchase.getText());

        int p=p2-p1;
        pays.setText(p+"");
        cash.setText(pays.getText());
    }

    private void calcZeroCash(int number){
        if(number==3){
            if (cash.getText().charAt(0)>'0'){
                cash.setText(cash.getText()+"000");
                pays.setText(cash.getText());

                int r = Integer.parseInt(cash.getText());
                int rr=Integer.parseInt(toPurchase.getText());

                card.setText(rr-r+"");
                pays1.setText(card.getText());
            }

        }

        if (number==2){
            if (cash.getText().charAt(0)>'0'){
                cash.setText(cash.getText()+"00");
                pays.setText(cash.getText());

                int r = Integer.parseInt(cash.getText());
                int rr = Integer.parseInt(toPurchase.getText());

                card.setText(rr-r+"");
                pays1.setText(card.getText());
            }
        }




    }

    private void calcZeroCard(int number){
        if(number==3){
            if (card.getText().charAt(0)>'0'){
                card.setText(card.getText()+"000");
                pays1.setText(card.getText());

                int r = Integer.parseInt(card.getText());
                int rr = Integer.parseInt(toPurchase.getText());

                cash.setText(rr-r+"");
                pays.setText(cash.getText());
            }
        }

        if (number==2){
            if (card.getText().charAt(0)>'0'){
                card.setText(card.getText()+"00");
                pays1.setText(card.getText());

                int r = Integer.parseInt(card.getText());
                int rr = Integer.parseInt(toPurchase.getText());

                cash.setText(rr-r+"");
                pays.setText(cash.getText());
            }

        }




    }


    private void initDigits(){
        one.setOnMouseClicked(event -> {
                calculate(1);



        });

        two.setOnMouseClicked(event -> {

                calculate(2);
            });

        three.setOnMouseClicked(event -> {

                calculate(3);
        });

        four.setOnMouseClicked(event -> {

            calculate(4);

        });

        five.setOnMouseClicked(event -> {


            calculate(5);
        });

        six.setOnMouseClicked(event -> {

            calculate(6);
        });

        seven.setOnMouseClicked(event -> {

            calculate(7);

        });

        eight.setOnMouseClicked(event -> {

            calculate(8);
        });

        nine.setOnMouseClicked(event -> {
            calculate(9);

        });

        zero.setOnMouseClicked(event -> {
            calculate(0);
        });
        zero2.setOnMouseClicked(event -> {


            calcZeroCash(2);
        });
        zero3.setOnMouseClicked(event -> {

            calcZeroCash(3);
        });
    }

    private void initDigits1(){
        one1.setOnMouseClicked(event -> {
            calculate2(1);


        });

        two1.setOnMouseClicked(event -> {

            calculate2(2);

        });

        three1.setOnMouseClicked(event -> {

            calculate2(3);


        });

        four1.setOnMouseClicked(event -> {

            calculate2(4);
        });

        five1.setOnMouseClicked(event -> {
            calculate2(5);
        });

        six1.setOnMouseClicked(event -> {

            calculate2(6);
        });

        seven1.setOnMouseClicked(event -> {

            calculate2(7);

        });

        eight1.setOnMouseClicked(event -> {

            calculate2(8);
        });

        nine1.setOnMouseClicked(event -> {

            calculate2(9);

        });

        zero1.setOnMouseClicked(event -> {

            calculate2(0);
        });

        zero4.setOnMouseClicked(event -> {
            calcZeroCard(2);
        });
        zero5.setOnMouseClicked(event -> {

            calcZeroCard(3);
        });
    }




    private void initBack(){
        String temp="./resources/img/back.png";
        back.setId("back-btn");
        back.setOnMouseClicked(event -> {
            selectedInput=1;
                if (cash.getText().length()==1){
                    if (cash.getText().charAt(0)!='0'){
                        card.setText(toPurchase.getText());
                        pays1.setText(toPurchase.getText());
                        cash.setText("0");

                    }

                }else {
                    cashValue = cash.getText(0,cash.getText().length()-1);
                    cash.setText(cashValue);

                    int rr = Integer.parseInt(cash.getText());
                    int r = Integer.parseInt(toPurchase.getText());

                    card.setText(r-rr+"");
                    pays1.setText(card.getText());

                    pays.setText(cash.getText());

                }

        });

        back1.setId("back-btn");
        back1.setOnMouseClicked(event -> {
            selectedInput=2;
            if (card.getText().length()==1){
                if (card.getText().charAt(0)!='0'){
                    cash.setText(toPurchase.getText());
                    pays.setText(toPurchase.getText());
                    card.setText("0");
                }

            }else {
                cardValue=card.getText(0,card.getText().length()-1);
                card.setText(cardValue);

                int rr = Integer.parseInt(card.getText());
                int r = Integer.parseInt(toPurchase.getText());

                cash.setText(r-rr+"");
                pays.setText(cash.getText());

                pays1.setText(card.getText());

            }

        });



    }

    private void initConfirm(){
        purchase.setOnMouseClicked(event -> {
            DecimalFormat format = new DecimalFormat("0.#####");
            List<PrintModel> list = new ArrayList<>();
            for(int i=0;i<Controller.list.size();i++){
                PrintModel printModel = new PrintModel();
                printModel.setId(i+1);
                printModel.setName(Controller.list.get(i).getName());
                printModel.setDimension(Controller.list.get(i).getMeasureType());
                printModel.setMeasureType(Controller.list.get(i).getMeasureType());
                printModel.setQuantity(format.format(Controller.list.get(i).getQuantity()));
                int fun = Integer.parseInt(Controller.list.get(i).getTotal_price().toString());
                printModel.setSumma(fun);
                printModel.setPrice(new BigDecimal(format.format(Controller.list.get(i).getTotal_quantity())));
                printModel.setJami(new BigDecimal(toPurchase.getText()));
                list.add(printModel);
            }


            Controller.instance.prepareToPurchase(Integer.parseInt(cash.getText()),Integer.parseInt(card.getText()),null);
            Print print = new Print(list);
            Stage stage = (Stage)((Button)(event).getSource()).getScene().getWindow();
            stage.close();
            Controller.instance.getUpdate();

        });
    }
    private void initExit(){
        exitBtn.setOnMouseClicked(event -> {
            Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            stage.close();



        });



    }






}
