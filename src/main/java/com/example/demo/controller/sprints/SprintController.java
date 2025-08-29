package com.example.demo.controller.sprints;

import java.util.Collection;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.Sprint;
import com.example.demo.service.proyecto.ProyectoService;
import com.example.demo.service.sprint.SprintService;

@Controller // This means that this class is a Controller
@RequestMapping("/scrum/sprints") // This means URL's start with /Sprint (after Application path)
public class SprintController {
  @Autowired
  private SprintService sprintService;
  @Autowired
  private ProyectoService proyectoService;


  @GetMapping("/index")
  public String showUserList(Model model) {
    model.addAttribute("sprints", sprintService.getSprints());
    model.addAttribute("proyectos",this.proyectoService.getProyectos());
    return "sprint/index";
  }

  @GetMapping("/registrar") // VISTA HMTL
  public String registrar(Model model) {
    //Sprint n = new Sprint();
    model.addAttribute("sprint", new Sprint());
    model.addAttribute("proyectos", this.proyectoService.getProyectos());
    return "sprint/registrarSprint";
  }

  @PostMapping(path = "/registrar") // registro DAO
  public String registrar(@ModelAttribute("sprint") @Valid Sprint e, BindingResult bindingResult,Model model,
		  RedirectAttributes ra) {
    if (bindingResult.hasErrors()) {
      return "/sprint/registrarSprint";
      
    }
    if (e.getFechaInicio() != null && e.getFechaFinal() != null
            && e.getFechaFinal().before(e.getFechaInicio())) {
        model.addAttribute("errorMessage", "La fecha final debe ser mayor o igual a la fecha inicial");
        return "sprint/registrarSprint";
    }

    // IMPORTANTE: el sprint debe tener proyecto por la FK NOT NULL
    if (e.getProyecto() == null) {
        model.addAttribute("errorMessage", "Debe seleccionar un proyecto para el sprint.");
        return "sprint/registrarSprint";
    }

    sprintService.createSprint(e);
    ra.addFlashAttribute("successMessage", "Sprint creado correctamente");
    return "redirect:/scrum/sprints/index";
}
    


  @GetMapping("/modificar/{id}") // VISTA HTML
  public String modificar(@PathVariable Integer id, Model model, RedirectAttributes ra) {
    Optional<Sprint> opt = sprintService.getSprint(id);
    if(opt.isEmpty()) {
    	ra.addFlashAttribute("errorMessage", "El sprint con id" + id + "no existe.");
    	return "redirect:/scrum/sprints/index";
    }
    
	  model.addAttribute("sprint", opt.get());
	  model.addAttribute("proyectos",this.proyectoService.getProyectos());
    return "sprint/modificarSprint";

  }

  
  @PostMapping("/actualizar/{id}") // PERSISTENTE
  public String modificar(@PathVariable Integer id, 
		  @ModelAttribute("sprint") @Valid
		  Sprint sprint, 
		  Model model, 
		  BindingResult bindingResult, 
		  RedirectAttributes ra) {
	  
	  if (bindingResult.hasErrors()) {
		  return "sprint/modificarSprint";
	  }
	  if(sprint.getFechaInicio() != null && sprint.getFechaFinal() != null
			  && sprint.getFechaFinal().before(sprint.getFechaInicio())) {
		  model.addAttribute("errorMessage", "La fecha inicial debe ser mayor o igual a la fecha inicia");
		  return "sprint/modificarSprint";
	  }
	  sprintService.updateSprint(id, sprint);
      ra.addFlashAttribute("successMessage", "Sprint actualizado correctamente");
      return "redirect:/scrum/sprints/index";
	  
  }

  
  @GetMapping("/eliminar/{id}")
  public String eliminar(@PathVariable Integer id, RedirectAttributes ra) {
      Optional<Sprint> opt = sprintService.getSprint(id);
      if (opt.isEmpty()) {
          ra.addFlashAttribute("errorMessage", "El sprint con id " + id + " no existe.");
          return "redirect:/scrum/sprints/index";
      }
      sprintService.deleteSprint(id);
      ra.addFlashAttribute("successMessage", "Sprint eliminado correctamente");
      return "redirect:/scrum/sprints/index";
  }

  // --- Endpoint JSON para Vue: listar sprints por proyecto ---
  @GetMapping("/api/by-proyecto/{proyectoId}")
  @ResponseBody
  public Collection<Sprint> getByProyecto(@PathVariable Integer proyectoId) {
      return sprintService.getSprintsByProyecto(proyectoId);
  }

}