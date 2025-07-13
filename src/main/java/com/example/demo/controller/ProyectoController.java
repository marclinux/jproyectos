package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.controller.empleado.EmpleadoNotfoundException;
import com.example.demo.model.Proyecto;
import com.example.demo.service.proyecto.ProyectoService;

@Controller 
@RequestMapping("/scrum/proyectos") // This means URL's start with /admin/proyecto
public class ProyectoController {
  @Autowired
  private ProyectoService proyectoService;

  @PostMapping(path = "/add") // Map ONLY POST Requests
  public @ResponseBody String addNewProyecto(@RequestParam String nombre) {
    Proyecto p = new Proyecto(nombre, true);
    proyectoService.createProyecto(p);
    return "Saved";
  }

  @PostMapping(path = "/addFrom") // Map ONLY POST Requests
  public String addNewEmpleado(@ModelAttribute("proyecto") Proyecto e) {
    proyectoService.createProyecto(e);
    return "redirect:/scrum/proyectos/index";
  }

  @GetMapping("/registrar") // VISTA HMTL
  public String agregar(Model model) {
    Proyecto proyecto = new Proyecto();
    model.addAttribute("proyecto", proyecto);
    return "proyecto/registrarProyecto";
  }

  @GetMapping("/modificar/{id}") // VISTA HTML
  public String modificar(@PathVariable int id, Model model) {
    model.addAttribute("proyecto", proyectoService.getProyecto(id).get());
    return "proyecto/modificarProyecto";

  }

  @PostMapping("/actualizar/{id}") // PERSISTENTE
  public String modificar(@PathVariable int id, @ModelAttribute("proyecto") Proyecto e, Model model) {
    proyectoService.updateProyecto(id,e);
    return "redirect:/scrum/proyectos/index";
  }


  @PutMapping(path = "/update/{id}")
  public @ResponseBody String updateEmpleado(@PathVariable int id,
      @RequestParam String nombre) {

    Proyecto n = proyectoService.getProyecto(id).get();
    if (n == null) {
      throw new EmpleadoNotfoundException();
    } else {
      n.setNombre(nombre);
      proyectoService.updateProyecto(id, n);
      return "Actualizado";
    }
  }

  @GetMapping(path = "/all")
  public @ResponseBody Iterable<Proyecto> getAll() {
    // This returns a JSON or XML with the users
    return proyectoService.getProyectos();
  }

  @GetMapping("/index")
  public String showUserList(Model model) {
    model.addAttribute("proyectos", proyectoService.getProyectos());
    return "proyecto/index";
  }

  @GetMapping("/one/{id}")
  public @ResponseBody Optional<Proyecto> getById(@PathVariable int id) {
    // This returns a JSON or XML with the users
    if (id < 100)
      return proyectoService.getProyecto(id);
    else
      throw new EmpleadoNotfoundException();
  }



  @GetMapping(path = "/eliminar/{id}")
  public String eliminarEmpleado(@PathVariable int id) {
    Proyecto e = proyectoService.getProyecto(id).get();
    if (e == null) {
      return "redirect:/scrum/proyectos/index";
    } else {
      proyectoService.deleteProyecto(id);
      return "redirect:/scrum/proyectos/index";
    }
  }

  @DeleteMapping(path = "/delete/{id}")
  public @ResponseBody String deleteEmpleado(@PathVariable int id) {
    Proyecto e = proyectoService.getProyecto(id).get();
    if (e == null) {
      return "error";
    } else {
      proyectoService.deleteProyecto(id);
      return "eliminado";
    }
  }
}