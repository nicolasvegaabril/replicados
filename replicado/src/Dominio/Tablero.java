package Dominio;

public class Tablero {

    int[][] tablero;
    int fichasRojas = 5;
    int fichasAzules = 5;

    public Tablero(int dimension) {
        int[][] posiciones = new int[dimension + 2][7];
        for (int i = 0; i < dimension + 2; i++) {
            for (int j = 0; j < 7; j++) {
                if (i == 0 || j == 0 || i == dimension + 1 || j == 6) {
                    posiciones[i][j] = -1;
                } else {
                    posiciones[i][j] = 0;
                }
                if ((i == 1) && (j > 0 && j < 6)) {
                    posiciones[i][j] = 1;//ROJO
                }
                if ((i == dimension) && (j > 0 && j < 6)) {
                    posiciones[i][j] = 2;//AZUL
                }
            }
        }
        tablero = posiciones;
    }

    public int[][] getTablero() {
        return tablero;
    }

    public void setTablero(int[][] unTableroMatriz) {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                tablero[i][j] = unTableroMatriz[i][j];
            }
        }
    }

    public void setFichasRojas(int unasFichas) {
        fichasRojas = unasFichas;
    }

    public void setFichasAzules(int unasFichas) {
        fichasAzules = unasFichas;
    }

    public int getFichasRojas() {
        return fichasRojas;
    }

    public int getFichasAzules() {
        return fichasAzules;
    }

    /**
     * Devuelve el largo del tablero para checkear en parametros.
     * @return Integer correspondiente al largo.
     * @author Nicolas Vega
     * @author Guillermo Ramirez
     */
    public int getLength() {     
        return tablero.length;
    }

    /**
     * Imprime la matriz del tablero en el formato con fichas deseado.
     * @author Nicolas Vega
     * @author Guillermo Ramirez
     */
    public void printTablero() {
        int largo = tablero.length;
        System.out.println("  1 2 3 4 5");
        System.out.println(" +-+-+-+-+-+");
        char[] letras = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        for (int i = 0; i < largo - 1; i++) {
            if (i > 0) {
                System.out.print(letras[i - 1]);
            }
            for (int j = 0; j < 7; j++) {
                if ((i > 0 && i <= largo - 2) && (j > 0 && j <= 5)) {
                    System.out.print("|");
                    if (tablero[i][j] == 1) {
                        System.out.print("\u001B[31m" + "\u0298" + "\u001B[0m"); // Codigo para imprimir ficha Roja
                    }
                    if (tablero[i][j] == 2) {
                        System.out.print("\u001B[34m" + "\u0298" + "\u001B[0m"); // Codigo para imprimir ficha Azul
                    }
                    if (tablero[i][j] == 0) {
                        System.out.print(" ");
                    }
                }
            }
            if (i > 0) {
                System.out.println("|");
                System.out.println(" +-+-+-+-+-+");
            }
        }
    }
}
