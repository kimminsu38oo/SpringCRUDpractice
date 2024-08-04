package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {
    @GetMapping("/hi")
    public String niceToMeetYou(Model model) {
        model.addAttribute("username", "kimminsu");
        return "greetings";
    }

    @GetMapping("/bye")
    public String goodbye(Model model) {
        model.addAttribute("username", "kimminsu");
        return "goodbye";
    }

}
