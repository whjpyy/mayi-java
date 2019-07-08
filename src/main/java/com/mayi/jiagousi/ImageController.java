package com.mayi.jiagousi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ImageController {

    @RequestMapping("/image")
    public String hello(){
        System.out.println("image_time");
        return "image_time";
    }
}
