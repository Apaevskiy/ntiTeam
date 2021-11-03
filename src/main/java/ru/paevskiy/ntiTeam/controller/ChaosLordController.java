package ru.paevskiy.ntiTeam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.paevskiy.ntiTeam.service.LordService;
import ru.paevskiy.ntiTeam.entity.Lord;

@Controller
@RequestMapping("/lords")
public class ChaosLordController {
    private final LordService service;

    @Autowired
    public ChaosLordController(LordService service) {
        this.service = service;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("lords", service.getAllLords());
        return "lords/index";
    }

    @PostMapping()
    public String create(@ModelAttribute("lord") Lord lord,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "lords/new";
        service.createLord(lord);
        return "redirect:/lords";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") long id, Model model) {
        model.addAttribute("lord", service.getLord(id));
        return "lords/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("lord", service.getLord(id));
        return "lords/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("lords") Lord lord, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "lords/edit";

        service.updateLord(lord);
        return "redirect:/lords";
    }

    @GetMapping("/new")
    public String newChaosLord(@ModelAttribute("lord") Lord planet) {
        return "lords/new";
    }
}
