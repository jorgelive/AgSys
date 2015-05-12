/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gopro.desktop;

import com.gopro.model.Pais;
import com.gopro.dao.PaisDAO;
import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.table.*;

/**
 *
 * @author jgomez
 */
public class PaisJframe extends javax.swing.JFrame {

    private final PaisDAO paisDAO = new PaisDAO();
    
    DefaultTableModel dtm = new DefaultTableModel() {

        @Override
        public boolean isCellEditable(int row, int column)
        {
            if(column == 0){
                return false;
            }else{
                return true;
            }
        }
        

        @Override
        public void setValueAt(Object value, int row, int col) {
            
            super.setValueAt(value,row,col);
            
            fireTableCellUpdated(row, col);
            
            Long llave = (Long) dtm.getValueAt(row, 0);
            
            String valor1 = (String) dtm.getValueAt(row, 1);
            
            String valor2 = (String) dtm.getValueAt(row, 2);
            
            if(!"".equals((String) valor1) && !"".equals((String) valor2) && col != 0){
                System.out.println("Operando");
                
                if(llave != null){
                    Pais pais = paisDAO.find(llave);
                    pais.setNombre((String) valor1);
                    pais.setNacionalidad((String) valor2);
                    if(!paisDAO.update(pais)){
                        JOptionPane.showMessageDialog(null, "Error: No se actualizar, verifique información duplicada.");
                    }
                }else{
                    Pais pais = new Pais();
                    pais.setNombre((String) valor1);
                    pais.setNacionalidad((String) valor2);
                    pais = paisDAO.create(pais);
                    if(pais != null){
                        super.setValueAt(pais.getId(),row,0);
                    }else{
                        JOptionPane.showMessageDialog(null, "Error: No se insertar el registro, verifique información duplicada.");
                    }
                    
                }
            }
        }
    };

    private final List<Long> removedRows = new ArrayList<>();

    
    
    /**
     * Creates new form Pais
     */
    public PaisJframe() {
        initComponents();
        LlenarTabla();
    }
    
    private void LlenarTabla(){
        
        this.dtm.addColumn("Id");
        this.dtm.addColumn("Nombre");
        this.dtm.addColumn("Nacionalidad");
        
        
        for (Pais pais : this.paisDAO.findAll()){
            this.dtm.addRow(new Object[]{pais.getId(), pais.getNombre(),pais.getNacionalidad()});
        }
        
        this.paisTabla.setModel(this.dtm);
        
        TableColumn idColumn = this.paisTabla.getColumnModel().getColumn(0);
        TableColumn nombreColumn = this.paisTabla.getColumnModel().getColumn(1);
        TableColumn nacionalidadColumn = this.paisTabla.getColumnModel().getColumn(2);
        idColumn.setPreferredWidth(10);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        paisTabla = new javax.swing.JTable();
        AgregarBoton = new javax.swing.JButton();
        BorrarBoton = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setTitle("Contactos");

        paisTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        paisTabla.setCellSelectionEnabled(true);
        jScrollPane1.setViewportView(paisTabla);

        AgregarBoton.setText("Nuevo");
        AgregarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AgregarBotonActionPerformed(evt);
            }
        });

        BorrarBoton.setText("Borrar");
        BorrarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BorrarBotonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(AgregarBoton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BorrarBoton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AgregarBoton)
                    .addComponent(BorrarBoton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AgregarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgregarBotonActionPerformed
        
        this.dtm.addRow(new Object[]{null, "", ""});

    }//GEN-LAST:event_AgregarBotonActionPerformed

    private void BorrarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BorrarBotonActionPerformed
        
        int botonDialogo = JOptionPane.YES_NO_OPTION;
        int resultadoDialogo = JOptionPane.showConfirmDialog (null, "Esta seguro que desea eliminar el registro?","Advertencia",botonDialogo );
        
        if(resultadoDialogo == JOptionPane.NO_OPTION){
            return;
        }
        
        int[] rows = this.paisTabla.getSelectedRows();
        for(int i=0;i<rows.length;i++){
            Long llave = (Long)this.dtm.getValueAt(rows[i]-i, 0);
            if(llave != null){
                this.removedRows.add(llave);
                
                Pais pais = this.paisDAO.find(llave);
                
                if(this.paisDAO.delete(pais)){
                    System.out.println("Se ha borrado de la base de datos el registro "+((Long) dtm.getValueAt(rows[i]-i, 0)));
                    this.dtm.removeRow(rows[i]-i);
                }else{
                    JOptionPane.showMessageDialog(null, "Error: No se puedo eliminar, verifique que no tenga registros dependientes.");
                }
            
            }else{
                this.dtm.removeRow(rows[i]-i);
            }
        }
    }//GEN-LAST:event_BorrarBotonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PaisJframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PaisJframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PaisJframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PaisJframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PaisJframe().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AgregarBoton;
    private javax.swing.JButton BorrarBoton;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable paisTabla;
    // End of variables declaration//GEN-END:variables
}
