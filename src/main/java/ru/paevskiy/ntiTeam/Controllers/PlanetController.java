package ru.paevskiy.ntiTeam.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.paevskiy.ntiTeam.DAO.ChaosLordService;
import ru.paevskiy.ntiTeam.DAO.PlanetService;
import ru.paevskiy.ntiTeam.Models.Planet;

import javax.validation.Valid;

@Controller
@RequestMapping("/planets")
public class PlanetController {


    private final PlanetService service;

    @Autowired
    public PlanetController(PlanetService service) {
        this.service = service;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("planets", service.getAllPlanet());
        return "planets/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("planet", service.getPlanet(id));
        return "planets/show";
    }

    @GetMapping("/new")
    public String newPlanet(@ModelAttribute("planet") Planet planet) {
        return "planets/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("planet") @Valid Planet planet,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "planets/new";

        service.createPlanet(planet);
        return "redirect:/planets";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("planet", service.getPlanet(id));
        return "planets/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("planet") @Valid Planet planet, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "planets/edit";

        service.appointALord(planet);
        return "redirect:/planets";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        service.destroyPlanet(id);
        return "redirect:/planets";
    }
}
