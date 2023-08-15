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
        int entidad_Lado_Izquierdo_MundoX = entidades.planoMundoX + entidades.areaEntidades_colision.x;
        int entidad_Lado_Derecho_MundoX = entidades.planoMundoX + entidades.areaEntidades_colision.x
                + entidades.areaEntidades_colision.width;
        int entidad_Lado_Superior_MundoY = entidades.planoMundoY + entidades.areaEntidades_colision.y;
        int entidad_Lado_Inferior_MundoY = entidades.planoMundoY + entidades.areaEntidades_colision.y
                + entidades.areaEntidades_colision.height;

        int entidades_Columna_Izquierda = entidad_Lado_Izquierdo_MundoX / cj.tamaño_finalObjeto;
        int entidades_Columna_Derecha = entidad_Lado_Derecho_MundoX / cj.tamaño_finalObjeto;
        int entidades_Fila_Superior = entidad_Lado_Superior_MundoY / cj.tamaño_finalObjeto;
        int entidades_Fila_Inferior = entidad_Lado_Inferior_MundoY / cj.tamaño_finalObjeto;

        int mosaico_1, mosaico_2;

        switch (entidades.movimiento) {
            case "arriba" -> {
                entidades_Fila_Superior = (entidad_Lado_Superior_MundoY - entidades.velocidad) / cj.tamaño_finalObjeto;
                mosaico_1 = cj.mosaicos.numero_de_Mosaico_Mapa[entidades_Columna_Izquierda][entidades_Fila_Superior];
                mosaico_2 = cj.mosaicos.numero_de_Mosaico_Mapa[entidades_Columna_Derecha][entidades_Fila_Superior];
                if (cj.mosaicos.mosaico[mosaico_1].colision_conMosaicos == true ||
                        cj.mosaicos.mosaico[mosaico_2].colision_conMosaicos == true) {
                    entidades.hayColisiones = true;
                }
            }
            case "abajo" -> {
                entidades_Fila_Inferior = (entidad_Lado_Inferior_MundoY + entidades.velocidad) / cj.tamaño_finalObjeto;
                mosaico_1 = cj.mosaicos.numero_de_Mosaico_Mapa[entidades_Columna_Izquierda][entidades_Fila_Inferior];
                mosaico_2 = cj.mosaicos.numero_de_Mosaico_Mapa[entidades_Columna_Derecha][entidades_Fila_Inferior];
                if (cj.mosaicos.mosaico[mosaico_1].colision_conMosaicos == true ||
                        cj.mosaicos.mosaico[mosaico_2].colision_conMosaicos == true) {
                    entidades.hayColisiones = true;
                }
            }
            case "izquierda" -> {
                entidades_Columna_Izquierda = (entidad_Lado_Izquierdo_MundoX - entidades.velocidad)
                        / cj.tamaño_finalObjeto;
                mosaico_1 = cj.mosaicos.numero_de_Mosaico_Mapa[entidades_Columna_Izquierda][entidades_Fila_Superior];
                mosaico_2 = cj.mosaicos.numero_de_Mosaico_Mapa[entidades_Columna_Izquierda][entidades_Fila_Inferior];
                if (cj.mosaicos.mosaico[mosaico_1].colision_conMosaicos == true ||
                        cj.mosaicos.mosaico[mosaico_2].colision_conMosaicos == true) {
                    entidades.hayColisiones = true;
                }
            }
            case "derecha" -> {
                entidades_Columna_Derecha = (entidad_Lado_Derecho_MundoX + entidades.velocidad) / cj.tamaño_finalObjeto;
                mosaico_1 = cj.mosaicos.numero_de_Mosaico_Mapa[entidades_Columna_Derecha][entidades_Fila_Superior];
                mosaico_2 = cj.mosaicos.numero_de_Mosaico_Mapa[entidades_Columna_Derecha][entidades_Fila_Inferior];
                if (cj.mosaicos.mosaico[mosaico_1].colision_conMosaicos == true ||
                        cj.mosaicos.mosaico[mosaico_2].colision_conMosaicos == true) {
                    entidades.hayColisiones = true;
                }
            }
            default -> throw new AssertionError();
        }
    }

    public int dectarObjetoEntidad(Entidades entidades, boolean jugador) {
        int indice = 999;
        for (int i = 0; i < cj.obj.length; i++) {
            if (cj.obj[i] != null) {
                // Obtener el area de colision de la entidad
                entidades.areaEntidades_colision.x = entidades.planoMundoX + entidades.areaEntidades_colision.x;
                entidades.areaEntidades_colision.y = entidades.planoMundoY + entidades.areaEntidades_colision.y;
                // Obtener la posicion del area de colision del objeto
                cj.obj[i].areaEntidades_colision.x = cj.obj[i].planoMundoX + cj.obj[i].areaEntidades_colision.x;
                cj.obj[i].areaEntidades_colision.y = cj.obj[i].planoMundoY + cj.obj[i].areaEntidades_colision.y;
                switch (entidades.movimiento) {
                    case "arriba" -> {
                        entidades.areaEntidades_colision.y -= entidades.velocidad;
                        if (entidades.areaEntidades_colision.intersects(cj.obj[i].areaEntidades_colision)) {
                            if (cj.obj[i].colisones == true) {
                                entidades.hayColisiones = true;
                            }
                            if (jugador == true) {
                                indice = i;
                            }
                        }
                    }
                    case "abajo" -> {
                        entidades.areaEntidades_colision.y += entidades.velocidad;
                        if (entidades.areaEntidades_colision.intersects(cj.obj[i].areaEntidades_colision)) {
                            if (cj.obj[i].colisones == true) {
                                entidades.hayColisiones = true;
                            }
                            if (jugador == true) {
                                indice = i;
                            }
                        }
                    }
                    case "izquierda" -> {
                        entidades.areaEntidades_colision.x -= entidades.velocidad;
                        if (entidades.areaEntidades_colision.intersects(cj.obj[i].areaEntidades_colision)) {
                            if (cj.obj[i].colisones == true) {
                                entidades.hayColisiones = true;
                            }
                            if (jugador == true) {
                                indice = i;
                            }
                        }
                    }
                    case "derecha" -> {
                        entidades.areaEntidades_colision.x += entidades.velocidad;
                        if (entidades.areaEntidades_colision.intersects(cj.obj[i].areaEntidades_colision)) {
                            if (cj.obj[i].colisones == true) {
                                entidades.hayColisiones = true;
                            }
                            if (jugador == true) {
                                indice = i;
                            }
                        }
                    }
                    default -> throw new AssertionError();
                }
                entidades.areaEntidades_colision.x = entidades.areaPorDEFECTO_colisionX;
                entidades.areaEntidades_colision.y = entidades.areaPorDEFECTO_colisionY;
                cj.obj[i].areaEntidades_colision.x = cj.obj[i].areaPorDEFECTO_colisionX;
                cj.obj[i].areaEntidades_colision.y = cj.obj[i].areaPorDEFECTO_colisionY;
            }
        }
        return indice;
    }

    public int verEntidadNPC(Entidades entidades, Entidades[] objetivo) {
        int indice = 999;
        for (int i = 0; i < objetivo.length; i++) {
            if (objetivo[i] != null) {
                // Obtener el area de colision de la entidad
                entidades.areaEntidades_colision.x = entidades.planoMundoX + entidades.areaEntidades_colision.x;
                entidades.areaEntidades_colision.y = entidades.planoMundoY + entidades.areaEntidades_colision.y;
                // Obtener la posicion del area de colision del objeto
                objetivo[i].areaEntidades_colision.x = objetivo[i].planoMundoX + objetivo[i].areaEntidades_colision.x;
                objetivo[i].areaEntidades_colision.y = objetivo[i].planoMundoY + objetivo[i].areaEntidades_colision.y;
                switch (entidades.movimiento) {
                    case "arriba" -> {
                        entidades.areaEntidades_colision.y -= entidades.velocidad;
                        if (entidades.areaEntidades_colision.intersects(objetivo[i].areaEntidades_colision)) {
                            entidades.hayColisiones = true;
                            indice = i;
                        }
                    }
                    case "abajo" -> {
                        entidades.areaEntidades_colision.y += entidades.velocidad;
                        if (entidades.areaEntidades_colision.intersects(objetivo[i].areaEntidades_colision)) {
                            entidades.hayColisiones = true;
                            indice = i;
                        }
                    }
                    case "izquierda" -> {
                        entidades.areaEntidades_colision.x -= entidades.velocidad;
                        if (entidades.areaEntidades_colision.intersects(objetivo[i].areaEntidades_colision)) {
                            entidades.hayColisiones = true;
                            indice = i;
                        }
                    }
                    case "derecha" -> {
                        entidades.areaEntidades_colision.x += entidades.velocidad;
                        if (entidades.areaEntidades_colision.intersects(objetivo[i].areaEntidades_colision)) {
                            entidades.hayColisiones = true;
                            indice = i;
                        }
                    }
                    default -> throw new AssertionError();
                }
                entidades.areaEntidades_colision.x = entidades.areaPorDEFECTO_colisionX;
                entidades.areaEntidades_colision.y = entidades.areaPorDEFECTO_colisionY;
                objetivo[i].areaEntidades_colision.x = objetivo[i].areaPorDEFECTO_colisionX;
                objetivo[i].areaEntidades_colision.y = objetivo[i].areaPorDEFECTO_colisionY;
            }
        }
        return indice;
    }

    public void verEntidadJUGADOR(Entidades entidades) {
        // Obtener el area de colision de la entidad
        entidades.areaEntidades_colision.x = entidades.planoMundoX + entidades.areaEntidades_colision.x;
        entidades.areaEntidades_colision.y = entidades.planoMundoY + entidades.areaEntidades_colision.y;
        // Obtener la posicion del area de colision del objeto
        cj.jugador.areaEntidades_colision.x = cj.jugador.planoMundoX + cj.jugador.areaEntidades_colision.x;
        cj.jugador.areaEntidades_colision.y = cj.jugador.planoMundoY + cj.jugador.areaEntidades_colision.y;
        switch (entidades.movimiento) {
            case "arriba" -> {
                entidades.areaEntidades_colision.y -= entidades.velocidad;
                if (entidades.areaEntidades_colision.intersects(cj.jugador.areaEntidades_colision)) {
                    entidades.hayColisiones = true;
                }
            }
            case "abajo" -> {
                entidades.areaEntidades_colision.y += entidades.velocidad;
                if (entidades.areaEntidades_colision.intersects(cj.jugador.areaEntidades_colision)) {
                    entidades.hayColisiones = true;
                }
            }
            case "izquierda" -> {
                entidades.areaEntidades_colision.x -= entidades.velocidad;
                if (entidades.areaEntidades_colision.intersects(cj.jugador.areaEntidades_colision)) {
                    entidades.hayColisiones = true;
                }
            }
            case "derecha" -> {
                entidades.areaEntidades_colision.x += entidades.velocidad;
                if (entidades.areaEntidades_colision.intersects(cj.jugador.areaEntidades_colision)) {
                    entidades.hayColisiones = true;
                }
            }
            default -> throw new AssertionError();
        }
        entidades.areaEntidades_colision.x = entidades.areaPorDEFECTO_colisionX;
        entidades.areaEntidades_colision.y = entidades.areaPorDEFECTO_colisionY;
        cj.jugador.areaEntidades_colision.x = cj.jugador.areaPorDEFECTO_colisionX;
        cj.jugador.areaEntidades_colision.y = cj.jugador.areaPorDEFECTO_colisionY;
    }
}
