package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import org.json.simple.JSONObject;
import util.JsonHelper;
import view.JavaFXView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static util.Values.GENRE;

public class MainController implements Initializable {

    private JavaFXView view;
    private Controller controller;
    private ObservableList<String> obsList = FXCollections.observableArrayList();

    public MainController() {
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setView(JavaFXView view) {
        this.view = view;
    }

    public void viewList(ArrayList<String> arr) {
        obsList.removeAll();
        listView.getItems().clear();
        for(int i = 0; i < arr.size(); i++){
            obsList.add(arr.get(i));
        }
        listView.setItems(obsList);
    }

    public void errorList(String str){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(str);
        alert.setTitle("Error");
        alert.setHeaderText("Error! Please read context");
        alert.showAndWait();
    }

    @FXML
    private Button trackButton;

    @FXML
    private Button genreButton;

    @FXML
    private ListView<String> listView;

    @FXML
    private Button clearButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        clearButton.setOnAction(actionEvent -> {
            obsList.removeAll();
            listView.getItems().clear();
        });

        trackButton.setOnAction(actionEvent -> {
            try {
                view.showTrackWindow();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        genreButton.setOnAction(actionEvent -> {
            try {
                view.showGenreWindow();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
