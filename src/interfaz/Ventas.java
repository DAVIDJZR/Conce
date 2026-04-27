/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package interfaz;

import com.toedter.calendar.JDateChooser;
import conexion.conexion;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.*; 
import java.text.SimpleDateFormat;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Toni
 */
public class Ventas extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Ventas.class.getName());

    /**
     * Creates new form Ventass
     */
    public Ventas() {
        initComponents();
        mostrarVentas();
        bloquearCampos();
        llenarCombo();
        obtenerFecha(txtFecha);
        txtFecha.setDateFormatString("yyyy-MM-dd");
        txtFecha.setMaxSelectableDate(new java.util.Date());
        ((JTextField) txtFecha.getDateEditor().getUiComponent()).setEditable(false);
          
    }
    int idVentaSeleccionada = -1;
    public void bloquearCampos() {
    txtMarca.setEditable(false);
    btnEliminar.setEnabled(false);
    txtModelo.setEditable(false);
    txtPrecio.setEditable(false);
    txtEstado.setEditable(false);
    txtTotal.setEditable(false);
    txtNombreCliente.setEditable(false);
    txtTelefonoCliente.setEditable(false);
}
    public String obtenerFecha(JDateChooser dateChooser) {
    if (dateChooser.getDate() == null) {
        return null;
    }

    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
    return formato.format(dateChooser.getDate());
}
    
