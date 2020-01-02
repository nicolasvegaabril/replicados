package Dominio;

import Interface.Consola;

public class Main {

    public static void main(String[] args) {
        
        Sistema sistema = new Sistema();
        Consola.mostrarOpciones();  //Muestra tabla de opciones
        boolean seguir = true;
        int opcion;
        while (seguir) { 
            opcion = Consola.leerIntEnRango(0, 5); //lee opcion ingresada   
                switch (opcion) {
                    case 1:  // opcion registrar jugador
                        System.out.println("Ingrese el nombre:");
                        String nombre = Consola.leerNombre();
                        System.out.println("Ingrese la edad:");
                        int edad = Consola.leerIntEnRango(0, Integer.MAX_VALUE);
                        boolean noExiste = false;
                        System.out.println("Ingrese el alias:");
                        while (!noExiste) {
                            String alias = Consola.leerAlias();
                            noExiste = sistema.registrarJugador(nombre, edad, alias);
                        }
                        break;
                    case 2: //opcion jugar replicado
                        sistema.jugar();
                        break;
                    case 3: //opcion ver ranking
                        sistema.mostrarRanking();
                        break;
                    case 4: //opcion salir
                        seguir = false;
                        break;
                }
            Consola.mostrarOpciones();
        }
    }
}
