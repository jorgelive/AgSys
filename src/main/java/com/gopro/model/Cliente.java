/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gopro.model;

import javax.persistence.*;

@Entity
@Table(name="cliente")
public class Cliente {

    @Id
    @GeneratedValue
    private Long id;
     
    @Column(name="nombre", unique = true, nullable = false, length = 100)
    private String nombre;
    
    @Column(name="direccion", unique = true, nullable = false, length = 100)
    private String direccion;
    
    @Column(name="montomonto", unique = true, nullable = false)
    private Integer montocredito;

     
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="empresa_id")  
    private Empresa empresa;  
     
    public Cliente() {
         
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
    
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getMontocredito() {
        return montocredito;
    }

    public void setMontocredito(Integer montocredito) {
        this.montocredito = montocredito;
    }
    
    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

}

