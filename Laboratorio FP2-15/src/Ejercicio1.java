// Ejercicio - 1
// Autor: Juan Victor Salas Aguilar
// Colaboro: Marco Aedo
// Tiempo:
import java.util.*;
public class Ejercicio1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean salir = false;
        System.out.print("Ingrese el nombre del reino que desea escoger (Inglaterra, Francia, Castilla-Aragón, Sacro\r\n"
        		+ "Imperio Romano-Germánico, Moros): ");
        String nombreReino = sc.nextLine();
        Ejercito ejercito = new Ejercito(nombreReino);
        ejercito.crearEjercito();     
        while (!salir) {
            System.out.println("\nMenú de opciones:");
            System.out.println("1. Modificar soldado");
            System.out.println("2. Eliminar soldado");
            System.out.println("3. Imprimir datos de los soldados");
            System.out.println("4. Imprimir ranking de poder del ejército");
            System.out.println("5. Soldado con mayor nivel de ataque");
            System.out.println("6. Salir");
            System.out.print("Elija una opción: ");
            int opcion = sc.nextInt();
            sc.nextLine(); 

            switch (opcion) {
                case 1:
 
                    break;
                case 2:
                    break;
                case 3:
                	System.out.println(ejercito.toString());
                    break;
                case 4:
                    ejercito.verRankingPoder();
                    break;
                case 5:
                    Soldado soldadoConMayorAtaque = ejercito.getSoldadoConMayorAtaque();
                    System.out.println("Soldado con mayor nivel de ataque: " + soldadoConMayorAtaque.getNombre());
                    break;
                case 6:                    
                    System.out.println("Saliendo del programa.");
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }
}