package cn.edu.zstu.app.sample_network.Utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import cn.edu.zstu.app.sample_network.bean.User;

/**
 * Created by lenovo on 2015/12/2.
 */
public class JSonUtil {

    public static<T> String toJsonString(T t){
        Gson gson=new Gson();
        return  gson.toJson(t);
    }

    public static<T> String toJsonString(ArrayList<T> t) {
        Gson gson=new Gson();
        return gson.toJson(t);
    }
    public static<T> String toJsonString(T[] t) {
        Gson gson = new Gson();
        return gson.toJson(t);
    }

    public static String toObjectString(String jsonString,String elementName){
        JsonParser parser=new JsonParser();
        JsonElement el=parser.parse(jsonString);
        String obj=el.getAsJsonObject().get(elementName).getAsString();
        return obj;
    }

    public static<T> T toObject(String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, new TypeToken<T>() {
        }.getType());
    }

    public static<T> List<T> toObjectList(String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, new TypeToken<List<T>>(){}.getType());
    }

    public static  String getJsonElementString(String jsonString,String elementName){
        JsonParser parser=new JsonParser();
        JsonElement el=parser.parse(jsonString);
        JsonObject obj=el.getAsJsonObject();
        return obj.get(elementName).toString();
    }

    public static void main(String[] arg){
        User user=new User();
        user.setId(1);;
        user.setName("Alice");
        user.setSex("female");
        user.setAge(10);
        User user2=new User();
        user2.setId(1);;
        user2.setName("Bob");
        user2.setSex("male");
        user2.setAge(20);
        List<User> list=new ArrayList<>();
        list.add(user);
        list.add(user2);
        System.out.println("对象—>JSON:" + JSonUtil.toJsonString(user));
        System.out.println("列表—>JSON:"+JSonUtil.toJsonString(list));
        System.out.println("JSON—>对象:"+JSonUtil.toObject(JSonUtil.toJsonString(user)));
        System.out.println("JSON—>列表:"+JSonUtil.toObjectList(JSonUtil.toJsonString(list)));
        String json = "{start: 0, pageCount: 2, " +
                "dataList:[{\"id\":1,\"name\":\"Alice\",\"sex\":\"female\",\"age\":10}"
                + ",{\"id\":2,\"name\":\"Bob\",\"sex\":\"male\",\"age\":20}]}";
        String jsonString=JSonUtil.getJsonElementString(json,"dataList");
        List<User> ll=JSonUtil.toObjectList(jsonString);
        System.out.println("获取JSON元素:"+ll);
    }

}
