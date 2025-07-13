package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name = "tblbeneficiarios")
public class Beneficiario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer clave;
    private String nombre;
    private String apPaterno;
    private String apMaterno;
    private String sexo;
    private int edad;
    private int tipoRelacion;
    
    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)     
    private Empleado empleado;
    
    public Beneficiario() {
    }
    public Beneficiario(String nombre, String apPaterno, String apMaterno, String sexo, int edad, int tipoRelacion) {
        this.nombre = nombre;
        this.apPaterno = apPaterno;
        this.apMaterno = apMaterno;
        this.sexo = sexo;
        this.edad = edad;
        this.tipoRelacion = tipoRelacion;        
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApPaterno() {
        return apPaterno;
    }
    public void setApPaterno(String apPaterno) {
        this.apPaterno = apPaterno;
    }
    public String getApMaterno() {
        return apMaterno;
    }
    public void setApMaterno(String apMaterno) {
        this.apMaterno = apMaterno;
    }
    public String getSexo() {
        return sexo;
    }
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }
    public int getTipoRelacion() {
        return tipoRelacion;
    }

    
    public Integer getClave() {
        return clave;
    }
    public void setClave(Integer clave) {
        this.clave = clave;
    }
    public Empleado getEmpleado() {
        return empleado;
    }
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
    public void setTipoRelacion(int tipoRelacion) {
        this.tipoRelacion = tipoRelacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Beneficiario )) return false;
        return clave != null && clave.equals(((Beneficiario) o).getClave());
    }
 
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
