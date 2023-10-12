import java.util.ArrayList;
public class VideoJuego3 {
    public static void main(String[] args) {
    	ArrayList<ArrayList<Soldado>> tablero = new ArrayList<>();
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
        
        for (int i = 0; i < 10; i++) {
            ArrayList<Soldado> fila = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                fila.add(null); 
            }
            tablero.add(fila);
        }
        for (Soldado soldado : ejercito1) {
            int fila = soldado.getFila() - 1;
            int columna = soldado.getColumna() - 1;
            tablero.get(fila).set(columna, soldado);
        }
        for (Soldado soldado : ejercito2) {
            int fila = soldado.getFila() - 1;
            int columna = soldado.getColumna() - 1;
            tablero.get(fila).set(columna, soldado);
        }
        imprimirTablero(tablero, ejercito1, ejercito2);
        String ganador = determinarGanador(ejercito1, ejercito2);
        System.out.println("\n" + ganador);         
    }   
    public static void llenarSoldados1(ArrayList<Soldado> soldados, int n) {
        for (int i = 0; i < n; i++) {
            String nombre = "soldado" + i + "X1";
            int columna = (int) (Math.random() * 10) + 1;
            int fila = (int) (Math.random() * 10) + 1;
            int vida = (int) (Math.random() * 5) + 1;
            Soldado soldado = new Soldado();
            soldado.setNombre(nombre);
            soldado.setFila(fila);
            soldado.setColumna(columna);
            soldado.setNivelVida(vida);
            soldados.add(soldado);
        }
    }
    public static void llenarSoldados2(ArrayList<Soldado> soldados, int n) {
        for (int i = 0; i < n; i++) {
            String nombre = "soldado" + i + "X2";
            int columna = (int) (Math.random() * 10) + 1;
            int fila = (int) (Math.random() * 10) + 1;
            int vida = (int) (Math.random() * 5) + 1;
            Soldado soldado = new Soldado();
            soldado.setNombre(nombre);
            soldado.setFila(fila);
            soldado.setColumna(columna);
            soldado.setNivelVida(vida);
            soldados.add(soldado);
        }
    }

    public static void imprimirSoldados(ArrayList<Soldado> ejercito) {
        for (Soldado soldado : ejercito) {
            System.out.println("nombre: " + soldado.getNombre());
            System.out.println("fila: " + soldado.getFila());
            System.out.println("columna: " + soldado.getColumna());
            System.out.println("vida: " + soldado.getNivelVida());
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
            // Si no hubo intercambios en esta pasada, el arreglo ya está ordenado
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
    public static String determinarGanador(ArrayList<Soldado> ejercito1, ArrayList<Soldado> ejercito2) {
        int sumaVidaEjercito1 = calcularSumaDeVidas(ejercito1);
        int sumaVidaEjercito2 = calcularSumaDeVidas(ejercito2);
        if (sumaVidaEjercito1 > sumaVidaEjercito2) {
            return "Ejército 1 es el ganador con una suma de vida de " + sumaVidaEjercito1;
        } else if (sumaVidaEjercito2 > sumaVidaEjercito1) {
            return "Ejército 2 es el ganador con una suma de vida de " + sumaVidaEjercito2;
        } else {
            return "Empate. Ambos ejércitos tienen la misma suma de vida: " + sumaVidaEjercito1;
        }
    }
    
    public static void imprimirTablero(ArrayList<ArrayList<Soldado>> tablero, ArrayList<Soldado> ejercito1, ArrayList<Soldado> ejercito2) {
        System.out.println(" _ _ _ _ _ _ _ _ _ _");
        for (ArrayList<Soldado> fila : tablero) {
            for (Soldado casilla : fila) {
                if (casilla != null) {
                    char letra = (ejercito1.contains(casilla)) ? 's' : 'c';                  
                    System.out.print("|" + letra);
                } else {              	
                    System.out.print("|_");
                }
            }
            System.out.println("|");
        }
    }
   
}