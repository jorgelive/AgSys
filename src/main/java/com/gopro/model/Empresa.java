/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gopro.model;

import javax.persistence.*;

@Entity
@Table(name="empresa")
public class Empresa {
    
    @Id
    @GeneratedValue
    private Long id;
     
    @Column(name="nombre", unique = true, nullable = false, length = 100)
    private String nombre;
    
    @Column(name="razonsocial", unique = true, nullable = false, length = 100)
    private String razonsocial;
    
    @Column(name="ruc", unique = true, nullable = false, length = 20)
    private String ruc;
     
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="pais_id")  
    private Pais pais;  
     
    public Empresa() {
         
    }

    public String getRazonsocial() {
        return razonsocial;
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

    public void setRazonsocial(String razonsocial) {
        this.razonsocial = razonsocial;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }
    
    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    
}

