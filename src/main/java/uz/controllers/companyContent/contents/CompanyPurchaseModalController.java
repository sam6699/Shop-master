package uz.controllers.companyContent.contents;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import uz.controllers.Controller;
import uz.models.ClientDto;
import uz.print.Print;
import uz.print.PrintModel;
import uz.service.ClientService;

import java.math.BigDecimal;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Jack on 21.03.2019.
 */
public class CompanyPurchaseModalController implements Initializable
{
    @FXML
    private JFXComboBox<ClientDto> companyNames;

    @FXML
    private JFXButton sell;

    @FXML
    private JFXButton exit;


    @FXML
    private Label totalPrice;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        events();

        loanMethods();
        company();
        prepareToSellToCompany();
    }

    private void events() {
        sell.setOnAction(event -> {
            if(!companyNames.getSelectionModel().isEmpty())
            {
                ClientDto clientDto = companyNames.getSelectionModel().getSelectedItem();

                clientDto.setPayed(Controller.instance.summa);
                prepareToPrint(clientDto);
                Controller.instance.prepareToPurchase(Controller.instance.getSumma(),0,clientDto);
                Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                stage.close();
            }
        });
        exit.setOnAction(event ->
        {
            Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            stage.close();
        });

    }

    private void prepareToPrint(ClientDto clientDto) {
            Locale.setDefault(Locale.US);
            List<PrintModel> list  = new ArrayList<>();
        DecimalFormat format = new DecimalFormat("0.#####");
        DecimalFormat format1 = new DecimalFormat("0");
        Controller.list.forEach(orderRowModel ->
           {
               PrintModel printModel = new PrintModel();
               printModel.setId(list.size() + 1);
               printModel.setName(orderRowModel.getName());
               printModel.setSell(format1.format(orderRowModel.getOne_price().multiply(new BigDecimal(1.2))));
               BigDecimal totalForColumn = new BigDecimal(printModel.getSell());
               printModel.setQuantity(format.format(orderRowModel.getQuantity()));
               totalForColumn = totalForColumn.multiply(new BigDecimal(orderRowModel.getQuantity()));
               int fun = Integer.parseInt(totalForColumn.toString());
               printModel.setSumma(fun);
               printModel.setDimension(orderRowModel.getMeasureType());
               printModel.setPrice(orderRowModel.getOne_price());
               printModel.setCompanyName(clientDto.getNameCompany());
               printModel.setDirectorName(clientDto.getDirector());
               printModel.setPhoneNumber(clientDto.getPhone());
               printModel.setTotalPrice(Controller.instance.getSumma()+"");
               printModel.setQqs(((int)(Controller.instance.getSumma() * 0.2))+"");
                list.add(printModel);
           });
            new Print().printTransferCompany(list);
    }

    private void paidMethods() {
        totalPrice.setStyle("-fx-text-fill: black");
    }

    private void loanMethods() {
        totalPrice.setStyle("-fx-text-fill: red");
    }

    public void prepareToSellToCompany(){
        String temp = String.valueOf(Controller.instance.getSumma());
        String nn = "";
        int cnt = 0;
        for(int i = temp.length() - 1 ; i >= 0 ; i --){
            if(cnt == 3){
                nn += ",";
                cnt = 0;
                nn += temp.charAt(i);
            }
            else
                nn += temp.charAt(i);
            cnt ++;
        }
        StringBuilder stringBuilder = new StringBuilder(nn);
        stringBuilder.reverse();
        totalPrice.setText(stringBuilder.toString());
        System.out.println(totalPrice.getText());

    }

    private void company() {
        companyNames.getItems().addAll(new ClientService().getClientsToTransfer());
    }
}
