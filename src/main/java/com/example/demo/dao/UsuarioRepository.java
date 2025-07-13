package com.example.demo.dao;



import org.springframework.data.repository.CrudRepository;


import com.example.demo.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

    Usuario findByEmail(String email);
}