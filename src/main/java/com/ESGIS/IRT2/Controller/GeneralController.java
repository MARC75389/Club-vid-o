package com.ESGIS.IRT2.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeneralController {
    @GetMapping("/")
    public String Home(){
        return "Hello";
    }
}
