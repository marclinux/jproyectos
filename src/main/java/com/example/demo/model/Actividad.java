package com.example.demo.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity // This tells Hibernate to make a table out of this class
@Table(    name = "actividad"   )
public class Actividad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
    /***Descripcion de la actividad*/
    private String descripcion; 
    @DateTimeFormat(pattern = "yyyy-MM-dd") 
    private Date fechaInicio;    
    private String horaInicio;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaFinal;
    private String horaFinal;
    /* La interrupcion en formato horas:minutos */
    private String interrupcion;    
    /* 1: Planeación, 2:Diseño, 3:Revisión de Diseño
     * 4:Codificación, 5:Revisión de Codificación
     * 6:Compilación, 7:Pruebas Unitarias, 8:postmortem
     */
    private int etapa;
    private String artefactos;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)     
    private Historia history;
    
    public Actividad(){
        history=new Historia();
    }      

    
    public Actividad(String descripcion, Date fechaInicio, String horaInicio, Date fechaFinal,
            String horaFinal, String interrupcion, int etapa, String artefactos) {        
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.horaInicio = horaInicio;
        this.fechaFinal = fechaFinal;
        this.horaFinal = horaFinal;
        this.interrupcion = interrupcion;
        this.etapa = etapa;
        this.artefactos = artefactos;
        history=new Historia();
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


    public String getHoraInicio() {
        return horaInicio;
    }


    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }


    public String getHoraFinal() {
        return horaFinal;
    }


    public void setHoraFinal(String horaFinal) {
        this.horaFinal = horaFinal;
    }


    public String getInterrupcion() {
        return interrupcion;
    }


    public void setInterrupcion(String interrupcion) {
        this.interrupcion = interrupcion;
    }


    public int getEtapa() {
        return etapa;
    }


    public void setEtapa(int etapa) {
        this.etapa = etapa;
    }

    public String getArtefactos() {
        return artefactos;
    }

    public void setArtefactos(String artefactos) {
        this.artefactos = artefactos;
    }   
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Actividad )) return false;
        return  history.getId()==((Actividad) o).getHistory().getId();
    }
 
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


    public Historia getHistory() {
        return history;
    }


    public void setHistory(Historia history) {
        this.history = history;
    }

    
}
