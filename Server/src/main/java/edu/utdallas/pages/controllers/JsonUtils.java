package edu.utdallas.pages.controllers;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {

    /**
     * Creates a json string from given key value pairs
     * @param pairs as varargs
     * @return json string
     */
    public static String createJson(String[]... pairs) {
        JSONObject jsonObject = new JSONObject();
        for(String[] pair : pairs) {
            jsonObject.put(pair[0], pair[1]);
        }
        return jsonObject.toString();
    }

    /**
     * Creates a json string from a given 2d list
     * @param list2d to transform into json
     * @return json array
     */
    public static JSONArray createJson(String[] attributes, ArrayList<ArrayList<String>> list2d) {
        JSONArray arr = new JSONArray();
        for(ArrayList<String> list : list2d) {
            JSONObject jsonObject = new JSONObject();
            int i = 0;
            for(String str : list) {
                jsonObject.put(attributes[i],str);
                i++;
            }
            arr.put(jsonObject);
        }
        return arr;
    }

    /**
     * Creates a json string with a json array and object
     * Json has status and contents
     * Status - fail or success
     * Content to display
     * @param arr to add to json
     * @param obj add to json
     * @return json array
     */
    public static String createJson(String contentKey, JSONArray arr, JSONObject obj) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status",obj);
        jsonObject.put(contentKey,arr);
        return jsonObject.toString();
    }



}
