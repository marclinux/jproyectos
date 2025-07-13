package com.example.demo.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.demo.dao.RoleRepository;
import com.example.demo.dao.UsuarioRepository;
import com.example.demo.model.Rol;
import com.example.demo.model.Usuario;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    private UsuarioRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UsuarioServiceImpl(UsuarioRepository userRepository,  RoleRepository roleRepository,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(Usuario user) {
        Usuario usuario = new Usuario();        
        usuario.setName(user.getName());
        usuario.setEmail(user.getEmail());
        usuario.setPassword(passwordEncoder.encode(user.getPassword()));

        Rol role = roleRepository.findByName("ROLE_ADMIN");
        if (role == null) {
            role = checkRoleExist();
        }
        usuario.setRoles(Arrays.asList(role));
        userRepository.save(usuario);
    }

    @Override
    public Usuario findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<Usuario> findAllUsers() {
        List<Usuario> users = (List<Usuario>) userRepository.findAll();
       
        return users;
        //return users.stream().map((user) -> mapToUser(user)).collect(Collectors.toList());
    }

    

    private Rol checkRoleExist() {
        Rol role = new Rol();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }

}
