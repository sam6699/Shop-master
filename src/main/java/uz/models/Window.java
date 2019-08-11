package uz.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

/**
 * Created by Jack on 09.03.2019.
 */
public class Window {
    private final String title;
    private final String fxmlUrl;
    private FXMLLoader loader;
    private final Stage stage;

    public Window(String title,String fxmlUrl) {
        this.title = title;
        this.fxmlUrl = fxmlUrl;
        stage = new Stage();
        this.init();
    }

    private void init() {

        try {
            loader = new FXMLLoader();
            AnchorPane parent = loader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(fxmlUrl)).openStream());
            stage.setScene(new Scene(parent));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setMaximized(true);
            stage.setResizable(false);
            stage.setTitle(this.title);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setModality(Modality modality) {
        this.stage.initModality(modality);
    }

    public void setStageStyle(StageStyle stageStyle) {
        this.stage.initStyle(stageStyle);
    }

    public void show(){
        stage.show();
    }

    public void setFullScreen(boolean yes){
        stage.setMaximized(yes);
    }

    public void showAndWait(){
        stage.showAndWait();
    }

    public <T> T getController(){
        return loader.getController();
    }

    public Stage getStage(){
        return this.stage;
    }
}
