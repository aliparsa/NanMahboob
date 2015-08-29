package Helpers;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * Created by Ali on 8/29/2015.
 */
public class ObjectToJsonConvertor<T> {

    public static   JSONObject objectjToJson(Object object){
        JSONObject jsonObject = new JSONObject();
        Field fields[] = object.getClass().getFields();
        try {
            for (Field field : fields) {
                jsonObject.accumulate(field.getName(), field.get(object).toString());
            }
        }catch (Exception e){

        }
        return jsonObject;
    }

    public   T jsonToObject(JSONObject jsonObject,T object){

        Field fields[] = object.getClass().getFields();

        for (Field field : fields) {
            try {

                if(field.getType() == Integer.class){
                    field.set(object,jsonObject.getInt(field.getName()));
                }

                if(field.getType() == String.class){
                    field.set(object,jsonObject.getString(field.getName()));
                }

                if(field.getType() == Double.class){
                    field.set(object,jsonObject.getDouble(field.getName()));
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return object;
    }


}
