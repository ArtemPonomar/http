package util;

import com.google.gson.Gson;
import entytis.Pet;

public class GsonController {
    private Gson gson = new Gson();

    public Object fromJson(String jString, Class c){
        return gson.fromJson(jString, c);
    }

    public String toJson(Object object){
        return gson.toJson(object);
    }
}
