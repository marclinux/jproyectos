package com.example.demo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;   
    private String nombre;
    private boolean estaActivo;

    @JsonManagedReference
    @OneToMany(
        mappedBy = "project",
        cascade = CascadeType.ALL,
        orphanRemoval = true,
        fetch = FetchType.EAGER
    )    
    private List<Historia> historias;
    
    
    public Proyecto() {
        estaActivo=true;
    }

    public Proyecto(String nombre, boolean estaActivo) {
        this.nombre = nombre;
        this.estaActivo = estaActivo;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public boolean isEstaActivo() {
        return estaActivo;
    }
    public void setEstaActivo(boolean estaActivo) {
        this.estaActivo = estaActivo;
    }

    public void addHistoria(Historia historia){
        historias.add(historia);
        historia.setProject(this);
        
    }
    public void removeHistoria(Historia historia){
        historias.remove(historia);        
        historia.setProject(null);
    }
    
}
