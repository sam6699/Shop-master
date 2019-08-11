package uz.controllers.companyContent;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import uz.Main;
import uz.controllers.companyContent.contents.AddCompanyModalController;
import uz.controllers.companyContent.events.AddCompanyEvent;
import uz.models.ClientDto;
import uz.models.Window;
import uz.service.ClientService;
import uz.utils.FxmlUrl;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Jack on 24.03.2019.
 */
public class CompanyContentController implements Initializable {
    @FXML
    private TableView<ClientDto> tableView;
    @FXML
    private TableColumn<ClientDto,Long> numC;
    @FXML
    private TableColumn<ClientDto,String> companyName;
    @FXML
    private TableColumn<ClientDto,String> directorName;
    @FXML
    private TableColumn<ClientDto,String> companyAddress;
    @FXML
    private TableColumn<ClientDto,String> phone;
    @FXML
    private JFXButton exit;
    @FXML
    private JFXButton historyCompany;
    @FXML
    private JFXButton editCompany;
    @FXML
    private JFXButton addCompany;
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        events();
        prepare();
        initHistory();

    }

    private void prepare() {
        editCompany.setDisable(true);
        //historyCompany.setDisable(true);
        numC.setCellValueFactory(new PropertyValueFactory<ClientDto, Long>("id"));
        companyName.setCellValueFactory(new PropertyValueFactory<ClientDto, String>("nameCompany"));
        directorName.setCellValueFactory(new PropertyValueFactory<ClientDto, String>("director"));
        companyAddress.setCellValueFactory(new PropertyValueFactory<ClientDto, String>("address"));
        phone.setCellValueFactory(new PropertyValueFactory<ClientDto, String>("phone"));
        tableView.setOnMouseClicked(event ->
        {
            if(!tableView.getSelectionModel().isEmpty()){
                //historyCompany.setDisable(false);
                editCompany.setDisable(false);
            }
        });
        tableView.setItems(FXCollections.observableArrayList(new ClientService().getClientsToTransfer()));
    }

    private void events() {
        exit.setOnAction(event ->
        {
            Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            stage.close();
        });
        addCompany.setOnAction(event -> {
            Window win = new Window("Корхона кўшиш", FxmlUrl.products.addCompany);
            win.setFullScreen(false);
            win.show();
        });
        editCompany.setOnAction(event ->
        {
            Window win = new Window("Корхона ўзгартириш",FxmlUrl.products.addCompany);
            win.setFullScreen(false);
            AddCompanyModalController add = win.getController();
            add.setToChange(tableView.getSelectionModel().getSelectedItem());
            win.show();
        });
        Main.eventBus.addEventHandler(AddCompanyEvent.ANY,event -> {
            tableView.setItems(FXCollections.observableArrayList(new ClientService().getClientsToTransfer()));
        });

    }

    private void initHistory(){
        historyCompany.setOnMouseClicked(event -> {
            Window window = new Window("Корхона тарихи",FxmlUrl.products.companyHistory);
            window.setFullScreen(false);
            window.show();
        });



    }

}
