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
        SprintRepository.deleteById(id);
        SprintRepository.save(Sprint);        
    }

    @Override
    public void deleteSprint(Integer id) {        
        SprintRepository.deleteById(id);        
    }

    @Override
    public Collection<Sprint> getSprints() {
        return (Collection<Sprint>) SprintRepository.findAll();
    }

    @Override
    public Optional<Sprint> getSprint(Integer id) {
        return SprintRepository.findById(id);
    }    
}
