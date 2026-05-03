package interfaz;

import com.toedter.calendar.JDateChooser;
import conexion.conexion;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultTreeSelectionModel;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JTextField;

public class Compras extends javax.swing.JFrame {
    DefaultTableModel modelo;

    public Compras() {
        initComponents();
        obtenerFecha(txtFecha2);
        txtFecha2.setDateFormatString("yyyy-MM-dd");
        txtFecha2.setMinSelectableDate(new java.util.Date());
        txtFecha2.setMaxSelectableDate(new java.util.Date());
        ((JTextField) txtFecha2.getDateEditor().getUiComponent()).setEditable(false);
        txtFecha2.setDate(new java.util.Date());
        tablaCompra.setRowSelectionAllowed(true);
        cargartabla("");
        
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
    
    public String obtenerFecha(JDateChooser dateChooser) {
        java.util.Date hoy = new java.util.Date();
        dateChooser.setDate(hoy); 

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        return formato.format(hoy);
}

    
    private String extraerID(String itemCombo) {
        return itemCombo.split(" - ")[0];
    }
    
    private void inhabilitar (){
        txtFecha2.setEnabled(false); 
        txtTotal.setEnabled(false);
        cboIdProveedor.setEnabled(false);
        cboIdAuto.setEnabled(false);
           
        txtFecha2.setDate(null);
        txtTotal.setText("");
        btnNuevo.setEnabled(true);
        btnGuardar.setEnabled(false);
        btnCancelar.setEnabled(false);
        btnEditar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnCambios.setEnabled(false);
    }
     
    private void habilitar(){
        txtFecha2.setEnabled(true);
        txtTotal.setEnabled(true);
        cboIdProveedor.setEnabled(true);
        cboIdAuto.setEnabled(true);

        txtFecha2.setDate(null);
        
        btnNuevo.setEnabled(false);
        btnGuardar.setEnabled(true);
        btnCancelar.setEnabled(true);
        btnEditar.setEnabled(false);
        btnEliminar.setEnabled(false);
    }
    
    private void seleccionarCombo(javax.swing.JComboBox<String> combo, String id){
        for(int i = 0; i < combo.getItemCount(); i++){
            String item = combo.getItemAt(i);
            if(item.startsWith(id + " -")){
                combo.setSelectedIndex(i);
                break;
            }
        }
    }
    
    private void cargartabla(String valor){
        String [] titulos ={"ID","Fecha","Total","ID proveedor","ID auto"};
        String [] registros = new String[5];
        modelo = new DefaultTableModel(null, titulos) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // 👈 bloquea edición
            }
        };
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

        ResultSet rs = st.executeQuery("SELECT idAuto, marca, modelo, precio, stock FROM autos");

