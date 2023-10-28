// Ejercicio - 1
// Autor: Juan Victor Salas Aguilar
// Colaboro: Marco Aedo
// Tiempo:
import java.util.*;
import java.util.ArrayList;
public class Ejercicio1 {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	int opcion = 0;
        while (opcion != 3) {
            System.out.println("Menú:");
            System.out.println("1. Juego Rápido");
            System.out.println("2. Personalizado");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    juegoRapido();
                    break;
                case 2:
 
                    break;
                case 3:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, elija una opción válida.");
                    break;
            }
        }
    }
    public static void juegoRapido() {  
    	Scanner sc = new Scanner(System.in);
    	Soldado[][] tablero = new Soldado[10][10];
        ArrayList<Soldado> ejercito1 = new ArrayList<>();
        ArrayList<Soldado> ejercito2 = new ArrayList<>();          
        int n = (int) (Math.random() * 10) + 1;
        int x = (int) (Math.random() * 10) + 1;                            
        // Ejercito1
        llenarSoldados1(ejercito1, n);        		
        System.out.println("\nEjercito-1");
        imprimirSoldados(ejercito1);
        System.out.println("\nEl soldado con mayor vida es: ");
        Soldado mayorVida = soldadoConMayorVida(ejercito1);
        System.out.println("Nombre: " + mayorVida.getNombre());
        System.out.println("Vida: " + mayorVida.getNivelVida());
        double promedioVida = calcularPromedioDeVida(ejercito1);
        System.out.println("\nPromedio de vida de todos los soldados: " + promedioVida);
        int sumaVidas = calcularSumaDeVidas(ejercito1);
        System.out.println("\nEl nivel de vida de todo el ejército es: " + sumaVidas);
        ordenamientoBurbuja(ejercito1);      
        System.out.println("\n--------------------");
        //Ejercito2
        llenarSoldados2(ejercito2, x);        
        System.out.println("\nEjercito-2");
        imprimirSoldados(ejercito2);
        System.out.println("\nEl soldado con mayor vida es: ");
        Soldado mayorV = soldadoConMayorVida(ejercito2);
        System.out.println("Nombre: " + mayorV.getNombre());
        System.out.println("Vida: " + mayorV.getNivelVida());
        double promedioV = calcularPromedioDeVida(ejercito2);
        System.out.println("\nPromedio de vida de todos los soldados: " + promedioV);
        int sumaV = calcularSumaDeVidas(ejercito2);
        System.out.println("\nEl nivel de vida de todo el ejército es: " + sumaV);
        ordenamientoSeleccion(ejercito2);              
        for (Soldado soldado : ejercito1) {
            int fila = soldado.getFila() - 1; 
            int columna = soldado.getColumna() - 1;
            tablero[fila][columna] = soldado;
        }
        for (Soldado soldado : ejercito2) {
            int fila = soldado.getFila() - 1; 
            int columna = soldado.getColumna() - 1;
            tablero[fila][columna] = soldado;
        }       
        boolean juegoActivo = true;
        while (juegoActivo) {
            System.out.println("\nTablero de juego:");
            imprimirTablero(tablero, ejercito1, ejercito2);         
            System.out.println("Turno del Jugador 1 - S ");
            moverSoldado(sc, tablero, ejercito1, ejercito2);
            if (ejercito2.isEmpty()) {
                System.out.println("¡El Jugador 1 ha ganado!");
                break;
            }
            System.out.println("Tablero de juego:");
            imprimirTablero(tablero, ejercito1, ejercito2);
            System.out.println("Turno del Jugador 2 - C ");
            moverSoldado(sc, tablero, ejercito2, ejercito1);
            if (ejercito1.isEmpty()) {
                System.out.println("¡El Jugador 2 ha ganado!");
                break;
            }
        }
    }
    
    public static void imprimirTablero(Soldado[][] tablero, ArrayList<Soldado> ejercito1, ArrayList<Soldado> ejercito2) {
        System.out.print("  ");
        for (char columna = 'A'; columna <= 'J'; columna++) {
            System.out.print("  " + columna);
        } 
        System.out.println();
        System.out.println("   __ __ __ __ __ __ __ __ __ __ ");

        for (int i = 0; i < tablero.length; i++) {
        	System.out.print((i + 1 < 10 ? " " : "") + (i + 1) );
            for (int j = 0; j < tablero[i].length; j++) {
                if (tablero[i][j] != null) {
                    Soldado soldado = tablero[i][j];
                    char letra = (ejercito1.contains(soldado)) ? 's' : 'c';
                    System.out.print("|" + letra + soldado.getNivelVida());
                } else {
                    System.out.print("|__");
                }
            }
            System.out.println("|");
        }
    }   
    public static void llenarSoldados1(ArrayList<Soldado> soldados, int n) {
        for (int i = 0; i < n; i++) {
        	int x = (int)(Math.random() * 5 + 1);
            String nombre = "soldado" + i + "X1";            
            Soldado soldado = new Soldado();
            soldado.setNombre(nombre);
            soldado.setFila();
            soldado.setColumna();
            soldado.setNivelAtaque();
            soldado.setNivelDefensa();
            soldado.setNivelVida(x);
            soldado.setVelocidad();
            soldados.add(soldado);
        }
    }   
    public static void llenarSoldados2(ArrayList<Soldado> soldados, int n) {
        for (int i = 0; i < n; i++) {
        	int x = (int)(Math.random() * 5 + 1);
            String nombre = "soldado" + i + "X2";         
            Soldado soldado = new Soldado();
            soldado.setNombre(nombre);
            soldado.setFila();
            soldado.setColumna();
            soldado.setNivelAtaque();
            soldado.setNivelDefensa();
            soldado.setNivelVida(x);
            soldado.setVelocidad();
            soldados.add(soldado);
        }
    }
    public static void imprimirSoldados(ArrayList<Soldado> ejercito) {
        for (Soldado soldado : ejercito) {
            System.out.println("nombre: " + soldado.getNombre());
            System.out.println("fila: " + soldado.getFila());
            System.out.println("columna: " + soldado.getColumna());
            System.out.println("vida: " + soldado.getNivelVida());
            System.out.println("nivel de Ataque: " + soldado.getNivelAtaque());
            System.out.println("nivel de Defensa: " + soldado.getNivelDefensa());
            System.out.println("velocidad: " + soldado.getVelocidad());
        }
        System.out.println();
    }
    public static Soldado soldadoConMayorVida(ArrayList<Soldado> ejercito) {
        Soldado mayorVida = null;
        int maxVida = 0;
        for (Soldado soldado : ejercito) {
            if (soldado.getNivelVida() > maxVida) {
                maxVida = soldado.getNivelVida();
                mayorVida = soldado;
            }
        }
        return mayorVida;
    }
    public static double calcularPromedioDeVida(ArrayList<Soldado> ejercito) {
        int totalVida = 0;
        for (Soldado soldado : ejercito) {
            totalVida += soldado.getNivelVida();
        }
        return (double) totalVida / ejercito.size();
    }
    public static int calcularSumaDeVidas(ArrayList<Soldado> ejercito) {
        int sumaVidas = 0;
        for (Soldado soldado : ejercito) {
            sumaVidas += soldado.getNivelVida();
        }
        return sumaVidas;
    }
    public static void ordenamientoBurbuja(ArrayList<Soldado> ejercito) {
    	System.out.println("\nRanking de poder por ordenamiento burbuja:");
        int n = ejercito.size();
        boolean ordenado;
        for (int i = 0; i < n - 1; i++) {
            ordenado = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (ejercito.get(j).getNivelVida() < ejercito.get(j + 1).getNivelVida()) {
                    Soldado temp = ejercito.get(j);
                    ejercito.set(j, ejercito.get(j + 1));
                    ejercito.set(j + 1, temp);
                    ordenado = true;
                }
            }
            if (!ordenado) {
                break;
            }
        }
        for (int i = 0; i < ejercito.size(); i++) {
            System.out.println("Posición " + (i + 1) + ": " + ejercito.get(i).getNombre() + " - Vida: " + ejercito.get(i).getNivelVida());
        }
    }

    public static void ordenamientoSeleccion(ArrayList<Soldado> ejercito) {
    	System.out.println("\nRanking de poder por ordenamiento de selección:");
        int n = ejercito.size();
        for (int i = 0; i < n - 1; i++) {
            int max = i;
            for (int j = i + 1; j < n; j++) {
                if (ejercito.get(j).getNivelVida() > ejercito.get(max).getNivelVida()) {
                    max = j;
                }
            }
            Soldado temp = ejercito.get(i);
            ejercito.set(i, ejercito.get(max));
            ejercito.set(max, temp);
        }
        for (int i = 0; i < ejercito.size(); i++) {
            System.out.println("Posición " + (i + 1) + ": " + ejercito.get(i).getNombre() + " - Vida: " + ejercito.get(i).getNivelVida());
        }
    }
    public static void moverSoldado(Scanner sc, Soldado[][] tablero, ArrayList<Soldado> ejercitoMov, ArrayList<Soldado> ejercitoOtro) {
        System.out.print("Ingrese la coordenada del soldado a mover (por ejemplo, d3): ");
        String coordenada = sc.next();
        int filaMov = Integer.parseInt(coordenada.substring(1)) - 1;
        char columnaChar = coordenada.charAt(0);
        int columnaMov = columnaChar - 'a';
        if (filaMov < 0 || filaMov >= tablero.length || columnaMov < 0 || columnaMov >= tablero[0].length) {
            System.out.println("Movimiento inválido: coordenada fuera del tablero.");
            return;
        }
        Soldado soldado = tablero[filaMov][columnaMov];
        if (soldado == null || !ejercitoMov.contains(soldado)) {
            System.out.println("Movimiento inválido: no hay un soldado del ejército correspondiente en esa posición.");
            return;
        }
        System.out.print("Ingrese la dirección del movimiento (w: arriba, a: izquierda, s: abajo, d: derecha): ");
        char direccion = sc.next().charAt(0);
        int nuevaFila = filaMov;
        int nuevaColumna = columnaMov;
        if (direccion == 'w') {
            nuevaFila--;
        } else if (direccion == 'a') {
            nuevaColumna--;
        } else if (direccion == 's') {
            nuevaFila++;
        } else if (direccion == 'd') {
            nuevaColumna++;
        } else {
            System.out.println("Movimiento inválido: dirección no válida.");
            return;
        }
        if (nuevaFila < 0 || nuevaFila >= tablero.length || nuevaColumna < 0 || nuevaColumna >= tablero[0].length) {
            System.out.println("Movimiento inválido: movimiento fuera del tablero.");
            return;
        }
        if (tablero[nuevaFila][nuevaColumna] != null) {
            Soldado soldadoEnNuevaPos = tablero[nuevaFila][nuevaColumna];
            if (ejercitoOtro.contains(soldadoEnNuevaPos)) {
                double probabilidadSoldado = soldado.getNivelVida() * 100.0 / (soldado.getNivelVida() + soldadoEnNuevaPos.getNivelVida());
                double probabilidadEnemigo = soldadoEnNuevaPos.getNivelVida() * 100.0 / (soldado.getNivelVida() + soldadoEnNuevaPos.getNivelVida());
                Soldado ganadorBatalla = batalla(soldado, soldadoEnNuevaPos);
                System.out.println("¡Batalla!");
                System.out.println(soldado.getNombre() + " - Vida: " + soldado.getNivelVida() + " - Probabilidad de victoria: " + probabilidadSoldado + "%");
                System.out.println(soldadoEnNuevaPos.getNombre() + " - Vida: " + soldadoEnNuevaPos.getNivelVida() + " - Probabilidad de victoria: " + probabilidadEnemigo + "%");

                if (ganadorBatalla == soldado) {
                    tablero[nuevaFila][nuevaColumna] = soldado;
                    tablero[filaMov][columnaMov] = null;
                    ejercitoOtro.remove(soldadoEnNuevaPos);
                    soldado.setNivelVida(soldado.getNivelVida() + 1);
                } else {
                	tablero[filaMov][columnaMov] = null;
                    ejercitoMov.remove(soldado);
                    soldadoEnNuevaPos.setNivelVida(soldadoEnNuevaPos.getNivelVida() + 1);
                }
                System.out.println("Ganador: " + ganadorBatalla.getNombre() + " - Vida: " + ganadorBatalla.getNivelVida());
                if (ejercitoOtro.isEmpty()) {
                    System.out.println("¡El ejército " + ejercitoMov.get(0).getNombre().substring(0, ejercitoMov.get(0).getNombre().length() - 2) + " ha ganado!");
                }
                return;
            } else {
                System.out.println("Movimiento inválido: ya hay un soldado en la nueva posición.");
                return;
            }
        }
        tablero[filaMov][columnaMov] = null;
        tablero[nuevaFila][nuevaColumna] = soldado;
    }
    public static Soldado batalla(Soldado soldado1, Soldado soldado2) {
        int totalVida = soldado1.getNivelVida() + soldado2.getNivelVida();
        double porcentaje1 = (soldado1.getNivelVida() * 100.0) / totalVida;
        double porcentaje2 = (soldado2.getNivelVida() * 100.0) / totalVida;
        int numeroAleatorio = (int) (Math.random() * 100) + 1;
        if (numeroAleatorio <= porcentaje1) {
            return soldado1;
        } else {
            return soldado2;
        }
    }
    public static void buscarSoldadoPorNombre(String nombreSoldado, ArrayList<Soldado> ejercito1, ArrayList<Soldado> ejercito2) {
        boolean encontrado = false;
        for (Soldado soldado : ejercito1) {
            if (soldado.getNombre().equals(nombreSoldado)) {
                imprimirCaracteristicasSoldado(soldado);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            for (Soldado soldado : ejercito2) {
                if (soldado.getNombre().equals(nombreSoldado)) {
                    imprimirCaracteristicasSoldado(soldado);
                    encontrado = true;
                    break;
                }
            }
        }
        if (!encontrado) {
            System.out.println("Soldado no encontrado");
        }
    }
    public static void imprimirCaracteristicasSoldado(Soldado soldado) {
        System.out.println("Características del soldado:");
        System.out.println("Nombre: " + soldado.getNombre());
        System.out.println("Fila: " + soldado.getFila());
        System.out.println("Columna: " + soldado.getColumna());
        System.out.println("Vida: " + soldado.getNivelVida());
        System.out.println("Nivel de Ataque: " + soldado.getNivelAtaque());
        System.out.println("Nivel de Defensa: " + soldado.getNivelDefensa());
        System.out.println("Velocidad: " + soldado.getVelocidad());
    }
}