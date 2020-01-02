package Interface;

import java.util.*;

public class Consola {

    public static void mostrarOpciones() {
        System.out.println("-------------------------------------------------");
        System.out.println("-               Elija una opcion                -");
        System.out.println("-------------------------------------------------");
        System.out.println("-  1) Registrar jugador                         -");
        System.out.println("-  2) Jugar Replicado                           -");
        System.out.println("-  3) Ver ranking de jugadores                  -");
        System.out.println("-  4) Salir                                     -");
        System.out.println("-------------------------------------------------");
    }

    public static void mostrarFinalizacion() {
        System.out.println("-------------------------------------------------");
        System.out.println("-       Elija una opcion de finalizacion        -");
        System.out.println("-------------------------------------------------");
        System.out.println("-  1) Sin fichas                                -");
        System.out.println("-  2) Cantidad de turnos                        -");
        System.out.println("-------------------------------------------------");
    }

    public static int leerIntEnRango(int minimo, int maximo) {
        int dato = 0;
        Scanner input = new Scanner(System.in);
        boolean correcto = false;
        while (!correcto) {
            try {
                dato = input.nextInt();
                if (dato > minimo && dato < maximo) {
                    correcto = true;
                } else {
                    System.out.println("Datos incorrectos, vuelva a ingresarlos");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error, ingrese un numero");
                input.nextLine();
            }
        }
        return dato;
    }

    public static String[] leerComando(int dimension) { //divide el comando en un array
        Scanner in = new Scanner(System.in);
        boolean correcto = false;
        String entrada = "";
        boolean checkparametros;
        String[] comando = {};
        while (!correcto) {
            try {
                entrada = in.nextLine().toUpperCase();
                comando = entrada.split("\\s+");
                if (!comando[0].equals("X") && !comando[0].equals("E")){
                    if (entrada.split("\\s+").length == 5 && checkParametros(entrada.split("\\s+"), dimension)) {
                        correcto = true;
                    } else {
                        System.out.println("Datos erroneos, vuelva a ingresarlos");
                    }
                } else {correcto = true;}
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Error, vuelva a ingresar el movimiento");
            }
        }

        return comando;
    }

    public static String leerModo() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public static int leerFilas() {
        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }

    public static String leerAlias() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public static int leerEdad() {
        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }

    public static String leerNombre() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public static boolean checkParametros(String[] unArray, int dimension) {
        boolean esCorrecto = true;
        if (unArray[0].equalsIgnoreCase("X")) {
            esCorrecto = false;
        }
        try {
            if (esCorrecto && (Integer.parseInt(unArray[0]) > 2 || Integer.parseInt(unArray[0]) < 0)) {    // Checkea que el tablero sea 1 o 2
                esCorrecto = false;
            }
        } catch (NumberFormatException e) {
            esCorrecto = false;
        }

        if (esCorrecto && unArray[1].length() == 2) {
            if ((int) (unArray[1].charAt(0) - 64) < 0 || (int) unArray[1].charAt(0) - 64 > dimension - 2) { // Checkea que se le de una letra de la a a la ultima letra del tablero
                esCorrecto = false;
            }
        }

        if (esCorrecto) {
            String letra = unArray[2].toUpperCase();
            String[] arrDirecciones = {"N", "NE", "E", "SE", "S", "SO", "O", "NO"};

            if (!Arrays.asList(arrDirecciones).contains(letra)) {   // Checkea si le paso una de las direcciones
                esCorrecto = false;
            }
        }

        if (esCorrecto) {
            try {
                if (Integer.parseInt(unArray[3]) > dimension) {
                    esCorrecto = false;
                }
            } catch (NumberFormatException e) {
                esCorrecto = false;
            }
        }

        if (esCorrecto) {
            try {
                if (Character.getNumericValue(unArray[4].charAt(1)) > dimension) {
                }
                if (unArray[4].length() == 2) {    // Checkea que el length sea 2  
                    if ((int) (unArray[4].charAt(0) - 64) < 0 || (int) unArray[4].charAt(0) - 64 > dimension - 2) { //checkea que se le de una letra 
                        esCorrecto = false;                                                                         //desde la A hasta la ultima letra del tablero
                    }
                }
            } catch (NumberFormatException e) {
                esCorrecto = false;
            }
        }

        return esCorrecto;
    }

}
