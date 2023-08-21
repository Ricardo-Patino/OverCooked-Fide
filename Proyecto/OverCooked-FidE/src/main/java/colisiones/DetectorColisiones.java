/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package colisiones;

import entidades.Entidades;
import juego_restaurante.Componentes_juego;

/**
 *
 * @author Gabriel
 */
public class DetectorColisiones {
    Componentes_juego cj;

    public DetectorColisiones(Componentes_juego cj) {
        this.cj = cj;
    }

    public void dectarMosaicoEntidad(Entidades entidades) {
        int entidad_Lado_Izquierdo_MundoX = entidades.getPlanoMundoX() + entidades.getAreaEntidades_colision().x;
        int entidad_Lado_Derecho_MundoX = entidades.getPlanoMundoX() + entidades.getAreaEntidades_colision().x
                + entidades.getAreaEntidades_colision().width;
        int entidad_Lado_Superior_MundoY = entidades.getPlanoMundoY() + entidades.getAreaEntidades_colision().y;
        int entidad_Lado_Inferior_MundoY = entidades.getPlanoMundoY() + entidades.getAreaEntidades_colision().y
                + entidades.getAreaEntidades_colision().height;

        int entidades_Columna_Izquierda = entidad_Lado_Izquierdo_MundoX / cj.getTamaño_finalObjeto();
        int entidades_Columna_Derecha = entidad_Lado_Derecho_MundoX / cj.getTamaño_finalObjeto();
        int entidades_Fila_Superior = entidad_Lado_Superior_MundoY / cj.getTamaño_finalObjeto();
        int entidades_Fila_Inferior = entidad_Lado_Inferior_MundoY / cj.getTamaño_finalObjeto();

        int mosaico_1, mosaico_2;

        switch (entidades.getMovimiento()) {
            case "arriba" -> {
                entidades_Fila_Superior = (entidad_Lado_Superior_MundoY - entidades.getVelocidad()) / cj.getTamaño_finalObjeto();
                mosaico_1 = cj.getMosaicos().getNumero_de_Mosaico_Mapa()[entidades_Columna_Izquierda][entidades_Fila_Superior];
                mosaico_2 = cj.getMosaicos().getNumero_de_Mosaico_Mapa()[entidades_Columna_Derecha][entidades_Fila_Superior];
                if (cj.getMosaicos().getMosaico()[mosaico_1].colision_conMosaicos == true ||
                        cj.getMosaicos().getMosaico()[mosaico_2].colision_conMosaicos == true) {
                    entidades.setHayColisiones(true);
                }
            }
            case "abajo" -> {
                entidades_Fila_Inferior = (entidad_Lado_Inferior_MundoY + entidades.getVelocidad()) / cj.getTamaño_finalObjeto();
                mosaico_1 = cj.getMosaicos().getNumero_de_Mosaico_Mapa()[entidades_Columna_Izquierda][entidades_Fila_Inferior];
                mosaico_2 = cj.getMosaicos().getNumero_de_Mosaico_Mapa()[entidades_Columna_Derecha][entidades_Fila_Inferior];
                if (cj.getMosaicos().getMosaico()[mosaico_1].colision_conMosaicos == true ||
                        cj.getMosaicos().getMosaico()[mosaico_2].colision_conMosaicos == true) {
                    entidades.setHayColisiones(true);
                }
            }
            case "izquierda" -> {
                entidades_Columna_Izquierda = (entidad_Lado_Izquierdo_MundoX - entidades.getVelocidad())
                        / cj.getTamaño_finalObjeto();
                mosaico_1 = cj.getMosaicos().getNumero_de_Mosaico_Mapa()[entidades_Columna_Izquierda][entidades_Fila_Superior];
                mosaico_2 = cj.getMosaicos().getNumero_de_Mosaico_Mapa()[entidades_Columna_Izquierda][entidades_Fila_Inferior];
                if (cj.getMosaicos().getMosaico()[mosaico_1].colision_conMosaicos == true ||
                        cj.getMosaicos().getMosaico()[mosaico_2].colision_conMosaicos == true) {
                    entidades.setHayColisiones(true);
                }
            }
            case "derecha" -> {
                entidades_Columna_Derecha = (entidad_Lado_Derecho_MundoX + entidades.getVelocidad()) / cj.getTamaño_finalObjeto();
                mosaico_1 = cj.getMosaicos().getNumero_de_Mosaico_Mapa()[entidades_Columna_Derecha][entidades_Fila_Superior];
                mosaico_2 = cj.getMosaicos().getNumero_de_Mosaico_Mapa()[entidades_Columna_Derecha][entidades_Fila_Inferior];
                if (cj.getMosaicos().getMosaico()[mosaico_1].colision_conMosaicos == true ||
                        cj.getMosaicos().getMosaico()[mosaico_2].colision_conMosaicos == true) {
                    entidades.setHayColisiones(true);
                }
            }
            default -> throw new AssertionError();
        }
    }