private void llenarCombo() {
    try {
        conexion cc = new conexion();
        Connection cn = cc.getConexion();

        cmbCliente.removeAllItems();
        cmbCliente.addItem("Seleccione un cliente");

        ResultSet rsC = cn.createStatement().executeQuery("SELECT nombre FROM cliente");
        while (rsC.next()) {
            cmbCliente.addItem(rsC.getString("nombre"));
        }

        cmbAuto.removeAllItems();
        cmbAuto.addItem("Seleccione un auto");
        

        ResultSet rsA = cn.createStatement().executeQuery(
            "SELECT CONCAT(marca, ' ', modelo) AS vehiculo FROM autos WHERE estado != 'vendido'"
        );
        while (rsA.next()) {
            cmbAuto.addItem(rsA.getString("vehiculo"));
        }

        cmbEmpleado.removeAllItems();
        cmbEmpleado.addItem("Seleccione un empleado");

        ResultSet rsE = cn.createStatement().executeQuery("SELECT nombre FROM empleados");
        while (rsE.next()) {
            cmbEmpleado.addItem(rsE.getString("nombre"));
        }
        cmbMetododepago.removeAllItems();
        cmbMetododepago.addItem("Seleccione método de pago");
        cmbMetododepago.addItem("Efectivo");
        cmbMetododepago.addItem("Tarjeta de crédito");
        cmbMetododepago.addItem("Tarjeta de débito");
        cmbMetododepago.addItem("Transferencia");
        
        cmbEstadodepago.removeAllItems();
        cmbEstadodepago.addItem("Selecciona el estado del pago");
        cmbEstadodepago.addItem("Pagado");
        cmbEstadodepago.addItem("por pagar");

        cn.close();

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al cargar combos: " + e.getMessage());
    }
}
    
    private void limpiarCampos() {
    cmbCliente.setSelectedIndex(0);
    cmbAuto.setSelectedIndex(0);
    txtTotal.setText("");
}
   public void mostrarVentas() {

    DefaultTableModel modelo = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false; // Hace la tabla solo de lectura
        }
    };

    modelo.addColumn("ID");
    modelo.addColumn("Fecha");
    modelo.addColumn("Cliente");
    modelo.addColumn("Empleado");
    modelo.addColumn("Auto");
    modelo.addColumn("Total");
    modelo.addColumn("Estado Auto");
    modelo.addColumn("Método de Pago");
    modelo.addColumn("Estado de Pago"); // solo visual

    tblVentas.setModel(modelo);

    String sql = "SELECT v.idVenta, v.fecha, " +
                 "c.nombre AS cliente, " +
                 "e.nombre AS empleado, " +
                 "CONCAT(a.marca, ' ', a.modelo) AS auto, " +
                 "v.totaldelaventa, " +
                 "a.estado AS estadoAuto, " +
                 "v.tipPag " +
                 "FROM ventas v " +
                 "LEFT JOIN cliente c ON v.idCliente = c.idCliente " +
                 "LEFT JOIN empleados e ON v.idEmpleado = e.idEmpleado " +
                 "LEFT JOIN autos a ON v.idAuto = a.idAuto";

    try {
        conexion cc = new conexion();
        Connection cn = cc.getConexion();

        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {

           
            String estadoPago = "Pagado";

            modelo.addRow(new Object[]{
                rs.getInt("idVenta"),
                rs.getString("fecha"),
                rs.getString("cliente"),
                rs.getString("empleado"),
                rs.getString("auto"),
                rs.getDouble("totaldelaventa"),
                rs.getString("estadoAuto"),
                rs.getString("tipPag"),
                estadoPago
            });
        }

        cn.close();

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al mostrar ventas: " + e.getMessage());
    }
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
        jScrollPane2 = new javax.swing.JScrollPane();
        tblVentas = new javax.swing.JTable();
        txtTotal = new javax.swing.JTextField();
        txtNombreCliente = new javax.swing.JTextField();
        btnLimpiar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtTelefonoCliente = new javax.swing.JTextField();
        txtPrecio = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtEstado = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtModelo = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtMarca = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        cmbCliente = new javax.swing.JComboBox<>();
        cmbAuto = new javax.swing.JComboBox<>();
        txtFecha = new com.toedter.calendar.JDateChooser();
        cmbEmpleado = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        cmbMetododepago = new javax.swing.JComboBox<>();
        cmbEstadodepago = new javax.swing.JComboBox<>();
        btnEliminar = new javax.swing.JButton();
        btnGenerarPDF = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Guardar venta");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        tblVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVentasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblVentas);

        txtTotal.addActionListener(this::txtTotalActionPerformed);

        txtNombreCliente.addActionListener(this::txtNombreClienteActionPerformed);

        btnLimpiar.setText("Limpiar campos");
        btnLimpiar.addActionListener(this::btnLimpiarActionPerformed);

        jLabel1.setText("Cliente");

        jLabel2.setText("Auto");

        jLabel6.setText("Nombre");

        jLabel3.setText("Fecha");

        jLabel4.setText("Empleado");

        jLabel5.setText("Total");

        jLabel7.setText("Telefono");

        txtTelefonoCliente.addActionListener(this::txtTelefonoClienteActionPerformed);

        txtPrecio.addActionListener(this::txtPrecioActionPerformed);

        jLabel8.setText("Precio");

        txtEstado.addActionListener(this::txtEstadoActionPerformed);

        jLabel9.setText("Estado");

        txtModelo.addActionListener(this::txtModeloActionPerformed);

        jLabel10.setText("Modelo");

        txtMarca.addActionListener(this::txtMarcaActionPerformed);

        jLabel11.setText("Marca");

        jButton2.setText("Inicio");
        jButton2.addActionListener(this::jButton2ActionPerformed);

        cmbCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbCliente.addItemListener(this::cmbClienteItemStateChanged);

        cmbAuto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbAuto.addItemListener(this::cmbAutoItemStateChanged);
        cmbAuto.addActionListener(this::cmbAutoActionPerformed);

        cmbEmpleado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel12.setText("Metodo de pago");

        jLabel13.setText("Estado del pago");

        cmbMetododepago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbEstadodepago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnEliminar.setText("Eliminar venta");
        btnEliminar.addActionListener(this::btnEliminarActionPerformed);

        btnGenerarPDF.setText("GENERAR REPORTE PDF");
        btnGenerarPDF.addActionListener(this::btnGenerarPDFActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtModelo, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                                        .addComponent(cmbAuto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cmbCliente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(cmbEmpleado, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtFecha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)))
                                .addGap(36, 36, 36)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel6)
                                                .addGap(32, 32, 32)
                                                .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel13)
                                                    .addComponent(jLabel12))
                                                .addGap(33, 33, 33)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(cmbMetododepago, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(cmbEstadodepago, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(20, 20, 20)
                                                .addComponent(btnGenerarPDF)))
                                        .addGap(28, 28, 28))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTelefonoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jButton2)
                                .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(0, 89, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jButton1)
                        .addGap(114, 114, 114)
                        .addComponent(btnLimpiar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(txtTelefonoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel8)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbAuto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cmbEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(cmbMetododepago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(cmbEstadodepago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(btnLimpiar)
                    .addComponent(jButton2)
                    .addComponent(btnEliminar))
                .addGap(18, 18, 18)
                .addComponent(btnGenerarPDF)
                .addGap(25, 25, 25)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
if (cmbCliente.getSelectedIndex() == 0 ||
    cmbAuto.getSelectedIndex() == 0 ||
    cmbEmpleado.getSelectedIndex() == 0 ||
    cmbMetododepago.getSelectedIndex() == 0) {

    JOptionPane.showMessageDialog(null, "Completa todos los campos");
    return;
}

try {
    conexion cc = new conexion();
    Connection cn = cc.getConexion();

    
    PreparedStatement pstC = cn.prepareStatement(
        "SELECT idCliente FROM cliente WHERE nombre = ?"
    );
    pstC.setString(1, cmbCliente.getSelectedItem().toString());
    ResultSet rsC = pstC.executeQuery();

    if (!rsC.next()) {
        JOptionPane.showMessageDialog(null, "Cliente no encontrado");
        return;
    }
    int idCliente = rsC.getInt("idCliente");

    
    PreparedStatement pstA = cn.prepareStatement(
        "SELECT idAuto, stock FROM autos WHERE CONCAT(marca, ' ', modelo) = ?"
    );
    pstA.setString(1, cmbAuto.getSelectedItem().toString());
    ResultSet rsA = pstA.executeQuery();

    if (!rsA.next()) {
        JOptionPane.showMessageDialog(null, "Auto no encontrado");
        return;
    }

    int idAuto = rsA.getInt("idAuto");
    int stock = rsA.getInt("stock");

    if (stock <= 0) {
        JOptionPane.showMessageDialog(null, "Ya no hay stock de este auto");
        return;
    }

   
    PreparedStatement pstE = cn.prepareStatement(
        "SELECT idEmpleado FROM empleados WHERE nombre = ?"
    );
    pstE.setString(1, cmbEmpleado.getSelectedItem().toString());
    ResultSet rsE = pstE.executeQuery();

    if (!rsE.next()) {
        JOptionPane.showMessageDialog(null, "Empleado no encontrado");
        return;
    }
    int idEmpleado = rsE.getInt("idEmpleado");

   
    String metodoPago = cmbMetododepago.getSelectedItem().toString();

  
    PreparedStatement pst = cn.prepareStatement(
        "INSERT INTO ventas (fecha, totaldelaventa, idEmpleado, idCliente, idAuto, tipPag) VALUES (?,?,?,?,?,?)"
    );

    java.sql.Date fecha = new java.sql.Date(txtFecha.getDate().getTime());

    pst.setDate(1, fecha);
    pst.setDouble(2, Double.parseDouble(txtTotal.getText()));
    pst.setInt(3, idEmpleado);
    pst.setInt(4, idCliente);
    pst.setInt(5, idAuto);
    pst.setString(6, metodoPago);

    pst.executeUpdate();

    
    PreparedStatement pstStock = cn.prepareStatement(
        "UPDATE autos SET stock = stock - 1 WHERE idAuto = ?"
    );
    pstStock.setInt(1, idAuto);
    pstStock.executeUpdate();

    
    if (stock - 1 == 0) {
        JOptionPane.showMessageDialog(null, "Este auto se ha quedado sin stock");
    }

    JOptionPane.showMessageDialog(null, "Venta registrada con éxito");

    llenarCombo();
    mostrarVentas();
    limpiarCampos();

    cn.close();

} catch (NumberFormatException e) {
    JOptionPane.showMessageDialog(null, "Datos numéricos inválidos");
} catch (SQLException e) {
    JOptionPane.showMessageDialog(null, "Error SQL: " + e.getMessage());
}
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tblVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVentasMouseClicked
int fila = tblVentas.getSelectedRow();

if (fila >= 0) {
    idVentaSeleccionada = Integer.parseInt(tblVentas.getValueAt(fila, 0).toString());
    btnEliminar.setEnabled(true);
}
    }//GEN-LAST:event_tblVentasMouseClicked

    private void txtTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalActionPerformed

    private void txtNombreClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreClienteActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
       limpiarCampos();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void txtTelefonoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoClienteActionPerformed

    private void txtPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioActionPerformed

    private void txtEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEstadoActionPerformed

    private void txtMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMarcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMarcaActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        inicio VentanaNueva = new inicio();
        VentanaNueva.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cmbClienteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbClienteItemStateChanged
        // TODO add your handling code here:
       
    if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
        
        String nombreSeleccionado = cmbCliente.getSelectedItem().toString();

       
        if (nombreSeleccionado.equals("Seleccione un cliente")) {
            txtNombreCliente.setText("");
            txtTelefonoCliente.setText("");
            return;
        }

        try {
            conexion cc = new conexion();
            Connection cn = cc.getConexion();

            PreparedStatement pst = cn.prepareStatement(
                "SELECT nombre, telefono FROM cliente WHERE nombre = ?"
            );

            pst.setString(1, nombreSeleccionado);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                
                txtNombreCliente.setText(rs.getString("nombre"));
                txtTelefonoCliente.setText(rs.getString("telefono"));
            } else {
                txtNombreCliente.setText("");
                txtTelefonoCliente.setText("");
            }
            
            cn.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener datos del cliente: " + e);
        }
    }
        
    }//GEN-LAST:event_cmbClienteItemStateChanged

    private void cmbAutoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbAutoItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
    
    String autoSeleccionado = cmbAuto.getSelectedItem().toString();

    if (autoSeleccionado.equals("Seleccione un auto")) {
        txtMarca.setText("");
        txtModelo.setText("");
        txtPrecio.setText("");
        txtEstado.setText("");
        txtTotal.setText("");
        return;
    }

    try {
        conexion cc = new conexion();
        Connection cn = cc.getConexion();

        PreparedStatement pst = cn.prepareStatement(
            "SELECT marca, modelo, precio, estado FROM autos WHERE CONCAT(marca, ' ', modelo) = ?"
        );

        pst.setString(1, autoSeleccionado);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            String estado = rs.getString("estado");
            double precioOriginal = rs.getDouble("precio");
            double totalFinal = precioOriginal;

            txtMarca.setText(rs.getString("marca"));
            txtModelo.setText(rs.getString("modelo"));
            txtEstado.setText(estado);

            if (estado.equalsIgnoreCase("vendido")) {
                JOptionPane.showMessageDialog(null, "Este auto ya está vendido ⚠️");
                txtPrecio.setText("");
                txtTotal.setText("");
                return;
            }

            if (estado.equalsIgnoreCase("usado")) {
                totalFinal = precioOriginal * 0.90; // 10% descuento
                JOptionPane.showMessageDialog(null, "Auto usado: 10% de descuento aplicado ");
            }

            txtPrecio.setText(String.valueOf(precioOriginal));
            txtTotal.setText(String.valueOf(totalFinal));

            txtTotal.setEditable(false);
            txtPrecio.setEditable(false);
        }

        cn.close();

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al obtener datos del auto: " + e);
    }
}

    }//GEN-LAST:event_cmbAutoItemStateChanged

    private void cmbAutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAutoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbAutoActionPerformed

    private void txtModeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtModeloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtModeloActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
