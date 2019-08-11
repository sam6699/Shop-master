package uz.utils;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import uz.models.ProductDtoList;


import java.math.BigDecimal;

/**
 * Created by Jack on 17.02.2019.
 */
public class HelpfullUtils {
        public void filter(TextField textField){
            textField.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue,
                                    String newValue) {
                    if (!newValue.matches("\\d*")) {
                        textField.setText(newValue.replaceAll("[^\\d]", ""));
                    }
                }
            });
        }
        public static void setCenterText(TableColumn<ProductDtoList,String> table)
        {
            table.setCellFactory(new Callback<TableColumn<ProductDtoList, String>, TableCell<ProductDtoList, String>>() {
                @Override
                public TableCell<ProductDtoList, String> call(TableColumn<ProductDtoList, String> param) {
                    TableCell<ProductDtoList,String> cell = new TableCell<ProductDtoList,String>(){
                        @Override
                        protected void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);
                            if(item != null)
                            {
                                super.setText(item.toString());

                            }
                            else
                            {
                                super.setText("");

                            }

                        }
                    };

                    cell.setAlignment(Pos.CENTER);
                    return cell;
                }
            });
        }
    public static void setCenterGraphic(TableColumn<ProductDtoList,ImageView> table)
    {
        table.setCellFactory(new Callback<TableColumn<ProductDtoList, ImageView>, TableCell<ProductDtoList,ImageView>>() {
            @Override
            public TableCell<ProductDtoList,ImageView> call(TableColumn<ProductDtoList, ImageView> param) {
                TableCell<ProductDtoList,ImageView> cell = new TableCell<ProductDtoList,ImageView>(){
                    @Override
                    protected void updateItem(ImageView item, boolean empty) {
                        super.updateItem(item, empty);
                        if(item != null)
                        {
                            super.setGraphic(item);

                        }
                        else
                        {
                            super.setGraphic(null);

                        }

                    }
                };

                cell.setAlignment(Pos.CENTER);
                return cell;
            }
        });
    }
    public static void setCenterInteger(TableColumn<ProductDtoList,Integer> table)
    {
        table.setCellFactory(new Callback<TableColumn<ProductDtoList, Integer>, TableCell<ProductDtoList, Integer>>() {
            @Override
            public TableCell<ProductDtoList, Integer> call(TableColumn<ProductDtoList, Integer> param) {
                TableCell<ProductDtoList,Integer> cell = new TableCell<ProductDtoList,Integer>(){
                    @Override
                    protected void updateItem(Integer item, boolean empty) {
                        super.updateItem(item, empty);
                        if(item != null)
                        {
                            super.setText(item.toString());

                        }
                        else
                        {
                            super.setText("");

                        }

                    }
                };

                cell.setAlignment(Pos.CENTER);
                return cell;
            }
        });
    }

    public static void setCenterBigDecimal(TableColumn<ProductDtoList,BigDecimal> table)
    {
        table.setCellFactory(new Callback<TableColumn<ProductDtoList, BigDecimal>, TableCell<ProductDtoList, BigDecimal>>() {
            @Override
            public TableCell<ProductDtoList, BigDecimal> call(TableColumn<ProductDtoList, BigDecimal> param) {
                TableCell<ProductDtoList,BigDecimal> cell = new TableCell<ProductDtoList,BigDecimal>(){
                    @Override
                    protected void updateItem(BigDecimal item, boolean empty) {
                        super.updateItem(item, empty);
                        if(item != null)
                        {
                            super.setText(item.toString());

                        }
                        else
                        {
                            super.setText("");

                        }

                    }
                };

                cell.setAlignment(Pos.CENTER);
                return cell;
            }
        });
    }


        }

