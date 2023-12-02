public class EspadachinReal extends Soldado {
    private int numeroCuchillos;
    private int nivelEvolucion;
    private static int contador = 0;

    public EspadachinReal() {
    	contador++;
        numeroCuchillos = 10000;
        nivelEvolucion = 1;
    }
    public static void reiniciarContadores() {
        contador = 0;
    }
    public static int getContadorEspadachin() {
        return contador;
    }
    public void evolucionar() {
        if (nivelEvolucion < 4) {
            nivelEvolucion++;
            numeroCuchillos += nivelEvolucion;
            System.out.println("Espadachín Real evolucionó a nivel " + nivelEvolucion);
        } else {
            System.out.println("Espadachín Real ya está en su nivel máximo de evolución.");
        }
    }
    public void lanzarCuchillo() {
        if (numeroCuchillos > 0) {
            System.out.println("Espadachín Real lanzó un cuchillo con precisión.");
            numeroCuchillos--;
        } else {
            System.out.println("Espadachín Real no tiene cuchillos para lanzar. Evoluciona para obtener más.");
        }
    }

}