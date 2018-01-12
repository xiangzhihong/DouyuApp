package com.xzh.douyuapp.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.List;


public class GsonUtil {
    // Gson实体
    private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss:SSS").create();


    public static <T> T json2Object(String json, TypeToken<T> typeToken) {
        return gson.fromJson(json, typeToken.getType());
    }


    public static <T> List<T> json2List(String json, TypeToken<List<T>> typeToken){
        return gson.fromJson(json, typeToken.getType());
    }


    public static String object2Json(Object obj) {
        return gson.toJson(obj);
    }

}
