/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gopro.dao;

import com.gopro.model.Empresa;

/**
 *
 * @author jgomez
 */
public class EmpresaDAO extends AbstractDAO<Empresa> {
    
    public EmpresaDAO(){
        super(Empresa.class);
    }
    
}
