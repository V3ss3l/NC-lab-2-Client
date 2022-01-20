package view;

import controller.Controller;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import util.JsonHelper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

public class ConsoleView implements View, Serializable, Runnable {
    private Controller controller;

    public ConsoleView() {
    }

    public ConsoleView(Controller controller) {
        this.controller = controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void run(){
        System.out.println("Program is running.\n" + "|Add, Delete, Set, Search, Watch, Help, Exit|");
        Scanner sc = new Scanner(System.in);
        String buff = sc.nextLine(); //ожидание ввода команды
        String[] str = buff.split(" ");
        switch (str[0]) {
            case "Add":
                controller.operationForEntity(JsonHelper.addEntityArrayJson(str));
                break;
            case "Watch":
                controller.operationForEntity(JsonHelper.watchEntity(str));
                break;
            case "Search":
                controller.operationForEntity(JsonHelper.searchEntityJson(str));
                break;
            case "Delete":
                controller.operationForEntity(JsonHelper.deleteEntityJson(str));
                break;
            case "Set":
                controller.operationForEntity(JsonHelper.setEntityJson(str));
                break;
            case "Help":
                System.out.println("List of operations:\n" + "Add - добавление сущности или сущностей в список : Entity|name of track or name of genre|name of performer|name of album|name of genre\n"
                        + "Delete - удаление сущности из списка : Entity|name of track or genre|name of performer\n" + "Set - изменение сущности по входным данным : Entity|name of track (search) or name of genre(search)|name of performer(search)|name of track|name of performer|name of album|name of genre\n"
                        + "Search - поиск сущности по ключам : Entity|name of track or genre|name of performer" + "Watch - просмотр списка сущностей : Entity\n" + "Help - справка по командам\n");
                break;
            case "Exit":
                System.out.println("Program is shutting down...");
                controller.exitSocket();
                break;
            default:
                System.out.println("Where is an action?");
        }
    }

    public void stringList(JSONObject obj) throws IOException, ParseException {
        System.out.println("Results:\n " + JsonHelper.viewModel(obj));
    }

    public void errorList(JSONObject object) {
        System.out.println("Result:\n " + JsonHelper.parseErrorJson(object));
    }
}