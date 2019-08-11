package uz.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import uz.controllers.companyContent.contents.CompanyPurchaseModalController;
import uz.entity.*;
import uz.models.*;
import uz.service.ProductService;
import uz.service.ProductTypeService;
import uz.service.SalesService;
import uz.service.TransactionService;
import uz.utils.FxmlUrl;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class Controller implements Initializable {
    //object of this class
    public static Controller instance;


    @FXML
    private Button purchase;
    @FXML
    private Button cancel;

    public Client client;
    @FXML
    public TabPane types;

    @FXML
    private Button addProduct;

    @FXML
    private JFXButton cash;

    @FXML
    private Button card;

    @FXML
    private Button transfer;

    @FXML
    private JFXCheckBox get;
    @FXML
    private JFXCheckBox sell;
    @FXML
    private JFXButton getProduct;
    @FXML
    private JFXButton clients;

    @FXML
    private Button report;
    @FXML
    public TableView<OrderRowModel> table;
    @FXML
    private TableColumn<OrderRowModel, String> name;
    @FXML
    private TableColumn<OrderRowModel, BigDecimal> amount;
    @FXML
    private TableColumn<OrderRowModel, String> measure;
    @FXML
    private TableColumn<OrderRowModel, Void> price;
    @FXML
    private TableColumn<OrderRowModel, Integer> one_price;

    @FXML
    private TableColumn<OrderRowModel, Void> remove;


    ///******Services******///
    private final ProductService productService = new ProductService();
    private final TransactionService transactionService = new TransactionService();
    private final SalesService salesService = new SalesService();
    private final ProductTypeService productTypeService = new ProductTypeService();
    //******Order lists******//
    //******Map used for store unique products without duplicate****////
    public static final ObservableMap<Integer, OrderRowModel> map = FXCollections.observableHashMap();
    //*****Orders table list*****/////
    public static final ObservableList<OrderRowModel> list = FXCollections.observableArrayList(map.values());


    @FXML
    private Label check;
    public int summa = 0;//total order amount

    @FXML
    private Button switchBtn;

    private boolean state = true;

    @FXML
    private JFXButton expense;

    public Controller() {
    }

    public void initialize(URL location, ResourceBundle resources) {
        instance = this;
        table.setId("order-table");
        initSwitch();
        doCancel();
        initTabs();
        initTable();
        clickReport();
        showVerifyCheck();
        dofastPay();
        events();
        sell.setSelected(true);
        prepareToSellProduct();


    }

    private void events() {
        addProduct.setOnAction(event -> new Window("Товар кўшиш", FxmlUrl.products.product).show());
        clients.setOnAction(event -> new Window("Корхоналар", FxmlUrl.products.companyContent).show());
        transfer.setOnAction(event -> initPayForClient());
        get.setOnAction(event -> {
            sell.setSelected(false);
            get.setSelected(true);
            prepareToGetProduct();
        });
        sell.setOnAction(event -> {
            sell.setSelected(true);
            get.setSelected(false);
            prepareToSellProduct();
        });
        expense.setOnAction(event -> {
          Window window =  new Window("Кассадан олинадиган пуллар",FxmlUrl.products.expense);
          window.setFullScreen(false);
          window.show();
        });

        getProduct.setOnAction(event ->
        {
            for (OrderRowModel orm : list) {
                orm.getRow().addQuantity(orm.getQuantity());
            }
            //add into product_get_history table before updating it
            for (OrderRowModel o : list) {
                productService.addProductIntoShop(o.getRow().getId(), o.getTotal_quantity(), LocalDate.now());
            }

            for (OrderRowModel orm : list) {
                productService.updateProduct(orm.getRow());
            }
            list.clear();

        });
    }

    private void prepareToSellProduct() {
        purchase.setDisable(false);
        card.setDisable(false);
        cash.setDisable(false);
        transfer.setDisable(false);
        getProduct.setDisable(true);
    }

    private void prepareToGetProduct() {
        purchase.setDisable(true);
        card.setDisable(true);
        cash.setDisable(true);
        transfer.setDisable(true);
        getProduct.setDisable(false);
    }


    private void initTable() {


        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        amount.setCellValueFactory(new PropertyValueFactory<>("total_quantity"));
        measure.setCellValueFactory(new PropertyValueFactory<>("measureType"));
        price.setCellValueFactory(new PropertyValueFactory<>("total_price"));
        one_price.setCellValueFactory(new PropertyValueFactory<>("one_price"));
        remove.setCellValueFactory(new PropertyValueFactory<>("increment"));
        addButtonToTable();
        table.setItems(list);


    }

    //Activates report table
    private void clickReport() {
        report.setOnMouseClicked(event -> {
            try {
                AnchorPane layout = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("report.fxml")));
                Scene reportLayout = new Scene(layout);

                Stage stage = new Stage();
                stage.setTitle("Хисобот");
                stage.setScene(reportLayout);
                stage.setMaximized(true);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void getUpdate() {
        summa = 0;
        for (OrderRowModel orm : list) {
            summa += orm.getRow().getPrice()  * orm.getQuantity();
        }
        check.setText("Жами: " + (int)(summa * 1.2) + "сум");
    }

    private void addButtonToTable() {

        Callback<TableColumn<OrderRowModel, Void>, TableCell<OrderRowModel, Void>> cellFactory2 = new Callback<TableColumn<OrderRowModel, Void>, TableCell<OrderRowModel, Void>>() {
            @Override
            public TableCell<OrderRowModel, Void> call(final TableColumn<OrderRowModel, Void> param) {
                return new TableCell<OrderRowModel, Void>() {

                    private final Button remove = new Button();

                    {
                        remove.setId("table-btn");
                        remove.setPrefHeight(30);
                        remove.setPrefWidth(30);
                        remove.setOnAction((ActionEvent event) -> {
                            OrderRowModel data = getTableView().getItems().get(getIndex());
                            data.setTotal_quantity(new BigDecimal(1));
                            getTableView().getItems().get(getIndex()).setTotal_price(getTableView().getItems().get(getIndex()).getOne_price());

                            map.remove(data.getRow().getId(), data);
                            list.clear();
                            list.addAll(map.values());
                            getUpdate();

                        });
                    }

                    private final Button sub = new Button();

                    {
                        sub.setId("table-btn-sub");
                        sub.setPrefWidth(30);
                        sub.setPrefHeight(30);
                        sub.setOnMouseClicked(event -> {
                            OrderRowModel data = getTableView().getItems().get(getIndex());
                            if (data.decrement().compareTo(BigDecimal.ZERO) == 0) {
                                getTableView().getItems().get(getIndex()).setTotal_quantity(new BigDecimal(1));
                                getTableView().getItems().get(getIndex()).setTotal_price(getTableView().getItems().get(getIndex()).getOne_price());
                                map.remove(data.getRow().getId(), data);
                            } else {
                                // data.decrement();
                                map.put(data.getRow().getId(), data);

                            }

                            list.clear();
                            list.addAll(map.values());
                            getUpdate();

                        });
                    }

                    private final Button add = new Button();

                    {

                        add.setId("table-btn-add");
                        add.setPrefHeight(30);
                        add.setPrefWidth(30);
                        add.setOnMouseClicked(event -> {
                            OrderRowModel data = getTableView().getItems().get(getIndex());
                            data.increment();
                            map.put(data.getRow().getId(), data);
                            list.clear();
                            list.addAll(map.values());
                            getUpdate();


                        });


                    }

                    private final HBox row = new HBox();

                    {
                        row.setId("table-row");
                        row.getChildren().add(add);
                        row.getChildren().add(sub);
                        row.getChildren().add(remove);

                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(row);
                        }
                    }
                };
            }
        };


        remove.setCellFactory(cellFactory2);
    }


    public ArrayList<ContentButton> loadData(List<Product> ls) {
        ArrayList<ContentButton> contentButtons = new ArrayList<>();
        for (Product p : ls) {
            ContentButton cb = new ContentButton(p);

            contentButtons.add(cb);
        }

        return contentButtons;
    }

        public void prepareToPurchase(int cash, int card, ClientDto client) {
        if (client == null)
            System.out.println("client is null");
        else
            System.out.println("client exists");
        for (OrderRowModel orm : list) {
            System.out.println("before: " +orm.getRow().getQuantity());
            orm.getRow().subQuantity(orm.getQuantity());
            System.out.println("after: " +orm.getRow().getQuantity());


        }
        for (OrderRowModel orm : list) {
            productService.updateProduct(orm.getRow());
        }
        Calendar calendar = new GregorianCalendar();

        Transaction transaction = new Transaction();
        transaction.setDate(new Date(calendar.getTimeInMillis()));
        transaction.setCard(card);
        transaction.setCash(cash);
        transaction.setId(transactionService.addTransaction(transaction));
        for (OrderRowModel orm : list) {
            Sale sale = new Sale();
            sale.setTransactionId(transaction);
            sale.setProductId(orm.getRow());
            sale.setQuantity(orm.getQuantity());
            sale.setAmount((int) orm.getPrice());
            if (client != null)
                sale.setClientId(client);
            if (client != null)
                sale.setPayed(client.isPayed());
            salesService.addSale(sale);
        }
        list.clear();
        map.clear();
        check.setText("Жами:0");
        summa = 0;


    }

    private void showVerifyCheck() {
        purchase.setOnMouseClicked(event -> {
            Window window = new Window("Yakuniy", "keyboard.fxml");
            window.setFullScreen(false);
            window.show();

        });


    }

    public int getSumma() {
        return this.summa;
    }

    private void doCancel() {
        cancel.setOnMouseClicked(event -> {
            check.setText("Жами: 0 сум");
            list.clear();
            map.clear();

        });

    }

    private void addTab(List<ContentButton> products, String type) {
        Tab tab = new Tab();

        GridPane grid = new GridPane();
        grid.setId("grid");

        Text txt = new Text(type);
        tab.setGraphic(txt);

        double n = products.size() / 6.0;
        int cnt = 0;
        int m;
        if (n <= 1) {
            n = 1;
            m = products.size();
        } else {
            n = (int) (n + 1);
            m = 6;
        }


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (cnt == products.size())
                    break;

                grid.add(products.get(cnt), j, i, 1, 1);
                 cnt++;



            }
        }
        tab.setContent(grid);
        types.getTabs().add(tab);


    }


    public List<ProductType> getTypes() {
        ProductTypeService productTypeService = new ProductTypeService();
        return productTypeService.getAllProduct();
    }

    public void initTabs() {
        types.getTabs().clear();

        productTypeService.getAllProduct().forEach(pt -> {
            List<ContentButton> ls = productService.getAllProduct(pt.getId());
            addTab(ls, pt.getName());
        });

    }

    private void dofastPay() {
        cash.setOnMouseClicked(event -> new PrintData());

        card.setOnMouseClicked(event -> new PrintData());
    }

    public boolean getState() {

        return this.state;

    }

    public void setState(boolean state) {
        this.state = state;

    }

    private void initSwitch() {
        switchBtn.setId("switchOff");
        switchBtn.setOnMouseClicked(event -> {
            if (!state) {
                state = true;
                switchBtn.setStyle("-fx-background-color: red");
                switchBtn.setText("Ахоли");
            } else {
                state = false;
                System.out.println("false");
                switchBtn.setStyle("-fx-background-color: #0040ff");
                switchBtn.setText("Оптом");
            }


        });



    }

    private void initPayForClient() {
        Window window = new Window("Корхонага сотиш", FxmlUrl.products.chooseCompany);
        CompanyPurchaseModalController c = window.getController();
        c.prepareToSellToCompany();
        window.setFullScreen(false);
        window.show();
    }








}




