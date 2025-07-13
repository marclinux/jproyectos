package com.example.demo.controller.actividad;

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
import com.example.demo.model.Actividad;
import com.example.demo.model.Historia;
import com.example.demo.service.Historia.HistoriaService;
import com.example.demo.service.actividad.ActividadService;

@Controller // This means that this class is a Controller
@RequestMapping("scrum/actividades") // This means URL's start with /Actividad (after Application path)
public class ActividadController {
  @Autowired
  private ActividadService ActividadService;

  @Autowired
  private HistoriaService historiaService;

  @GetMapping("/index")
  public String showUserList(Model model) {
    model.addAttribute("actividades", ActividadService.getActividads());
    return "actividad/index";
  }

  @GetMapping("/registrar") // VISTA HMTL
  public String registrar(Model model) {    
    model.addAttribute("actividad", new Actividad());
    model.addAttribute("historias",historiaService.getHistorias());
    return "actividad/registrarActividad";
  }

  @PostMapping(path = "/registrar") // registro DAO
  public String registrar(@ModelAttribute("actividad") @Valid Actividad e, BindingResult bindingResult,Model model) {
    if (bindingResult.hasErrors()) {
      return "/actividad/registrarActividad";
    } else {      
      if(e.getFechaFinal().before(e.getFechaInicio())){
        model.addAttribute("errorMessage","LA FECHA DEBE SER MAYOR A LA FECHA INICIAL");
        return "/actividad/registrarActividad";
      }      
      Historia h=historiaService.getHistoria(e.getHistory().getId()).get();
      if(h==null){
        model.addAttribute("errorMessage","LA FECHA DEBE SER MAYOR A LA FECHA INICIAL");
        return "/actividad/registrarActividad";
      }else{
        e.setHistory(h);
        ActividadService.createActividad(e);
      }
      
      return "redirect:/scrum/actividades/index";
    }
  }

  @GetMapping("/modificar/{id}") // VISTA HTML
  public String modificar(@PathVariable int id, Model model) {
    model.addAttribute("historias",historiaService.getHistorias());
    model.addAttribute("actividad", ActividadService.getActividad(id).get());
    //obtener historias

    return "actividad/modificarActividad";

  }

  @PostMapping("/actualizar/{id}") // PERSISTENTE
  public String modificar(@PathVariable int id, @ModelAttribute("actividad") Actividad e, Model model) {
    e.setHistory(historiaService.getHistoria(e.getHistory().getId()).get());
    ActividadService.updateActividad(id, e);
    return "redirect:/scrum/actividades/index";
  }

  @GetMapping(path = "/eliminar/{id}")
  public String eliminar(@PathVariable int id) {    
    Actividad e = ActividadService.getActividad(id).get();    
    if (e == null) {
      return "redirect:/scrum/actividades/index";
    } else {
      Historia h=historiaService.getHistoria(e.getHistory().getId()).get();
      //eliminar primero la relación con el padre
      //para poder eliminar el hijo
      h.removeActividad(e);
      ActividadService.deleteActividad(id);
      return "redirect:/scrum/actividades/index";
    }
  }

}