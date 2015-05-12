/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gopro.desktop;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

/**
 *
 * @author jgomez
 */
public class AutenticacionJdialog extends JDialog {
    
    private final JLabel labelNombre = new JLabel("Nombre de usuario");
    private final JLabel labelContrasena = new JLabel("Contraseña");

    private final JTextField textoNombre = new JTextField(15);
    private final JPasswordField passwordContrasena = new JPasswordField();

    private final JButton botonIngresar = new JButton("Ingresar");
    private final JButton botonCancelar = new JButton("Cancelar");

    private final JLabel labelStatus = new JLabel(" ");

    public AutenticacionJdialog() {
        this(null, true);
    }

    public AutenticacionJdialog(final JFrame parent, boolean modal) {
        super(parent, modal);

        JPanel jPanel3 = new JPanel(new GridLayout(2, 1));
        jPanel3.add(labelNombre);
        jPanel3.add(labelContrasena);

        JPanel jPanel4 = new JPanel(new GridLayout(2, 1));
        jPanel4.add(textoNombre);
        jPanel4.add(passwordContrasena);


        JPanel jPanel1 = new JPanel();
        jPanel1.add(jPanel3);
        jPanel1.add(jPanel4);

        JPanel jPanel2 = new JPanel();
        jPanel2.add(botonIngresar);
        jPanel2.add(botonCancelar);

        JPanel p5 = new JPanel(new BorderLayout());
        p5.add(jPanel2, BorderLayout.CENTER);
        p5.add(labelStatus, BorderLayout.NORTH);
        labelStatus.setForeground(Color.RED);
        labelStatus.setHorizontalAlignment(SwingConstants.CENTER);

        setLayout(new BorderLayout());
        add(jPanel1, BorderLayout.CENTER);
        add(p5, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        addWindowListener(new WindowAdapter() {  
            @Override
            public void windowClosing(WindowEvent e) {  
                System.exit(0);  
            }  
        });


        botonIngresar.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if ("ap".equals(textoNombre.getText())
                        && "1234".equals(passwordContrasena.getText())) {
                    parent.setVisible(true);
                    setVisible(false);
                } else {
                    labelStatus.setText("Usuario o contraseña incorrectos");
                }
            }
        });
        
        botonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                parent.dispose();
                System.exit(0);
            }
        });
    }
    
}
