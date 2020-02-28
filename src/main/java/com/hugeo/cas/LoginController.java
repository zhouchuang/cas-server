package com.hugeo.cas;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @GetMapping("/register")
    public String register(){
        return "register...";
    }
}
