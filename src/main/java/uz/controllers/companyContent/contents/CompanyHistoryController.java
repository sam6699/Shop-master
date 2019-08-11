package uz.controllers.companyContent.contents;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import uz.entity.Client;
import uz.entity.Sale;
import uz.models.ClientDto;
import uz.models.ReportRowModel;
import uz.service.ClientService;
import uz.service.SalesService;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class CompanyHistoryController implements Initializable {

    @FXML
    private JFXButton searchBtn;

    @FXML
    private JFXButton exitBtn;

    @FXML
    private JFXDatePicker dateOne;

    @FXML
    private JFXDatePicker dateTwo;

    @FXML
    private TableView<ReportRowModel> table;

    @FXML
    private TableColumn<ReportRowModel, Integer> dateCol;

    @FXML
    private TableColumn<ReportRowModel, String> productCol;

    @FXML
    private TableColumn<ReportRowModel, Double> quantityCol;

    @FXML
    private TableColumn<ReportRowModel,Integer> totalCol;

    @FXML
    private TableColumn<ReportRowModel, Integer> debtCol;

    @FXML
    private JFXComboBox<ClientDto> companyList;

    private ObservableList<ReportRowModel> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Locale locale = new Locale("ru","russia");
        Locale.setDefault(locale);

        table.setItems(list);
        initClientList();
        initSearch();
        initTable();
        exitBtn.setOnMouseClicked(event -> {
            Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            stage.close();
         });

    }

    private void initClientList(){
        companyList.getItems().addAll(new ClientService().getClientsToTransfer());
    }

    private void initSearch(){
        searchBtn.setOnMouseClicked(event -> {
            System.out.println("prepare");
             if(dateOne.getValue().getYear()==dateTwo.getValue().getYear()&&
                     dateOne.getValue().getMonthValue()==dateTwo.getValue().getMonthValue()&&
                     dateOne.getValue().getDayOfMonth()==dateTwo.getValue().getDayOfMonth()){
                 System.out.println(dateOne.getValue().toString()==dateTwo.getValue().toString());
                list.clear();
                for (Sale s : new SalesService().getOnDayHistory(companyList.getSelectionModel().getSelectedItem().getId(),dateOne.getValue())){
                    ReportRowModel rrm = new ReportRowModel(s);
                    list.add(rrm);

                }


            }else if (dateOne.getValue()!=null&&dateTwo.getValue()!=null){
                 System.out.println("is active");
                 list.clear();
                 for (Sale s : new SalesService().getHistoryByClient((companyList.getSelectionModel().getSelectedItem().getId()),dateOne.getValue(),dateTwo.getValue())){
//                    System.out.println(s.getProductId().getName());
                     ReportRowModel rrm = new ReportRowModel(s);
                     list.add(rrm);
                 }
             }
        });


    }

    private void initTable(){
        dateCol.setCellValueFactory(new PropertyValueFactory<>("soldDate"));
        productCol.setCellValueFactory(new PropertyValueFactory<>("productName"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        totalCol.setCellValueFactory(new PropertyValueFactory<>("amount"));

    }




}