    public int dectarObjetoEntidad(Entidades entidades, boolean jugador) {
        int indice = 999;
        for (int i = 0; i < cj.getObj().length; i++) {
            if (cj.getObj()[i] != null) {
                // Obtener el area de colision de la entidad
                entidades.getAreaEntidades_colision().x = entidades.getPlanoMundoX() + entidades.getAreaEntidades_colision().x;
                entidades.getAreaEntidades_colision().y = entidades.getPlanoMundoY() + entidades.getAreaEntidades_colision().y;
                // Obtener la posicion del area de colision del objeto
                cj.getObj()[i].getAreaEntidades_colision().x = cj.getObj()[i].getPlanoMundoX() + cj.getObj()[i].getAreaEntidades_colision().x;
                cj.getObj()[i].getAreaEntidades_colision().y = cj.getObj()[i].getPlanoMundoY() + cj.getObj()[i].getAreaEntidades_colision().y;
                switch (entidades.getMovimiento()) {
                    case "arriba" -> {
                        entidades.getAreaEntidades_colision().y -= entidades.getVelocidad();
                        if (entidades.getAreaEntidades_colision().intersects(cj.getObj()[i].getAreaEntidades_colision())) {
                            if (cj.getObj()[i].isColisones() == true) {
                                entidades.setHayColisiones(true);
                            }
                            if (jugador == true) {
                                indice = i;
                            }
                        }
                    }
                    case "abajo" -> {
                        entidades.getAreaEntidades_colision().y += entidades.getVelocidad();
                        if (entidades.getAreaEntidades_colision().intersects(cj.getObj()[i].getAreaEntidades_colision())) {
                            if (cj.getObj()[i].isColisones() == true) {
                                entidades.setHayColisiones(true);
                            }
                            if (jugador == true) {
                                indice = i;
                            }
                        }
                    }
                    case "izquierda" -> {
                        entidades.getAreaEntidades_colision().x -= entidades.getVelocidad();
                        if (entidades.getAreaEntidades_colision().intersects(cj.getObj()[i].getAreaEntidades_colision())) {
                            if (cj.getObj()[i].isColisones() == true) {
                                entidades.setHayColisiones(true);
                            }
                            if (jugador == true) {
                                indice = i;
                            }
                        }
                    }
                    case "derecha" -> {
                        entidades.getAreaEntidades_colision().x += entidades.getVelocidad();
                        if (entidades.getAreaEntidades_colision().intersects(cj.getObj()[i].getAreaEntidades_colision())) {
                            if (cj.getObj()[i].isColisones() == true) {
                                entidades.setHayColisiones(true);
                            }
                            if (jugador == true) {
                                indice = i;
                            }
                        }
                    }
                    default -> throw new AssertionError();
                }
                entidades.getAreaEntidades_colision().x = entidades.getAreaPorDEFECTO_colisionX();
                entidades.getAreaEntidades_colision().y = entidades.getAreaPorDEFECTO_colisionY();
                cj.getObj()[i].getAreaEntidades_colision().x = cj.getObj()[i].getAreaPorDEFECTO_colisionX();
                cj.getObj()[i].getAreaEntidades_colision().y = cj.getObj()[i].getAreaPorDEFECTO_colisionY();
            }
        }
        return indice;
    }

