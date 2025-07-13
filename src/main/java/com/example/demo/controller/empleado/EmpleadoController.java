package com.example.demo.controller.empleado;

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

import com.example.demo.model.Beneficiario;
import com.example.demo.model.Empleado;
import com.example.demo.service.empleado.EmpleadoService;

@Controller // This means that this class is a Controller
@RequestMapping("/empleado") // This means URL's start with /empleado (after Application path)
public class EmpleadoController {
  @Autowired          
  private EmpleadoService empleadoService;

  @PostMapping(path="/add") // Map ONLY POST Requests
  public @ResponseBody String addNewEmpleado (@RequestParam String fullname
      , @RequestParam String apPaterno, 
      @RequestParam String apMaterno, 
      @RequestParam String rfc, 
      @RequestParam String edoCivil,@RequestParam String sexo) {
    // @ResponseBody means the returned String is the response, not a view name
    // @RequestParam means it is a parameter from the GET or POST request

    Empleado n = new Empleado();
    n.setNombre(fullname);
    n.setApPaterno(apPaterno);
    n.setApMaterno(apMaterno);
    n.setEstadoCivil(edoCivil);
    n.setSexo(sexo);    
    n.setRFC(rfc);
    Beneficiario b=new Beneficiario("levi","juarez","hernandez","h",25,1);    
    n.addBeneficiario(b);    
    empleadoService.createEmpleado(n);
    return "Saved"; 
  }


  @PostMapping(path="/addFrom") // Map ONLY POST Requests
  public String addNewEmpleado (@ModelAttribute("empleado")Empleado e) {    
    empleadoService.createEmpleado(e);
    return "redirect:/empleado/index";
  }

  @GetMapping("/registrar") // VISTA HMTL
  public String   agregar (Model model) {
    Empleado n = new Empleado();
    model.addAttribute("empleado", n);   
    return "registrarEmpleado"; 
  }

  @GetMapping("/modificar/{id}") //VISTA HTML
  public String   modificar (@PathVariable int id,Model model) {
    model.addAttribute("empleado", empleadoService.getEmpleado(id).get());
    return "prueba/modificarEmpleado";  
    
  }

  @PostMapping("/actualizar/{id}") //PERSISTENTE
  public String   modificar (@PathVariable int id,@ModelAttribute("empleado")Empleado e,Model model) {
    empleadoService.createEmpleado(e);
    return "redirect:/empleado/index";    
  }

  @GetMapping(path="/all")
  public @ResponseBody Iterable<Empleado> getAll() {
    // This returns a JSON or XML with the users
    return empleadoService.getEmpleados();
  }


  @GetMapping("/index")
public String showUserList(Model model) {
    model.addAttribute("empleados", empleadoService.getEmpleados());
    
    return "index";
}



 

  @GetMapping("/one/{id}")
  public @ResponseBody Optional<Empleado> getById(@PathVariable int id) {
    // This returns a JSON or XML with the users
    if(id<100)
        return empleadoService.getEmpleado(id);
    else
        throw new EmpleadoNotfoundException();
  }

  
  @PutMapping(path = "/update/{id}")
  public @ResponseBody String updateEmpleado( @PathVariable int id,
  @RequestParam String fullname
  , @RequestParam String apPaterno, 
  @RequestParam String apMaterno, 
  @RequestParam String rfc, 
  @RequestParam String edoCivil,
  @RequestParam String sexo){    

    Empleado n = empleadoService.getEmpleado(id).get();    
    if(n==null){
      throw new EmpleadoNotfoundException();      
    }else{
      n.setNombre(fullname);
      n.setApPaterno(apPaterno);
      n.setApMaterno(apMaterno);
      n.setEstadoCivil(edoCivil);
      n.setSexo(sexo);    
      n.setRFC(rfc);
      empleadoService.updateEmpleado(id, n);
      return "Actualizado";
    }
  }



  @GetMapping(path= "/eliminar/{id}")
  public String eliminarEmpleado(@PathVariable int id){
    Empleado e=empleadoService.getEmpleado(id).get();    
    if(e==null){
      return "redirect:empleado/index";
    }else{
          empleadoService.deleteEmpleado(id);
          return "redirect:/empleado/index";
    }
  }


  @DeleteMapping(path= "/delete/{id}")
  public @ResponseBody String deleteEmpleado(@PathVariable int id){
    Empleado e=empleadoService.getEmpleado(id).get();
    if(e==null){
      return "error";
    }else{
      empleadoService.deleteEmpleado(id);
          return "eliminado";
    }
  }

}