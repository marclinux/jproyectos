package com.example.demo.service.actividad;

import java.util.Collection;
import java.util.Optional;

import com.example.demo.model.Actividad;

public interface ActividadService {
    public abstract void createActividad(Actividad Actividad);

    public abstract void updateActividad(Integer id, Actividad Actividad);

    public abstract void deleteActividad(Integer id);

    public abstract Collection<Actividad> getActividads();

    public abstract Optional<Actividad> getActividad(Integer id);
    void calcularHorasTotales(Actividad actividad);
}
