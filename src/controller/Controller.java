package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import util.JsonHelper;
import view.ConsoleView;
import view.View;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

import static util.Values.COMMAND;

public class Controller implements Serializable, Runnable{
    private static View view;
    private static boolean flag;
    public static final int PORT = 6000;
    public static final String HOST = "LocalHost";
    private static Socket socket;
    private static ObjectOutputStream out;
    private static ObjectInputStream in;

    public Controller() {
    }

    public Controller(View view) {
        this.view = view;
    }


    public void operationForEntity(JSONObject js) {
        try {
            out.writeObject(js);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exitSocket(){
        this.flag = false;
    }

    public void run(){
        try {
            try {
                socket = new Socket(HOST, PORT);
                out = new ObjectOutputStream(socket.getOutputStream());
                in = new ObjectInputStream(socket.getInputStream());
                flag = true;
                while (flag) {
                    JSONObject obj = (JSONObject) in.readObject();
                    if (obj.containsKey(COMMAND)) {
                        view.errorList(obj);
                    } else view.stringList(obj);
                    view.run();
                }
            } finally {
                System.out.println("Client was closed...");
                socket.close();
                in.close();
                out.close();
            }
        } catch (IOException | ClassNotFoundException | ParseException e) {
            System.err.println(e);
        }
    }

    @FXML
    private Button trackButton;

    @FXML
    private Button genresButton;

    @FXML
    private Button okButton;

    @FXML
    private Button cancelButton;

    @FXML
    private ChoiceBox choiceBox;

    @FXML
    private MenuBar menuBar;

    @FXML
    private ListView listView;

    @FXML
    private TextField textField;

    @FXML
    private void add(){
        String buff = textField.getText();
        String[] str = buff.split(" ");
        JSONObject obj = JsonHelper.addEntityArrayJson(str);
        operationForEntity(obj);
    }

    @FXML
    private void delete(){

    }

    @FXML
    private void watch(){

    }

    @FXML
    private void search(){

    }

    @FXML
    private void set(){

    }
}

