package view;

import controller.Controller;
import javafx.application.*;
import javafx.fxml.FXMLLoader;
import javafx.stage.*;


public class JavaFXView extends Application{

    private Controller controller;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setController(controller);
        stage.show();
    }

}
