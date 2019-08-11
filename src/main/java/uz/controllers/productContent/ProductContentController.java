package uz.controllers.productContent;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import uz.Main;
import uz.controllers.AddProductController;
import uz.controllers.Controller;
import uz.controllers.productContent.contents.ConfirmDeletController;
import uz.controllers.productContent.controllerEvents.AddProductEvent;
import uz.entity.Product;
import uz.entity.ProductType;
import uz.models.ProductDtoList;
import uz.models.Window;
import uz.service.ProductService;
import uz.utils.FxmlUrl;
import uz.utils.HelpfullUtils;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Jack on 10.03.2019.
 */
public class ProductContentController implements Initializable
{
    @FXML
    private AnchorPane anchor;

    private final VBox vBox = new VBox();

    @FXML
    private JFXButton addProduct;

    @FXML
    private JFXButton deletProduct;

    @FXML
    private JFXButton editProduct;

    @FXML
    private JFXButton exit;

    @FXML
    private TableView<ProductDtoList> tableView;

    @FXML
    private TableColumn<ProductDtoList,Long> numC;

    @FXML
    private TableColumn<ProductDtoList,ImageView> productImageC;

    @FXML
    private TableColumn<ProductDtoList,String> productNameC;

    @FXML
    private TableColumn<ProductDtoList,String> productDimenstionC;

    @FXML
    private TableColumn<ProductDtoList,Long> productPriceC;

    private List<ProductDtoList> productDtoListList;

    @FXML
    private JFXButton deleteType;


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {



        productDtoListList = new ProductService().getAllProductss();

        prepare();
        events();
        tableView.getItems().addAll(productDtoListList);
        deletProduct.setDisable(true);
        editProduct.setDisable(true);



    }



    private void events() {
        addProduct.setOnAction(event -> {
            //open new Add window for new product
        Window wPopup =  new Window("Янги махсулот кўшиш",FxmlUrl.products.addProduct);
        wPopup.setFullScreen(false);
        wPopup.getStage().setOnCloseRequest(event1 -> {
            AddProductEvent addProductEvent = new AddProductEvent(AddProductEvent.ANY);
            Main.eventBus.fireEvent(addProductEvent);
        });
        wPopup.show();
        });

        //After adding some products
        Main.eventBus.addEventHandler(AddProductEvent.ANY, event ->
        {
            tableView.getItems().clear();
            productDtoListList.clear();
            productDtoListList = new ProductService().getAllProductss();
            tableView.getItems().addAll(productDtoListList);
        });

        //o`chirish
        deletProduct.setOnAction(event ->
        {
            if(!tableView.getItems().isEmpty()){
                Window window = new Window("Товар Ўчириш",FxmlUrl.products.deletProduct);
                window.setFullScreen(false);
                window.getStage().setOnCloseRequest(event1 ->
                {
                    AddProductEvent addProductEvent = new AddProductEvent(AddProductEvent.ANY);
                    Main.eventBus.fireEvent(addProductEvent);
                });
                ConfirmDeletController confirmDeletController = window.getController();
                confirmDeletController.setForDelet(tableView.getSelectionModel().getSelectedItem().getProductName(),tableView.getSelectionModel().getSelectedItem().getProductId());
                window.show();
            }
        });
        //o`zgartirish
        editProduct.setOnAction(event -> {
            if(!tableView.getSelectionModel().isEmpty()){
                ProductDtoList productDtoList = tableView.getSelectionModel().getSelectedItem();
                productDtoList.setChange(true);
                Window wPopup = new Window("Махсулотни Ўзгартириш",FxmlUrl.products.addProduct);
                wPopup.setFullScreen(false);
                AddProductController addProductModal = wPopup.getController();
                addProductModal.setEditProduct(productDtoList);
                wPopup.show();
            }
        });

        exit.setOnAction(event ->
        {
            //TODO make here pro

            Controller.instance.initTabs();
            Stage stage = (Stage)(anchor).getScene().getWindow();
            stage.close();


            System.out.println("closed");
        });
        tableView.setOnMouseClicked(event -> {
            deletProduct.setDisable(false);
            editProduct.setDisable(false);
        });



        //DeleteType
        deleteType.setOnAction(event -> {
            Window window = new Window("Категория Ўчириш",FxmlUrl.products.deletType);
            window.setFullScreen(false);
            window.getStage().setOnCloseRequest(event1 ->
            {
                AddProductEvent addProductEvent = new AddProductEvent(AddProductEvent.ANY);
                Main.eventBus.fireEvent(addProductEvent);
            });
            window.show();
        });

    }

    private void prepare() {
        numC.setCellValueFactory(new PropertyValueFactory<ProductDtoList, Long>("id"));
        productNameC.setCellValueFactory(new PropertyValueFactory<ProductDtoList, String>("productName"));
        productDimenstionC.setCellValueFactory(new PropertyValueFactory<ProductDtoList, String>("dimension"));
        productPriceC.setCellValueFactory(new PropertyValueFactory<ProductDtoList, Long>("sellPrice"));
        productImageC.setCellValueFactory(new PropertyValueFactory<ProductDtoList, ImageView>("image"));
        HelpfullUtils.setCenterText(productNameC);
        HelpfullUtils.setCenterText(productDimenstionC);
        HelpfullUtils.setCenterGraphic(productImageC);



    }
}
