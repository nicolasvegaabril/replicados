package Dominio;

import Interface.Consola;
import java.util.*;

public class Sistema {

    ArrayList<Jugador> listaJugadores = new ArrayList<>(); // Donde se guardaran los jugadores

    Partida partida = new Partida();
    Jugador jugadorRojo;
    Jugador jugadorAzul;
    boolean cortarPartida = false;
    boolean porTurnos = false;
    int turnos = 1;
    int cantidadTurnos;

    /**
     * Crea un nuevo jugador con los datos de nombre, edad y alias pasado por
     * consola
     *
     * @param unNombre Nombre del jugador a registrar
     * @param unaEdad Edad del jugador a registrar
     * @param unAlias Alias del jugador a registrar
     * @return boolean diciendo si NO existe dicho jugador
     * @author Nicolas Vega
     * @author Guillermo Ramirez
     */
    public boolean registrarJugador(String unNombre, int unaEdad, String unAlias) {
        Jugador jugaux = new Jugador();
        boolean noExiste;
        jugaux.setAlias(unAlias);
        if (!listaJugadores.contains(jugaux)) {
            // Busca si ya existe un jugador con ese alias (contains con override)
            jugaux.setNombre(unNombre);
            jugaux.setEdad(unaEdad);
            listaJugadores.add(jugaux);
            noExiste = true;
        } else {
            // Pide re-ingreso del alias
            System.out.println("Ese alias ya existe, por favor ingrese uno diferente.");
            noExiste = false;
        }
        return noExiste;
    }

    /**
     * Ordena y muestra la lista de jugadores registrados si existe al menos uno
     * en la lista.
     *
     * @author Nicolas Vega
     * @author Guillermo Ramirez
     */
    public void mostrarRanking() {
        if (this.listaJugadores.isEmpty()) {
            System.out.println("No se ha ingresado ningun jugador");
        } else {
            Collections.sort(listaJugadores);             // Ordenar segun partidas ganadas
            // Comienza a mostrar los jugadores
            System.out.println("-----------------------------------------------------");
            System.out.println("   Alias        Partidas jugadas    Partidas Ganadas ");
            System.out.println("-----------------------------------------------------");
            int indice = 0;
            Iterator<Jugador> iter = this.listaJugadores.iterator();
            while (iter.hasNext()) { // Itera hasta que no haya un siguiente
                indice++;
                Jugador j = iter.next();
                System.out.print(indice + "  ");
                System.out.printf("%-25s", j.getAlias());
                System.out.printf("%-20d", j.getPartidas());
                System.out.println(j.getPartidasGanadas());
                System.out.println("-----------------------------------------------------");
            }
        }
    }

    /**
     * Lee el comando de movimiento desde la consola. Comprueba que el
     * movimiento sea valido, y si lo es lo realiza.
     *
     * @param partida la partida actual.
     * @param jugador el jugador que realiza el movimiento.
     * @return boolean estableciendo si el movimiento es correcto o no.
     * @author Nicolas Vega
     * @author Guillermo Ramirez
     */
    public boolean jugadas(Partida partida, Jugador jugador) {
        boolean esCorrecto = false;
        System.out.println("Ingrese el movimiento del jugador " + jugador.getAlias() + " de color " + jugador.getFichaColor());
        String[] input = Consola.leerComando(partida.tablero1.getLength());
        if (input[0].equals("E") || input[0].equals("X")) { // Si el jugador abandona o pide el empate
            if (input[0].equals("E")) { 
                if (porTurnos){
                turnos = cantidadTurnos + 1;
                } else {
                    cortarPartida = true;
                }
            } else {
                if (jugador.getFichaColor().equals("Rojo")) {
                    jugadorAzul.setPartidasGanadas(jugadorAzul.getPartidasGanadas() + 1);
                } else {
                    jugadorRojo.setPartidasGanadas(jugadorRojo.getPartidasGanadas() + 1);
                }
                if (porTurnos){
                turnos = cantidadTurnos + 1;
                } else {
                    cortarPartida = true;
                }
            }
        } else {
            if (partida.checkPreparacionMovimiento(input, jugador)) { // Si el movimiento de preparacion es valido el programa sigue
                esCorrecto = true;
            } else {    //si no es valido lo notifica
                System.out.println("Preparacion no valida");
            }
            if (esCorrecto) { // Si la preparacion es correcta, se valida la replica
                if (partida.checkReplicaMovimiento(input, jugador)) {
                } else {
                    System.out.println("Replica no valida");
                    esCorrecto = false;
                }
            }
            if (esCorrecto) {
                partida.movimiento(input);
            }
        }
        return esCorrecto;
    }

