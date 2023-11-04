// Ejercicio - 1
// Autor: Juan Victor Salas Aguilar
// Colaboro: Marco Aedo
// Tiempo:
import java.util.*;
import java.util.ArrayList;
public class Ejercicio1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[][] tablero = new String[10][10];
        ArrayList<ArrayList<Soldado>> reinos = new ArrayList<>();
        String[] nombresReinos = {"Inglaterra", "Francia", "Sacro", "Castilla", "Aragon", "Moros"};
        String reino1 = nombresReinos[(int)(Math.random() * nombresReinos.length)];
        String reino2;
        do {
            reino2 = nombresReinos[(int)(Math.random() * nombresReinos.length)];
        } while (reino2.equals(reino1));
        crearEjercito(reinos, reino1, tablero);
        crearEjercito(reinos, reino2, tablero);
        int turno = (int)(Math.random() * 2) + 1; 
        boolean juegoActivo = true;
        while (juegoActivo) {
            imprimirTablero(tablero, reinos);
            String jugadorActual = (turno == 1) ? reino1 : reino2;
            System.out.println("Turno del Jugador " + turno + " (" + jugadorActual + ")");
            moverEjercito(sc, tablero, reinos);
            if (reinos.get(0).isEmpty() || reinos.get(1).isEmpty()) {
                juegoActivo = false;
                String ganador = (reinos.get(0).isEmpty()) ? reino2 : reino1;
                System.out.println(ganador + " ha ganado el juego.");
            }

            turno = (turno == 1) ? 2 : 1;
        }
    }
    public static void crearEjercito(ArrayList<ArrayList<Soldado>> reinos, String nombreReino, String[][] tablero) {
        int numEjercitos = (int)(Math.random() * 10) + 1;
        for (int ejercitoId = 1; ejercitoId <= numEjercitos; ejercitoId++) {
            ArrayList<Soldado> ejercito = new ArrayList<>();
            for (int i = 0; i < (int)(Math.random() * 10) + 1; i++) {
            	int f = (int)(Math.random() * 10) + 1;
            	int c = (int)(Math.random() * 10) + 1;
                int x = (int)(Math.random() * 5 + 1);
                String nombreSoldado = "soldado" + i + "X" + nombreReino;
                Soldado soldado = new Soldado();
                soldado.setNombre(nombreSoldado);
                soldado.setNivelAtaque();
                soldado.setFila(f);
                soldado.setColumna(c);
                soldado.setNivelDefensa();
                soldado.setNivelVida(x);
                soldado.setVelocidad();
                ejercito.add(soldado);
            }
            reinos.add(ejercito);
            int fila;
            int columna;
            do {
                fila = (int)(Math.random() * tablero.length);
                columna = (int)(Math.random() * tablero[0].length);
            } while (tablero[fila][columna] != null);
            tablero[fila][columna] = nombreReino + ejercitoId;
        }
    }
    public static void imprimirTablero(String[][] tablero, ArrayList<ArrayList<Soldado>> reinos) {
        System.out.print("  ");
        for (char columna = 'A'; columna <= 'J'; columna++) {
            System.out.print(" " + columna);
        }
        System.out.println();
        System.out.println("   _ _ _ _ _ _ _ _ _ _ ");       
        for (int i = 0; i < tablero.length; i++) {
            System.out.print((i + 1 < 10 ? " " : "") + (i + 1));
            for (int j = 0; j < tablero[i].length; j++) {
                if (tablero[i][j] != null) {
                    String reinoSoldado = tablero[i][j];
                    char letra = reinoSoldado.charAt(0);
                    System.out.print("|" + letra);
                } else {
                    System.out.print("|_");
                }
            }
            System.out.println("|");
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
    public static int calcularSumaDeVidas(ArrayList<Soldado> ejercito) {
        int sumaVidas = 0;
        for (Soldado soldado : ejercito) {
            sumaVidas += soldado.getNivelVida();
        }
        return sumaVidas;
    }   
    public static String batallaEjercito(ArrayList<Soldado> ejercito1, ArrayList<Soldado> ejercito2) {
        int sumaVidaEjercito1 = calcularSumaDeVidas(ejercito1);
        int sumaVidaEjercito2 = calcularSumaDeVidas(ejercito2);
        if (sumaVidaEjercito1 > sumaVidaEjercito2) {
            return ejercito1.get(0).getNombre();
        } else if (sumaVidaEjercito1 < sumaVidaEjercito2) {
            return ejercito2.get(0).getNombre();
        } else {
            return "Empate";
        }
    }    
    public static void moverEjercito(Scanner sc, String[][] tablero, ArrayList<ArrayList<Soldado>> reinos) {
        System.out.print("Ingrese el nombre del reino del cual desea mover ejércitos: ");
        String reino = sc.next();
        int reinoIndex = -1;
        for (int i = 0; i < reinos.size(); i++) {
            ArrayList<Soldado> ejercitoReino = reinos.get(i);
            if (!ejercitoReino.isEmpty() && ejercitoReino.get(0).getNombre().contains(reino)) {
                reinoIndex = i;
                break;
            }
        }
        if (reinoIndex == -1) {
            System.out.println("No se encontró el reino " + reino + ".");
            return;
        }
        ArrayList<Soldado> ejercitoReino = reinos.get(reinoIndex);
        System.out.print("Ingrese las coordenadas del ejército que desea mover (por ejemplo, d3): ");
        String coordenada = sc.next().toUpperCase();
        if (coordenada.length() != 2) {
            System.out.println("Formato de coordenada inválido.");
            return;
        }
        char columnaChar = coordenada.charAt(0);
        int filaMov = Integer.parseInt(coordenada.substring(1)) - 1;
        int columnaMov = columnaChar - 'A';
        if (filaMov < 0 || filaMov >= tablero.length || columnaMov < 0 || columnaMov >= tablero[0].length) {
            System.out.println("Movimiento inválido: coordenada fuera del tablero.");
            return;
        }
        String coordenadaReino = tablero[filaMov][columnaMov];
        if (coordenadaReino == null || !coordenadaReino.contains(reino)) {
            System.out.println("Movimiento inválido: no hay un ejército de " + reino + " en la coordenada seleccionada.");
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
        String coordenadaNueva = tablero[nuevaFila][nuevaColumna];       
        if (coordenadaNueva != null) {
            ArrayList<Soldado> ejercitoEnemigo = null;
            
            for (ArrayList<Soldado> ejercito : reinos) {
                if (ejercito != ejercitoReino && coordenadaNueva.contains(ejercito.get(0).getNombre())) {
                    ejercitoEnemigo = ejercito;
                    break;
                }
            }           
            if (ejercitoEnemigo != null) {
                int sumaVidaEjercitoReino = calcularSumaDeVidas(ejercitoReino);
                int sumaVidaEjercitoEnemigo = calcularSumaDeVidas(ejercitoEnemigo);            
                if (sumaVidaEjercitoReino > sumaVidaEjercitoEnemigo) {                  
                    ejercitoEnemigo.clear();
                } else if (sumaVidaEjercitoEnemigo > sumaVidaEjercitoReino) {
                    ejercitoReino.clear();
                } else {
                    System.out.println("¡Empate! Ambos ejércitos tienen la misma suma de vida.");
                }
            }
        }
        tablero[filaMov][columnaMov] = null;
        tablero[nuevaFila][nuevaColumna] = coordenadaReino;
        ejercitoReino.get(0).setFila(nuevaFila);
        ejercitoReino.get(0).setColumna((char) (nuevaColumna + 'A'));
    }
}