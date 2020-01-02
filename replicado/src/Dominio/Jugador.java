package Dominio;

public class Jugador implements Comparable<Jugador> {

    private String nombre;
    private int edad;
    private String alias;
    private int partidas;
    private int partidasGanadas;
    private String fichaColor;

    public Jugador() {
        this.setPartidas(0);
        this.setPartidasGanadas(0);
        this.setPartidasGanadas(0);
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int unaEdad) {
        edad = unaEdad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String unNombre) {
        nombre = unNombre;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String unAlias) {
        alias = unAlias;
    }

    public int getPartidas() {
        return partidas;
    }

    public void setPartidas(int unasPartidas) {
        partidas = unasPartidas;
    }

    public int getPartidasGanadas() {
        return partidasGanadas;
    }

    public void setPartidasGanadas(int unasPartidasGanadas) {
        partidasGanadas = unasPartidasGanadas;
    }

    @Override
    
    /** Se modifica el metodo compareTo para comparar las partidas 
     * ganadas, y asi ordenar la lista en forma decreciente en dicho factor.
     * @param unJugador el jugador que se utiliza para ir ordenando la lista.
     * @return un integer entre 0 y 1.
     * @author Nicolas Vega
     * @author Guillermo Ramirez
     */
    public int compareTo(Jugador unJugador) {
        int comparePartidasGanadas = ((Jugador) unJugador).getPartidasGanadas();
        return comparePartidasGanadas - this.partidasGanadas;
    }

    @Override
    
    /** Se modifica el metodo equals para comparar los alias 
     * de los jugadores registrados con el que se desea registrar. 
     * @param obj objeto que se desea comparar.
     * @return un booleano que dira si ya existe o no dicho alias.
     * @author Nicolas Vega
     * @author Guillermo Ramirez
     */
    public boolean equals(Object obj) {
        return this.getAlias().toUpperCase().equals(((Jugador) obj).getAlias().toUpperCase());
    }

    /** Se le asigna un color al jugador al comienzo de la partida 
     * para evitar el uso de los turnos fuera de la clase main y tener una solucion mas limpia. 
     * @param color color de jugador, debe ser "Rojo" o en otro caso tomara por
     * defecto que se habla del jugador azul.
     * @author Nicolas Vega
     * @author Guillermo Ramirez
     */
    public void setColorFichas(String color) {
        if (color.equals("Rojo")) {
            fichaColor = "Rojo"; //Ficha roja
        } else {
            fichaColor = "Azul"; // Ficha azul
        }
    }
    
    /**
     * Devuelve el color de la ficha de dicho jugador
     * @return color de la ficha en formato String
     * @author Nicolas Vega
     * @author Guillermo Ramirez
     */
    public String getFichaColor() {
        return this.fichaColor;
    }
}