    /**
     * Metodo central del juego. Al ejecutarse se pasa por la seleccion de
     * jugadores de una lista. Pide parametros de tamaño de tablero y la opcion
     * por la cual finalizara el juego. Se encarga de elegir el tipo de
     * finalizacion y ejecutar las funciones correspondientes. Todos los datos
     * son validados.
     *
     * @author Nicolas Vega
     * @author Guillermo Ramirez
     */
    public void jugar() {
        this.mostrarRanking();
        if (this.listaJugadores.size() > 1) {
            System.out.println("Elija de la lista al jugador 1...");
            int indiceJugador1 = Consola.leerIntEnRango(0, this.listaJugadores.size()) - 1; //Se resta uno por el borde del tablero
            jugadorRojo = this.listaJugadores.get(indiceJugador1);
            jugadorRojo.setColorFichas("Rojo");
            System.out.println("Elija de la lista al jugador 2...");
            boolean noSeleccionado = false;
            int indiceJugador2 = Consola.leerIntEnRango(0, this.listaJugadores.size() + 1) - 1;
            while (!noSeleccionado) {
                if (indiceJugador1 != indiceJugador2) {
                    noSeleccionado = true;
                } else {
                    noSeleccionado = false;
                    System.out.println("Ese jugador ya fue seleccionado, ingrese uno diferente...");
                    indiceJugador2 = Consola.leerIntEnRango(0, this.listaJugadores.size() + 1) - 1;
                }
            }
            jugadorAzul = this.listaJugadores.get(indiceJugador2);
            jugadorAzul.setColorFichas("Azul");
            System.out.println("Ingrese el largo de tablero, de 5 a 7...");
            this.partida.crearTableros();
            Consola.mostrarFinalizacion();
            if (Consola.leerIntEnRango(0, 3) == 1) {
                porTurnos = false;// Final por fichas
                this.partida.tablero1.printTablero();
                this.partida.tablero2.printTablero();
                System.out.println("-------------");
                boolean hayFichas = true;
                Jugador jugador;
                turnos = 1;
                boolean esCorrecto;
                turnos = 1;
                while (hayFichas) {
                    if ((turnos % 2) == 0) {
                        jugador = jugadorAzul;
                    } else {
                        jugador = jugadorRojo;
                    }
                    esCorrecto = jugadas(this.partida, jugador);
                    if (esCorrecto) {
                        turnos++;
                    }
                    hayFichas = this.partida.checkFichasRestantes(jugadorRojo, jugadorAzul);
                    if (cortarPartida) {
                        hayFichas = false;
                    }
                }
            } else { // Final por turnos
                porTurnos = true;
                System.out.println("Ingrese la cantidad de turnos...");
                cantidadTurnos = Consola.leerIntEnRango(0, Integer.MAX_VALUE) * 2;
                this.partida.tablero1.printTablero();
                this.partida.tablero2.printTablero();
                Jugador jugador;

                boolean esCorrecto;
                while (turnos <= cantidadTurnos) {
                    if ((turnos % 2) == 0) {
                        jugador = jugadorAzul;
                    } else {
                        jugador = jugadorRojo;
                    }
                    esCorrecto = jugadas(this.partida, jugador);
                    if (esCorrecto) {
                        turnos++;
                    }
                }
                this.partida.checkMayoriaColumnas(jugadorRojo, jugadorAzul);
            }
            cortarPartida = false;
            turnos = 1;
        } else {
            if (this.listaJugadores.size() == 1) {
                System.out.println("Registre jugador mas");
            }
        }
    }
}
