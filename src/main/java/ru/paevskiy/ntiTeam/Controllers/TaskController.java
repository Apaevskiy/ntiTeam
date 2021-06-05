package ru.paevskiy.ntiTeam.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.paevskiy.ntiTeam.DAO.ChaosLordService;

@Controller
public class TaskController {
    private final ChaosLordService service;

    @Autowired
    public TaskController(ChaosLordService service) {
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
