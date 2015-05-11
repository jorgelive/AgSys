/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gopro.entity;

import javax.persistence.*;

@Entity
@Table(name="contacto")
public class Contacto {
    
    @Id
    @GeneratedValue
    private Long id;
     
    @Column(name="nombre", unique = true, nullable = false, length = 100)
    private String nombre;
     
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="pais_id")  
    private Pais pais;  
     
    public Contacto() {
         
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

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }
    


     
    
}

