/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;
import java.sql.*;
import javax.swing.JOptionPane;


/**
 *
 * @author kelly
 */
public class conexion {
   private static final String URL = "jdbc:mysql://ipn1-4.mysql.database.azure.com:3306/concesionaria?useSSL=true";
    private static final String USER = "adminIpn";
    private static final String PASS = "IPNErro202414";
    public Connection getConexion() {
        Connection cn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection(URL, USER, PASS); 
            System.out.println("Hemos Conectado a Azure carajo");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return cn;
    }
}
