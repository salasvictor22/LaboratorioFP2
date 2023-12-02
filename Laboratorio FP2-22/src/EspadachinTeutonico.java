public class EspadachinTeutonico extends Soldado {
    private int numeroJabalinas;
    private int nivelEvolucion;
    private static int contador = 0;
    
    public EspadachinTeutonico() {       
        contador++;
        numeroJabalinas = 10000;
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
            numeroJabalinas += nivelEvolucion;
            System.out.println("Espadachín Teutónico evolucionó a nivel " + nivelEvolucion);
        } else {
            System.out.println("Espadachín Teutónico ya está en su nivel máximo de evolución.");
        }
    }
    public void lanzarJabalina() {
        if (numeroJabalinas > 0) {
            System.out.println("Espadachín Teutónico lanzó una jabalina con precisión.");
            numeroJabalinas--;
        } else {
            System.out.println("Espadachín Teutónico no tiene jabalinas para lanzar. Evoluciona para obtener más.");
        }
    }
    public void modoTortuga() {
        System.out.println("Espadachín Teutónico activó el modo tortuga. Defensa especial activada.");
    }
}