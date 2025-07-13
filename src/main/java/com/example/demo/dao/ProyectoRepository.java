package com.example.demo.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Proyecto;

public interface ProyectoRepository extends CrudRepository<Proyecto,Integer> {
    
}
