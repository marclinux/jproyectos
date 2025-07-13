package com.example.demo.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Beneficiario;

// This will be AUTO IMPLEMENTED by 
//Spring into a Bean called BeneficiarioRepository
// CRUD refers Create, Read, Update, Delete

public interface BeneficiarioRepository extends CrudRepository<Beneficiario, Integer> {
    Beneficiario findByClave(Integer clave);

    @Query("from Beneficiario")
    Iterable<Beneficiario> findAllByHQL();

    @Query("from Beneficiario e where e.id = ?1")
    Optional<Beneficiario> findByIdHQL(int id);
}