package com.example.demo.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "historias")
public class Historia {
    @Id
    private int id;
    private String nombre;
    private String descripcion;
    private int peso;
    private int usuario;
    private int proyecto;
    private int estado;

    @JsonManagedReference
    @OneToMany(mappedBy = "history", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Actividad> actividades;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    private Proyecto project;

    public Historia() {
        actividades =new  HashSet<Actividad>();
    }

    public Historia(String nombre, String descripcion, int peso, int usuario, int proyecto, int estado) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.peso = peso;
        this.usuario = usuario;
        this.proyecto = proyecto;
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public int getProyecto() {
        return proyecto;
    }

    public void setProyecto(int proyecto) {
        this.proyecto = proyecto;
    }

    public void addActividad(Actividad actividad) {
        actividades.add(actividad);
        actividad.setHistory(this);

    }

    public void removeActividad(Actividad actividad) {
        actividades.remove(actividad);
        actividad.setHistory(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Historia))
            return false;
        return project.getId() == ((Historia) o).getProject().getId();
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public Proyecto getProject() {
        return project;
    }

    public void setProject(Proyecto project) {
        this.project = project;
    }

    public Set<Actividad> getActividades() {
        return actividades;
    }

    public void setActividades(Set<Actividad> actividades) {
        this.actividades = actividades;
    }

}
