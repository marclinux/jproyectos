package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.config.authentication.PasswordEncoderParser;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import com.example.demo.dao.UsuarioRepository;
import com.example.demo.model.Usuario;

@SpringBootTest
class DemoApplicationTests {

	@Autowired 
	private UsuarioRepository usuario;
  
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Test
	void crearUsuarioTest() {
		usuario.save(new Usuario("admin",encoder.encode("admin"),"ADMIN"));
		assertTrue(usuario.save(new Usuario("admin",encoder.encode("admin"),"ADMIN")).getName().compareTo("admin")==0);

	}

	

}
