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
        boolean ejercitoCreado = false;
        boolean continuarCreando = false;
        while (!salir) {
            System.out.println("\nMenú de opciones:");
            if (ejercitoCreado) {              
                System.out.println("4. Eliminar soldado");
                System.out.println("5. Modificar soldado");
                System.out.println("6. Imprimir datos de los soldados");
                System.out.println("7. Soldado con mayor nivel de vida");
                System.out.println("8. Imprimir soldados por nivel de vida descendente");
                System.out.println("9. Salir");
            } else {
            	System.out.println("1. Crear ejército aleatorio");
                System.out.println("2. Crear ejército manualmente");
                System.out.println("3. Salir");
            }

            System.out.print("Elija una opción: ");
            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    ejercito.crearEjercito();
                    ejercitoCreado = true;
                    break;
                case 2:
                	System.out.println("creando su ejercito:");
                	System.out.println("crear soldado:");
                	ArrayList<Soldado> soldado = ejercito.getMisSoldados();
                	continuarCreando = true;                   
                    while (continuarCreando && soldado.size() < 10) {
                        System.out.print("Nuevo nombre: ");
                        String nuevoNombre = sc.next();
                        System.out.print("Nuevo nivel de ataque: ");
                        int nuevoNivelAtaque = sc.nextInt();
                        System.out.print("Nuevo nivel de defensa: ");
                        int nuevoNivelDefensa = sc.nextInt();                        
                        System.out.print("Nueva velocidad: ");
                        int nuevaVelocidad = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Nueva fila: ");
                        int nuevaFila = sc.nextInt();
                        System.out.print("Nueva columna: ");
                        int nuevaColumna = sc.nextInt();
                        System.out.print("Nuevo nivel de vida: ");
                        int nuevoNivelVida = sc.nextInt();                       
                        ejercito.crearSoldado(nuevoNombre, nuevoNivelAtaque, nuevoNivelDefensa, nuevaVelocidad, nuevaFila, nuevaColumna, nuevoNivelVida);                      
                        if (soldado.size() < 10) {
                            System.out.print("¿Desea crear otro soldado? (Si/No): ");
                            String respuesta = sc.next().toLowerCase();
                            if (respuesta.equals("no")) {
                                continuarCreando = false;
                                ejercitoCreado = true;
                            }
                        }
                    }
                    break;
                case 3:
                	System.out.println("Saliendo del programa.");
                    salir = true;
                    break;
                case 4:               	                      
                        System.out.print("Seleccione el número del soldado que desea eliminar: ");
                        int numeroSoldado = sc.nextInt();                                    
                        ejercito.eliminarSoldado(numeroSoldado - 1);
                        System.out.println("Soldado eliminado correctamente.");                                         
                    break;
                case 5:
                	ArrayList<Soldado> soldados = ejercito.getMisSoldados();
                	System.out.print("Seleccione el número del soldado que desea modificar: ");
                    int solda = sc.nextInt();                    
                    if (solda >= 1 && solda <= soldados.size()) {
                        sc.nextLine();                      
                        System.out.print("Nuevo nombre: ");
                        String nuevoNombre = sc.nextLine();
                        System.out.print("Nuevo nivel de ataque: ");
                        int nuevoNivelAtaque = sc.nextInt();
                        System.out.print("Nuevo nivel de defensa: ");
                        int nuevoNivelDefensa = sc.nextInt();
                        System.out.print("Nueva velocidad: ");
                        int nuevaVelocidad = sc.nextInt();
                        sc.nextLine();                                               
                        System.out.print("Nueva fila: ");
                        int nuevaFila = sc.nextInt();
                        System.out.print("Nueva columna: ");
                        int nuevaColumna = sc.nextInt();
                        System.out.print("Nuevo nivel de vida: ");
                        int nuevoNivelVida = sc.nextInt();
                        
                        ejercito.modificarSoldado(solda - 1, nuevoNombre, nuevoNivelAtaque, nuevoNivelDefensa, nuevaVelocidad, nuevaFila, nuevaColumna, nuevoNivelVida);
                        System.out.println("Soldado modificado correctamente.");
                    } else {
                        System.out.println("Número de soldado no válido.");
                    }                   
                    break;
                case 6:
                	System.out.println(ejercito.toString());
                    break;
                case 7:
                	Soldado soldadoConMayorAtaque = ejercito.getSoldadoConMayorAtaque();
                    System.out.println("Soldado con mayor nivel de ataque: " + soldadoConMayorAtaque.getNombre());
                    System.out.println("Nivel de ataque: " + soldadoConMayorAtaque.getNivelAtaque());
                    break;
                case 8:
                	ejercito.verRankingPoder();
                    
                    break;
                case 9:
                    System.out.println("Saliendo del programa.");
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }
}