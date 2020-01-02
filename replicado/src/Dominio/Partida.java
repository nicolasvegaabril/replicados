package Dominio;

import Interface.Consola;

public class Partida {

    Tablero tablero1;
    Tablero tablero2;
    int posicionEmpujaY;
    int posicionEmpujaX;
    boolean yaEmpuja;

    /**
     * Crea dos tableros con la dimension leida desde consola dentro del mismo
     * metodo
     * @author Nicolas Vega
     * @author Guillermo Ramirez
     */
    public void crearTableros() {
        boolean correcto = false;
        while (!correcto) {
            int dimension = Consola.leerIntEnRango(4, 8); // Se usa el rango 4-8 porque debe ser
                tablero1 = new Tablero(dimension);        // un numero entre 5 y 7
                tablero2 = new Tablero(dimension);
                correcto = true;
        }
    }

    /**
     * Se encarga de validar si es posible el movimiento en el tablero de preparacion
     * @param unMovimiento El comando ingresado en consola por el jugador
     * @param unJugador jugador que desea mover la ficha
     * @return boolean aclarando si el movimiento es posible
     * @author Nicolas Vega
     * @author Guillermo Ramirez
     */
    public boolean checkPreparacionMovimiento(String[] unMovimiento, Jugador unJugador) {
        Tablero tablero;
        if (unMovimiento[0].equals("1")) { //elige tablero preparacion por lo tanto el otro es replica
            tablero = tablero1;
        } else {
            tablero = tablero2;
        }
        int numeroJugador;
        if (unJugador.getFichaColor().equals("Rojo")) {
            numeroJugador = 1;
        } else {
            numeroJugador = 2;
        }
        boolean posible = true;
        int posicionY;
        int posicionX;
        int[][] tableroPosiciones = tablero.getTablero();
        String posicion = unMovimiento[1];
        posicionY = (int) (posicion.charAt(0) - 64);// 64 por que la matriz tiene borde, nuestra matriz real empieza en 1
        posicionX = Character.getNumericValue(posicion.charAt(1));
        String direccion = unMovimiento[2];
        boolean correcto = false;
        int pasos = 0;
        while (!correcto) {
            try {
                pasos = Integer.parseInt(unMovimiento[3]);
            } catch (NumberFormatException e) {
                posible = false;

            }
            correcto = true;
        }
        if (posible && posicionY < tableroPosiciones.length && posicionX < tableroPosiciones[0].length && posicionY > 0 && posicionX > 0) {
            if (tableroPosiciones[posicionY][posicionX] == numeroJugador) {
                switch (direccion) {
                    case "S":
                        for (int i = 1; i <= pasos && posible; i++) {
                            if (tableroPosiciones[posicionY + i][posicionX] != 0) {
                                posible = false;
                            }
                        }
                        break;
                    case "N":
                        for (int i = 1; i <= pasos && posible; i++) {
                            if (tableroPosiciones[posicionY - i][posicionX] != 0) {
                                posible = false;
                            }
                        }
                        break;
                    case "E":
                        for (int i = 1; i <= pasos && posible; i++) {
                            if (tableroPosiciones[posicionY][posicionX + i] != 0) {
                                posible = false;
                            }
                        }
                        break;
                    case "O":

                        for (int i = 1; i <= pasos && posible; i++) {
                            if (tableroPosiciones[posicionY][posicionX - i] != 0) {
                                posible = false;
                            }
                        }
                        break;
                    case "NO":
                        for (int i = 1; i <= pasos && posible; i++) {
                            if (tableroPosiciones[posicionY - i][posicionX - i] != 0) {
                                posible = false;
                            }
                        }
                        break;
                    case "NE":
                        for (int i = 1; i <= pasos && posible; i++) {
                            if (tableroPosiciones[posicionY - i][posicionX + i] != 0) {
                                posible = false;
                            }
                        }
                        break;
                    case "SE":
                        for (int i = 1; i <= pasos && posible; i++) {
                            if (tableroPosiciones[posicionY + i][posicionX + i] != 0) {
                                posible = false;
                            }
                        }
                        break;
                    case "SO":
                        for (int i = 1; i <= pasos && posible; i++) {
                            if (tableroPosiciones[posicionY + i][posicionX - i] != 0) {
                                posible = false;
                            }
                        }
                        break;
                }
            } else {
                posible = false;
            }
        } else {
            posible = false;
        }

        return posible;
    }
    
