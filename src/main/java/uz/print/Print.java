package uz.print;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.util.HashMap;
import java.util.List;

public class Print {
    public Print(List<PrintModel> list)
    {
        System.out.println("Start...");
        String printName = null;
        String path = "";
        path = "c:\\Program Files\\easySqlad\\TilloDomor.jasper";
        JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(list);
        HashMap hashMap = new HashMap();
        try {
            printName = JasperFillManager.fillReportToFile(path,hashMap,jrBeanCollectionDataSource);
            if(printName != null){
                JasperPrintManager.printReport(printName,false);
            }
        } catch (JRException e) {
            e.printStackTrace();
        }

    }

    public Print() {

    }

    public boolean printedReport(List<PrintModel> list)
    {
        if(!list.isEmpty())
        {
        System.out.println("Start...");
        String printName = null;
        String path = "";
        path = "c:\\Program Files\\easySqlad\\TilloDomorReport.jasper";
        JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(list);
        HashMap hashMap = new HashMap();
        try {
            printName = JasperFillManager.fillReportToFile(path,hashMap,jrBeanCollectionDataSource);
            if(printName != null){
                JasperPrintManager.printReport(printName,false);
            }
        } catch (JRException e) {
            e.printStackTrace();
        }
        return true;
        }
        return false;
    }
    public void printTransferCompany(List<PrintModel> list)
    {
        System.out.println("Start...");
        String printName = null;
        String path = "";
        path = "c:\\Program Files\\easySqlad\\TilloDomorReportTransfer.jasper";
        JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(list);
        HashMap hashMap = new HashMap();
        
        try {
            printName = JasperFillManager.fillReportToFile(path,hashMap,jrBeanCollectionDataSource);
            if(printName != null){
                JasperPrintManager.printReport(printName,false);
                JasperPrintManager.printReport(printName,false);
            }
        } catch (JRException e) {
            e.printStackTrace();
        }

    }


    public void printEnterProducts(List<PrintModel> list) {
        System.out.println("Start...");

        String printName = null;
        String path = "";
        path = "c:\\Program Files\\easySqlad\\TilloDomorGetProducts.jasper";
        JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(list);
        HashMap hashMap = new HashMap();
        try {
            printName = JasperFillManager.fillReportToFile(path,hashMap,jrBeanCollectionDataSource);
            if(printName != null){
                JasperPrintManager.printReport(printName,false);
            }
        } catch (JRException e) {
            e.printStackTrace();
        }
    }
}
