package com.example.demo.service;



import java.util.List;

import com.example.demo.model.Usuario;

public interface UsuarioService {
    void saveUser(Usuario user);

    Usuario findUserByEmail(String email);

    List<Usuario> findAllUsers();
}