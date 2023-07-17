package com.wudu.Roll.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
       @GetMapping("/hello") // http://localhost:8090/hello
        public String hello() {
            return "Hello World!";
        }
}
