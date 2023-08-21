/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tecleado_juego;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import juego_restaurante.Componentes_juego;

/**
 *
 * @author Gabriel
 */
public class Teclado implements KeyListener {
    Componentes_juego cj;
    private boolean arriba, abajo, izquierda, derecha,
            presionadoEnter_dialogos, presionadoEscape, presionado_E = false, presionado_R = false,
            presionadoEnter_ingrediente=false,presionado_C_ingrediente=false;

    public Teclado(Componentes_juego cj) {
        this.cj = cj;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public void estadoTituloTeclado(int presionado) {
        if (presionado == KeyEvent.VK_W || presionado == KeyEvent.VK_UP) {
            cj.getUi().numeroComando--;
            if (cj.getUi().numeroComando < 0) {
                cj.getUi().numeroComando = 1;
            }
        }
        if (presionado == KeyEvent.VK_S|| presionado == KeyEvent.VK_DOWN) {
            cj.getUi().numeroComando++;
            if (cj.getUi().numeroComando > 1) {
                cj.getUi().numeroComando = 0;
            }
        }
        
        if (presionado == KeyEvent.VK_ENTER) {
            if (cj.getUi().numeroComando == 0) {
                cj.setEstadoJuego(cj.getJuegoIniciar());
                cj.pararMusica();
                cj.iniciarMusica(0);
                cj.ponerVolumenTema((float) 0.7);
                presionadoEscape = false;
            }
            if (cj.getUi().numeroComando == 1) {
                System.exit(0);
                
            }
        }
    }

    public void estadoIniciarTeclado(int presionado) {
        if (presionado == KeyEvent.VK_W) {
            arriba = true;
        }
        if (presionado == KeyEvent.VK_S) {
            abajo = true;
        }
        if (presionado == KeyEvent.VK_A) {
            izquierda = true;
        }
        if (presionado == KeyEvent.VK_D) {
            derecha = true;
        }
        if (presionado == KeyEvent.VK_ENTER) {
            presionadoEnter_dialogos = true;
        }
        if (presionado == KeyEvent.VK_ESCAPE) {
            cj.pararMusica();
            cj.iniciarMusica(4);
            cj.ponerVolumenTema((float) 0.7);
            presionadoEscape = true;
            cj.setEstadoJuego(cj.getEstadoTitulo());
        }
        if (presionado == KeyEvent.VK_E) {
            presionado_E = true;
        }
        if (presionado == KeyEvent.VK_R&& presionado_E==true){
            presionado_R=true;
            cj.setEstadoJuego(cj.getEstadoJugador());
        }
    }

    public void estadoDialogosTeclado(int presionado) {
        if (presionado == KeyEvent.VK_ENTER) {
            presionadoEnter_dialogos = true;

        }
    }

    public void estadoVerJugador(int presionado) {
        if (presionado == KeyEvent.VK_R && presionado_E==true) {
            cj.setEstadoJuego(cj.getJuegoIniciar());
        }
        // if (presionado == KeyEvent.VK_A && presionado_E==true){
        //     if (cj.getUi().espacioColumna!=0){
        //         cj.getUi().espacioColumna--;
        //         cj.usarEfecto_Sonido(10);
        //     }
        // }
        // if (presionado == KeyEvent.VK_D && presionado_E==true){
        //     if (cj.getUi().espacioColumna!=4){
        //         cj.getUi().espacioColumna++;
        //         cj.usarEfecto_Sonido(10);
        //     }
        // }
        if (presionado == KeyEvent.VK_ENTER && presionado_E==true){
            presionadoEnter_ingrediente=true;
        }
        if (presionado == KeyEvent.VK_C && presionado_E==true){
            presionado_C_ingrediente=true;
        }
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        int presionado = e.getKeyCode();

        if (cj.getEstadoJuego() == cj.getEstadoTitulo()) {
            estadoTituloTeclado(presionado);
        }

        if (cj.getEstadoJuego() == cj.getJuegoIniciar()) {
            estadoIniciarTeclado(presionado);

        } else if (cj.getEstadoJuego() == cj.getJuegoDialogos()) {
            estadoDialogosTeclado(presionado);
        }else if (cj.getEstadoJuego() == cj.getEstadoJugador()) {
            estadoVerJugador(presionado);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int presionado = e.getKeyCode();

        if (presionado == KeyEvent.VK_W) {
            arriba = false;
        }
        if (presionado == KeyEvent.VK_S) {
            abajo = false;
        }
        if (presionado == KeyEvent.VK_A) {
            izquierda = false;
        }
        if (presionado == KeyEvent.VK_D) {
            derecha = false;
        }
    }

    public boolean isArriba() {
        return arriba;
    }

    public void setArriba(boolean arriba) {
        this.arriba = arriba;
    }

    public boolean isAbajo() {
        return abajo;
    }

    public void setAbajo(boolean abajo) {
        this.abajo = abajo;
    }

    public boolean isIzquierda() {
        return izquierda;
    }

    public void setIzquierda(boolean izquierda) {
        this.izquierda = izquierda;
    }

    public boolean isDerecha() {
        return derecha;
    }

    public void setDerecha(boolean derecha) {
        this.derecha = derecha;
    }

    public boolean isPresionadoEnter_dialogos() {
        return presionadoEnter_dialogos;
    }

    public void setPresionadoEnter_dialogos(boolean presionadoEnter_dialogos) {
        this.presionadoEnter_dialogos = presionadoEnter_dialogos;
    }

    public boolean isPresionadoEscape() {
        return presionadoEscape;
    }

    public void setPresionadoEscape(boolean presionadoEscape) {
        this.presionadoEscape = presionadoEscape;
    }

    public boolean isPresionado_E() {
        return presionado_E;
    }

    public void setPresionado_E(boolean presionado_E) {
        this.presionado_E = presionado_E;
    }

    public boolean isPresionado_R() {
        return presionado_R;
    }

    public void setPresionado_R(boolean presionado_R) {
        this.presionado_R = presionado_R;
    }

    public boolean isPresionadoEnter_ingrediente() {
        return presionadoEnter_ingrediente;
    }

    public void setPresionadoEnter_ingrediente(boolean presionadoEnter_ingrediente) {
        this.presionadoEnter_ingrediente = presionadoEnter_ingrediente;
    }

    public boolean isPresionado_C_ingrediente() {
        return presionado_C_ingrediente;
    }

    public void setPresionado_C_ingrediente(boolean presionado_C_ingrediente) {
        this.presionado_C_ingrediente = presionado_C_ingrediente;
    }
    
}
