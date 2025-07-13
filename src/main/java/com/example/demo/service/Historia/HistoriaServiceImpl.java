package com.example.demo.service.Historia;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.historia.HistoriaRepository;
import com.example.demo.model.Historia;

@Service
public class HistoriaServiceImpl implements HistoriaService{

    @Autowired          
  private HistoriaRepository HistoriaRepository;

    @Override
    public void createHistoria(Historia historia) {
        HistoriaRepository.save(historia);
        
    }

    @Override
    public void updateHistoria(Integer id, Historia historia) {
        HistoriaRepository.deleteById(id);
        HistoriaRepository.save(historia);        
    }

    @Override
    public void deleteHistoria(Integer id) {        
        HistoriaRepository.deleteById(id);        
    }

    @Override
    public Collection<Historia> getHistorias() {
        return (Collection<Historia>) HistoriaRepository.findAll();
    }

    @Override
    public Optional<Historia> getHistoria(Integer id) {
        return HistoriaRepository.findById(id);
    }    
}