    /**
     * Se encarga de validar si es posible el movimiento en el tablero de replica
     * @param unMovimiento El comando ingresado en consola por el jugador
     * @param jugador jugador que desea mover la ficha
     * @return boolean aclarando si el movimiento es posible
     * @author Nicolas Vega
     * @author Guillermo Ramirez
     */
    public boolean checkReplicaMovimiento(String[] unMovimiento, Jugador jugador) {
        String direccion = unMovimiento[2];
        int pasos = Integer.parseInt(unMovimiento[3]);
        int indiceX;
        int indiceY;
        int pasosX;
        int pasosY;
        boolean posible = true;
        yaEmpuja = false;
        switch (direccion) {
            case "S":
                indiceX = 0;
                indiceY = 1;
                pasosX = 0;
                pasosY = pasos;
                for (int i = 1; i <= pasos && posible; i++) {
                    int iteradorPasosY = i;
                    int iteradorPasosX = 0;
                    posible = probarReplica(jugador, indiceX, indiceY, unMovimiento, iteradorPasosY, iteradorPasosX, pasosY, pasosX);
                }
                break;
            case "SO":
                indiceX = -1;
                indiceY = 1;
                pasosX = -pasos;
                pasosY = pasos;
                for (int i = 1; i <= pasos && posible; i++) {
                    int iteradorPasosY = i;
                    int iteradorPasosX = -i;
                    posible = probarReplica(jugador, indiceX, indiceY, unMovimiento, iteradorPasosY, iteradorPasosX, pasosY, pasosX);
                }
                break;
            case "O":
                indiceX = -1;
                indiceY = 0;
                pasosX = -pasos;
                pasosY = 0;
                for (int i = 1; i <= pasos && posible; i++) {
                    int iteradorPasosY = 0;
                    int iteradorPasosX = -i;
                    posible = probarReplica(jugador, indiceX, indiceY, unMovimiento, iteradorPasosY, iteradorPasosX, pasosY, pasosX);
                }
                break;
            case "NO":
                indiceX = -1;
                indiceY = -1;
                pasosX = -pasos;
                pasosY = -pasos;
                for (int i = 1; i <= pasos && posible; i++) {
                    int iteradorPasosY = -i;
                    int iteradorPasosX = -i;
                    posible = probarReplica(jugador, indiceX, indiceY, unMovimiento, iteradorPasosY, iteradorPasosX, pasosY, pasosX);
                }
                break;
            case "N":
                indiceX = 0;
                indiceY = -1;
                pasosX = 0;
                pasosY = -pasos;
                for (int i = 1; i <= pasos && posible; i++) {
                    int iteradorPasosY = -i;
                    int iteradorPasosX = 0;
                    posible = probarReplica(jugador, indiceX, indiceY, unMovimiento, iteradorPasosY, iteradorPasosX, pasosY, pasosX);
                }
                break;
            case "NE":
                indiceX = 1;
                indiceY = -1;
                pasosX = pasos;
                pasosY = -pasos;
                for (int i = 1; i <= pasos && posible; i++) {
                    int iteradorPasosY = -i;
                    int iteradorPasosX = i;
                    posible = probarReplica(jugador, indiceX, indiceY, unMovimiento, iteradorPasosY, iteradorPasosX, pasosY, pasosX);
                }
                break;
            case "E":
                indiceX = 1;
                indiceY = 0;
                pasosX = pasos;
                pasosY = 0;
                for (int i = 1; i <= pasos && posible; i++) {
                    int iteradorPasosY = 0;
                    int iteradorPasosX = i;
                    posible = probarReplica(jugador, indiceX, indiceY, unMovimiento, iteradorPasosY, iteradorPasosX, pasosY, pasosX);
                }
                break;
            case "SE":
                indiceX = 1;
                indiceY = 1;
                pasosX = pasos;
                pasosY = pasos;
                for (int i = 1; i <= pasos && posible; i++) {
                    int iteradorPasosY = i;
                    int iteradorPasosX = i;
                    posible = probarReplica(jugador, indiceX, indiceY, unMovimiento, iteradorPasosY, iteradorPasosX, pasosY, pasosX);
                }
                break;
        }
        return posible;
    }

