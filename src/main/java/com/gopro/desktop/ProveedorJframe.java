/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gopro.desktop;

import com.gopro.model.Empresa;
import com.gopro.model.Proveedor;
import com.gopro.dao.EmpresaDAO;
import com.gopro.dao.ProveedorDAO;
import java.util.*;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.*;

/**
 *
 * @author jgomez
 */
public class ProveedorJframe extends javax.swing.JFrame {

    private final ProveedorDAO proveedorDAO = new ProveedorDAO();
    
    private final EmpresaDAO empresaDAO = new EmpresaDAO();
    
    private final TreeMap <Long, String> empresaMap = new TreeMap<>();
    
    DefaultTableModel dtm = new DefaultTableModel() {

        @Override
        public boolean isCellEditable(int row, int column)
        {
            return column != 0;
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
                    JOptionPane.showMessageDialog(null, "El número de dias de plazo es inválido.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                
                if(llave != null){
                    Proveedor proveedor = proveedorDAO.find(llave);
                    proveedor.setNombre((String) valor1);
                    proveedor.setDireccion((String) valor2);
                    proveedor.setDiaspago(valorInteger3);
                    proveedor.setEmpresa(empresaDAO.find(getKeyForValue((String) valor4)));
                    if(!proveedorDAO.update(proveedor)){
                        JOptionPane.showMessageDialog(null, "No se actualizar, verifique información duplicada.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    Proveedor proveedor = new Proveedor();
                    proveedor.setNombre((String) valor1);
                    proveedor.setDireccion((String) valor2);
                    proveedor.setDiaspago(valorInteger3);
                    proveedor.setEmpresa(empresaDAO.find(getKeyForValue((String) valor4)));
                    proveedor = proveedorDAO.create(proveedor);
                    if(proveedor != null){
                        super.setValueAt(proveedor.getId(),row,0);
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
    public ProveedorJframe() {
        initComponents();
        LlenarTabla();
    }
    
    private Long getKeyForValue(String value) {
        for (Map.Entry<Long, String> entry : this.empresaMap.entrySet()) {
             if (entry.getValue().equals(value)) {
                 return entry.getKey();
             }
         }
        return (long) 0;
    }
    
    private void LlenarTabla(){
        
        for (Empresa empresa : this.empresaDAO.findAll()){
            this.empresaMap.put(empresa.getId(), empresa.getNombre());
        }
        
        this.dtm.addColumn("Id");
        this.dtm.addColumn("Nombre");
        this.dtm.addColumn("Dirección");
        this.dtm.addColumn("Plazo de pago");
        this.dtm.addColumn("Empresa");
        
        
        for (Proveedor proveedor : this.proveedorDAO.findAll()){
            this.dtm.addRow(new Object[]{proveedor.getId(), proveedor.getNombre(), proveedor.getDireccion(), proveedor.getDiaspago().toString(), proveedor.getEmpresa().getNombre()});
        }
        
        this.proveedorTabla.setModel(this.dtm);
        
        TableColumn idColumn = this.proveedorTabla.getColumnModel().getColumn(0);
        TableColumn nombreColumn = this.proveedorTabla.getColumnModel().getColumn(1);
        TableColumn direccionColumn = this.proveedorTabla.getColumnModel().getColumn(2);
        TableColumn diasPagoColumn = this.proveedorTabla.getColumnModel().getColumn(3);
        TableColumn empresaColumn = this.proveedorTabla.getColumnModel().getColumn(4);
       
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
        proveedorTabla = new javax.swing.JTable();
        AgregarBoton = new javax.swing.JButton();
        BorrarBoton = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setTitle("Clientes");

        proveedorTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        proveedorTabla.setCellSelectionEnabled(true);
        jScrollPane1.setViewportView(proveedorTabla);

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
        
        int[] rows = this.proveedorTabla.getSelectedRows();
        for(int i=0;i<rows.length;i++){
            Long llave = (Long)this.dtm.getValueAt(rows[i]-i, 0);
            if(llave != null){
                this.removedRows.add(llave);
                
                Proveedor proveedor = this.proveedorDAO.find(llave);
                
                if(this.proveedorDAO.delete(proveedor)){
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
            java.util.logging.Logger.getLogger(ProveedorJframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProveedorJframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProveedorJframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProveedorJframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ProveedorJframe().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AgregarBoton;
    private javax.swing.JButton BorrarBoton;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable proveedorTabla;
    // End of variables declaration//GEN-END:variables
}
