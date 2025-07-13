package com.example.demo.service.proyecto;

import java.util.Collection;
import java.util.Optional;

import com.example.demo.model.Proyecto;

public interface ProyectoService {
    public abstract void createProyecto(Proyecto empleado);

    public abstract void updateProyecto(Integer id, Proyecto empleado);

    public abstract void deleteProyecto(Integer id);

    public abstract Collection<Proyecto> getProyectos();

    public abstract Optional<Proyecto> getProyecto(Integer id);
}
