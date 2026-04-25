/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;
/**
 *
 * @author kelly
 */
public class PanelRedondeado extends JPanel {
    
private int radio = 10; // Qué tan redondo quieres el borde (puedes cambiar este número)

    public PanelRedondeado() {
    
        setOpaque(false); // Esto es súper importante para que las esquinas sean transparentes y no se vea un cuadrado blanco detrás
        setBackground(new java.awt.Color(41, 128, 185));
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }
    

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // BORRA la línea que decía: g2.setColor(getBackground());
        // Y pon esta, forzando el color azul oscuro (puedes cambiar los números RGB a tu gusto)
        g2.setColor(new java.awt.Color(41, 128, 185)); 
        
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radio, radio);
        super.paintComponent(g); 
        
    }
    @Override
    public java.awt.Dimension getPreferredSize() {
        return new java.awt.Dimension(100, 10); // Ancho de 200, Alto de 50
    }
}
