public class Espadachin extends Soldado {
    private int longitudEspada;
    private static int contadorEspadachines = 0;
    
    public Espadachin() {       
        contadorEspadachines++;
    }
    public static void reiniciarContadores() {
        contadorEspadachines = 0;
    }
    public static int getContadorEspadachines() {
        return contadorEspadachines;
    }
    public void setLongitudEspada(int longitud) {
        longitudEspada = longitud;
    }
    public int getLongitudEspada() {
        return longitudEspada;
    }
    public void crearMuroEscudos() {       
        System.out.println("Espadachín creando un muro de escudos.");
    }
}