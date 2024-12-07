package com.klef.jfsd.springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {

   
    @GetMapping("/")
    public String checkServerStatus() {
        return "API GATEWAY Server IS UP AND RUNNING!!";
    }
}