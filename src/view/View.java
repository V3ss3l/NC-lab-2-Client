package view;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;

public interface View {

    public void init(BufferedReader reader) throws InterruptedException, IOException, ParseException, ClassNotFoundException;

    public void stringList(JSONObject object) throws IOException, ParseException;

    public void errorList(JSONObject object);

}
