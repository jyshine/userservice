package com.june.sample.userservice.home;

import com.june.sample.userservice.core.web.RestResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @RequestMapping("/ping")
    public RestResponse<String> ping(){
        return new RestResponse<>("OK");
    }

}
