package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity // This tells Hibernate to make a table out of this class
@Table(    name = "empleados"   )
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;   
    @Column(name="nombre", nullable=false, length=55)
    private String nombre;
    @Column(name="apPaterno", nullable=false, length=55)
    private String apPaterno;
    @Column(name="apMaterno", nullable=false, length=55)
    private String apMaterno;
    @Column(name="rfc", nullable=false, length=55)
    private String RFC;
    @Column(name="edoCivil", nullable=false, length=1)
    private String estadoCivil;
    @Column(name="sexo", nullable=false, length=1)
    private String sexo;

    
    @JsonManagedReference
    @OneToMany(
        mappedBy = "empleado",
        cascade = CascadeType.ALL,
        orphanRemoval = true,
        fetch = FetchType.EAGER
    )    
    private List<Beneficiario> beneficiarios;

    public Empleado(){
        beneficiarios=new ArrayList<Beneficiario>();
    }
    public Empleado(String nombre, String apPaterno, String apMaterno, String RFC, String estadoCivil, String sexo) {
        this.nombre = nombre;
        this.apPaterno = apPaterno;
        this.apMaterno = apMaterno;
        this.RFC = RFC;
        this.estadoCivil = estadoCivil;
        this.sexo = sexo;
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

    public String getRFC() {
        return RFC;
    }

    public void setRFC(String RFC) {
        this.RFC = RFC;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    

    public void addBeneficiario(Beneficiario beneficiario){
        beneficiarios.add(beneficiario);
        beneficiario.setEmpleado(this);
        
    }
    public void removeBeneficiario(Beneficiario beneficiario){
        beneficiarios.remove(beneficiario);        
        beneficiario.setEmpleado(null);
    }

    public List<Beneficiario> getBeneficiarios() {
        return beneficiarios;
    }

    public void setBeneficiarios(List<Beneficiario> beneficiarios) {
        this.beneficiarios = beneficiarios;
    }
    @Override
    public String toString() {
        return  nombre + " " + apPaterno + " " + apMaterno + " " + RFC + " " + estadoCivil + " " + sexo;
    }
}
