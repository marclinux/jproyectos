package com.example.demo.service.actividad;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.actividad.ActividadRepository;
import com.example.demo.model.Actividad;

@Service
public class ActividadServiceImpl implements ActividadService{

    @Autowired          
  private ActividadRepository actividadRepository;

    @Override
    public void createActividad(Actividad Actividad) {
        actividadRepository.save(Actividad);
        
    }

    @Override
    public void updateActividad(Integer id, Actividad Actividad) {
        actividadRepository.deleteById(id);
        actividadRepository.save(Actividad);
    }

    @Override
    public void deleteActividad(Integer id) {        
        actividadRepository.deleteById(id);        
    }

    @Override
    public Collection<Actividad> getActividads() {
        return (Collection<Actividad>) actividadRepository.findAll();
    }

    @Override
    public Optional<Actividad> getActividad(Integer id) {
        return actividadRepository.findById(id);
    }    
}
