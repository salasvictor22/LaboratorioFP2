public class CaballeroMoro extends Soldado {
    private int numeroFlechasLanzables;
    private int nivelEvolucion;
    private static int contador = 0;
    
    public CaballeroMoro() {
        contador++;
        numeroFlechasLanzables = 10000;
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
            numeroFlechasLanzables += nivelEvolucion;
            System.out.println("Caballero Moro evolucionó a nivel " + nivelEvolucion);
        } else {
            System.out.println("Caballero Moro ya está en su nivel máximo de evolución.");
        }
    }
    public void lanzarFlecha() {
        if (numeroFlechasLanzables > 0) {
            System.out.println("Caballero Moro lanzó una flecha con precisión.");
            numeroFlechasLanzables--;
        } else {
            System.out.println("Caballero Moro no tiene flechas para lanzar. Evoluciona para obtener más.");
        }
    }
}