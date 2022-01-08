package util;

import org.json.simple.*;
import org.json.simple.parser.ParseException;

import static util.Values.*;

import java.io.IOException;
import java.util.ArrayList;

public abstract class JsonHelper {

    public static JSONObject addEntityArrayJson(String[] str) {
        JSONObject obj = new JSONObject();
        JSONObject temp;
        JSONArray arr = new JSONArray();
        obj.put(COMMAND, ADD);
        obj.put(ENTITY, str[1]);
        String entity = str[1];
        if (entity.contains(TRACK)) {
            for (int i = 2; i < str.length; i++) {
                temp = new JSONObject();
                temp.put(NAME_OF_TRACK, str[i++]);
                temp.put(NAME_OF_PERFORMER, str[i++]);
                temp.put(NAME_OF_ALBUM, str[i++]);
                temp.put(NAME_OF_GENRE, str[i]);
                arr.add(temp);
            }
        } else {
            for (int i = 2; i < str.length; i++) {
                temp = new JSONObject();
                temp.put(NAME_OF_GENRE, str[i]);
                arr.add(temp);
            }
        }
        obj.put(ARRAY, arr);
        return obj;
    }

    public static JSONObject watchEntity(String[] str) {
        JSONObject obj = new JSONObject();
        obj.put(COMMAND, WATCH);
        obj.put(ENTITY, str[1]);
        return obj;
    }

    //json метод поиска
    public static JSONObject searchEntityJson(String[] str) {
        JSONObject obj = new JSONObject();
        obj.put(COMMAND, str[0]);
        obj.put(ENTITY, str[1]);
        String entity = str[1];
        if (entity.contains(TRACK)) {
            obj.put(NAME_OF_TRACK, str[2]);
            obj.put(NAME_OF_PERFORMER, str[3]);
            obj.put(NAME_OF_ALBUM, str[4]);
            obj.put(NAME_OF_GENRE, str[5]);
        } else {
            obj.put(NAME_OF_GENRE, str[2]);
        }
        return obj;
    }

    // джейсон для удаления
    public static JSONObject deleteEntityJson(String[] str) {
        JSONObject obj = new JSONObject();
        obj.put(COMMAND, str[0]);
        obj.put(ENTITY, str[1]);
        String entity = str[1];
        if (entity.contains(TRACK)) {
            obj.put(NAME_OF_TRACK, str[2]);
            obj.put(NAME_OF_PERFORMER, str[3]);
        } else obj.put(NAME_OF_GENRE, str[2]);
        return obj;
    }

    public static JSONObject setEntityJson(String[] str) {
        JSONObject obj = new JSONObject();
        obj.put(COMMAND, SET);
        obj.put(ENTITY, str[1]);
        String entity = str[1];
        int i = 2;
        if (entity.contains(TRACK)) {
            obj.put(NAME_FOR_SEARCH, str[i++]);
            obj.put(PERFORMER_FOR_SEARCH, str[i++]);
            obj.put(NAME_OF_TRACK, str[i++]);
            obj.put(NAME_OF_PERFORMER, str[i++]);
            obj.put(NAME_OF_ALBUM, str[i++]);
            obj.put(NAME_OF_GENRE, str[i]);
        } else {
            obj.put(GENRE_FOR_SEARCH, str[i++]);
            obj.put(NAME_OF_GENRE, str[i]);
        }
        return obj;
    }

    public static ArrayList viewModel(JSONObject obj) throws IOException, ParseException {
        ArrayList arr = new ArrayList<>();
        Object entity;
        JSONArray jsArr;
        JSONObject buff;
        jsArr = (JSONArray) obj.get(ARRAY);
        for (int i = 0; i < jsArr.size(); i++) {
            buff = (JSONObject) jsArr.get(i);
            entity = buff.get(ENTITY);
            arr.add(entity);
        }
        return arr;
    }

    public static String parseErrorJson(JSONObject obj) {
        String str = (String) obj.get(COMMAND);
        return str;
    }

}
