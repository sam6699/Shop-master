package uz.models;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import uz.controllers.Controller;
import uz.controllers.KeyboardSimple;
import uz.entity.Product;


public class ContentButton extends Pane {
    private final Product dm;
private final OrderRowModel orm;
public ContentButton(Product dm) {
        super();
        this.dm = dm;
        this.setPrefSize(130,130);
        this.setId("content-btn");
        this.orm = new OrderRowModel(this.dm);
        init(dm.getName(),dm.getPrice()+"");

    }

    private void init(String s1,String s2){
        this.setId("content-btn");
        String temp = "file:///";
        temp+=this.dm.getPath();
        Image image = new Image(temp,120,125,false,false);
        ImageView imageView = new ImageView(image);
        this.getChildren().add(imageView);

        createLabel(s1,s2);

        click();
    }
    private void createLabel(String s1,String s2){
        Text text1 = new Text(s1);
        text1.setId("text");
        text1.setTranslateY(15);



        Text text2 = new Text(s2);
        text2.setId("text");
        text2.setTranslateY(118);
        text2.setLayoutX(25);

        this.getChildren().add(text1);
        this.getChildren().add(text2);

    }
    private void click(){
        this.setOnMouseClicked(event -> {
            if (!Controller.instance.getState()||orm.getRow().getMeasureValue()<1.0){
                Window window = new Window("","keyboard_simple.fxml");
                KeyboardSimple keyboardSimple = window.getController();
                keyboardSimple.setOrm(this.orm);
                window.setFullScreen(false);
                window.show();

            }else {
                if (Controller.map.containsKey(orm.getRow().getId())){
                    orm.increment();
                }
                Controller.map.put(orm.getRow().getId(),orm);
                Controller.list.clear();
                Controller.list.addAll(Controller.map.values());
            }

                Controller.instance.getUpdate();





        });


    }
}
