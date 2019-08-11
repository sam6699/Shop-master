package uz.utils;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

/**
 * Created by Jack on 10.03.2019.
 */
public abstract class FilterForTextField
{
    // for OnlyIntegers
    public  static void isIntegerValidation(TextField text)
    {
        text.textProperty().addListener((observable, oldValue, newValue) ->{
            if(!newValue.matches("\\d*")){
                text.setText(newValue.replaceAll("[^\\d]", ""));
            }
        } );

    }
    //this for double
    public  static  void isDoubleValidation(TextField first)
    {
        first.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,10}([\\.]\\d{0,2})?")) {
                    first.setText(oldValue);
                }
            }
        });

    }

}
