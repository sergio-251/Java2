package main;

import java.util.ArrayList;
import java.util.HashMap;


public class CollectionClass {

    public static ArrayList<String> getArrayList(String[] data){
        ArrayList<String> result = new ArrayList<String>(data.length);
        for (int i = 0; i < data.length; i++){
            result.add(data[i]);
        }

        return result;
    }

    public static HashMap<String, Integer> uniqString(ArrayList<String> data){
        HashMap<String, Integer> result = new HashMap<String, Integer>(data.size());

        for (int i = 0; i < data.size(); i++){
            Integer count = result.get(data.get(i));
            result.put(data.get(i), count == null ? 1 : count + 1);
        }
        return result;
    }
}
