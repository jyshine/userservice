package com.june.sample.userservice.home;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @RequestMapping("/ping")
    public String home(){
        return "welcome!";
    }
}
