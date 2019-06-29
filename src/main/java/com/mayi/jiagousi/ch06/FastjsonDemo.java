package com.mayi.jiagousi.ch06;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class FastjsonDemo {

    static String json = "{\"id\":20,\"name\":\"陈友增\",\"items\":[{\"itemId\":1,\"itemName\":\"汉服\"},{\"itemId\":2,\"itemName\":\"商服\"}]}";

    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject().parseObject(json);
        System.out.println("json:" + jsonObject);
        System.out.println("id：" + jsonObject.getInteger("id"));
        System.out.println("name: " + jsonObject.getString("name"));
        JSONArray items = jsonObject.getJSONArray("items");
        for (int i = 0; i < items.size(); i++) {
            final JSONObject item = items.getJSONObject(i);
            System.out.println("itemId:" + item.getInteger("itemId"));
            System.out.println("itemName:" + item.getString("itemName"));
        }
        System.out.println("---------------------------");
        // JSON转对象
        final FastUser user = new JSONObject().parseObject(json, FastUser.class);
        System.out.println(user);
    }
}
