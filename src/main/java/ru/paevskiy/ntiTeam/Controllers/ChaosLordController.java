package ru.paevskiy.ntiTeam.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.paevskiy.ntiTeam.DAO.ChaosLordService;
import ru.paevskiy.ntiTeam.Models.ChaosLord;

import javax.validation.Valid;


@Controller
@RequestMapping("/lords")
public class ChaosLordController {
    private final ChaosLordService service;

    @Autowired
    public ChaosLordController(ChaosLordService service) {
        this.service = service;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("lords", service.getAllLords());
        return "lords/index";
    }

    @PostMapping()
    public String create(@ModelAttribute("lord") @Valid ChaosLord lord,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "lords/new";

        service.createLord(lord);
        return "redirect:/lords";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("lord", service.getLord(id));

        return "lords/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("lord", service.getLord(id));
        return "lords/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("lords") @Valid ChaosLord lord, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "lords/edit";

        service.updateLord(lord);
        return "redirect:/lords";
    }

    @GetMapping("/new")
    public String newChaosLord(@ModelAttribute("lord") ChaosLord planet) {
        return "lords/new";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        return "redirect:/planets";
    }
}
