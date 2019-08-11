package uz.controllers.companyContent.contents;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import uz.Main;
import uz.controllers.companyContent.events.AddCompanyEvent;
import uz.entity.Client;
import uz.models.ClientDto;
import uz.service.ClientService;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Jack on 21.03.2019.
 */
public class AddCompanyModalController implements Initializable
{
    @FXML
    private JFXButton exit;
    @FXML
    private JFXButton accept;
    @FXML
    private JFXTextField companyName;
    @FXML
    private JFXTextField directorName;
    @FXML
    private JFXTextField phone;
    @FXML
    private JFXTextField address;
    @FXML
    private Label fillName;
    @FXML
    private Label fillDirector;
    @FXML
    private Label fillPhone;
    @FXML
    private Label fillAdress;
    private Client client;
    @FXML
    private Label info;
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        events();
        prepareThem();



    }

    private void events() {
        exit.setOnAction(event -> {
            Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            stage.close();
        });
        accept.setOnAction(event ->
        {
                if(checkAllFields())
                {
                    if(this.client == null)
                    {
                        this.client = new Client(companyName.getText(),directorName.getText(),phone.getText(),address.getText());
                        if(new ClientService().addClient(client) != -1){
                            AddCompanyEvent addCompanyEvent = new AddCompanyEvent(AddCompanyEvent.ANY);
                            Main.eventBus.fireEvent(addCompanyEvent);
                            info.setText("Малумот кўшилди");
                            info.setVisible(true);
                            clearAllFields();
                        }
                        else
                        {
                            Tooltip tooltip = new Tooltip();
                            tooltip.setText("Кўшишда хатолик рўй берди");
                            tooltip.setStyle("-fx-text-fill:red");
                            tooltip.setAutoHide(true);
                            Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                            tooltip.show(stage);
                        }
                    }
                    else
                    {
                        System.out.println("update");
                        this.client.setName(companyName.getText());
                        this.client.setAddress(address.getText());
                        this.client.setDirector(directorName.getText());
                        this.client.setPhone(phone.getText());
                        if(new ClientService().updateClient(client) != -1){
                            AddCompanyEvent addCompanyEvent = new AddCompanyEvent(AddCompanyEvent.ANY);
                            Main.eventBus.fireEvent(addCompanyEvent);
                            info.setText("Малумот янгиланди");
                            info.setVisible(true);
                            clearAllFields();

                        }
                        else
                        {
                            Tooltip tooltip = new Tooltip();
                            tooltip.setText("Кўшишда хатолик рўй берди");
                            tooltip.setStyle("-fx-text-fill:red");
                            tooltip.setAutoHide(true);
                            Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                            tooltip.show(stage);
                        }
                    }
                }
        });

        address.setOnMouseClicked(event -> {
                info.setVisible(false);
        });
        phone.setOnMouseClicked(event -> {
            info.setVisible(false);
        });
       companyName.setOnMouseClicked(event -> {
           info.setVisible(false);
       });
       directorName.setOnMouseClicked(event -> {
           info.setVisible(false);
       });

    }
    private boolean checkAllFields()
    {       fillName.setVisible(companyName.getText().isEmpty());
            fillPhone.setVisible(phone.getText().isEmpty());
            fillDirector.setVisible(directorName.getText().isEmpty());
            fillAdress.setVisible(address.getText().isEmpty());
            if(fillName.isVisible() || fillPhone.isVisible() || fillDirector.isVisible() || fillAdress.isVisible())
                return false;
            return  true;
    }
    private void prepareThem() {
        fillName.setVisible(false);
        fillAdress.setVisible(false);
        fillDirector.setVisible(false);
        fillPhone.setVisible(false);
        info.setVisible(false);
    }
    public void setToChange(ClientDto selectedItem){
        this.client = new Client(selectedItem.getNameCompany(),selectedItem.getDirector(),selectedItem.getPhone(),selectedItem.getAddress());
        this.client.setId(Math.toIntExact(selectedItem.getClientId()));
        companyName.setText(this.client.getName());
        phone.setText(this.client.getPhone());
        address.setText(this.client.getAddress());
        directorName.setText(this.client.getDirector());
    }
    private  void clearAllFields(){
        companyName.setText("");
        phone.setText("");
        address.setText("");
        directorName.setText("");
    }
}
