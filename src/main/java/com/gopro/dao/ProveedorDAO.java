/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gopro.dao;

import com.gopro.model.Proveedor;

/**
 *
 * @author jgomez
 */
public class ProveedorDAO extends AbstractDAO<Proveedor> {
    
    public ProveedorDAO(){
        super(Proveedor.class);
    }
    
}
