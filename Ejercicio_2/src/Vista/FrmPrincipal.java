package Vista;

import Controlador.Controlador_Comida;
import TDA_Lista.ListaEnlazada;
import TDA_Lista.TipoOrdenacion;
import Tabla.TablaD_Aleatorios;
import Tabla.TablaOrdenada;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * @author Jaime Mendoza
 */
public class FrmPrincipal extends javax.swing.JFrame {

    TablaD_Aleatorios tablaC = new TablaD_Aleatorios();
    TablaOrdenada ordenT = new TablaOrdenada();
    Controlador_Comida ctrlComida = new Controlador_Comida();

    public FrmPrincipal() {
        initComponents();
        cargarTabla();

        //Centrar ventana
        this.setLocationRelativeTo(null);
        //Titulo barra superior
        this.setTitle("Administracion de Pizzerias");
    }

    private void cargarTabla() {
        tablaC.setListaComida(ctrlComida.guardarLista());
        tblDatos.setModel(tablaC);
        tblDatos.updateUI();
    }

    private void buscarAtributo(ListaEnlazada lista, String atributo, String nr, String txt) {
        ctrlComida.ordenarShell(atributo, TipoOrdenacion.ASCENDENTE);
        try
        {
            lista = ctrlComida.getListaComida().getLista().buscarDatoPosicionObjetoBinaria(nr, txt);
            ordenT.setListaComida(lista);
            tblDatos.setModel(ordenT);
            tblDatos.updateUI();
        } catch (Exception ex)
        {
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Metodo Busqueda Binaria 
    private void buscar() {
        String nr = cbxAtributoBuscar.getSelectedItem().toString();
        String txt = txtDato.getText();
        ListaEnlazada lista = new ListaEnlazada();
        try
        {
            if (txt.trim().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Por favor ingrese el dato que desea buscar");
            } else
            {
                if (nr.equals("Restaurante"))
                {
                    nr = "restaurante";
                    buscarAtributo(lista, "restaurante", nr, txt);
                } else
                {
                    if (nr.equals("Sabor"))
                    {
                        nr = "sabor";
                        buscarAtributo(lista, "sabor", nr, txt);
                    } else
                    {
                        if (nr.equals("Tamaño"))
                        {
                            nr = "tamanio";
                            buscarAtributo(lista, "tamanio", nr, txt);
                        } else
                        {
                            if (nr.equals("Precio"))
                            {
                                nr = "precio";
                                lista = ctrlComida.getListaComida().getLista().buscarDatoPosicionObjetoBinaria(nr, Double.valueOf(txt));
                                ordenT.setListaComida(lista);
                                tblDatos.setModel(ordenT);
                                tblDatos.updateUI();
                            }
                        }
                    }
                }
            }
        } catch (Exception e)
        {
            System.out.println("ERROR: " + e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        cbxAtributoBuscar = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDatos = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtDato = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        btnActualizar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(96, 215, 155));
        jPanel1.setForeground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbxAtributoBuscar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Restaurante", "Sabor", "Tamaño", "Precio" }));
        cbxAtributoBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxAtributoBuscarActionPerformed(evt);
            }
        });
        jPanel1.add(cbxAtributoBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 130, 130, -1));

        tblDatos.setBackground(new java.awt.Color(15, 106, 97));
        tblDatos.setForeground(new java.awt.Color(255, 255, 255));
        tblDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblDatos);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 410, 540));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(15, 106, 97));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SISTEMA DE ADMINISTRACION DE PIZZERIAS");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 540, 50));

        jLabel10.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(15, 106, 97));
        jLabel10.setText("BUSCAR DATOS:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 80, 110, 30));

        jLabel13.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(205, 46, 46));
        jLabel13.setText("Ingrese el dato:");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 170, -1, -1));

        jLabel16.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(205, 46, 46));
        jLabel16.setText("Buscar por:");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 110, -1, -1));

        txtDato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDatoActionPerformed(evt);
            }
        });
        jPanel1.add(txtDato, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 190, 120, -1));

        btnBuscar.setBackground(new java.awt.Color(0, 170, 173));
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Icon_Buscar.png"))); // NOI18N
        btnBuscar.setText("BUSQUEDA BINARIA");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        jPanel1.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 230, 200, 40));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(15, 106, 97));
        jLabel6.setText("DATOS GENERADOS:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 210, 30));

        btnActualizar.setBackground(new java.awt.Color(0, 170, 173));
        btnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        jPanel1.add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 80, -1, -1));

        btnGuardar.setBackground(new java.awt.Color(0, 170, 173));
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("GUARDAR");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 620, -1, -1));

        jButton1.setBackground(new java.awt.Color(0, 170, 173));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Icon_Buscar.png"))); // NOI18N
        jButton1.setText("BUSQUEDA LINEAL");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 300, 200, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 754, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        buscar();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtDatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDatoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDatoActionPerformed

    private void cbxAtributoBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxAtributoBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxAtributoBuscarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        cargarTabla();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
//        if (ctrlComida.getListaComida().getSize() != 0)
//        {
//            if (Vista.Utilidades.guardarArchivoJSON(ctrlComida))
//            {
//                JOptionPane.showMessageDialog(this, "Se ha guardado el archivo", "Exito", JOptionPane.INFORMATION_MESSAGE);
//            } else
//            {
//                JOptionPane.showMessageDialog(this, "No se genero el archivo", "Error", JOptionPane.ERROR_MESSAGE);
//            }
//        } else
//        {
//            JOptionPane.showMessageDialog(this, "Genere un listado de autos", "Error", JOptionPane.ERROR_MESSAGE);
//        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        buscar();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cbxAtributoBuscar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblDatos;
    private javax.swing.JTextField txtDato;
    // End of variables declaration//GEN-END:variables
}
