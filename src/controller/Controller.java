package controller;

import org.json.simple.JSONObject;

import java.io.*;

public class Controller implements Serializable {
    private ObjectOutputStream out;

    public Controller() {
    }

    public void setOut(ObjectOutputStream out) {
        this.out = out;
    }

    public void operationForEntity(JSONObject js) throws IOException {
        out.writeObject(js);
    }

}

