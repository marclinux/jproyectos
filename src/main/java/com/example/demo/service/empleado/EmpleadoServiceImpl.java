package com.example.demo.service.empleado;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.EmpleadoRepository;
import com.example.demo.model.Empleado;

@Service
public class EmpleadoServiceImpl implements EmpleadoService{

    @Autowired          
  private EmpleadoRepository empleadoRepository;

    @Override
    public void createEmpleado(Empleado empleado) {
        empleadoRepository.save(empleado);
        
    }

    @Override
    public void updateEmpleado(Integer id, Empleado empleado) {
        empleadoRepository.deleteById(id);
        empleadoRepository.save(empleado);        
    }

    @Override
    public void deleteEmpleado(Integer id) {        
        empleadoRepository.deleteById(id);        
    }

    @Override
    public Collection<Empleado> getEmpleados() {
        return (Collection<Empleado>) empleadoRepository.findAll();
    }

    @Override
    public Optional<Empleado> getEmpleado(Integer id) {
        return empleadoRepository.findById(id);
    }


    
}
