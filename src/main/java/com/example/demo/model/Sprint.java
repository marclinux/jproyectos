package com.example.demo.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

@Entity // This tells Hibernate to make a table out of this class
@Table(    name = "sprints"   )
public class Sprint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;     
    @NotBlank(message = "debe indicar el nombre del usuario.")     
    private String nombre;    
    private String descripcion; 
    @DateTimeFormat(pattern = "yyyy-MM-dd") 
    private Date fechaInicio;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaFinal;
    private Integer dias;    
    private int estado;
    
    //Relacion con Proyecto
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proyecto_id", nullable = false)
    private Proyecto proyecto;
    
    public Sprint(){
        
    }
    

    
    public Sprint(String nombre, String descripcion, Date fechaInicio, Date fechaFinal, Integer dias, int estado) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.dias = dias;
        this.estado = estado;
    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }      

    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }



    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }



    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }



    public Date getFechaFinal() {
        return fechaFinal;
    }
    
    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }


    public Integer getDias() {
        return dias;
    }
    
    public void setDias(Integer dias) {
        this.dias = dias;
    }



    public int getEstado() {
        return estado;
    }
    
    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    
    public Proyecto getProyecto() {
    	return proyecto;
    }
    
    public void setProyecto(Proyecto proyecto) {
    	this.proyecto = proyecto;
    }
    
}
