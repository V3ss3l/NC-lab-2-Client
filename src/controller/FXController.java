package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.json.simple.JSONObject;
import util.JsonHelper;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FXController implements Initializable {

    private Controller controller;
    private ObservableList<String> obsList = FXCollections.observableArrayList();

    public FXController(){
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void viewList(ArrayList<String> arr) {
        inputText.getText();
        textField.clear();
        listView.getItems().removeAll();
        obsList.removeAll();
        obsList.addAll(arr);
        listView.getItems().addAll(obsList);
    }

    @FXML
    private Button trackButton;

    @FXML
    private Button genreButton;

    @FXML
    private Button okButton;

    @FXML
    private Button cancelButton;

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private ListView<String> listView;

    @FXML
    private TextField textField;

    @FXML
    private Label inputText;

    @FXML
    private void add(Boolean flag) {
        okButton.setOnAction(actionEvent -> {
            inputText.setText("Input :" + textField.getText());
            String buff = textField.getText();
            String[] str = buff.split(" ");
            JSONObject obj;
            if (flag == true) obj = JsonHelper.addTrackArrayJson(str);
            else obj = JsonHelper.addGenreArrayJson(str);
            Controller.operationForEntity(obj);
        });
        cancelButton.setOnAction(actionEvent1 -> {
            inputText.setText("Operation is canceled");
        });
    }

    @FXML
    private void delete(Boolean flag) {
        okButton.setOnAction(actionEvent -> {
            inputText.setText("Input :" + textField.getText());
            String buff = textField.getText();
            String[] str = buff.split(" ");
            JSONObject obj;
            if (flag == true) obj = JsonHelper.deleteTrackJson(str);
            else obj = JsonHelper.deleteGenreJson(str);
            Controller.operationForEntity(obj);
        });
        cancelButton.setOnAction(actionEvent1 -> {
            inputText.setText("Operation is canceled");
        });
    }

    @FXML
    private void watch(Boolean flag) {
        okButton.setOnAction(actionEvent -> {
            if(flag == true) Controller.operationForEntity(JsonHelper.watchTrack());
            else Controller.operationForEntity(JsonHelper.watchGenre());
        });
        cancelButton.setOnAction(actionEvent -> {
            inputText.setText("Operation is canceled");
        });
    }

    @FXML
    private void search(Boolean flag) {
        okButton.setOnAction(actionEvent -> {
            inputText.setText("Input :" + textField.getText());
            String buff = textField.getText();
            String[] str = buff.split(" ");
            JSONObject obj;
            if (flag == true) obj = JsonHelper.searchTrackJson(str);
            else obj = JsonHelper.searchGenreJson(str);
            Controller.operationForEntity(obj);
        });
        cancelButton.setOnAction(actionEvent1 -> {
            inputText.setText("Operation is canceled");
        });
    }

    @FXML
    private void set(Boolean flag) {
        okButton.setOnAction(actionEvent -> {
            inputText.setText("Input :" + textField.getText());
            String buff = textField.getText();
            String[] str = buff.split(" ");
            JSONObject obj;
            if (flag == true) obj = JsonHelper.setTrackJson(str);
            else obj = JsonHelper.setGenreJson(str);
            Controller.operationForEntity(obj);
        });
        cancelButton.setOnAction(actionEvent1 -> {
            inputText.setText("Operation is canceled");
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBox.getItems().addAll(FXCollections.observableArrayList("Add", "Delete", "Watch", "Search", "Set"));

        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        trackButton.setOnAction(actionEvent -> {
            choiceBox.setOnAction(event -> choiceBoxOperations(true));
        });
        genreButton.setOnAction(actionEvent -> {
            choiceBox.setOnAction(event -> choiceBoxOperations(false));
        });
    }

    @FXML
    private void choiceBoxOperations(Boolean flag) {
        String operation = choiceBox.getValue();
        switch (operation) {
            case "Add":
                if (flag == true) add(true);
                else add(false);
                break;
            case "Watch":
                if(flag == true) watch(true);
                else watch(false);
                break;
            case "Search":
                if (flag == true) search(true);
                else search(false);
                break;
            case "Delete":
                if (flag == true) delete(true);
                else delete(false);
                break;
            case "Set":
                if (flag == true) set(true);
                else set(false);
                break;
        }
    }

}
