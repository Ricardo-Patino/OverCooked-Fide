/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import juego_restaurante.Componentes_juego;
import renderizado_optimizado.Renderizado;

import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author Gabriel
 */
public class Entidades {
    Componentes_juego cj;
    // Plano del mundo X y Y
    private int planoMundoX, planoMundoY;
    private int velocidad;
    // Imagenes del personaje
    private BufferedImage abajo_1, abajo_2, abajo_3, abajo_4,
            arriba_1, arriba_2, arriba_3, arriba_4,
            derecha_1, derecha_2, derecha_3, derecha_4,
            izquierda_1, izquierda_2, izquierda_3, izquierda_4;
    // Posicion a cambiar del personaje
    private String movimiento = "abajo";
    // Un contador para patrones cada cada 60 frames por segundo
    private int contadorPatrones = 0;
    // Cambiador del numero de patrones para escoger entre estados
    // En el que esta la animacion del jugador
    private int numPatrones = 1;
    private Rectangle areaEntidades_colision = new Rectangle(0, 0, 48, 48);
    private int areaPorDEFECTO_colisionX, areaPorDEFECTO_colisionY;
    private boolean hayColisiones = false;

    private BufferedImage imagen;
    private String nombre;
    private String nombre_ingredientes;
    private boolean colisones = false;

    private int bloqueoDeAccionesporSegundo = 0;
    private String dialogos[][] = new String[20][20];
    private int dialogosIndex = 0;
    private int dialogosPaginado = 0;

    private int tipo;
    private String nombre_jugador;
    private int hamburgesas_hechas;
    private int puntos;
    private Entidades objeto_ver_pan;
    private Entidades objeto_ver_lechuga;
    private Entidades objeto_ver_carne;
    private Entidades objeto_ver_queso;
    private int valor_pan;
    private int valor_lechuga;
    private int valor_carne;
    private int valor_queso;


    public void iniciarAccionNPC() {
    }

    public void hablaNPC() {

    }

    public void mirarJugador() {
        movimiento = switch (cj.getJugador().getMovimiento()) {
            case "arriba" -> "abajo";
            case "abajo" -> "arriba";
            case "izquierda" -> "derecha";
            case "derecha" -> "izquierda";
            default -> throw new AssertionError();
        };
    }

    public void iniciarDialogo(Entidades entidades, int setNumero) {
        cj.setEstadoJuego(cj.getJuegoDialogos());
        cj.getUi().npc = entidades;
        dialogosPaginado = setNumero;
    }

    public void update() {
        iniciarAccionNPC();
        hayColisiones = false;
        cj.getDc().dectarMosaicoEntidad(this);
        cj.getDc().dectarObjetoEntidad(this, false);
        cj.getDc().verEntidadJUGADOR(this);
        // if (hayColisiones == false) {
        //     switch (movimiento) {
        //         case "arriba" -> planoMundoY -= velocidad;
        //         case "abajo" -> planoMundoY += velocidad;
        //         case "izquierda" -> planoMundoX -= velocidad;
        //         case "derecha" -> planoMundoX += velocidad;
        //         default -> throw new AssertionError();
        //     }
        // }

        // contadorPatrones++;
        // if (contadorPatrones > 13) {
        //     switch (numPatrones) {
        //         case 1 -> numPatrones = 2;
        //         case 2 -> numPatrones = 3;
        //         case 3 -> numPatrones = 4;
        //         case 4 -> numPatrones = 1;
        //         default -> {
        //         }
        //     }
        //     contadorPatrones = 0;
        // }
    }

    public Entidades(Componentes_juego cj) {
        this.cj = cj;
    }

    public void dibujar(Graphics2D g2) {
        int pantallaDibujarX = planoMundoX - cj.getJugador().getPlanoMundoX() + cj.getJugador().getPantallaX();
        int pantallaDibujarY = planoMundoY - cj.getJugador().getPlanoMundoY() + cj.getJugador().getPantallaY();

        if (getPlanoMundoX() + cj.getTamaño_finalObjeto() > cj.getJugador().getPlanoMundoX() - cj.getJugador().getPantallaX() &&
                getPlanoMundoX() - cj.getTamaño_finalObjeto() < cj.getJugador().getPlanoMundoX() + cj.getJugador().getPantallaX() &&
                getPlanoMundoY() + cj.getTamaño_finalObjeto() > cj.getJugador().getPlanoMundoY() - cj.getJugador().getPantallaY() &&
                getPlanoMundoY() - cj.getTamaño_finalObjeto() < cj.getJugador().getPlanoMundoY() + cj.getJugador().getPantallaY()) {
            BufferedImage imagen = null;
            switch (movimiento) {
                case "arriba" -> {
                    if (numPatrones == 1) {
                        imagen = arriba_1;
                    }
                    if (numPatrones == 2) {
                        imagen = arriba_2;
                    }
                    if (numPatrones == 3) {
                        imagen = arriba_3;
                    }
                    if (numPatrones == 4) {
                        imagen = arriba_4;
                    }
                }
                case "abajo" -> {
                    if (numPatrones == 1) {
                        imagen = abajo_1;
                    }
                    if (numPatrones == 2) {
                        imagen = abajo_2;
                    }
                    if (numPatrones == 3) {
                        imagen = abajo_3;
                    }
                    if (numPatrones == 4) {
                        imagen = abajo_4;
                    }
                }
                case "izquierda" -> {
                    if (numPatrones == 1) {
                        imagen = izquierda_1;
                    }
                    if (numPatrones == 2) {
                        imagen = izquierda_2;
                    }
                    if (numPatrones == 3) {
                        imagen = izquierda_3;
                    }
                    if (numPatrones == 4) {
                        imagen = izquierda_4;
                    }
                }
                case "derecha" -> {
                    if (numPatrones == 1) {
                        imagen = derecha_1;
                    }
                    if (numPatrones == 2) {
                        imagen = derecha_2;
                    }
                    if (numPatrones == 3) {
                        imagen = derecha_3;
                    }
                    if (numPatrones == 4) {
                        imagen = derecha_4;
                    }
                }
                default -> throw new AssertionError();
            }
            g2.drawImage(imagen, pantallaDibujarX,
                    pantallaDibujarY, null);
        }
    }

