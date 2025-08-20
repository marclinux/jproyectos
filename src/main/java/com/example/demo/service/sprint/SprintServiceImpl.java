package com.example.demo.service.sprint;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.sprint.SprintRepository;
import com.example.demo.model.Sprint;

@Service
public class SprintServiceImpl implements SprintService{

    @Autowired          
  private SprintRepository SprintRepository;

    @Override
    public void createSprint(Sprint Sprint) {
        SprintRepository.save(Sprint);
        
    }

    @Override
    public void updateSprint(Integer id, Sprint Sprint) {
    	 Optional<Sprint> existing = SprintRepository.findById(id);
         if (existing.isPresent()) {
             Sprint s = existing.get();
             s.setNombre(Sprint.getNombre());
             s.setDescripcion(Sprint.getDescripcion());
             s.setFechaInicio(Sprint.getFechaInicio());
             s.setFechaFinal(Sprint.getFechaFinal());
             s.setDias(Sprint.getDias());
             s.setEstado(Sprint.getEstado());
             s.setProyecto(Sprint.getProyecto()); // importante!
             SprintRepository.save(s);
         }      
    }

    @Override
    public void deleteSprint(Integer id) {        
        SprintRepository.deleteById(id);        
    }

    @Override
    public Collection<Sprint> getSprints() {
        return SprintRepository.findAll();
    }

    @Override
    public Optional<Sprint> getSprint(Integer id) {
        return SprintRepository.findById(id);
    }  
    
    @Override
    public Collection<Sprint> getSprintsByProyecto(Integer proyectoId) {
        return SprintRepository.findByProyectoId(proyectoId);
    }
    
}