    public int verEntidadNPC(Entidades entidades, Entidades[] objetivo) {
        int indice = 999;
        for (int i = 0; i < objetivo.length; i++) {
            if (objetivo[i] != null) {
                // Obtener el area de colision de la entidad
                entidades.getAreaEntidades_colision().x = entidades.getPlanoMundoX() + entidades.getAreaEntidades_colision().x;
                entidades.getAreaEntidades_colision().y = entidades.getPlanoMundoY() + entidades.getAreaEntidades_colision().y;
                // Obtener la posicion del area de colision del objeto
                objetivo[i].getAreaEntidades_colision().x = objetivo[i].getPlanoMundoX() + objetivo[i].getAreaEntidades_colision().x;
                objetivo[i].getAreaEntidades_colision().y = objetivo[i].getPlanoMundoY() + objetivo[i].getAreaEntidades_colision().y;
                switch (entidades.getMovimiento()) {
                    case "arriba" -> {
                        entidades.getAreaEntidades_colision().y -= entidades.getVelocidad();
                        if (entidades.getAreaEntidades_colision().intersects(objetivo[i].getAreaEntidades_colision())) {
                            entidades.setHayColisiones(true);
                            indice = i;
                        }
                    }
                    case "abajo" -> {
                        entidades.getAreaEntidades_colision().y += entidades.getVelocidad();
                        if (entidades.getAreaEntidades_colision().intersects(objetivo[i].getAreaEntidades_colision())) {
                            entidades.setHayColisiones(true);
                            indice = i;
                        }
                    }
                    case "izquierda" -> {
                        entidades.getAreaEntidades_colision().x -= entidades.getVelocidad();
                        if (entidades.getAreaEntidades_colision().intersects(objetivo[i].getAreaEntidades_colision())) {
                            entidades.setHayColisiones(true);
                            indice = i;
                        }
                    }
                    case "derecha" -> {
                        entidades.getAreaEntidades_colision().x += entidades.getVelocidad();
                        if (entidades.getAreaEntidades_colision().intersects(objetivo[i].getAreaEntidades_colision())) {
                            entidades.setHayColisiones(true);
                            indice = i;
                        }
                    }
                    default -> throw new AssertionError();
                }
                entidades.getAreaEntidades_colision().x = entidades.getAreaPorDEFECTO_colisionX();
                entidades.getAreaEntidades_colision().y = entidades.getAreaPorDEFECTO_colisionY();
                objetivo[i].getAreaEntidades_colision().x = objetivo[i].getAreaPorDEFECTO_colisionX();
                objetivo[i].getAreaEntidades_colision().y = objetivo[i].getAreaPorDEFECTO_colisionY();
            }
        }
        return indice;
    }

    public void verEntidadJUGADOR(Entidades entidades) {
        // Obtener el area de colision de la entidad
        entidades.getAreaEntidades_colision().x = entidades.getPlanoMundoX() + entidades.getAreaEntidades_colision().x;
        entidades.getAreaEntidades_colision().y = entidades.getPlanoMundoY() + entidades.getAreaEntidades_colision().y;
        // Obtener la posicion del area de colision del objeto
        cj.getJugador().getAreaEntidades_colision().x = cj.getJugador().getPlanoMundoX() + cj.getJugador().getAreaEntidades_colision().x;
        cj.getJugador().getAreaEntidades_colision().y = cj.getJugador().getPlanoMundoY() + cj.getJugador().getAreaEntidades_colision().y;
        switch (entidades.getMovimiento()) {
            case "arriba" -> {
                entidades.getAreaEntidades_colision().y -= entidades.getVelocidad();
                if (entidades.getAreaEntidades_colision().intersects(cj.getJugador().getAreaEntidades_colision())) {
                    entidades.setHayColisiones(true);
                }
            }
            case "abajo" -> {
                entidades.getAreaEntidades_colision().y += entidades.getVelocidad();
                if (entidades.getAreaEntidades_colision().intersects(cj.getJugador().getAreaEntidades_colision())) {
                    entidades.setHayColisiones(true);
                }
            }
            case "izquierda" -> {
                entidades.getAreaEntidades_colision().x -= entidades.getVelocidad();
                if (entidades.getAreaEntidades_colision().intersects(cj.getJugador().getAreaEntidades_colision())) {
                    entidades.setHayColisiones(true);
                }
            }
            case "derecha" -> {
                entidades.getAreaEntidades_colision().x += entidades.getVelocidad();
                if (entidades.getAreaEntidades_colision().intersects(cj.getJugador().getAreaEntidades_colision())) {
                    entidades.setHayColisiones(true);
                }
            }
            default -> throw new AssertionError();
        }
        entidades.getAreaEntidades_colision().x = entidades.getAreaPorDEFECTO_colisionX();
        entidades.getAreaEntidades_colision().y = entidades.getAreaPorDEFECTO_colisionY();
        cj.getJugador().getAreaEntidades_colision().x = cj.getJugador().getAreaPorDEFECTO_colisionX();
        cj.getJugador().getAreaEntidades_colision().y = cj.getJugador().getAreaPorDEFECTO_colisionY();
    }
}
