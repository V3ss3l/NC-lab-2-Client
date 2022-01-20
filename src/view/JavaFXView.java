package view;

import controller.Controller;
import controller.FXController;
import javafx.application.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import util.JsonHelper;

import java.io.IOException;
import java.net.URL;


public class JavaFXView extends Application implements View{

    private static Controller socketController;
    private static FXMLLoader loader; //= new FXMLLoader(JavaFXView.class.getResource("resources/FXView.fxml"));

    public JavaFXView() {
        loader = new FXMLLoader(JavaFXView.class.getResource("resources/FXView.fxml"));
    }

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }

    public FXController getViewController(){
        return loader.getController();
    }

    @Override
    public void run() {
        javafx.application.Application.launch(JavaFXView.class);
    }

    @Override
    public void setController(Controller controller) {
        socketController = controller;
    }

    @Override
    public void stringList(JSONObject object) throws IOException, ParseException {
        FXController fxController = loader.getController();
        fxController.viewList(JsonHelper.viewModel(object));
    }

    @Override
    public void errorList(JSONObject object) {

    }

    /*public void main(String[] args){
        *//*socketController = new Controller(viewController);
        new Thread(socketController).start();*//*
        launch(args);
    }*/
}
