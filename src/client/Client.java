package client;

import controller.Controller;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import view.ConsoleView;
import view.View;

import java.io.*;
import java.net.*;

import static util.Values.*;

public class Client {
    public static final int PORT = 6000;
    public static final String HOST = "LocalHost";

    private static Socket socket;
    private static BufferedReader reader;
    private static ObjectInputStream in;
    private static ObjectOutputStream out = null;

    public static void main(String[] args) throws IOException {
        try {
            try {
                socket = new Socket(HOST, PORT);
                reader = new BufferedReader(new InputStreamReader(System.in));
                out = new ObjectOutputStream(socket.getOutputStream());
                in = new ObjectInputStream(socket.getInputStream());
                Controller controller = new Controller();
                View view = new ConsoleView(controller);
                controller.setOut(out);
                boolean flag = true;
                while(flag) {
                    view.init(reader);
                    JSONObject obj = (JSONObject) in.readObject();
                    if(obj.containsKey(COMMAND)){
                        view.errorList(obj);
                    } else view.stringList(obj);
                    System.out.println("Operation is complete. Do you want to repeat? yes|no");
                    String answer = reader.readLine();
                    if(answer.equals("no")) flag = false;
                    out.flush();
                }
            } finally {
                System.out.println("Client was closed...");
                socket.close();
                in.close();
                out.close();
            }
        } catch (IOException | ParseException | ClassNotFoundException | InterruptedException e) {
            System.err.println(e);
        }
    }
}


