package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.json.simple.JSONObject;
import util.JsonHelper;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GenreController implements Initializable {

    @FXML
    private Button okButton;

    @FXML
    private Button cancelButton;

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private TextField fieldNameOfGenre;

    @FXML
    private TextField fieldForSearchAndSet;

    @FXML
    private Label inputText;

    public GenreController() {
        fieldNameOfGenre = new TextField();
        fieldForSearchAndSet = new TextField();
        inputText = new Label();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBox.getItems().addAll(FXCollections.observableArrayList("Add", "Delete", "Watch", "Search", "Set"));
        choiceBox.setOnAction(event -> choiceBoxOperations());
    }

    @FXML
    private void add() {
        okButton.setOnAction(actionEvent -> {
            inputText.setText("Input :" + fieldNameOfGenre.getText());
            String buff = fieldNameOfGenre.getText();
            JSONObject obj = JsonHelper.addGenreArrayJson(buff);
            Controller.operationForEntity(obj);
            fieldNameOfGenre.clear();
            fieldForSearchAndSet.clear();
        });
        cancelButton.setOnAction(actionEvent1 -> {
            fieldNameOfGenre.clear();
            fieldForSearchAndSet.clear();
            inputText.setText("Operation is canceled");
        });
    }

    @FXML
    private void watch() {
        okButton.setOnAction(actionEvent -> {
            Controller.operationForEntity(JsonHelper.watchGenre());
            fieldNameOfGenre.clear();
            fieldForSearchAndSet.clear();
        });
        cancelButton.setOnAction(actionEvent -> {
            fieldNameOfGenre.clear();
            fieldForSearchAndSet.clear();
            inputText.setText("Operation is canceled");
        });
    }

    @FXML
    private void delete() {
        okButton.setOnAction(actionEvent -> {
            inputText.setText("Input :" + fieldForSearchAndSet.getText());
            String name = fieldForSearchAndSet.getText();
            Controller.operationForEntity(JsonHelper.deleteGenreJson(name));
            fieldNameOfGenre.clear();
            fieldForSearchAndSet.clear();
        });
        cancelButton.setOnAction(actionEvent1 -> {
            fieldNameOfGenre.clear();
            fieldForSearchAndSet.clear();
            inputText.setText("Operation is canceled");
        });
    }

    @FXML
    private void search() {
        okButton.setOnAction(actionEvent -> {
            inputText.setText("Input :" + fieldForSearchAndSet.getText());
            String name = fieldForSearchAndSet.getText();
            Controller.operationForEntity(JsonHelper.searchGenreJson(name));
            fieldNameOfGenre.clear();
            fieldForSearchAndSet.clear();
        });
        cancelButton.setOnAction(actionEvent1 -> {
            fieldNameOfGenre.clear();
            fieldForSearchAndSet.clear();
            inputText.setText("Operation is canceled");
        });
    }

    @FXML
    private void set() {
        okButton.setOnAction(actionEvent -> {
            inputText.setText("Input :" + fieldNameOfGenre.getText()+", "+fieldForSearchAndSet.getText());
            String name = fieldNameOfGenre.getText();
            String nameForSet = fieldForSearchAndSet.getText();
            String[] str = {name, nameForSet};
            Controller.operationForEntity(JsonHelper.setGenreJson(str));
            fieldNameOfGenre.clear();
            fieldForSearchAndSet.clear();
        });
        cancelButton.setOnAction(actionEvent1 -> {
            fieldNameOfGenre.clear();
            fieldForSearchAndSet.clear();
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
