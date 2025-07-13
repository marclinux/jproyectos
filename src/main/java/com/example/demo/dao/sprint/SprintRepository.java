package com.example.demo.dao.sprint;

import org.springframework.data.repository.CrudRepository;
import com.example.demo.model.Sprint;
public interface SprintRepository extends CrudRepository<Sprint, Integer> {    
}