    public BufferedImage estructura(String ruta, String nombreImagen, int ancho, int largo) {
        Renderizado render = new Renderizado();
        BufferedImage imagen = null;
        try {
            imagen = ImageIO.read(getClass().getResourceAsStream("/img" + ruta + "/" + nombreImagen + ".png"));
            imagen = render.imagen_a_Escalar(imagen, ancho, largo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imagen;
    }

    public int getPlanoMundoX() {
        return planoMundoX;
    }

    public void setPlanoMundoX(int planoMundoX) {
        this.planoMundoX = planoMundoX;
    }

    public int getPlanoMundoY() {
        return planoMundoY;
    }

    public void setPlanoMundoY(int planoMundoY) {
        this.planoMundoY = planoMundoY;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public BufferedImage getAbajo_1() {
        return abajo_1;
    }

    public void setAbajo_1(BufferedImage abajo_1) {
        this.abajo_1 = abajo_1;
    }

    public BufferedImage getAbajo_2() {
        return abajo_2;
    }

    public void setAbajo_2(BufferedImage abajo_2) {
        this.abajo_2 = abajo_2;
    }

    public BufferedImage getAbajo_3() {
        return abajo_3;
    }

    public void setAbajo_3(BufferedImage abajo_3) {
        this.abajo_3 = abajo_3;
    }

    public BufferedImage getAbajo_4() {
        return abajo_4;
    }

    public void setAbajo_4(BufferedImage abajo_4) {
        this.abajo_4 = abajo_4;
    }

    public BufferedImage getArriba_1() {
        return arriba_1;
    }

    public void setArriba_1(BufferedImage arriba_1) {
        this.arriba_1 = arriba_1;
    }

    public BufferedImage getArriba_2() {
        return arriba_2;
    }

    public void setArriba_2(BufferedImage arriba_2) {
        this.arriba_2 = arriba_2;
    }

    public BufferedImage getArriba_3() {
        return arriba_3;
    }

    public void setArriba_3(BufferedImage arriba_3) {
        this.arriba_3 = arriba_3;
    }

    public BufferedImage getArriba_4() {
        return arriba_4;
    }

    public void setArriba_4(BufferedImage arriba_4) {
        this.arriba_4 = arriba_4;
    }

    public BufferedImage getDerecha_1() {
        return derecha_1;
    }

    public void setDerecha_1(BufferedImage derecha_1) {
        this.derecha_1 = derecha_1;
    }

    public BufferedImage getDerecha_2() {
        return derecha_2;
    }

    public void setDerecha_2(BufferedImage derecha_2) {
        this.derecha_2 = derecha_2;
    }

    public BufferedImage getDerecha_3() {
        return derecha_3;
    }

    public void setDerecha_3(BufferedImage derecha_3) {
        this.derecha_3 = derecha_3;
    }

    public BufferedImage getDerecha_4() {
        return derecha_4;
    }

    public void setDerecha_4(BufferedImage derecha_4) {
        this.derecha_4 = derecha_4;
    }

    public BufferedImage getIzquierda_1() {
        return izquierda_1;
    }

    public void setIzquierda_1(BufferedImage izquierda_1) {
        this.izquierda_1 = izquierda_1;
    }

    public BufferedImage getIzquierda_2() {
        return izquierda_2;
    }

    public void setIzquierda_2(BufferedImage izquierda_2) {
        this.izquierda_2 = izquierda_2;
    }

    public BufferedImage getIzquierda_3() {
        return izquierda_3;
    }

    public void setIzquierda_3(BufferedImage izquierda_3) {
        this.izquierda_3 = izquierda_3;
    }

    public BufferedImage getIzquierda_4() {
        return izquierda_4;
    }

    public void setIzquierda_4(BufferedImage izquierda_4) {
        this.izquierda_4 = izquierda_4;
    }

    public String getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(String movimiento) {
        this.movimiento = movimiento;
    }

    public int getContadorPatrones() {
        return contadorPatrones;
    }

    public void setContadorPatrones(int contadorPatrones) {
        this.contadorPatrones = contadorPatrones;
    }

    public int getNumPatrones() {
        return numPatrones;
    }

    public void setNumPatrones(int numPatrones) {
        this.numPatrones = numPatrones;
    }

    public Rectangle getAreaEntidades_colision() {
        return areaEntidades_colision;
    }

    public void setAreaEntidades_colision(Rectangle areaEntidades_colision) {
        this.areaEntidades_colision = areaEntidades_colision;
    }

    public int getAreaPorDEFECTO_colisionX() {
        return areaPorDEFECTO_colisionX;
    }

    public void setAreaPorDEFECTO_colisionX(int areaPorDEFECTO_colisionX) {
        this.areaPorDEFECTO_colisionX = areaPorDEFECTO_colisionX;
    }

    public int getAreaPorDEFECTO_colisionY() {
        return areaPorDEFECTO_colisionY;
    }

    public void setAreaPorDEFECTO_colisionY(int areaPorDEFECTO_colisionY) {
        this.areaPorDEFECTO_colisionY = areaPorDEFECTO_colisionY;
    }

    public boolean isHayColisiones() {
        return hayColisiones;
    }

    public void setHayColisiones(boolean hayColisiones) {
        this.hayColisiones = hayColisiones;
    }

    public BufferedImage getImagen() {
        return imagen;
    }

    public void setImagen(BufferedImage imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre_ingredientes() {
        return nombre_ingredientes;
    }

    public void setNombre_ingredientes(String nombre_ingredientes) {
        this.nombre_ingredientes = nombre_ingredientes;
    }

    public boolean isColisones() {
        return colisones;
    }

    public void setColisones(boolean colisones) {
        this.colisones = colisones;
    }

    public int getBloqueoDeAccionesporSegundo() {
        return bloqueoDeAccionesporSegundo;
    }

    public void setBloqueoDeAccionesporSegundo(int bloqueoDeAccionesporSegundo) {
        this.bloqueoDeAccionesporSegundo = bloqueoDeAccionesporSegundo;
    }

    public String[][] getDialogos() {
        return dialogos;
    }

    public void setDialogos(String[][] dialogos) {
        this.dialogos = dialogos;
    }

    public int getDialogosIndex() {
        return dialogosIndex;
    }

    public void setDialogosIndex(int dialogosIndex) {
        this.dialogosIndex = dialogosIndex;
    }

    public int getDialogosPaginado() {
        return dialogosPaginado;
    }

    public void setDialogosPaginado(int dialogosPaginado) {
        this.dialogosPaginado = dialogosPaginado;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getNombre_jugador() {
        return nombre_jugador;
    }

    public void setNombre_jugador(String nombre_jugador) {
        this.nombre_jugador = nombre_jugador;
    }

    public int getHamburgesas_hechas() {
        return hamburgesas_hechas;
    }

    public void setHamburgesas_hechas(int hamburgesas_hechas) {
        this.hamburgesas_hechas = hamburgesas_hechas;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public Entidades getObjeto_ver_pan() {
        return objeto_ver_pan;
    }

    public void setObjeto_ver_pan(Entidades objeto_ver_pan) {
        this.objeto_ver_pan = objeto_ver_pan;
    }

    public Entidades getObjeto_ver_lechuga() {
        return objeto_ver_lechuga;
    }

    public void setObjeto_ver_lechuga(Entidades objeto_ver_lechuga) {
        this.objeto_ver_lechuga = objeto_ver_lechuga;
    }

    public Entidades getObjeto_ver_carne() {
        return objeto_ver_carne;
    }

    public void setObjeto_ver_carne(Entidades objeto_ver_carne) {
        this.objeto_ver_carne = objeto_ver_carne;
    }

    public Entidades getObjeto_ver_queso() {
        return objeto_ver_queso;
    }

    public void setObjeto_ver_queso(Entidades objeto_ver_queso) {
        this.objeto_ver_queso = objeto_ver_queso;
    }

    public int getValor_pan() {
        return valor_pan;
    }

    public void setValor_pan(int valor_pan) {
        this.valor_pan = valor_pan;
    }

    public int getValor_lechuga() {
        return valor_lechuga;
    }

    public void setValor_lechuga(int valor_lechuga) {
        this.valor_lechuga = valor_lechuga;
    }

    public int getValor_carne() {
        return valor_carne;
    }

    public void setValor_carne(int valor_carne) {
        this.valor_carne = valor_carne;
    }

    public int getValor_queso() {
        return valor_queso;
    }

    public void setValor_queso(int valor_queso) {
        this.valor_queso = valor_queso;
    }
}
