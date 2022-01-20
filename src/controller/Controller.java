package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import util.JsonHelper;
import view.JavaFXView;
import view.View;

import java.awt.event.ActionEvent;
import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

import static util.Values.COMMAND;

public class Controller implements Serializable, Runnable{
    private View view;
    private static boolean flag;
    public static final int PORT = 6000;
    public static final String HOST = "LocalHost";
    private static Socket socket;
    private static ObjectOutputStream out;
    private static ObjectInputStream in;

    public Controller() {
    }

    public Controller(View view){
        this.view = view;
    }

    public static void operationForEntity(JSONObject js) {
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
                    if(obj.containsKey(COMMAND)) view.errorList(obj);
                    else view.stringList(obj);
                    System.out.println("Result: " + JsonHelper.viewModel(obj).toString());
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
}

