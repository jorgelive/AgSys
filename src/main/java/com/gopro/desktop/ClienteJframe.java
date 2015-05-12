/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gopro.desktop;

import com.gopro.model.Empresa;
import com.gopro.model.Cliente;
import com.gopro.dao.EmpresaDAO;
import com.gopro.dao.ClienteDAO;
import java.util.*;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.*;

/**
 *
 * @author jgomez
 */
public class ClienteJframe extends javax.swing.JFrame {

    private final ClienteDAO clienteDAO = new ClienteDAO();
    
    private final EmpresaDAO empresaDAO = new EmpresaDAO();
    
    private final TreeMap <Long, String> empresaMap = new TreeMap<>();
    
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
            
            Integer valorInteger3 = 0;
            
            Long llave = (Long) dtm.getValueAt(row, 0);
            
            String valor1 = (String) dtm.getValueAt(row, 1);
            
            String valor2 = (String) dtm.getValueAt(row, 2);
            
            String valor3 = (String) dtm.getValueAt(row, 3);
 
            String valor4 = (String) dtm.getValueAt(row, 4);
            
            if(!"".equals((String) valor1) && !"".equals(valor2) && !"".equals(valor3) && !"".equals(valor4) && col != 0){
                System.out.println("Operando");
                
                try{
                    valorInteger3 = Integer.parseInt(valor3);
                }catch(NumberFormatException e){
                    JOptionPane.showMessageDialog(null, "El valor del monto de crédito es inválido.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                
                if(llave != null){
                    Cliente cliente = clienteDAO.find(llave);
                    cliente.setNombre((String) valor1);
                    cliente.setDireccion((String) valor2);
                    
                    cliente.setMontocredito(valorInteger3);
                    cliente.setEmpresa(empresaDAO.find(getKeyForValue((String) valor4)));
                    if(!clienteDAO.update(cliente)){
                        JOptionPane.showMessageDialog(null, "No se actualizar, verifique información duplicada.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    Cliente cliente = new Cliente();
                    cliente.setNombre((String) valor1);
                    cliente.setDireccion((String) valor2);
                    cliente.setMontocredito(valorInteger3);
                    cliente.setEmpresa(empresaDAO.find(getKeyForValue((String) valor4)));
                    cliente = clienteDAO.create(cliente);
                    if(cliente != null){
                        super.setValueAt(cliente.getId(),row,0);
                    }else{
                        JOptionPane.showMessageDialog(null, "No se insertar el registro, verifique información duplicada.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    };

    private final List<Long> removedRows = new ArrayList<>();

    
    
    /**
     * Creates new form Empresa
     */
    public ClienteJframe() {
        initComponents();
        LlenarTabla();
    }
    
    private Long getKeyForValue(String value) {
        for (Map.Entry<Long, String> entry : this.empresaMap.entrySet()) {
             if (entry.getValue().equals(value)) {
                 return entry.getKey();
             }
         }
        return new Long(0);
    }
    
    private void LlenarTabla(){
        
        for (Empresa empresa : this.empresaDAO.findAll()){
            this.empresaMap.put(empresa.getId(), empresa.getNombre());
        }
        
        this.dtm.addColumn("Id");
        this.dtm.addColumn("Nombre");
        this.dtm.addColumn("Dirección");
        this.dtm.addColumn("Monto de crédito");
        this.dtm.addColumn("Empresa");
        
        
        for (Cliente cliente : this.clienteDAO.findAll()){
            this.dtm.addRow(new Object[]{cliente.getId(), cliente.getNombre(), cliente.getDireccion(), cliente.getMontocredito().toString(), cliente.getEmpresa().getNombre()});
        }
        
        this.clienteTabla.setModel(this.dtm);
        
        TableColumn idColumn = this.clienteTabla.getColumnModel().getColumn(0);
        TableColumn nombreColumn = this.clienteTabla.getColumnModel().getColumn(1);
        TableColumn direccionColumn = this.clienteTabla.getColumnModel().getColumn(2);
        TableColumn montoCreditoColumn = this.clienteTabla.getColumnModel().getColumn(3);
        TableColumn empresaColumn = this.clienteTabla.getColumnModel().getColumn(4);

        
        JComboBox comboBox = new JComboBox();
        
        for (Empresa empresa : this.empresaDAO.findAll()){
            comboBox.addItem(empresa.getNombre());
        }
        idColumn.setPreferredWidth(10);
        empresaColumn.setCellEditor(new DefaultCellEditor(comboBox));
    
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
        clienteTabla = new javax.swing.JTable();
        AgregarBoton = new javax.swing.JButton();
        BorrarBoton = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setTitle("Clientes");

        clienteTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        clienteTabla.setCellSelectionEnabled(true);
        jScrollPane1.setViewportView(clienteTabla);

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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE)
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AgregarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgregarBotonActionPerformed
        
        this.dtm.addRow(new Object[]{null, "", "", "", ""});

    }//GEN-LAST:event_AgregarBotonActionPerformed

    private void BorrarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BorrarBotonActionPerformed
        
        int botonDialogo = JOptionPane.YES_NO_OPTION;
        int resultadoDialogo = JOptionPane.showConfirmDialog (null, "Esta seguro que desea eliminar el registro?", "Advertencia",botonDialogo );
        
        if(resultadoDialogo == JOptionPane.NO_OPTION){
            return;
        }
        
        int[] rows = this.clienteTabla.getSelectedRows();
        for(int i=0;i<rows.length;i++){
            Long llave = (Long)this.dtm.getValueAt(rows[i]-i, 0);
            if(llave != null){
                this.removedRows.add(llave);
                
                Cliente cliente = this.clienteDAO.find(llave);
                
                if(this.clienteDAO.delete(cliente)){
                    System.out.println("Se ha borrado de la base de datos el registro "+((Long) dtm.getValueAt(rows[i]-i, 0)));
                    this.dtm.removeRow(rows[i]-i);
                }else{
                    JOptionPane.showMessageDialog(null, "No se puedo eliminar, verifique que no tenga registros dependientes.", "Error", JOptionPane.ERROR_MESSAGE);
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
            java.util.logging.Logger.getLogger(ClienteJframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClienteJframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClienteJframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClienteJframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                new ClienteJframe().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AgregarBoton;
    private javax.swing.JButton BorrarBoton;
    private javax.swing.JTable clienteTabla;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
