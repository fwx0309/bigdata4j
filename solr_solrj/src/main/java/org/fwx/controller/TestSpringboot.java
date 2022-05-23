package org.fwx.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestSpringboot {

    @RequestMapping("/")
    public String testWeb(){
        return "welcome !";
    }
}
