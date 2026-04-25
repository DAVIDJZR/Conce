package interfaz;

import conexion.conexion;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultTreeSelectionModel;
import java.io.FileWriter;

public class Compras extends javax.swing.JFrame {
       DefaultTableModel modelo;

    public Compras() {
        initComponents();
        
        txtFecha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                String text = txtFecha.getText().replace("-", "");

                if(text.length() > 8) text = text.substring(0,8);

                StringBuilder formatted = new StringBuilder();

                for(int i = 0; i < text.length(); i++){
                    if(i == 4 || i == 6){
                        formatted.append("-");
                    }
                    formatted.append(text.charAt(i));
                }

                txtFecha.setText(formatted.toString());
            }

            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                if(!Character.isDigit(c) && c != '.'){
                    evt.consume();
                }
            }
        });
        
        java.awt.event.KeyAdapter soloNumeros = new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                if(!Character.isDigit(c) && c != '.'){
                    evt.consume();
                }
            }
        };

        txtTotal.addKeyListener(soloNumeros);
        txtBuscar.addKeyListener(soloNumeros);
        
        cargarProveedores();
        cargarAutos();
        
        inhabilitar();
    }

    
    private String extraerID(String itemCombo) {
        return itemCombo.split(" - ")[0];
    }
    
    private void inhabilitar (){
        txtFecha.setEnabled(false);
        txtTotal.setEnabled(false);
        cboIdProveedor.setEnabled(false);
        cboIdAuto.setEnabled(false);
        
        txtFecha.setText("YYYY-MM-DD");
        txtTotal.setText("");
    }
     
    private void habilitar(){
        txtFecha.setEnabled(true);
        txtTotal.setEnabled(true);
        cboIdProveedor.setEnabled(true);
        cboIdAuto.setEnabled(true);
        
        txtFecha.setText("");
    }
    
    private void cargartabla(String valor){
        String [] titulos ={"ID","Fecha","Total","ID proveedor","ID auto"};
        String [] registros = new String[5];
        modelo = new DefaultTableModel(null,titulos);
        conexion mysql = new conexion();
        Connection cn = mysql.getConexion();
        String sSQL = "SELECT idCompra, fecha, total, idProveedor, idAuto FROM compras WHERE (idCompra) LIKE '%"+valor+"%'";
 
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                registros[0] = rs.getString("idCompra");
                registros[1] = rs.getString("fecha");
                registros[2] = rs.getString("total");
                registros[3] = rs.getString("idProveedor");
                registros[4] = rs.getString("idAuto");

                modelo.addRow(registros);
            }
            tablaCompra.setModel(modelo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void cargarProveedores() {
        conexion mysql = new conexion();
        Connection cn = mysql.getConexion();

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT idProveedor, nombre_empresa FROM proveedores");

            cboIdProveedor.removeAllItems();

            while(rs.next()){
                String item = rs.getString("idProveedor") + " - " + rs.getString("nombre_empresa");
                cboIdProveedor.addItem(item);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void cargarAutos() {
        conexion mysql = new conexion();
        Connection cn = mysql.getConexion();

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT idAuto, marca, modelo FROM autos");

            cboIdAuto.removeAllItems();

            while(rs.next()){
                String item = rs.getString("idAuto") + " - " + rs.getString("marca") + " " + rs.getString("modelo");
                cboIdAuto.addItem(item);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        txtTotal = new javax.swing.JTextField();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        cboIdProveedor = new javax.swing.JComboBox<>();
        cboIdAuto = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCompra = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnReporte = new javax.swing.JButton();
        jToggleButton1 = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Compras"));

        jLabel1.setText("Fecha");

        jLabel2.setText("Total");

        jLabel3.setText("idProveedor");

        jLabel4.setText("idAuto");

        txtFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaActionPerformed(evt);
            }
        });

        txtTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalActionPerformed(evt);
            }
        });

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        cboIdProveedor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cboIdAuto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtFecha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
                            .addComponent(txtTotal, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboIdProveedor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboIdAuto, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(97, 97, 97))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnNuevo)
                        .addGap(18, 18, 18)
                        .addComponent(btnGuardar)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar)
                        .addGap(18, 18, 18)
                        .addComponent(btnRegresar)
                        .addContainerGap(266, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(cboIdProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(cboIdAuto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevo)
                    .addComponent(btnGuardar)
                    .addComponent(btnCancelar)
                    .addComponent(btnRegresar))
                .addGap(26, 26, 26))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Consultar datos"));

        tablaCompra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tablaCompra);

        jLabel5.setText("Buscar registro por ID");

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnReporte.setText("Generar Reporte");
        btnReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteActionPerformed(evt);
            }
        });

        jToggleButton1.setText("Inicio");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(38, 38, 38)
                                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(btnBuscar))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnEditar)
                                .addGap(18, 18, 18)
                                .addComponent(btnEliminar)
                                .addGap(18, 18, 18)
                                .addComponent(btnReporte)
                                .addGap(77, 77, 77)
                                .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 90, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminar)
                    .addComponent(btnEditar)
                    .addComponent(btnReporte)
                    .addComponent(jToggleButton1))
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaActionPerformed

    private void txtTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        habilitar();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        conexion sql = new conexion();
        Connection cn = sql.getConexion();

        if(cn == null){
            JOptionPane.showMessageDialog(null,"No se conectó a la base de datos");
            return;
        }

        String fec, tot, pro, aut;
        String sSQL = "INSERT INTO compras(fecha, total, idProveedor, idAuto) VALUES(?,?,?,?)";

        fec = txtFecha.getText();
        tot = txtTotal.getText();
        pro = extraerID(cboIdProveedor.getSelectedItem().toString());
        aut = extraerID(cboIdAuto.getSelectedItem().toString());      

        if(fec.length() < 4){
            JOptionPane.showMessageDialog(null, "Fecha incompleta");
            return;
        }

        int añoActual = java.time.Year.now().getValue();
        int añoIngresado = Integer.parseInt(fec.substring(0,4));

        if(añoIngresado > añoActual){
            JOptionPane.showMessageDialog(null, "La fecha no puede ser mayor a la actual");
            return;
        }
        if(fec.length() < 10){
            JOptionPane.showMessageDialog(null, "Fecha incompleta");
            return;
        }

        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);

            pst.setDate(1, Date.valueOf(fec));
            pst.setString(2, tot);
            pst.setString(3, pro);
            pst.setString(4, aut);

            int n = pst.executeUpdate();

            if(n > 0){
                JOptionPane.showMessageDialog(null,"Datos guardados correctamente");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        inhabilitar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        String valor = txtBuscar.getText();
        cargartabla(valor);
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        inicio abrir = new inicio();
        abrir.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        String valor = txtBuscar.getText();
        String sql = "DELETE FROM compras WHERE idCompra = '"+valor+"'";
        conexion mysql = new conexion();
        Connection cn = mysql.getConexion();
        
        try {
            Statement instruccion = cn.createStatement();
            boolean borrar = instruccion.execute(sql);
            if(borrar==false){
                JOptionPane.showMessageDialog(null, "Registro eliminado exitosamente");
            }else{
                JOptionPane.showMessageDialog(null, "Registro no existente");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        conexion mysql = new conexion();
        Connection cn = mysql.getConexion();

        String id = txtBuscar.getText();

        String fec = txtFecha.getText();
        String tot = txtTotal.getText();
        String pro = extraerID(cboIdProveedor.getSelectedItem().toString()); 
        String aut = extraerID(cboIdAuto.getSelectedItem().toString());      

        String sql = "UPDATE compras SET fecha=?, total=?, idProveedor=?, idAuto=? WHERE idCompra=?";

        try {
            PreparedStatement pst = cn.prepareStatement(sql);

            pst.setDate(1, Date.valueOf(fec));
            pst.setString(2, tot);
            pst.setString(3, pro);
            pst.setString(4, aut);
            pst.setString(5, id);

            int n = pst.executeUpdate();

            if(n > 0){
                JOptionPane.showMessageDialog(null,"Registro actualizado correctamente");
                cargartabla("");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteActionPerformed
        conexion mysql = new conexion();
        Connection cn = mysql.getConexion();

        try {
            if(cn == null){
                JOptionPane.showMessageDialog(null,"Error de conexión a la base de datos");
                return;
            }

            Statement st = cn.createStatement();

            ResultSet totalCompras = st.executeQuery("SELECT COUNT(*) FROM compras");
            totalCompras.next();
            int total = totalCompras.getInt(1);

            ResultSet sumaTotal = st.executeQuery("SELECT SUM(total) FROM compras");
            sumaTotal.next();
            double dinero = sumaTotal.getDouble(1);

            FileWriter archivo = new FileWriter("reporte_compras.txt");

            archivo.write("REPORTE DE COMPRAS\n\n");
            archivo.write("Total de compras registradas: " + total + "\n");
            archivo.write("Monto total acumulado: $" + dinero + "\n");

            archivo.close();

            java.awt.Desktop.getDesktop().open(new java.io.File("reporte_compras.txt"));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_btnReporteActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        // TODO add your handling code here:
        inicio VentanaNueva = new inicio();
        VentanaNueva.setVisible(true);
        dispose();
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Compras().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JButton btnReporte;
    private javax.swing.JComboBox<String> cboIdAuto;
    private javax.swing.JComboBox<String> cboIdProveedor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JTable tablaCompra;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
