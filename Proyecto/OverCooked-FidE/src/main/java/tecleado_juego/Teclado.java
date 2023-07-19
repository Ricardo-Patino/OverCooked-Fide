/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tecleado_juego;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Gabriel
 */
public class Teclado implements KeyListener {
    
    public boolean arriba, abajo, izquierda, derecha;

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int presionado = e.getKeyCode();
        
        if (presionado== KeyEvent.VK_W){
            arriba = true;
        }
        if (presionado== KeyEvent.VK_S){
            abajo = true;
        }
        if (presionado== KeyEvent.VK_A){
            izquierda = true;
        }
        if (presionado== KeyEvent.VK_D){
            derecha = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int presionado = e.getKeyCode();
        
        if (presionado== KeyEvent.VK_W){
            arriba = false;
        }
        if (presionado== KeyEvent.VK_S){
            abajo = false;
        }
        if (presionado== KeyEvent.VK_A){
            izquierda = false;
        }
        if (presionado== KeyEvent.VK_D){
            derecha = false;
        }
    }
    
}
