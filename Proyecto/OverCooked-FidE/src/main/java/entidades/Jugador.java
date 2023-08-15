/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import juego_restaurante.Componentes_juego;

import objeto.Objetos_Ingrediente_Carne;
import objeto.Objetos_Ingrediente_Lechuga;
import objeto.Objetos_Ingrediente_Pan;
import objeto.Objetos_Ingrediente_Queso;
import tecleado_juego.Teclado;

/**
 *
 * @author Gabriel
 */
public class Jugador extends Entidades {
    Teclado teclas;
    public final int pantallaX;
    public final int pantallaY;
    int puntosHamburguesas = 0;
    public ArrayList<Entidades> inventario = new ArrayList<>();
    public final int maximoTamañoInventario=5;

    public Jugador(Componentes_juego cj, Teclado teclas) {
        super(cj);
        this.teclas = teclas;
        // Donde esta ubicado el jugador con respecto al mapa y a su imagen
        // Se basa en el txt del mapa
        pantallaX = cj.anchoPantalla / 2 - (cj.tamaño_finalObjeto / 2);
        pantallaY = cj.largoPantalla / 2 - (cj.tamaño_finalObjeto / 2);

        areaEntidades_colision = new Rectangle();
        areaEntidades_colision.x = 15;
        areaEntidades_colision.y = 20;
        areaPorDEFECTO_colisionX = areaEntidades_colision.x;
        areaPorDEFECTO_colisionY = areaEntidades_colision.y;
        areaEntidades_colision.width = 22;
        areaEntidades_colision.height = 22;

        setValoresPorDefecto();
        getImagenJugador();
    }

    public void setValoresPorDefecto() {
        // Se inserta el jugador en una area del mapa
        planoMundoX = cj.tamaño_finalObjeto * 35;
        planoMundoY = cj.tamaño_finalObjeto * 15;
        // Se inserta la velocidad en la que el jugador recorrera 
        // el mapa 
        velocidad = 3;
        // Se inserta el movimiento por default que tendra el jugador al iniciar
        // en el juego en este caso mira hacia abajo
        movimiento = "abajo";
        // Se insertan las estadisticas del jugador 
        nombre_jugador= "Chef Experto";
        hamburgesas_hechas= 0;
        puntos=0;
        objeto_ver_pan = new Objetos_Ingrediente_Pan(cj);
        objeto_ver_carne =new Objetos_Ingrediente_Carne(cj);
        objeto_ver_queso =new Objetos_Ingrediente_Queso(cj);
        objeto_ver_lechuga =new Objetos_Ingrediente_Lechuga(cj);
        valor_pan= getValorPan();
        valor_carne= getValorCarne();
        valor_queso= getValorQueso();
        valor_lechuga= getValorLechuga();
    }


    public int getValorPan(){
        return valor_pan = objeto_ver_pan.valor_pan;
    }
    public int getValorCarne(){
        return valor_carne = objeto_ver_pan.valor_carne;
    }
    public int getValorQueso(){
        return valor_queso = objeto_ver_pan.valor_queso;
    }
    public int getValorLechuga(){
        return valor_lechuga = objeto_ver_pan.valor_lechuga;
    }

