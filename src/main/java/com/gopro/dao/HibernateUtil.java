/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gopro.dao;

import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
 
public class HibernateUtil {
 
    private static final SessionFactory sessionFactory;
   
    static {
        try {            
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception.
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
   
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
