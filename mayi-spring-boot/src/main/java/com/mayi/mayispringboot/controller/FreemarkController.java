package com.mayi.mayispringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class FreemarkController {

    @RequestMapping("/freemark")
    public String freemark(Map<String, Object> model){
        model.put("name", "chen");
        return "freemark";
    }
}
