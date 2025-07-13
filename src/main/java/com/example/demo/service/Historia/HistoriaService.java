package com.example.demo.service.Historia;

import java.util.Collection;
import java.util.Optional;

import com.example.demo.model.Historia;

public interface HistoriaService {
    public abstract void createHistoria(Historia historia);

    public abstract void updateHistoria(Integer id, Historia historia);

    public abstract void deleteHistoria(Integer id);

    public abstract Collection<Historia> getHistorias();

    public abstract Optional<Historia> getHistoria(Integer id);
}
