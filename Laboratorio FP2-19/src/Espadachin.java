public class Espadachin extends Soldado {
    private int longitudEspada;
    
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