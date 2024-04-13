package ru.vihub.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecommendationController {
    @GetMapping("/recommended")
    public String listRecommended(){
        return "recommendation-page";
    }
}
