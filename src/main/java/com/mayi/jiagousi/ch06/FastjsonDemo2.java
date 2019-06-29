package com.mayi.jiagousi.ch06;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

/**
 * JSON封装
 */
public class FastjsonDemo2 {

    public static void main(String[] args) {
        JSONObject root = new JSONObject();
        root.put("id", 20);
        root.put("name", "陈友增");
        JSONArray items = new JSONArray();
        JSONObject item1 = new JSONObject();
        item1.put("itemId", "20");
        item1.put("itemName", "汉服");
        JSONObject item2 = new JSONObject();
        item2.put("itemId", "21");
        item2.put("itemName", "商服");
        items.add(item1);
        items.add(item2);
        root.put("items", items);
        System.out.println(root.toJSONString());
        // 使用实体类分装
        FastUser user = new FastUser();
        user.setId(20);
        user.setName("陈友增");
        final ArrayList<FastItem> listItem = new ArrayList<>();
        System.out.println(new JSONObject().toJSONString(user));
    }
}
