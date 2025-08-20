package com.example.demo.dao.sprint;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Sprint;
public interface SprintRepository extends JpaRepository<Sprint, Integer> {    
	List<Sprint> findByProyectoId(Integer proyectoId);
}