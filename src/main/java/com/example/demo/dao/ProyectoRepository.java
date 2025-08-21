package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Proyecto;

public interface ProyectoRepository extends JpaRepository<Proyecto,Integer> {
    
}
