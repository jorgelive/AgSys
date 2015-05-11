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
@Table(name="paisasociacion")

public class Paisasociacion {
    
    @Id
    @GeneratedValue
    private Long id;
     
    @Column(name="nombre", unique = true, nullable = false, length = 100)
    private String nombre;
    
    @ManyToMany(mappedBy = "paisasociaciones")  
    private Set<Pais> paises = new HashSet<Pais>();  
  
    public Paisasociacion() {
         
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

    public Set<Pais> getPaises() {
        return paises;
    }

    public void setPaises(Set<Pais> paises) {
        this.paises = paises;
    }

    
    
}