        cboIdAuto.removeAllItems();
        while(rs.next()){
 
            String item = rs.getString("idAuto") + " - " + rs.getString("marca") + " " + rs.getString("modelo") 
                        + " - $" + rs.getString("precio") + " - Cant:" + rs.getString("stock");
            cboIdAuto.addItem(item);
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error: " + e);
    }    
}
    private void calcularTotal() {
        try {

        String item = cboIdAuto.getSelectedItem().toString();
        
        int inicioPrecio = item.indexOf("$") + 1;
        int finPrecio = item.indexOf(" - Cant:");
        String precioStr = item.substring(inicioPrecio, finPrecio);
        double precio = Double.parseDouble(precioStr);

        int inicioCant = item.indexOf("Cant:") + 5;
        String cantStr = item.substring(inicioCant);
        int cantidad = Integer.parseInt(cantStr);

        txtTotal.setText(String.valueOf(precio * cantidad));

    } catch (Exception e) {
        txtTotal.setText("0.0");
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
        txtTotal = new javax.swing.JTextField();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        cboIdProveedor = new javax.swing.JComboBox<>();
        cboIdAuto = new javax.swing.JComboBox<>();
        txtFecha2 = new com.toedter.calendar.JDateChooser();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnCambios = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCompra = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        btnReporte = new javax.swing.JButton();
        jToggleButton1 = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Compras"));

        jLabel1.setText("Fecha");

        jLabel2.setText("Total");

        jLabel3.setText("idProveedor");

        jLabel4.setText("idAuto");

        txtTotal.setEditable(false);
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

        cboIdProveedor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboIdProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboIdProveedorActionPerformed(evt);
            }
        });

        cboIdAuto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboIdAuto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboIdAutoActionPerformed(evt);
            }
        });

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnCambios.setText("Guaradar Cambios");
        btnCambios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 42, Short.MAX_VALUE)
                        .addComponent(btnNuevo)
                        .addGap(26, 26, 26)
                        .addComponent(btnGuardar)
                        .addGap(27, 27, 27)
                        .addComponent(btnCancelar)
                        .addGap(27, 27, 27)
                        .addComponent(btnEliminar)
                        .addGap(36, 36, 36)
                        .addComponent(btnEditar)
                        .addGap(42, 42, 42)
                        .addComponent(btnCambios)
                        .addGap(64, 64, 64))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(126, 126, 126)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtFecha2, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
                            .addComponent(txtTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
                            .addComponent(cboIdProveedor, 0, 413, Short.MAX_VALUE)
                            .addComponent(cboIdAuto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFecha2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(cboIdProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cboIdAuto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevo)
                    .addComponent(btnGuardar)
                    .addComponent(btnCancelar)
                    .addComponent(btnEditar)
                    .addComponent(btnEliminar)
                    .addComponent(btnCambios))
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
        tablaCompra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaCompraMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaCompraMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tablaCompra);

        jLabel5.setText("Buscar registro por ID");

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
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
                                .addComponent(btnReporte)
                                .addGap(18, 18, 18)
                                .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(34, 34, 34)
                                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)
                                .addComponent(btnBuscar)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(btnBuscar))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReporte)
                    .addComponent(jToggleButton1))
                .addGap(15, 15, 15))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
    java.util.Date fechaSeleccionada = txtFecha2.getDate();
    
    if(fechaSeleccionada == null){
        JOptionPane.showMessageDialog(null, "Error: La fecha no puede estar vacía");
        return;
    }

    java.sql.Date fec = new java.sql.Date(fechaSeleccionada.getTime());

    if(txtTotal.getText().trim().isEmpty()){
        JOptionPane.showMessageDialog(null, "Por favor, ingresa el monto total de la compra");
        txtTotal.requestFocus();
        return;
    }

    String tot = txtTotal.getText().trim();
    String pro = extraerID(cboIdProveedor.getSelectedItem().toString());
    String aut = extraerID(cboIdAuto.getSelectedItem().toString());

    String sSQL = "INSERT INTO compras(fecha, total, idProveedor, idAuto) VALUES(?,?,?,?)";

    try {
        PreparedStatement pst = cn.prepareStatement(sSQL);

        pst.setDate(1, fec);
        pst.setString(2, tot);
        pst.setString(3, pro);
        pst.setString(4, aut);

        int n = pst.executeUpdate();

        if(n > 0){
            JOptionPane.showMessageDialog(null, "Compra registrada exitosamente");
            cargartabla(""); 
            inhabilitar();   
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al guardar en la base de datos: " + e.getMessage());
    } 
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        inhabilitar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        String valor = txtBuscar.getText();
        cargartabla(valor);
    }//GEN-LAST:event_btnBuscarActionPerformed

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
        cargartabla("");
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        
    txtTotal.setEnabled(true);
    cboIdProveedor.setEnabled(true);
    cboIdAuto.setEnabled(true);
    txtTotal.requestFocus();

    
    btnCambios.setEnabled(true); // 
    btnEditar.setEnabled(false);        
    btnEliminar.setEnabled(false);      
    btnNuevo.setEnabled(false);
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

    private void cboIdProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboIdProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboIdProveedorActionPerformed

    private void tablaCompraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaCompraMouseClicked
      int fila = tablaCompra.getSelectedRow();

    if(fila >= 0){
        txtTotal.setEnabled(false);
        cboIdProveedor.setEnabled(false);
        cboIdAuto.setEnabled(false);
       
        txtBuscar.setText(tablaCompra.getValueAt(fila, 0).toString());
        
        try {

            String fechaTexto = tablaCompra.getValueAt(fila, 1).toString();
            java.util.Date fecha = java.sql.Date.valueOf(fechaTexto);
            txtFecha2.setDate(fecha);


            txtTotal.setText(tablaCompra.getValueAt(fila, 2).toString());


            String idProveedor = tablaCompra.getValueAt(fila, 3).toString();
            seleccionarCombo(cboIdProveedor, idProveedor);

            // Auto
            String idAuto = tablaCompra.getValueAt(fila, 4).toString();
            seleccionarCombo(cboIdAuto, idAuto);

            btnEditar.setEnabled(true);
            btnEliminar.setEnabled(true);
            btnGuardar.setEnabled(false);
            btnCancelar.setEnabled(true);
            btnNuevo.setEnabled(false);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar datos: " + e);
        }
    }
      
    }//GEN-LAST:event_tablaCompraMouseClicked

    private void tablaCompraMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaCompraMousePressed
        int fila = tablaCompra.getSelectedRow();

    if(fila >= 0){
        
        txtTotal.setEnabled(false);
        cboIdProveedor.setEnabled(false);
        cboIdAuto.setEnabled(false);
       
       
        txtBuscar.setText(tablaCompra.getValueAt(fila, 0).toString());
        
        try {

            String fechaTexto = tablaCompra.getValueAt(fila, 1).toString();
            java.util.Date fecha = java.sql.Date.valueOf(fechaTexto);
            txtFecha2.setDate(fecha);


            txtTotal.setText(tablaCompra.getValueAt(fila, 2).toString());

            String idProveedor = tablaCompra.getValueAt(fila, 3).toString();
            seleccionarCombo(cboIdProveedor, idProveedor);

            String idAuto = tablaCompra.getValueAt(fila, 4).toString();
            seleccionarCombo(cboIdAuto, idAuto);


            btnEditar.setEnabled(true);
            btnEliminar.setEnabled(true);
            btnGuardar.setEnabled(false);
            btnCancelar.setEnabled(true);
            btnNuevo.setEnabled(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar datos: " + e);
        }
    }
        
    }//GEN-LAST:event_tablaCompraMousePressed

    private void btnCambiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiosActionPerformed

    if (txtTotal.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "El total no puede estar vacío");
        return;
    }

    try {
        conexion mysql = new conexion();
        Connection cn = mysql.getConexion();
        
        String id = txtBuscar.getText();
        java.sql.Date fechaSQL = new java.sql.Date(txtFecha2.getDate().getTime());
        String pro = extraerID(cboIdProveedor.getSelectedItem().toString());
        String aut = extraerID(cboIdAuto.getSelectedItem().toString());

        String sql = "UPDATE compras SET fecha=?, total=?, idProveedor=?, idAuto=? WHERE idCompra=?";
        PreparedStatement pst = cn.prepareStatement(sql);

        pst.setDate(1, fechaSQL);
        pst.setString(2, txtTotal.getText().trim());
        pst.setString(3, pro);
        pst.setString(4, aut);
        pst.setString(5, id);

        int n = pst.executeUpdate();

        if (n > 0) {
            JOptionPane.showMessageDialog(null, "¡Cambios guardados correctamente!");
            cargartabla(""); 
            estadoInicial();
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al actualizar: " + e.getMessage());
    }
    }//GEN-LAST:event_btnCambiosActionPerformed

    private void cboIdAutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboIdAutoActionPerformed

        if (txtTotal.isEnabled()) {
            try {
                String item = cboIdAuto.getSelectedItem().toString();

   
                int posDolar = item.indexOf("$") + 1;
                int posGuion = item.indexOf(" - Cant:");
                double precio = Double.parseDouble(item.substring(posDolar, posGuion));

 
                int posCant = item.indexOf("Cant:") + 5;
                int stock = Integer.parseInt(item.substring(posCant));

   
                double totalFinal = precio * stock;
                txtTotal.setText(String.valueOf(totalFinal));

            } catch (Exception e) {
                txtTotal.setText("0.0");
            }
        }
    }//GEN-LAST:event_cboIdAutoActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Compras().setVisible(true);
            }
        });
    }
    
    private void estadoInicial() {
    inhabilitar(); // Bloquea todo y limpia
    btnNuevo.setEnabled(true);
    btnGuardar.setEnabled(false);
    btnCancelar.setEnabled(false);
    btnEditar.setEnabled(false);
    btnCambios.setEnabled(false); 
    btnEliminar.setEnabled(false);
}
    private void estadoEdicion() {
    txtTotal.setEnabled(true);
    cboIdProveedor.setEnabled(true);
    cboIdAuto.setEnabled(true);
    txtTotal.requestFocus();

    btnNuevo.setEnabled(false);
    btnGuardar.setEnabled(false);
    btnCancelar.setEnabled(true);
    btnEditar.setEnabled(false);
    btnCambios.setEnabled(true); 
    btnEliminar.setEnabled(false);
}
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCambios;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
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
    private com.toedter.calendar.JDateChooser txtFecha2;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
