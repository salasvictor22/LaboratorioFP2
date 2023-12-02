public class CaballeroFranco extends Soldado{
    private int numeroLanzas;
    private int nivelEvolucion;
    private static int contador = 0;
    
    public CaballeroFranco() {        
        contador++;
        numeroLanzas = 10000;
        nivelEvolucion = 1;      
    }   
    public static void reiniciarContadores() {
        contador = 0;
    }
    public static int getContadorCaballero() {
        return contador;
    }
    public void evolucionar() {
        if (nivelEvolucion < 4) {
            nivelEvolucion++;
            numeroLanzas += nivelEvolucion;
            System.out.println("Caballero Franco evolucionó a nivel " + nivelEvolucion);
        } else {
            System.out.println("Caballero Franco ya está en su nivel máximo de evolución.");
        }
    }   
    public void lanzarLanza() {
        if (numeroLanzas > 0) {
            System.out.println("Caballero Franco lanzó una lanza con precisión.");
            numeroLanzas--;
        } else {
            System.out.println("Caballero Franco no tiene lanzas para lanzar. Evoluciona para obtener más.");
        }
    }
}