    /**
     * Realiza el movimiento 
     * @param unMovimiento toma el comando que ingreso el usuario anteriormente
     * @author Nicolas Vega
     * @author Guillermo Ramirez
     */
    public void movimiento(String[] unMovimiento) {
        String posicion = unMovimiento[1];
        int posicionY = (int) (posicion.charAt(0) - 64);
        int posicionX = Character.getNumericValue(posicion.charAt(1));
        String direccion = unMovimiento[2];
        int pasos = Integer.parseInt(unMovimiento[3]);
        String posicionReplica = unMovimiento[4];
        int posicionReplicaY = (int) (posicionReplica.charAt(0) - 64);
        int posicionReplicaX = Character.getNumericValue(posicionReplica.charAt(1));
        Tablero prep;
        Tablero rep;
        if (unMovimiento[0].equals("1")) {//eligo tablero para preparacion
            prep = tablero1;
            rep = tablero2;
        } else {
            prep = tablero2;
            rep = tablero1;
        }
        int[][] tableroPrep = prep.getTablero();
        int[][] tableroRep = rep.getTablero();
        switch (direccion) {
            case "N":
                tableroPrep[posicionY - pasos][posicionX] = tableroPrep[posicionY][posicionX];//ficha
                tableroPrep[posicionY][posicionX] = 0;
                if (yaEmpuja) { //MOVIMIENTO REPLICA
                    if (tableroRep[posicionReplicaY - pasos - 1][posicionReplicaX] == 0) {
                        tableroRep[posicionReplicaY - pasos - 1][posicionReplicaX] = tableroRep[posicionEmpujaY][posicionEmpujaX];
                        tableroRep[posicionEmpujaY][posicionEmpujaX] = 0;
                    } else {
                        tableroRep[posicionEmpujaY][posicionEmpujaX] = 0;
                        if (tableroPrep[posicionY - pasos][posicionX] == 1) {
                            rep.setFichasAzules(rep.getFichasAzules() - 1);
                        } else {
                            rep.setFichasRojas(rep.getFichasRojas() - 1);
                        }
                    }
                    yaEmpuja = false;
                }
                tableroRep[posicionReplicaY - pasos][posicionReplicaX] = tableroRep[posicionReplicaY][posicionReplicaX];
                tableroRep[posicionReplicaY][posicionReplicaX] = 0;

                prep.setTablero(tableroPrep);
                rep.setTablero(tableroRep);
                tablero1.printTablero();
                tablero2.printTablero();
                break;
            case "NE":

                tableroPrep[posicionY - pasos][posicionX + pasos] = tableroPrep[posicionY][posicionX];//Ficha
                tableroPrep[posicionY][posicionX] = 0;
                if (yaEmpuja) { //MOVIMIENTO REPLICA
                    if (tableroRep[posicionReplicaY - pasos - 1][posicionReplicaX + pasos + 1] == 0) {
                        tableroRep[posicionReplicaY - pasos - 1][posicionReplicaX + pasos + 1] = tableroRep[posicionEmpujaY][posicionEmpujaX];

                        tableroRep[posicionEmpujaY][posicionEmpujaX] = 0;
                    } else {
                        tableroRep[posicionEmpujaY][posicionEmpujaX] = 0;
                        if (tableroPrep[posicionY - pasos][posicionX + pasos] == 1) {
                            rep.setFichasAzules(rep.getFichasAzules() - 1);
                        } else {
                            rep.setFichasRojas(rep.getFichasRojas() - 1);
                        }
                    }
                    yaEmpuja = false;
                }
                tableroRep[posicionReplicaY - pasos][posicionReplicaX + pasos] = tableroRep[posicionReplicaY][posicionReplicaX];
                tableroRep[posicionReplicaY][posicionReplicaX] = 0;

                prep.setTablero(tableroPrep);
                rep.setTablero(tableroRep);
                tablero1.printTablero();
                tablero2.printTablero();
                break;
            case "E":

                tableroPrep[posicionY][posicionX + pasos] = tableroPrep[posicionY][posicionX];// Ficha
                tableroPrep[posicionY][posicionX] = 0;
                if (yaEmpuja) { //MOVIMIENTO REPLICA
                    if (tableroRep[posicionReplicaY][posicionReplicaX + pasos + 1] == 0) {
                        tableroRep[posicionReplicaY][posicionReplicaX + pasos + 1] = tableroRep[posicionEmpujaY][posicionEmpujaX];
                        tableroRep[posicionEmpujaY][posicionEmpujaX] = 0;
                    } else {
                        tableroRep[posicionEmpujaY][posicionEmpujaX] = 0;
                        if (tableroPrep[posicionY][posicionX + pasos] == 1) {
                            rep.setFichasAzules(rep.getFichasAzules() - 1);
                        } else {
                            rep.setFichasRojas(rep.getFichasRojas() - 1);
                        }
                    }
                    yaEmpuja = false;
                }
                tableroRep[posicionReplicaY][posicionReplicaX + pasos] = tableroRep[posicionReplicaY][posicionReplicaX];
                tableroRep[posicionReplicaY][posicionReplicaX] = 0;

                prep.setTablero(tableroPrep);
                rep.setTablero(tableroRep);
                tablero1.printTablero();
                tablero2.printTablero();
                break;
            case "SE":
                tableroPrep[posicionY + pasos][posicionX + pasos] = tableroPrep[posicionY][posicionX];//ficha
                tableroPrep[posicionY][posicionX] = 0;
                if (yaEmpuja) { //MOVIMIENTO REPLICA
                    if (tableroRep[posicionReplicaY + pasos + 1][posicionReplicaX + pasos + 1] == 0) {
                        tableroRep[posicionReplicaY + pasos + 1][posicionReplicaX + pasos + 1] = tableroRep[posicionEmpujaY][posicionEmpujaX];

                        tableroRep[posicionEmpujaY][posicionEmpujaX] = 0;
                    } else {
                        tableroRep[posicionEmpujaY][posicionEmpujaX] = 0;
                        if (tableroPrep[posicionY + pasos][posicionX + pasos] == 1) {
                            rep.setFichasAzules(rep.getFichasAzules() - 1);
                        } else {
                            rep.setFichasRojas(rep.getFichasRojas() - 1);
                        }
                    }
                    yaEmpuja = false;
                }
                tableroRep[posicionReplicaY + pasos][posicionReplicaX + pasos] = tableroRep[posicionReplicaY][posicionReplicaX];
                tableroRep[posicionReplicaY][posicionReplicaX] = 0;

                prep.setTablero(tableroPrep);
                rep.setTablero(tableroRep);
                tablero1.printTablero();
                tablero2.printTablero();
                break;
            case "S":
                tableroPrep[posicionY + pasos][posicionX] = tableroPrep[posicionY][posicionX];//ficha
                tableroPrep[posicionY][posicionX] = 0;
                if (yaEmpuja) { //MOVIMIENTO REPLICA
                    if (tableroRep[posicionReplicaY + pasos + 1][posicionReplicaX] == 0) {
                        tableroRep[posicionReplicaY + pasos + 1][posicionReplicaX] = tableroRep[posicionEmpujaY][posicionEmpujaX];
                        tableroRep[posicionEmpujaY][posicionEmpujaX] = 0;
                    } else {
                        tableroRep[posicionEmpujaY][posicionEmpujaX] = 0;
                        if (tableroPrep[posicionY + pasos][posicionX] == 1) {
                            rep.setFichasAzules(rep.getFichasAzules() - 1);
                        } else {
                            rep.setFichasRojas(rep.getFichasRojas() - 1);
                        }
                    }
                    yaEmpuja = false;
                }
                tableroRep[posicionReplicaY + pasos][posicionReplicaX] = tableroRep[posicionReplicaY][posicionReplicaX];
                tableroRep[posicionReplicaY][posicionReplicaX] = 0;
                prep.setTablero(tableroPrep);
                rep.setTablero(tableroRep);
                tablero1.printTablero();
                tablero2.printTablero();
                break;
            case "SO":
                tableroPrep[posicionY + pasos][posicionX - pasos] = tableroPrep[posicionY][posicionX];//ficha
                tableroPrep[posicionY][posicionX] = 0;
                if (yaEmpuja) { //MOVIMIENTO REPLICA
                    if (tableroRep[posicionReplicaY + pasos + 1][posicionReplicaX - pasos - 1] == 0) {
                        tableroRep[posicionReplicaY + pasos + 1][posicionReplicaX - pasos - 1] = tableroRep[posicionEmpujaY][posicionEmpujaX];
                        tableroRep[posicionEmpujaY][posicionEmpujaX] = 0;
                    } else {
                        tableroRep[posicionEmpujaY][posicionEmpujaX] = 0;
                        if (tableroPrep[posicionY + pasos][posicionX - pasos] == 1) {
                            rep.setFichasAzules(rep.getFichasAzules() - 1);
                        } else {
                            rep.setFichasRojas(rep.getFichasRojas() - 1);
                        }
                    }
                    yaEmpuja = false;
                }
                tableroRep[posicionReplicaY + pasos][posicionReplicaX - pasos] = tableroRep[posicionReplicaY][posicionReplicaX];
                tableroRep[posicionReplicaY][posicionReplicaX] = 0;

                prep.setTablero(tableroPrep);
                rep.setTablero(tableroRep);
                tablero1.printTablero();
                tablero2.printTablero();
                break;
            case "O":

                tableroPrep[posicionY][posicionX - pasos] = tableroPrep[posicionY][posicionX];//ficha
                tableroPrep[posicionY][posicionX] = 0;
                if (yaEmpuja) { //MOVIMIENTO REPLICA
                    if (tableroRep[posicionReplicaY][posicionReplicaX - pasos - 1] == 0) {
                        tableroRep[posicionReplicaY][posicionReplicaX - pasos - 1] = tableroRep[posicionEmpujaY][posicionEmpujaX];
                        tableroRep[posicionEmpujaY][posicionEmpujaX] = 0;
                    } else {
                        tableroRep[posicionEmpujaY][posicionEmpujaX] = 0;
                        if (tableroPrep[posicionY][posicionX - pasos] == 1) {
                            rep.setFichasAzules(rep.getFichasAzules() - 1);
                        } else {
                            rep.setFichasRojas(rep.getFichasRojas() - 1);
                        }
                    }
                    yaEmpuja = false;
                }
                tableroRep[posicionReplicaY][posicionReplicaX - pasos] = tableroRep[posicionReplicaY][posicionReplicaX];
                tableroRep[posicionReplicaY][posicionReplicaX] = 0;

                prep.setTablero(tableroPrep);
                rep.setTablero(tableroRep);
                tablero1.printTablero();
                tablero2.printTablero();
                break;
            case "NO":

                tableroPrep[posicionY - pasos][posicionX - pasos] = tableroPrep[posicionY][posicionX];//ficha
                tableroPrep[posicionY][posicionX] = 0;
                if (yaEmpuja) { //MOVIMIENTO REPLICA
                    if (tableroRep[posicionReplicaY - pasos - 1][posicionReplicaX - pasos - 1] == 0) {
                        tableroRep[posicionReplicaY - pasos - 1][posicionReplicaX - pasos - 1] = tableroRep[posicionEmpujaY][posicionEmpujaX];
                        tableroRep[posicionEmpujaY][posicionEmpujaX] = 0;
                    } else {
                        tableroRep[posicionEmpujaY][posicionEmpujaX] = 0;
                        if (tableroPrep[posicionY - pasos][posicionX - pasos] == 1) {
                            rep.setFichasAzules(rep.getFichasAzules() - 1);
                        } else {
                            rep.setFichasRojas(rep.getFichasRojas() - 1);
                        }
                    }
                    yaEmpuja = false;
                }
                tableroRep[posicionReplicaY - pasos][posicionReplicaX - pasos] = tableroRep[posicionReplicaY][posicionReplicaX];
                tableroRep[posicionReplicaY][posicionReplicaX] = 0;

                prep.setTablero(tableroPrep);
                rep.setTablero(tableroRep);
                tablero1.printTablero();
                tablero2.printTablero();
                break;

        }

    }

