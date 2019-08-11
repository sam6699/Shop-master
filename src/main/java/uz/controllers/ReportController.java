package uz.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import uz.dao.ProductDAO;
import uz.entity.Sale;
import uz.models.ReportRowModel;
import uz.models.Window;
import uz.print.Print;
import uz.print.PrintModel;
import uz.service.ProductService;
import uz.service.SalesService;
import uz.utils.FxmlUrl;

import java.math.BigDecimal;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class ReportController implements Initializable {
    @FXML
    private TableView<ReportRowModel> tableReport;

    @FXML
    private TableColumn<ReportRowModel,Integer> columnData;

    @FXML
    private TableColumn<ReportRowModel,String> columnProductN;

    @FXML
    private TableColumn<ReportRowModel,Integer> columnCount;

    @FXML
    private TableColumn<ReportRowModel,Integer> columnProductPrice;
    @FXML
    private TableColumn<ReportRowModel,Integer> columnTotal;

    public   int change = 0;

    @FXML
    private  DatePicker startDate;

    @FXML
    private  DatePicker endDate;

    @FXML
    private Label totalValue;

    private int summa=0;

    @FXML private JFXButton exit;

    @FXML
    private JFXButton printBtn;

    @FXML
    private Button search;

    @FXML
    private Label dayExpense;

    @FXML
    private Label moneyInShop;

    private int expense = 0;

    @FXML
    private JFXButton warehouseProduct;

    private final SalesService salesService = new SalesService();
    private static final ObservableList<ReportRowModel> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Locale locale = new Locale("ru","russia");
        Locale.setDefault(locale);
//        startDate.setValue(LocalDate.now());
//        endDate.setValue(LocalDate.now());
        initTable();
        search();
      //  modifyIt();



    }


    private void search(){

        printBtn.setOnAction(event ->
        {
            if((startDate.getValue() != null && endDate.getValue() != null) || !tableReport.getItems().isEmpty())
            {
                if(change == 1)
                {
                    printReportAllProducts();
                    System.out.println("PrintAllProducts");
                }
                else if(change == 2)
                {
                    printReportByDates();
                    System.out.println("PrintByDates");
                }
                else if (change == 3)
                {
                    printRecievedProducts();
                    System.out.println("PrintRecieved products");
                }
                else
                {
                    printReportAllProducts();
                    System.out.println("ostatka tovar");
                }
            }
            else
            {   summa = 0;
                list.clear();
                Tooltip tooltip = new Tooltip();
                tooltip.setText("Бошаланиш куни ва тугаланган кунини тўлдиринг");
                tooltip.setAutoHide(true);
                Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                tooltip.show(stage);
            }


        });




        search.setOnMouseClicked(event -> {

            if(startDate.getValue() != null && endDate.getValue() != null)
            {   Window window = new Window("Тури", FxmlUrl.products.chooseTypeReport);
                window.setFullScreen(false);
                TypeReportController typeReportController = window.getController();
                typeReportController.setParams(this);
                window.show();
            }
            else
            {   summa = 0;
                list.clear();
                Tooltip tooltip = new Tooltip();
                tooltip.setText("Бошаланиш куни ва тугаланган кунини тўлдиринг");
                tooltip.setAutoHide(true);
                Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                tooltip.show(stage);
            }
        });

        exit.setOnAction(event -> {
//            Controller.instance.initTabs();
            if (list!=null)
                list.clear();
            Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            stage.close();
        });
        warehouseProduct.setOnAction(event ->
        {
            list.clear();
            list.addAll(new ProductService().getAllProductsFromWareHouse());
            summa = 0;
            for (ReportRowModel reportRowModel : list){
                summa += Double.parseDouble(reportRowModel.getAmount());
            }
            totalValue.setText(summa+" сум");
        });
        }

    private void printRecievedProducts() {
        Locale.setDefault(Locale.US);
        List<PrintModel> list = new ArrayList<>();
        tableReport.getItems().forEach(reportRowModel -> {
            DecimalFormat decimalFormat = new DecimalFormat("0.###");
            PrintModel printModel = new PrintModel(list.size() + 1,reportRowModel.getProductName(),decimalFormat.format(Double.parseDouble(reportRowModel.getQuantity())),reportRowModel.getSoldDate());
            list.add(printModel);
        });
        new Print().printEnterProducts(list);
    }


    private void initTable(){

        columnData.setCellValueFactory(new PropertyValueFactory<>("soldDate"));
        columnData.getStyleClass().add(".table-column");
        columnProductN.setCellValueFactory(new PropertyValueFactory<>("productName"));
        columnCount.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        columnProductPrice.setCellValueFactory(new PropertyValueFactory<>("productPrice"));
        columnTotal.setCellValueFactory(new PropertyValueFactory<>("amount"));
        tableReport.setItems(list);
    }

    private ArrayList<ReportRowModel> findByDates(LocalDate start, LocalDate end) {


        ArrayList<ReportRowModel> list = new ArrayList<>();
        for (Sale s :  salesService.getSalesByDates(start)){
            ReportRowModel rrm = new ReportRowModel(s);
            list.add(rrm);
            summa += s.getAmount() * 1.2;
        }
            totalValue.setText(summa+" сум");
            return list;

    }
    private ArrayList<ReportRowModel> findByGroupProducts(LocalDate start, LocalDate end){
        ArrayList<ReportRowModel> list = new ArrayList<>();
        for(Sale s : salesService.getSalesByDateWithGroupProducts(start,end)){
            ReportRowModel reportRowModel = new ReportRowModel();
            reportRowModel.setAmount(s.getAmount()+"");
            reportRowModel.setProductName(s.getProductId().getName());
            reportRowModel.setQuantity(s.getQuantity()+"");
            reportRowModel.setProductPrice(String.valueOf(s.getProductId().getPrice()));
            summa += s.getAmount() * 1.2;
            list.add(reportRowModel);
        }
        totalValue.setText(summa + " сум");
        return  list;
    }
    public void drawTableWithGroupProduct(){
            summa = 0;
            list.clear();
            LocalDate date1 = startDate.getValue();
            LocalDate date2 = endDate.getValue();
            if(change == 1)
            {

                if(date1.equals(date2)){
                    list.addAll(findByGroupProducts(date1,date2));
                    expense = new ProductDAO().expenseAllSuma(startDate.getValue().toString());
                    dayExpense.setText(expense +"  cумма");
                    moneyInShop.setText(summa - expense+" cумма");
                }
                else {
                    list.addAll(findByGroupProducts(date1,date2));
                    expense = 0;
                    dayExpense.setText(expense + " cумма");
                    moneyInShop.setText(summa - expense+" cумма");
                }
                tableReport.setItems(list);
            }
            else if (change == 2)
            {
                expense = 0;
                for(LocalDate i = date1; i.isBefore(date2.plusDays(1)); i = i.plusDays(1)){
                    list.addAll(findByDates(i,date2));
                    //expense += new ProductDAO().expenseAllSuma(i.toString());
                }
                tableReport.setItems(list);
                dayExpense.setText(expense +" cумма");
                moneyInShop.setText(summa - expense+" cумма");
            }
            else if (change == 3){
                summa=0;
                expense = 0;
                getAllRecievedProducts();
                dayExpense.setText(expense +" cумма");
                moneyInShop.setText(summa - expense+" cумма");
                totalValue.setText(""+summa);
            }
    }
    private void getAllRecievedProducts(){
        list.clear();
        if(startDate.getValue() != null && endDate.getValue() != null){
           list.addAll(new ProductService().getByDateEnterProducts(startDate.getValue(),endDate.getValue()));
        }
        tableReport.setItems(list);
    }
    //here is printed from date to date Report
    private void printReportByDates()
    {
        LocalDate start = startDate.getValue();
        LocalDate end = endDate.getValue();
        for (LocalDate i = start; i.isBefore(end.plusDays(1)); i = i.plusDays(1)) {
            List<PrintModel> list = new ArrayList<>();
            LocalDate finalI = i;
            tableReport.getItems().forEach(reportRowModel -> {
                if (finalI.toString().equals(reportRowModel.getSoldDate().toString())) {
                    DecimalFormat decimalFormat = new DecimalFormat("0.###");
                    PrintModel printModel = new PrintModel();
                    printModel.setId(list.size() + 1);
                    printModel.setName(reportRowModel.getProductName());
                    printModel.setPrice(new BigDecimal(reportRowModel.getProductPrice()));
                    printModel.setQuantity(decimalFormat.format(Double.parseDouble(reportRowModel.getQuantity())));
                    printModel.setSumma(Integer.parseInt(reportRowModel.getAmount()));
                    printModel.setJami(new BigDecimal(summa));
                    printModel.setSoldDate(reportRowModel.getSoldDate());
                    printModel.setTip1(true);
                    printModel.setGetSumm("0");
                    list.add(printModel);
                }
            });
            Print print = new Print();
            if (print.printedReport(list))
                continue;
        }


    }
    private void printReportAllProducts(){

        List<PrintModel> list = new ArrayList<>();
        tableReport.getItems().forEach(reportRowModel -> {
            DecimalFormat decimalFormat = new DecimalFormat("0.###");
            PrintModel printModel = new PrintModel();
            printModel.setId(list.size() + 1);
            printModel.setName(reportRowModel.getProductName());
            printModel.setPrice(new BigDecimal(reportRowModel.getProductPrice()));
            printModel.setQuantity(decimalFormat.format(Double.parseDouble(reportRowModel.getQuantity())));
            printModel.setSumma(Integer.parseInt(reportRowModel.getAmount()));
            printModel.setJami(new BigDecimal(summa));
            printModel.setGetSumm(expense+"");
            printModel.setSoldDate("");
            printModel.setTip1(true);
            list.add(printModel);
        });
        Print print = new Print();
        print.printedReport(list);
    }










}
