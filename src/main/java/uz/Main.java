package uz;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import uz.events.EventBus;
import uz.events.FXEventBus;
import uz.utils.FxmlUrl;

import java.util.Locale;

public class Main extends Application
{
    public static EventBus eventBus = new FXEventBus();


    @Override
    public void start(Stage primaryStage) throws Exception{
        Locale.setDefault(Locale.US);
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("main.fxml"));
        primaryStage.setTitle("Tillo domor");
        primaryStage.setScene(new Scene(root));
        primaryStage.setMaximized(true);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