    /**
     * Comprueba si quedan fichas restantes de ambos jugadores en los dos tableros
     * @param jugadorRojo jugador registrado como rojo
     * @param jugadorAzul jugador registrado como azul
     * @return  boolean si quedan fichas o no
     * @author Nicolas Vega
     * @author Guillermo Ramirez
     */
    public boolean checkFichasRestantes(Jugador jugadorRojo, Jugador jugadorAzul) {
        boolean quedanFichas = true;
        if (tablero1.getFichasAzules() == 0 || tablero2.getFichasAzules() == 0) {
            quedanFichas = false;
            System.out.println("El jugador " + jugadorRojo.getAlias() + " ha ganado la partida");
            jugadorRojo.setPartidasGanadas(jugadorRojo.getPartidasGanadas() + 1);
            sumarPartidaJugada(jugadorRojo, jugadorAzul);
        }
        if (tablero1.getFichasRojas() == 0 || tablero2.getFichasRojas() == 0) {
            quedanFichas = false;
            System.out.println("El jugador " + jugadorAzul.getAlias() + " ha ganado la partida");
            jugadorAzul.setPartidasGanadas(jugadorAzul.getPartidasGanadas() + 1);
            sumarPartidaJugada(jugadorRojo, jugadorAzul);
        }
        return quedanFichas;
    }

