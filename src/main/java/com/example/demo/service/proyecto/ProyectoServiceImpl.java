package com.example.demo.service.proyecto;

import java.util.Collection;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.dao.ProyectoRepository;
import com.example.demo.model.Proyecto;

@Service
public class ProyectoServiceImpl implements ProyectoService{

    @Autowired          
  private ProyectoRepository proyectoRepository;

    @Override
    public void createProyecto(Proyecto proyecto) {
        proyectoRepository.save(proyecto);
    }

    @Override
    public void updateProyecto(Integer id, Proyecto proyecto) {
        proyectoRepository.deleteById(id);
        proyectoRepository.save(proyecto);        
    }

    @Override
    public void deleteProyecto(Integer id) {        
        proyectoRepository.deleteById(id);        
    }

    @Override
    public Collection<Proyecto> getProyectos() {
        return (Collection<Proyecto>) proyectoRepository.findAll();
    }

    @Override
    public Optional<Proyecto> getProyecto(Integer id) {
        return proyectoRepository.findById(id);
    }    
}
