package com.example.demo.dao;



import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Rol;

public interface RoleRepository extends CrudRepository<Rol, Integer> {

    Rol findByName(String name);
}