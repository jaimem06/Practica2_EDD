package Vista;

import Controlador.Controlador_Comida;
import TDA_Lista.ListaEnlazadaServices;
import TDA_Lista.TipoOrdenacion;
import Modelo.Datos_Comida;
import Tabla.TablaD_Aleatorios;
import Tabla.TablaOrdenada;
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
        tblCuenta.setModel(tablaC);
        tblCuenta.updateUI();
    }

    private void cargarTablaOrden(ListaEnlazadaServices<Datos_Comida> listaPrueba) {
        tablaC.setListaComida(listaPrueba);
        tblCuenta.setModel(tablaC);
        tblCuenta.updateUI();
    }

    private String cambiarNombre() {
        String nr = cbxOpciones.getSelectedItem().toString();
        if (nr.equals("    Restaurante"))
        {
            nr = "restaurante";
        } else
        {
            if (nr.equals("    Sabor"))
            {
                nr = "sabor";
            } else
            {
                if (nr.equals("    Tamaño"))
                {
                    nr = "tamanio";
                } else
                {
                    if (nr.equals("    Precio"))
                    {
                        nr = "precio";
                    }
                }
            }
        }
        return nr;
    }

    private void ordenarShell() {

        if (cbxOrden.getSelectedIndex() == 1)
        {
            txtTiempo.setText("");
            long inicio = System.currentTimeMillis();
            ctrlComida.ordenarShell(cambiarNombre(), TipoOrdenacion.ASCENDENTE);
            long fin = System.currentTimeMillis();
            cargarTablaOrden(ctrlComida.getListaComida());

            //Calcular tiempo de Busqueda
            double tiempo = (double) ((fin - inicio) / 1000);
            txtTiempo.setText("Tiempo medido: \n" + String.valueOf(tiempo) + " segundos");
        } else
        {
            if (cbxOrden.getSelectedIndex() == 2)
            {
                txtTiempo.setText("");
                long inicio = System.currentTimeMillis();
                ctrlComida.ordenarShell(cambiarNombre(), TipoOrdenacion.DESCENDENTE);
                long fin = System.currentTimeMillis();
                cargarTablaOrden(ctrlComida.getListaComida());
                double tiempo = (double) ((fin - inicio) / 1000);
                txtTiempo.setText("Tiempo medido: \n" + String.valueOf(tiempo) + " segundos");
            } else
            {
                JOptionPane.showMessageDialog(null, "Por favor seleccione el formato de orden");
            }
        }
    }

    private void ordenarQuickShort() {
        if (cbxOrden.getSelectedIndex() == 1)
        {
            txtTiempo.setText("");
            long inicio = System.currentTimeMillis();
            ctrlComida.ordenarQuickShort(cambiarNombre(), TipoOrdenacion.ASCENDENTE);
            long fin = System.currentTimeMillis();
            cargarTablaOrden(ctrlComida.getListaComida());
            double tiempo = (double) ((fin - inicio) / 1000);
            txtTiempo.setText("Tiempo medido: \n" + String.valueOf(tiempo) + " segundos");
        } else
        {
            if (cbxOrden.getSelectedIndex() == 2)
            {
                txtTiempo.setText("");
                long inicio = System.currentTimeMillis();
                ctrlComida.ordenarQuickShort(cambiarNombre(), TipoOrdenacion.DESCENDENTE);
                long fin = System.currentTimeMillis();
                cargarTablaOrden(ctrlComida.getListaComida());
                double tiempo = (double) ((fin - inicio) / 1000);
                txtTiempo.setText("Tiempo medido: \n" + String.valueOf(tiempo) + " segundos");
            } else
            {
                JOptionPane.showMessageDialog(null, "Por favor seleccione el formato de orden");
            }
        }
    }

    private void OrdenarDatos() {
        int seleccion = cbxMetodo.getSelectedIndex();
        switch (seleccion)
        {
            case 1:
                ordenarShell();
                break;
            case 2:
                ordenarQuickShort();
                break;
            default:
                JOptionPane.showMessageDialog(null, "Por favor seleccione el Metodo");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        cbxOpciones = new javax.swing.JComboBox<>();
        cbxMetodo = new javax.swing.JComboBox<>();
        cbxOrden = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCuenta = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        btnOrdenar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtTiempo = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(158, 238, 197));
        jPanel1.setForeground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbxOpciones.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "    Seleccione", "    Restaurante", "    Sabor", "    Tamaño", "    Precio" }));
        cbxOpciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxOpcionesActionPerformed(evt);
            }
        });
        jPanel1.add(cbxOpciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 140, 150, -1));

        cbxMetodo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "    Seleccione", "    Metodo Shell", "    Metodo Quickshort" }));
        jPanel1.add(cbxMetodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 200, 150, -1));

        cbxOrden.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "    Seleccione", "    Ascendente", "    Descendente" }));
        jPanel1.add(cbxOrden, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 260, 150, -1));

        tblCuenta.setBackground(new java.awt.Color(15, 106, 97));
        tblCuenta.setForeground(new java.awt.Color(255, 255, 255));
        tblCuenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblCuenta);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 490, 540));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(15, 106, 97));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SISTEMA DE ADMINISTRACION DE PIZZERIAS");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, 540, 50));

        jLabel8.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(219, 15, 15));
        jLabel8.setText("Formato de Orden:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 230, -1, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(219, 15, 15));
        jLabel11.setText("Método:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 170, -1, -1));

        jLabel15.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(219, 15, 15));
        jLabel15.setText("Ordenar por:");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 110, -1, -1));

        btnOrdenar.setBackground(new java.awt.Color(15, 106, 97));
        btnOrdenar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnOrdenar.setForeground(new java.awt.Color(255, 255, 255));
        btnOrdenar.setText("ORDENAR");
        btnOrdenar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrdenarActionPerformed(evt);
            }
        });
        jPanel1.add(btnOrdenar, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 190, -1, 30));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(15, 106, 97));
        jLabel6.setText("DATOS GENERADOS:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 210, 30));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(15, 106, 97));
        jLabel2.setText("Tiempo de Busqueda:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 320, -1, -1));

        txtTiempo.setBackground(new java.awt.Color(15, 106, 97));
        txtTiempo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtTiempo.setForeground(new java.awt.Color(255, 255, 255));
        txtTiempo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTiempoActionPerformed(evt);
            }
        });
        jPanel1.add(txtTiempo, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 350, 210, 50));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 853, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOrdenarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdenarActionPerformed
        OrdenarDatos();
    }//GEN-LAST:event_btnOrdenarActionPerformed

    private void cbxOpcionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxOpcionesActionPerformed

    }//GEN-LAST:event_cbxOpcionesActionPerformed

    private void txtTiempoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTiempoActionPerformed

    }//GEN-LAST:event_txtTiempoActionPerformed

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
    private javax.swing.JButton btnOrdenar;
    private javax.swing.JComboBox<String> cbxMetodo;
    private javax.swing.JComboBox<String> cbxOpciones;
    private javax.swing.JComboBox<String> cbxOrden;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblCuenta;
    private javax.swing.JTextField txtTiempo;
    // End of variables declaration//GEN-END:variables
}
