package uz.models;

import uz.controllers.Controller;
import uz.print.Print;
import uz.print.PrintModel;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PrintData {

    public PrintData() {
        print();
    }

    public void print(){
        Locale.setDefault(Locale.US);
        DecimalFormat format = new DecimalFormat("0.#####");
        DecimalFormat format1 = new DecimalFormat("0");
        List<PrintModel> list = new ArrayList<>();
        Controller.instance.table.getItems().forEach(orderRowModel -> {
            PrintModel printModel = new PrintModel();
            printModel.setId(list.size() + 1);
            printModel.setName(orderRowModel.getName());
            printModel.setSell(format1.format(orderRowModel.getOne_price().multiply(new BigDecimal(1.2))));
            BigDecimal totalForColumn = new BigDecimal(printModel.getSell());
            printModel.setQuantity(format.format(orderRowModel.getQuantity()));
            totalForColumn = totalForColumn.multiply(new BigDecimal(orderRowModel.getQuantity()));
            int fun = Integer.parseInt(totalForColumn.toString());
            printModel.setSumma(fun);
            printModel.setTotalPrice(Controller.instance.getSumma()+"");
            printModel.setDimension(orderRowModel.getMeasureType());
            printModel.setPrice(orderRowModel.getOne_price());
            printModel.setQqs((int)(Controller.instance.getSumma()*0.2)+"");
            list.add(printModel);
        });
        Print print = new Print(list);
        Controller.instance.prepareToPurchase(Controller.instance.getSumma(),0,null);



    }




}
