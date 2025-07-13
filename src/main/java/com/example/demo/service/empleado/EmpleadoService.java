package com.example.demo.service.empleado;

import java.util.Collection;
import java.util.Optional;

import com.example.demo.model.Empleado;

public interface EmpleadoService {
    public abstract void createEmpleado(Empleado empleado);

    public abstract void updateEmpleado(Integer id, Empleado empleado);

    public abstract void deleteEmpleado(Integer id);

    public abstract Collection<Empleado> getEmpleados();

    public abstract Optional<Empleado> getEmpleado(Integer id);
}
