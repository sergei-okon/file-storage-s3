package ua.com.sergeiokon.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @GetMapping("/v1/")
    public String welcome() {
        return "WELCOME";
    }
}
