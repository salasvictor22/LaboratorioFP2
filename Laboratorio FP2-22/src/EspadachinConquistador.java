public class EspadachinConquistador extends Soldado {
    private int numeroHachasLanzables;
    private int nivelEvolucion;
    private static int contador = 0;
    
    public EspadachinConquistador() {             
        contador++;
        numeroHachasLanzables = 10000;
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
            numeroHachasLanzables += nivelEvolucion;
            System.out.println("Espadachín Conquistador evolucionó a nivel " + nivelEvolucion);
        } else {
            System.out.println("Espadachín Conquistador ya está en su nivel máximo de evolución.");
        }
    }
    public void lanzarHacha() {
        if (numeroHachasLanzables > 0) {
            System.out.println("Espadachín Conquistador lanzó un hacha con precisión.");
            numeroHachasLanzables--;
        } else {
            System.out.println("Espadachín Conquistador no tiene hachas para lanzar. Evoluciona para obtener más.");
        }
    }
}


