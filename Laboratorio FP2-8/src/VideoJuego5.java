// Ejercicio - 1
// Autor: Juan Victor Salas Aguilar
// Colaboro: Marco Aedo
// Tiempo:
import java.util.ArrayList;
import java.util.HashMap;
public class VideoJuego5 {
    public static void main(String[] args) {
        Soldado[][] tablero = new Soldado[10][10];
        HashMap<String, Soldado> ejercito1 = new HashMap<>();
        HashMap<String, Soldado> ejercito2 = new HashMap<>();
        int n = (int) (Math.random() * 10) + 1;
        int x = (int) (Math.random() * 10) + 1;
        // Ejercito1
        llenarSoldados(ejercito1, n, "X1");
        System.out.println("\nEjercito-1");
        imprimirSoldados(ejercito1);
        System.out.println("\nEl soldado con mayor vida es: ");
        Soldado mayorVida1 = soldadoConMayorVida(ejercito1);
        System.out.println("Nombre: " + mayorVida1.getNombre());
        System.out.println("Vida: " + mayorVida1.getNivelVida());
        double promedioVida1 = calcularPromedioDeVida(ejercito1);
        System.out.println("\nPromedio de vida de todos los soldados: " + promedioVida1);
        int sumaVidas1 = calcularSumaDeVidas(ejercito1);
        System.out.println("\nEl nivel de vida de todo el ejército es: " + sumaVidas1);
        System.out.println("\n--------------------");
        // Ejercito2
        llenarSoldados(ejercito2, x, "X2");
        System.out.println("\nEjercito-2");
        imprimirSoldados(ejercito2);
        System.out.println("\nEl soldado con mayor vida es: ");
        Soldado mayorVida2 = soldadoConMayorVida(ejercito2);
        System.out.println("Nombre: " + mayorVida2.getNombre());
        System.out.println("Vida: " + mayorVida2.getNivelVida());
        double promedioVida2 = calcularPromedioDeVida(ejercito2);
        System.out.println("\nPromedio de vida de todos los soldados: " + promedioVida2);
        int sumaVidas2 = calcularSumaDeVidas(ejercito2);
        System.out.println("\nEl nivel de vida de todo el ejército es: " + sumaVidas2);
     
        for (Soldado soldado : ejercito1.values()) {
            int fila = soldado.getFila() - 1;
            int columna = soldado.getColumna() - 1;
            tablero[fila][columna] = soldado;
        }
        for (Soldado soldado : ejercito2.values()) {
            int fila = soldado.getFila() - 1;
            int columna = soldado.getColumna() - 1;
            tablero[fila][columna] = soldado;
        }
        System.out.println("\nTablero de juego:");
        imprimirTablero(tablero, ejercito1, ejercito2);
        String ganador = determinarGanador(ejercito1, ejercito2);
        System.out.println("\n" + ganador);       
        ordenarEjercitoBurbuja(ejercito1);
        System.out.println("\nEjercito-1 (ordenado por nivel de vida)");
        imprimirSoldados(ejercito1);      
        ordenarEjercitoSeleccion(ejercito2);	
        System.out.println("\nEjercito-2 (ordenado por nivel de vida)");
        imprimirSoldados(ejercito2);
    }

