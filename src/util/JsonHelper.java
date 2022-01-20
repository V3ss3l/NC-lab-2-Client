package util;

import org.json.simple.*;
import org.json.simple.parser.ParseException;

import static util.Values.*;

import java.io.IOException;
import java.util.ArrayList;

public abstract class JsonHelper {

    public static JSONObject addTrackArrayJson(String[] str) {
        JSONObject obj = new JSONObject();
        JSONObject temp;
        JSONArray arr = new JSONArray();
        obj.put(COMMAND, ADD);
        obj.put(ENTITY, TRACK);
        for (int i = 0; i < str.length; i++) {
            temp = new JSONObject();
            temp.put(NAME_OF_TRACK, str[i++]);
            temp.put(NAME_OF_PERFORMER, str[i++]);
            temp.put(NAME_OF_ALBUM, str[i++]);
            temp.put(NAME_OF_GENRE, str[i]);
            arr.add(temp);
        }
        obj.put(ARRAY, arr);
        return obj;
    }

    public static JSONObject addGenreArrayJson(String[] str) {
        JSONObject obj = new JSONObject();
        JSONObject temp;
        JSONArray arr = new JSONArray();
        obj.put(COMMAND, ADD);
        obj.put(ENTITY, GENRE);
        temp = new JSONObject();
        temp.put(NAME_OF_GENRE, str[0]);
        arr.add(temp);
        obj.put(ARRAY, arr);
        return obj;
    }

    public static JSONObject watchTrack() {
        JSONObject obj = new JSONObject();
        obj.put(COMMAND, WATCH);
        obj.put(ENTITY, TRACK);
        return obj;
    }
    public static JSONObject watchGenre() {
        JSONObject obj = new JSONObject();
        obj.put(COMMAND, WATCH);
        obj.put(ENTITY, GENRE);
        return obj;
    }

    //json метод поиска
    public static JSONObject searchTrackJson(String[] str) {
        JSONObject obj = new JSONObject();
        obj.put(COMMAND, SEARCH);
        obj.put(ENTITY, TRACK);
        int i = 0;
        obj.put(NAME_OF_TRACK, str[i++]);
        obj.put(NAME_OF_PERFORMER, str[i++]);
        obj.put(NAME_OF_ALBUM, str[i++]);
        obj.put(NAME_OF_GENRE, str[i]);
        return obj;
    }

    public static JSONObject searchGenreJson(String[] str) {
        JSONObject obj = new JSONObject();
        obj.put(COMMAND, SEARCH);
        obj.put(ENTITY, GENRE);
        obj.put(NAME_OF_GENRE, str[0]);
        return obj;
    }

    // джейсон для удаления
    public static JSONObject deleteTrackJson(String[] str) {
        JSONObject obj = new JSONObject();
        obj.put(COMMAND, DELETE);
        obj.put(ENTITY, TRACK);
        obj.put(NAME_OF_TRACK, str[0]);
        obj.put(NAME_OF_PERFORMER, str[1]);
        return obj;
    }

    public static JSONObject deleteGenreJson(String[] str){
        JSONObject obj = new JSONObject();
        obj.put(COMMAND, DELETE);
        obj.put(ENTITY, GENRE);
        obj.put(NAME_OF_GENRE, str[0]);
        return obj;
    }

    public static JSONObject setTrackJson(String[] str) {
        JSONObject obj = new JSONObject();
        obj.put(COMMAND, SET);
        obj.put(ENTITY, TRACK);
        int i = 0;
        obj.put(NAME_FOR_SEARCH, str[i++]);
        obj.put(PERFORMER_FOR_SEARCH, str[i++]);
        obj.put(NAME_OF_TRACK, str[i++]);
        obj.put(NAME_OF_PERFORMER, str[i++]);
        obj.put(NAME_OF_ALBUM, str[i++]);
        obj.put(NAME_OF_GENRE, str[i]);
        return obj;
    }

    public static JSONObject setGenreJson(String[] str){
        JSONObject obj = new JSONObject();
        obj.put(COMMAND, SET);
        obj.put(ENTITY, GENRE);
        obj.put(GENRE_FOR_SEARCH, str[0]);
        obj.put(NAME_OF_GENRE, str[1]);
        return obj;
    }

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

    public static ArrayList<String> viewModel(JSONObject obj) {
        ArrayList<String> arr = new ArrayList<>();
        StringBuffer str;
        JSONArray jsArr;
        JSONObject buff;
        if (obj.get(ENTITY).equals(GENRE)) {
            jsArr = (JSONArray) obj.get(ARRAY);
            for (int i = 0; i < jsArr.size(); i++) {
                str = new StringBuffer();
                buff = (JSONObject) jsArr.get(i);
                str.append("Genre: " + buff.get(NAME_OF_GENRE) + "\n");
                arr.add(str.toString());
            }
            return arr;
        } else {
            jsArr = (JSONArray) obj.get(ARRAY);
            for (int i = 0; i < jsArr.size(); i++) {
                str = new StringBuffer();
                buff = (JSONObject) jsArr.get(i);
                str.append("Name: " + buff.get(NAME_OF_TRACK) + " ");
                str.append("Performer: " + buff.get(NAME_OF_PERFORMER) + " ");
                str.append("Album: " + buff.get(NAME_OF_ALBUM) + " ");
                str.append("Genre: " + buff.get(NAME_OF_GENRE) + "\n");
                arr.add(str.toString());
            }
            return arr;
        }
    }

    public static String parseErrorJson(JSONObject obj) {
        String str = (String) obj.get(COMMAND);
        return str;
    }

}
