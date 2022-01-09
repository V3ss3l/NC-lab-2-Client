package controller;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import view.ConsoleView;
import view.View;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

import static util.Values.COMMAND;

public class Controller implements Serializable, Runnable{
    private static View view;
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

    public void run(){
        try {
            try {
                socket = new Socket(HOST, PORT);
                out = new ObjectOutputStream(socket.getOutputStream());
                in = new ObjectInputStream(socket.getInputStream());
                Scanner sc = new Scanner(System.in);
                boolean flag = true;
                while (flag) {
                    JSONObject obj = (JSONObject) in.readObject();
                    if (obj.containsKey(COMMAND)) {
                        view.errorList(obj);
                    } else view.stringList(obj);
                    System.out.println("Operation is complete. Do you want to repeat? yes|no");
                    String answer = sc.nextLine();
                    if (answer.equals("no")) flag = false;
                    else view.run();
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