    /**
     * Comprueba quien tiene mas columnas con mayoria de fichas juntando ambos tableros
     * @param jugadorRojo
     * @param jugadorAzul 
     * @author Nicolas Vega
     * @author Guillermo Ramirez
     */
    public void checkMayoriaColumnas(Jugador jugadorRojo, Jugador jugadorAzul) {
        int[][] copiaTab1 = tablero1.getTablero();
        int[][] copiaTab2 = tablero2.getTablero();
        int largoY = copiaTab1.length - 1;
        int largoX = copiaTab1[0].length - 1;
        int mayoriaRojo = 0;
        int mayoriaAzul = 0;
        for (int i = 1; i < largoX; i++) {
            int fichaRoja = 0;
            int fichaAzul = 0;
            for (int j = 1; j < largoY; j++) {
                if (copiaTab1[j][i] == 1) {
                    fichaRoja++;
                }
                if (copiaTab1[j][i] == 2) {
                    fichaAzul++;
                }
                if (copiaTab2[j][i] == 1) {
                    fichaRoja++;
                }
                if (copiaTab2[j][i] == 2) {
                    fichaAzul++;
                }
            }
            if (fichaRoja > fichaAzul) {
                mayoriaRojo++;
            }
            if (fichaRoja < fichaAzul) {
                mayoriaAzul++;
            }
        }
        if (mayoriaRojo > mayoriaAzul) {
            System.out.println("El jugador " + jugadorRojo.getAlias() + " ha ganado la partida");
            jugadorRojo.setPartidasGanadas(jugadorRojo.getPartidasGanadas() + 1);
            sumarPartidaJugada(jugadorRojo, jugadorAzul);
        } else {
            if (mayoriaRojo < mayoriaAzul) {
                System.out.println("El jugador " + jugadorAzul.getAlias() + " ha ganado la partida");
                jugadorAzul.setPartidasGanadas(jugadorAzul.getPartidasGanadas() + 1);
                sumarPartidaJugada(jugadorRojo, jugadorAzul);
            } else {
                System.out.println("La partida termino en empate");
                sumarPartidaJugada(jugadorRojo, jugadorAzul);
            }
        }
    }

