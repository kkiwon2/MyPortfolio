package com.myportfolio.web.controller;

import com.myportfolio.web.domain.Person;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class SimpleRestController {
    @GetMapping("/comment")
    public String test() {
        return "comment";
    }

    @GetMapping("/test")
    public String test2() {
        return "test";
    }

    @GetMapping("/test2")
    public String test3() {
        return "comment2";
    }

    @PostMapping("/send")
    @ResponseBody
    public Person test(@RequestBody Person p) {
        System.out.println("p = " + p);
        p.setName("ABC");
        p.setAge(p.getAge() + 10);

        return p;
    }
}