    public void getImagenJugador() {
        abajo_1 = estructura("/img_jugador_chef/Abajo", "caminandoAbajo_0",cj.tamaño_finalObjeto,cj.tamaño_finalObjeto);
        abajo_2 = estructura("/img_jugador_chef/Abajo", "caminandoAbajo_1",cj.tamaño_finalObjeto,cj.tamaño_finalObjeto);
        abajo_3 = estructura("/img_jugador_chef/Abajo", "caminandoAbajo_2",cj.tamaño_finalObjeto,cj.tamaño_finalObjeto);
        abajo_4 = estructura("/img_jugador_chef/Abajo", "caminandoAbajo_3",cj.tamaño_finalObjeto,cj.tamaño_finalObjeto);

        arriba_1 = estructura("/img_jugador_chef/Arriba", "caminandoArriba_0",cj.tamaño_finalObjeto,cj.tamaño_finalObjeto);
        arriba_2 = estructura("/img_jugador_chef/Arriba", "caminandoArriba_1",cj.tamaño_finalObjeto,cj.tamaño_finalObjeto);
        arriba_3 = estructura("/img_jugador_chef/Arriba", "caminandoArriba_2",cj.tamaño_finalObjeto,cj.tamaño_finalObjeto);
        arriba_4 = estructura("/img_jugador_chef/Arriba", "caminandoArriba_3",cj.tamaño_finalObjeto,cj.tamaño_finalObjeto);

        derecha_1 = estructura("/img_jugador_chef/Derecha", "caminandoDerecha_0",cj.tamaño_finalObjeto,cj.tamaño_finalObjeto);
        derecha_2 = estructura("/img_jugador_chef/Derecha", "caminandoDerecha_1",cj.tamaño_finalObjeto,cj.tamaño_finalObjeto);
        derecha_3 = estructura("/img_jugador_chef/Derecha", "caminandoDerecha_2",cj.tamaño_finalObjeto,cj.tamaño_finalObjeto);
        derecha_4 = estructura("/img_jugador_chef/Derecha", "caminandoDerecha_3",cj.tamaño_finalObjeto,cj.tamaño_finalObjeto);

        izquierda_1 = estructura("/img_jugador_chef/Izquierda", "caminandoIzquierda_0",cj.tamaño_finalObjeto,cj.tamaño_finalObjeto);
        izquierda_2 = estructura("/img_jugador_chef/Izquierda", "caminandoIzquierda_1",cj.tamaño_finalObjeto,cj.tamaño_finalObjeto);
        izquierda_3 = estructura("/img_jugador_chef/Izquierda", "caminandoIzquierda_2",cj.tamaño_finalObjeto,cj.tamaño_finalObjeto);
        izquierda_4 = estructura("/img_jugador_chef/Izquierda", "caminandoIzquierda_3",cj.tamaño_finalObjeto,cj.tamaño_finalObjeto);
    }

    // Cambia el frame de la imagen a uno diferente en cual patron este, y en que direccion
    // esta el jugador
    @Override
    public void dibujar(Graphics2D g2) {
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
        g2.drawImage(imagen, pantallaX, pantallaY, null);
    }
    
    public void implementar_Acciones_Objetos(int i) {
        if (i != 999) {
            int numeroOrden;
            String nombreObjeto = cj.obj[i].nombre;
            switch (nombreObjeto) {
                //AGREGAR OPCION CORRESPONDIENTE A ORDEN DE HAMBURGUESA PARA QUE NO COGA 1000 DE LAS MISMAS
                case "Hamburguesa_Completa":
                    cj.primerEncolado = cj.cola.buscarPrimerEncolado();
                    if(cj.tecla.presionado_E==false){
                        break;
                    }
                    if (valor_lechuga>0 && valor_queso >0
                            && valor_carne >0&& valor_pan >0){
                        break;
                    }
                    if (cj.primerEncolado == null) {
                        break;
                    }
                    numeroOrden = cj.primerEncolado.getNumeroRandom();
                    if(numeroOrden!=0){
                        break;
                    }
                    
                    hamburgesas_hechas +=1;
                    puntos += 300; 
                    valor_carne--;
                    valor_lechuga--;
                    valor_pan--;
                    valor_queso--;
                    cj.nodoEliminar=cj.cola.atiende();
                    cj.obj[i] = null;
                    cj.usarEfecto_Sonido(3);
                    
                    break;
                case "Hamburguesa__Carne":
                    cj.primerEncolado = cj.cola.buscarPrimerEncolado();
                    if(cj.tecla.presionado_E==false){
                        break;
                    }
                    if (valor_carne >0&& valor_pan >0){
                        break;  
                    }if (cj.primerEncolado == null) {
                        break;
                    }
                    numeroOrden = cj.primerEncolado.getNumeroRandom();
                    if(numeroOrden!=2){
                        break;
                    }
                        hamburgesas_hechas  +=1;
                        puntos += 100; 
                        valor_carne--;
                        valor_pan--;
                        cj.nodoEliminar=cj.cola.atiende();
                        cj.obj[i] = null;
                        cj.usarEfecto_Sonido(1);
                    
                    break;
                case "Hamburguesa__Carne_Queso":
                    cj.primerEncolado = cj.cola.buscarPrimerEncolado();
                    if(cj.tecla.presionado_E==false){
                        break;
                    }
                    if (valor_carne >0&& valor_pan >0
                            && valor_queso >0){
                        break;
                    }if (cj.primerEncolado == null) {
                        break;
                    }
                    numeroOrden = cj.primerEncolado.getNumeroRandom();
                    if(numeroOrden!=1){
                        break;
                    }
                    hamburgesas_hechas  += 1;
                    puntos += 200; 
                    valor_carne--;
                    valor_pan--;
                    valor_queso--;
                    cj.nodoEliminar=cj.cola.atiende();
                    cj.obj[i] = null;
                    cj.usarEfecto_Sonido(2);
                    
                    break;
                case "Puerta":
                    cj.obj[i] = null;
                    cj.usarEfecto_Sonido(6);
                    break;
            }
        }

    }
    // Implementa Acciones del NPC cuando la tecla presionada sea Enter y este en
    // el estado de juego Dialogos
    public void implementar_Acciones_NPC(int i) {
        if (i != 999) {
            if (cj.tecla.presionadoEnter_dialogos == true) {

                cj.npc[i].hablaNPC();
            }
        }
    }


