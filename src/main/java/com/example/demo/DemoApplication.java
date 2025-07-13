package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.dao.BeneficiarioRepository;
import com.example.demo.dao.EmpleadoRepository;

@SpringBootApplication
public class DemoApplication implements ApplicationRunner {

	@Autowired
	BeneficiarioRepository beneficiario;
	@Autowired
	EmpleadoRepository empleado;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments arg0) throws Exception {
		System.out.println("Hello World from Application Runner");
	}

}
