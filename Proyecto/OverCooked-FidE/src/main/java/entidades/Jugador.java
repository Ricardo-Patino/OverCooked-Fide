/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.awt.Color;
import java.awt.Graphics2D;
import juego_restaurante.Componentes_juego;
import tecleado_juego.Teclado;

/**
 *
 * @author Gabriel
 */
public class Jugador extends Entidades{
    Componentes_juego cj;
    Teclado teclas;

    public Jugador(Componentes_juego cj, Teclado teclas) {
        this.cj = cj;
        this.teclas = teclas;
        setValoresPorDefecto();
    }
    public void setValoresPorDefecto(){
        x= 100;
        y= 100;
        velocidad=4;
    }
    
    public void dibujar (Graphics2D g2){
        g2.setColor(Color.white);
        g2.fillRect(x, y, cj.tamaño_finalObjeto, cj.tamaño_finalObjeto);
    }
    
    public void update (){
        if (teclas.arriba == true){
            //MOVIMIENTO ARRIBA
            y -= velocidad;
            // MOVER EN DIAGONALES
            if (teclas.izquierda == true){
                x -= velocidad;
            }
            else if(teclas.abajo == true){
                y += velocidad;
            }else if(teclas.derecha == true){
                x += velocidad;
            }
        }else if(teclas.abajo == true){
            //MOVIMIENTO ABAJO
            y += velocidad;
            // MOVER EN DIAGONALES
            if (teclas.izquierda == true){
                x -= velocidad;
            }
            else if(teclas.arriba == true){
                y -= velocidad;
            }else if(teclas.derecha == true){
                x += velocidad;
            }
        }else if(teclas.izquierda == true){
            //MOVIMIENTO IZQUIERDA
            x -= velocidad;
            
            // MOVER EN DIAGONALES
            if (teclas.arriba == true){
                y -= velocidad;
            }
            else if(teclas.abajo == true){
                y += velocidad;
            }else if(teclas.derecha == true){
                x += velocidad;
            }
        }else if(teclas.derecha == true){
            //MOVIMIENTO DERECHA
            x += velocidad;
            // MOVER EN DIAGONALES
            if (teclas.arriba == true){
                y -= velocidad;
            }
            else if(teclas.abajo == true){
                y += velocidad;
            }else if(teclas.izquierda == true){
                x -= velocidad;
            }
        }            
    }
}