    @Override
    public void update() {
        if (teclas.arriba == true || teclas.abajo == true ||
                teclas.derecha == true || teclas.izquierda == true) {

            if (teclas.arriba == true) {
                // MOVIMIENTO ARRIBA
                movimiento = "arriba";

            }else if (teclas.abajo == true) {
                // MOVIMIENTO ABAJO
                movimiento = "abajo";
            } else if (teclas.izquierda == true) {
                // MOVIMIENTO IZQUIERDA
                movimiento = "izquierda";
            } else if (teclas.derecha == true) {
                // MOVIMIENTO DERECHA
                movimiento = "derecha";
            }
            // Detecta las colisiones con los mosaicos
            hayColisiones = false;
            cj.dc.dectarMosaicoEntidad(this);

            // verifica la colision con "Entidades"
            int indiceOBJ = cj.dc.dectarObjetoEntidad(this, true);
            implementar_Acciones_Objetos(indiceOBJ);
            int indiceNPC = cj.dc.verEntidadNPC(this, cj.npc);
            implementar_Acciones_NPC(indiceNPC);
            // Colisiones con "Entidaes" Jugador no tiene velocidad, no puede traspasarlo
            cj.tecla.presionadoEnter_dialogos = false;
            if (hayColisiones == false) {
                switch (movimiento) {
                    case "arriba":
                        planoMundoY -= velocidad;
                        break;
                    case "abajo":
                        planoMundoY += velocidad;
                        break;
                    case "izquierda":
                        planoMundoX -= velocidad;
                        break;
                    case "derecha":
                        planoMundoX += velocidad;
                        break;
                    default:
                        throw new AssertionError();
                }

                // Para mover en diagonales se necesita un if (aun no es resuelto como hacerlo con un swtich)
                // if (movimiento.equals("arriba")) {
                //     planoMundoY -= velocidad;
                //     // MOVER EN DIAGONALES
                //     // if (teclas.izquierda == true){
                //     //     planoMundoX -= (velocidad-1.4);
                //     // }
                //     // if (teclas.derecha == true){
                //     //     planoMundoX += (velocidad-1.4);
                //     // }
                // } else if (movimiento.equals("abajo")) {
                //     planoMundoY += velocidad;
                //     // MOVER EN DIAGONALES
                //     // if (teclas.izquierda == true){
                //     //     planoMundoX -= (velocidad-1.4);
                //     // }
                //     // if (teclas.derecha == true){
                //     //     planoMundoX += (velocidad-1.4);
                //     // }
                // } else if (movimiento.equals("izquierda")) {
                //     planoMundoX -= velocidad;
                // } else if (movimiento.equals("derecha")) {
                //     planoMundoX += velocidad;
                // } else {
                //     throw new AssertionError();
                // }
            }
            // Cantidad de frames por segundo en el que el presonaje va a cambiar su animacion al caminar
            // entre mas frames mas lento va 
            contadorPatrones++;
            if (contadorPatrones > 13) {
                switch (numPatrones) {
                    case 1 -> numPatrones = 2;
                    case 2 -> numPatrones = 3;
                    case 3 -> numPatrones = 4;
                    case 4 -> numPatrones = 1;
                    default -> {
                    }
                }
                contadorPatrones = 0;
            }
        }

    }

}