    public static void imprimirTablero(Soldado[][] tablero, HashMap<String, Soldado> ejercito1, HashMap<String, Soldado> ejercito2) {
        System.out.println(" _____________________________");
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                if (tablero[i][j] != null) {
                    Soldado soldado = tablero[i][j];
                    char letra = (ejercito1.containsValue(soldado)) ? 's' : 'c';
                    System.out.print("|" + letra + soldado.getNivelVida());
                } else {
                    System.out.print("|__");
                }
            }
            System.out.println("|");
        }
    }

    public static void llenarSoldados(HashMap<String, Soldado> soldados, int n, String ejercito) {
        for (int i = 0; i < n; i++) {
            String nombre = "soldado" + i + ejercito;
            int columna = (int) (Math.random() * 10) + 1;
            int fila = (int) (Math.random() * 10) + 1;
            int vida = (int) (Math.random() * 5) + 1;
            Soldado soldado = new Soldado();
            soldado.setNombre(nombre);
            soldado.setFila(fila);
            soldado.setColumna(columna);
            soldado.setNivelVida(vida);
            soldados.put(nombre, soldado);
        }
    }

    public static void imprimirSoldados(HashMap<String, Soldado> ejercito) {
        for (Soldado soldado : ejercito.values()) {
            System.out.println("nombre: " + soldado.getNombre());
            System.out.println("fila: " + soldado.getFila());
            System.out.println("columna: " + soldado.getColumna());
            System.out.println("vida: " + soldado.getNivelVida());
        }
        System.out.println();
    }

    public static Soldado soldadoConMayorVida(HashMap<String, Soldado> ejercito) {
        Soldado mayorVida = null;
        int maxVida = 0;
        for (Soldado soldado : ejercito.values()) {
            if (soldado.getNivelVida() > maxVida) {
                maxVida = soldado.getNivelVida();
                mayorVida = soldado;
            }
        }
        return mayorVida;
    }

    public static double calcularPromedioDeVida(HashMap<String, Soldado> ejercito) {
        int totalVida = 0;
        for (Soldado soldado : ejercito.values()) {
            totalVida += soldado.getNivelVida();
        }
        return (double) totalVida / ejercito.size();
    }

    public static int calcularSumaDeVidas(HashMap<String, Soldado> ejercito) {
        int sumaVidas = 0;
        for (Soldado soldado : ejercito.values()) {
            sumaVidas += soldado.getNivelVida();
        }
        return sumaVidas;
    }
    
    public static void ordenamientoBurbuja(HashMap<String, Soldado> ejercito) {
        HashMap<String, Soldado> ejercitoOrdenado = new HashMap<>(ejercito);
        boolean intercambiar = true;
        while (intercambiar) {
            intercambiar = false;
            for (String key1 : ejercito.keySet()) {
                Soldado soldado1 = ejercito.get(key1);
                for (String key2 : ejercito.keySet()) {
                    Soldado soldado2 = ejercito.get(key2);
                    if (soldado1.getNivelVida() < soldado2.getNivelVida()) {                       
                        ejercitoOrdenado.put(key1, soldado2);
                        ejercitoOrdenado.put(key2, soldado1);
                        intercambiar = true; 
                    }
                }
            }
        }
        
        ejercito.clear();
        ejercito.putAll(ejercitoOrdenado);
    }
    public static void ordenarEjercitoSeleccion(HashMap<String, Soldado> ejercito) {
        ArrayList<HashMap.Entry<String, Soldado>> soldados = new ArrayList<>(ejercito.entrySet());
        int n = soldados.size();
        for (int i = 0; i < n - 1; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (soldados.get(j).getValue().getNivelVida() > soldados.get(maxIndex).getValue().getNivelVida()) {
                    maxIndex = j;
                }
            }
            HashMap.Entry<String, Soldado> temp = soldados.get(i);
            soldados.set(i, soldados.get(maxIndex));
            soldados.set(maxIndex, temp);
        }
        ejercito.clear();
        for (HashMap.Entry<String, Soldado> entry : soldados) {
            ejercito.put(entry.getKey(), entry.getValue());
        }
    }
    public static void ordenarEjercitoBurbuja(HashMap<String, Soldado> ejercito) {
        ArrayList<Soldado> soldados = new ArrayList<>(ejercito.values());
        int n = soldados.size();
        boolean intercambiar = true;

        while (intercambiar) {
        	intercambiar = false;
            for (int i = 1; i < n; i++) {
                if (soldados.get(i - 1).getNivelVida() < soldados.get(i).getNivelVida()) {
                    Soldado temp = soldados.get(i - 1);
                    soldados.set(i - 1, soldados.get(i));
                    soldados.set(i, temp);
                    intercambiar = true; 
                }
            }
        }       
        ejercito.clear();
        for (Soldado soldado : soldados) {
            ejercito.put(soldado.getNombre(), soldado);
        }
    }
    public static String determinarGanador(HashMap<String, Soldado> ejercito1, HashMap<String, Soldado> ejercito2) {
        int sumaVidasEjercito1 = calcularSumaDeVidas(ejercito1);
        int sumaVidasEjercito2 = calcularSumaDeVidas(ejercito2);

        if (sumaVidasEjercito1 > sumaVidasEjercito2) {
            return "El ejército 1 es el ganador con un total de vida de " + sumaVidasEjercito1;
        } else if (sumaVidasEjercito2 > sumaVidasEjercito1) {
            return "El ejército 2 es el ganador con un total de vida de " + sumaVidasEjercito2;
        } else {
            return "La batalla terminó en empate, ambos ejércitos tienen el mismo total de vida: " + sumaVidasEjercito1;
        }
    }
}