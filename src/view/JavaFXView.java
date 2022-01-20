package view;

import com.sun.tools.javac.Main;
import controller.Controller;
import controller.MainController;
import javafx.application.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import util.JsonHelper;

import java.io.IOException;


public class JavaFXView extends Application implements View{

    private static Controller socketController;
    private Stage primaryStage;
    private static FXMLLoader loader; //= new FXMLLoader(JavaFXView.class.getResource("resources/FXView.fxml"));

    public JavaFXView() {
        loader = new FXMLLoader(JavaFXView.class.getResource("resources/FXView.fxml"));
    }

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        primaryStage.setTitle("NC-lab-2");
        showBaseWindows();
    }

    public MainController getViewController(){
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
        MainController mainController = loader.getController();
        mainController.viewList(JsonHelper.viewModel(object));
    }

    @Override
    public void errorList(JSONObject object) {
        MainController mainController = loader.getController();
        mainController.errorList(JsonHelper.parseErrorJson(object));
    }

    public void showBaseWindows() throws IOException {
        loader = new FXMLLoader(JavaFXView.class.getResource("resources/FXView.fxml"));
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
        MainController mainController = loader.getController();
        mainController.setView(this);
        primaryStage.show();
    }

    public void showTrackWindow() throws IOException {
        FXMLLoader genreLoader = new FXMLLoader(JavaFXView.class.getResource("resources/TrackView.fxml"));
        Stage stage = new Stage();
        stage.setTitle("MusicTrack");
        Scene scene = new Scene(genreLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public void showGenreWindow() throws IOException {
        FXMLLoader genreLoader = new FXMLLoader(JavaFXView.class.getResource("resources/GenreView.fxml"));
        Stage stage = new Stage();
        stage.setTitle("MusicGenres");
        Scene scene = new Scene(genreLoader.load());
        stage.setScene(scene);
        stage.show();
    }
}
