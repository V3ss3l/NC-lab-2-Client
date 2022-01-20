package controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.json.simple.JSONObject;
import util.JsonHelper;

import java.net.URL;
import java.util.ResourceBundle;

public class TrackController implements Initializable {
    @FXML
    private Button okButton;

    @FXML
    private Button cancelButton;

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private TextField trackField;

    @FXML
    private TextField perfField;

    @FXML
    private TextField albumField;

    @FXML
    private TextField genreField;

    @FXML
    private TextField trackSearchField;

    @FXML
    private TextField perfSearchField;

    @FXML
    private Label inputText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBox.getItems().addAll(FXCollections.observableArrayList("Add", "Delete", "Watch", "Search", "Set"));
        choiceBox.setOnAction(event -> choiceBoxOperations());
    }

    private void clearFields(){
        trackField.clear();
        perfField.clear();
        albumField.clear();
        genreField.clear();
        trackSearchField.clear();
        perfSearchField.clear();
    }

    @FXML
    private void add() {
        okButton.setOnAction(actionEvent -> {
            inputText.setText("Input was sent");
            String name = trackField.getText();
            String perf = perfField.getText();
            String album = albumField.getText();
            String genre = genreField.getText();
            String[] str = {name, perf, album, genre};
            JSONObject obj = JsonHelper.addTrackArrayJson(str);
            Controller.operationForEntity(obj);
            clearFields();
        });
        cancelButton.setOnAction(actionEvent1 -> {
            clearFields();
            inputText.setText("Operation was canceled");
        });
    }

    @FXML
    private void watch() {
        okButton.setOnAction(actionEvent -> {
            inputText.setText("Input was sent");
            Controller.operationForEntity(JsonHelper.watchTrack());
            clearFields();
        });
        cancelButton.setOnAction(actionEvent -> {
            clearFields();
            inputText.setText("Operation is canceled");
        });
    }

    @FXML
    private void delete() {
        okButton.setOnAction(actionEvent -> {
            inputText.setText("Input was sent");
            String nameForSearch = trackSearchField.getText();
            String perfForSearch = perfSearchField.getText();
            String[] str = {nameForSearch, perfForSearch};
            Controller.operationForEntity(JsonHelper.deleteTrackJson(str));
            clearFields();
        });
        cancelButton.setOnAction(actionEvent1 -> {
            clearFields();
            inputText.setText("Operation is canceled");
        });
    }

    @FXML
    private void search() {
        okButton.setOnAction(actionEvent -> {
            inputText.setText("Input was sent");
            String nameForSearch = trackSearchField.getText();
            String perfForSearch = perfSearchField.getText();
            String[] str = {nameForSearch, perfForSearch};
            Controller.operationForEntity(JsonHelper.searchTrackJson(str));
            clearFields();
        });
        cancelButton.setOnAction(actionEvent1 -> {
            clearFields();
            inputText.setText("Operation is canceled");
        });
    }

    @FXML
    private void set() {
        okButton.setOnAction(actionEvent -> {
            inputText.setText("Input was sent");
            String nameForSearch = trackSearchField.getText();
            String perfForSearch = perfSearchField.getText();
            String name = trackField.getText();
            String perf = perfField.getText();
            String album = albumField.getText();
            String genre = genreField.getText();
            String[] str = {nameForSearch, perfForSearch, name, perf, album, genre};
            Controller.operationForEntity(JsonHelper.setTrackJson(str));
            clearFields();
        });
        cancelButton.setOnAction(actionEvent1 -> {
            clearFields();
            inputText.setText("Operation is canceled");
        });
    }

    @FXML
    private void choiceBoxOperations() {
        String operation = choiceBox.getValue();
        switch (operation) {
            case "Add":
                add();
                break;
            case "Watch":
                watch();
                break;
            case "Search":
                search();
                break;
            case "Delete":
                delete();
                break;
            case "Set":
                set();
                break;
        }
    }
}
