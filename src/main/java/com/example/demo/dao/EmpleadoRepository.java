package com.example.demo.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Empleado;

// This will be AUTO IMPLEMENTED by 
//Spring into a Bean called EmpleadoRepository
// CRUD refers Create, Read, Update, Delete

public interface EmpleadoRepository extends CrudRepository<Empleado, Integer> {

    @Query("from Empleado")
    Iterable<Empleado> findAllByHQL();

    @Query("from Empleado e where e.id = ?1")
    Optional<Empleado> findByIdHQL(int id);

}