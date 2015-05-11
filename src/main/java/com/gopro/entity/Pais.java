/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gopro.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name="pais")

public class Pais {
    
    @Id
    @GeneratedValue
    private Long id;
     
    @Column(name="nombre", unique = true, nullable = false, length = 100)
    private String nombre;
     
    @Column(name="nacionalidad", unique = true, nullable = false, length = 100)
    private String nacionalidad;

    public Set<Paisasociacion> getPaisasociaciones() {
        return paisasociaciones;
    }

    public void setPaisasociaciones(Set<Paisasociacion> paisasociaciones) {
        this.paisasociaciones = paisasociaciones;
    }
    
    @ManyToMany(cascade = { CascadeType.ALL })  
    @JoinTable(name = "pais_paisasociacion", joinColumns = { @JoinColumn(name = "pais_id") }, inverseJoinColumns = { @JoinColumn(name = "paisasociacion_id") })  
    private Set<Paisasociacion> paisasociaciones = new HashSet<Paisasociacion>();  
     
    public Pais() {
         
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
     
    
}

