package com.example.demo.controller.sprints;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.model.Sprint;
import com.example.demo.service.sprint.SprintService;

@Controller // This means that this class is a Controller
@RequestMapping("/scrum/sprints") // This means URL's start with /Sprint (after Application path)
public class SprintController {
  @Autowired
  private SprintService SprintService;

  @GetMapping("/index")
  public String showUserList(Model model) {
    model.addAttribute("sprints", SprintService.getSprints());
    return "sprint/index";
  }

  @GetMapping("/registrar") // VISTA HMTL
  public String registrar(Model model) {
    Sprint n = new Sprint();
    model.addAttribute("sprint", n);
    return "sprint/registrarSprint";
  }

  @PostMapping(path = "/registrar") // registro DAO
  public String registrar(@ModelAttribute("sprint") @Valid Sprint e, BindingResult bindingResult,Model model) {
    if (bindingResult.hasErrors()) {
      return "/sprint/registrarSprint";
    } else {      
      if(e.getFechaFinal().before(e.getFechaInicio())){
        model.addAttribute("errorMessage","LA FECHA DEBE SER MAYOR A LA FECHA INICIAL");
        return "/sprint/registrarSprint";
      }
      
      SprintService.createSprint(e);
      return "redirect:/scrum/sprints/index";
    }
  }

  @GetMapping("/modificar/{id}") // VISTA HTML
  public String modificar(@PathVariable int id, Model model) {
    model.addAttribute("sprint", SprintService.getSprint(id).get());
    return "sprint/modificarSprint";

  }

  @PostMapping("/actualizar/{id}") // PERSISTENTE
  public String modificar(@PathVariable int id, @ModelAttribute("sprint") Sprint e, Model model) {
    SprintService.updateSprint(id, e);
    return "redirect:/scrum/sprints/index";
  }

  @GetMapping(path = "/eliminar/{id}")
  public String eliminar(@PathVariable int id) {
    Sprint e = SprintService.getSprint(id).get();
    if (e == null) {
      return "redirect:/scrum/sprints/index";
    } else {
      SprintService.deleteSprint(id);
      return "redirect:/scrum/sprints/index";
    }
  }

}