if (idVentaSeleccionada == -1) {
    JOptionPane.showMessageDialog(null, "Selecciona una venta");
    return;
}

int confirm = JOptionPane.showConfirmDialog(null, "¿Eliminar esta venta?", "Confirmar", JOptionPane.YES_NO_OPTION);

if (confirm != JOptionPane.YES_OPTION) return;

try {
    conexion cc = new conexion();
    Connection cn = cc.getConexion();

    
    PreparedStatement pstBuscar = cn.prepareStatement(
        "SELECT idAuto FROM ventas WHERE idVenta = ?"
    );
    pstBuscar.setInt(1, idVentaSeleccionada);
    ResultSet rs = pstBuscar.executeQuery();

    int idAuto = 0;
    if (rs.next()) {
        idAuto = rs.getInt("idAuto");
    }

    
    PreparedStatement pstEliminar = cn.prepareStatement(
        "DELETE FROM ventas WHERE idVenta = ?"
    );
    pstEliminar.setInt(1, idVentaSeleccionada);
    pstEliminar.executeUpdate();

    
    PreparedStatement pstStock = cn.prepareStatement(
        "UPDATE autos SET stock = stock + 1 WHERE idAuto = ?"
    );
    pstStock.setInt(1, idAuto);
    pstStock.executeUpdate();

    JOptionPane.showMessageDialog(null, "Venta eliminada correctamente");

    mostrarVentas();
    llenarCombo();

    idVentaSeleccionada = -1;
    btnEliminar.setEnabled(false);

    cn.close();

} catch (SQLException e) {
    JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
}
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnGenerarPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarPDFActionPerformed
        com.itextpdf.text.Document documento = new com.itextpdf.text.Document(com.itextpdf.text.PageSize.A4);

       
        String idUnico = String.valueOf(System.currentTimeMillis());
        String rutaSegura = System.getProperty("java.io.tmpdir") + "Factura_Venta_" + idUnico + ".pdf";

        try {
            com.itextpdf.text.pdf.PdfWriter.getInstance(documento, new java.io.FileOutputStream(rutaSegura));
            documento.open();

            
            com.itextpdf.text.BaseColor azulInstitucional = new com.itextpdf.text.BaseColor(0, 51, 102);
            com.itextpdf.text.Font fuenteTitulo = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 22, com.itextpdf.text.Font.BOLD, azulInstitucional);

            com.itextpdf.text.Paragraph titulo = new com.itextpdf.text.Paragraph("COMPROBANTE DE VENTA OFICIAL\n\n", fuenteTitulo);
            titulo.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            documento.add(titulo);

           
            com.itextpdf.text.Font fuenteLabel = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 11, com.itextpdf.text.Font.BOLD);
            String fechaActual = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm").format(new java.util.Date());

          
            String nombreCliente = cmbCliente.getSelectedItem().toString();
            String nombreEmpleado = cmbEmpleado.getSelectedItem().toString();

            documento.add(new com.itextpdf.text.Paragraph("Fecha de Emisión: " + fechaActual, fuenteLabel));
            documento.add(new com.itextpdf.text.Paragraph("Cliente: " + nombreCliente, fuenteLabel));
            documento.add(new com.itextpdf.text.Paragraph("Atendido por ID Empleado: " + nombreEmpleado, fuenteLabel));
            documento.add(new com.itextpdf.text.Paragraph("______________________________________________________________________________\n\n"));

            
            com.itextpdf.text.pdf.PdfPTable tablaPDF = new com.itextpdf.text.pdf.PdfPTable(2);
            tablaPDF.setWidthPercentage(100);

            com.itextpdf.text.Font fuenteEncabezado = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 12, com.itextpdf.text.Font.BOLD, com.itextpdf.text.BaseColor.WHITE);

            
            String[] encabezados = {"Concepto", "Información del Vehículo"};
            for (String h : encabezados) {
                com.itextpdf.text.pdf.PdfPCell celda = new com.itextpdf.text.pdf.PdfPCell(new com.itextpdf.text.Paragraph(h, fuenteEncabezado));
                celda.setBackgroundColor(azulInstitucional);
                celda.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                celda.setPadding(8);
                tablaPDF.addCell(celda);
            }

            
            tablaPDF.addCell("Marca:");
            tablaPDF.addCell(txtMarca.getText());
            tablaPDF.addCell("Modelo:");
            tablaPDF.addCell(txtModelo.getText());
            tablaPDF.addCell("Estado:");
            tablaPDF.addCell(txtEstado.getText());
            tablaPDF.addCell("Precio Base:");
            tablaPDF.addCell("$" + txtPrecio.getText());
            tablaPDF.addCell("TOTAL FINAL:");
            tablaPDF.addCell("$" + txtTotal.getText());

            documento.add(tablaPDF);

           
            if (txtEstado.getText().equalsIgnoreCase("usado")) {
                documento.add(new com.itextpdf.text.Paragraph("\n* Nota: Este vehículo cuenta con un descuento automático del 10% aplicado por ser unidad usada."));
            }

           
            documento.add(new com.itextpdf.text.Paragraph("\n\n__________________________"));
            documento.add(new com.itextpdf.text.Paragraph("Firma de Conformidad"));

            documento.close();

          
            java.io.File archivo = new java.io.File(rutaSegura);
            java.awt.Desktop.getDesktop().open(archivo);

        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Error al generar reporte: " + e.getMessage());
        }
    }//GEN-LAST:event_btnGenerarPDFActionPerformed

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Ventas().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGenerarPDF;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JComboBox<String> cmbAuto;
    private javax.swing.JComboBox<String> cmbCliente;
    private javax.swing.JComboBox<String> cmbEmpleado;
    private javax.swing.JComboBox<String> cmbEstadodepago;
    private javax.swing.JComboBox<String> cmbMetododepago;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblVentas;
    private javax.swing.JTextField txtEstado;
    private com.toedter.calendar.JDateChooser txtFecha;
    private javax.swing.JTextField txtMarca;
    private javax.swing.JTextField txtModelo;
    private javax.swing.JTextField txtNombreCliente;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtTelefonoCliente;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}

