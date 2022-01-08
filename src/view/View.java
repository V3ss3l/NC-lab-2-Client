package view;

import controller.Controller;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;

public interface View extends Runnable{

    public void run();

    public void setController(Controller controller);

    public void stringList(JSONObject object) throws IOException, ParseException;

    public void errorList(JSONObject object);

}
