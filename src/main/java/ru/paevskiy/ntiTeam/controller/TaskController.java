package ru.paevskiy.ntiTeam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.paevskiy.ntiTeam.service.LordService;

@Controller
public class TaskController {
    private final LordService service;

    @Autowired
    public TaskController(LordService service) {
        this.service = service;
    }

    @GetMapping("/top")
    public String top(Model model) {
        model.addAttribute("lords", service.getTop());
        return "lords/index";
    }

    @GetMapping("/parasites")
    public String parasites(Model model) {
        model.addAttribute("lords", service.getParasites());
        return "lords/index";
    }
}
