package uz.models;

import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import uz.entity.Product;

import java.util.List;

public class MyTabPane extends TabPane {

    public MyTabPane() {
    }

    public void addTab(List<ContentButton> products,String type){
        Tab tab = new Tab();
        GridPane grid = new GridPane();
        tab.setText(type);
        double n = products.size()/6.0;
        int cnt=0;
        int m;
        if (n<=1){
            n=1;
            m=products.size();
        }else {
            n = (int)(n+1);
            m=9;
        }

        for (int i=0;i<n;i++){
            for (int j=0;j<m;j++){
                if (cnt==products.size())
                    break;
                grid.add(products.get(cnt),j,i);
                cnt++;
            }
        }
        for (int i=0;i<6;i++){
            grid.add(new Label(""),i,31);
        }
        tab.setContent(grid);
        this.getTabs().add(tab);


    }



}