    /**
     * Suma a ambos jugadores una partida a su parametro de partidas jugadas al termino
     * de cada partida
     * @param jugadorRojo
     * @param jugadorAzul 
     * @author Nicolas Vega
     * @author Guillermo Ramirez
     */
    public void sumarPartidaJugada(Jugador jugadorRojo, Jugador jugadorAzul) {
        jugadorAzul.setPartidas(jugadorAzul.getPartidas() + 1);
        jugadorRojo.setPartidas(jugadorRojo.getPartidas() + 1);
    }
    
    /**
     * Pueba si es posible realizar un movimiento luego de que se le sean pasados dichos
     * parametros
     * @param jugador jugador que realiza el movimiento
     * @param indiceX incremento en sentido X de ejes cartesianos al moverse por 
     * la matriz (Derecha 1, izquierda -1, sin incremento 0)
     * @param indiceY incremento en sentido Y de ejes cartesianos al moverse por 
     * la matriz (Derecha 1, izquierda -1, sin incremento 0)
     * @param unMovimiento el comando ingresado por el jugador en consola
     * @param iteradorPasosY veces que SE HA movido en dicho eje
     * @param iteradorPasosX veces que SE HA movido en dicho eje
     * @param pasosY veces que DEBE moverse en dicho eje
     * @param pasosX veces que DEBE moverse en dicho eje
     * @return boolean aclarando si es posible el movimiento
     * @author Nicolas Vega
     * @author Guillermo Ramirez
     */
    public boolean probarReplica(Jugador jugador, int indiceX, int indiceY, String[] unMovimiento, int iteradorPasosY, int iteradorPasosX, int pasosY, int pasosX) {
        Tablero tablero;
        if (unMovimiento[0].equals("1")) { // Elige tablero preparacion por lo tanto el otro es replica
            tablero = tablero2;
        } else {
            tablero = tablero1;
        }
        int numeroJugador;
        if (jugador.getFichaColor().equals("Rojo")) {
            numeroJugador = 1;
        } else {
            numeroJugador = 2;
        }
        int[][] tableroPosiciones = tablero.getTablero();
        String posicion = unMovimiento[4];
        int posicionY = (int) (posicion.charAt(0) - 64); //64 ya que nuestra matriz real empieza en 1 (tiene borde)
        int posicionX = Character.getNumericValue(posicion.charAt(1));
        boolean posible = true;
            if (numeroJugador == 1) { //Jugador rojo
                if (tableroPosiciones[posicionY][posicionX] == numeroJugador && (posicionY + pasosY <= tablero.getLength() - 2) && (posicionX + pasosX <= tablero.getLength() - 2)) { 
                    if (tableroPosiciones[posicionY][posicionX] == 1) {
                        if (tableroPosiciones[posicionY + iteradorPasosY][posicionX + iteradorPasosX] == 2) { // Se encuentra con ficha azul
                            if (yaEmpuja) {
                                posible = false;
                            } else {
                                yaEmpuja = true;
                                posicionEmpujaY = posicionY + iteradorPasosY;//posicion de la ficha que debe empujar
                                posicionEmpujaX = posicionX + iteradorPasosX;
                                if (tableroPosiciones[posicionY + pasosY][posicionX + pasosX] == -1) { 
                                    posible = false;
                                }
                            }
                        }
                        if (yaEmpuja && tableroPosiciones[posicionY + iteradorPasosY + indiceY][posicionX + iteradorPasosX + indiceX] > 0) {
                            posible = false;
                        }
                        if (tableroPosiciones[posicionY + iteradorPasosY][posicionX + iteradorPasosX] == 1) {
                            posible = false;
                        }
                        }
                    } else {
                        posible = false;
                    }
                
            } else { //Jugador azul
                if (tableroPosiciones[posicionY][posicionX] == numeroJugador && (posicionY + pasosY <= tablero.getLength() - 2) && (posicionX + pasosX <= tablero.getLength() - 2)) {
                    if (tableroPosiciones[posicionY + iteradorPasosY][posicionX + iteradorPasosX] == 1) { //se encuentra con ficha azul
                        if (yaEmpuja) {
                            posible = false;
                        } else {
                            yaEmpuja = true;
                            posicionEmpujaY = posicionY + iteradorPasosY;//posicion de la ficha que debe empujar
                            posicionEmpujaX = posicionX + iteradorPasosX;
                            if (tableroPosiciones[posicionY + pasosY][posicionX + pasosX] == -1) { //lo empuja afuera de la matriz
                                posible = false;
                            }
                        }
                    }
                    if (yaEmpuja && tableroPosiciones[posicionY + iteradorPasosY + indiceY][posicionX + iteradorPasosX + indiceX] > 0) {
                        posible = false;
                    }
                    if (tableroPosiciones[posicionY + iteradorPasosY][posicionX + iteradorPasosX] == 2) {
                        posible = false;
                    }
                } else {
                    posible = false;
                }
            }
        return posible;

    }
}
