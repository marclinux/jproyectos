package com.example.demo.controller.historias;

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
import com.example.demo.model.Historia;
import com.example.demo.model.Proyecto;
import com.example.demo.service.Historia.HistoriaService;
import com.example.demo.service.proyecto.ProyectoService;

@Controller // This means that this class is a Controller
@RequestMapping("/scrum/historias") // This means URL's start with /Historia (after Application path)
public class HistoriaController {
  @Autowired
  private HistoriaService historiaService;

  @Autowired
  private ProyectoService proyectoService;

  @GetMapping("/index")
  public String showUserList(Model model) {
    model.addAttribute("historias", historiaService.getHistorias());
    return "historia/index";
  }

  @GetMapping("/registrar") // VISTA HMTL
  public String registrar(Model model) {
    Historia n = new Historia();
    model.addAttribute("historia", n);
    model.addAttribute("proyectos",proyectoService.getProyectos());
    return "historia/registrarHistoria";
  }

  @PostMapping(path = "/registrar") // registro DAO
  public String registrar(@ModelAttribute("historia") @Valid Historia e, BindingResult bindingResult,Model model) {
    if (bindingResult.hasErrors()) {
      return "/scrum/historias/registrarHistoria";
    } else {      
      Proyecto p=proyectoService.getProyecto(e.getProject().getId()).get();
      if(p==null){
        model.addAttribute("errorMessage","No existe el proyecto");
        return "/scrum/actividades/registrarActividad";
      }else{
        e.setProject(p);
        historiaService.createHistoria(e);
        return "redirect:/scrum/historias/index";
      }      
    }
  }

  @GetMapping("/modificar/{id}") // VISTA HTML
  public String modificar(@PathVariable Integer id, Model model) {
    model.addAttribute("historia", historiaService.getHistoria(id).get());
    model.addAttribute("proyectos",proyectoService.getProyectos());
    return "historia/modificarHistoria";

  }

  @PostMapping("/actualizar/{id}") // PERSISTENTE
  public String modificar(@PathVariable Integer id, @ModelAttribute("historia") Historia e, Model model) {
    e.setProject(proyectoService.getProyecto(e.getProject().getId()).get());    
    
    historiaService.updateHistoria(id, e);
    return "redirect:/scrum/historias/index";
  }

  @GetMapping(path = "/eliminar/{id}")
  public String eliminar(@PathVariable Integer id) {
    Historia e = historiaService.getHistoria(id).get();
    if (e == null) {
      return "redirect:/scrum/historias/index";
    } else {
      Proyecto h=proyectoService.getProyecto(e.getProject().getId()).get();
      //eliminar primero la relación con el padre
      //para poder eliminar el hijo
      h.removeHistoria(e);
      historiaService.deleteHistoria(id);
      return "redirect:/scrum/historias/index";
    }
  }

}