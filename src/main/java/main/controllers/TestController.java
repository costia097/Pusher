package main.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class TestController {

    @GetMapping("/check")
    public List<String> check() {
        return Arrays.asList("A", "B", "C", "D");
    }
}
