package com.mayi.jiagousi.ch06;

import lombok.Data;

import java.util.List;

@Data
class FastItem{
    private Integer itemId;
    private String itemName;
}

@Data
public class FastUser {

    private Integer id;
    private String name;
    private List<FastItem> items;

}
