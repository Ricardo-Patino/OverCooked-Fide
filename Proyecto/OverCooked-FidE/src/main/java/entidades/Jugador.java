/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import inventario.Inventario;
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
    private final int pantallaX;
    private final int pantallaY;
    private int puntosHamburguesas = 0;
    private Inventario inventarioIngredientesMostrarJugador = new Inventario();

    public Jugador(Componentes_juego cj, Teclado teclas) {
        super(cj);
        this.teclas = teclas;
        // Donde esta ubicado el jugador con respecto al mapa y a su imagen
        // Se basa en el txt del mapa
        pantallaX = cj.getAnchoPantalla() / 2 - (cj.getTamaño_finalObjeto() / 2);
        pantallaY = cj.getLargoPantalla() / 2 - (cj.getTamaño_finalObjeto() / 2);

        setAreaEntidades_colision(new Rectangle(15,20,22,22));
        setAreaPorDEFECTO_colisionX (15);
        setAreaPorDEFECTO_colisionY(22);

        setValoresPorDefecto();
        getImagenJugador();
    }

    public void setValoresPorDefecto() {
        // Se inserta el jugador en una area del mapa
        setPlanoMundoX(cj.getTamaño_finalObjeto() * 35);
        setPlanoMundoY (cj.getTamaño_finalObjeto() * 15);
        // Se inserta la velocidad en la que el jugador recorrera 
        // el mapa 
        setVelocidad(3);
        // Se inserta el movimiento por default que tendra el jugador al iniciar
        // en el juego en este caso mira hacia abajo
        setMovimiento( "abajo");
        // Se insertan las estadisticas del jugador 
        setNombre_jugador( "Chef Experto");
        setHamburgesas_hechas( 0);
        setPuntos(0);
        setObjeto_ver_pan( new Objetos_Ingrediente_Pan(cj));
        setObjeto_ver_carne(new Objetos_Ingrediente_Carne(cj));
        setObjeto_ver_queso(new Objetos_Ingrediente_Queso(cj));
        setObjeto_ver_lechuga(new Objetos_Ingrediente_Lechuga(cj));
        setValor_pan(getValorPan());
        setValor_carne( getValorCarne());
        setValor_queso( getValorQueso());
        setValor_lechuga( getValorLechuga());
    }


    public int getValorPan(){
        return getObjeto_ver_pan().getValor_pan();
    }
    public int getValorCarne(){
        return getObjeto_ver_pan().getValor_carne();
    }
    public int getValorQueso(){
        return getObjeto_ver_pan().getValor_queso();
    }
    public int getValorLechuga(){
        return getObjeto_ver_pan().getValor_lechuga();
    }


    public void getImagenJugador() {
        setAbajo_1(estructura("/img_jugador_chef/Abajo", "caminandoAbajo_0",cj.getTamaño_finalObjeto(),cj.getTamaño_finalObjeto()));
        setAbajo_2(estructura("/img_jugador_chef/Abajo", "caminandoAbajo_1",cj.getTamaño_finalObjeto(),cj.getTamaño_finalObjeto()));
        setAbajo_3(estructura("/img_jugador_chef/Abajo", "caminandoAbajo_2",cj.getTamaño_finalObjeto(),cj.getTamaño_finalObjeto()));
        setAbajo_4(estructura("/img_jugador_chef/Abajo", "caminandoAbajo_3",cj.getTamaño_finalObjeto(),cj.getTamaño_finalObjeto()));

        setArriba_1(estructura("/img_jugador_chef/Arriba", "caminandoArriba_0",cj.getTamaño_finalObjeto(),cj.getTamaño_finalObjeto()));
        setArriba_2(estructura("/img_jugador_chef/Arriba", "caminandoArriba_1",cj.getTamaño_finalObjeto(),cj.getTamaño_finalObjeto()));
        setArriba_3(estructura("/img_jugador_chef/Arriba", "caminandoArriba_2",cj.getTamaño_finalObjeto(),cj.getTamaño_finalObjeto()));
        setArriba_4(estructura("/img_jugador_chef/Arriba", "caminandoArriba_3",cj.getTamaño_finalObjeto(),cj.getTamaño_finalObjeto()));

        setDerecha_1(estructura("/img_jugador_chef/Derecha", "caminandoDerecha_0",cj.getTamaño_finalObjeto(),cj.getTamaño_finalObjeto()));
        setDerecha_2(estructura("/img_jugador_chef/Derecha", "caminandoDerecha_1",cj.getTamaño_finalObjeto(),cj.getTamaño_finalObjeto()));
        setDerecha_3(estructura("/img_jugador_chef/Derecha", "caminandoDerecha_2",cj.getTamaño_finalObjeto(),cj.getTamaño_finalObjeto()));
        setDerecha_4(estructura("/img_jugador_chef/Derecha", "caminandoDerecha_3",cj.getTamaño_finalObjeto(),cj.getTamaño_finalObjeto()));

        setIzquierda_1(estructura("/img_jugador_chef/Izquierda", "caminandoIzquierda_0",cj.getTamaño_finalObjeto(),cj.getTamaño_finalObjeto()));
        setIzquierda_2(estructura("/img_jugador_chef/Izquierda", "caminandoIzquierda_1",cj.getTamaño_finalObjeto(),cj.getTamaño_finalObjeto()));
        setIzquierda_3(estructura("/img_jugador_chef/Izquierda", "caminandoIzquierda_2",cj.getTamaño_finalObjeto(),cj.getTamaño_finalObjeto()));
        setIzquierda_4(estructura("/img_jugador_chef/Izquierda", "caminandoIzquierda_3",cj.getTamaño_finalObjeto(),cj.getTamaño_finalObjeto()));
    }

    // Cambia el frame de la imagen a uno diferente en cual patron este, y en que direccion
    // esta el jugador
    @Override
    public void dibujar(Graphics2D g2) {
        BufferedImage imagen = null;
        switch (getMovimiento()) {
            case "arriba" -> {
                if (getNumPatrones() == 1) {
                    imagen = getArriba_1();
                }
                if (getNumPatrones() == 2) {
                    imagen = getArriba_2();
                }
                if (getNumPatrones() == 3) {
                    imagen = getArriba_3();
                }
                if (getNumPatrones() == 4) {
                    imagen = getArriba_4();
                }
            }
            case "abajo" -> {
                if (getNumPatrones() == 1) {
                    imagen = getAbajo_1();
                }
                if (getNumPatrones() == 2) {
                    imagen = getAbajo_2();
                }
                if (getNumPatrones() == 3) {
                    imagen = getAbajo_3();
                }
                if (getNumPatrones() == 4) {
                    imagen = getAbajo_4();
                }
            }
            case "izquierda" -> {
                if (getNumPatrones() == 1) {
                    imagen = getIzquierda_1();
                }
                if (getNumPatrones() == 2) {
                    imagen = getIzquierda_2();
                }
                if (getNumPatrones() == 3) {
                    imagen = getIzquierda_3();
                }
                if (getNumPatrones() == 4) {
                    imagen = getIzquierda_4();
                }
            }
            case "derecha" -> {
                if (getNumPatrones() == 1) {
                    imagen = getDerecha_1();
                }
                if (getNumPatrones() == 2) {
                    imagen = getDerecha_2();
                }
                if (getNumPatrones() == 3) {
                    imagen = getDerecha_3();
                }
                if (getNumPatrones() == 4) {
                    imagen = getDerecha_4();
                }
            }
            default -> throw new AssertionError();
        }
        g2.drawImage(imagen, pantallaX, pantallaY, null);
    }
    
    public void implementar_Acciones_Objetos(int i) {
        if (i != 999) {
            int numeroOrden;
            String nombreObjeto = cj.getObj()[i].getNombre();
            int agregarHamburgesas=getHamburgesas_hechas();
            int puntaje = getPuntos();
            int cantidadCarne = getValor_carne();
            int cantidadLechuga = getValor_lechuga();
            int cantidadPan= getValor_pan();
            int cantidadQueso = getValor_queso();
            switch (nombreObjeto) {
                //AGREGAR OPCION CORRESPONDIENTE A ORDEN DE HAMBURGUESA PARA QUE NO COGA 1000 DE LAS MISMAS
                case "Hamburguesa_Completa":
                    cj.setPrimerEncolado(cj.getCola().buscarPrimerEncolado());
                    if(cj.getTecla().isPresionado_E()==false){
                        break;
                    }
                    if (getValor_lechuga()==0 || getValor_queso()==0  
                            || getValor_carne()==0 || getValor_pan()==0){
                        break;
                    }
                    if (cj.getPrimerEncolado() == null) {
                        break;
                    }
                    numeroOrden = cj.getPrimerEncolado().getNumeroRandom();
                    if(numeroOrden!=0 ){
                        break;
                    }
                    setHamburgesas_hechas(agregarHamburgesas+=1);
                    setPuntos(puntaje+=300); 
                    setValor_carne(cantidadCarne-=1);
                    setValor_lechuga(cantidadLechuga-=1);
                    setValor_pan(cantidadPan-=1);
                    setValor_queso(cantidadQueso-=1);
                    cj.setNodoEliminar(cj.getCola().atiende());
                    cj.getObj()[i]=null;
                    cj.usarEfecto_Sonido(3);
                    
                    break;
                case "Hamburguesa__Carne":
                    cj.setPrimerEncolado(cj.getCola().buscarPrimerEncolado());
                    if(cj.getTecla().isPresionado_E()==false){
                        break;
                    }
                    if (getValor_pan()==0   || getValor_carne()==0   ){
                        break;  
                    }
                    if (cj.getPrimerEncolado() == null) {
                        break;
                    }
                    numeroOrden = cj.getPrimerEncolado().getNumeroRandom();
                    if(numeroOrden!=2){
                        break;
                    }
                        setHamburgesas_hechas(agregarHamburgesas+=1);
                        setPuntos(puntaje+=100); 
                        setValor_carne(cantidadCarne-=1);
                        setValor_pan(cantidadPan-=1);
                        cj.setNodoEliminar(cj.getCola().atiende());
                        cj.getObj()[i]=null;
                        cj.usarEfecto_Sonido(1);
                    
                    break;
                case "Hamburguesa__Carne_Queso":
                    cj.setPrimerEncolado(cj.getCola().buscarPrimerEncolado());
                    if(cj.getTecla().isPresionado_E()==false){
                        break;
                    }
                    if (getValor_pan()==0   || getValor_carne()==0   
                            || getValor_queso()==0  ){
                        break;
                    }
                    if (cj.getPrimerEncolado() == null) {
                        break;
                    }
                    numeroOrden = cj.getPrimerEncolado().getNumeroRandom();
                    if(numeroOrden!=1){
                        break;
                    }
                    setHamburgesas_hechas(agregarHamburgesas+=1);
                    setPuntos(puntaje+=200); 
                    setValor_carne(cantidadCarne-=1);
                    setValor_pan(cantidadPan-=1);
                    setValor_queso(cantidadQueso-=1);
                    cj.setNodoEliminar(cj.getCola().atiende());
                    cj.getObj()[i]=null;
                    cj.usarEfecto_Sonido(2);
                    
                    break;
                case "Puerta":
                    cj.getObj()[i]=null;
                    cj.usarEfecto_Sonido(6);
                    break;
            }
        }

    }
    // Implementa Acciones del NPC cuando la tecla presionada sea Enter y este en
    // el estado de juego Dialogos
    public void implementar_Acciones_NPC(int i) {
        if (i != 999) {
            if (cj.getTecla().isPresionadoEnter_dialogos() == true) {

                cj.getNpc()[i].hablaNPC();
            }
        }
    }


    @Override
    public void update() {
        if (teclas.isArriba() == true || teclas.isAbajo() == true ||
                teclas.isDerecha() == true || teclas.isIzquierda() == true) {

            if (teclas.isArriba() == true) {
                // MOVIMIENTO ARRIBA
                setMovimiento("arriba");

            }else if (teclas.isAbajo()== true) {
                // MOVIMIENTO ABAJO
                setMovimiento("abajo");
            } else if (teclas.isIzquierda() == true) {
                // MOVIMIENTO IZQUIERDA
                setMovimiento("izquierda");
            } else if (teclas.isDerecha()  == true) {
                // MOVIMIENTO DERECHA
                setMovimiento("derecha");
            }
            // Detecta las colisiones con los mosaicos
            setHayColisiones(false);
            cj.getDc().dectarMosaicoEntidad(this);

            // verifica la colision con "Entidades"
            int indiceOBJ = cj.getDc().dectarObjetoEntidad(this, true);
            implementar_Acciones_Objetos(indiceOBJ);
            int indiceNPC = cj.getDc().verEntidadNPC(this, cj.getNpc());
            implementar_Acciones_NPC(indiceNPC);
            // Colisiones con "Entidaes" Jugador no tiene velocidad, no puede traspasarlo
            cj.getTecla().setPresionadoEnter_dialogos(false);

            if (isHayColisiones() == false) {
                int velocity =getVelocidad();
                //duda
                int Y = getPlanoMundoY();
                int X = getPlanoMundoX();
                switch (getMovimiento()) {
                    case "arriba":
                        setPlanoMundoY(Y -=velocity);
                        break;
                    case "abajo":
                        setPlanoMundoY(Y += velocity);
                        break;
                    case "izquierda":
                        setPlanoMundoX(X -= velocity);
                        break;
                    case "derecha":
                        setPlanoMundoX(X += velocity);
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
            int count = getContadorPatrones();
            setContadorPatrones(count+=1);
            if (getContadorPatrones() > 13) {
                switch (getNumPatrones()) {
                    case 1 -> setNumPatrones(2);
                    case 2 -> setNumPatrones(3);
                    case 3 -> setNumPatrones(4);
                    case 4 -> setNumPatrones(1);
                    default -> {
                    }
                }
                setContadorPatrones(0);
            }
        }

    }

    public int getPantallaX() {
        return pantallaX;
    }

    public int getPantallaY() {
        return pantallaY;
    }

    public int getPuntosHamburguesas() {
        return puntosHamburguesas;
    }

    public void setPuntosHamburguesas(int puntosHamburguesas) {
        this.puntosHamburguesas = puntosHamburguesas;
    }

    public Inventario getInventarioIngredientesMostrarJugador() {
        return inventarioIngredientesMostrarJugador;
    }

    public void setInventarioIngredientesMostrarJugador(Inventario inventarioIngredientesMostrarJugador) {
        this.inventarioIngredientesMostrarJugador = inventarioIngredientesMostrarJugador;
    }

    
}
